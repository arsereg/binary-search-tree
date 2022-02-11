package com.main.java.ginomarin.elements;

public class Nodo<T extends Comparable<T>>{

    public Nodo(T key){
        this.key = key;
    }

    public T key;
    public int height;
    public Nodo<T> left;
    public Nodo<T> right;


    public Nodo<T> setLeft(Nodo<T> node){
        this.left = node;
        return this;
    }

    public Nodo<T> setRight(Nodo<T> node){
        this.right = node;
        return this;
    }
}
