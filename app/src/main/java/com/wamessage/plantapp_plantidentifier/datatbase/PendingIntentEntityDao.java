package com.wamessage.plantapp_plantidentifier.datatbase;

import com.wamessage.plantapp_plantidentifier.models.PendingIntentEntity;
import java.util.List;


public interface PendingIntentEntityDao {
    void deletePendingIntentEntity(PendingIntentEntity pendingIntentEntity);

    List<PendingIntentEntity> getPendingIntentEntities();

    PendingIntentEntity getPendingIntentEntityById(long id);

    long insertPendingIntentEntity(PendingIntentEntity pendingIntentEntity);

    void updatePendingIntentEntity(PendingIntentEntity pendingIntentEntity);
}
