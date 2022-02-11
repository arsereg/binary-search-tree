package com.ginomarin.services;

import com.ginomarin.elements.Nodo;
import com.ginomarin.structure.ArbolBinario;

public class DataService {

    private ArbolBinario arbol;

    public void initializeApplication(){
        //ArbolWrapper
        resetArbol();
        //Izquierda
        Nodo<Integer> uno = new Nodo(1).setLeft(null).setRight(null);
        Nodo<Integer> once = new Nodo(11).setLeft(null).setRight(null);
        Nodo<Integer> cinco = new Nodo(5).setLeft(uno).setRight(once);
        //Derecha

        Nodo<Integer> veintiuno = new Nodo(21).setLeft(null).setRight(null);
        Nodo<Integer> diesiciete = new Nodo(17).setLeft(null).setRight(veintiuno);
        Nodo<Integer> veinticinco = new Nodo(25).setLeft(diesiciete).setRight(null);

        //Raiz
        Nodo<Integer> trece = new Nodo(13).setLeft(cinco).setRight(veinticinco);
        getArbol().setNodoInicial(trece);
        //Setting service
        ArbolBinarioService<Integer> arbolBinarioService = new ArbolBinarioService<>();
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
