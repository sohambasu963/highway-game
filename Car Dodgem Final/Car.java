import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    
/**
* Write a description of class Car here.
* 
* @author Soham Basu 
* @version 1.0
*/
public class Car extends Actor
{
    //instance variables
    String licensePlate;
    double speed;
    double maxSpeed;
    
    /**
     * Act - do whatever the Car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Car() 
    {
        speed = 0.0;
        maxSpeed = 30.0;
    }  
    
    /**
     * Car reswpawns on opposite end of the world if it reaches the edge of the world
     */
    protected void wrap() {
       World world = getWorld(); //getting the World
       int x = getX(); //get the x-coordinate of the car
       int y = getY(); //get the y-coordinate of the car
       int r = getRotation(); //get which way the car is facing
       if (x == 1017 && (r > 270||r < 90)) {
          x = 0;           } //If car is moving right and reaches edge of world
       else if (x == 0 && (r > 90||r < 270)) {
           x = 1017;        } //If car is moving left and reaches edge of world
       else if (y == 0 && (r > 0||r < 180)) {
           y = 599;        } //If car is moving up and reaches edge of world
       else if (y == 599 && (r > 0||r < 180)) {
           y = 0;          } //If car is moving down and reaches edge of world
       setLocation(x,y); //setting the new location
}
   

    //Setters
    /**
     * Car accelerates or decelerates based on user input
     */
    protected void accelerate(double speedChange) {
        speed = speed + speedChange;
        if (speed < 0.0) {
            speed = 0.0; }
        else if (speed > maxSpeed) {
            speed = maxSpeed; }
        }
    /**
     * Sets the speed of the car
     */    
    protected void setSpeed(double speed) { //setting the speed variable
        if (speed <= maxSpeed) { //if speed is less than or equal to maxSpeed, intialize speed
            this.speed = speed; }
        else{
            speed = 0.0; }
        }
    /**
     * Sets the max speed of the car
     */
    protected void setMaxSpeed(double maxSpeed) { //setting the maxSpeed variable
        if (maxSpeed >= 0) { //if max speed is greater than or equal to 0, intialize maxSpeed
            this.maxSpeed = maxSpeed; }
        else {
            maxSpeed = 0.0; }
        }
    /**
     * Gets the speed of the car
     */
    protected double getSpeed() { //gets the speed
        return speed; } 
    /**
     * Gets the max speed of the car
     */
    protected double getMaxSpeed() { //gets the maxSpeed
        return maxSpeed; }

}
