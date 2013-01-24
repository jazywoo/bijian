package bijian.model.dao;

public interface IBaseDao<T,ID> {
    public void insert(T entity);
    public void update(T entity);
    public void delete(ID id);
    public T get(ID id);
}
