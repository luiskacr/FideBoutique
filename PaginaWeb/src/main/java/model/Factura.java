package model;

import java.util.Date;

public class Factura {

    private int USER_ID;
    private Date DATE_SELL;
    private float SALE_TOTAL;
    private int DISCOUNT_ID;
    private int PAYMENT_TYPE_ID;
    private int PAYMENT_STATE_ID;

    public Factura() {

    }

    public Factura(int USER_ID, Date DATE_SELL, float SALE_TOTAL, int DISCOUNT_ID, int PAYMENT_TYPE_ID, int PAYMENT_STATE_ID) {
        this.USER_ID = USER_ID;
        this.DATE_SELL = DATE_SELL;
        this.SALE_TOTAL = SALE_TOTAL;
        this.DISCOUNT_ID = DISCOUNT_ID;
        this.PAYMENT_TYPE_ID = PAYMENT_TYPE_ID;
        this.PAYMENT_STATE_ID = PAYMENT_STATE_ID;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public Date getDATE_SELL() {
        return DATE_SELL;
    }

    public void setDATE_SELL(Date DATE_SELL) {
        this.DATE_SELL = DATE_SELL;
    }

    public float getSALE_TOTAL() {
        return SALE_TOTAL;
    }

    public void setSALE_TOTAL(float SALE_TOTAL) {
        this.SALE_TOTAL = SALE_TOTAL;
    }

    public int getDISCOUNT_ID() {
        return DISCOUNT_ID;
    }

    public void setDISCOUNT_ID(int DISCOUNT_ID) {
        this.DISCOUNT_ID = DISCOUNT_ID;
    }

    public int getPAYMENT_TYPE_ID() {
        return PAYMENT_TYPE_ID;
    }

    public void setPAYMENT_TYPE_ID(int PAYMENT_TYPE_ID) {
        this.PAYMENT_TYPE_ID = PAYMENT_TYPE_ID;
    }

    public int getPAYMENT_STATE_ID() {
        return PAYMENT_STATE_ID;
    }

    public void setPAYMENT_STATE_ID(int PAYMENT_STATE_ID) {
        this.PAYMENT_STATE_ID = PAYMENT_STATE_ID;
    }

}
