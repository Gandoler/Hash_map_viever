import Hash_map_generator.Hash_map_correct_viewer;
///Users/gl.krutoimail.ru/Desktop/Random_hash_maps/HashMap.ser
import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file_name = new File("/Users/gl.krutoimail.ru/Desktop/Random_hash_maps/HashMap.ser");
        Map_data data = new Map_data(file_name);
        Hash_map_correct_viewer view_map = new Hash_map_correct_viewer(data.get_current_hash_map());
        Window_map window_map = new Window_map();
    }

}