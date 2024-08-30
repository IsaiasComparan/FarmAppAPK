package com.wamessage.plantapp_plantidentifier.datatbase;

import com.wamessage.plantapp_plantidentifier.models.Plant;
import java.util.List;


public interface PlantDao {
    void deletePlant(Plant plant);

    Plant getFirstPlant();

    int getPlantCount();

    List<Plant> getPlants();

    long insertPlant(Plant plant);

    void updatePlant(Plant plant);
}
