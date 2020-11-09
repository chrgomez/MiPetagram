package com.tupa.mipetagram;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.widget.Toolbar;

import com.tupa.mipetagram.adapter.MascotaAdapter;
import com.tupa.mipetagram.fragment.IRecyclerViewFragmentView;
import com.tupa.mipetagram.pojo.Mascota;
import com.tupa.mipetagram.presentador.IMascotasFavoritasPresenter;
import com.tupa.mipetagram.presentador.IRecyclerViewFragmentPresenter;
import com.tupa.mipetagram.presentador.MascotasFavoritasPresenter;
import com.tupa.mipetagram.presentador.RecyclerViewFragmentPresenter;
import com.tupa.mipetagram.view.IMascotasFavoritasView;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity implements IMascotasFavoritasView {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IMascotasFavoritasPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mascotas_favoritas);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);
        presenter = new MascotasFavoritasPresenter(this, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdapter adapter = new MascotaAdapter(mascotas, this);
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdapter mascotaAdapter) {
        listaMascotas.setAdapter(mascotaAdapter);
    }
}
