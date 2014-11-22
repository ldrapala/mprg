package luke.projectmprg.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.List;
import luke.projectmprg.IRepository;
import luke.projectmprg.entity.Category;
import luke.projectmprg.entity.Product;
import luke.projectmprg.entity.Shop;
import luke.projectmprg.entity.Shopping;
import luke.projectmprg.repository.CategoryRepository;
import luke.projectmprg.repository.ProductRepository;
import luke.projectmprg.repository.ShopRepository;
import luke.projectmprg.repository.ShoppingRepository;

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

            IRepository<Shopping> shoppingRepo = new ShoppingRepository(connection);
            IRepository<Shop> shopRepo = new ShopRepository(connection);
            IRepository<Category> categoryRepo = new CategoryRepository(connection);
            IRepository<Product> productRepo = new ProductRepository(connection);

            shoppingRepo.save(shopping);
            shopRepo.save(shop);
            categoryRepo.save(category);
            productRepo.save(product);

            List<Shopping> shoppingFromDb = shoppingRepo.getAll();
            List<Shop> shopsFromDb = shopRepo.getAll();
            List<Category> categoriesFromDb = categoryRepo.getAll();
            List<Product> productsFromDb = productRepo.getAll();

            printer.printAllElementsFromShopping(shoppingFromDb);
            System.out.println("---");
            printer.printAllElementsFromShop(shopsFromDb);
            System.out.println("---");
            printer.printAllElementsFromCategory(categoriesFromDb);
            System.out.println("---");
            printer.printAllElementsFromProduct(productsFromDb);
            System.out.println("------");

            Shopping shopping1 = shoppingRepo.get(1);
            shopping1.setDate(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24));
            shoppingRepo.update(shopping1);
            shoppingRepo.delete(shoppingFromDb.get(0));

            Shop shop1 = shopRepo.get(1);
            shop1.setName("biedronka");
            shopRepo.update(shop1);
            shopRepo.delete(shopRepo.get(0));

            Category category1 = categoryRepo.get(1);
            category1.setName("odzież");
            categoryRepo.update(category1);
            categoryRepo.delete(categoryRepo.get(0));

            Product product1 = productRepo.get(1);
            product1.setPrice(3.01);
            productRepo.update(product1);
            productRepo.delete(productsFromDb.get(0));

            printer.printAllElementsFromShopping(shoppingRepo.getAll());
            System.out.println("---");
            printer.printAllElementsFromShop(shopRepo.getAll());
            System.out.println("---");
            printer.printAllElementsFromCategory(categoryRepo.getAll());
            System.out.println("---");
            printer.printAllElementsFromProduct(productRepo.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("koniec");
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
