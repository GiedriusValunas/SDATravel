package pro.sdacademy.travel.test;

import pro.sdacademy.travel.entity.Entity;

public interface TestCase<T extends Entity<?>> {

    T testCreate();

    void testRead();

    void testUpdate();

    void testDelete();

    void cleanup();
}
