package luke.projectmprg.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import luke.projectmprg.IRepositoryCatalog;
import luke.projectmprg.RepositoryCatalog;
import luke.projectmprg.entity.Category;
import luke.projectmprg.entity.Product;
import luke.projectmprg.entity.Shop;
import luke.projectmprg.entity.Shopping;
import luke.projectmprg.unitofwork.IUnitOfWork;
import luke.projectmprg.unitofwork.UnitOfWork;

public class Test {
    
    public static void main(String[] args) {
        
        Test printer = new Test();        
        
        String url = "jdbc:postgresql://localhost:5432/manageyourmoney";
        
        Shopping shopping = new Shopping();
        shopping.setDate(new Date(System.currentTimeMillis()));
        
        Shop shop = new Shop();
        shop.setName("osiedlowy");
        
        Category category = new Category();
        category.setName("alkohol");
        
        Product product = new Product();
        product.setName("Warka");
        product.setPrice(2.99);
        product.setWeight(500);
        
        try {
            Connection connection = DriverManager.getConnection(url, "postgres", "luke");
            IUnitOfWork uow = new UnitOfWork(connection);
            IRepositoryCatalog catalog = new RepositoryCatalog(connection, uow);
            
            catalog.getShopping().save(shopping);
            catalog.getShops().save(shop);
            catalog.getCategories().save(category);
            catalog.getProducts().save(product);
            
            uow.commit();
            
            System.out.println("After first save and commit");
            printer.printAll(catalog);
            
            Shopping shopping1 = catalog.getShopping().get(1);
            shopping1.setDate(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
            catalog.getShopping().update(shopping1);
            catalog.getShopping().delete(catalog.getShopping().get(0));
            
            Shop shop1 = catalog.getShops().get(1);
            shop1.setName("biedronka");
            catalog.getShops().update(shop1);
            catalog.getShops().delete(catalog.getShops().get(0));
            
            Category category1 = catalog.getCategories().get(1);
            category1.setName("odzież");
            catalog.getCategories().update(category1);
            catalog.getCategories().delete(catalog.getCategories().get(0));
            
            Product product1 = catalog.getProducts().get(1);
            product1.setPrice(3.01);
            catalog.getProducts().update(product1);
            catalog.getProducts().delete(catalog.getProducts().get(0));
            
            System.out.println("After update, delete without commit");
            printer.printAll(catalog);
            System.out.println("After commit");
            uow.commit();
            printer.printAll(catalog);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("koniec");
    }
    
    public void printAll(IRepositoryCatalog catalog) {
        printAllElementsFromShopping(catalog.getShopping().getAll());
        printAllElementsFromShop(catalog.getShops().getAll());
        printAllElementsFromCategory(catalog.getCategories().getAll());
        printAllElementsFromProduct(catalog.getProducts().getAll());
    }
    
    public void printAllElementsFromShopping(List<Shopping> list) {
        for (Shopping shopping : list) {
            shopping.getDate();
        }
    }
    
    public void printAllElementsFromShop(List<Shop> list) {
        for (Shop shop : list) {
            shop.getName();
        }
    }
    
    public void printAllElementsFromCategory(List<Category> list) {
        for (Category category : list) {
            category.getName();
        }
    }
    
    public void printAllElementsFromProduct(List<Product> list) {
        for (Product product : list) {
            System.out.println(product.getId() + " " + product.getName()
                    + " " + product.getPrice() + " " + product.getWeight());
        }
    }
    
}
