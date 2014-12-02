package luke.projectmprg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import luke.projectmprg.entity.Entity;
import luke.projectmprg.entity.builder.IEntityBuilder;

public abstract class Repository <TEntity extends Entity>
        implements IRepository <TEntity> {

    protected Connection connection;
    protected PreparedStatement insert;
    protected PreparedStatement selectById;
    protected PreparedStatement update;
    protected PreparedStatement selectAll;
    protected PreparedStatement delete;
    protected IEntityBuilder <TEntity> builder;

    protected String selectByIdSql
            = "SELECT * FROM "
            + getTableName()
            + " WHERE id=?";
    protected String deleteSql
            = "DELETE FROM "
            + getTableName()
            + " WHERE id=?";
    protected String selectAllSql
            = "SELECT * FROM " + getTableName();

    protected Repository(Connection connection, IEntityBuilder<TEntity> builder) {
        this.builder = builder;
        this.connection = connection;
        try {
            this.insert = connection.prepareStatement(getInsertQuery());
            this.selectById = connection.prepareStatement(this.selectByIdSql);
            this.update = connection.prepareStatement(getUpdateQuery());
            this.delete = connection.prepareStatement(this.deleteSql);
            this.selectAll = connection.prepareStatement(this.selectAllSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TEntity entity) {
        try {
            this.delete.setInt(1, entity.getId());
            this.delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(TEntity entity) {
        try {
            setUpInsertQuery(entity);
            this.insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TEntity entity) {
        try {
            setUpUpdateQuery(entity);
            this.update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TEntity get(int id) {
        try {
            this.selectById.setInt(1, id);
            ResultSet rs = this.selectById.executeQuery();
            while (rs.next()) {
                return this.builder.build(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TEntity> getAll() {
        List<TEntity> result = new ArrayList<>();
        try {
            ResultSet rs = this.selectAll.executeQuery();
            while (rs.next()) {
                result.add(this.builder.build(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected abstract void setUpUpdateQuery(TEntity entity) throws SQLException;

    protected abstract void setUpInsertQuery(TEntity entity) throws SQLException;

    protected abstract String getTableName();

    protected abstract String getInsertQuery();

    protected abstract String getUpdateQuery();
}
