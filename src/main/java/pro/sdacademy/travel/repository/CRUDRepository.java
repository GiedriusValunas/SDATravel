package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.entity.DbEntity;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<ID, T extends DbEntity<ID>> {

    // C/U
    void save(T entity);

    // R
    Optional<T> find(ID id);

    List<T> findAll();

    // D
    void delete(T entity);
}
