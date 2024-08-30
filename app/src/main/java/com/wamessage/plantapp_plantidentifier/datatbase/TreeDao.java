package com.wamessage.plantapp_plantidentifier.datatbase;

import com.wamessage.plantapp_plantidentifier.models.Tree;
import java.util.List;


public interface TreeDao {
    void deleteTree(Tree tree);

    Tree getFirstTree();

    int getTreeCount();

    List<Tree> getTrees();

    long insertTree(Tree tree);

    void updateTree(Tree tree);
}
