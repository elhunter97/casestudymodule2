package model;

public class Medicine {
    private String medID;
    private String medName;
    private String medType;
    private String inputDate;
    private String expDate;
    private int price;
    private int quantity;

    public Medicine() {
    }

    public Medicine(String medID,String medName, String medType, String inputDate, String expDate, int price, int quantity) {
        this.medID = "MD"+medID;
        this.medName = medName;
        this.medType = medType;
        this.inputDate = inputDate;
        this.expDate = expDate;
        this.price = price;
        this.quantity = quantity;
    }

    public String getMedID() {
        return medID;
    }

    public void setMedID(String medID) {
        this.medID = medID;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedType() {
        return medType;
    }

    public void setMedType(String medType) {
        this.medType = medType;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return medID+","+medName+","+medType+","+inputDate+","+expDate+","+price+","+quantity;
    }
}
