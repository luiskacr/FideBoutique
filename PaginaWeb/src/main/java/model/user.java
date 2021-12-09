package model;

public class user {

    //atributos
    private String USER_NAME;
    private String PASSWORD;
    private String USER_ROL;

    //constructor vacio
    public user() {
    }

    public user(String USER_NAME, String PASSWORD, String USER_ROL) {
        this.USER_NAME = USER_NAME;
        this.PASSWORD = PASSWORD;
        this.USER_ROL = USER_ROL;
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
