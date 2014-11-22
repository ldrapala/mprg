package luke.projectmprg.entity;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import luke.projectmprg.Entity;

public class Shopping extends Entity{
    
    private List<Product> products;
    private Date date;
    private Shop shop;

    public Shopping() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    
}
