package Hash_map_generator;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class Hash_map_generator {
    public static void main(String[] args){
        int hashMapDepth = Input_depth_of_HashMap();
        HashMap<String,Object> nestedMapLevel8 = new HashMap<>();
        HashMap_fill(hashMapDepth,nestedMapLevel8);
        save_hash_map(nestedMapLevel8);

    }

    public static int Input_depth_of_HashMap(){
        int hashMapDepth = 0;
        boolean correct_input = false;
        do{
            try{
                String input = JOptionPane.showInputDialog("Введите число 1<=x<9:");
                hashMapDepth = Integer.parseInt(input);
                if(hashMapDepth < 1 ){
                    throw new Exception("Число слишком маленькое");
                }
                else if(hashMapDepth > 8  ){
                    throw new Exception("Число слишком большое");
                }
                correct_input = true;
            }
            catch (Exception e ){
                JOptionPane.showMessageDialog(null,e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }while (!correct_input);
        return hashMapDepth;
    }


    public static void HashMap_fill(int hashMapDepth, HashMap<String, Object> map){
        if (hashMapDepth<=0){
            return;
        }
        if (hashMapDepth == 1){
            HashMap<String, String> nested_map = new HashMap<>();
            for (int i = 0; i<new Random().nextInt(3,8); i++){
                nested_map.put(Random_String_generator.Str_generate(),
                        Random_String_generator.Str_generate());
            }
            map.put(Random_String_generator.Str_generate(), nested_map);

        }
        else {
            for (int i = 0; i < new Random().nextInt(5, 10); i++) {
                HashMap<String, Object> nested_map = new HashMap<>();
                map.put(Random_String_generator.Str_generate(), nested_map);
                HashMap_fill(hashMapDepth - 1, nested_map);
            }
        }
    }

    private static void save_hash_map(HashMap<String, Object> nestedMapLevel8)  {
       String directory = "/Users/gl.krutoimail.ru/Desktop/Random_hash_maps/";
       String File_name = "HashMap.ser";


        try(FileOutputStream file_out = new FileOutputStream(directory+File_name);
            ObjectOutputStream object_out = new ObjectOutputStream(file_out)){
            object_out.writeObject(nestedMapLevel8);
            System.out.println("Hashmap successfully saved");
        }
        catch (IOException exception){
            System.out.println(exception.toString());
            //JOptionPane.showMessageDialog(this.parent,exception,"Save error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
