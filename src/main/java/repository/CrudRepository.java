package repository;

import dto.Customer;

import java.util.List;

public interface CrudRepository <T,ID> extends SuperDao{
    boolean save(T entity);
    boolean update(T entity, ID id);
    boolean delete(ID id);
    Customer search(ID id);
    List<T> findAll();
}
