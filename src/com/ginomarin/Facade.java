package com.ginomarin;

import com.ginomarin.services.ArbolBinarioService;

public class Facade {

    ArbolBinarioService arbolBinarioService = new ArbolBinarioService();

    public void initializeApplication() {
        arbolBinarioService.initializeApplication();
    }

    public void printTree() {
        arbolBinarioService.printBinaryTree();
    }

    public void add(Comparable comparable){
        arbolBinarioService.insert(comparable);
    }

    public String printPreOrden() {
        return arbolBinarioService.getPreordenResult();
    }


    public String printInOrden() {
        return arbolBinarioService.getInOrdenResult();
    }

    public String printPostOrden() {
        return arbolBinarioService.getPostOrdenResult();
    }
}
