package edu.ufp.inf.lp2._project;

public class BasicUser extends User{

    public BasicUser(int id, String nome, String tipo) {
        super(id, nome, tipo);
    }

    public BasicUser() {
        super();
    }

    @Override
    public int compareTo(User o) {
        return 0;
    }
}