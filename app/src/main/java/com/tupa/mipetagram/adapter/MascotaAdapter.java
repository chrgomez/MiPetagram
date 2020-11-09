package com.tupa.mipetagram.adapter;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tupa.mipetagram.db.ConstructorMascotas;
import com.tupa.mipetagram.pojo.Mascota;
import com.tupa.mipetagram.R;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>{

    private ArrayList<Mascota> mascotas;
    private Activity activity;

    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas= mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);

        return new MascotaViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotasViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        final ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
        mascotasViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotasViewHolder.tvNombre.setText(mascota.getNombre());
        mascotasViewHolder.tvLikes.setText(constructorMascotas.obtenerLikesMascota(mascota).toString());
        mascotasViewHolder.btnMeGusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Diste like a "+mascota.getNombre(), Toast.LENGTH_SHORT).show();
                constructorMascotas.darLike(mascota);
                mascotasViewHolder.tvLikes.setText(constructorMascotas.obtenerLikesMascota(mascota).toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private ImageButton btnMeGusta;
        private TextView tvNombre;
        private TextView tvLikes;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            btnMeGusta = (ImageButton) itemView.findViewById(R.id.btnMeGusta);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);
        }
    }
}
