package edu.ufp.inf.lp2._project;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {


        Geocaching geocaching = new Geocaching();
        geocaching.carregarInfo("input\\input.txt");

        int[] cachesManuel = {1,2,6,8,13,16,17};
        int[] cachesPedro = {18,13,8};
        int[] cachesFernando = {12,11,10,8,9,5,6,3,2,1,7,15,17,18,13};
        int[] cachesJoana = {14,15,18,17,13};
        int[] cachesMaria = {3,8,9,10,16,11,12};
        int[] cachesFilomena = {5,6,7,3,2,1,8,13};

        //Manuel
        for (int i = 0; i<cachesManuel.length; i++) {
            String geocache = "geocache" + cachesManuel[i];
            geocaching.addCacheVisitada(1, geocache);
        }
        User manuel = geocaching.findUser(1);
        geocaching.moverTravelBug("travelbug2", "geocache17");

        //Pedro
        for (int i = 0; i<cachesPedro.length; i++) {
            String geocache = "geocache" + cachesPedro[i];
            geocaching.addCacheVisitada(2, geocache);
        }
        User pedro = geocaching.findUser(2);
        Cache geocache8 = geocaching.findCache("geocache8");
        Item capa = geocache8.findItem("capa");
        geocache8.removeItem(capa);

        //Fernando
        for (int i = 0; i<cachesFernando.length; i++) {
            String geocache = "geocache" + cachesFernando[i];
            geocaching.addCacheVisitada(3, geocache);
        }
        User fernando = geocaching.findUser(3);
        geocaching.moverTravelBug("travelbug1", "geocache15");

        //Joana
        for (int i = 0; i<cachesJoana.length; i++) {
            String geocache = "geocache" + cachesJoana[i];
            geocaching.addCacheVisitada(4, geocache);
        }
        User joana = geocaching.findUser(4);

        //Maria
        for (int i = 0; i<cachesMaria.length; i++) {
            String geocache = "geocache" + cachesMaria[i];
            geocaching.addCacheVisitada(3, geocache);
        }
        User maria = geocaching.findUser(3);
        geocaching.moverTravelBug("travelbug3", "geocache12");

        //Filomena
        for (int i = 0; i<cachesFilomena.length; i++) {
            String geocache = "geocache" + cachesFilomena[i];
            geocaching.addCacheVisitada(3, geocache);
        }
        User filomena = geocaching.findUser(3);





        geocaching.pesquisaTopUtilizador();

        //Remover user Pedro
        geocaching.removeUser(2);

        //Remover as geocaches 4, 8, 18
        geocaching.removeCache("geocache4");
        geocaching.removeCache("geocache8");
        geocaching.removeCache("geocache18");


        //Listar todas as caches
        geocaching.listCaches();
        //Listar os itens atuais
        geocaching.listItems();
        //Geocaches visitadas por cada user
        geocaching.pesquisaCachesUserGlobal(manuel);
        geocaching.pesquisaCachesUserGlobal(pedro);
        geocaching.pesquisaCachesUserGlobal(fernando);
        geocaching.pesquisaCachesUserGlobal(joana);
        geocaching.pesquisaCachesUserGlobal(maria);
        geocaching.pesquisaCachesUserGlobal(filomena);
        //Todos os users que visitaram a geocache6
        geocaching.pesquisaUsersVisitaramCache("geocache6");
        //Todas as geocaches premium com pelo menos um objeto
        geocaching.pesquisaCachesPremiumComObjeto();
        //Top-5 utilizadores que visitaram o maior numero de caches

        //Travelbugs com maior numero de localizacoes no seu historico
        geocaching.pesquisaTravelbugMaisLocalizacoes();

        geocaching.pesquisaUserTop5();

    }




}