package javagame;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Enemy {

	int health;
	int strength;
	String name;
	boolean active = true;
	private Vector2f pos;
	private Vector2f speed;
	private Circle enemyShape;
	private int size;
	private Color enemyColor;
	
	public Enemy(String name, Vector2f pos, Vector2f speed) throws SlickException{
		this.name = name;
		this.pos = pos;
		this.speed = speed;
		
		if (this.getName().equals("Grunt")){
			this.health = 13;
			this.strength = 14;
			this.size = 14;
			this.enemyColor = Color.red;
			enemyShape = new Circle(pos.getX(), pos.getY(), size);
		}
		else if (this.getName().equals("Elite")){
			this.health = 47;
			this.strength = 19;
			this.size = 17;
			this.enemyColor = Color.yellow;
			enemyShape = new Circle(pos.getX(), pos.getY(), size);
		}
		else if (this.getName().equals("SuperElite")){
			this.health = 267;
			this.strength = 29;
			this.size = 20;
			this.enemyColor = Color.cyan;
			enemyShape = new Circle(pos.getX(), pos.getY(), size);
		}
		else if (this.getName().equals("Mega")){
			this.health = 375;
			this.strength = 45;
			this.size = 40;
			this.enemyColor = Color.pink;
			enemyShape = new Circle(pos.getX(), pos.getY(), size);
			
		}
		else if (this.getName().equals("MegaElite")){
			this.health = 460;
			this.strength = 55;
			this.size = 65;
			this.enemyColor = Color.magenta;
			enemyShape = new Circle(pos.getX(), pos.getY(), size);
		}
		else if (this.getName().equals("SuperMega")){
			this.health = 660;
			this.strength = 55;
			this.size = 115;
			this.enemyColor = Color.green;
			enemyShape = new Circle(pos.getX(), pos.getY(), size);
		}
	}
	
	public Enemy() throws SlickException{
		
		 active = false;
		 
	}
	
	public void update(int i){
		
		if (active){
			Vector2f realSpeed = speed.copy();
			realSpeed.scale( (i/1000.0f) );
			pos.add(realSpeed);
			
			
			if (this.pos.getX() > 750)
				this.speed = new Vector2f( - this.speed.getX(), this.speed.getY());
			if (this.pos.getX() < 30)
				this.speed = new Vector2f( -1 * this.speed.getX() + 1, this.speed.getY());
			if (this.pos.getY() < 100)
				this.speed = new Vector2f(this.speed.getX(), - this.speed.getY());
			if (this.pos.getY() > 750)
				this.speed = new Vector2f(this.speed.getX(), -1 * this.speed.getY() + 1);
			
			if (this.getHealth() <= 0){
				Play.currentOnScreenEnemies--;
				Play.incrementEnemiesDefeated();
				active = false;
			}
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		if (active){
			
			if (this.getName().equals("Grunt")){
				g.setColor(enemyColor);
				enemyShape = new Circle(pos.getX(), pos.getY(), size);
				g.draw(enemyShape);
			}
			
			if (this.getName().equals("Elite")){
				g.setColor(enemyColor);
				enemyShape = new Circle(pos.getX(), pos.getY(), size);
				g.draw(enemyShape);
			}
			
			if (this.getName().equals("SuperElite")){
				g.setColor(enemyColor);
				enemyShape = new Circle(pos.getX(), pos.getY(), size);
				g.draw(enemyShape);
			}
			
			if (this.getName().equals("Mega")){
				g.setColor(enemyColor);
				enemyShape = new Circle(pos.getX(), pos.getY(), size);
				g.draw(enemyShape);
			}
			if (this.getName().equals("MegaElite")){
				g.setColor(enemyColor);
				enemyShape = new Circle(pos.getX(), pos.getY(), size);
				g.draw(enemyShape);
			}
			if (this.getName().equals("SuperMega")){
				g.setColor(enemyColor);
				enemyShape = new Circle(pos.getX(), pos.getY(), 30);
				g.draw(enemyShape);
			}
		}
    }
	
	public void applyDamage(int damage){
		
		this.health -= damage;
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
	public boolean isActive(){
		return this.active;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Vector2f getPos(){
		return this.pos;
	}
	
	public Circle getCircle(){
		return this.enemyShape;
	}
	
	public int getStrength(){
		return this.strength;
	}
	
	public void reduceSize(){
		this.size -= 5;
	}
	
	public Color getColor(){
		return enemyColor;
	}
}
