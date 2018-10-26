class CaesarCipher {
  public static void main(String[] args) {
    // put your code here
    if (IsCorrectCountOfArgs(args)) {
      if (IsStringOnlyWithLetters(args[2])) {
        String operationMode = args[0];
        if (operationMode.contains("-e")) {
          try {
            String encodingWord = GetEncodeWord(args[2], Integer.parseInt(args[1]));
            System.out.println(encodingWord);
          } catch (NumberFormatException e) {
            System.out.println("Wrong arguments");
          }
        } else if (operationMode.contains("-d")) {
          try {
            String decodingWord = GetDecodeWord(args[2], Integer.parseInt(args[1]));
            System.out.println(decodingWord);
          } catch (NumberFormatException e) {
            System.out.println("Wrong arguments");
          }
        } else {
          System.out.println("Wrong arguments");
        }

      } else
        System.out.println("Wrong arguments");
    } else
      System.out.println("Wrong arguments");
  }

  private static boolean IsCorrectCountOfArgs(String[] args) {
    return (args.length == 3);
  }

  private static String GetEncodeWord(String word, int key) {
    String newString = new String();
    for (int i = 0; i < word.length(); i++) {
      boolean isLowerCase = true;
      char letter = word.charAt(i);

      if (!(letter >= 'a' && letter <= 'z')) {
        isLowerCase = false;
        letter = Character.toLowerCase(letter);
      }

      if (Character.isLetter((char) ((int) letter + key)))
        newString += ((char) ((int) letter + key));
      else {
        int keyCheck = key;
        keyCheck -= (int) 'z' - (int) letter;
        keyCheck = keyCheck % 26;
        if (keyCheck != 0) {
          if (isLowerCase)
            newString += ((char) ((int) 'a' + keyCheck - 1));
          else
            newString += (Character.toUpperCase((char) ((int) 'a' + keyCheck - 1)));
        } else {
          if (isLowerCase)
            newString += 'z';
          else
            newString += 'Z';
        }
      }
    }

    return newString;
  }

  private static String GetDecodeWord(String word, int key) {
    String newString = new String();
    for (int i = 0; i < word.length(); i++) {
      boolean isLowerCase = true;
      char letter = word.charAt(i);

      if (!(letter >= 'a' && letter <= 'z')) {
        isLowerCase = false;
        letter = Character.toLowerCase(letter);
      }

      if (Character.isLetter((char) ((int) letter - key)))
        newString += ((char) ((int) letter - key));
      else {
        int keyCheck = key;
        keyCheck -= (int) letter - (int) 'a';
        keyCheck = keyCheck % 26;
        if (keyCheck != 0) {
          if (isLowerCase)
            newString += ((char) ((int) 'z' - keyCheck + 1));
          else
            newString += Character.toUpperCase(((char) ((int) 'z' - keyCheck + 1)));
        } else {
          if (isLowerCase)
            newString += 'a';
          else
            newString += 'A';
        }
      }
    }
    return newString;
  }

  private static boolean IsStringOnlyWithLetters(String line) {
    for (int i = 0; i < line.length(); i++)
      if (!(Character.isLetter(line.charAt(i))))
        return false;
    return true;
  }
}