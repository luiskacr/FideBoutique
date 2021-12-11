package model;

public class user {

    private int USER_AUTH_ID;
    private String USER_NAME;
    private String PASSWORD;
    private String USER_ROL;
  
    public user() {
    }
    
    public user(int USER_AUTH_ID, String USER_NAME, String PASSWORD, String USER_ROL) {
        this.USER_AUTH_ID = USER_AUTH_ID;
        this.USER_NAME = USER_NAME;
        this.PASSWORD = PASSWORD;
        this.USER_ROL = USER_ROL;
    }

    public int getUSER_AUTH_ID() {
        return USER_AUTH_ID;
    }

    public void setUSER_AUTH_ID(int USER_AUTH_ID) {
        this.USER_AUTH_ID = USER_AUTH_ID;
    }
    
    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getUSER_ROL() {
        return USER_ROL;
    }

    public void setUSER_ROL(String USER_ROL) {
        this.USER_ROL = USER_ROL;
    }
}
