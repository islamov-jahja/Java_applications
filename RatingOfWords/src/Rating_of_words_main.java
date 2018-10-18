import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rating_of_words_main {
    public static void main(String[] args){
        if(IsCorrectCountOfArgs(args)) {
            File file = new File(args[0]);
            try {
                BufferedReader fin = new BufferedReader(new FileReader(file));
                int count = Integer.parseInt(args[1]);
                List<String> arrOfLines = new ArrayList<String>();
                String line = new String();

                while(true){

                }

             //fin.close();
            } catch(IOException error) {
                System.out.println(error.toString());
            }
        }
    }

    private static boolean IsCorrectCountOfArgs(String[] args) {
        return (args.length == 2);
    }
}
