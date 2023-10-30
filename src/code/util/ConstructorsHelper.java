package src.code.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class ConstructorsHelper {
    // Method to print constructors of an object
    public static void printConstructors(Object obj) {
        // Get the class of the provided object
        Class<?> clazz = obj.getClass();
        // Get the simple name of the class
        String className = clazz.getSimpleName();

        // Print the class name and a separator line
        System.out.println(className + " Class Constructors");
        System.out.println("------------------------");

        // Get an array of constructors using reflection
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        // Iterate through each constructor
        for (Constructor<?> constructor : constructors) {
            // Get the constructor's name and trim it to exclude the package name
            String constructorName = constructor.getName();
            constructorName = constructorName.substring(constructorName.lastIndexOf('.') + 1);

            // Initialize a StringBuilder to build the parameter list
            StringBuilder parameters = new StringBuilder();

            // Get the constructor parameters using reflection
            Parameter[] constructorParameters = constructor.getParameters();

            // Iterate through the parameters
            for (int i = 0; i < constructorParameters.length; i++) {
                Parameter param = constructorParameters[i];
                // Append the simple name of the parameter type
                parameters.append(param.getType().getSimpleName());
                // Add a comma and space if there are multiple parameters
                if (i < constructorParameters.length - 1) {
                    parameters.append(", ");
                }
            }

            // Print the constructor signature
            System.out.println("public " + constructorName + "(" + parameters + ")");
        }
    }
}
