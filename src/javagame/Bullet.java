package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.*;

public class Bullet {

	private Vector2f pos; // Position on the screen
	private Vector2f speed; // Speed and direction of which bullet is moving
	private int lived = 0; // How long the bullet has been active
	private Rectangle rectangle; // The rectangle for the bullet
	
	private Color bulletColor;
	private int shooterID; // The ID of the enemy which shot the bullet, in the case this is an enemies bullet.
	
	private boolean active = true; // Determines if the bullet is active on the screen or not
	private boolean enemyBullet;
	
	public static int MAX_LIFETIME = 2000; // How long the bullet is allowed to live
	
	/**
	 * 
	 * @param pos
	 * @param speed
	 * @throws SlickException
	 * 
	 * This is the constructor for an active friendly bullet
	 */
	public Bullet(Vector2f pos, Vector2f speed) throws SlickException{
		
		this.pos = pos;
		this.speed = speed;
		this.rectangle = new Rectangle(pos.getX() + 25 , pos.getY() - 25, 5, 20);
		enemyBullet = false;
		
	}
	
	/**
	 * 
	 * @param pos
	 * @param speed
	 * @param bulletColor
	 * @throws SlickException
	 * 
	 * This is the constructor for an active enemy bullet
	 */
	public Bullet(Vector2f pos, Vector2f speed, Color bulletColor) throws SlickException{
		
		this.pos = pos;
		this.speed = speed;
		this.rectangle = new Rectangle(pos.getX() + 25 , pos.getY() - 25, 5, 20);
		this.bulletColor = bulletColor;
		enemyBullet = true;
	}
	
	/**
	 * 
	 * @throws SlickException
	 * 
	 * Constructor for an inactive bullet
	 */
	public Bullet() throws SlickException{
		
		 active = false;
		 
	}
	
	/**
	 * 
	 * @param i
	 * 
	 * Updates the position of the bullet
	 */
	public void update(int i){
		
		if (active){
			Vector2f realSpeed = speed.copy();
			realSpeed.scale( (i/1000.0f) );
			pos.add(realSpeed);
			
			lived += i;
			
			if (lived > MAX_LIFETIME)
				active = false;
		}
	}
	
	/**
	 * 
	 * @param gc
	 * @param f
	 * @throws SlickException
	 * 
	 * Draws the bullet based on who fired it
	 */
	public void render(GameContainer gc, Graphics f) throws SlickException {
		
		
		if (active){
			this.rectangle = new Rectangle(pos.getX() + 25 , pos.getY() - 25, 7, 20);
			if (enemyBullet)
				f.setColor(bulletColor);
			else
				f.setColor(Color.blue);
			f.fill(rectangle);
			f.setColor(Color.white);
		}
    }
	
	public boolean isActive(){
		return this.active;
	}
	
	public Vector2f getPos(){
		return this.pos;
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
	public Rectangle getRect(){
		return this.rectangle;
	}
	
	public int getShooterID(){
		return this.shooterID;
	}
	
}
