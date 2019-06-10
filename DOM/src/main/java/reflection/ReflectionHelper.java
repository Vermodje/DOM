package reflection;

import org.w3c.dom.NodeList;

import java.lang.reflect.Field;

public class ReflectionHelper {
    private NodeList nodeList;

    public static Object createInstance(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldValue(Object object, String fieldName, String value) throws IllegalAccessException, NoSuchFieldException {
        if (object != null) {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Types types = Types.getType(field.getType());
            switch (types) {
                case INT:
                    field.set(object, Integer.decode(value));
                    break;
                case STRING:
                    field.set(object, value);
                    break;
                case BYTE:
                    field.set(object, Byte.valueOf(value));
                    break;
                case BOOLEAN:
                    field.set(object, Boolean.valueOf(value));
                    break;
                case SHORT:
                    field.set(object, Short.valueOf(value));
                    break;
                case CHAR:
                    field.set(object, value.charAt(0));
                    break;
                case FLOAT:
                    field.set(object, Float.valueOf(value));
                    break;
                case LONG:
                    field.set(object, Long.valueOf(value));
                    break;
                case DOUBLE:
                    field.set(object, Double.valueOf(value));

            }
            field.setAccessible(false);
        }
    }

}


