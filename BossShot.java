import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BossShot{
    private int x;
    private int y;

    private int width;
    private int height;
    private int counter = 0;

    private int shipY;
    private boolean visible;

    private BufferedImage bullet;


    public BossShot(){

        try {
            bullet = ImageIO.read(new File("./sprites/adidas.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        this.x = 1200;
        this.y = (int)(Math.random() * 800);

        this.width = 80;
        this.height = 32;

        this.visible = true;

    }

    public void start(){
        x = 1200;
    }
    public void drawMe(Graphics g){

        g.drawImage(bullet,x,y,width,height,null);
    }

    public void restart(){
        visible = true;
        x = 1200;
        y = (int)(Math.random() * 800);
        
    }

    public Boolean checkBossShot(Ship s){
        Boolean hit = false;
        if(visible){
            int sX = s.getX();
            int sY = s.getY();
            int sWidth = s.getWidth();
            int sHeight = s.getHeight();
            hit = false;

            if(sY <= y + height && sY >= y && sX >= x && sX <= x + width){
                visible = false;
                hit = true;
            }

        }
        return hit;
    }

    public void moveLeft(){
        if( visible == true ){
            x-=2;
        }

        if( x < 0 ){
            x = (int)(Math.random() * 100 + 1000);
            y = (int)(Math.random() * 800 - 10);

        }

        counter++;

        if(counter == 2){
            if(shipY < y){
                y--;
            }

            if(shipY > y){
                y++;
            }
            counter = 0;
        }

        if(visible == false){
            x = 1000;
            y = 450;
            visible = true;
        }


    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public void setPosition(int yGo){
        if( visible == true){
            shipY = yGo;
        }

    }

    public Boolean checkCollision(Projectile p){
        Boolean hit = false;
        if(visible){
            int pX = p.getX();
            int pY = p.getY();
            int pWidth = p.getWidth();
            int pHeight = p.getHeight();
            hit = false;

            if(pY <= y + height && pY >= y && pX >= x && pX <= x + width){
	            visible = false;
                hit = true;
                x = 1200;
                y = (int)(Math.random() * 800 + 10);
            }

        }
        return hit;
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
