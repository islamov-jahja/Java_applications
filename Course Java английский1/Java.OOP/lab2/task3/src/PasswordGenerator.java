import java.util.Random;

class PasswordGenerator {
  public static void main(String[] args) {
    if (IsCorrectCountOfArgs(args)) {
      try {
        String password = GetPassword(Integer.parseInt(args[0]), args[1]);
        System.out.println(password);
      } catch (NumberFormatException e){
        System.out.println("Wrong arguments");
      }
    } else
      System.out.println("Wrong arguments");
  }

  private static boolean IsCorrectCountOfArgs(String[] args) {
    return (args.length  == 2);
  }

  private static String GetPassword(int lengthOfPassword, String SymbolsForPassword) {
    if (lengthOfPassword <= 0)
      return "Wrong arguments";
    String password = new String();
    Random myRandom = new Random(System.nanoTime());
    for(int i = 0; i < lengthOfPassword; i++)
      password += SymbolsForPassword.charAt(myRandom.nextInt(SymbolsForPassword.length()));

    return password;
  }
}