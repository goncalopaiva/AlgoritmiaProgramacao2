package edu.ufp.inf.lp2._project;

import java.util.Vector;

public abstract class BasicUser extends Proprietario, BasicUser, PremiumUser, AdminUser {

  public ArrayList<String> hisCachesEscondidas;

  public ArrayList<String> hisCachesVisitadas;

    /**
   * 
   * @element-type Cache
   */
  public Vector  myCache;
    /**
   * 
   * @element-type Cache
   */
  public Vector  has;
    public Vector  myProprietario;
      public Vector  myLog;
    /**
   * 
   * @element-type Cache
   */
  public Vector  has;

  public void addUser() {
  }

  public void removeUser() {
  }

  public void editUser() {
  }

  public void listUsers() {
  }

  public void uploadUsers() {
  }

  public void downloadUsers() {
  }

}