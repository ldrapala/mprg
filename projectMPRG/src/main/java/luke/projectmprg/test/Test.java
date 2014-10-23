package luke.projectmprg.test;

import java.util.List;
import luke.projectmprg.IRepositoryCatalog;
import luke.projectmprg.entity.Product;
import luke.projectmprg.repository.DummyRepositoryCatalog;

public class Test {

    public static void main(String[] args) {

        IRepositoryCatalog catalog = new DummyRepositoryCatalog();
        List<Product> admins = catalog.getProducts().withCategory("alkohol");

    }

}
