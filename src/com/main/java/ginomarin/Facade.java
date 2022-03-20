package com.main.java.ginomarin;


import com.main.java.ginomarin.structure.ArbolB;

public class Facade {

    ArbolB arbolB;

    public void initializeTree(int nodeSize){
        arbolB = new ArbolB(nodeSize);
    }

    public void printTree() {
        arbolB.show();
    }

    public void add(int comparable){
        arbolB.insert(comparable);
    }

}
