package ort.t5.tpfinal.entrenator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SesionesSQLiteHelper extends SQLiteOpenHelper {
   
   String sqlCreate = "CREATE TABLE Sesiones (codigo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
   		"fecha TEXT , actividad TEXT , distancia TEXT , tiempo TEXT , velocidad TEXT , comentario TEXT)";
	
   public SesionesSQLiteHelper(Context context, String name, CursorFactory factory, int version) {
      super(context, name, factory, version);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL(sqlCreate);
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS Sesiones");
      db.execSQL(sqlCreate);
   }
   
}