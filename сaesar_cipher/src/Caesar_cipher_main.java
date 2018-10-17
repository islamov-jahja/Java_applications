import java.lang.System;

public class Caesar_cipher_main {
    public static void main(String[] args) {
        if (IsCorrectCountOfArgs(args)) {
            String operationMode = args[0];
            if (operationMode.contains("-e")) {
                try {
                    String encodingWord = EncodeAWord(args[2], Integer.parseInt(args[1]));
                    System.out.println(encodingWord);
                } catch (NumberFormatException e) {
                    System.out.println("Вторым аргументом вы передали не цифру");
                }
            } else if (operationMode.contains("-d")) {
                try {
                    String decodingWord = DecodeAWord(args[2], Integer.parseInt(args[1]));
                    System.out.println(decodingWord);
                } catch (NumberFormatException e) {
                    System.out.println("Вторым аргументом вы передали не цифру");
                }
            } else {
                System.out.println("такой команды не сущесвует");
            }

        } else
            System.out.println("неверное количество аргументов было передано приложению. Их должно быть 3.");
    }

    private static boolean IsCorrectCountOfArgs(String[] args) {
        return (args.length  == 3);
    }

    private static String EncodeAWord(String word, int key) {
        String newString = new String();
        for(int i = 0; i < word.length(); i++)
            newString += ((char)((int)word.charAt(i) + key));

        return newString;
    }

    private static String DecodeAWord(String word, int key) {
        String newString  = new String();
        for(int i = 0; i < word.length(); i++)
            newString += ((char)((int)word.charAt(i) - key));

        return newString;
    }
}
