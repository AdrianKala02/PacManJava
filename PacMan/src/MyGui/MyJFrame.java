package MyGui;

import javax.swing.*;
import java.awt.*;

public class MyJFrame extends JFrame {
   public MyJFrame(){
        setMinimumSize(new Dimension(600, 600));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
}
