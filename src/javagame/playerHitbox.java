package javagame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class playerHitbox {

	private Shape playerHitbox;
	private boolean active = true;
	private Vector2f pos;
	
	public playerHitbox(Vector2f pos){
		
		this.pos = pos;
		playerHitbox = new Circle((float) Play.getShipX() + 30, (float) Play.getShipY() + 30, 26);
	}
	
	public void update(int i){
		
			if (active){
				this.pos.set((float)Play.getShipX(), (float)Play.getShipY());
			}
		}
	
		public void render(GameContainer gc, Graphics f) throws SlickException {
		
		
			if (active){
				this.playerHitbox = new Circle((float) Play.getShipX() + 30, (float) Play.getShipY() + 30, 26);
				
			}
		}
		
		public Circle getCircle(){
			return (Circle) this.playerHitbox;
		}
		
		public boolean isActive(){
			return this.active;
		}
	}
