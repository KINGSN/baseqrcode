package qrcode.app.com.darwinbark.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import qrcode.app.com.darwinbark.R;

import qrcode.app.com.darwinbark.ui.home.HomeActivity;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button callSignUp, loginBtn,forgotbtn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout Phone, Password;
    String phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        //Hooks
        callSignUp = findViewById(R.id.signUp_screen);
        loginBtn = findViewById(R.id.login_btn);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        Phone = findViewById(R.id.phoneNo);
        Password = findViewById(R.id.password);
        forgotbtn=(Button)findViewById(R.id.forgotbtn);

        forgotbtn.setOnClickListener(v -> {
            //startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String v2;
                phone = Objects.requireNonNull(Phone.getEditText()).getText().toString().trim();
                password = Objects.requireNonNull(Password.getEditText()).getText().toString().trim();

                if(phone.equals("") || password.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Your Phone or Password is Worng",Toast.LENGTH_SHORT).show();
                }


                else {
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    // String url ="http://www.google.com";
                    String url = "https://darwinbark.com/gget/app_login.php?phone=" + phone + "&password=" + password;
// Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String code = jsonObject.getString("code");
                                        if (code.equals("Login_Failed")) {
                                            Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                            //displayAlert(jsonObject.getString("message"));


                                        } else {


                                            //Toast.makeText(LoginActivity.this, jsonObject.getString("code"), Toast.LENGTH_SHORT).show();
                                            Intent loginIntent = new Intent(LoginActivity.this,HomeActivity.class);
                                            startActivity(loginIntent);
                                            finish();


                                            Toast.makeText(LoginActivity.this,jsonObject.getString("code"),Toast.LENGTH_SHORT).show();

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();

                        }
                    });

// Add the request to the RequestQueue.
                    queue.add(stringRequest);

                }

            }
        });

    }

    public void OpenSignUp(View view)
    {
        Intent loginIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(loginIntent);
        finish();
    }
    @Override
    public void onBackPressed()
    {
        finish();
        finishAffinity();
    }
    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}