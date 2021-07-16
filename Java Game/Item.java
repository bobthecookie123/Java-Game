import java.util.ArrayList;
import java.awt.Graphics;

/**
 * player items/weapons
 */
public abstract class Item
{
    private boolean itemForm = false;
    private boolean equipped = false;
    private boolean unlocked = false;
    private boolean inInventory = false;
    
    private String name = "";
    
    private int slotNum = 0;
    
    private int dispX = 0;
    private int dispY = 0;
    
    public Item(String n){
        name = n;
    }
    public String getName(){
        return name;
    }
    public abstract void tick(ArrayList<GameObject> object);
    public abstract void render(Graphics g);
    
    
   
    
    
}
