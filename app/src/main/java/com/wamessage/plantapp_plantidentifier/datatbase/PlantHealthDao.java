package com.wamessage.plantapp_plantidentifier.datatbase;

import com.wamessage.plantapp_plantidentifier.models.PlantHealth;
import java.util.List;


public interface PlantHealthDao {
    void deletePlantHealth(PlantHealth planthealth);

    PlantHealth getFirstPlantHealth();

    int getPlantHealthCount();

    List<PlantHealth> getPlantHealths();

    long insertPlantHealth(PlantHealth plantHealth);

    void updatePlantHealth(PlantHealth plantHealth);
}
