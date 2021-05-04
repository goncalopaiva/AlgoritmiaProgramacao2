package edu.ufp.inf.lp2._project;

import java.util.Objects;

public abstract class User {

    private int id;
    private String nome;
    private String tipo;

    private static final int ID_POR_OMISSAO = 0;
    private static final String NOME_POR_OMISSAO = "";
    private static final String TIPO_POR_OMISSAO = "Basic";

    public User(int id, String nome, String tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public User(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public User() {
        this.id = ID_POR_OMISSAO;
        this.nome = NOME_POR_OMISSAO;
        this.tipo = TIPO_POR_OMISSAO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getNome(), user.getNome()) && Objects.equals(getTipo(), user.getTipo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getTipo());
    }

    @Override
    public String toString() {
        return String.format("ID: %d\t\t" +
                "Nome: %s\t\t" +
                "Tipo: %s\n", this.id, this.nome, this.tipo);
    }

}
