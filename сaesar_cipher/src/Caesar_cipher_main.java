import java.lang.System;

public class Caesar_cipher_main {
    public static void main(String[] args) {
        if(IsStringOnlyWithLetters(args[2])) {
            if (IsCorrectCountOfArgs(args)) {
                String operationMode = args[0];
                if (operationMode.contains("-e")) {
                    try {
                        String encodingWord = GetEncodeWord(args[2], Integer.parseInt(args[1]));
                        System.out.println(encodingWord);
                    } catch (NumberFormatException e) {
                        System.out.println("Вторым аргументом вы передали не цифру");
                    }
                } else if (operationMode.contains("-d")) {
                    try {
                        String decodingWord = GetDecodeWord(args[2], Integer.parseInt(args[1]));
                        System.out.println(decodingWord);
                    } catch (NumberFormatException e) {
                        System.out.println("Вторым аргументом вы передали не цифру");
                    }
                } else {
                    System.out.println("такой команды не сущесвует");
                }

            } else
                System.out.println("неверное количество аргументов было передано приложению. Их должно быть 3.");
        } else
            System.out.println("Строка содержит некорректные символы");
    }

    private static boolean IsCorrectCountOfArgs(String[] args) {
        return (args.length  == 3);
    }

    private static String GetEncodeWord(String word, int key) {
        String newString = new String();
        for(int i = 0; i < word.length(); i++) {
            boolean isLowerCase = true;
            char letter = word.charAt(i);
            if(Character.isUpperCase(letter)) {
                isLowerCase = false;
                letter = Character.toLowerCase(letter);
            }

            key = key % 26;
            if((int)letter + key <= (int)'z'){
              if (isLowerCase)
                  newString += (char) ((int)letter + key);
              else
                  newString += Character.toUpperCase((char) ((int)letter + key));
            } else {
                if (isLowerCase)
                    newString += (char)((int)'a' + ((int)key - 1 - ((int)'z' - (int) letter)));
                else
                    newString += Character.toUpperCase ((char)((int)'a' + ((int)key - 1 - ((int)'z' - (int) letter))));
            }
        }
        return newString;
    }

    private static String GetDecodeWord(String word, int key) {
        String newString = new String();
        for(int i = 0; i < word.length(); i++) {
            boolean isLowerCase = true;
            char letter = word.charAt(i);
            if(Character.isUpperCase(letter)) {
                isLowerCase = false;
                letter = Character.toLowerCase(letter);
            }

            key = key % 26;
            if((int)letter - key >= (int)'a'){
                if (isLowerCase)
                    newString += (char) ((int)letter - key);
                else
                    newString += Character.toUpperCase((char) ((int)letter - key));
            } else {
                if (isLowerCase)
                    newString += (char)((int)'z' - ((int) key - 1 - (((int) letter - (int)'a'))));
                else
                    newString += Character.toUpperCase ((char)((int)'z' - ((int) key - 1 - (((int) letter - (int)'a')))));
            }
        }
        return newString;
    }

    private static boolean IsStringOnlyWithLetters(String line)
    {
        for(int i = 0; i < line.length(); i++)
            if (!(Character.isLetter(line.charAt(i))))
                return false;
        return true;
    }
}
