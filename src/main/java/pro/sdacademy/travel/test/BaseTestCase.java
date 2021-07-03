package pro.sdacademy.travel.test;

import pro.sdacademy.travel.entity.DbEntity;
import pro.sdacademy.travel.repository.CRUDRepository;

public abstract class BaseTestCase<E extends DbEntity<?>, T extends CRUDRepository<?, E>> implements TestCase<E> {

    protected T repository;

    public BaseTestCase(T repository) {
        this.repository = repository;
    }

    @Override
    public void testRead() {
        repository.findAll().forEach(System.out::println);
    }

    @Override
    public void cleanup() {
        repository.findAll().forEach(e -> repository.delete(e));
    }
}
