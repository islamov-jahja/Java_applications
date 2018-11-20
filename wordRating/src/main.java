import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class main {
  public static void main(String[] args) {
    if (IsCorrectCountOfArgs(args)) {
      File file = new File(args[0]);
      try {
        BufferedReader fin = new BufferedReader(new FileReader(file));
        int count = Integer.parseInt(args[1]);
        int checkCount = 0;
        HashMap<String, Integer> mapWithLines = new HashMap<String, Integer>();
        List<String> arrForCheck = new ArrayList<String>();
        int numberOfСhar = -1;
        char letter;
        while (true) {
          String line = new String();
          numberOfСhar = fin.read();

          if (numberOfСhar == -1)
            break;

          while (numberOfСhar != -1 && numberOfСhar != (int) ' ') {
            if (numberOfСhar == (int)'\n')
              break;
            letter = (char) numberOfСhar;
            line += letter;
            numberOfСhar = fin.read();
          }
          if(numberOfСhar != (int)'\n') {
            if (mapWithLines.containsKey(line))
                mapWithLines.put(line, mapWithLines.get(line) + 1);
            else {
              checkCount++;
              if (checkCount <= count)
                mapWithLines.put(line, 1);
            }
          }

          if (checkCount > count)
            break;
        }

        fin.close();

        List<Map.Entry<String, Integer>> list = SortHashMapKey(mapWithLines);

        for (Map.Entry<String, Integer> myPair : list)
          System.out.println(myPair.getKey() + ' ' + myPair.getValue());

      } catch (IOException error) {
        System.out.println(error.toString());
      }
    }
  }

  private static boolean IsCorrectCountOfArgs(String[] args) {
    return (args.length == 2);
  }

  public static List<Map.Entry<String, Integer>> SortHashMapKey(HashMap<String, Integer> unsortedMap) {
    List<Map.Entry<String, Integer>>  list = new LinkedList<Map.Entry<String, Integer>>(unsortedMap.entrySet());

    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
      @Override
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o2.getValue().compareTo(o1.getValue());
      }
    });

    return list;
  }
}
