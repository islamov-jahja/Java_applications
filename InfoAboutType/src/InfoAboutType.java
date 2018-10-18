public class InfoAboutType {
    public static void main(String[] args)
    {
        PrintFormat();
        PrintLong();
        PrintInteger();
        PrintShort();
        PrintByte();
        PrintDouble();
        PrintFloat();
        PrintChar();
    }

    private static void PrintFormat(){
        System.out.printf("%s", "Type");
        System.out.printf("%25s", "Min");
        System.out.printf("%25s", "Max");
        System.out.printf("%25s%n", "Size");
    }
    private static void PrintInteger(){
        System.out.printf("%s", Integer.TYPE);
        System.out.printf("%26d", Integer.MIN_VALUE);
        System.out.printf("%25d", Integer.MAX_VALUE);
        System.out.printf("%24d%n", Integer.SIZE);
    }

    private static void PrintLong()
    {
        System.out.printf("%s", Long.TYPE);
        System.out.printf("%25d", Long.MIN_VALUE);
        System.out.printf("%25d", Long.MAX_VALUE);
        System.out.printf("%24d%n", Long.SIZE);
    }

    private static void PrintShort()
    {
        System.out.printf("%s", Short.TYPE);
        System.out.printf("%24d", Short.MIN_VALUE);
        System.out.printf("%25d", Short.MAX_VALUE);
        System.out.printf("%24d%n", Short.SIZE);
    }

    private static void PrintByte(){
        System.out.printf("%s", Byte.TYPE);
        System.out.printf("%25d", Byte.MIN_VALUE);
        System.out.printf("%25d", Byte.MAX_VALUE);
        System.out.printf("%24d%n", Byte.SIZE);
    }

    private static void PrintDouble() {
        System.out.printf("%s", Double.TYPE);
        System.out.printf("%25.4f", Double.MIN_VALUE);
        System.out.printf("%25.4f", Double.MAX_VALUE);
        System.out.printf("%24d%n", Double.SIZE);
    }

    private static void PrintFloat() {
        System.out.printf("%s", Float.TYPE);
        System.out.printf("%25.4f", Float.MIN_VALUE);
        System.out.printf("%25.4f", Float.MAX_VALUE);
        System.out.printf("%24d%n", Float.SIZE);
    }

    private static void PrintChar(){
        System.out.printf("%s", Character.TYPE);
        System.out.printf("%25c", Character.MIN_VALUE);
        System.out.printf("%25c", Character.MAX_VALUE);
        System.out.printf("%24d%n", Character.SIZE);
    }
}
