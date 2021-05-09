package edu.ufp.inf.lp2._project;

import java.util.ArrayList;
import java.util.Objects;

public abstract class User implements Comparable<User> {

    private int id;
    private String nome;
    private String tipo;
    public ArrayList<String> cachesVisitadas = new ArrayList<>();
    public ArrayList<String> cachesNaoVisitadas = new ArrayList<>();

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

    /**
     * get user id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * set user id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get user name
     * @return name
     */
    public String getNome() {
        return nome;
    }

    /**
     * set user name
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * get user type
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * set user type
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getCachesNaoVisitadas() {
        return cachesNaoVisitadas;
    }

    public void setCachesNaoVisitadas(ArrayList<String> cachesNaoVisitadas) {
        this.cachesNaoVisitadas = cachesNaoVisitadas;
    }

    public ArrayList<String> getCachesVisitadas() {
        return cachesVisitadas;
    }

    public void setCachesVisitadas(ArrayList<String> cachesVisitadas) {
        this.cachesVisitadas = cachesVisitadas;
    }

    /**
     * compares if two objets are equal
     * @param o - object
     * @return true (if equal) or false (if not equal)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getNome(), user.getNome()) && Objects.equals(getTipo(), user.getTipo());
    }

    /**
     * return object hashcode
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getTipo());
    }

    /**
     * Returns a string representation of the object
     * @return
     */
    @Override
    public String toString() {
        return String.format("ID: %d\t\t" +
                "NOME: %s\t\t" +
                "TIPO: %s\n", this.id, this.nome, this.tipo);
    }

}
