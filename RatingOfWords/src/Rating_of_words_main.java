import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore;
import java.util.*;

public class Rating_of_words_main {
    public static void main(String[] args){
        if(IsCorrectCountOfArgs(args)) {
            File file = new File(args[0]);
            try {
                BufferedReader fin = new BufferedReader(new FileReader(file));
                int count = Integer.parseInt(args[1]);
                int checkCount = 0;
                HashMap<String, Integer> mapWithLines = new HashMap<String, Integer>();
                List<String> arrForCheck = new ArrayList<String>();
                String line = new String();

                while(true){
                    line = fin.readLine();
                    if (line.length() == 0)
                        break;

                    Collections.addAll(arrForCheck, line.split(" "));
                    for(int i = 0; i < arrForCheck.size(); i++) {
                        if (mapWithLines.containsKey(arrForCheck.get(i))) {
                            mapWithLines.put(arrForCheck.get(i), mapWithLines.get(arrForCheck.get(i)) + 1);
                        } else {
                            checkCount++;
                            if (checkCount <= count)
                                mapWithLines.put(arrForCheck.get(i), 1);
                        }

                        if (checkCount > count)
                            break;
                    }

                    if (checkCount > count)
                        break;
                    arrForCheck.clear();

                }

                fin.close();
                for(Map.Entry<String, Integer> myPair : mapWithLines.entrySet())
                    System.out.println(myPair.getKey() +' ' + myPair.getValue());

            } catch(IOException error) {
                System.out.println(error.toString());
            }
        }
    }

    private static boolean IsCorrectCountOfArgs(String[] args) {
        return (args.length == 2);
    }
}
