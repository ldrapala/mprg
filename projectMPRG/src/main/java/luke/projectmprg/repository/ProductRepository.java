package luke.projectmprg.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import luke.projectmprg.IRepository;
import luke.projectmprg.entity.Product;

public class ProductRepository implements IRepository<Product> {

    private String insertSql
            = "INSERT INTO product(name, price, weight)"
            + "VALUES(?,?,?)";
    private String selectByIDSql
            = "SELECT * FROM product WHERE id=?";
    private String updateSql
            = "UPDATE product SET (name, price, weight)=(?,?,?) WHERE id=?";
    private String deleteSql
            = "DELETE FROM product WHERE id=?";
    private String selectAllSql
            = "SELECT * FROM product";

    private PreparedStatement selectByID;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    private PreparedStatement selectAll;

    public ProductRepository(Connection connection) {
        try {
            this.insert = connection.prepareStatement(this.insertSql);
            this.selectByID = connection.prepareStatement(this.selectByIDSql);
            this.update = connection.prepareStatement(this.updateSql);
            this.delete = connection.prepareStatement(this.deleteSql);
            this.selectAll = connection.prepareStatement(this.selectAllSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product entity) {
        try {
            this.insert.setString(1, entity.getName());
            this.insert.setDouble(2, entity.getPrice());
//            insert.setInt(3, entity.getCategory().getId());
            this.insert.setDouble(3, entity.getWeight());
            this.insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product entity) {
        try {
            this.update.setString(1, entity.getName());
            this.update.setDouble(2, entity.getPrice());
//            update.setInt(3, entity.getCategory().getId());
            this.update.setDouble(3, entity.getWeight());
            this.update.setInt(4, entity.getId());
            this.update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product entity) {
        try {
            this.delete.setInt(1, entity.getId());
            this.delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product get(int id) {
        Product result = null;
        try {
            this.selectByID.setInt(1, id);
            ResultSet rs = this.selectByID.executeQuery();
            while (rs.next()) {
                result = new Product();
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));
                result.setPrice(rs.getDouble("price"));
//                result.setCategory(rs.getInt("category_id"));
                result.setWeight(rs.getDouble("weight"));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Product> getAll() {
        List<Product> result = new ArrayList<>();
        try {
            ResultSet rs = this.selectAll.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
//                product.setCategory(rs.getInt("category_id"));
                product.setWeight(rs.getDouble("weight"));
                result.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
