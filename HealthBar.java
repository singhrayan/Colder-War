import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.logging.*;

public class HealthBar{
    private int x;
    private int y;
    private BufferedImage bar;
    private int health;
    private int changer = 0;
    private int width;
    private int height;


    public HealthBar(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        health = 18;
        try {
            bar = ImageIO.read(new File("./sprites/healthbar/h" + health + ".png"));
        } catch (IOException ex) {
            // handle exception...
        }

        this.width = width;
        this.height = height;
    }


    public void drawMe(Graphics g){
        try {
            bar = ImageIO.read(new File("./sprites/healthbar/h" + health + ".png"));
        } catch (IOException ex) {
            // handle exception...
        }

        g.drawImage(bar,x,y,width,height,null);
    }

    public void restart(){
        try {
            bar = ImageIO.read(new File("./sprites/healthbar/h18.png"));
        } catch (IOException ex) {
            // handle exception...
        }

    }

    public void change(int a){
        health = a;
    }

    public void setPos(int a, int b){
        x = a;

        changer++;

        if(changer == 2){
            if(b - 20 > y){
                y+=2;
            }

            if(b - 40 < y){
                y-=3;
            }
            changer = 0;
        }

    }
}
