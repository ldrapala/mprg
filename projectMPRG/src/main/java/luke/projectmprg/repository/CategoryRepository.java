package luke.projectmprg.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import luke.projectmprg.IRepository;
import luke.projectmprg.entity.Category;

public class CategoryRepository implements IRepository<Category> {
    
    private String insertSql
            = "INSERT INTO category(name)"
            + "VALUES(?)";
    private String selectByIDSql
            = "SELECT * FROM category WHERE id=?";
    private String updateSql
            = "UPDATE category SET (name)=(?) WHERE id=?";
    private String deleteSql
            = "DELETE FROM category WHERE id=?";
    private String selectAllSql
            = "SELECT * FROM category";
    
    private PreparedStatement selectByID;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    private PreparedStatement selectAll;

    public CategoryRepository(Connection connection) {
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
    public void save(Category entity) {
        try {
            this.insert.setString(1, entity.getName());
            this.insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category entity) {
        try {
            this.update.setString(1, entity.getName());
            this.update.setInt(2, entity.getId());
            this.update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category entity) {
        try {
            this.delete.setInt(1, entity.getId());
            this.delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category get(int id) {
        Category result = null;
        try {
            this.selectByID.setInt(1, id);
            ResultSet rs = this.selectByID.executeQuery();
            while (rs.next()) {
                result = new Category();
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Category> getAll() {
         List<Category> result = new ArrayList<>();
        try {
            ResultSet rs = this.selectAll.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                result.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
