package luke.projectmprg.unitofwork;

import luke.projectmprg.entity.Entity;

public interface IUnitOfWorkRepository {

    public void persistAdd(Entity entity);

    public void persistUpdate(Entity entity);

    public void persistDelete(Entity entity);

}
