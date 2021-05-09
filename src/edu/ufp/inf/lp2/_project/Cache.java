package edu.ufp.inf.lp2._project;

import java.util.ArrayList;
import java.util.Objects;

public class Cache {

    private String id;
    private float latitude;
    private float longitude;
    private int nObjetos;
    private String tipo;
    private String dificuldade;
    private Regiao regiao;
    private final ArrayList<Item> historicoItens;
    private final ArrayList<Log> historicoLogs;
    private final ArrayList<String> nomesObjetos;

    private static final String ID_POR_OMISSAO = "";
    private static final float LATITUDE_POR_OMISSAO = 0;
    private static final float LONGITUDE_POR_OMISSAO = 0;
    private static final int N_OBJETOS_POR_OMISSAO = 0;
    private static final String TIPO_POR_OMISSAO = "Básico";
    private static final String DIFICULDADE_POR_OMISSAO = "Fácil";

    public Cache(String id, String tipo, float latitude, float longitude, int nObjetos, ArrayList<String> nomesObjetos) {
        this.id = id;
        this.tipo = tipo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nObjetos = nObjetos;
        this.nomesObjetos = nomesObjetos;
        this.regiao = new Regiao();
        this.dificuldade = DIFICULDADE_POR_OMISSAO;
        this.historicoItens = new ArrayList<>();
        this.historicoLogs = new ArrayList<>();
    }

    public Cache(String id, String tipo, float latitude, float longitude, int nObjetos, Regiao regiao, String dificuldade) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nObjetos = nObjetos;
        this.tipo = tipo;
        this.dificuldade = dificuldade;
        this.regiao = regiao;
        this.nomesObjetos = new ArrayList<>();
        this.historicoItens = new ArrayList<>();
        this.historicoLogs = new ArrayList<>();
    }

    public Cache() {
        this.id = ID_POR_OMISSAO;
        this.latitude = LATITUDE_POR_OMISSAO;
        this.longitude = LONGITUDE_POR_OMISSAO;
        this.nObjetos = N_OBJETOS_POR_OMISSAO;
        this.tipo = TIPO_POR_OMISSAO;
        this.dificuldade = DIFICULDADE_POR_OMISSAO;
        this.regiao = new Regiao();
        this.nomesObjetos = new ArrayList<>();
        this.historicoItens = new ArrayList<>();
        this.historicoLogs = new ArrayList<>();
    }

    /**
     * Get cache id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Set cache id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get cache latitude
     * @return
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * Set cache latitude
     * @param latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Get cache type
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Set cache type
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Get cache dificulty
     * @return
     */
    public String getDificuldade() {return dificuldade;}

    /**
     * Set cache dificulty
     * @param dificuldade
     */
    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    /**
     * Get cache region
     * @return
     */
    public Regiao getRegiao() {
        return regiao;
    }

    /**
     * Set cache region
     * @param regiao
     */
    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    /**
     * get Cache longitude
     * @return
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * Set cache longitude
     * @param longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Get number of objects in a cache
     * @return
     */
    public int getnObjetos() {
        return nObjetos;
    }

    /**
     * Set cache number o objects
     * @param nObjetos
     */
    public void setnObjetos(int nObjetos) {
        this.nObjetos = nObjetos;
    }

    /**
     * get item history of a cache
     * @return
     */
    public ArrayList<Item> getHistoricoItens() {
        return historicoItens;
    }

    /**
     * get log history of a cache
     * @return
     */
    public ArrayList<Log> getHistoricoLogs() {
        return historicoLogs;
    }

    /**
     * Get names of objets in a cache
     * @return
     */
    public ArrayList<String> getNomesObjetos() {
        return nomesObjetos;
    }

    /**
     * Add new item to a cache
     * @param item
     */
    public void addItem(Item item) {
        historicoItens.add(item);
    }

    /**
     * Remove an item
     * @param item
     */
    public void removeItem(Item item) {
        historicoItens.remove(item);
    }

    /**
     * Find item
     * @param item
     * @return
     */
    public Item findItem(String item) {
        for(Item itens : historicoItens) {
            if (itens.getNome().equals(item)) {
                return itens;
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cache)) return false;
        Cache cache = (Cache) o;
        return getId() == cache.getId() && Float.compare(cache.getLatitude(), getLatitude()) == 0 && Float.compare(cache.getLongitude(), getLongitude()) == 0 && getnObjetos() == cache.getnObjetos() && Objects.equals(nomesObjetos, cache.nomesObjetos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLatitude(), getLongitude(), getnObjetos(), nomesObjetos);
    }

    @Override
    public String toString() {
        return String.format("ID: %s\n" +
                "Latitude: %f\n" +
                "Longitude: %f\n" +
                "Número de objetos: %d\n" +
                "Nomes dos objetos: %s\n", this.id, this.latitude, this.longitude, this.nObjetos, this.nomesObjetos);
    }

}