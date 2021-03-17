import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author Soham Basu
 * @version 1.0
 */
public class Bomb extends Actor
{
    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //removes the bomb if user has run into it
        if (isTouching(ControlledCar.class)) {
            getWorld().removeObject(this);
        }
        //prevents the bomb from spawning on the health bar or timer and blocking them from view
        if (getWorld()!=null) {
            if (isTouching(HealthBar.class)||isTouching(Button.class)) {
                getWorld().removeObject(this);
            }  
        }
    }   
}