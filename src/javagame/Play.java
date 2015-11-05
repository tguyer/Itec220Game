package javagame;



import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.*;
/**
 *
 * @author Travis Guyer
 * 
 * Play.java -- contains the "meat and bones" of the game. Due to the nature of using the slick libraries,
 * 				the update method is rather beefy.
 */
public class Play extends BasicGameState {

	Player player = new Player();
	playerHitbox hitbox;
	
	private Enemy[] enemies;
	private int currentEnemy = 0;
	private int deltaEnemy = 495;
	private static int SPAWN_RATE = 500;
	private String enemyHealth = "";
	
	private int abilityDelta = 0;
	
	private Bullet[] bullets;
	private static int FIRE_RATE = 200;
	private int current = 0;
	private int delta = 0;
	
	private Bullet[] enemyBullets;
	private int enemyFireRate = 500;
	private int enemyCurrentBullet = 0;
	private int enemyDeltaBullet = 0;
	private int shootingEnemy;
	
	private final int MAX_ONSCREEN_ENEMIES = 20;
	protected static int currentOnScreenEnemies = 0;
	private static int enemiesDefeated;
	Random spawner = new Random();
	
	int gruntUpper = 700;
	int eliteLower = 700;
	int eliteUpper = 850;
	int superEliteLower = 850;
	int superEliteUpper = 930;
	int megaLower = 930;
	int megaUpper = 960;
	int megaEliteLower = 960;
	int megaEliteUpper = 980;
	int superMegaLower = 980;
	
	private int score;
	int numbullets;
	int gameTime;
	
	String name;
	
	Image ship;
	
	String info;
	
	static double shipx = 375;
	static double shipy = 700;
	
	static double enemyX;
	static double enemyY;
	
    public Play(int state){
        
    }
    
    
    public int getID() {
        return 1;
    }

    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	ship = new Image("res/ship1.png");
    	hitbox = new playerHitbox(new Vector2f((float)this.getShipX(), (float)this.getShipY()));
    	
    	bullets = new Bullet[10];
    	for (int i = 0; i < bullets.length; i++){
    		bullets[i] = new Bullet();
    	}
    	
    	enemies = new Enemy[5000];
    	for (int i = 0; i < enemies.length; i++){
    		enemies[i] = new Enemy();
    	}
    	
    	enemyBullets = new Bullet[20];
    	for (int i = 0; i < enemyBullets.length; i++){
    		enemyBullets[i] = new Bullet();
    	}
    }

    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	
    	
    	g.drawImage(ship, (int) shipx, (int) shipy);
    	
    	
    
    	g.drawString(info, 20, 810);
    	g.drawString(enemyHealth,(float) enemyX, (float) enemyY);
    	
    	for (Bullet b: bullets){
    		b.render(gc, g);
    	}
    	
    	for (Enemy e: enemies){
    		e.render(gc, g);
    	}
    	
    	for (Bullet b: enemyBullets){
    		b.render(gc, g);
    	}
    }

    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    	Input input = gc.getInput();
    	hitbox = new playerHitbox(new Vector2f((float)this.getShipX(), (float)this.getShipY()));
    	
    	gameTime += i;
    	delta += i;
    	
    	//Enables the player to shoot bullets
    	if (delta > FIRE_RATE && (input.isKeyDown(Input.KEY_SPACE))){
    		bullets[current] = new Bullet(new Vector2f((int)shipx,(int)shipy), new Vector2f(0,-1000));
    		current++;
    		delta = 0;
    		if (current >= bullets.length){
    			current = 0;
    		}
    		
    	}
    	
    	enemyDeltaBullet += i;
    	
    	//Enables enemies to shoot bullets
    	if (enemyDeltaBullet % enemyFireRate == 0 || enemyDeltaBullet % enemyFireRate == 5 || enemyDeltaBullet % enemyFireRate == 3 || enemyDeltaBullet % enemyFireRate == 4){
    		
    		shootingEnemy = spawner.nextInt(20) + enemiesDefeated;
    		enemyX = enemies[shootingEnemy].getPos().getX() - 20;
    		enemyY = enemies[shootingEnemy].getPos().getY() + 20;
    		
    		if (enemies[shootingEnemy].isActive()){
    			enemyBullets[enemyCurrentBullet] = new Bullet(new Vector2f((int)enemyX,(int)enemyY), new Vector2f(0,500), enemies[shootingEnemy].getColor());
    			enemyCurrentBullet++;
    			numbullets++;
    			
    		if (enemyCurrentBullet >= enemyBullets.length){
    			enemyCurrentBullet = 0;
    			enemyDeltaBullet = 0;
    		}
    	}
    		
    }
    	
    	/* This block manages the spawning of enemies, as well as determines their direction of travel.
    	 * Also, this block of code ensures that the player will never run out of enemies to defeat :).
    	 */
    	
    	if (i >= 30000 && gameTime < 60000){
    		gruntUpper = 500;
    		eliteLower = 500;
    		eliteUpper = 800;
    		superEliteLower = 800;
    		superEliteUpper = 930;
    		megaLower = 930;
    		megaUpper = 960;
    		megaEliteLower = 960;
    		megaEliteUpper = 980;
    		superMegaLower = 980;
    	}
    	else if (gameTime >= 60000 && gameTime < 90000){
    		gruntUpper = 300;
    		eliteLower = 300;
    		eliteUpper = 600;
    		superEliteLower = 600;
    		superEliteUpper = 830;
    		megaLower = 830;
    		megaUpper = 960;
    		megaEliteLower = 960;
    		megaEliteUpper = 980;
    		superMegaLower = 980;
    	}
    	else if (gameTime >= 90000 && gameTime < 120000){
    		gruntUpper = 100;
    		eliteLower = 100;
    		eliteUpper = 200;
    		superEliteLower = 200;
    		superEliteUpper = 530;
    		megaLower = 530;
    		megaUpper = 760;
    		megaEliteLower = 760;
    		megaEliteUpper = 980;
    		superMegaLower = 980;
    	}
    	else if (gameTime >= 120000){
    		gruntUpper = 0;
    		eliteLower = 0;
    		eliteUpper = 200;
    		superEliteLower = 200;
    		superEliteUpper = 300;
    		megaLower = 300;
    		megaUpper = 500;
    		megaEliteLower = 500;
    		megaEliteUpper = 700;
    		superMegaLower = 700;
    	}
    	
    	deltaEnemy += i;
    	while (currentOnScreenEnemies < MAX_ONSCREEN_ENEMIES){
    		
    		int enemyChoice = spawner.nextInt(1000) + 1;
    		
    		//Determining which type of enemy will spawn based on the string parameter that is passed to the constructor
    		if (enemyChoice <= gruntUpper)
    			name = "Grunt";
    		if (enemyChoice > eliteLower && enemyChoice < eliteUpper)
    			name = "Elite";
    		if (enemyChoice >= superEliteLower && enemyChoice < superEliteUpper)
    			name = "SuperElite";
    		if (enemyChoice >= megaLower && enemyChoice < megaUpper)
    			name = "Mega";
    		if (enemyChoice >= megaEliteLower && enemyChoice < megaEliteUpper)
    			name = "MegaElite";
    		if (enemyChoice >= superMegaLower)
    			name = "SuperMega";
    		
    		
    		//Initialize random generators that will determine spawn location
    		int spawnX = spawner.nextInt(735) + 14;
    		int spawnY = spawner.nextInt(400);
    		
    		int direction = spawner.nextInt(2)+1;
    		int directionX = 0;
    		
    		//Ensures that not all of the enemies will spawn heading in the same direction
    		if (direction == 1)
    			directionX = spawner.nextInt(120) + 1;
    		else if (direction == 2)
    			directionX = - spawner.nextInt(120) + 1;
    		
    		int directionY = spawner.nextInt(30) + 1;		
    		
    		//Instantiates each enemy and sets them as as active on the Game Container
    		enemies[currentEnemy] = new Enemy(name, new Vector2f(spawnX, spawnY), new Vector2f(directionX, directionY));
    		currentOnScreenEnemies++;
    		currentEnemy++;
    		
    			
    	}
    		
    	for (Enemy e: enemies){
    		if (e.isActive())
    			e.update(i);
    	}
    	
    	
    	for (Bullet b: bullets){
    		b.update(i);
    	
    	}
    	
    	for (Bullet b: enemyBullets){
    		b.update(i);
    	}
    	
    	
    	abilityDelta += i;    	
    	
    	//Control the input being taken from the keyboard
         if (input.isKeyDown(Input.KEY_UP) && shipy > 130){
         	shipy -= player.getSpeed();
         }
         if (input.isKeyDown(Input.KEY_DOWN) && shipy < 750){
         	shipy += player.getSpeed();
         }
         if (input.isKeyDown(Input.KEY_LEFT) && shipx > 15){
         	shipx -= player.getSpeed();
         }
         if (input.isKeyDown(Input.KEY_RIGHT) && shipx < 735){
         	shipx += player.getSpeed();
         }
         if (input.isKeyPressed(Input.KEY_V)){
        	 if (abilityDelta > 20000){
        		 player.useAbility();
        		 abilityDelta = 0;
        	 }
         }
         if (input.isKeyPressed(Input.KEY_1)){
        	 player.changeShip(1);
        	 ship = new Image("res/ship1.png");
         }
         if (input.isKeyPressed(Input.KEY_2)){
        	 player.changeShip(2);
        	 ship = new Image ("res/ship2.png");
         }
         if (input.isKeyPressed(Input.KEY_3)){
        	 player.changeShip(3);
        	 ship = new Image ("res/ship3.png");
         }
         if (input.isKeyPressed(Input.KEY_4)){
        	 player.changeShip(4);
        	 ship = new Image("res/ship4.png");
         }
         if (input.isKeyPressed(Input.KEY_5)){
        	 player.changeShip(5);
        	 ship = new Image ("res/ship5.png");
         }
         if (input.isKeyPressed(Input.KEY_6)){
        	 player.changeShip(6);
        	 ship = new Image ("res/ship6.png");
         }
         if (input.isKeyPressed(Input.KEY_ESCAPE)){
        	 sbg.enterState(0);
         }
         
         //Collision detection. Checks all bullets and all enemies and sees if they intersect
         for (Bullet b: bullets){
     		for (Enemy e: enemies){
     			if (e.isActive() && b.isActive()){
     				if (e.getCircle().intersects(b.getRect())){
     					
     					b.setActive(false);
     					e.applyDamage(player.getStrength());
     					player.addPassiveHeal(player.getStrength() * 0.1);
     					score += player.getStrength();
     				}
     			}
     		}
     	}
         
       /* Collision detection. Checks all enemy bullets to see if they collide with the player hit box.
        * Furthermore, checks to see which enemy shot the bullet, so that the appropriate damage can be 
        * applied to the player.
        */
         for (Bullet b: enemyBullets){
      			if (b.isActive() && hitbox.isActive()){
      				if (b.getRect().intersects(hitbox.getCircle())){
      					int shooter = b.getShooterID();
      					
      					b.setActive(false);
      					player.applyDamage(enemies[shooter].getStrength());
      					
      				}
      			}
      		} 
         
         /*
          * Checks to see if an enemy has run into the players hit box. If so, subtracts that enemies
          * remaining health from the players own.
          */
         for (Enemy e: enemies){
   			if (e.isActive() && hitbox.isActive()){
   				if (e.getCircle().intersects(hitbox.getCircle())){
   					
   					e.setActive(false);
   					player.applyDamage(e.getHealth());
   					
   				}
   			}
   		} 
         
         if (abilityDelta > 20000)
        	 info = "Health: " + player.getHealth() + " --- Speed: " + player.getSpeed() + " --- Armor: " + player.getArmor() + " --- Heal Available: " + player.getPassiveHeal() + " --- Score: " + this.score;
         else
        	 info = "Health: " + player.getHealth() + " --- Speed: " + player.getSpeed() + " --- Armor: " + player.getArmor() + " --- Heal NOT AVAILABLE" + " --- Score: " + this.score;
         
         if (player.getHealth() <= 0){
        	sbg.enterState(0);
         }
    }
    public static double getShipX(){
    	return shipx;
    }
    public static double getShipY(){
    	return shipy;
    }
    
    public String getShooterName(int ID){
    	return enemies[ID].getName();
    }
    
    public static void incrementEnemiesDefeated(){
    	enemiesDefeated++;
    }
    
}