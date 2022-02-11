package com.main.java.ginomarin.services;

import com.main.java.ginomarin.elements.Nodo;
import com.main.java.ginomarin.structure.ArbolBinario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArbolBinarioService<T extends Comparable<T>> {

    private List<T> preOrdenResultList = new ArrayList<>();
    private List<T> inOrdenResultList = new ArrayList<>();
    private List<T> postOrdenResultList = new ArrayList<>();

    DataService dataService = new DataService();

    private void recorrerInOrden(){
        if(null == dataService.getArbol()){
            printError("El arbol no ha sido inicializado");
        }else{
            recorrerInOrden(dataService.getArbol().getNodoInicial());
        }
    }

    private void recorrerPreOrden(){
        if(null == dataService.getArbol()){
            printError("El arbol no ha sido inicializado");
        }else{
            recorrerPreOrden(dataService.getArbol().getNodoInicial());
        }
    }

    private void recorrerPostOrden(){
        if(null == dataService.getArbol()){
            printError("El arbol no ha sido inicializado");
        }else{
            recorrerPostOrden(dataService.getArbol().getNodoInicial());
        }
    }

    private void recorrerPreOrden(Nodo<T> nodo){
        preOrdenResultList.add(nodo.key);
        if(nodo.left != null){
            recorrerPreOrden(nodo.left);
        }
        if(nodo.right != null){
            recorrerPreOrden(nodo.right);
        }
    }


    private void recorrerInOrden(Nodo<T> nodo){
        if(nodo.left != null){
            recorrerInOrden(nodo.left);
        }
        inOrdenResultList.add(nodo.key);
        if(nodo.right != null){
            recorrerInOrden(nodo.right);
        }
    }

    private void recorrerPostOrden(Nodo<T> nodo){
        if(nodo.left != null){
            recorrerPostOrden(nodo.left);
        }
        if(nodo.right != null){
            recorrerPostOrden(nodo.right);
        }
        postOrdenResultList.add(nodo.key);
    }

    public void printBinaryTree(){
        if(null != dataService.getArbol()){
            printBinaryTree(dataService.getArbol().getNodoInicial());
        }else{
            printError("El arbol no ha sido inicializado");
        }
    }

    public void printBinaryTree(Nodo<T> root) {
        String ANSI_GREEN = "\033[1;32m";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_YELLOW = "\033[0;93m";
        List<List<String>> lines = new ArrayList<>();

        List<Nodo> level = new ArrayList<>();
        List<Nodo> next = new ArrayList<>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;

            for (Nodo n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.key.toString();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Nodo> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        String divider = "";
        for (int i = 0; i < perpiece; i++) {
            divider += "-";
        }
        System.out.println();
        System.out.println(divider);
        System.out.println();
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    String c = " ";
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? "┴" : "┘";
                        } else {
                            if (j < line.size() && line.get(j) != null) c = "└";
                        }
                    }
                    System.out.print(ANSI_GREEN);
                    System.out.print(c);
                    System.out.print(ANSI_RESET);
                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(ANSI_GREEN);
                            System.out.print(j % 2 == 0 ? " " : "─");
                            System.out.print(ANSI_RESET);
                        }
                        System.out.print(ANSI_GREEN);
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        System.out.print(ANSI_RESET);
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(ANSI_GREEN);
                            System.out.print(j % 2 == 0 ? "─" : " ");
                            System.out.print(ANSI_RESET);
                        }
                    }
                }
                System.out.println();
            }
            // print line of numbers
            System.out.print(ANSI_YELLOW);
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                float a = perpiece / 2f - f.length() / 2f;
                int gap1 = (int) Math.ceil(a);
                int gap2 = (int) Math.floor(a);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.print(ANSI_RESET);
            System.out.println();

            perpiece /= 2;

        }
    }

    public void insert(T numero, boolean print){
        if(dataService.getArbol() == null){
            dataService.setArbol(new ArbolBinario());
        }
        dataService.getArbol().setNodoInicial(insert(dataService.getArbol().getNodoInicial(), numero));
        if(print){
            printBinaryTree();
        }
    }

    private Nodo<T> insert(Nodo<T> node, T key){
        if (node == null) {
            return new Nodo(key);
        } else if (node.key.compareTo(key) > 0) {
            node.left = insert(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            node.right = insert(node.right, key);
        } else {
            printError("Ya existe un nodo con ese valor. No agregado");
        }
        return balancear(node);
    }

    public String getPreordenResult(){
        recorrerPreOrden();
        return preOrdenResultList.stream().map(v -> v.toString()).collect(Collectors.joining(", "));
    }

    public String getInOrdenResult(){
        recorrerInOrden();
        return inOrdenResultList.stream().map(v -> v.toString()).collect(Collectors.joining(", "));
    }

    public String getPostOrdenResult(){
        recorrerPostOrden();
        return postOrdenResultList.stream().map(v -> v.toString()).collect(Collectors.joining(", "));
    }


    void actualizarAltura(Nodo<T> nodo){
        nodo.height = 1 + Math.max(altura(nodo.left), altura(nodo.right));
    }

    int altura(Nodo<T> nodo){
        return nodo == null ? -1 : nodo.height;
    }

    int getBalance(Nodo<T> nodo){
        int result = (nodo == null) ? 0 : altura(nodo.right) - altura(nodo.left);
        return result;
    }

    private Nodo<T> rotarDerecha(Nodo<T> y){
        Nodo<T> x = y.left;
        Nodo<T> z = x.right;
        x.right = y;
        y.left = z;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    private Nodo<T> rotarIzquierda(Nodo<T> y){
        Nodo<T> x = y.right;
        Nodo<T> z = x.left;
        x.left = y;
        y.right = z;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    Nodo<T> balancear(Nodo<T> z) {
        actualizarAltura(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (altura(z.right.right) > altura(z.right.left)) {
                z = rotarIzquierda(z);
            } else {
                z.right = rotarDerecha(z.right);
                z = rotarIzquierda(z);
            }
        } else if (balance < -1) {
            if (altura(z.left.left) > altura(z.left.right))
                z = rotarDerecha(z);
            else {
                z.left = rotarIzquierda(z.left);
                z = rotarDerecha(z);
            }
        }
        return z;
    }



    public void initializeApplication() {
        dataService.initializeApplication();
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
