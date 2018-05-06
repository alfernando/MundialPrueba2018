package quiniela.tesis.com.mundialprueba2018.Login;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import quiniela.tesis.com.mundialprueba2018.MainActivity;
import quiniela.tesis.com.mundialprueba2018.R;

public class loginActivity extends Activity implements View.OnClickListener{

    EditText txt_usuario,txt_password;
    CardView btnLogin;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        txt_usuario =(EditText) findViewById(R.id.editText);
        txt_password=(EditText) findViewById(R.id.editText2);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v.getId()==R.id.btnLogin){
            PL_Ingresar();
        }
    }

    public void PL_Ingresar(){
        DBHelper admin = new DBHelper(this,"quiniela",null,1);
        String usuario;
        String password;
        SQLiteDatabase db = admin.getWritableDatabase();


            usuario = txt_usuario.getText().toString();
            password = txt_password.getText().toString();

            fila = db.rawQuery("select usuario,password from usuario where usuario = '" + usuario + "' and password='" + password + "'", null);


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
            else{
                Toast.makeText(getApplicationContext(),"Usuario Incorrecto",Toast.LENGTH_LONG).show();
            }
        }

    }

}
