package com.wamessage.plantapp_plantidentifier.listeners;

import com.wamessage.plantapp_plantidentifier.models.Mushroom;


public interface OnMushroomHistoryListener {
    void onItemHistoryClick(Mushroom mushroom);

    void onItemHistoryDelete(Mushroom mushroom);

    void onItemHistoryFavorite(Mushroom mushroom);
}
