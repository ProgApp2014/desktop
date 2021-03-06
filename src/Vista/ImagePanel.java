/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import clases.ImagesProxy;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author rodro
 */
public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    public ImagePanel() {

    }

    public void cleanIMG() {
        image = null;
    }

    public void loadImg(String path) {

        loadImg(path, true);
    }

    public void loadImg(String path, boolean s) {

        try {
            ImagesProxy ih = new ImagesProxy();
            byte[] b = ih.getImage(path);
            InputStream is = new ByteArrayInputStream(b);
            image = ImageIO.read(is);
        } catch (IOException ex) {

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            System.out.println(getWidth() + " " + getHeight());

            g.drawImage(image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT), 0, 0, null); // see javadoc for more info on the parameters            
        }
    }

}
