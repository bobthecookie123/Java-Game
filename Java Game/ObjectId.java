
/**
 * 
 */
public class ObjectId
{
    //list of ids:
    /*
     * player 0
     * 
     * starting room:
     *
     * 
     * 
     * 
     * 
     * 
     */
    
    
    
    
    public static int currentId = 0;
    
    public static int getId(){
        int id = currentId;
        currentId++;
        return id;
    }

}
