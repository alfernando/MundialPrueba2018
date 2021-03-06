package quiniela.tesis.com.mundialprueba2018.Login;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpConnection;
import quiniela.tesis.com.mundialprueba2018.MainActivity;
import quiniela.tesis.com.mundialprueba2018.R;
import quiniela.tesis.com.mundialprueba2018.Utils.HttpUtils;

public class loginActivity extends Activity implements View.OnClickListener{

    EditText txt_usuario,txt_password;
    CardView btnLogin;
    TextView txt_rigistro;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        txt_usuario =(EditText) findViewById(R.id.editText);
        txt_password=(EditText) findViewById(R.id.editText2);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        txt_rigistro = findViewById(R.id.textView2);
        txt_rigistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.btnLogin){
            PL_Ingresar();
        }
        if (v.getId()==R.id.textView2){
            Intent intent = new Intent (this, registroActivity.class);
            startActivity(intent);

        }
    }

    public void PL_Ingresar()
    {

        PL_GetUsuario();

    }


    public void PL_GetUsuario(){

        try{
            RequestParams rp = new RequestParams();

            rp.add("usua", txt_usuario.getText().toString());
            rp.add("pass", txt_password.getText().toString());

            HttpUtils.get("Usuario.php", rp, new JsonHttpResponseHandler() {
                // ACA!!! ESTOS MÉTODOS
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // If the response is JSONObject instead of expected JSONArray
                    try {
                        String vrlReponse = response.toString();
                        JSONObject serverResp = new JSONObject(response.toString());
                        //Toast.makeText(getApplicationContext(),vrlReponse.toString(),Toast.LENGTH_LONG).show();
                        JSONArray jsonArray = serverResp.getJSONArray("listaUsuario");
                        for (int i = 0 ; i<jsonArray.length();i++)
                        {
                            JSONObject json_data = jsonArray.getJSONObject(i);

                            String usua = json_data.getString("us_usuario");
                            if (usua.equals(txt_usuario.getText().toString()))
                            {
                                PL_IngresarSistema();
                            }
                        }


                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                    // Pull out the first event on the public timeline
                    int temp = statusCode;

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void PL_IngresarSistema()
    {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void PL_Ingresar2(){
        DBHelper admin = new DBHelper(this,"quiniela",null,2);
        String usuario;
        String password;
        SQLiteDatabase db = admin.getWritableDatabase();


            usuario = txt_usuario.getText().toString();
            password = txt_password.getText().toString();

            fila = db.rawQuery("select usuario,password from usuario where usuario = '" + usuario + "' and password ='"+password+ "'", null);


        if (fila.moveToFirst()){
            String usua = fila.getString(0);
            String pass = fila.getString(1);
            if (usuario.equals(usua) && password.equals(pass)){
                Intent intent = new Intent (this, MainActivity.class);
                startActivity(intent);
                txt_usuario.setText("");
                txt_password.setText("");
                finish();

            }

        }
        else{
            Toast.makeText(getApplicationContext(),"Usuario Incorrecto",Toast.LENGTH_LONG).show();
            Intent intent = new Intent (this, MainActivity.class);
            PL_GetUsuario();
            startActivity(intent);
            finish();
        }

    }






}
