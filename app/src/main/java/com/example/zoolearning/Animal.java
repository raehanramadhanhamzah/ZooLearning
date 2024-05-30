package com.example.zoolearning;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Animal implements Parcelable {
    int id;
    String name,species,family,habitat,place_of_found,diet,description,image,weight_kg,height_cm;

    public Animal(int id, String name, String species, String family, String habitat, String place_of_found, String diet, String description, String weight_kg, String height_cm) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.family = family;
        this.habitat = habitat;
        this.place_of_found = place_of_found;
        this.diet = diet;
        this.description = description;
        this.weight_kg = weight_kg;
        this.height_cm = height_cm;
    }

    protected Animal(Parcel in) {
        id = in.readInt();
        name = in.readString();
        species = in.readString();
        family = in.readString();
        habitat = in.readString();
        place_of_found = in.readString();
        diet = in.readString();
        description = in.readString();
        image = in.readString();
        weight_kg = in.readString();
        height_cm = in.readString();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getPlace_of_found() {
        return place_of_found;
    }

    public void setPlace_of_found(String place_of_found) {
        this.place_of_found = place_of_found;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getWeight_kg() {
        return weight_kg;
    }

    public void setWeight_kg(String weight_kg) {
        this.weight_kg = weight_kg;
    }

    public String getHeight_cm() {
        return height_cm;
    }

    public void setHeight_cm(String height_cm) {
        this.height_cm = height_cm;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imgHewan) {
        this.image = imgHewan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(species);
        dest.writeString(family);
        dest.writeString(habitat);
        dest.writeString(place_of_found);
        dest.writeString(diet);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(weight_kg);
        dest.writeString(height_cm);
    }
}