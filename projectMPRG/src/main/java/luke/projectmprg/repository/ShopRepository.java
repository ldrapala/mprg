package luke.projectmprg.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import luke.projectmprg.IRepository;
import luke.projectmprg.entity.Shop;

public class ShopRepository implements IRepository<Shop> {
    
    private String insertSql
            = "INSERT INTO shop(name)"
            + "VALUES(?)";
    private String selectByIDSql
            = "SELECT * FROM shop WHERE id=?";
    private String updateSql
            = "UPDATE shop SET (name)=(?) WHERE id=?";
    private String deleteSql
            = "DELETE FROM shop WHERE id=?";
    private String selectAllSql
            = "SELECT * FROM shop";
    
    private PreparedStatement selectByID;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    private PreparedStatement selectAll;

    public ShopRepository(Connection connection) {
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
    public void save(Shop entity) {
        try {
            this.insert.setString(1, entity.getName());
            this.insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Shop entity) {
        try {
            this.update.setString(1, entity.getName());
            this.update.setInt(2, entity.getId());
            this.update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Shop entity) {
        try {
            this.delete.setInt(1, entity.getId());
            this.delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Shop get(int id) {
        Shop result = null;
        try {
            this.selectByID.setInt(1, id);
            ResultSet rs = this.selectByID.executeQuery();
            while (rs.next()) {
                result = new Shop();
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
    public List<Shop> getAll() {
         List<Shop> result = new ArrayList<>();
        try {
            ResultSet rs = this.selectAll.executeQuery();
            while (rs.next()) {
                Shop shop = new Shop();
                shop.setId(rs.getInt("id"));
                shop.setName(rs.getString("name"));
                result.add(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
