import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

///Users/gl.krutoimail.ru/Desktop/Random_hash_maps/HashMap.ser
///Users/gl.krutoimail.ru/Desktop/Random_hash_maps/OpenedChain.dat
public class Map_data {
    private Window_map parent;
    private File file;
    private HashMap<String,Object> nestedMapLevel8 ;

    private int HashMap_depth = 0;

    Map_data(Window_map parent, File filename){
        this.parent = parent;
        this.file = filename;
        load_hash_map();
        System.out.println("f");

    }
    Map_data(File filename){
        this.file = filename;
        load_hash_map();
        System.out.println("f");

    }

public int get_hash_map_depth(){
        return this.HashMap_depth;
}

    private void save_hash_map()  {
        try(FileOutputStream file_out = new FileOutputStream(this.file);
            ObjectOutputStream object_out = new ObjectOutputStream(file_out)){
                object_out.writeObject(nestedMapLevel8);
                System.out.println("Hashmap successfully saved");
        }
        catch (IOException exception){
            System.out.println(exception.toString());
            JOptionPane.showMessageDialog(this.parent,exception,"Save error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void load_hash_map(){
        System.out.println("Началась загрузка HashMap");
        try(FileInputStream file_in = new FileInputStream(this.file);
        ObjectInputStream object_in = new ObjectInputStream(file_in)){
            Object object = object_in.readObject();
            if (object instanceof HashMap){
                this.nestedMapLevel8 = (HashMap<String,Object>)object;
                System.out.println("Загрузка прошла успешно");
                JOptionPane.showMessageDialog(this.parent,"HashMap-успешно загружен");
                this.HashMap_depth = this.calculateDepth(this.nestedMapLevel8);
                return;
            }

        }
        catch (IOException exception){
            System.out.println(exception.toString());
            JOptionPane.showMessageDialog(this.parent,exception,"Load error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }




    }
    public HashMap<String,Object> get_current_hash_map(){
        return this.nestedMapLevel8;
    }

    public JList<String> Get_first_table_list(){
        DefaultListModel<String> model_list = new DefaultListModel<String>();
        for(String item: this.nestedMapLevel8.keySet()){
            model_list.addElement(item);
        }
        this.HashMap_depth = calculateDepth(this.nestedMapLevel8);
        return new JList<>(model_list);

    }///Users/gl.krutoimail.ru/Desktop/Random_hash_maps/HashMap.ser
    public JList<String> Get_n_table_list(ArrayList<String> chain){
        DefaultListModel<String> model_list = new DefaultListModel<String>();
        HashMap<String,Object> links_on_nested = this.nestedMapLevel8;
        ArrayList<String> last_item = new ArrayList<>();
        Boolean find_las_item = false;
        for (String itm : chain){
            if(links_on_nested.get(itm) instanceof HashMap){

                links_on_nested = (HashMap<String, Object>) links_on_nested.get(itm);
            }

            else{
                for(Object strings : links_on_nested.values()){
                    last_item.add(strings.toString());
                }
                find_las_item = true;
            }

        }
        if(!find_las_item) {
            for (String item : links_on_nested.keySet()) {
                model_list.addElement(item);
            }
        }
        else{
            for(String item : last_item){
                model_list.addElement("Value:\t"+item);
            }
        }

        return new JList<>(model_list);

    }

    private  int calculateDepth(HashMap<String, Object> map) {
        int maxDepth = 1;

        for (String key : map.keySet()) {
            if (map.get(key) instanceof HashMap) {
                int depth = 1 + calculateDepth((HashMap<String, Object>) map.get(key));
                maxDepth = Math.max(maxDepth, depth);
            }
        }

        return maxDepth;
    }

    public void Intel_search_in_list(JList[] lists, int current_list_index, String searched_str){
        if(searched_str.equals("")){
            lists[current_list_index].setModel(Get_n_table_list
                    (this.parent.get_Element_Event_listener_object().Get_opened_chain()).getModel());
            this.parent.get_Element_Event_listener_object().Set_Current_max_page(current_list_index-1);
        }
        DefaultListModel<String> model = (DefaultListModel<String>) lists[current_list_index].getModel();
        DefaultListModel<String> matchedItems = new DefaultListModel<String>();
        for (int i = 0; i < model.getSize(); i++) {
            String listItem = model.getElementAt(i);
            if (listItem.startsWith(searched_str)) {
                matchedItems.addElement(listItem);
            }
        }
        lists[current_list_index].setModel(matchedItems);

    }
    ///Users/gl.krutoimail.ru/Desktop/Random_hash_maps/HashMap.ser
///Users/gl.krutoimail.ru/Desktop/Random_hash_maps/OpenedChain.dat
    public void fill_by_loaded_chain(ArrayList<String> chain){

        ArrayList<String> tmp_chain = new ArrayList<>();
        JList[] lists = parent.get_lists();
        lists[0].setModel(Get_first_table_list().getModel());
        parent.get_Element_Event_listener_object().Set_Current_max_page(0);
        parent.get_Element_Event_listener_object().Set_opened_chain(new ArrayList<>());

        for(int i=1;i<chain.size();i++){

            tmp_chain.add(chain.get(i-1));
            lists[i].setModel(Get_n_table_list(tmp_chain).getModel());
            lists[i].setSelectedValue(chain.get(i-1),true);

            MouseListener[] mouseListeners = lists[i].getMouseListeners();
            List<Hash_map_panels.Element_Event_listener> listenerList = new ArrayList<>();

            for (MouseListener listener : mouseListeners) {
                if (listener instanceof Hash_map_panels.Element_Event_listener) {
                    listenerList.add((Hash_map_panels.Element_Event_listener) listener);
                }
            }

            Hash_map_panels.Element_Event_listener[] listenerArray = listenerList.toArray(new Hash_map_panels.Element_Event_listener[0]);
            listenerArray[0].Set_Amount_of_clicks(1);

            this.parent.get_Element_Event_listener_object().Set_opened_chain(tmp_chain);
            this.parent.get_Element_Event_listener_object().Set_Current_max_page(i+1);

        }
        for(int i=0;i<chain.size();i++){
            lists[i].setSelectedValue(chain.get(i),true);
        }
    }
}
