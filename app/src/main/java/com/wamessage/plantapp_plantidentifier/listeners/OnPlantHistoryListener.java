package com.wamessage.plantapp_plantidentifier.listeners;

import com.wamessage.plantapp_plantidentifier.models.Plant;


public interface OnPlantHistoryListener {
    void onItemHistoryClick(Plant plant);

    void onItemHistoryDelete(Plant plant);

    void onItemHistoryFavorite(Plant plant);
}
