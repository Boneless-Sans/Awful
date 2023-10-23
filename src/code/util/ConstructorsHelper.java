package src.code.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class ConstructorsHelper {
    public static void printConstructors(Object obj) {
        Class<?> clazz = obj.getClass();
        String className = clazz.getSimpleName();

        System.out.println(className + " Class Constructors");
        System.out.println("------------------------");

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            String constructorName = constructor.getName();
            StringBuilder parameters = new StringBuilder();

            Parameter[] constructorParameters = constructor.getParameters();
            for (int i = 0; i < constructorParameters.length; i++) {
                Parameter param = constructorParameters[i];
                parameters.append(param.getType().getSimpleName());
                if (i < constructorParameters.length - 1) {
                    parameters.append(", ");
                }
            }

            constructorName = constructorName.substring(constructorName.lastIndexOf('.') + 1);
            System.out.println("public " + constructorName + "(" + parameters + ")");
        }
    }
}
