import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore;
import java.util.*;

public class Rating_of_words_main {
  public static void main(String[] args) {
    if (IsCorrectCountOfArgs(args)) {
      File file = new File(args[0]);
      try {
        BufferedReader fin = new BufferedReader(new FileReader(file));
        int count = Integer.parseInt(args[1]);
        int checkCount = 0;
        HashMap<String, Integer> mapWithLines = new HashMap<String, Integer>();
        List<String> arrForCheck = new ArrayList<String>();
        int numberOfchar = -1;
        String line = new String();
        char letter;
        while (true) {
          numberOfchar = fin.read();

          if (numberOfchar == -1)
            break;

          while (numberOfchar != -1 && numberOfchar != (int) ' ') {
            letter = (char) numberOfchar;
            line += letter;
            numberOfchar = fin.read();
          }

          if (mapWithLines.containsKey(line))
            mapWithLines.put(line, mapWithLines.get(line) + 1);
          else {
            checkCount++;
            if (checkCount <= count)
              mapWithLines.put(line, 1);
          }


          if (checkCount > count)
            break;
        }

        fin.close();
        for (Map.Entry<String, Integer> myPair : mapWithLines.entrySet())
          System.out.println(myPair.getKey() + ' ' + myPair.getValue());

      } catch (IOException error) {
        System.out.println(error.toString());
      }
    }
  }

  private static boolean IsCorrectCountOfArgs(String[] args) {
    return (args.length == 2);
  }

  public static void SortHashMapKey(Map unsortedMap) {
    List<Integer> list = new ArrayList(unsortedMap.values());
    Collections.sort(list, new Comparator<Integer>() {

      @Override
      public int compare(Integer p1, Integer p2) {
        return p1 - p2;
      }
    });
  }

}
