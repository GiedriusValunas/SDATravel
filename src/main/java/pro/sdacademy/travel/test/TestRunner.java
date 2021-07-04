package pro.sdacademy.travel.test;

import pro.sdacademy.travel.entity.DbEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class TestRunner {

    public static void runTests(TestCase<?>... testCase) {
        for (TestCase<?> aCase : testCase) {
            runTests(aCase);
        }
    }

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

        runExtraTests(testCase);
        System.out.println("============================");
    }

    private static void runExtraTests(TestCase testCase) {
        Class<? extends TestCase> testCaseClass = testCase.getClass();
        Arrays.stream(testCaseClass.getDeclaredMethods())
                .filter(m -> m.getName().startsWith("extraTest"))
                .filter(m -> m.getParameters().length == 0)
                .forEach(m -> {
                    try {
                        System.out.println("--- Running Extra Test :: " + m.getName() + " ---");
                        m.invoke(testCase);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
