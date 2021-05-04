package edu.ufp.inf.lp2._project;

import java.io.FileNotFoundException;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        Geocaching geocaching = new Geocaching();
        geocaching.carregarInfo("C:\\Users\\esago\\OneDrive\\Ambiente de Trabalho\\Files\\UFP\\4º Semestre\\Linguagens de Programação II\\Projeto\\input\\input");
        geocaching.listUsers();
    }

}