package models;

public class Order {
    private String software;
    private String iklm;
    private String version;
    private String path;

    public Order(String software, String iklm, String version) {
        this.software = software;
        this.iklm = iklm;
        this.version = version;
    }
    
    public Order(String software, String iklm, String version, String path) {
        this.software = software;
        this.iklm = iklm;
        this.version = version;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    

    public Order(Order selectedItem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSoftware() {
        return software;
    }

    public String getIklm() {
        return iklm;
    }

    public String getVersion() {
        return version;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public void setIklm(String iklm) {
        this.iklm = iklm;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Order{" + "software=" + software + ", iklm=" + iklm + ", version=" + version + '}';
    }
    
    

}
