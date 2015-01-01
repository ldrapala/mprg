package luke.projectmprg;

import java.sql.Connection;
import luke.projectmprg.entity.Category;
import luke.projectmprg.entity.Product;
import luke.projectmprg.entity.Shop;
import luke.projectmprg.entity.Shopping;
import luke.projectmprg.entity.builder.CategoryBuilder;
import luke.projectmprg.entity.builder.ProductBuilder;
import luke.projectmprg.entity.builder.ShopBuilder;
import luke.projectmprg.entity.builder.ShoppingBuilder;
import luke.projectmprg.repository.impl.CategoryRepository;
import luke.projectmprg.repository.IRepository;
import luke.projectmprg.repository.impl.ProductRepository;
import luke.projectmprg.repository.impl.ShopRepository;
import luke.projectmprg.repository.impl.ShoppingRepository;
import luke.projectmprg.unitofwork.IUnitOfWork;

public class RepositoryCatalog implements IRepositoryCatalog {

    private Connection connection;
    private IUnitOfWork uow;

    public RepositoryCatalog(Connection connection, IUnitOfWork uow) {
        this.connection = connection;
        this.uow = uow;
    }

    @Override
    public IRepository<Product> getProducts() {
        return new ProductRepository(connection, new ProductBuilder(), uow);
    }

    @Override
    public IRepository<Category> getCategories() {
        return new CategoryRepository(connection, new CategoryBuilder(), uow);
    }

    @Override
    public IRepository<Shopping> getShopping() {
        return new ShoppingRepository(connection, new ShoppingBuilder(), uow);
    }

    @Override
    public IRepository<Shop> getShops() {
        return new ShopRepository(connection, new ShopBuilder(), uow);
    }

}
