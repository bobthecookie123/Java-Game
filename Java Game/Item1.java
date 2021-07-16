import java.util.ArrayList;
import java.awt.Graphics;
/**
 * 
 */
public class Item1 extends Item
{
    private boolean itemForm = false;
    private boolean equipped = false;
    private boolean unlocked = false;
    private boolean inInventory = false;
    
    private String name = "";
    
    private int slotNum = 1;
    
    private int dispX = 0;
    private int dispY = 0;
    public Item1(String n){
        super(n);
        
    }
    public void tick(ArrayList<GameObject> object){
        
    }
    public void render(Graphics g){
        
    }
    
}
