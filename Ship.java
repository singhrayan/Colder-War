import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ship {
    private int x;
    private int y;
    private BufferedImage ship;
    private int width;
    private int height;
    private Color blue;
    private Boolean perimeter = false;

    public Ship(int x, int y){

        this.x = x;
        this.y = y;

        try {
            ship = ImageIO.read(new File("./sprites/spaceship1.png"));
        } catch (IOException ex) {
            // handle exception...
        }


        this.width = 72;
        this.height = 48;

        this.blue = new Color(0,0,255);



    }


    public void drawMe(Graphics g){

        g.drawImage(ship,x,y,width,height,null);



        if(perimeter){
            g.setColor(Color.green);
            g.drawRect(x,y,width,height);
        }

    }

    public void restart(){
        x = 200;
        y = 400;
    }

    public Boolean checkCollision(Enemy e, Boolean a){
        Boolean hit = false;
        if(a == true){

            int eX = e.getX();
            int eY = e.getY();
            int eWidth = e.getWidth();
            int eHeight = e.getHeight();
            hit = false;

            if(eY <= y + height && eY >= y && eX >= x && eX <= x + width){
                hit = true;
            }
        }
        return hit;


    }

    public void moveRight(){
        if(x < 1400){
            x = x + 2;
        }
    }

    public void moveLeft(){
        if(x > 0){
            x = x - 2;
        }
    }

    public void moveUp(){
        if(y > 0){
            y-=2;

        }
    }

    public void moveDown(){
        if(y < 1000){
            y+=2;
        }
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

    public void showPerim(){
        perimeter = true;
    }

    public void hidePerim(){
        perimeter = false;
    }

}
