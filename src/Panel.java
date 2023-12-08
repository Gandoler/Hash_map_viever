import javax.swing.*;
import java.awt.*;


public class Panel extends JPanel {
    private Panel_with_text_field textField;
    private Hash_map_panels hashMapPanels;
    private Load_safe_and_search loadSafeAndSearch;

    public Panel(Window_map parent){
        textField = new Panel_with_text_field(parent);
        hashMapPanels = new Hash_map_panels(parent);
        loadSafeAndSearch = new Load_safe_and_search(parent);
        setLayout(new BorderLayout());
        add(textField,BorderLayout.NORTH);
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(300,300));
        add(hashMapPanels, BorderLayout.CENTER);
        add(loadSafeAndSearch,BorderLayout.SOUTH);
    }
    public JList[] get_lists(){
       return  this.hashMapPanels.get_lists();
    }
    public Hash_map_panels.Element_Event_listener get_Element_Event_listener_object(){

        return this.hashMapPanels.get_Element_Event_listener_object();
    }
    public Hash_map_panels Get_Hash_map_panels(){
        return this.hashMapPanels;
    }
    public  void Update_scroll_panels(){
        this.hashMapPanels.Update_scroll_panels();
    }

    public void Get_load_button_function(){
        this.loadSafeAndSearch.Load_button_function();
    }
    public void Get_save_button_function(){
        this.loadSafeAndSearch.Save_button_function();
    }
}
