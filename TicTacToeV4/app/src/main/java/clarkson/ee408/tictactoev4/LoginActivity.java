package clarkson.ee408.tictactoev4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import clarkson.ee408.tictactoev4.client.AppExecutors;
import clarkson.ee408.tictactoev4.client.SocketClient;
import clarkson.ee408.tictactoev4.model.User;
import clarkson.ee408.tictactoev4.socket.GamingResponse;
import clarkson.ee408.tictactoev4.socket.Request;
import clarkson.ee408.tictactoev4.socket.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Getting UI elements
        Button loginButton = findViewById(R.id.buttonLogin);
        Button registerButton = findViewById(R.id.buttonRegister);
        usernameField = findViewById(R.id.editTextUsername);
        passwordField = findViewById(R.id.editTextPassword);

        // TODO: Initialize Gson with null serialization option
        gson = new GsonBuilder().serializeNulls().create();

        //Adding Handlers
        loginButton.setOnClickListener(view -> handleLogin());
        registerButton.setOnClickListener(view -> gotoRegister());
    }

    /**
     * Process login input and pass it to {@link #submitLogin(User)}
     */
    public void handleLogin() {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        // TODO: verify that all fields are not empty before proceeding. Toast with the error message
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            // TODO: Create User object with username and password and call submitLogin()
            User user = new User(username, password, null, false);
            submitLogin(user);
        }
    }

    /**
     * Sends a LOGIN request to the server
     * @param user User object to login
     */
    public void submitLogin(User user) {
        // TODO: Send a LOGIN request, If SUCCESS response, call gotoPairing(), else, Toast the error message from sever
        Request request = new Request();
        request.setType(Request.RequestType.LOGIN);
        request.setData(gson.toJson(user)); // Convert the User object to JSON and set it as data

        AppExecutors.getInstance().networkIO().execute(() -> {
            Response response = SocketClient.getInstance().sendRequest(request, Response.class);

            AppExecutors.getInstance().mainThread().execute(() -> {
                if (response == null) {
                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_LONG).show();
                } else if (response.getStatus() == Response.ResponseStatus.FAILURE) {
                    Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // If login is successful, call gotoPairing() with the username
                    gotoPairing(user.getUsername());
                }
            });
        });
    }





    /**
     * Switch the page to {@link PairingActivity}
     * @param username the data to send
     */
    public void gotoPairing(String username) {
        // TODO: start PairingActivity and pass the username
        Intent intent = new Intent(this, PairingActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    /**
     * Switch the page to {@link RegisterActivity}
     */
    public void gotoRegister() {
        // TODO: start RegisterActivity
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}