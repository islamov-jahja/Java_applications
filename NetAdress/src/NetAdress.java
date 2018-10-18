public class NetAdress {
    public static void main(String[] args) {
        if (IsCorrectCountOfArgs(args)) {
            try {
                if (args[0].split("\\.").length == 4 && args[1].split("\\.").length == 4) {
                    int[] ip_address = GetArrOfIntegerFromString(args[0].split("\\."));
                    int[] mask = GetArrOfIntegerFromString(args[1].split("\\."));
                    System.out.println(GetNetAdress(ip_address, mask));
                }
            } catch (NumberFormatException e) {
                System.out.println("некорректные данные");
            }
        } else
            System.out.println("неверное количество аргументов было передано приложению. Их должно быть 2.");
    }

    private static int[] GetArrOfIntegerFromString(String[] arrOfString) {
        int[] intArray = new int[4];

        for (int i = 0; i < arrOfString.length; i++)
            intArray[i] += Integer.parseInt(arrOfString[i]);

        return intArray;
    }

    private static String GetNetAdress(int[] ip_adress, int[] mask) {
        int[] address = new int[4];
        String addressS = new String();

        for (int i = 0; i < 4; i++) {
            if (i != 3) {
                address[i] = ip_adress[i] & mask[i];
                addressS += String.valueOf(address[i]) + '.';
            } else {
                address[i] = ip_adress[i] & mask[i];
                addressS += String.valueOf(address[i]);
            }
        }

        return addressS;
    }

    private static boolean IsCorrectCountOfArgs(String[] args) {
        return (args.length == 2);
    }
}
