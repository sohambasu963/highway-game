import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PoliceCar here.
 * 
 * @author Soham Basu 
 * @version 1.0
 */
public class PoliceCar extends Car
{
    //instance variables
    Highway currentWorld;
    /**
     * Constructor for Police Car class
     */
    public PoliceCar() {
        super();
        setRotation(270); //sets rotation so that car is facing upwards when game starts
    }
    /**
     * Act - do whatever the PoliceCar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() { 
        //calling world
        currentWorld = (Highway) getWorld();
        if (getWorld().getObjects(ControlledCar.class).isEmpty()) {
            return; 
        }
        //getting the position of the user's car
        Actor car = (Actor)getWorld().getObjects(ControlledCar.class).get(0);
        if (currentWorld.startTime <= 60) { //once game has started
            //chase the car at a speed of 3
            turnTowards(car.getX(), car.getY());
            speed = 3;
            move((int)speed);
            //10 seconds into the game, chase the car at a speed of 5
            if (currentWorld.gameTime >= 10*60) {
                speed = 5;
                move((int)speed);
            }
             //20 seconds into the game, chase the car at a speed of 10
            else if (currentWorld.gameTime >= 20*60) {
                speed = 10;
                move((int)speed);
            }
        }

        }
}