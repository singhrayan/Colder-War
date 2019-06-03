import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Boss{
    private int x;
    private int y;
    private Boolean rising = false;

    private int width;
    private int height;
    private Boolean visible = true;
    private Color green;
    private BufferedImage putin;
    private BufferedImage putin2;
    private BufferedImage blowup1;
    private BufferedImage blowup2;
    private Boolean perimeter = false;
    private Boolean dead = false;
    private int costume = 0;
    private int changer = 0;
    private int health = 3600;

    public Boss(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 400;
        this.height = 500;

        try {
            putin = ImageIO.read(new File("./sprites/pixelputin.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        try {
            putin2 = ImageIO.read(new File("./sprites/pixelputin2.png"));
        } catch (IOException ex) {
            // handle exception...
        }
        try {
            blowup1 = ImageIO.read(new File("./sprites/blowup1.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        try {
            blowup2 = ImageIO.read(new File("./sprites/blowup2.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        this.green = new Color(0,255,00);

        visible = true;

    }


    public void drawMe(Graphics g){

        if(health > 0){
            visible = true;
        } else {
            visible = false;
        }


        if(visible == true){
            if(costume == 0){
                g.drawImage(putin,x,y,width,height,null);
            } else if(costume == 1){
                g.drawImage(putin2,x,y,width,height,null);
            }
        } else if(visible == false){
            if(costume == 0){
                g.drawImage(blowup1,x + 50,y + 50,300,300,null);
            } else if(costume == 1){
                g.drawImage(blowup2,x + 50,y + 50,300,300,null);
            }
        }

        if(perimeter && visible){
        	g.setColor(Color.red);
        	g.drawRect(x,y,width,height);
        }

    }

    public Boolean checkCollision(Projectile p){
        Boolean hit = false;
        int pX = p.getX();
        int pY = p.getY();
        int pWidth = p.getWidth();
        int pHeight = p.getHeight();
        hit = false;

        if(pY <= y + height && pY >= y && pX >= x && pX <= x + width){
            hit = true;
            health--;
        }
        return hit;
    }



    public void move(){
        if(visible){
            if(changer == 10){
                changer = 0;
                if(costume == 0){
                    costume = 1;
                } else if(costume == 1){
                    costume = 0;
                }
            } else {
                changer++;
            }

            if(rising == false){
                y++;
            } else {
                y--;
            }

            if(y == 300){
                rising = true;
            }

            if(y == 0){
                rising = false;
            }
        }

    }

    public void restart(){
        health = 3600;
        dead = false;
    }

    public void showPerim(){
    	perimeter = true;
    }

    public Boolean checkDead(){
        dead = false;
        if(health > 0){
            dead = false;
        }

        if(health <= 0){
            dead = true;
        }

        return dead;
    }

    public void hidePerim(){
    	perimeter = false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
