package com.wamessage.plantapp_plantidentifier.listeners;

import com.wamessage.plantapp_plantidentifier.models.PlantHealth;


public interface OnPlantHealthHistoryListener {
    void onItemHistoryClick(PlantHealth plantHealth);

    void onItemHistoryDelete(PlantHealth plantHealth);

    void onItemHistoryFavorite(PlantHealth plantHealth);
}
