package model;

public class Productos {

    private int PRODUCT_ID;
    private String PRODUCT_NAME;
    private int TYPE_PRODUCT_ID;
    private int DEPARMENT_PRODUCT_ID;
    private int BRAND_PRODUCT_ID;
    private String PRODUCT_DESCRIPTION;
    private int PRODUCT_QUANTITIES;
    private float PRODUCT_PRICE;

    public Productos() {
    }

    public Productos(int PRODUCT_ID, String PRODUCT_NAME, int TYPE_PRODUCT_ID, int DEPARMENT_PRODUCT_ID, int BRAND_PRODUCT_ID, String PRODUCT_DESCRIPTION, int PRODUCT_QUANTITIES, float PRODUCT_PRICE) {
        this.PRODUCT_ID = PRODUCT_ID;
        this.PRODUCT_NAME = PRODUCT_NAME;
        this.TYPE_PRODUCT_ID = TYPE_PRODUCT_ID;
        this.DEPARMENT_PRODUCT_ID = DEPARMENT_PRODUCT_ID;
        this.BRAND_PRODUCT_ID = BRAND_PRODUCT_ID;
        this.PRODUCT_DESCRIPTION = PRODUCT_DESCRIPTION;
        this.PRODUCT_QUANTITIES = PRODUCT_QUANTITIES;
        this.PRODUCT_PRICE = PRODUCT_PRICE;

    }

    public int getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(int PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public int getTYPE_PRODUCT_ID() {
        return TYPE_PRODUCT_ID;
    }

    public void setTYPE_PRODUCT_ID(int TYPE_PRODUCT_ID) {
        this.TYPE_PRODUCT_ID = TYPE_PRODUCT_ID;
    }

    public int getDEPARMENT_PRODUCT_ID() {
        return DEPARMENT_PRODUCT_ID;
    }

    public void setDEPARMENT_PRODUCT_ID(int DEPARMENT_PRODUCT_ID) {
        this.DEPARMENT_PRODUCT_ID = DEPARMENT_PRODUCT_ID;
    }

    public int getBRAND_PRODUCT_ID() {
        return BRAND_PRODUCT_ID;
    }

    public void setBRAND_PRODUCT_ID(int BRAND_PRODUCT_ID) {
        this.BRAND_PRODUCT_ID = BRAND_PRODUCT_ID;
    }

    public String getPRODUCT_DESCRIPTION() {
        return PRODUCT_DESCRIPTION;
    }

    public void setPRODUCT_DESCRIPTION(String PRODUCT_DESCRIPTION) {
        this.PRODUCT_DESCRIPTION = PRODUCT_DESCRIPTION;
    }

    public int getPRODUCT_QUANTITIES() {
        return PRODUCT_QUANTITIES;
    }

    public void setPRODUCT_QUANTITIES(int PRODUCT_QUANTITIES) {
        this.PRODUCT_QUANTITIES = PRODUCT_QUANTITIES;
    }

    public float getPRODUCT_PRICE() {
        return PRODUCT_PRICE;
    }

    public void setPRODUCT_PRICE(float PRODUCT_PRICE) {
        this.PRODUCT_PRICE = PRODUCT_PRICE;
    }

}
