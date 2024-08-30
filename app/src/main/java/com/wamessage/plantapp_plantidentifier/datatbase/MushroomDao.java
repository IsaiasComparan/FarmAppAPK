package com.wamessage.plantapp_plantidentifier.datatbase;

import com.wamessage.plantapp_plantidentifier.models.Mushroom;
import java.util.List;


public interface MushroomDao {
    void deleteMushroom(Mushroom mushroom);

    Mushroom getFirstMushroom();

    int getMushroomCount();

    List<Mushroom> getMushrooms();

    long insertMushroom(Mushroom mushroom);

    void updateMushroom(Mushroom mushroom);
}
