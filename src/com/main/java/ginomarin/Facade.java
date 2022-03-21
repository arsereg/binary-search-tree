package com.main.java.ginomarin;

import com.main.java.ginomarin.services.ArbolBinarioService;
import com.main.java.ginomarin.structure.RbTree;

public class Facade {

    RbTree rbTree = new RbTree();


    public void printTree() {
        rbTree.PrintLevel(rbTree.Root, rbTree.HeightT(rbTree.Root));
        System.out.println();
    }

    public void add(String data){
        rbTree.Insert(rbTree, data);
    }

}
