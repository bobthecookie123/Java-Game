import java.awt.Canvas;
import java.awt.image.BufferStrategy; 
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;



import java.util.*;
/**
 * game code
 */
public class Game extends Canvas implements Runnable
{
    private boolean running = false;
    private Thread thread;

    private boolean printTicksAndFps = false;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static BufferStrategy bs;

    public static int currentRoom = 0;

    private static boolean startRound = false;

    private static int roundNum = 1;

    public static Player p;

    public static boolean gameOver = false;

    Handler handler;
    Random rand = new Random();

    //initialises game objects
    private void init(){
        int abc = 123;

        handler = new Handler();

        handler.createPlayer();

        handler.createDoors1();

        this.addKeyListener(new KeyInput(handler));
    }

    //starts game
    public synchronized void start(){
        if(running){
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    //runs game at 60 ticks per second
    public void run(){
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                if(printTicksAndFps==true){
                    System.out.println("FPS: " + frames + " TICKS: " + updates);
                }
                frames = 0;
                updates = 0;
            }
        }

        
    }

    //calls tick method
    private void tick(){
        handler.tick();
    }

    //renders game (this is where the main game loop is)
    private void render(){
        bs = this.getBufferStrategy();

        if(bs==null){
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        /////////////draw game here/////////////

        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        handler.render(g);
        p = (Player)Handler.object.get(0);
        

        if(Bullet.bulletTicks<=100)
            Bullet.bulletTicks++;            

        handler.manageBullets();
        if(currentRoom==1&&startRound==false){
            handler.createEnemies(roundNum);
            startRound = true;
        }

        if(handler.getEnemyCount()==0&&startRound==true){
            startRound=false;
            roundNum++;
            p.resetHp();
        }

        if(gameOver==true){
            try{
                end();
            }
            catch(IOException e){
                
            }
        }
        
        

        
        
        
        /////////////////////////////////////////
        g.dispose();
        bs.show();
    }
    
    public void displayInstructions(){
        BufferStrategy bs = this.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        g.setFont(new Font("Serif",Font.BOLD,40));
        g.setColor(Color.GRAY);
        g.drawString("Instructions:",70,100);
       
        g.setColor(Color.LIGHT_GRAY);
        //controls
        g.drawString("use WASD to move",30,180);
        g.drawString("use the Arrow Keys to",30,260);
        g.drawString("shoot while in combat",30,300);       
        g.drawString("enter the portal to begin",15,380);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("press Esc to exit the game",15,460);
        g.drawString("press 1 or 2 to change weapons",15,540);
        g.drawString("weapon 1 is precise but shoots slower",15,620);
        g.drawString("weapon 2 not precise but shoots faster",15,700);
        
        
        
        
        
    }

    //saves top 5 scores
    public ArrayList saveScore(int score)throws IOException{
        
        ArrayList<Integer> savedScores = readScores();
        
        clearFile();
        PrintWriter pw = new PrintWriter(getFile());
        
        for(int i = 0; i < 5; i ++){
            if(score>=savedScores.get(i)){
                savedScores.remove(i);
                savedScores.add(score);
                break;
            }
        }
        Collections.sort(savedScores);
        Collections.reverse(savedScores);
        
        System.out.println("new highscores list");
        for(int i = 0; i < 5; i ++){
            pw.println(savedScores.get(i));
            System.out.println(savedScores.get(i));
        }
       
        pw.close();
        System.out.println("saved");
        
        return savedScores;
    }
    
    public static void clearFile()throws IOException {
        FileWriter fwOb = new FileWriter("scores", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
    
    public static File getFile(){
        File scores = new File("scores.txt");
        return scores;
    }

    
    public static ArrayList readScores()throws IOException{
        File scores = getFile();
        
        Scanner sc = new Scanner(scores);
        String[] s = new String[5];//scores as string
        int count = 0;
        //System.out.println("saved scores:");
        while(sc.hasNext()){

            s[count]=sc.nextLine();
            
            //System.out.println(s[count]);

            count++;
        }
        sc.close();

        ArrayList<Integer> sAsInt = new ArrayList(5);//scores as integer
        for(int i = 4; i >= 0; i --){
            sAsInt.add(Integer.valueOf(s[i])) ;
        }

        return sAsInt;
    }

    
    //end game display when hp = 0
    public void end()throws IOException{
        BufferStrategy bs = this.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        
        
        
        handler.object.clear();
        handler.enemy.clear();
        handler.items.clear();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.RED);
        g.setFont(new Font("Serif",Font.BOLD,100));
        g.drawString("Game Over",280,300);
        g.setFont(new Font("Serif",Font.BOLD,60));
        g.setColor(Color.WHITE);
        g.drawString("Score: "+p.getScore(),397,400);
        
        System.out.println("saving score...");
        System.out.println("final score: "+p.getScore());
        ArrayList<Integer> scores = saveScore(p.getScore());
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Serif",Font.BOLD,40));
        g.drawString("Highscores:",410,460);
        g.setFont(new Font("Serif",Font.BOLD,30));
        g.setColor(Color.WHITE);
        for(int i = 0; i < 5; i ++){
            g.drawString(String.valueOf(scores.get(i)),440,500+i*30);
        }
        g.setColor(Color.BLUE);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        g.drawRect(400,420, 216, 250);
        g.setColor(Color.GREEN);
        g2.drawRect(260,220,540,110);
        
        
    }

    //calculates distances between points
    public static double dist(float x1, float y1, float x2, float y2){
        double distance = Math.hypot(x1-x2, y1-y2);
        return distance;
    }

    //main method
    public static void main()throws InterruptedException{
        new Window(WIDTH, HEIGHT, "test game", new Game());

    }
}
