import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * 
 */
public class KeyInput extends KeyAdapter
{
    Handler handler;
    //initialisation
    public KeyInput(Handler handler){
        this.handler=handler;
    }

    //checks if certain keys are pressed
    //and calls certain methods
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i ++){
            GameObject tempObject = handler.object.get(i);
            if(key == KeyEvent.VK_D)Game.p.setVelX(7);
            if(key == KeyEvent.VK_A)Game.p.setVelX(-7);
            if(key == KeyEvent.VK_S)Game.p.setVelY(7);
            if(key == KeyEvent.VK_W)Game.p.setVelY(-7);

            if(tempObject.getId() == Player.ID){


                //select item
                if(key == KeyEvent.VK_1){
                    (Game.p).selectItem(1);
                }
                if(key == KeyEvent.VK_2){
                    (Game.p).selectItem(2);
                }
                if(key == KeyEvent.VK_3){
                    (Game.p).selectItem(3);
                }

                //item 1
                if((Game.p).getSelectedItem()==1&&(Game.p).inCombat()==true){
                    if(Bullet.bulletTicks>=80){
                        if(key == KeyEvent.VK_RIGHT){
                            handler.createBullet(1);
                            Bullet.bulletTicks=0;
                        }
                        if(key == KeyEvent.VK_LEFT){
                            handler.createBullet(2);
                            Bullet.bulletTicks=0;
                        }
                        if(key == KeyEvent.VK_DOWN){
                            handler.createBullet(3);
                            Bullet.bulletTicks=0;
                        }
                        if(key == KeyEvent.VK_UP){
                            handler.createBullet(4);
                            Bullet.bulletTicks=0;
                        }

                    }
                }
                
                
                //item 2
                if((Game.p).getSelectedItem()==2&&(Game.p).inCombat()==true){
                    if(Bullet.bulletTicks>=50){
                        if(key == KeyEvent.VK_RIGHT){
                            handler.createBullet(5);
                            Bullet.bulletTicks=0;
                        }
                        if(key == KeyEvent.VK_LEFT){
                            handler.createBullet(6);
                            Bullet.bulletTicks=0;
                        }
                        if(key == KeyEvent.VK_DOWN){
                            handler.createBullet(7);
                            Bullet.bulletTicks=0;
                        }
                        if(key == KeyEvent.VK_UP){
                            handler.createBullet(8);
                            Bullet.bulletTicks=0;
                        }

                    }
                }
                
                
                
                
                
                
                


            }

        }

        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i ++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == Player.ID){

                if(key == KeyEvent.VK_D)tempObject.setVelX(0);
                if(key == KeyEvent.VK_A)tempObject.setVelX(0);
                if(key == KeyEvent.VK_S)tempObject.setVelY(0);
                if(key == KeyEvent.VK_W)tempObject.setVelY(0);
                
                
                
                
            }
        }
    }

}
