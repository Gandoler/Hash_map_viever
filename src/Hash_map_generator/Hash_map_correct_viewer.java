package Hash_map_generator;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Hash_map_correct_viewer extends JFrame {
    private JPanel panel ;
    public Hash_map_correct_viewer(HashMap<String, Object> nestedMapLevel8){
        panel = new JPanel(new BorderLayout());
        this.add(panel);
        DefaultMutableTreeNode root = createTree(nestedMapLevel8);
        JTree hashMapTree = new JTree(root);
        panel.add(new JScrollPane(hashMapTree), BorderLayout.CENTER);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Hash map viewer");
        this.setSize(800,400);
    }
    private DefaultMutableTreeNode createTree(Map<?, ?> map) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("HashMap");

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(entry.getKey() + " : " + entry.getValue());

            if (entry.getValue() instanceof Map) {
                Map<?, ?> nestedMap = (Map<?, ?>) entry.getValue();
                node.add(createTree(nestedMap));
            }

            root.add(node);
        }

        return root;
    }
}
