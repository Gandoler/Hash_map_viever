import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Console;
import java.util.ArrayList;
///Users/gl.krutoimail.ru/Desktop/Random_hash_maps/HashMap.ser
public class Hash_map_panels extends JPanel {
    private JList[] list_levels;
    private JScrollPane[] scrollPane;
    private Window_map parent;

    Hash_map_panels(Window_map parent){
        this.list_levels = new JList[11];
        this.scrollPane = new JScrollPane[11];
        this.parent = parent;

        setLayout(new GridLayout());
        setBackground(Color.BLACK);
        Dimension dimension = new Dimension(
                parent.getSize().width/2,parent.getSize().height/2);
        setSize(dimension);
        for(int i=0;i<list_levels.length-1;i++){
            list_levels[i] = new JList();
        }

        for(int i=0;i<list_levels.length-1;i++){
            list_levels[i].addMouseListener(new Element_Event_listener(i , list_levels));
            scrollPane[i] = new JScrollPane(list_levels[i]);
            add(scrollPane[i],i);

        }

    }


    public void Reload(){
        this.repaint();
    }
    public int get_current_max_page(){
        int i=0;
        return new Element_Event_listener(i,this.list_levels).return_Current_max_page();
    }


    public JList[] get_lists(){
        return this.list_levels;
    }

    public  void Update_scroll_panels(){
        this.repaint();
    }
    public Element_Event_listener get_Element_Event_listener_object(){
        int i=0;
        return new Element_Event_listener (i,this.list_levels);
    }

    public class Element_Event_listener implements MouseListener{
        static ArrayList<String> opened_chain = new ArrayList<>();
        private int Index_of_lists;
        private JList[] list_levels;
        private int Amount_of_clicks = 0;
        static int Current_max_page = 0;

        public int return_Current_max_page(){
            return Current_max_page;
        }
        public void Set_Amount_of_clicks(int clicks){
            this.Amount_of_clicks = clicks;
        }
        public void Set_Current_max_page(int max_page){
            Current_max_page = max_page;
        }
        public ArrayList<String> Get_opened_chain(){
            return opened_chain;
        }
        public void Set_opened_chain(ArrayList<String> chain){
            opened_chain = chain;
        }

        Element_Event_listener(int Index_of_lists, JList[] list_levels){

            this.Index_of_lists = Index_of_lists;
            this.list_levels = list_levels;
        }
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            Current_max_page = Math.max(Current_max_page,Index_of_lists);
            if(Index_of_lists< parent.Get_map_data().get_hash_map_depth()) {
                Amount_of_clicks++;
                if (Amount_of_clicks % 2 == 1) {
                    JList<String> clicked_list = (JList<String>) e.getSource();
                    opened_chain.add(clicked_list.getSelectedValue());

                    list_levels[this.Index_of_lists + 1]
                            .setModel(parent.get_data().Get_n_table_list(opened_chain).getModel());
                }
                else if (Amount_of_clicks % 2 == 0 && Amount_of_clicks>1){
                    for(int i= this.Index_of_lists+1;i<list_levels.length-1;i++){
                        list_levels[i].setListData(new String[0]);
                    }
                    opened_chain =  new ArrayList<>(opened_chain.subList(0,Index_of_lists));


                }
            }


        }


        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
