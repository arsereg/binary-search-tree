package com.main.java.ginomarin.structure;

import com.main.java.ginomarin.elements.Nodo;

public class ArbolBinario<T extends Comparable<T>> {
    private Nodo<T> nodoInicial;

    public Nodo<T> getNodoInicial() {
        return nodoInicial;
    }

    public void setNodoInicial(Nodo<T> nodoInicial) {
        this.nodoInicial = nodoInicial;
    }
}
