package com.tupa.mipetagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tupa.mipetagram.R;
import com.tupa.mipetagram.pojo.Mascota;

import java.util.ArrayList;
import java.util.List;

public class ConstructorMascotas {

    private static final Integer LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos() {
        BaseDatos db = new BaseDatos(context);
        insertar10Mascotas(db);
        return db.obtenerAllMascotas();
    }

    public void insertar10Mascotas(BaseDatos db){
        //MASCOTA 1
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_NOMBRE, "CANDY");
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro5);
        db.insertarMascota(contentValues);
        //MASCOTA 2
        contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_NOMBRE, "ROCO");
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro4);
        db.insertarMascota(contentValues);
        //MASCOTA 3
        contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_NOMBRE, "DUKY");
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro3);
        db.insertarMascota(contentValues);
        //MASCOTA 4
        contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_NOMBRE, "KAISER");
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro2);
        db.insertarMascota(contentValues);
        //MASCOTA 5
        contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_NOMBRE, "FLOPY");
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro1);
        db.insertarMascota(contentValues);
        //MASCOTA 6
        contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_NOMBRE, "TOOR");
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro6);
        db.insertarMascota(contentValues);
        //MASCOTA 7
        contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_NOMBRE, "PALOMA");
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro7);
        db.insertarMascota(contentValues);
        //MASCOTA 8
        contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_NOMBRE, "TITI");
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro8);
        db.insertarMascota(contentValues);
        //MASCOTA 9
        contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_NOMBRE, "FIRULAIS");
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro9);
        db.insertarMascota(contentValues);
        //MASCOTA 10
        contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_NOMBRE, "PEPE");
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.perro10);
        db.insertarMascota(contentValues);
    }

    public void darLike(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_LIKE_ID_MASCOTA, mascota.getId());
        contentValues.put(ConfigBaseDatos.TABLE_MASCOTA_LIKE_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public Integer obtenerLikesMascota(Mascota mascota){
        BaseDatos db =  new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

    public ArrayList<Mascota> obtenerMascotasFavoritas(){
        BaseDatos db =  new BaseDatos(context);
        return db.obtenerMascotaFavoritas();
    }

}
