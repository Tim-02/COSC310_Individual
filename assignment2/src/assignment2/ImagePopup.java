package assignment2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImagePopup {
    public static void popImage(String img, String title){
        JFrame f = new JFrame(title); //creates jframe f

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //this is your screen size

        try {
            ImageIcon image = new ImageIcon(ImageIO.read(new URL(img))); //import the image from URL

            JLabel lbl = new JLabel(image); //puts the image into a jlabel

            f.getContentPane().add(lbl); //puts label inside the jframe

            f.setSize(image.getIconWidth(), image.getIconHeight()); //gets h and w of image and sets jframe to the size
        } catch (IOException e) {
            e.printStackTrace();
        }

        int x = (screenSize.width - f.getSize().width)/2; //These two lines are the dimensions
        int y = (screenSize.height - f.getSize().height)/2;//of the center of the screen

        f.setLocation(x, y); //sets the location of the jframe
        f.setVisible(true); //makes the jframe visible
    }
}
