package luke.projectmprg.unitofwork;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import luke.projectmprg.entity.Entity;
import luke.projectmprg.entity.EntityState;

public class UnitOfWork implements IUnitOfWork {

    private Connection connection;
    private Map<Entity, IUnitOfWorkRepository> entities;

    public UnitOfWork(Connection connection) {
        this.entities = new LinkedHashMap<>();
        this.connection = connection;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit() {
        for (Entity entity : entities.keySet()) {
            switch (entity.getEntityState()) {
                case CHANGED:
                    entities.get(entity).persistUpdate(entity);
                    break;
                case DELETED:
                    entities.get(entity).persistDelete(entity);
                    break;
                case NEW:
                    entities.get(entity).persistAdd(entity);
                    break;
                case UNCHANGED:
                    break;
                default:
                    break;
            }
        }
        try {
            connection.commit();
            entities.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void rollback() {
        entities.clear();
    }

    @Override
    public void markAsNew(Entity entity, IUnitOfWorkRepository repository) {
        entity.setEntityState(EntityState.NEW);
        entities.put(entity, repository);

    }

    @Override
    public void markAsDirty(Entity entity, IUnitOfWorkRepository repository) {
        entity.setEntityState(EntityState.CHANGED);
        entities.put(entity, repository);

    }

    @Override
    public void markAsDeleted(Entity entity, IUnitOfWorkRepository repository) {
        entity.setEntityState(EntityState.DELETED);
        entities.put(entity, repository);

    }

}
