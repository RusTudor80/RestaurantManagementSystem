package Presentation;

import javax.swing.*;
import java.awt.*;

public class Bucatar {
    JFrame frame;
    public Bucatar() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame();
        frame.setTitle("Bucatar");
        frame.setLayout(new GridLayout(3, 1));
        frame.setSize(screenSize.width/2, screenSize.height/2);
        frame.setLocation(680, 0);
        frame.getContentPane().setBackground(Color.black);
        frame.setVisible(true);
      
    }

    public void popUp(String compositeItems) {
        JOptionPane.showMessageDialog(frame, compositeItems + " a fost comandata.", "O noua comanda a fost plasata!", JOptionPane.WARNING_MESSAGE);
    }
}
