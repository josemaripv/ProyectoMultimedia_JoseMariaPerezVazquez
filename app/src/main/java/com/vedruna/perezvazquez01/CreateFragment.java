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

import com.vedruna.perezvazquez01.dto.PersonajeDTO;
import com.vedruna.perezvazquez01.interfaces.CRUDInterface;
import com.vedruna.perezvazquez01.model.Personaje;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * El fragmento `CreateFragment` representa la pantalla de creación de personajes en la aplicación.
 *
 * Permite al usuario ingresar información como el nombre, descripción y URL de la imagen del personaje.
 * Al hacer clic en el botón de actualización, se envían los datos al servidor mediante una solicitud Retrofit,
 * creando así un nuevo personaje en la base de datos.
 *
 * Utiliza la interfaz `CRUDInterface` para comunicarse con el servidor y realizar operaciones CRUD.
 */
public class CreateFragment extends Fragment {

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
     * Botón para iniciar el proceso de creación del personaje.
     */
    Button button;

    /**
     * Interfaz para realizar operaciones CRUD (Create, Read, Update, Delete) en el servidor.
     */
    CRUDInterface crudInterface;

    /**
     * Constructor vacío requerido por la arquitectura de fragmentos de Android.
     */
    public CreateFragment() {
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

        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_create, container, false);

        // Inicializar los EditText
        nameText = rootView.findViewById(R.id.editTextNombre);
        descriptionText = rootView.findViewById(R.id.editTextDescription);
        editTextUrlImagen = rootView.findViewById(R.id.editTextUrlImagen);

        // Inicializar el botón
        button = rootView.findViewById(R.id.buttonActualizarPersonaje);

        // Agregar un listener al botón para manejar clics
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nameText.getText().toString();
                String descripcion = descriptionText.getText().toString();
                String url = editTextUrlImagen.getText().toString();

                // Validar que todos los campos estén completos
                if (nombre.isEmpty() || descripcion.isEmpty() || url.isEmpty()) {
                    mostrarToast("Por favor, complete todos los campos.");
                } else {
                    // Crear un objeto PersonajeDTO con los datos ingresados
                    PersonajeDTO personaje = new PersonajeDTO(nombre, descripcion, url);
                    // Iniciar el proceso de creación del personaje
                    create(personaje);
                }
            }
        });

        return rootView;
    }

    /**
     * Método que realiza la creación de un nuevo personaje en el servidor mediante Retrofit.
     *
     * @param personajeDTO Objeto que contiene la información del personaje a crear.
     */
    private void create(PersonajeDTO personajeDTO) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.145:8080/").
                addConverterFactory(GsonConverterFactory.create()).
                build();

        // Inicializar la interfaz CRUDInterface
        crudInterface = retrofit.create(CRUDInterface.class);

        // Realizar la llamada al servidor para crear el personaje
        Call<Personaje> call = crudInterface.create(personajeDTO);

        call.enqueue(new Callback<Personaje>() {
            @Override
            public void onResponse(Call<Personaje> call, Response<Personaje> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err ", response.message());
                    return;
                }
                // Mostrar un mensaje de éxito en caso de una respuesta exitosa
                Personaje personaje = response.body();
                mostrarToast("Personaje añadido correctamente: " + personaje.getNombre());
            }

            @Override
            public void onFailure(Call<Personaje> call, Throwable t) {
                // Manejar fallos en la llamada al servidor
                Log.e("Throw err:", t.getMessage());
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
