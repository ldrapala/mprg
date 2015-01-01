package luke.projectmprg.entity;

public abstract class Entity {

    private int id;
    private EntityState entityState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntityState getEntityState() {
        return entityState;
    }

    public void setEntityState(EntityState entityState) {
        this.entityState = entityState;
    }
    
}
