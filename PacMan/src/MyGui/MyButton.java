package MyGui;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    public MyButton(String text){
        setText(text);

        setOpaque(true);
        setBackground(new Color(245,169,91));
        setForeground(new Color(47, 72, 92));
        setFont(new Font(Font.SERIF,Font.PLAIN,20));
        setBorder(BorderFactory.createLineBorder(null,10));
        //setBorderPainted(false);
    }
}
