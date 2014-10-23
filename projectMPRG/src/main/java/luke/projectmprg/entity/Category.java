package luke.projectmprg.entity;

import java.util.ArrayList;
import java.util.List;
import luke.projectmprg.Entity;

public class Category extends Entity {
    
    private String category;
    private List<Product> products;

    public Category() {
       this.products = new ArrayList<>();
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
}
