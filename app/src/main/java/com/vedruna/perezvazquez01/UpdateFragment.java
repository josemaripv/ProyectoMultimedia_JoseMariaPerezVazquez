package com.vedruna.perezvazquez01;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedruna.perezvazquez01.dto.PersonajeDTO;
import com.vedruna.perezvazquez01.interfaces.CRUDInterface;
import com.vedruna.perezvazquez01.model.Personaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * La clase `UpdateFragment` representa un fragmento utilizado para actualizar información de un personaje.
 * Extiende la clase `Fragment` de Android.
 */
public class UpdateFragment extends Fragment {

    /**
     * Variables de vista para manejar elementos de la interfaz de usuario en el fragmento de actualización.
     */
    /**
     * Campo de texto para el nombre del personaje.
     */
    EditText nameText;

    /**
     * Campo de texto para la descripción del personaje.
     */
    EditText descriptionText;

    /**
     * Campo de texto para la URL de la imagen del personaje.
     */
    EditText editTextUrlImagen;

    /**
     * Botón utilizado para iniciar la actualización del personaje.
     */
    Button button;

    /**
     * Campo de texto para ingresar el ID del personaje que se desea actualizar.
     */
    EditText idText;

    /**
     * Variables para manejar la comunicación con la API a través de Retrofit.
     */
    /**
     * Instancia de Retrofit utilizada para configurar la conexión a la API.
     */
    private Retrofit retrofit;

    /**
     * Interfaz que define métodos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la API.
     */
    CRUDInterface crudInterface;


    /**
     * Constructor público por defecto requerido por Android.
     * No realiza ninguna operación especial.
     */
    public UpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Método llamado para crear y devolver la vista asociada al fragmento.
     *
     * @param inflater           Objeto utilizado para inflar cualquier vista en la jerarquía de vistas.
     * @param container          Si no es nulo, es el grupo al que se adjuntará la vista después de inflarse.
     * @param savedInstanceState Bundle que contiene el estado previamente guardado del fragmento, si lo hay.
     * @return La vista asociada al fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);

        // Inicializar los EditText y Retrofit
        idText = rootView.findViewById(R.id.editTextID);  // Nuevo EditText para el ID
        nameText = rootView.findViewById(R.id.editTextNombre);
        descriptionText = rootView.findViewById(R.id.editTextDescription);
        editTextUrlImagen = rootView.findViewById(R.id.editTextUrlImagen);

        retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.145:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Inicializar el botón
        button = rootView.findViewById(R.id.buttonActualizarPersonaje);

        // Puedes agregar un listener al botón si deseas manejar clics
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });

        return rootView;
    }

    /**
     * Método utilizado para realizar la actualización de un personaje mediante una llamada a la API.
     * Se obtienen los valores de los campos de entrada, se verifica si están vacíos y se crea un
     * objeto
     * {@link PersonajeDTO} con los datos proporcionados. Luego, se llama al método
     * {@link CRUDInterface#actualizar(int, PersonajeDTO)}
     * para realizar la actualización.
     */
    private void actualizar() {
        // Obtener los valores de los EditText
        String id = idText.getText().toString().trim();
        String nombre = nameText.getText().toString().trim();
        String descripcion = descriptionText.getText().toString().trim();
        String imagen = editTextUrlImagen.getText().toString().trim();

        // Verificar si algún campo está vacío
        if (id.isEmpty() || nombre.isEmpty() || descripcion.isEmpty() || imagen.isEmpty()) {
            // Mostrar un Toast indicando que todos los campos son obligatorios
            mostrarToast("Todos los campos son obligatorios. Por favor, rellénelos.");
            return;  // Salir del método sin continuar con la actualización
        }

        // Crear un objeto PersonajeDTO en lugar de Personaje
        PersonajeDTO personajeDTO = new PersonajeDTO(nombre, descripcion, imagen);

        crudInterface = retrofit.create(CRUDInterface.class);

        // Llamar al método actualizar con el DTO y el ID
        Call<Personaje> call = crudInterface.actualizar(Integer.parseInt(id), personajeDTO);
        // Continuar con la ejecución de la llamada asíncrona
        call.enqueue(new Callback<Personaje>() {
            /**
             * Se llama cuando la respuesta de la API es exitosa (código de respuesta HTTP en el
             * rango [200, 300)indican que la solicitud fue exitosa).
             *
             * @param call     La llamada que generó esta respuesta.
             * @param response La respuesta de la API que contiene el personaje actualizado.
             */
            @Override
            public void onResponse(Call<Personaje> call, Response<Personaje> response) {
                if (!response.isSuccessful()) {
                    // Loggear un mensaje de error si la respuesta no es exitosa
                    Log.e("Response err ", response.message());
                    return;
                }
                // Obtener el personaje actualizado de la respuesta
                Personaje personaje = response.body();
                // Realizar acciones adicionales si es necesario con el personaje actualizado
                mostrarToast("Personaje actualizado: " + personaje.getNombre());
            }

            /**
             * Se llama cuando la llamada a la API falla por alguna razón.
             *
             * @param call La llamada que generó esta falla.
             * @param t    El objeto Throwable que indica la causa de la falla.
             */
            @Override
            public void onFailure(Call<Personaje> call, Throwable t) {
                // Loggear un mensaje de error y mostrar un Toast indicando la falla al actualizar el personaje
                Log.e("Throw err:", t.getMessage());
                mostrarToast("Error al actualizar el personaje");
            }
        });
    }


    /**
     * Método utilizado para mostrar un Toast con el mensaje proporcionado.
     *
     * @param mensaje Mensaje a mostrar en el Toast.
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
