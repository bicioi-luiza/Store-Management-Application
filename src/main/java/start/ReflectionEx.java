package start;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionEx {

    public static void retrieveProperties(Object object) {

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                System.out.println(field.getName() + "=" + value);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public static void  getValues( ArrayList<Object> obj,Object obiect){
        int i=0;
        for (Field field : obiect.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                obj.add(field.get(obiect));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public static void getFields(ArrayList<String> fields,Object obiect){
        Class<?> clasa = obiect.getClass();
        for(Field field:clasa.getDeclaredFields()){
            fields.add(field.getName());
        }

    }
}



