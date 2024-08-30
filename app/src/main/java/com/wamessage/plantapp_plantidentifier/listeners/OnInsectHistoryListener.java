package com.wamessage.plantapp_plantidentifier.listeners;

import com.wamessage.plantapp_plantidentifier.models.Insect;


public interface OnInsectHistoryListener {
    void onItemHistoryClick(Insect insect);

    void onItemHistoryDelete(Insect insect);

    void onItemHistoryFavorite(Insect insect);
}
