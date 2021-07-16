import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.io.*;
/**
 * 
 */
public class Player extends GameObject
{
    public static final int SIZE = 64;

    public static final int ID = 0;

    private int hp = 5;

    private boolean combat = false;

    private boolean damaged = false;
    private int dmgTicks = 0;

    private int score = 0;

    private int selectedItem = 1;

    private boolean showScores = false;

    private Item currentItem;

    public Item[] items = new Item[3];

    public Player(float x, float y, int id){
        super(x, y, id);

    }

    public void tick(ArrayList<GameObject> object){
        x += velX;
        y += velY;

        currentItem = items[selectedItem-1];

        if(dmgTicks<=30){
            dmgTicks++;
        }
        if(dmgTicks>=30){
            damaged=false;
            damageable();
        }

    }

    public void render(Graphics g){
        keepInWindow();
        //draw player
        g.setColor(new Color(240,238,0));
        g.fillOval((int)x, (int)y, SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.fillOval((int)x+12, (int)y+15, 15, 15);
        g.fillOval((int)x+37, (int)y+15, 15, 15);
        g.fillRect((int)x+15,(int)y+40,35,15);

        g.fillOval((int)x+12, (int)y+39, 15, 15);
        g.fillOval((int)x+37, (int)y+39, 15, 15);

        //item inventory
        if(combat==true){

            //selected item
            g.setColor(Color.YELLOW);
            if(selectedItem==1)
                g.fillRect(15,15,85,85);
            if(selectedItem==2)
                g.fillRect(115,15,85,85);
            if(selectedItem==3)
                g.fillRect(215,15,85,85);

            //item slots
            g.setColor(Color.GRAY);
            g.fillRect(20,20,75,75);                        
            g.fillRect(120,20,75,75);
            g.fillRect(220,20,75,75);

            //icon 1
            g.setColor(Color.BLACK);
            g.fillRect(25,40,65,12);
            Graphics2D g2 = (Graphics2D)g;
            Polygon p = new Polygon();
            p.addPoint(65,40);
            p.addPoint(80,40);
            p.addPoint(85,75);
            p.addPoint(72,75);            
            g2.fillPolygon(p);
            g2.setStroke(new BasicStroke(3));
            g2.drawOval(56,42,20,20);

            //icon 2
            g.setColor(Color.BLACK);
            g.fillRect(155, 33,32,22);
            g.drawOval(150,47,20,20);
            g.fillRect(133,36,60,20);
            g.fillRect(122,42,20,8);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(133,36,60,5);
            g.fillRect(133,51,60,5);
            g.setColor(Color.BLACK);
            g.fillRect(163,53,12,35);
            
            

            //player hp
            g.setColor(Color.DARK_GRAY);
            g.fillRect(645, 23, 350, 64);
            for(int i = 0; i < hp; i ++){
                g.setColor(Color.RED);
                g.fillRect(650+i*70,25,60,60);

            }
            for(int i = 5-hp; i > 0; i--){
                g.setColor(Color.BLACK);
                g.fillRect(1000-i*70,25,60,60);

            }

            //draw score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Serif",Font.BOLD,45));
            g.drawString("Score: "+score,410,70);

        }

        //check for collisions with enemies
        ArrayList<Enemy> enemy = Handler.getEnemy();

        for(int i = 0; i < enemy.size(); i ++){
            Enemy temp = enemy.get(i);

            double dist = Game.dist(x,y, temp.getX(),temp.getY());
            if(dist<=50&&damaged==false){
                hp--;
                damaged=true;
                dmgTicks=0;
            }
        }
        if(hp==0){
            Game.gameOver=true;
        }

        //show instructions
        if(Game.currentRoom==0){
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
    }
    
    public int getSelectedItemSlot(){
        return selectedItem;
    }
    
    public void enableScoreDisplay(){
        showScores=true;
    }

    public void disableScoreDisplay(){
        showScores=false;
    }

    public void addScore(int s){
        score+=s;
    }

    public int getScore(){
        return score;
    }

    public void resetHp(){
        hp=5; 
    }

    //makes player damageable after getting hit
    public void damageable(){
        damaged=false;
    }

    public boolean inCombat(){
        return combat;
    }

    public int getSelectedItem(){
        return selectedItem;
    }

    public void selectItem(int s){
        selectedItem = s;
    }

    public Item getCurrentItem(){
        return currentItem;
    }

    public void toggleCombat(){
        if(combat==true){
            combat=false;
        }
        if(combat==false){
            combat=true;
        }
    }

    public float getCenteredX(){
        return x+SIZE/2;   
    }

    public float getCenteredY(){
        return y+SIZE/2;
    }

    public void keepInWindow(){
        if(getCenteredX()<=SIZE/2){
            x=2;
        }
        if(getCenteredX()>=Game.WIDTH-SIZE/2){
            x=Game.WIDTH-60;
        }
        if(getCenteredY()<=SIZE/2){
            y=2;
        }
        if(getCenteredY()>=Game.HEIGHT-SIZE/2){
            y=Game.HEIGHT-60;
        }
    }
}
