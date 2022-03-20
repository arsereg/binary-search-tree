package com.main.java.ginomarin;

import com.main.java.ginomarin.enums.Actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class Main {

    public static boolean continueExecution = true;
    public static Main instance;


    private BufferedReader in;
    private PrintStream out;

    Facade facade = new Facade();

    public static void main(String[] args) throws IOException {
        instance = new Main();
        instance.in = new BufferedReader(new InputStreamReader(System.in));
        instance.out = System.out;
        instance.startApplication();
    }

    private void startApplication() throws IOException {
        do{
            printMenu();
            int input = Integer.parseInt(in.readLine());
            if(input != 0){
                execute(Arrays.stream(Actions.values()).filter(v-> v.id == input).findFirst().get());
            }else{
                continueExecution = false;
            }
        }while(continueExecution);
    }


    public void printMenu(){
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_RESET = "\u001B[0m";
        String[] options = {
                "Ingresar datos",
                "Imprimir arbol",
                "Ingresar rango"};
        for (int i = 0; i < options.length; i++) {
            System.out.println(String.format(ANSI_CYAN + "%s" + ANSI_RESET + " - %s", i + 1, options[i]));
        }
        System.out.println(ANSI_CYAN + "0" + ANSI_RESET +" - Salir");
    }

    public void execute(Actions action) throws IOException {
        switch (action) {
            case GET_INPUT_FROM_USER:
                loadFromInput();
            break;
            case PRINT_CURRENT_TREE:
                printTree();
            break;
            case LOAD_RANGE:
                loadRange();
            break;
        }
    }

    private void loadRange() throws IOException {
        out.println("Digite un rango en el siguiente formato X:Y para ingresar esos numeros");
        String[] input = in.readLine().split(":");
        if(input.length != 2){
            printError("Formato invalido");
            return;
        }
        int[] range = {Integer.parseInt(input[0]), Integer.parseInt(input[1])};
        if(range[0] > range[1]){
            printError("El segundo valor debe ser más alto que el primer valor");
            return;
        }

    }


    private void printTree() throws IOException {
        facade.printTree();
    }

    private void loadFromInput() throws IOException {
        boolean keepAdding = true;
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        do{
            System.out.println("Digite el numero a agregar (escribir "+ANSI_RED+"Stop"+ANSI_RESET+" para terminar)");
            String input = in.readLine();
            if(input.equalsIgnoreCase("stop")){
                keepAdding = false;
            }else{
                if(null == facade.arbolB){
                    System.out.println("Inicializando Arbol");
                    System.out.print("Digite el tamaño del nodo para el Arbol B:");
                    int size = Integer.parseInt(in.readLine());
                    facade.initializeTree(size);
                }
                if(input.contains(",")){
                    Arrays.stream(input.split(",")).forEach(v -> facade.add(Integer.parseInt(v.trim())));
                }else{
                    facade.add(Integer.parseInt(input));
                }
            }
        }while(keepAdding);
    }

    private void printError(String error){
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println(ANSI_RED);
        System.out.println("--------------------------------");
        System.out.println(error);
        System.out.println("--------------------------------");
        System.out.println(ANSI_RESET);
    }

}
