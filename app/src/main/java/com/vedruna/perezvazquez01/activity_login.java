package com.vedruna.perezvazquez01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
/**
 * La clase `activity_login` es la pantalla de inicio de sesión de la aplicación.
 *
 * Esta actividad permite a los usuarios iniciar sesión a través de credenciales de usuario o mediante Google Sign-In.
 * Utiliza Firebase Authentication para la autenticación.
 *
 * Algunas de las funcionalidades incluyen:
 * - Inicio de sesión con Google
 * - Inicio de sesión con credenciales de usuario (admin/admin)
 * - Redirección a la pantalla principal (MainActivity) después de un inicio de sesión exitoso.
 *
 * Para el inicio de sesión con Google, se utiliza la API de Google SignIn y Firebase Authentication.
 * Se realiza la autenticación con Google y, en caso de éxito, se redirige al usuario a la pantalla principal.
 * Si el inicio de sesión falla, se muestra un mensaje de error en el registro (log).
 *
 * La pantalla también permite el inicio de sesión manual mediante credenciales de usuario (actualmente configuradas
 * como "admin" para ambos usuario y contraseña). Si las credenciales son correctas, el usuario es redirigido
 * a la pantalla principal.
 */
public class activity_login extends AppCompatActivity {

    /**
     * Campo de texto para el nombre de usuario.
     */
    private TextInputEditText usuario;

    /**
     * Campo de texto para la contraseña.
     */
    private TextInputEditText contraseña;

    /**
     * Código de solicitud para el inicio de sesión con Google.
     */
    private static final int RC_SIGN_IN = 1;

    /**
     * Cliente para el inicio de sesión con Google.
     */
    private GoogleSignInClient mGoogleSignInClient;

    /**
     * Objeto de Firebase Authentication.
     */
    private FirebaseAuth mAuth;

    /**
     * Etiqueta para mensajes de registro (log).
     */
    private static final String TAG = "activity_login";

    /**
     * Botón de inicio de sesión con Google.
     */
    SignInButton signInButton;

    /**
     * Método llamado al crear la actividad.
     * Realiza la inicialización de los componentes de la interfaz de usuario, configura el botón de inicio
     * de sesión con Google, y establece los listeners necesarios.
     *
     * @param savedInstanceState Datos de estado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // Inicialización de los campos de texto y el botón de inicio de sesión con Google
        usuario = findViewById(R.id.texto_Nombre);
        contraseña = findViewById(R.id.texto_Password);
        signInButton = findViewById(R.id.btnGoogle);

        // Inicialización de Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Configuración de las opciones de inicio de sesión con Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Configuración del cliente de inicio de sesión con Google
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Configuración del listener para el botón de inicio de sesión con Google
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    /**
     * Método llamado al iniciar la actividad.
     * Verifica si el usuario ya ha iniciado sesión y actualiza la interfaz de usuario en consecuencia.
     */
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /**
     * Inicia el proceso de inicio de sesión con Google.
     */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * Método llamado después de completar la actividad de inicio de sesión con Google.
     * Verifica el resultado de la operación y realiza la autenticación en Firebase si la operación fue exitosa.
     *
     * @param requestCode Código de solicitud.
     * @param resultCode  Código de resultado.
     * @param data        Datos de la actividad.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Resultado devuelto al lanzar la actividad de inicio de sesión con Google
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Operación de inicio de sesión con Google exitosa
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Fallo en la operación de inicio de sesión con Google
                Log.e(TAG, "Google Sign In failed. Error code: " + e.getStatusCode());
            }
        }
    }

    /**
     * Autentica al usuario en Firebase utilizando la credencial de Google.
     *
     * @param idToken Token de identificación de Google.
     */
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Autenticación con Firebase exitosa
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // Fallo en la autenticación con Firebase
                            Log.e(TAG, "Firebase authentication failed.", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    /**
     * Actualiza la interfaz de usuario después de un inicio de sesión (o fallo) exitoso.
     *
     * @param user Usuario autenticado.
     */
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // Autenticación con Firebase exitosa
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            goHome();
        } else {
            // Verificar si el cierre de sesión fue la causa
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                // Cierre de sesión, no mostrar Toast
                return;
            }

            // Otro escenario de fallo en la autenticación con Firebase
            Toast.makeText(this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Redirige a la pantalla principal (MainActivity) después de un inicio de sesión exitoso.
     */
    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Método invocado al hacer clic en el botón de inicio de sesión con credenciales de usuario.
     *
     * @param view Vista del botón.
     */
    public void login(View view) {
        String user = usuario.getText().toString();
        String password = contraseña.getText().toString();
        if (user.equals("admin") && password.equals("admin")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("usuario", user);
            startActivity(intent);
        }
    }
}
