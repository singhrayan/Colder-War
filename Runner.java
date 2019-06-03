import javax.swing.JFrame;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Colder War");
        String name;
        Boolean changed;
        int score;



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create panel and add it to the frame
        Screen sc = new Screen();

        frame.add(sc);
        frame.pack();
        frame.setVisible(true);

        while(true){
            sc.animate();
        }

    }
}
