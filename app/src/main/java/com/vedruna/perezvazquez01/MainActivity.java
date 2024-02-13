package com.vedruna.perezvazquez01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
/**
 * La clase `MainActivity` representa la actividad principal de la aplicación.
 *
 * Contiene un `BottomNavigationView` para la navegación entre fragmentos.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Método llamado al crear la actividad.
     *
     * Configura la interfaz de usuario y la navegación en la actividad principal.
     *
     * - Obtiene la referencia al `BottomNavigationView` desde el diseño.
     * - Establece el fragmento de inicio como seleccionado.
     * - Obtiene el fragmento de navegación anidado.
     * - Obtiene el controlador de navegación asociado al fragmento de navegación.
     * - Configura el listener para los elementos del `BottomNavigationView`, navegando al fragmento correspondiente
     *   según el elemento seleccionado.
     *
     * @param savedInstanceState Datos de estado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener la referencia al BottomNavigationView desde el diseño
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // Establecer el fragmento de inicio como seleccionado
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // Obtener el fragmento de navegación anidado
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        // Obtener el controlador de navegación asociado al fragmento de navegación
        NavController navController = navHostFragment.getNavController();

        // Configurar el listener para los elementos del BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            // Navegar al fragmento correspondiente según el elemento seleccionado
            if (item.getItemId() == R.id.navigation_home) {
                navController.navigate(R.id.homeFragment);
            } else if (item.getItemId() == R.id.navigation_create) {
                navController.navigate(R.id.CreateFragment);
            } else if(item.getItemId() == R.id.navigation_update){
                navController.navigate(R.id.UpdateFragment);
            }else if(item.getItemId() == R.id.navigation_delete){
                navController.navigate(R.id.deleteFragment);
            } else {
                navController.navigate(R.id.exitFragment);
            }
            return true;
        });
    }
}
