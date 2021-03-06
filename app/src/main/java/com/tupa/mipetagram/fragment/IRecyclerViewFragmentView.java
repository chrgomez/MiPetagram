package com.tupa.mipetagram.fragment;

import com.tupa.mipetagram.adapter.MascotaAdapter;
import com.tupa.mipetagram.pojo.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdapter mascotaAdapter);

}
