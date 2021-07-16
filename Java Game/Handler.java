import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Random;
import java.io.*;
/**
 * 
 */
public class Handler
{
    public static ArrayList<GameObject> object = new ArrayList<GameObject>();

    public static ArrayList<Item> items = new ArrayList<Item>();

    public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    public static ArrayList<Enemy> enemy = new ArrayList<Enemy>();

    private static int enemyCount = 0;

    private static int highestIndex = 0;

    private static int bulletIndex = 0;

    private GameObject tempObject;

    private Item tempItem;

    private Random rand = new Random();

    //loops through all objects and runs tick methods
    public void tick(){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            tempObject.tick(object);
        }
    }

    //runs render methods which draw stuff
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            tempObject.render(g);
        }
        /*
        for(int j = 0; j < items.size(); j++){
            tempItem = items.get(j);
            tempItem.render(g);
        }
        */
    }

    //add item
    public void addItem(Item itm){
        this.items.add(itm);

    }

    //add object to array for ticking and rendering
    public void addObject(GameObject object){
        this.object.add(object);
        highestIndex++;
    }

    //remove object
    public static void removeObject(GameObject obj){
        object.remove(obj);
        highestIndex--;
    }

    //remove enemy object
    public static void removeEnemy(Enemy en){
        enemy.remove(en);
        enemyCount--;
    }

    //create 4 test objects at the start of the game
    public void createDoors1(){  //displays starting doors for menu, level selection, etc 
        //Test[] door1Arr = new Test[4];

        //addObject(new Test(0, Game.HEIGHT/2-Test.SIZE/2, ObjectId.getId(),"l1","Inventory"));//left

        //addObject(new Test(Game.WIDTH-Test.SIZE, Game.HEIGHT/2-Test.SIZE/2, ObjectId.getId(),"r1","Instructions"));//right

        addObject(new Test(Game.WIDTH/2-Test.SIZE/2, 0, ObjectId.getId(),"t1","PORTAL"));//top

        //addObject(new Test(Game.WIDTH/2-Test.SIZE/2, Game.HEIGHT-Test.SIZE, ObjectId.getId(),"b1","Scores"));//bottom

    }

    //create a player
    public void createPlayer(){
        addObject(new Player (500, 400, ObjectId.getId()));
    }

    //generate random int within a range
    public static int random_int(int Min, int Max)
    {
        return (int) (Math.random()*(Max-Min))+Min;
    }

    //method called if arrow keys are pressed
    //int parameter determines which way the bullet shoots
    public void createBullet(int dir){
        Bullet b;
        Player p = (Player)object.get(0);
        int offset =4;
        if(dir==5){//right
            b = new Bullet(p.getX()+20, p.getY()+20, ObjectId.getId(), 5);
            addObject(b);
            b.setVelX(20);
            b.setVelY(random_int(-offset,offset));
            bullets.add(b);
            bulletIndex=highestIndex;
        }
        if(dir==6){//left
            b = new Bullet(p.getX()+20, p.getY()+20, ObjectId.getId()+20, 6);
            addObject(b);
            b.setVelX(-20);
            b.setVelY(random_int(-offset,offset));
            bullets.add(b);
            bulletIndex=highestIndex;
        }
        if(dir==7){//down
            b = new Bullet(p.getX()+20, p.getY()+20, ObjectId.getId(), 7);
            addObject(b);
            b.setVelY(20);
            b.setVelX(random_int(-offset,offset));
            bullets.add(b);
            bulletIndex=highestIndex;
        }
        if(dir==8){//up
            b = new Bullet(p.getX()+20, p.getY()+20, ObjectId.getId(), 8);
            addObject(b);
            b.setVelY(-20);
            b.setVelX(random_int(-offset,offset));
            bullets.add(b);
            bulletIndex=highestIndex;
        }
        
        
        if(dir==1){//right
            b = new Bullet(p.getX()+20, p.getY()+20, ObjectId.getId(), 1);
            addObject(b);
            b.setVelX(20);
            bullets.add(b);
            bulletIndex=highestIndex;
        }
        if(dir==2){//left
            b = new Bullet(p.getX()+20, p.getY()+20, ObjectId.getId()+20, 2);
            addObject(b);
            b.setVelX(-20);
            bullets.add(b);
            bulletIndex=highestIndex;
        }
        if(dir==3){//down
            b = new Bullet(p.getX()+20, p.getY()+20, ObjectId.getId(), 3);
            addObject(b);
            b.setVelY(20);
            bullets.add(b);
            bulletIndex=highestIndex;
        }
        if(dir==4){//up
            b = new Bullet(p.getX()+20, p.getY()+20, ObjectId.getId(), 4);
            addObject(b);
            b.setVelY(-20);
            bullets.add(b);
            bulletIndex=highestIndex;
        }

    }

    //deletes bullets if they are out of bounds
    public void manageBullets(){
        Bullet temp;
        for(int i = 0; i < bullets.size(); i ++){
            temp = bullets.get(i);
            if(temp.isInBounds()==false){
                bullets.remove(temp);
                removeObject(temp);
            }

        }
    }

    //creates a set of enemies each round
    public void createEnemies(int r){

        for(int i = 0; i < r+4; i++){
            Enemy test = new Enemy(random_int(100,900),random_int(100,700),ObjectId.getId(),3);
            addObject(test);
            enemy.add(test);
            enemyCount++;
        }

    }

    
    //get number of enemies
    public int getEnemyCount(){
        return enemyCount;
    }

    //get array of active bullet projectiles
    public static ArrayList getBullets(){
        return bullets;
    }

    //delete bullet
    public static void removeBullet(Bullet b){
        bullets.remove(b);
    }

    //get array of active enemies
    public static ArrayList getEnemy(){
        return enemy;   
    }


}
