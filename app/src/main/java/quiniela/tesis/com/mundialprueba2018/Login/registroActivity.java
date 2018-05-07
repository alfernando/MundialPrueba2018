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

import quiniela.tesis.com.mundialprueba2018.R;

public class registroActivity extends Activity implements View.OnClickListener{
    EditText txt_usuario,txt_password;
    CardView btn_Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txt_usuario = (EditText) findViewById(R.id.editText);
        txt_password = (EditText) findViewById(R.id.editText2);
        btn_Registro = findViewById(R.id.btnRegistro);
        btn_Registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnRegistro){
            PL_Registro();
        }
    }

    public void PL_Registro(){
        DBHelper admin = new DBHelper(this,"quiniela",null,2);
        String usuario;
        String password;
        SQLiteDatabase db= admin.getWritableDatabase();

        usuario = txt_usuario.getText().toString();
        password = txt_password.getText().toString();

        ContentValues values = new ContentValues();
        values.put("usuario",usuario);
        values.put("password",password);

        db.insert("usuario",null,values );
        db.close();

        Intent intent = new Intent(this,loginActivity.class);
        startActivity(intent);
       finish();
    }
}
