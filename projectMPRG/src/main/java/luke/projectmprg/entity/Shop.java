package luke.projectmprg.entity;

import java.util.ArrayList;
import java.util.List;

public class Shop extends Entity{
    
    private String name;
    private List<Product> products;
    private List<Shopping> shopping;

    public Shop() {
        this.products = new ArrayList<>();
        this.shopping = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Shopping> getShopping() {
        return shopping;
    }

    public void setShopping(List<Shopping> shopping) {
        this.shopping = shopping;
    }
    
}
