package pro.sdacademy.travel.test;

import pro.sdacademy.travel.entity.DbEntity;

public class TestRunner {

    public static void runTests(TestCase testCase) {
        System.out.println("=== Running Test Case For " + testCase.getClass().getName() + " =====");
        testCase.cleanup();
        System.out.println("--- First Read ---");
        testCase.testRead();

        System.out.println("--- Create 3 records ---");
        testCase.testCreate();
        DbEntity<?> obj2 = testCase.testCreate();
        DbEntity<?> obj3 = testCase.testCreate();
        testCase.testRead();

        System.out.println("--- Update 2nd record ---");
        testCase.testUpdate(obj2);
        testCase.testRead();

        System.out.println("--- Delete 3rd record ---");
        testCase.testDelete(obj3);
        testCase.testRead();
        System.out.println("============================");
    }
}
