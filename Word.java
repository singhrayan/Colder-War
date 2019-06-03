import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Word{
    private char[] chars;
    private int x1 = 0;
    private BufferedImage letter;
    private int size;
    private int y1;

    public Word(){

    }

    public void write(Graphics gr, String str, int x, int y, int size){
        chars = new char[str.length()];



        for(int i = 0; i < str.length(); i++){
            chars[i] = str.charAt(i);
        }

        x1 = x;
        y1 = y;
        for(int i = 0; i < str.length(); i++){


            if(chars[i] == ' '){
                try {
                    letter = ImageIO.read(new File("./letters/space.png"));
                } catch (IOException ex) {
                    // handle exception...
                }
                gr.drawImage(letter,x1,y1,size,size,null);
            } else {
                try {
                    letter = ImageIO.read(new File("./letters/" + chars[i] + ".png"));
                } catch (IOException ex) {
                    // handle exception...
                }

                gr.drawImage(letter,x1,y1,size,size,null);
            }

            x1+=size + 15;
        }

    }
}
