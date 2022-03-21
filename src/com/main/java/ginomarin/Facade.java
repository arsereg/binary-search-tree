package com.main.java.ginomarin;

import com.main.java.ginomarin.structure.BPlusTree;

public class Facade {

    BPlusTree arbolBPlus;

    public void initializeTree(int nodeSize){
        arbolBPlus = new BPlusTree(nodeSize);
    }

    public void add(int key, int value){
        arbolBPlus.insert(key, value);
    }

}
