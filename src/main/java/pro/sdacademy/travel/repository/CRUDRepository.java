package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.DbEntity;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<ID, T extends DbEntity<ID>> {

    // C
    void create(T entity);

    // R
    Optional<T> find(ID id);

    List<T> findAll();

    // U
    void update(T entity);

    // D
    void delete(T entity);

    default void save(T entity) {
        if (entity.getId() == null) {
            create(entity);
        } else {
            update(entity);
        }
    }
}
