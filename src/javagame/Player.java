package javagame;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import java.util.Timer;

/**
 *
 * @author Travis
 */
public class Player {
    
   private int health; // Player Health
   private int strength; // How much damage a player deals
   private double speed; // How fast a player will move across the screen
   private int armor; // How much damage reduction a player will have
   private int ability = 1; //Right now, all ships have the same ability. May change this in the future
   private int ship = 1; // Ship 1 is the default 
   
   private double passiveHeal = 0; // How much a player can heal upon using their ability
   
   
    
    public Player(){
        
        this.health = 200;
        this.strength = 10;
        this.speed = 0.5;
        this.armor = 3;
        
    }
    
 
    protected void applyDamage(int damage){
        
        this.health -= (damage - this.armor);
        
    }
    
    protected void changeShip(int ship){
        
    	this.ship = ship;
    	
        if (ship == 1){
            
            //This is the default ship.
            this.strength = 9;
            this.speed = 0.5;
            this.armor = 5;
            this.ability = 1;

        }
        
        else if (ship == 2){
            
            //This ship will model Donnie.
            this.strength = 7;
            this.speed = 0.75;
            this.armor = 4;
            this.ability = 1;
            
        }
        
        else if (ship == 3){
            
            //This ship will model Shawn.
            this.strength = 5;
            this.speed = 0.8;
            this.armor = 4;
            this.ability = 1;
            
        }
        
        else if (ship == 4){
            
            //This ship will model Devin.
            this.strength = 13;
            this.speed = 0.1;
            this.armor = 11;
            this.ability = 1;
            
        }
        
        else if (ship == 5){
            
            //This ship will model Cole.
            this.strength = 9;
            this.speed = 0.6;
            this.armor = 5;
            this.ability = 1;
            
        }
        
        else if (ship == 6){
            
            //This ship will model Alex.
            this.strength = 7;
            this.speed = 0.35;
            this.armor = 4;
            this.ability = 1;
        }
    }
    
    protected void useAbility(){
        
        if (this.ability == 1){
            
            /*
             * In future versions, each ship will have a different ability. However, for now this will be it. Players
             * passively gain an amount of health that they can heal based on how much damage they have dealt. Activating
             * this ability will heal for that amount.
             */
        	this.health += (int) passiveHeal;	 
        	this.passiveHeal = 0;
        }
        
        else if (this.ability == 2){
            
            
        }
        
        else if (this.ability == 3){
            
            
        }
        
        else if (this.ability == 4){
            
            //Devin's ability will be to sharply increase armor and slightly increase speed
            
        }
        
        else if (this.ability == 5){
            
        	//Cole's ability is to double all stats for a period of time
        	this.strength = 18;
        	this.speed = 1.2;
        	this.armor = 4;
            
        }
        
        else if (this.ability == 6){
            
        	//Alex's ability will be to become invincible, and heal for a portion of the damage that would have been originally dealt
            
        }
    }
    
    public int getHealth(){
        
        return this.health;
    }
    
    
    public double getSpeed(){
    	return this.speed;
    }
    
    public int getArmor(){
    	return this.armor;
    }
    
    public int getStrength(){
    	return this.strength;
    }

    public void addPassiveHeal(double heal){
    	this.passiveHeal += heal;
    }
    
    public int getPassiveHeal(){
    	return (int) this.passiveHeal;
    }
    
}