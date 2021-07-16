import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
/**
 * 
 */
public class Ball extends GameObject
{
    public Ball(float x, float y, int id){
        super(x, y, id);
    }
    
    public void tick(ArrayList<GameObject> object){
        
    }
    public void render(Graphics g){
        g.setColor(Color.red);
        g.drawRect((int)x, (int)y, 32, 32);
        
    }
    
}
