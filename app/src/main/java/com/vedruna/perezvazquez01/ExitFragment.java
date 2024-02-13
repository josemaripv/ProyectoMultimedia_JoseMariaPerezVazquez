package com.vedruna.perezvazquez01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * El fragmento `ExitFragment` representa la pantalla de salida en la aplicación.
 *
 * Permite al usuario cerrar sesión y regresar a la pantalla de inicio de sesión.
 * Utiliza Firebase Authentication para gestionar la sesión del usuario.
 */
public class ExitFragment extends Fragment {

    /**
     * Instancia de FirebaseAuth para gestionar la autenticación del usuario.
     */
    FirebaseAuth firebaseAuth;

    /**
     * Constructor vacío requerido por la arquitectura de fragmentos de Android.
     */
    public ExitFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_exit, container, false);

        // Aquí puedes agregar cualquier lógica adicional que necesites para el fragmento de salida
        firebaseAuth = FirebaseAuth.getInstance();

        // Configurar el listener para el botón de salida
        view.findViewById(R.id.buttonSalir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        return view;
    }

    /**
     * Método para cerrar sesión y regresar a la pantalla de inicio de sesión.
     */
    private void logOut() {
        // Cerrar sesión utilizando Firebase Authentication
        firebaseAuth.signOut();
        // Navegar de vuelta a la pantalla de inicio de sesión
        backToLogin();
    }

    /**
     * Método para iniciar una nueva actividad que represente la pantalla de inicio de sesión.
     */
    private void backToLogin() {
        Intent intent = new Intent(getActivity(), activity_login.class);
        startActivity(intent);
    }
}
