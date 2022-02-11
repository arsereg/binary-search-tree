package com.ginomarin.enums;

public enum Actions {
    LOAD_PREBUILT_TREE(1),
    GET_INPUT_FROM_USER(2),
    PRINT_CURRENT_TREE(3),
    PRINT_PREORDEN(4),
    PRINT_INORDEN(5),
    PRINT_POSTORDEN(6);

    public int id;

    private Actions(int id){
        this.id = id;
    }
}
