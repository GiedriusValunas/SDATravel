package pro.sdacademy.travel.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<ID, T> {

    // C
    void create(T entity);

    // R
    Optional<T> find(ID id);

    List<T> findAll();

    // U
    void update(T entity);

    // D
    void delete(T entity);
}
