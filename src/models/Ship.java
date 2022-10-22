package models;

public class Ship {
    private int productNumber;
    private int shipNumber;
    private String name;
    private String version;
    private int id;

    public Ship(int id, int productNumber, int shipNumber, String name, String version) {
        this.id = id;
        this.productNumber = productNumber;
        this.shipNumber = shipNumber;
        this.name = name;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public void setShipNumber(int shipNumber) {
        this.shipNumber = shipNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public int getShipNumber() {
        return shipNumber;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
