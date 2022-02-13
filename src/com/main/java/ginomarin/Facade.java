package com.main.java.ginomarin;

import com.main.java.ginomarin.services.ArbolBinarioService;

public class Facade {

    ArbolBinarioService arbolBinarioService = new ArbolBinarioService();

    public void initializeApplication() {
        arbolBinarioService.initializeApplication();
    }

    public void printTree(boolean saveAsHtml) {
        arbolBinarioService.printBinaryTree(saveAsHtml);
    }

    public void add(Comparable comparable){
        arbolBinarioService.insert(comparable, true, false);
    }

    public void silentlyAdd(Comparable comparable){
        arbolBinarioService.insert(comparable, false, false);
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
