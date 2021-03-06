package luke.projectmprg.entity;

import java.util.ArrayList;
import java.util.List;

public class Category extends Entity {
    
    private String name;
    private List<Product> products;

    public Category() {
       this.products = new ArrayList<>();
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
    
}
