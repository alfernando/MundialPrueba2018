package quiniela.tesis.com.mundialprueba2018.Login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(codigo integer primary key autoincrement, usuario text, password text )");
        db.execSQL("insert into usuario values(1,'admin','admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table usuario(codigo integer primary key autoincrement, usuario text, password text )");
        db.execSQL("insert into usuario values(1,'admin','admin')");
    }
}
