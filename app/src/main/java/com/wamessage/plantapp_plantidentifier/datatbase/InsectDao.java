package com.wamessage.plantapp_plantidentifier.datatbase;

import com.wamessage.plantapp_plantidentifier.models.Insect;
import java.util.List;


public interface InsectDao {
    void deleteInsect(Insect insect);

    Insect getFirstInsect();

    int getInsectCount();

    List<Insect> getInsects();

    long insertInsect(Insect insect);

    void updateInsect(Insect insect);
}
