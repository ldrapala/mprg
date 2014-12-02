package luke.projectmprg;

public interface IRepositoryCatalog {
    
    public IProductRepository getProducts();
    public ICategoryRepository getCategories();
    public IShoppingRepository getShopping();
    
}
