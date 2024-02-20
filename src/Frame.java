import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame{
    boolean spacePressed;
    Frame(){
        this.setSize(1920, 1440);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("OPEN-MOTION");
        this.setFocusable(true);

    }

}