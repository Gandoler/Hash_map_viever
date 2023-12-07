import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window_map extends JFrame {
    private Panel panel;
    private Dimension screen_size = getToolkit().getScreenSize();
    private Dimension window_size  = new Dimension(1000,400);
    private Map_data data;





    public Window_map(){




        panel = new Panel(this);


        setLayout(new BorderLayout());
        add(panel,BorderLayout.CENTER);
        JToolBar toolBar = new JToolBar();
        JButton reloadButton = new JButton("Перезагрузить окно");
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Window_map();
            }
        });


        toolBar.add(reloadButton);
        add(toolBar, BorderLayout.NORTH);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setBackground(Color.CYAN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds((screen_size.width-window_size.width)/2
                ,(screen_size.height-window_size.height)/2
                ,window_size.width,window_size.height);
        setVisible(true);
        setResizable(false);
       setTitle("HachMap viever");
    }

    public void Set_map_data(Map_data data){this.data = data;}
    public Map_data Get_map_data(){
        return this.data;
    }
    public JList[] get_lists(){
       return this.panel.get_lists();
    }
    public Map_data get_data(){
        return this.data;
    }

    public Hash_map_panels.Element_Event_listener get_Element_Event_listener_object(){

        return this.panel.get_Element_Event_listener_object();
    }
    public  void Update_scroll_panels(){
        this.panel.Update_scroll_panels();
    }
    public Hash_map_panels Get_Hash_map_panels(){
        return this.panel.Get_Hash_map_panels();
    }
}
