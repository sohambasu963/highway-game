import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TrafficCar here.
 * 
 * @author Soham Basu 
 * @version 1.0
 */
public class TrafficCar extends Car
{
    //instance variables
    Highway currentWorld;
    
    /**
     * Constructor for Traffic Car Class
     */
    public TrafficCar() {
        super();
        setRotation(270); //rotation is set so that cars face upwards when game starts
    }
    /**
     * Act - do whatever the TrafficCar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //calling the World
        currentWorld = (Highway) getWorld();
        //setting speed to 3
        speed = 3; 
        move((int)speed); 
        //traffic cars will be able to wrap around edges
        wrap();
        
        //halfway through the game, all traffic cars will turn in the same random direction
        if (currentWorld.gameTime == 15*60) {
            int[] rotations = {0,90,180};
            int index = Greenfoot.getRandomNumber(2);
            setRotation(rotations[index]);
        }
    }    
}