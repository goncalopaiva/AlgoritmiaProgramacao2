package edu.ufp.inf.lp2._project;

import java.util.Date;
import java.util.Objects;

public class Log {
    private String mensagem;
    private Date data;
    private BasicUser user;

    private static final String MENSAGEM_POR_OMISSAO = "";
    private static final Date DATA_POR_OMISSAO = null;
    private static final BasicUser USER_POR_OMISSAO = null;

    public Log(String mensagem, Date data, BasicUser user) {
        this.mensagem = mensagem;
        this.data = data;
        this.user = user;
    }

    public Log() {
        this.mensagem = MENSAGEM_POR_OMISSAO;
        this.data = DATA_POR_OMISSAO;
        this.user = USER_POR_OMISSAO;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BasicUser getUser() {
        return user;
    }

    public void setUser(BasicUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log)) return false;
        Log log = (Log) o;
        return Objects.equals(getMensagem(), log.getMensagem()) && Objects.equals(getData(), log.getData()) && Objects.equals(getUser(), log.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMensagem(), getData(), getUser());
    }

    @Override
    public String toString() {
        return String.format("Mensagem: %s\n" +
                "Data: %s\n" +
                "domain.User: %s\n", this.mensagem, this.data, this.user);
    }
}