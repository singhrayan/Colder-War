import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Projectile{
    private int x;
    private int y;

    private int width;
    private int height;

    private Color red;
    private boolean visible;

    private BufferedImage bullet;


    public Projectile(int x, int y){

        try {
            bullet = ImageIO.read(new File("./sprites/bullet.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        this.x = x;
        this.y = y;

        this.width = 16;
        this.height = 4;

        this.visible = false;

    }

    public void drawMe(Graphics g){

        g.drawImage(bullet,x,y,width,height,null);
    }

    public void moveRight(){
        if( visible == true ){
            x+=5;
        }

        if( x > 1200){
            visible = false;
        }

    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public void setPosition(int x, int y){
        visible = true;
        this.x = x;
        this.y = y;
    }


    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }



}
