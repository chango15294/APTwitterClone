package com.example.harri.aptwitterclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail,edtPassword,edtUserName;
    private Button btnLogin,btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Registro");

        edtEmail = findViewById(R.id.edtEnterEmail);
        edtPassword = findViewById(R.id.edtEnterPassword);
        edtUserName = findViewById(R.id.edtUsername);

        btnLogin = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        //hay un usuario con sesion iniciada
        if(ParseUser.getCurrentUser() != null)
        {


            transitionToTwitterUsersActivity();
        }

    }

    private void transitionToTwitterUsersActivity()
    {
        Intent intent = new Intent(MainActivity.this,TwitterUsers.class);
        startActivity(intent);
        finish();
    }



    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnLogIn:

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;


            case R.id.btnSignUp:

                if(edtEmail.getText().toString().equals("")
                        || edtUserName.getText().toString().equals("")
                        || edtPassword.getText().toString().equals(""))
                {
                    FancyToast.makeText(MainActivity.this,
                            "Email, NombreUsuario y Contraseña son campos obligatorios...",
                            Toast.LENGTH_LONG,FancyToast.SUCCESS,
                            true).show();
                }
                else
                {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUserName.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());


                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Registrándose " + edtUserName.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e)
                        {

                            if (e == null)
                            {
                                FancyToast.makeText(MainActivity.this,
                                        appUser.getUsername() + " se ha registrado",
                                        Toast.LENGTH_LONG,FancyToast.SUCCESS,
                                        true).show();

                                transitionToTwitterUsersActivity();
                            }
                            else
                            {
                                FancyToast.makeText(MainActivity.this,
                                        "Hubo un error " + e.getMessage(),
                                        Toast.LENGTH_LONG,FancyToast.ERROR,
                                        true).show();
                            }
                            progressDialog.dismiss();

                        }
                    });

                }
                break;
        }

    }

    public void rootLayoutTapped(View view)
    {
        try
        {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
