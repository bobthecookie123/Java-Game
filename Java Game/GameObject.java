import java.util.ArrayList;
import java.awt.Graphics;
import java.io.*;

/**
 * for game objects ex: player, mobs, etc
 */
public abstract class GameObject
{
    protected float x, y;
    protected int id;
    protected float velX=0, velY=0;

    // each object has a position, velocity, and appearance
    public GameObject(float x, float y, int id){
        this.x=x;
        this.y=y;
        this.id=id;
        
    }

    public abstract void tick(ArrayList<GameObject> object);
    public abstract void render(Graphics g);
    //public abstract Rectangle getBounds();

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
