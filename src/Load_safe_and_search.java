import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

///Users/gl.krutoimail.ru/Desktop/Random_hash_maps/HashMap.ser
///Users/gl.krutoimail.ru/Desktop/Random_hash_maps/OpenedChain.dat
public class Load_safe_and_search extends JPanel {
    private JButton load = new JButton("load");
    private JButton save = new JButton("save");
    private JTextField textField_for_search = new JTextField();
    private Window_map parent;

    Load_safe_and_search(Window_map parent){
        this.parent = parent;
        setLayout(new BorderLayout());
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Load_button_function();
            }

        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Save_button_function();
            }

        });
        textField_for_search.addKeyListener(new Search_listener());

        add(load,BorderLayout.EAST);
        add(save,BorderLayout.WEST);
        add(textField_for_search,BorderLayout.CENTER);
    }



    public void Save_button_function(){
        boolean correct_input = false;
        File file_path;
        do{
        String File_name = JOptionPane.showInputDialog("Введите путь к файлу");
        file_path = new File(File_name);
        correct_input = Check_file_path_entered(file_path,true);
        }while (!correct_input);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file_path))) {
            oos.writeObject(parent.get_Element_Event_listener_object().Get_opened_chain());
            System.out.println("ArrayList сохранен в " + file_path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file_txf = new File("/Users/gl.krutoimail.ru/Desktop/Random_hash_maps/chain.txt");
        JOptionPane.showMessageDialog(parent,"All good-chain safe");
        saveArrayListToFile(parent.get_Element_Event_listener_object().Get_opened_chain(),file_txf);
    }
    private  static void saveArrayListToFile(ArrayList<String> list,File filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String item : list) {
                writer.write(item);
                writer.newLine(); // Добавляем новую строку для каждого элемента
            }
            System.out.println("ArrayList успешно сохранен в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении ArrayList в файл: " + e.getMessage());
        }
    }
    public void Load_button_function(){
        boolean correct_input = false;
        File file_path;
        do{
            String File_name = JOptionPane.showInputDialog("Введите путь к файлу");
            file_path = new File(File_name);
            correct_input = Check_file_path_entered(file_path);
        }while (!correct_input);

        ArrayList<String> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file_path))) {
            list = (ArrayList<String>) ois.readObject();
            System.out.println("ArrayList загружен из " + file_path);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        parent.Get_map_data().fill_by_loaded_chain(list);
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
        if (!file_path.getName().endsWith(".dat")){
            String msg = "Файл имеет неправльное расширение";
            JOptionPane.showMessageDialog(this.parent , msg, "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }
    private boolean Check_file_path_entered(File file_path,Boolean safe){
        if (!file_path.getName().endsWith(".dat")){
            String msg = "Файл имеет неправльное расширение";
            JOptionPane.showMessageDialog(this.parent , msg, "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }


    public class Search_listener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                Search();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private void Search(){
        String searched = this.textField_for_search.getText();
        parent.Get_map_data().Intel_search_in_list(parent.get_lists(),
                parent.get_Element_Event_listener_object().return_Current_max_page()+1 , searched);

    }
}
