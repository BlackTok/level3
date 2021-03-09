package lesson7;

import lesson7.annotations.AfterSuite;
import lesson7.annotations.BeforeSuite;

import java.awt.print.PrinterException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

public class Test {
    private static final List<Method> beforeSuiteMethods = new LinkedList<>();
    private static final List<Method> afterSuiteMethods = new LinkedList<>();
    private static final Map<Enum<Priority>, List<Method>> testMethods = new HashMap<>();

    private static final int integerTestVar = 5;
    private static final double doubleTestVar = 3.5;
    private static final boolean booleanTestVar = true;
    private static final char charTestVar = 'A';
    private static final String stringTestVar = "string";

    public static <T> void start(T aClass) throws RuntimeException, InvocationTargetException, IllegalAccessException {
        if (aClass == null)
            throw new RuntimeException("Class is null");

        for (Priority priority : Priority.values()) {
            testMethods.put(priority, new LinkedList<>());
        }

        Method[] methods = aClass.getClass().getMethods();

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getAnnotation(BeforeSuite.class) != null)
                beforeSuiteMethods.add(methods[i]);

            if (methods[i].getAnnotation(AfterSuite.class) != null)
                afterSuiteMethods.add(methods[i]);

            if (methods[i].getAnnotation(lesson7.annotations.Test.class) != null) {
                Priority priority = methods[i].getAnnotation(lesson7.annotations.Test.class).priority();
                List<Method> methodList = testMethods.get(priority);
                if (methodList == null)
                    methodList = new LinkedList<>();

                methodList.add(methods[i]);

                testMethods.put(priority, methodList);
            }
        }

        if (beforeSuiteMethods.size() > 1)
            throw new RuntimeException("Method with @BeforeSuit more than one");

        if (afterSuiteMethods.size() > 1)
            throw new RuntimeException("Method with @AfterSuite more than one");

        invokeMethods(beforeSuiteMethods);
        testingMethods(testMethods);
        invokeMethods(afterSuiteMethods);
    }

    private static void testingMethods(Map<Enum<Priority>, List<Method>> testMethods) throws InvocationTargetException, IllegalAccessException {
        for (Priority value : Priority.values()) {
            List<Method> methodList = testMethods.get(value);
            if (methodList == null)
                continue;

            invokeMethods(methodList);
        }
    }

    private static void invokeMethods(List<Method> methodList) throws InvocationTargetException, IllegalAccessException {
        if (methodList == null)
            throw new RuntimeException("Method List is NULL");

        for (int i = 0; i < methodList.size(); i++) {
            Method method = methodList.get(i);
            Class[] parameterTypes = method.getParameterTypes();
            Class returnType = method.getReturnType();
            Object[] parameters = new Object[parameterTypes.length];

            for (int j = 0; j < parameterTypes.length; j++) {
                if (parameterTypes[j] == int.class)
                    parameters[j] = integerTestVar;
                else if (parameterTypes[j] == double.class)
                    parameters[j] = doubleTestVar;
                else if (parameterTypes[j] == boolean.class)
                    parameters[j] = booleanTestVar;
                else if (parameterTypes[j] == char.class)
                    parameters[j] = charTestVar;
                else if (parameterTypes[j] == String.class)
                    parameters[j] = stringTestVar;
            }

            if (returnType == void.class) {
                method.invoke(method, parameters);
            } else {
                System.out.println(method.invoke(method, parameters));
            }
        }
    }
}
