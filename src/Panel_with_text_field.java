import javax.swing.*;
import java.awt.*;


public class Panel_with_text_field extends JPanel {
    private Text_field textField;
    private JTextArea textPane= new JTextArea("File:");

    public Panel_with_text_field(Window_map parent){
        this.textField = new Text_field(parent);
        textPane.setEditable(false);
        setLayout(new FlowLayout());
        add(textPane);
        textField.setPreferredSize(new Dimension(700, 20));
        add(textField);



    }
}
