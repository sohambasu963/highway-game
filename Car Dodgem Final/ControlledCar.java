import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ControlledCar here.
 * 
 * @author Soham Basu 
 * @version 1.0
 */
public class ControlledCar extends Car
{
    //instance variables
    int health = 10000;
    Highway currentWorld;
    HealthBar bar = new HealthBar("You");
    int score;   
    //score is measured by adding the the health at the end of the game to the time survived in seconds multiplied by 100
    
    /**
     * Constructor for user controlled car
     */
    public ControlledCar(double maxSpeedIn) {
        super();
        setRotation(270);                
    }
    
    /**
     * Act - do whatever the ControlledCar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.isKeyDown("w")||Greenfoot.isKeyDown("up")) {   //press w or up arrow key to accelerate car 
            accelerate(0.5); }
        if (Greenfoot.isKeyDown("s")||Greenfoot.isKeyDown("down")) { //press s or down arrow key to slow down car
            accelerate(-1); }
        if (Greenfoot.isKeyDown("a")||Greenfoot.isKeyDown("left")) { //press a or left arrow key to turn left
            turn(-5); }
        if (Greenfoot.isKeyDown("d")||Greenfoot.isKeyDown("right")) { //press d or right arrow key to turn right
            turn(5);}
        wrap(); //allows user's car to wrap around edges
        speed = Math.round(speed); //round speed to nearest whole number
        move((int)speed);  //move at the given speed
        
        currentWorld = (Highway) getWorld();
        //add health bar once user has started the game
        if (currentWorld.startTime < 6*60) { 
            currentWorld.addObject(bar, 100, 50);
        }
        //if user's car runs into a traffic car
        if (isTouching(TrafficCar.class)) {
            speed = speed/1.3; //slow user's car 
            move((int)speed); 
            //reduce health by 3 for every act cycle of contact
            health -= 3;
            bar.setValue(health);

        }
        //if user runs into police car
        if (isTouching(PoliceCar.class)) {
            //reduce health by 10 for every act cycle of contact
            health -= 10;
            bar.setValue(health);
        }
        //if user runs into a bomb
        if (isTouching(Bomb.class)) {
            //reduce health by 1000
            //bomb despawns after one act cycle of contact with car
            health -= 1000; 
            bar.setValue(health);
        }
        //if user runs out of health
        if (health <= 0) {
            Greenfoot.stop(); 
            currentWorld.setBackground(new GreenfootImage("startingImage.PNG"));
            currentWorld.removeObjects(currentWorld.getObjects(null));
            currentWorld.addObject(new Button("Game Over, You've Lost"), 509, 200);
            currentWorld.showText("You ran out of health", 509, 300);
            int remainingTime = 30*60 - currentWorld.gameTime;
            currentWorld.showText("The time remaining on the clock was "+remainingTime/60+" seconds", 509, 350);
            score = currentWorld.gameTime/60*100;
            currentWorld.showText("Your score was "+score+" points", 509, 400);
            currentWorld.showText("Thank You For Playing!", 509, 500);
            currentWorld.showText("To play again, reset the program", 509, 550);
        }
        
        if (currentWorld.gameTime == 30*60) { //when game ends, display remaining health to user
            currentWorld.setBackground(new GreenfootImage("startingImage.PNG"));
            currentWorld.removeObjects(currentWorld.getObjects(null));
            currentWorld.addObject(new Button("Congratulations, You've Won!"), 509, 200);
            currentWorld.showText("Your remaining health was "+health+" HP", 509, 300);
            score = health + 30*100;
            currentWorld.showText("Your score was "+score+" points", 509, 350);
            currentWorld.showText("Thank You For Playing!", 509, 450);
            currentWorld.showText("To play again, reset the program", 509, 500);
        }
    }
}