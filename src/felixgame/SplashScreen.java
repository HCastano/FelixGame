package felixgame;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class SplashScreen extends JFrame {
   JPanel contentPane;
   JLabel imageLabel = new JLabel();
   //JLabel headerLabel = new JLabel();

   public SplashScreen() {
     super ("Felix by 3AM Productions");
       try {
           setDefaultCloseOperation(EXIT_ON_CLOSE);
           contentPane = (JPanel) getContentPane();
           setSize(new Dimension(900, 600));

           
           ImageIcon ii = new ImageIcon("assets/Pictures/BlueCup2.gif");
           imageLabel.setIcon(ii);
           contentPane.add(imageLabel);
          
           this.setLocationRelativeTo(null);
           this.setVisible(true);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public static void main(String[] args) {
       new SplashScreen();
   }

}