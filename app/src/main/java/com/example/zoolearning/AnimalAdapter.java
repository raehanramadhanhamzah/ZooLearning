package com.example.zoolearning;

import static com.example.zoolearning.DetailHewanActivity.*;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {
    public List<Animal> animalList;


    public AnimalAdapter(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_hewan, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animalList.get(position);
        holder.setData(animal);
        holder.btnDetail.setOnClickListener(v -> {
            Intent toDetailActivity = new Intent(holder.itemView.getContext(), DetailHewanActivity.class);
            toDetailActivity.putExtra(PARCEL_DATA, animal);

            holder.itemView.getContext().startActivity(toDetailActivity);
        });


    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }
    public static class AnimalViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgHewan;
        private final TextView name;
        private final TextView species;
        private final TextView family;
        private final TextView habitat;
        private TextView placeFound;
        private TextView diet;
        private final TextView desc;
        private final TextView weight;
        private final TextView height;
        Button btnDetail;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHewan = itemView.findViewById(R.id.imgHewan);
            name = itemView.findViewById(R.id.tvNamaHewan);
            species = itemView.findViewById(R.id.tvSpecies);
            habitat = itemView.findViewById(R.id.tvHabitat);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            desc = itemView.findViewById(R.id.tvDesc);
            family = itemView.findViewById(R.id.tvFamily);
            height = itemView.findViewById(R.id.tvHeight);
            weight = itemView.findViewById(R.id.tvWeight);
        }
        public void setData(Animal animal) {
            if(animal.getDiet().equals("Carnivore")){
                imgHewan.setImageResource(R.drawable.gambar_carnivore);
            }else if(animal.getDiet().equals("Herbivore")){
                imgHewan.setImageResource(R.drawable.gambar_herbivore);
            }else if(animal.getDiet().equals("Omnivore")){
                imgHewan.setImageResource(R.drawable.gambar_omnivore);
            }else{
                imgHewan.setImageResource(R.drawable.gambar_insectivore);
            }
            name.setText(animal.getName());
            species.setText("Spesies: "+animal.getSpecies());
            habitat.setText("Habitat: "+animal.getHabitat());
        }
    }

}