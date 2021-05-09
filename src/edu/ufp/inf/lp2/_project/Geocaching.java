package edu.ufp.inf.lp2._project;

import edu.princeton.cs.algs4.BST;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

//TODO - Diagrama UML
//TODO - Criar graph
//TODO - Ordenar na listagem de utilizadores
//TODO - Criar listagem das outras classes
//TODO - Remover e editar as outras classes
//TODO - Pesquisas
//TODO - Documentação

//TODO - guardarInfo()

public class Geocaching {

    private static Scanner in;
    Hashtable<Integer, User> users = new Hashtable<>();
    BST<Integer, Integer> nCachesVisitadas = new BST<>();
    Hashtable<Regiao, Hashtable<String, Cache>> regioesComCaches = new Hashtable<>();
    Hashtable<String, TravelBug> travelBugs = new Hashtable<>();

    /**
     * Importa toda a informação relativa a todas as estruturas de dados
     * @param filename - path do ficheiro a importar
     * @throws FileNotFoundException
     */
    public void carregarInfo(String filename) throws FileNotFoundException {
        in = new Scanner(new File(filename));
        while (in.hasNextLine()) {
            String linha = in.nextLine();
            String[] info = linha.trim().split(",");
            int nUsers = Integer.parseInt(info[0].trim());
            lerUsers(nUsers);

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

        }
    }

    public void guardarInfo(String filename) throws IOException {
        FileWriter myWriter = new FileWriter(filename);

        //nº users
        //lineByline: id, nome, tipo
        myWriter.write(users.size());
        for (User user : users.values()) {
            myWriter.write(user.getId() + ", " + user.getNome() + ", " + user.getTipo());
        }
        //nº regioes
        //lineByline: nomee, nº caches
        //Cache: id, tipo, latitude, longitude, nº objetos, [objetos]
        myWriter.write(regioesComCaches.size());
        for (Regiao regiao: regioesComCaches.keySet()) {
            myWriter.write(regiao.getNome() + ", " + regiao.getnCaches());
            for (Hashtable<String, Cache> caches : regioesComCaches.values()) {
                for (Cache cache : caches.values()) {
                    myWriter.write(cache.getId() + ", " + cache.getTipo() + ", "
                                    + cache.getLatitude() + ", " + cache.getLongitude()
                                    + ", " + cache.getnObjetos() + ", "
                                    + cache.getNomesObjetos().toString()
                    );
                }
            }
        }

        //nº ligações entre geocaches
        //lineByline: geocache_inicial, geocache_final, distancia, tempo viagem entre geocaches


        //nº travelbugs
        //lineByline: id, nome user que criou, geocache_inicial, geocache_destino


        myWriter.close();

    }

    /////////////////////////////////////////USERS/////////////////////////////////////////

    /**
     * Lê os utilizadores de um ficheiro
     * @param n - número de utilizadores a ler
     */
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

    /**
     * Adiciona um utilizador (através de ficheiro)
     * @param user
     */
    private void addUser(User user) {
        System.out.println("adduser");
        int id = user.getId();
        if (!users.containsKey(id)) {
            users.put(id, user);
            nCachesVisitadas.put(id, 0);
        }
    }

    /**
     * Adicionar um utilizador novo
     * @param user
     */
    public void addNewUser(User user) {
        System.out.println("addnewuser");
        int id = users.size();
        users.put(id, user);
        //nCachesVisitadas.put(user, 0);
    }

    /**
     * Lista todos os utilizadores
     */
    public void listUsers() {
        int count = 0;
        System.out.println("\n*********************************** LISTA DE UTILIZADORES **************************************");
        for (User user : users.values()) {
            System.out.print(user.toString());
            count++;
        }
        System.out.println("**************************************** TOTAL: " + count + " ************************************************");
    }

    /**
     * Encontra um utilizador através do seu id
     * @param id - id do utilizador a procurar
     * @return
     */
    public User findUser(int id){
        for (User user : users.values()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    /**
     * Edita as informações de um utilizador
     * @param id - id do utilizador a alterar
     * @param nome - novo nome (se != de "")
     * @param tipo - novo tipo (se != de "")
     */
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

    /**
     * Remove um utilizador
     * @param id - id do utilizador a remover
     */
    public void removeUser(int id) {
        users.remove(id);
    }

    /**
     * Guarda para um ficheiro as informações dos utilizadores
     * @param filename - ficheiro onde queremos guardar a informação
     * @throws IOException
     */
    public void guardarUsers(String filename) throws IOException {
        //nº users
        //lineByline: id, nome, tipo
        FileWriter myWriter = new FileWriter(filename);
        myWriter.write(users.size() + "\n");
        for (User user : users.values()) {
            myWriter.write(user.getId() + ", " + user.getNome() + ", " + user.getTipo() + "\n");
        }
        myWriter.close();
    }

    /////////////////////////////////////////ITEM/////////////////////////////////////////


    public void listItems() {
        System.out.println("\n********************************* LISTA DE ITENS *********************************");
        for (Hashtable<String, Cache> caches : regioesComCaches.values()) {
            for (Cache cache : caches.values()) {
                System.out.print("CACHE: " + cache.getId() + "\t\t" + cache.getnObjetos() + " OBJETOS: ");
                for (Item item : cache.getHistoricoItens()) {
                    System.out.print(item.getNome() + "  ");
                }
                System.out.print("\n");
            }
        }
        System.out.println("**********************************************************************************");
    }


    /////////////////////////////////////////REGIÕES/////////////////////////////////////////

    /**
     * Lê as regiões de um ficheiro
     * @param n - número de regiões a adicionar
     */
    private void lerRegioes(int n) {
        for (int i = 0; i < n; i++) {
            String[] linha;
            linha = in.nextLine().trim().split(",");
            int nCaches = Integer.parseInt(linha[1].trim());
            Regiao regiao = new Regiao(linha[0], nCaches);
            lerCaches(regiao, nCaches);
        }
    }

    /**
     * Lista todas as regiões e a respetiva quantidade de caches
     */
    public void listRegioes() {
        int count = 0;
        System.out.println("\n***************************************************** LISTA DE REGIÕES *****************************************************");
        for (Regiao regiao : regioesComCaches.keySet()) {
            System.out.println("NOME: " + regiao.getNome() + "\t\t\t\t\t Nº CACHES: " + regiao.getnCaches());
            count++;
        }
        System.out.println("*********************************************************** TOTAL: " + count + " ********************************************************");
    }

    /**
     * Procura uma região
     * @param nome - nome da região a procurar
     * @return região (se encontrar) ou null (se não encontrar)
     */
    public Regiao findRegiao(String nome) {
        for (Regiao regiao : regioesComCaches.keySet()) {
            if (regiao.getNome().equals(nome)) {
                return regiao;
            }
        }
        return null;
    }

    /**
     * Edita as informações de uma região
     * @param regiao - região a editar
     * @param nome - novo nome (se != de "")
     * @param nCaches - nova quantidade de caches (se >0)
     */
    public void editRegiao(Regiao regiao, String nome, int nCaches) {
        for (Regiao regiao1 : regioesComCaches.keySet()) {
            if (regiao1.compareTo(regiao) == 0) {
                if (!regiao.getNome().equals(nome) && !nome.equals("")) {
                    regiao1.setNome(nome);
                }
                if (regiao.getnCaches() != nCaches && nCaches>=0) {
                    regiao1.setnCaches(nCaches);
                }
            }
        }
    }

    /**
     * Adiciona uma região a regioesComCaches
     * @param regiao - região a ser adicionda
     */
    public void addRegiao(Regiao regiao) {
        for (Regiao regiao1 : regioesComCaches.keySet()) {
            if (regiao1.compareTo(regiao) != 0) {
                regioesComCaches.put(regiao, null);
            }
        }
    }

    /**
     * Remove uma região
     * @param nomeRegiao - nome da região a remover
     */
    public void removeRegiao(String nomeRegiao) {
        Regiao regiao = findRegiao(nomeRegiao);
        regioesComCaches.remove(regiao);
    }


    /////////////////////////////////////////CACHES/////////////////////////////////////////

    /**
     * Lê de um ficheiro e inicializa uma região
     * @param regiao - regiao a adiconar
     * @param n - numero de caches a ciar para a região
     */
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
            Cache cache = new Cache(linha[0].trim(), linha[1].trim(), Float.parseFloat(linha[2]), Float.parseFloat(linha[3]),
                    Integer.parseInt(linha[4].trim()), nomesObjetos);
            cache.setRegiao(regiao);
            addCacheRegiao(regiao, cache);
            for (int k=0; k<nomesObjetos.size(); k++) {
                String nome = nomesObjetos.get(k);
                Item item = new Item(nome) {
                    @Override
                    public void setNome(String nome) {
                        super.setNome(nome);
                    }
                };
                cache.addItem(item);
            }
        }
    }

    /**
     * Adicona uma cache a uma regiao
     * @param regiao - região à qual queremos adicionar caches
     * @param cache - cache que queremos adicionar
     */
    private void addCacheRegiao(Regiao regiao, Cache cache) {
        String cacheID = cache.getId();
        if (regioesComCaches.containsKey(regiao)) {
            regioesComCaches.get(regiao).put(cacheID, cache);
        } else {
            Hashtable<String, Cache> caches = new Hashtable<>();
            caches.put(cacheID, cache);
            regioesComCaches.put(regiao, caches);
        }
        for (User user : users.values()) {
            if (user.getTipo().equals(cache.getTipo())) {
                user.cachesNaoVisitadas.add(cache.getId());
            }
        }
    }

    /**
     * Listar todas as caches numa região
     * @param regiao - região que queremos listar o numero de caches
     */
    public void listCachesInRegiao(Regiao regiao) {
        System.out.println("******** LISTA DE CACHES NA REGIÃO " +  regiao.getNome() +  " ********");
        for (Regiao regiao2 : regioesComCaches.keySet()) {
            if (regiao2.compareTo(regiao) == 0) {
                Hashtable<String, Cache> cachesRegiao = regioesComCaches.get(regiao);
                for (Cache cache : cachesRegiao.values()) {
                    System.out.println("ID: " + cache.getId() + "\t\tLAT. " + cache.getLatitude()
                        + "\t\tLONG. " + cache.getLongitude() + "\t\tNº OBJ. " + cache.getnObjetos()
                        + "\t\tTIPO: " + cache.getTipo() + "\t\tDIFICULDADE: " + cache.getDificuldade()
                    );
                }
                //System.out.println(regioesComCaches.get(regiao));
            }
        }
    }

    /**
     * Listar todas as caches
     */
    public void listCaches() {
        System.out.println("\n******************************************************** LISTA DE CACHES ********************************************************");
        int count = 0;
        for (Regiao regiao : regioesComCaches.keySet()) {
            Hashtable<String, Cache> cachesRegiao = regioesComCaches.get(regiao);
            for (Cache cache : cachesRegiao.values()) {
                System.out.println("ID: " + cache.getId() + "\t\tLAT. " + cache.getLatitude()
                        + "\t\tLONG. " + cache.getLongitude() + "\t\tNº OBJ. " + cache.getnObjetos()
                        + "\t\tTIPO: " + cache.getTipo() + "\t\tDIF. " + cache.getDificuldade()
                        + "\t\tREGIAO: " + cache.getRegiao().getNome()
                );
                count++;
            }
        }
        System.out.println("************************************************************* TOTAL: " + count + " ***********************************************************");

    }

    /**
     * Procura e retorna uma cache pelo id
     * @param id - id da cache a procurar
     * @return cache (se encontrar) ou null (se não encontrar)
     */
    public Cache findCache (String id) {
        for (Regiao regiao : regioesComCaches.keySet()) {
            Hashtable<String, Cache> cachesRegiao = regioesComCaches.get(regiao);
            for (Cache cache : cachesRegiao.values()) {
                if (cache.getId().equals(id)) {
                    return cache;
                }
            }
        }
        return null;
    }

    /**
     * Remove uma cache pelo id
     * @param id - id da cache a ser removida
     */
    public void removeCache(String id) {
        Cache cache = findCache(id);
        if (cache == null) {
            System.out.println("removeCache(" + id + "): cache não encontrada. Impossível remover");
            return;
        }
        String nomeRegiao = cache.getRegiao().toString();
        Regiao regiao = findRegiao(nomeRegiao);
        regioesComCaches.remove(id);

        for (User user : users.values()) {
            user.cachesVisitadas.remove(id);
            user.cachesNaoVisitadas.remove(id);
        }

    }

    public Cache findItemCache (String nomeItem) {
        for (Regiao regiao : regioesComCaches.keySet()) {
            Hashtable<String, Cache> cachesRegiao = regioesComCaches.get(regiao);
            for (Cache cache : cachesRegiao.values()) {
                for (Item itens : cache.getHistoricoItens()){
                    if (itens.getNome().equals(nomeItem)) {
                        return cache;
                    }
                }
            }
        }
        return null;
    }

    /////////////////////////////////////////TRAVELBUGS/////////////////////////////////////////

    /**
     * Lê os travelbugs de um ficheiro
     * @param n - numero de travelbugs a criar
     */
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

    /**
     * Adiciona um travelbug
     * @param travelBug - travelbug a ser adicionado
     */
    public void addTravelBug(TravelBug travelBug) {
        String id = travelBug.getId();
        if (!travelBugs.containsKey(id)) {
            travelBugs.put(id, travelBug);
        }
    }

    /**
     * Lista todos os travelbugs
     */
    public void listTravelBugs() {
        System.out.println("\n******************************************************** " + "LISTA DE TRAVELBUGS ********************************************************");
        int count = 0;
        for (TravelBug travelBug : travelBugs.values()) {
            Cache cacheInicial = travelBug.getCacheInicial();
            Cache cacheFinal = travelBug.getCacheFinal();
            System.out.println("ID: " + travelBug.getId() + "\t\t USER: "
                + travelBug.getNomeUser() + "\t\t CACHE INICIAL: "
                + cacheInicial.getId() + "\t\t CACHE FINAL: " + cacheFinal.getId()
            );
            count++;
        }
        System.out.println("*************************************************************** " + "TOTAL: " + count + " ************************************************************");
    }

    /**
     * Encontra um travelbug através do seu id
     * @param id - id do travelbug a encontrar
     * @return travelBug (se encontrar) ou null (se não encontrar)
     */
    public TravelBug findTravelBug(String id) {
        for (TravelBug travelBug : travelBugs.values()) {
            if (travelBug.getId().equals(id)) {
                return travelBug;
            }
        }
        return null;
    }

    /**
     * Edita o nome de utilizador de um travelBug
     * @param id - id do travelBug a editar
     * @param nomeUser - Novo username (se != de "")
     */
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

    /**
     * Remove um travelBug através de um id
     * @param id - id do travelbug a remover
     */
    public void removeTravelBug(String id) {
        travelBugs.remove(id);
    }

    public void moverTravelBug(String idTravelbug, String idCacheFinal) {
        TravelBug travelBug = findTravelBug(idTravelbug);
        Cache cacheFinal = findCache(idCacheFinal);
        if (travelBug == null || cacheFinal == null) {
            return;
        }
        travelBug.setCacheFinal(cacheFinal);
        travelBug.addLocalizacao(cacheFinal);
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

    /////////////////////////////////////////PESQUISAS/////////////////////////////////////////

    public void pesquisaCachesUserRegiao(User user, String nomeRegiao) {
        System.out.println("\n********************************* CACHES VISITADAS POR " + user.getNome() + " EM " + nomeRegiao +" *********************************");
        int count = 0;
        Regiao regiao = findRegiao(nomeRegiao);
        if (regiao == null) {
            System.out.println("Região não encontrada");
            return;
        }
        for (int i=0; i<user.cachesVisitadas.size(); i++) {
            String idCache = user.getCachesVisitadas().get(i);
            Cache cache = findCache(idCache);
            if (nomeRegiao.equals(cache.getRegiao().getNome())) {
                System.out.println("ID: " + user.getCachesVisitadas().get(i));
                count++;
            }
        }
        System.out.println("************************************************ TOTAL: " + count + " ***********************************************");
    }

    public void pesquisaCachesUserGlobal(User user) {
        System.out.println("\n********************************* CACHES VISITADAS POR " + user.getNome() + " *********************************");
        int count = 0;
        for (int i=0; i<user.cachesVisitadas.size(); i++) {
            System.out.println("ID: " + user.getCachesVisitadas().get(i));
            count++;
        }
        System.out.println("****************************************** TOTAL: " + count + " **********************************************");
    }

    public void pesquisaCachesPremiumComObjeto() {
        System.out.println("\n********************************* CACHES PREMIUM COM PELO MENOS UM OBJETO *********************************");
        int count = 0;
        for (Regiao regiao : regioesComCaches.keySet()) {
            Hashtable<String, Cache> cachesRegiao = regioesComCaches.get(regiao);
            for (Cache cache : cachesRegiao.values()) {
                if (cache.getTipo().equals("premium") && cache.getnObjetos() > 0) {
                    System.out.println("ID: " + cache.getId() + "\t\t LAT. "
                            + cache.getLatitude() + "\t\t LONG. "
                            + cache.getLongitude() + "\t\t Nº OBJ. "
                            + cache.getnObjetos());
                    count++;
                }
            }
        }
        System.out.println("************************************************* TOTAL: " + count + " *************************************************");
    }

    public void pesquisaUsersVisitaramCache(String idCache) {
        System.out.println("\n********************************* UTILIZADORES QUE VISITARAM " + idCache + " *********************************");
        int count=0;
        for (User user : users.values()) {
            if (user.cachesVisitadas.contains(idCache)) {
                System.out.println("ID: " + user.getId() + "\t\t\t NOME: " + user.getNome());
                count++;
            }
        }
        System.out.println("******************************************* TOTAL: " + count + " ********************************");
    }

    public void pesquisaTopUtilizador() {
        User user1 = null;
        int maior = 0;
        for (User user : users.values()) {
            if (user.cachesVisitadas.size() > maior) {
                maior = user.cachesVisitadas.size();
                user1 = user;
            }
        }
        assert user1 != null;
        System.out.println("\n-> User com maior numero de caches visitadas: " + user1.getNome()
                            + " (" + user1.getCachesVisitadas().size() + " caches)"
        );
    }

    public void addCacheVisitada (int idUser, String idCache) {

        User user = findUser(idUser);
        Cache cache = findCache(idCache);

        if (user.getTipo().equals("basic")) {
            //Só caches basic
            if (cache.getTipo().equals("premium") || cache.getTipo().equals("admin")) {
                System.out.println("-> Cache <" + cache.getId() + "> (" + cache.getTipo() + ") fora da hierarquia do utilizador" + user.getNome() + " (" + user.getTipo() + ").");
                return;
            }
        }

        if (user.getTipo().equals("premium") && cache.getTipo().equals("admin")) {
            //Só caches premium
            System.out.println("-> Cache <" + cache.getId() + "> (" + cache.getTipo() + ") fora da hierarquia do utilizador" + user.getNome() + " (" + user.getTipo() + ").");
            return;
        }

        if (user.cachesVisitadas.size() == 0) {
            user.cachesVisitadas.add(idCache);
            user.cachesNaoVisitadas.remove(idCache);
            nCachesVisitadas.put(idUser, 1);
            return;
        }

        for (int i = 0; i<user.cachesVisitadas.size(); i++) {
            if (!user.cachesVisitadas.contains(idCache)) {
                user.cachesVisitadas.add(idCache);
                user.cachesNaoVisitadas.remove(idCache);

                Integer numeroCachesVisitadas = nCachesVisitadas.get(idUser);
                numeroCachesVisitadas++;
                nCachesVisitadas.put(idUser, numeroCachesVisitadas);

            }
        }

    }

    public void pesquisaTravelbugMaisLocalizacoes() {
        System.out.println("\n********************************* TRAVELBUG COM MAIS LOCALIZAÇÕES *********************************");
        int maior = 0;
        TravelBug winner = new TravelBug();
        for(TravelBug travelBug : travelBugs.values()) {
            if (travelBug.getNLocalizacoes() > maior) {
                winner = travelBug;
                maior = travelBug.getNLocalizacoes();
            }
        }
        System.out.println("ID: " + winner.getId() + "\t\tNº LOCALIZACOES: " + maior);
        System.out.println("***************************************************************************************************");
    }


    public void pesquisaUserTop5(){
        System.out.println("\n****************************************** TOP-5 USERS *****************************************");
        for (int i=0; i<nCachesVisitadas.size(); i++) {
            System.out.println("User: " + nCachesVisitadas.select(i) + "\t\tNº CACHES: " + nCachesVisitadas.get(i));
        }
        System.out.println("***************************************************************************************************");
    }

}
