package com.tupa.mipetagram.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tupa.mipetagram.R;
import com.tupa.mipetagram.adapter.PerfilAdapter;
import com.tupa.mipetagram.pojo.Mascota;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaPerfil;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        listaPerfil = (RecyclerView) v.findViewById(R.id.rvPerfil);

//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);

        listaPerfil.setLayoutManager(glm);

        inicializarListaMascotas();
        inicializarAdapter();

        return v;
    }

    public void inicializarAdapter(){
        PerfilAdapter adapter = new PerfilAdapter(mascotas);
        listaPerfil.setAdapter(adapter);
    }

    public void inicializarListaMascotas() {
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota("CANDY", R.drawable.perro11, new Integer("2")));
        mascotas.add(new Mascota("CANDY", R.drawable.perro12, new Integer("5")));
        mascotas.add(new Mascota("CANDY", R.drawable.perro13, new Integer("7")));
        mascotas.add(new Mascota("CANDY", R.drawable.perro14, new Integer("8")));
        mascotas.add(new Mascota("CANDY", R.drawable.perro15, new Integer("10")));
        mascotas.add(new Mascota("CANDY", R.drawable.perro16, new Integer("3")));
    }
}
