package luke.projectmprg.unitofwork;

import luke.projectmprg.entity.Entity;

public interface IUnitOfWork {

    public void commit();

    public void rollback();

    public void markAsNew(Entity entity, IUnitOfWorkRepository repository);

    public void markAsDirty(Entity entity, IUnitOfWorkRepository repository);

    public void markAsDeleted(Entity entity, IUnitOfWorkRepository repository);

}
