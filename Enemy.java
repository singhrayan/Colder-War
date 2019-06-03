import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Enemy{
    private int x;
    private int y;

    private int width;
    private int height;
    private Boolean visible = true;
    private Color green;
    private BufferedImage rocket;
    private Boolean perimeter = false;

    public Enemy(int a){

        if(a == 1){
            this.x = (int)(Math.random() * 1800 + 200);
        } else if(a == 2){
            this.x = (int)(Math.random() * 1800 + 1000);
        }

        this.y = (int)(Math.random() * 600 + 10);
        this.width = 32;
        this.height = 28;

        try {
            rocket = ImageIO.read(new File("./sprites/saucer.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        this.green = new Color(0,255,00);



    }

    public void restart(){
        x = (int)(Math.random() * 1800 + 200);
        y = (int)(Math.random() * 600 + 20);
        visible = true;
    }

    public void drawMe(Graphics g){
        if(visible == true){
            g.drawImage(rocket,x,y,width,height,null);
        } else {
            x = 20000;
        }

        if(perimeter && visible){
        	g.setColor(Color.red);
        	g.drawRect(x,y,width,height);
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
            }

        }
        return hit;
    }



    public void move(){
        if(visible){
            x--;
            y += (int)(Math.random() * 6 - 3);
        }
    }


    public void setX(){
        if(visible){
            x = 1400;
        }
    }
    public void showPerim(){
    	perimeter = true;
    }

    public void show(){
        visible = true;
    }

    public Boolean hitLeft(){
        Boolean left = false;
        if(x<0){
            left = true;
            x = 1200;
            y = (int)(Math.random() * 800 + 10);
        }

        return left;
    }

    public Boolean getIt(){
        return visible;
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

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
