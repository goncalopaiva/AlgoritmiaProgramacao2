package edu.ufp.inf.lp2._project;

import java.util.Objects;

public abstract class Item {
    private String nome;
    private static final String NOME_POR_OMISSAO = "";

    public Item(String nome) {
        this.nome = nome;
    }

    public Item() {
        this.nome = NOME_POR_OMISSAO;
    }

    /**
     * get item name
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * set item name
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getNome(), item.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome());
    }

    @Override
    public String toString() {
        return String.format("Nome do item: %s\n", this.nome);
    }
}
