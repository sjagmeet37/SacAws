package helloworld;


public class Products {

    private String id;
    private String toolType;
    private String brand;
    private String name;
    private int count;

    public Products() {
    }

    public Products(String id, String toolType, String brand, String name, int count) {
        this.id = id;
        this.toolType = toolType;
        this.brand = brand;
        this.name = name;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
