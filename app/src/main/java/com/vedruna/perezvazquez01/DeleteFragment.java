package com.vedruna.perezvazquez01;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedruna.perezvazquez01.interfaces.CRUDInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * El fragmento `DeleteFragment` representa la pantalla de eliminación de personajes en la aplicación.
 *
 * Permite al usuario ingresar el ID del personaje que desea eliminar y luego proceder con el borrado.
 * Utiliza la interfaz `CRUDInterface` para comunicarse con el servidor y realizar operaciones CRUD.
 */
public class DeleteFragment extends Fragment {

    /**
     * Interfaz para realizar operaciones CRUD (Create, Read, Update, Delete) en el servidor.
     */
    CRUDInterface crudInterface;

    /**
     * Botón para iniciar el proceso de eliminación del personaje.
     */
    Button button;

    /**
     * Campo de texto para ingresar el ID del personaje que se desea eliminar.
     */
    EditText idEditText;

    /**
     * Constructor vacío requerido por la arquitectura de fragmentos de Android.
     */
    public DeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Método llamado al crear el fragmento.
     *
     * @param savedInstanceState Datos de estado del fragmento.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método llamado para crear y devolver la vista del fragmento.
     * Infla el diseño del fragmento y configura los elementos de la interfaz de usuario.
     *
     * @param inflater           Inflador utilizado para inflar la vista.
     * @param container          Contenedor que contendrá la vista del fragmento.
     * @param savedInstanceState Datos de estado del fragmento.
     * @return La vista del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);

        // Inicializar el EditText para el ID
        idEditText = view.findViewById(R.id.editTextIDdelete);

        // Configurar el botón de borrado con el clic
        setupDeleteButton(view);

        return view;
    }

    /**
     * Configura el botón de borrado para manejar clics del usuario.
     *
     * @param view Vista que contiene el botón de borrado.
     */
    private void setupDeleteButton(View view) {
        button = view.findViewById(R.id.buttonSalir);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el ID ingresado por el usuario
                String idString = idEditText.getText().toString().trim();

                if (!idString.isEmpty()) {
                    // Convertir el ID a entero
                    int personajeId = Integer.parseInt(idString);
                    // Iniciar el proceso de eliminación del personaje
                    delete(personajeId);
                } else {
                    // Mostrar un Toast si el ID está vacío
                    mostrarToast("Ingrese un ID antes de borrar");
                }
            }
        });
    }

    /**
     * Realiza la eliminación de un personaje en el servidor mediante Retrofit.
     *
     * @param id ID del personaje que se desea eliminar.
     */
    private void delete(int id) {
        // Construir la instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.145:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear la interfaz CRUDInterface
        crudInterface = retrofit.create(CRUDInterface.class);

        // Llamar al método de borrado con el ID del personaje
        Call<Void> call = crudInterface.delete(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Mostrar un Toast si el borrado es exitoso
                    mostrarToast("Personaje eliminado con éxito");
                    // Puedes realizar acciones adicionales si es necesario
                } else {
                    // Mostrar un Toast si hay un error en la respuesta
                    Log.e("Response err ", response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Mostrar un Toast si hay un error de conexión
                Log.e("Throw err:", t.getMessage());
                mostrarToast("Error al intentar borrar el personaje");
            }
        });
    }

    /**
     * Método para mostrar un mensaje Toast en la interfaz de usuario.
     *
     * @param mensaje Mensaje que se mostrará en el Toast.
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
