package luke.projectmprg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shopping {
    
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
