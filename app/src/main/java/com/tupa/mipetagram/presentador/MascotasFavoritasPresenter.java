package com.tupa.mipetagram.presentador;

import android.content.Context;

import com.tupa.mipetagram.db.ConstructorMascotas;
import com.tupa.mipetagram.pojo.Mascota;
import com.tupa.mipetagram.view.IMascotasFavoritasView;

import java.util.ArrayList;

public class MascotasFavoritasPresenter implements IMascotasFavoritasPresenter{

    private IMascotasFavoritasView iMascotasFavoritasView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotasFavoritas;

    public MascotasFavoritasPresenter(IMascotasFavoritasView iMascotasFavoritasView, Context context) {
        this.iMascotasFavoritasView = iMascotasFavoritasView;
        this.context = context;
        obtenerMascotasFavoritasBaseDatos();
    }



    @Override
    public void obtenerMascotasFavoritasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotasFavoritas = constructorMascotas.obtenerMascotasFavoritas();
        mostrarMascotasFavoritasRV();

    }

    @Override
    public void mostrarMascotasFavoritasRV() {
        iMascotasFavoritasView.inicializarAdaptadorRV(iMascotasFavoritasView.crearAdaptador(mascotasFavoritas));
        iMascotasFavoritasView.generarLinearLayoutVertical();
    }
}
