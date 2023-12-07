package Hash_map_generator;

import java.util.Random;

public class Random_String_generator {
    public static String Str_generate(){
        Random random = new Random();
        String char_letters ="abcdefghijklmnopqrstuvwxyz";

        String str_to_return = "";
        for(int i = 0; i<random.nextInt(5,10);i++) {
            str_to_return = str_to_return +
                    char_letters.charAt(random.nextInt(char_letters.length()));

        }

        return str_to_return;
    }
}
