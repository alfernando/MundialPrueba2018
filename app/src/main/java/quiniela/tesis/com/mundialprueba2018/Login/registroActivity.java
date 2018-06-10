package quiniela.tesis.com.mundialprueba2018.Login;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import quiniela.tesis.com.mundialprueba2018.R;
import quiniela.tesis.com.mundialprueba2018.Utils.HttpUtils;

public class registroActivity extends Activity implements View.OnClickListener{
    EditText txt_usuario,txt_password,txt_telefono;
    CardView btn_Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txt_usuario = (EditText) findViewById(R.id.editText);
        txt_password = (EditText) findViewById(R.id.editText2);
        txt_telefono = (EditText) findViewById(R.id.editText3);
        btn_Registro = findViewById(R.id.btnRegistro);
        btn_Registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnRegistro){
            PL_Registro();
        }
    }

    public void PL_Registro()
    {
        try{
            RequestParams rp = new RequestParams();

            rp.add("usua", txt_usuario.getText().toString());
            rp.add("pass", txt_password.getText().toString());
            rp.add("tel", txt_telefono.getText().toString());

            HttpUtils.post("Usuario.php", rp, new JsonHttpResponseHandler() {
                // ACA!!! ESTOS MÉTODOS
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(getApplicationContext(),"Usuario Registrado.",Toast.LENGTH_LONG).show();
                    PL_IngresarSistema();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                    // Pull out the first event on the public timeline
                    int temp = statusCode;

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Toast.makeText(getApplicationContext(),"No se puede registrar usuario.",Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(this,loginActivity.class);
        startActivity(intent);
        finish();
    }
}
