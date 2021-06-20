package pro.sdacademy.travel.test;

public class TestRunner {

    public static void runTests(TestCase testCase) {
        System.out.println("=== Running Test Case For " + testCase.getClass().getName() + " =====");
        testCase.cleanup();
        System.out.println("--- First Read ---");
        testCase.testRead();

        System.out.println("--- Create 3 records ---");
        testCase.testCreate();
        testCase.testCreate();
        testCase.testCreate();
        testCase.testRead();

        System.out.println("--- Update 2nd record ---");
        testCase.testUpdate();
        testCase.testRead();

        System.out.println("--- Delete 3rd record ---");
        testCase.testDelete();
        testCase.testRead();
        System.out.println("============================");
    }
}
