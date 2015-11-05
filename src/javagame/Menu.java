package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
/**
 *
 * @author Travis
 * 
 * Menu.java -- Models the initial state of the game upon starting it up. Provides information
 * 				about controls, and instructs players to click anywhere on the screen to begin playing.
 */
public class Menu extends BasicGameState{
    
	
	
    public Menu(int state){
        
    }

    
    public int getID() {
        return 0;
    }

    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Welcome to Heroes of the Sky!", 220, 100);
    	g.drawString("The controls: \n\nUP, RIGHT, LEFT, DOWN arrows will steer your craft\nin the appropriate direction " +
    			"\n\n SPACEBAR to fry your enemies!\n\n V will use your special ability: HEALING" , 220, 200);
    	g.drawString("Click anywhere on this screen to play\n\n\n version: 1.0", 220, 700);
    	
    }

   
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
       Input input = gc.getInput();
       if (input.isMouseButtonDown(0))
    	   sbg.enterState(1);
    }
    
}