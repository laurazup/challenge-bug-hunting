package util;

public class EnumHandler {

    public static <T extends Enum<T>> void printAllValues(Class<T> enumClass) {
        int index = 1;
        for (T constant : enumClass.getEnumConstants()) {
            System.out.println(index++ + ". " + constant.toString());
        }
    }
}
