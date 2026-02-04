package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {

    public static void inspectClass(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("Reflection Inspection: " + clazz.getName());

        System.out.println("Fields:");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(" - " + field.getName() + " : " + field.getType().getSimpleName());
        }

        System.out.println("Methods:");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(" - " + method.getName() + "()");
        }
    }
}
