package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
/**
 * eroes of the sky is a space arcade game that players will find familiar.
 * 
 * @author Travis Guyer
 * @version 1.0
 */
public class HeroesoftheSkyDriver extends StateBasedGame {
    
	public static final String gameName = "Heroes of the Sky";
    public static final int menu = 0;
    public static final int play = 1;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AppGameContainer appgc;
        
        try{
            appgc = new AppGameContainer(new HeroesoftheSkyDriver(gameName));
            appgc.setDisplayMode(800,850,false);
            appgc.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
        
    }

    /**
     * 
     * @param gameName
     * 
     * Constructor for the game. Calls the parent class and passes the game name.
     */
    public HeroesoftheSkyDriver(String gameName) {
        super(gameName);
        
        this.addState(new Menu(menu));
        this.addState(new Play(play));
    }
   
    /**
    * This method creates the individual states of the game. My game will only require two states,
    * the Play state and the Menu state.
    */
    public void initStatesList(GameContainer gc) throws SlickException {
        
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.enterState(menu);
    }
    
}