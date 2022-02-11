package com.ginomarin;

import com.ginomarin.enums.Actions;

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
                "Datos precargados",
                "Ingresar datos",
                "Imprimir arbol",
                "Imprimir Datos en Preorden",
                "Imprimir Datos en Inorden",
                "Imprimir Datos en PostOrden",
                "Ingresar rango"};
        for (int i = 0; i < options.length; i++) {
            System.out.println(String.format(ANSI_CYAN + "%s" + ANSI_RESET + " - %s", i + 1, options[i]));
        }
        System.out.println(ANSI_CYAN + "0" + ANSI_RESET +" - Salir");
    }

    public void execute(Actions action) throws IOException {
        switch (action) {
            case LOAD_PREBUILT_TREE:
                loadPrebuiltTree();
            break;
            case GET_INPUT_FROM_USER:
                loadFromInput();
            break;
            case PRINT_CURRENT_TREE:
                printTree();
            break;
            case PRINT_PREORDEN:
                printPreorden();
            break;
            case PRINT_INORDEN:
                printInOrden();
            break;
            case PRINT_POSTORDEN:
                printPostOrden();
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
        for (int i = range[0]; i <= range[1]; i++) {
            facade.silentlyAdd(i);
        }

    }

    private void printPostOrden() {
        System.out.println(facade.printPostOrden());
    }

    private void printInOrden() {
        System.out.println(facade.printInOrden());
    }

    private void printPreorden() {
        System.out.println(facade.printPreOrden());
    }

    private void printTree() {
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
                if(input.contains(",")){
                    Arrays.stream(input.split(",")).forEach(v -> facade.add(Integer.parseInt(v.trim())));
                }else{
                    facade.add(Integer.parseInt(input));
                }
            }
        }while(keepAdding);
    }

    private void loadPrebuiltTree() {
        out.println("Loading prebuilt");
        facade.initializeApplication();
        Arrays.asList(7, 14, 28, 5, 9, 8, 21, 3, 15, 24, 100, 1).stream().forEach(v -> facade.add(v.intValue()));
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
