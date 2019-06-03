import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.logging.*;

public class Planet{
    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage planet1;
    private BufferedImage planet2;
    private BufferedImage planet3;
    private int planet;
    private int nathanisbigdumb = 0;

    public Planet(){
        try {
            planet1 = ImageIO.read(new File("./sprites/planet1.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        try {
            planet2 = ImageIO.read(new File("./sprites/planet2.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        try {
            planet3 = ImageIO.read(new File("./sprites/planet3.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        this.x = (int)(Math.random() * 1200);
        this.y = (int)(Math.random() * 1000);
        planet = (int)(Math.random() * 3);

        width = (int)(Math.random() * 60 );
        height = width;


    }

    public void restart(){
        this.x = (int)(Math.random() * 1200);
        this.y = (int)(Math.random() * 1000);
        planet = (int)(Math.random() * 3);

        width = (int)(Math.random() * 60 );
        height = width;
    }

    public void drawMe(Graphics g){
        //g.setColor(Color.blue);
        //g.fillOval(x,y,width,height);
        if(planet == 1){
            g.drawImage(planet1,x,y,65,45,null);
        } else if(planet == 2){
            g.drawImage(planet2,x,y,width,height,null);
        } else {
            g.drawImage(planet3,x,y,width,height,null);
        }
    }



    public void move(){

        if(x < 0){
            x = 1200;
            y = (int)(Math.random() * 600);
        }

        if(nathanisbigdumb == 5){
            x--;
            nathanisbigdumb = 0;
        }

        nathanisbigdumb++;

    }
}
