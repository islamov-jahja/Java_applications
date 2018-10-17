import java.lang.System;
import java.util.Random;
public class generate_of_password_main {
    public static void main(String[] args) {
        if (IsCorrectCountOfArgs(args)) {
            try {
                String password = GetPassword(Integer.parseInt(args[0]), args[1]);
                System.out.println(password);
            } catch (NumberFormatException e){
                System.out.println("первым аргументов вы передали не цифру");
            }
        } else
            System.out.println("неверное количество аргументов было передано приложению. Их должно быть 3.");
    }

    private static boolean IsCorrectCountOfArgs(String[] args) {
        return (args.length  == 2);
    }

    private static String GetPassword(int lengthOfPassword, String SymbolsForPassword) {
        String password = new String();
        Random myRandom = new Random(System.currentTimeMillis());
        for(int i = 0; i < lengthOfPassword; i++)
            password += SymbolsForPassword.charAt(myRandom.nextInt(SymbolsForPassword.length()));

        return password;
    }
}
