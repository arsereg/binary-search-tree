package com.main.java.ginomarin.enums;

public enum Actions {
    GET_INPUT_FROM_USER(1),
    PRINT_CURRENT_TREE(2),
    LOAD_RANGE(3);

    public int id;

    private Actions(int id){
        this.id = id;
    }
}
