import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.logging.*;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Screen extends JPanel implements KeyListener {

    private Projectile p1;
    private Ship s1;



    private BossShot[] shots;
    private Music m1;
    private Boss putin;
    private Word writer;
    private HealthBar hb;
    private HealthBar hb2;
    private Boolean game = true;

    private Boolean gameover = false;

    private int continueIt = 0;
    private int timer = 0;
    private int lives = 3;

    private String[] names;
    private int[] scores;


    private String writehelper;


    private int bossHealth = 3600;
    private int wall1X = 0;
    private int wall2X = 1300;
    private int scrollX2 = 1300;
    private int changer = 2000;
    private int stage = 0;
    private int time = 0;
    private int interval = 0;
    private int killed = 0;
    private int hits = 0;
    private int change = 0;
    private int change2 = 0;
    private int change3 = 0;
    private int health = 1800;

    private int scrollX = 0;

    private Enemy[] enemies;
    private Enemy[] enemies2;
    private Planet[] planets;


    private BufferedImage bkgd;
    private BufferedImage gover;
    private BufferedImage wallpaper;
    private BufferedImage wallpaper2;
    private BufferedImage wallpaper3;
    private BufferedImage wallpaper4;
    private BufferedImage pause;


    private Boolean up = false;
    private Boolean down = false;
    private Boolean right = false;
    private Boolean left = false;
    private Boolean hit = false;
    private Boolean helpShown = false;
    private Boolean paused = false;


    private int score = 0;
    private Boolean changed = false;
    private String named;
    private int scored;
    private String name = "rayan";

    private int scrollY = 0;


    public Screen(){

        m1 = new Music();



        try {
            bkgd = ImageIO.read(new File("./screens/space.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }

        try {
            pause = ImageIO.read(new File("./sprites/pause.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }

        try {
            wallpaper = ImageIO.read(new File("./screens/wallpaper.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        try {
            wallpaper3 = ImageIO.read(new File("./screens/wallpaper2.png"));
        } catch (IOException ex) {
            // handle exception...
        }
        try {
            wallpaper4 = ImageIO.read(new File("./screens/wallpaper2.png"));
        } catch (IOException ex) {
            // handle exception...
        }


        try {
            wallpaper2 = ImageIO.read(new File("./screens/wallpaper.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        try {
            gover = ImageIO.read(new File("./screens/go.png"));
        } catch (IOException ex) {
            // handle exception...
        }

        planets = new Planet[20];
        s1 = new Ship(375,500);
        p1 = new Projectile(385,480);
        writer = new Word();
        shots = new BossShot[10];

        putin = new Boss(800,50);
        hb = new HealthBar(500, 20, 80,12);
        hb2 = new HealthBar(800,20, 200,30);
        enemies = new Enemy[3];
        enemies2 = new Enemy[10];


        m1.rickRoll();
        for(int i = 0; i < planets.length; i++){
            planets[i] = new Planet();
        }

        for(int i = 0; i < shots.length; i++){
            shots[i] = new BossShot();
        }

        //sets keylistener
        setFocusable(true);
        addKeyListener(this);
    }

    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1400,800);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(game){
            if(stage == 1){
                g.drawImage(wallpaper,0,0,1400,1000,null);
                writer.write(g,"trump has declared war on russia", 0, 0, 15);
                for(int i = 0; i < enemies.length; i++){
                    enemies[i] = new Enemy(1);
                }

                for(int i = 0; i < enemies2.length; i++){
                    enemies2[i] = new Enemy(2);
                }
            } else if(stage == 2){
                g.drawImage(wallpaper,0,0,1400,1000,null);
                writer.write(g,"you have been drafted into the spaceforce", 0, 0, 15);
            } else if(stage == 3){
                g.drawImage(wallpaper,0,0,1400,1000,null);
                writer.write(g,"kill the enemies", 0, 0, 15);
            } else if(stage == 4){
                g.drawImage(wallpaper,0,0,1400,1000,null);
                writer.write(g,"Level 1", 800,0,10);
                writer.write(g,"" + lives + " lives left", 0,700,30);
                writer.write(g,"press h for help",0,0,10);
				writer.write(g,"" + killed + " killed",500,0,10);
                for(int i = 0; i < planets.length; i++){
                    planets[i].drawMe(g);
                }
                for(int i = 0; i < enemies.length; i++){
                    enemies[i].drawMe(g);
                }
                s1.drawMe(g);
                p1.drawMe(g);
                hb.drawMe(g);
                hb.change((int)(health/100));
                if(killed == 3){
                    killed = 0;
                    stage++;

                    health = 1800;

                }
                if(lives <= 0){
                    stage = 69;
                }
                if(helpShown){
                    writer.write(g,"press the arrow keys to move", 0,15,10);
                    writer.write(g,"press space to shoot", 0,30,10);

                }
            } else if(stage == 5){
                g.drawImage(wallpaper3,scrollX,0,1400,1000,null);
                g.drawImage(wallpaper4,scrollX2,0,1400,1000,null);
                writer.write(g,"" + killed + " killed",500,0,10);
                writer.write(g,"press h for help", 0,0,10);
                writer.write(g,"" + lives + " lives left", 0,700,30);
                writer.write(g,"Level 2", 800,0,10);
                for(int i = 0; i < enemies2.length; i++){
                    enemies2[i].drawMe(g);
                }
                p1.drawMe(g);
                s1.drawMe(g);
                hb.drawMe(g);
                hb.change((int)(health/100));
                if(killed == 10){
                    stage++;
                    health = 1800;
                }
                if(helpShown){
                    writer.write(g,"press the arrow keys to move", 0,15,10);
                    writer.write(g,"press space to shoot", 0,30,10);

                }
            } else if(stage == 6){
                g.drawImage(wallpaper,wall1X,0,1400,1000,null);
                g.drawImage(wallpaper,wall2X,0,1400,1000,null);
                writer.write(g,"Bossfight", 800,0,10);
                writer.write(g,"" + lives + " lives left", 0,700,30);
                writer.write(g,"press h for help", 0,0,10);
                putin.drawMe(g);
                for(int s = 0; s < shots.length; s++){
                    shots[s].drawMe(g);
                }
                s1.drawMe(g);
                p1.drawMe(g);
                hb.drawMe(g);
                hb2.drawMe(g);
                if(helpShown){
                    writer.write(g,"press the arrow keys to move", 0,15,10);
                    writer.write(g,"press space to shoot", 0,30,10);

                }
                if(paused){
                    g.drawImage(pause,650,400,100,100,null);
                }
            } else if(stage == 7){
				
				stage = 666;
			
			
			}else if(stage == 69){
                g.setColor(Color.black);
                g.fillRect(0,0,1400,1000);

                writer.write(g,"you died", 0,0,30);
                writer.write(g,"Press q to restart", 0,200,30);
                writer.write(g,"Press w to end the game", 0,500,30);
            } else if(stage == 70){
                g.setColor(Color.black);
                g.fillRect(0,0,1400,1000);

                writer.write(g,"game", 0,scrollY,300);
                writer.write(g,"over", 0,scrollY + 410,300);
                writer.write(g,"All Sprites Except Backgrounds by Rayan Singh", 0,scrollY + 810,10);
                writer.write(g,"All Code Written by Rayan Singh", 0,scrollY + 830,10);
                writer.write(g,"Text Engine by Rayan Singh", 0,scrollY + 850, 10);

            } else if(stage == 666){


                g.setColor(Color.black);
                g.fillRect(0,0,1400,1000);

                writer.write(g,"you", 0,scrollY,300);
                writer.write(g,"win", 0,scrollY + 410,300);
                writer.write(g,"All Sprites Except Backgrounds by Rayan Singh", 0,scrollY + 810,10);
                writer.write(g,"All Code Written by Rayan Singh", 0,scrollY + 830,10);
                writer.write(g,"Text Engine by Rayan Singh", 0,scrollY + 850, 10);
            }
        }


    }


    public void animate(){

        if(game == true){
            interval++;
            scored = score;
            named = name;
            score = killed;

            for(int s = 0; s < shots.length; s++){
                shots[s].setPosition(s1.getY());
            }

            try {
                Thread.sleep(changer);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            repaint();

            p1.moveRight();

            if(stage == 4){
                hb.setPos(s1.getX(), s1.getY() - 10);

                timer++;

                if(timer == 2){
                    for(int i = 0; i < enemies.length; i++){
                        enemies[i].move();
                    }

                    timer = 0;
                }
                for(int i = 0; i < enemies.length; i++){
                    Boolean shot = enemies[i].checkCollision(p1);

                    if(shot){
                        killed++;
                        m1.boom();
                    }
                }
                for(int i = 0; i < enemies.length; i++){
                    Boolean hit = s1.checkCollision(enemies[i], enemies[i].getIt());
                    Boolean hitLeft = enemies[i].hitLeft();

                    if(hitLeft){
                        health-=200;
                        m1.damage();

                        if(health <= 0 && lives > 0){
                            lives--;
                            if(lives == 0){
                                stage = 69;
                            }
                            health = 1800;
                        }
                    }

                    if(hit){
                        health-=200;
                        m1.damage();

                        if(health <= 0 && lives > 0){
                            lives--;
                            if(lives == 0){
                                stage = 69;
                            }
                            health = 1800;
                        }


                        enemies[i].setX();
                    }
                }


                for(int i = 0; i < planets.length; i++){

                    planets[i].move();


                }

            } else if(stage == 5){
                for(int i = 0; i < enemies2.length; i++){
                    Boolean shot = enemies2[i].checkCollision(p1);

                    if(shot){
                        killed++;
                        m1.boom();
                    }

                }
                scrollX--;
                scrollX2--;
                if(scrollX == -1300){
                    scrollX = 1300;
                }

                if(scrollX2 == -1300){
                    scrollX2 = 1300;
                }
                for(int i = 0; i < enemies2.length; i++){
                    Boolean hit = s1.checkCollision(enemies2[i], enemies2[i].getIt());
                    Boolean hitLeft = enemies2[i].hitLeft();

                    if(hitLeft){
                        health-=200;
                        m1.damage();

                        if(health <= 0 && lives > 0){
                            lives--;
                            if(lives == 0){
                                stage = 69;
                            }
                            health = 1800;
                        }
                    }
                    if(hit){
                        health-=200;
                        m1.damage();

                        if(health <= 0 && lives > 0){
                            health = 1800;
                            lives--;

                            if(lives == 0){
                                stage = 69;
                            }
                        }

                        enemies2[i].setX();
                    }
                }
                for(int i = 0; i < enemies2.length; i++){
                    enemies2[i].move();
                }
                hb.setPos(s1.getX(), s1.getY() - 10);

            } else if(stage == 6){
                hb.change((int)(health/100));

                hb2.change((int)(bossHealth/200));
                hb2.setPos(putin.getX() + 100, putin.getY());
                hb.setPos(s1.getX(), s1.getY() - 10);

                putin.move();

                for(int s = 0; s < shots.length; s++){
                    shots[s].moveLeft();
                    Boolean bossHit = shots[s].checkBossShot(s1);
                    if(bossHit){
                        health-=200;

                        if(health <= 0){
                            lives--;
                            health = 1800;


                        }



                    }
                    Boolean shotDown = shots[s].checkCollision(p1);
                    if(shotDown){
                        m1.boom();
                    }
                }


                Boolean triggered = putin.checkCollision(p1);

                if(triggered){
                    bossHealth--;

                    triggered = false;
                }

                wall1X--;
                wall2X--;

                if(wall1X == -1300){
                    wall1X = 1300;
                }

                if(wall2X == -1300){
                    wall2X = 1300;
                }

            }

            Boolean putinDead = putin.checkDead();

            if(putinDead){
                stage = 666;
            }

            if(right == true){
                s1.moveRight();
            }

            if(left == true){
                s1.moveLeft();
            }

            if(up == true){
                s1.moveUp();
            }

            if(down == true){
                s1.moveDown();
            }

            if(stage < 4){
                stage++;
            } else {
                changer = 5;
            }

            if(lives == 0 && gameover == false){
                gameover = true;
                stage = 69;
            }
        } else if(stage == 70 || stage == 71 || stage == 66) {
            try {
                Thread.sleep(50);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            scrollY--;
            repaint();
        }

    }

    //implement methods of the KeyListener
    public void keyPressed(KeyEvent e) {

        System.out.println( e.getKeyCode() );
        //key code
        //http://www.cambiaresearch.com/articles/15/javascript-char-codes-key-codes

        if (e.getKeyCode()==37){//Left Arrow
            left = true;
            right = false;
        }

        if (e.getKeyCode()==39){//Right Arrow
            right = true;
            left = false;
        }

        if (e.getKeyCode()==69){//e
            stage = 69;
        }

        if (e.getKeyCode()==38){//Up Arrow
            up = true;
            down = false;
        }

        if(e.getKeyCode() == 40){//Down Arrow
            down = true;
            up = false;

        }

        if(e.getKeyCode() == 80){//P
            if(paused == false){
                paused = true;
            }

            if(paused){
                paused = false;
            }

        }


        if(e.getKeyCode() == 77){//M
            m1.play();
        }

        if(e.getKeyCode() == 73){//i
            m1.rickRoll();
        }

        if ( e.getKeyCode() == 32 ) { //Spacebar
            //update the position of the projectile to the position of the spaceship
            p1.setPosition( s1.getX() + 72,  s1.getY() + 23);

            //shoot the projectile

            if(health > 0){
                m1.shoot();
            }


        }

        if(e.getKeyCode() == 79){
            s1.showPerim();

            for(int i = 0; i < enemies.length; i++){
                enemies[i].showPerim();
            }
        }



        if(e.getKeyCode() == 72){ //h
            helpShown = true;
        }


        if(e.getKeyCode() == 27){ //Esc
            m1.stopMusic();
        }
        repaint();



    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==37){//Left Arrow
            left = false;
        }

        if(e.getKeyCode()==39){//Right Arrow
            right = false;
        }

        if(e.getKeyCode()==38){//Up Arrow
            up = false;
        }

        if(e.getKeyCode() == 40){//Down Arrow
            down = false;
        }

        if(e.getKeyCode() == 80){//P
            stage++;
        }
        if(e.getKeyCode() == 72){ //h
            helpShown = false;
        }
        if(e.getKeyCode() == 81){ //q
            if(stage == 69){
                stage = 71;
            }
            scrollY = 0;

            for(int i = 0; i < enemies.length; i++){
                enemies[i].restart();
            }
            for(int i = 0; i < enemies2.length; i++){
                enemies2[i].restart();
            }
            for(int i = 0; i < shots.length; i++){
                shots[i].restart();
            }
            for(int i = 0; i < planets.length; i++){
                planets[i].restart();
            }
            hb.restart();
            hb2.restart();
            s1.restart();
            m1.stopMusic();
            health = 1800;
            lives = 3;
            stage = 1;
            killed = 0;

        }
        if(e.getKeyCode() == 87){ //w
            if(stage == 69){
                stage = 70;
            }
            scrollY = 0;
        }

        if(e.getKeyCode() == 79){
            s1.hidePerim();

            for(int i = 0; i < enemies.length; i++){
                enemies[i].hidePerim();
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public Boolean changed(){

        if(name != named || score != scored){
            changed = true;
        } else {
            changed = false;
        }

        return changed;
    }

}
