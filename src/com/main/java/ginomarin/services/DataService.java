package com.main.java.ginomarin.services;

import com.main.java.ginomarin.structure.ArbolBinario;

public class DataService {

    private ArbolBinario arbol;

    public void initializeApplication(){
        resetArbol();
    }


    public void resetArbol(){
        //ArbolWrapper
        setArbol(new ArbolBinario());
    }

    public ArbolBinario getArbol() {
        return arbol;
    }

    public void setArbol(ArbolBinario arbol) {
        this.arbol = arbol;
    }
}
