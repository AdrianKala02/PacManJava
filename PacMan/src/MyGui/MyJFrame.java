package MyGui;

import javax.swing.*;
import java.awt.*;

public class MyJFrame extends JFrame {
   public MyJFrame(){
        setMinimumSize(new Dimension(600, 600));
        setVisible(true);
       setForeground(new Color(47, 72, 92));
       setBackground(new Color(245,169,91));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
}
