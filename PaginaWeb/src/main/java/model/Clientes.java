package model;

public class Clientes {

    private int USER_ID;
    private String USER_NAME1;
    private String USER_NAME2;
    private String USER_LASTNAME1;
    private String USER_LASTNAME2;
    private String USER_ID_CARD;
    private String USER_EMAIL;
    private int USER_AUTH_ID;
    private int COUNTRY_ID;
    private int PROVINCE_ID;
    private int DISTRICT_ID;
    private int CANTON_ID;
    private String USER_ADDRESS;

    public Clientes() {
    }

    public Clientes(int USER_ID, String USER_NAME1, String USER_NAME2, String USER_LASTNAME1, String USER_LASTNAME2,
            String USER_ID_CARD, String USER_EMAIL, int USER_AUTH_ID, int COUNTRY_ID, int PROVINCE_ID, int DISTRICT_ID, int CANTON_ID, String USER_ADDRESS) {
        this.USER_ID = USER_ID;
        this.USER_NAME1 = USER_NAME1;
        this.USER_NAME2 = USER_NAME2;
        this.USER_LASTNAME1 = USER_LASTNAME1;
        this.USER_LASTNAME2 = USER_LASTNAME2;
        this.USER_ID_CARD = USER_ID_CARD;
        this.USER_EMAIL = USER_EMAIL;
        this.USER_AUTH_ID = USER_AUTH_ID;
        this.COUNTRY_ID = COUNTRY_ID;
        this.PROVINCE_ID = PROVINCE_ID;
        this.DISTRICT_ID = DISTRICT_ID;
        this.CANTON_ID = CANTON_ID;
        this.USER_ADDRESS = USER_ADDRESS;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NAME1() {
        return USER_NAME1;
    }

    public void setUSER_NAME1(String USER_NAME1) {
        this.USER_NAME1 = USER_NAME1;
    }

    public String getUSER_NAME2() {
        return USER_NAME2;
    }

    public void setUSER_NAME2(String USER_NAME2) {
        this.USER_NAME2 = USER_NAME2;
    }

    public String getUSER_LASTNAME1() {
        return USER_LASTNAME1;
    }

    public void setUSER_LASTNAME1(String USER_LASTNAME1) {
        this.USER_LASTNAME1 = USER_LASTNAME1;
    }

    public String getUSER_LASTNAME2() {
        return USER_LASTNAME2;
    }

    public void setUSER_LASTNAME2(String USER_LASTNAME2) {
        this.USER_LASTNAME2 = USER_LASTNAME2;
    }

    public String getUSER_ID_CARD() {
        return USER_ID_CARD;
    }

    public void setUSER_ID_CARD(String USER_ID_CARD) {
        this.USER_ID_CARD = USER_ID_CARD;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public int getUSER_AUTH_ID() {
        return USER_AUTH_ID;
    }

    public void setUSER_AUTH_ID(int USER_AUTH_ID) {
        this.USER_AUTH_ID = USER_AUTH_ID;
    }

    public int getCOUNTRY_ID() {
        return COUNTRY_ID;
    }

    public void setCOUNTRY_ID(int COUNTRY_ID) {
        this.COUNTRY_ID = COUNTRY_ID;
    }

    public int getPROVINCE_ID() {
        return PROVINCE_ID;
    }

    public void setPROVINCE_ID(int PROVINCE_ID) {
        this.PROVINCE_ID = PROVINCE_ID;
    }

    public int getDISTRICT_ID() {
        return DISTRICT_ID;
    }

    public void setDISTRICT_ID(int DISTRICT_ID) {
        this.DISTRICT_ID = DISTRICT_ID;
    }

    public int getCANTON_ID() {
        return CANTON_ID;
    }

    public void setCANTON_ID(int CANTON_ID) {
        this.CANTON_ID = CANTON_ID;
    }

    public String getUSER_ADDRESS() {
        return USER_ADDRESS;
    }

    public void setUSER_ADDRESS(String USER_ADDRESS) {
        this.USER_ADDRESS = USER_ADDRESS;
    }

}
