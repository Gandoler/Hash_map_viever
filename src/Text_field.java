import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;


public class Text_field extends JFormattedTextField {
    private Dimension text_field_size = new Dimension(700,40);
    Window_map parent;
    Text_field(Window_map parent){
        this.parent = parent;
        setSize(text_field_size);
        addKeyListener(new Listener_for_text_field());
    }

    private void hash_map_load(){
        if(Check_file_path_entered(new File(this.getText()))){
            File filename  = new File(this.getText());
            Map_data Hash_map_data = new Map_data(parent,filename);
            this.parent.Set_map_data(Hash_map_data);

            JList[] lists =  this.parent.get_lists();
            lists[0].setModel(this.parent.get_data().Get_first_table_list().getModel());


        }


    }




    private boolean Check_file_path_entered(File file_path){
        if (!file_path.isFile()){
            String msg = "Выбран не файл";
            JOptionPane.showMessageDialog(this.parent , msg, "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!file_path.exists()){
            String msg = "Файл не существует";
            JOptionPane.showMessageDialog(this.parent , msg, "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!file_path.getName().endsWith(".ser")){
            String msg = "Файл имеет неправльное расширение";
            JOptionPane.showMessageDialog(this.parent , msg, "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }

    public class Listener_for_text_field implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                hash_map_load();
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {}
    }
}
