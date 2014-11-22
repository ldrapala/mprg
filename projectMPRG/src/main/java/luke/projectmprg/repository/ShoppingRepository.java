package luke.projectmprg.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import luke.projectmprg.IRepository;
import luke.projectmprg.entity.Shopping;

public class ShoppingRepository implements IRepository<Shopping> {
    
     private String insertSql
            = "INSERT INTO shopping(name)"
            + "VALUES(?)";
    private String selectByIDSql
            = "SELECT * FROM shopping WHERE id=?";
    private String updateSql
            = "UPDATE shopping SET (name)=(?) WHERE id=?";
    private String deleteSql
            = "DELETE FROM shopping WHERE id=?";
    private String selectAllSql
            = "SELECT * FROM shopping";
    
    private PreparedStatement selectByID;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;
    private PreparedStatement selectAll;

    public ShoppingRepository(Connection connection) {
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
    public void save(Shopping entity) {
        try {
            this.insert.setDate(1, entity.getDate());
            this.insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Shopping entity) {
         try {
            this.update.setDate(1, entity.getDate());
            this.update.setInt(2, entity.getId());
            this.update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Shopping entity) {
        try {
            this.delete.setInt(1, entity.getId());
            this.delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Shopping get(int id) {
        Shopping result = null;
        try {
            this.selectByID.setInt(1, id);
            ResultSet rs = this.selectByID.executeQuery();
            while (rs.next()) {
                result = new Shopping();
                result.setId(rs.getInt("id"));
                result.setDate(rs.getDate("date"));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Shopping> getAll() {
        List<Shopping> result = new ArrayList<>();
        try {
            ResultSet rs = this.selectAll.executeQuery();
            while (rs.next()) {
                Shopping shopping = new Shopping();
                shopping.setId(rs.getInt("id"));
                shopping.setDate(rs.getDate("date"));
                result.add(shopping);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
