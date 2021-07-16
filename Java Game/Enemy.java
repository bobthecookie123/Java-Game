import java.util.ArrayList;
import java.awt.*;
import java.util.*;

/**
 * 
 */
public class Enemy extends GameObject
{
    private int hp;
    private String name;
    private boolean inBounds = true;

    private int size = 50;

    private static Random rand = new Random();
    public Enemy(float x, float y, int id,int h){
        super(x, y, id);

        hp=h;

        velX=random_int(5,8);
        velY=random_int(5,8);

    }
    public static int random_int(int Min, int Max)
    {
        return (int) (Math.random()*(Max-Min))+Min;
    }

    public void tick(ArrayList<GameObject> object){
        bounce();
        x+=velX;
        y+=velY;

        
    }

    public void render(Graphics g){
        g.setColor(Color.BLUE);
        if(hp==3)
            g.setColor(Color.GREEN);

        if(hp==2)
            g.setColor(Color.YELLOW);

        if(hp==1)
            g.setColor(Color.RED);

        g.fillOval((int)x,(int)y, size, size);

        //collisions with bullets
        ArrayList<Bullet> bullets = Handler.getBullets();

        for(int i = 0; i < bullets.size(); i ++){
            Bullet temp = bullets.get(i);

            double dist = Game.dist(x,y, temp.getX(),temp.getY());
            if(dist<=40){
                damage(temp.getDamage());
                Handler.removeObject(temp);
                Handler.removeBullet(temp);

                g.setColor(Color.ORANGE);
                g.fillOval((int)x,(int)y, size, size);

            }
        }
        //remove if hp = 0;
        if(hp==0){

            Handler.removeObject(this);
            Handler.removeEnemy(this);
            Game.p.addScore(1);
        }

    }

    public void bounce(){
        if(getCenteredX()<=size/2){
            velX=-velX;
        }
        if(getCenteredX()>=Game.WIDTH-size/2){
            velX=-velX;
        }
        if(getCenteredY()<=size/2){
            velY=-velY;
        }
        if(getCenteredY()>=Game.HEIGHT-size/2){
            velY=-velY;
        }
    }

    public float getCenteredX(){
        return x+size/2;   
    }

    public float getCenteredY(){
        return y+size/2;
    }

    public int getHp(){
        return hp;
    }

    public void damage(int dmg){
        hp-=dmg;
    }

}
