package MyGui;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    public MyButton(String text){
        setText(text);
        setBackground(new Color(12,12,12));
        setForeground(new Color(199, 43, 43));
        setFont(new Font(Font.SERIF,Font.PLAIN,20));
    }
}
