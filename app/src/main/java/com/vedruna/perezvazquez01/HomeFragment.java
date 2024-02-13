package com.vedruna.perezvazquez01;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vedruna.perezvazquez01.adapters.PersonajeAdapter;
import com.vedruna.perezvazquez01.interfaces.CRUDInterface;
import com.vedruna.perezvazquez01.model.Personaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * El fragmento `HomeFragment` representa la pantalla principal de la aplicación.
 *
 * Muestra una lista de personajes obtenidos del servidor mediante la interfaz `CRUDInterface`.
 */
public class HomeFragment extends Fragment {

    /**
     * Lista que contiene objetos de la clase Personaje.
     */
    List<Personaje> personajeList;

    /**
     * Instancia de la interfaz CRUDInterface para realizar operaciones CRUD (Create, Read, Update, Delete) en el servidor.
     */
    CRUDInterface crudInterface;

    /**
     * Vista de lista utilizada para mostrar la lista de personajes.
     */
    ListView listView;

    /**
     * Constructor vacío requerido por la arquitectura de fragmentos de Android.
     */
    public HomeFragment() {
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
     * Método para obtener la lista de personajes del servidor mediante Retrofit y actualizar la interfaz de usuario.
     */
    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.145:8080/").
                addConverterFactory(GsonConverterFactory.create()).build();
        crudInterface = retrofit.create(CRUDInterface.class);
        Call<List<Personaje>> call = crudInterface.getAll();
        call.enqueue(new Callback<List<Personaje>>() {
            @Override
            public void onResponse(Call<List<Personaje>> call, Response<List<Personaje>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err ", response.message());
                    return;
                }
                // Obtener la lista de personajes del cuerpo de la respuesta
                personajeList = response.body();
                // Crear un adaptador personalizado para la lista de personajes
                PersonajeAdapter productAdapter = new PersonajeAdapter(requireContext(), personajeList);
                // Establecer el adaptador en la vista de lista
                listView.setAdapter(productAdapter);
                // Imprimir información de los personajes en el registro
                personajeList.forEach(p -> Log.i("Personajes: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Personaje>> call, Throwable t) {
                // Manejar fallos en la llamada al servidor
                Log.e("Throw err:", t.getMessage());
            }
        });
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.listView);

        // Obtener la lista de personajes y actualizar la interfaz de usuario
        getAll();
        return view;
    }
}
