package edu.ufp.inf.lp2._project;

import java.util.ArrayList;
import java.util.Objects;

public class TravelBug extends Item {
    private String id;
    private String nomeUser;
    private Cache cacheInicial;
    private Cache cacheFinal;
    private ArrayList<Cache> historicoLocalizacoes = new ArrayList<>();

    private static final String ID_POR_OMISSAO = "";
    private static final String NOME_USER_POR_OMISSAO = "";

    public TravelBug(String id, String nomeUSer, Cache cacheInicial, Cache cacheFinal) {
        this.id = id;
        this.nomeUser = nomeUSer;
        this.cacheInicial = cacheInicial;
        this.cacheFinal = cacheFinal;
    }

    public TravelBug() {
        this.id = ID_POR_OMISSAO;
        this.nomeUser = NOME_USER_POR_OMISSAO;
        this.cacheInicial = new Cache();
        this.cacheFinal = new Cache();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public Cache getCacheInicial() {
        return cacheInicial;
    }

    public void setCacheInicial(Cache cacheInicial) {
        this.cacheInicial = cacheInicial;
    }

    public Cache getCacheFinal() {
        return cacheFinal;
    }

    public void setCacheFinal(Cache cacheFinal) {
        this.cacheFinal = cacheFinal;
    }

    public ArrayList<Cache> getHistoricoLocalizacoes() {
        return historicoLocalizacoes;
    }

    public void setHistoricoLocalizacoes(ArrayList<Cache> historicoLocalizacoes) {
        this.historicoLocalizacoes = historicoLocalizacoes;
    }

    public void addLocalizacao(Cache cache) {
        historicoLocalizacoes.add(cache);
    }

    public int getNLocalizacoes() {
        return historicoLocalizacoes.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelBug)) return false;
        if (!super.equals(o)) return false;
        TravelBug travelBug = (TravelBug) o;
        return getId() == travelBug.getId() && Objects.equals(getNomeUser(), travelBug.getNomeUser()) && Objects.equals(getCacheInicial(), travelBug.getCacheInicial()) && Objects.equals(getCacheFinal(), travelBug.getCacheFinal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getNomeUser(), getCacheInicial(), getCacheFinal());
    }

    @Override
    public String toString() {
        return String.format("ID: %s \t" +
                "Nome de Utilizador: %s \t" +
                "Cache Inicial: %s \t" +
                "Cache Final: %s\n", this.id, this.nomeUser, this.cacheInicial, this.cacheFinal);
    }
}
