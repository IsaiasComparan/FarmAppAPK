package com.wamessage.plantapp_plantidentifier.listeners;

import com.wamessage.plantapp_plantidentifier.models.Tree;


public interface OnTreeHistoryListener {
    void onItemHistoryClick(Tree tree);

    void onItemHistoryDelete(Tree tree);

    void onItemHistoryFavorite(Tree tree);
}
