package MyGui;

import javax.swing.*;
import java.awt.*;

public class MyJlable extends JLabel {
   public MyJlable(){
        setPreferredSize(new Dimension(32,32));
        setMinimumSize(new Dimension(32,32));
        setMaximumSize(new Dimension(32,32));
        setBackground(new Color(47, 72, 92));
        setForeground(new Color(245,169,91));
      setFont(new Font("Serif", Font.PLAIN, 20));
    }
}
