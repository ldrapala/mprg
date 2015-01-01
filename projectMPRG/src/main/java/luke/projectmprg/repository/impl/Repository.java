package luke.projectmprg.repository.impl;

import luke.projectmprg.repository.IRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import luke.projectmprg.entity.Entity;
import luke.projectmprg.entity.builder.IEntityBuilder;
import luke.projectmprg.unitofwork.IUnitOfWork;
import luke.projectmprg.unitofwork.IUnitOfWorkRepository;

public abstract class Repository<TEntity extends Entity>
        implements IRepository<TEntity>, IUnitOfWorkRepository {

    protected Connection connection;
    protected PreparedStatement insert;
    protected PreparedStatement selectById;
    protected PreparedStatement update;
    protected PreparedStatement selectAll;
    protected PreparedStatement delete;
    protected IEntityBuilder<TEntity> builder;
    protected IUnitOfWork unitOfWork;

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

    protected Repository(Connection connection, IEntityBuilder<TEntity> builder,
            IUnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
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
        this.unitOfWork.markAsDeleted(entity, this);
    }

    @Override
    public void save(TEntity entity) {
        this.unitOfWork.markAsNew(entity, this);
    }

    @Override
    public void update(TEntity entity) {
        this.unitOfWork.markAsDirty(entity, this);
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

    @Override
    public void persistAdd(Entity entity) {
        try {
            setUpInsertQuery((TEntity) entity);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persistUpdate(Entity entity) {
        try {
            setUpUpdateQuery((TEntity) entity);
            update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persistDelete(Entity entity) {
        try {
            delete.setInt(1, entity.getId());
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract void setUpUpdateQuery(TEntity entity) throws SQLException;

    protected abstract void setUpInsertQuery(TEntity entity) throws SQLException;

    protected abstract String getTableName();

    protected abstract String getInsertQuery();

    protected abstract String getUpdateQuery();
}
