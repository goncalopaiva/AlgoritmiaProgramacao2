package edu.ufp.inf.lp2._project;

import edu.princeton.cs.algs4.*;
import org.junit.jupiter.engine.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Scanner;

//TODO - Diagrama UML
//TODO - Criar graph
//TODO - Ordenar na listagem de utilizadores
//TODO - Criar listagem das outras classes
//TODO - Remover e editar as outras classes
//TODO - Pesquisas
//TODO - Documentação

public class Geocaching {

    private static Scanner in;
    Hashtable<Integer, User> users = new Hashtable<>();
    Hashtable<String, Hashtable<String, Cache>> regioesComCaches = new Hashtable<>();
    Hashtable<String, TravelBug> travelBugs = new Hashtable<>();


    public void carregarInfo(String filename) throws FileNotFoundException {
        in = new Scanner(new File(filename + ".txt"));
        while (in.hasNextLine()) {
            String linha = in.nextLine();
            String[] info = linha.trim().split(",");
            int nUsers = Integer.parseInt(info[0].trim());
            lerUsers(nUsers);

            ////////////////////////////////
            break;
            ////////////////////////////////


            /*

            // Avançar linha
            linha = in.nextLine();
            info = linha.trim().split(",");

            int nRegioes = Integer.parseInt(info[0].trim());
            lerRegioes(nRegioes);

            // Avançar linha
            linha = in.nextLine();
            info = linha.trim().split(",");

            int nLigacoes = Integer.parseInt(info[0].trim());
            lerLigacoes(nLigacoes);

            // Avançar linha
            linha = in.nextLine();
            info = linha.trim().split(",");

            int nTravelBugs = Integer.parseInt(info[0].trim());
            lerTravelBugs(nTravelBugs);
*/
        }
    }

    /////////////////////////////////////////USERS

    private void lerUsers(int n) {
        for (int i = 0; i < n; i++) {
            String[] linha;
            linha = in.nextLine().trim().split(",");
            if (linha[2].trim().equalsIgnoreCase("basic")) {
                BasicUser basic = new BasicUser(Integer.parseInt(linha[0].trim()), linha[1], linha[2].trim());
                addUser(basic);
            } else if (linha[2].trim().equalsIgnoreCase("premium")) {
                PremiumUser premium = new PremiumUser(Integer.parseInt(linha[0].trim()), linha[1], linha[2].trim());
                addUser(premium);
            } else if (linha[2].trim().equalsIgnoreCase("admin")) {
                AdminUser admin = new AdminUser(Integer.parseInt(linha[0].trim()), linha[1], linha[2].trim());
                addUser(admin);
            }
        }
    }

    private void addUser(User user) {
        int id = user.getId();
        if (!users.containsKey(id)) {
            users.put(id, user);
        }
    }

    public void listUsers(/*Hashtable<Integer, User> users*/) {
        System.out.println("******** LISTA DE UTILIZADORES ********");
        for (User user : users.values()) {
            System.out.print(user.toString());
        }
    }

    public User findUser(int id){
        for (User user : users.values()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void editUser(int id, String nome, String tipo) {
        //System.out.println("editUser()");
        User user = findUser(id);
        if (user == null) {
            System.out.println("Error finding user.");
            return;
        }
        if (!user.getNome().equals(nome) && !nome.equals("")) {
            user.setNome(nome);
        }
        if (!user.getTipo().equals(tipo) && !tipo.equals("")) {
            user.setTipo(tipo);
        }
    }

    public void removeUser(int id) {
        users.remove(id);
    }


    /////////////////////////////////////////REGIÕES

    private void lerRegioes(int n) {
        for (int i = 0; i < n; i++) {
            String[] linha;
            linha = in.nextLine().trim().split(",");
            int nCaches = Integer.parseInt(linha[1].trim());
            Regiao regiao = new Regiao(linha[0], nCaches);
            lerCaches(regiao, nCaches);
        }
    }

    /////////////////////////////////////////CACHES

    private void lerCaches(Regiao regiao, int n) {
        for (int i = 0; i < n; i++) {
            String[] linha;
            linha = in.nextLine().trim().split(",");

            // Ler nomes dos objetos
            ArrayList<String> nomesObjetos = new ArrayList<>();
            int aux = Integer.parseInt(linha[4].trim());
            for (int j = 0; j < aux; j++) {
                nomesObjetos.add(linha[5 + j].trim());
            }

            Cache cache = new Cache(linha[0], linha[1], Float.parseFloat(linha[2]), Float.parseFloat(linha[3]),
                    Integer.parseInt(linha[4].trim()), nomesObjetos);
            cache.setRegiao(regiao);
            addCacheRegiao(regiao, cache);
        }
    }

    private void addCacheRegiao(Regiao regiao, Cache cache) {
        String nomeRegiao = regiao.getNome();
        String cacheID = cache.getId();
        if (regioesComCaches.containsKey(nomeRegiao)) {
            regioesComCaches.get(nomeRegiao).put(cacheID, cache);
        } else {
            Hashtable<String, Cache> caches = new Hashtable<>();
            caches.put(cacheID, cache);
            regioesComCaches.put(nomeRegiao, caches);
        }
    }


    /////////////////////////////////////////TRAVELBUGS

    private void lerTravelBugs(int n) {
        for (int i = 0; i < n; i++) {
            String[] linha;
            linha = in.nextLine().trim().split(",");
            Cache cacheInicial = getCacheByID(linha[2].trim());
            Cache cacheFinal = getCacheByID(linha[3].trim());
            TravelBug travelBug = new TravelBug(linha[0].trim(), linha[1].trim(), cacheInicial, cacheFinal);
            travelBugs.put(linha[0].trim(), travelBug);
        }
    }

    public void addTravelBug(TravelBug travelBug) {
        String id = travelBug.getId();
        if (!travelBugs.containsKey(id)) {
            travelBugs.put(id, travelBug);
        }
    }

    public void listTravelBugs() {
        System.out.println("******** LISTA DE TRAVELBUGS ********");
        for (TravelBug travelBug : travelBugs.values()) {
            System.out.println(travelBug.toString());
        }
    }

    public TravelBug findTravelBug(String id) {
        for (TravelBug travelBug : travelBugs.values()) {
            if (travelBug.getId().equals(id)) {
                return travelBug;
            }
        }
        return null;
    }

    public void editTravelBug(String id, String nomeUser) {
        TravelBug travelBug = findTravelBug(id);
        if (travelBug == null) {
            System.out.println("Error finding travel bug.");
            return;
        }
        if (!travelBug.getNomeUser().equals(nomeUser) && !nomeUser.equals("")) {
            travelBug.setNomeUser(nomeUser);
        }
    }

    public void removeTravelBug(String id) {
        travelBugs.remove(id);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    private void lerLigacoes(int n) {
       for(int i = 0; i < n; i++){
           String[] linha;
           linha = in.nextLine().trim().split(",");
           Cache c1 = getCacheByID(linha[0].trim());
           Cache c2 = getCacheByID(linha[1].trim());

        }
    }


    private Cache getCacheByID(String id) {
        for (Hashtable<String, Cache> caches : regioesComCaches.values()) {
            for (Cache cache : caches.values()) {
                if (cache.getId().equals(id)) {
                    return cache;
                }
            }
        }
        return null;
    }



}
