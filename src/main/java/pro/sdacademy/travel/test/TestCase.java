package pro.sdacademy.travel.test;

import pro.sdacademy.travel.entity.DbEntity;

public interface TestCase<T extends DbEntity<?>> {

    T testCreate();

    void testRead();

    void testUpdate();

    void testDelete();

    void cleanup();
}
