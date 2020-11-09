package com.tupa.mipetagram.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.WindowInsets;

import androidx.annotation.Nullable;
import com.tupa.mipetagram.pojo.Mascota;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConfigBaseDatos.DATABASE_NAME, null, ConfigBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE "+
                ConfigBaseDatos.TABLE_MASCOTA+ "("
                +ConfigBaseDatos.TABLE_MASCOTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ConfigBaseDatos.TABLE_MASCOTA_NOMBRE+" TEXT, "
                +ConfigBaseDatos.TABLE_MASCOTA_FOTO+" INTEGER "
                +")";

        String queryCrearTablaMascotaLike = "CREATE TABLE "+
                ConfigBaseDatos.TABLE_MASCOTA_LIKE+ "("
                +ConfigBaseDatos.TABLE_MASCOTA_LIKE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ConfigBaseDatos.TABLE_MASCOTA_LIKE_ID_MASCOTA+" INTEGER, "
                +ConfigBaseDatos.TABLE_MASCOTA_LIKE_LIKES+" INTEGER, "
                +"FOREIGN KEY ("+ConfigBaseDatos.TABLE_MASCOTA_LIKE_ID_MASCOTA+") "
                +"REFERENCES "+ ConfigBaseDatos.TABLE_MASCOTA + "("+ConfigBaseDatos.TABLE_MASCOTA_ID+")"
                +")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaMascotaLike);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ConfigBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS "+ConfigBaseDatos.TABLE_MASCOTA_LIKE);
    }

    public ArrayList<Mascota> obtenerAllMascotas(){
        ArrayList<Mascota> mascotas =  new ArrayList<>();
        String query = "SELECT * FROM "+ConfigBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota actual = new Mascota();
            actual.setId(registros.getInt(0));
            actual.setNombre(registros.getString(1));
            actual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT("+ConfigBaseDatos.TABLE_MASCOTA_LIKE_LIKES+") as likes " +
                    " FROM "+ConfigBaseDatos.TABLE_MASCOTA_LIKE+
                    " WHERE "+ConfigBaseDatos.TABLE_MASCOTA_LIKE_ID_MASCOTA+" = "+actual.getId();
            Cursor registroLikes = db.rawQuery(query, null);
            if(registroLikes.moveToNext()){
                actual.setLikes(registroLikes.getInt(0));
            }else{
                actual.setLikes(0);
            }
            mascotas.add(actual);
        }
        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConfigBaseDatos.TABLE_MASCOTA, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConfigBaseDatos.TABLE_MASCOTA_LIKE, null, contentValues);
        db.close();
    }

    public Integer obtenerLikesMascota(Mascota mascota){
        Integer likes = 0;
        String query = "SELECT COUNT("+ConfigBaseDatos.TABLE_MASCOTA_LIKE_LIKES+") " +
                " FROM "+ConfigBaseDatos.TABLE_MASCOTA_LIKE +
                " WHERE "+ConfigBaseDatos.TABLE_MASCOTA_LIKE_ID_MASCOTA+" = "+mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();

        return likes;
    }

    public ArrayList<Mascota> obtenerMascotaFavoritas(){

        //RECUPERO TODAS LAS MASCOTAS
        ArrayList<Mascota> allMascotas = obtenerAllMascotas();
        //OBTENGO LOS LIKE DE CADA MASCOTA
        for (Mascota m: allMascotas) {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT COUNT("+ConfigBaseDatos.TABLE_MASCOTA_LIKE_LIKES+") " +
                    " FROM "+ConfigBaseDatos.TABLE_MASCOTA_LIKE +
                    " WHERE "+ConfigBaseDatos.TABLE_MASCOTA_LIKE_ID_MASCOTA+" = "+m.getId();

            Cursor registros = db.rawQuery(query, null);
            if (registros.moveToNext()){
                //SET DE LA CANTIDAD DE LIKES DE LA MASCOTA SELECCIONADA
                m.setLikes(registros.getInt(0));
            }
            db.close();
        }

        //ORDENO LA LISTA DE MASCOTAS (IMPLEMENTO Comparable EN LA CLASE MASCOTA)
        Collections.sort(allMascotas);
        //RETORNO LOS 5 PRIMERAS MASCOTAS, LAS QUE MAS LIKES TIENEN
        ArrayList<Mascota> mascotasFavoritas = new ArrayList<>();
        for (int i=0; i<5; i++){
            mascotasFavoritas.add(allMascotas.get(i));
        }
        return mascotasFavoritas;
    }

}
