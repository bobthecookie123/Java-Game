import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
/**
 * player bullets
 */
public class Bullet extends GameObject
{
    public static int size = 20;
    public static int bulletTicks = 0;
    private int dir;
    private boolean inBounds = true;
    private int damage = 1;
    public Bullet(float x, float y, int id, int dir){
        super(x, y, id);
        this.dir=dir;
    }

    public void tick(ArrayList<GameObject> object){
        x += velX;
        y += velY;
        if(x<=0||x>=Game.WIDTH||y<=0||y>=Game.HEIGHT){
            inBounds = false;
        }
        if(inBounds==false){
            /*velX=0;
            velY=0;
            x=9000;
            y=9000;
            */
           Handler.removeObject(this);
        }
        

    }

    public void render(Graphics g){
        g.setColor(new Color(200,0,255));
        g.fillOval((int)x, (int)y, size, size);

    }
    
    public boolean isInBounds(){
        return inBounds;
    }
    
    public int getDamage(){
        return damage;
    }

    public float getX(){
        return x;   
    };

    public float getY(){
        return y;
    };

    public void setX(float x){
        this.x=x;   
    };

    public void setY(float y){
        this.y=y;
    };

    public float getVelX(){
        return velX;
    };

    public float getVelY(){
        return velY;
    };

    public void setVelX(float velX){
        this.velX=velX;
    };

    public void setVelY(float velY){
        this.velY=velY; 
    };

    public int getId(){
        return id; 
    };

}
