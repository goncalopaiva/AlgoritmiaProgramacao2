package edu.ufp.inf.lp2._project;

import java.util.Objects;

/**
 * Classe representativa de um objeto região
 */
public class Regiao implements Comparable<Regiao> {
    /**
     * Nome da região
     */
    private String nome;
    private int nCaches;

    private static final String NOME_POR_OMISSAO = "";
    private static final int N_CACHES_POR_OMISSAO = 0;

    public Regiao(String nome, int nCaches) {
        this.nome = nome;
        this.nCaches = nCaches;
    }

    public Regiao() {
        this.nome = NOME_POR_OMISSAO;
        this.nCaches = N_CACHES_POR_OMISSAO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getnCaches() {
        return nCaches;
    }

    public void setnCaches(int nCaches) {
        this.nCaches = nCaches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Regiao)) return false;
        Regiao regiao = (Regiao) o;
        return getnCaches() == regiao.getnCaches() && Objects.equals(getNome(), regiao.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getnCaches());
    }

    @Override
    public String toString() {
        return String.format("Nome: %s" +
                "Número de caches: %d", this.nome, this.nCaches);
    }

    @Override
    public int compareTo(Regiao o) {
        return this.nome.compareTo(o.getNome());
    }
}
