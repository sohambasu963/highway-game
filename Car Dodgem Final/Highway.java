import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Highway here.
 * 
 * @author Soham Basu 
 * @version 1.0
 */
public class Highway extends World
{
    //instance variables
    int gameState = 0;
    int gameTime = 0;
    int startTime = 6*60;
    boolean showInstructions = true; //Note: Disable to avoid buildup of instructions in output box

    /**
     * Constructor for objects of class Highway.
     * 
     */
    public Highway()
    {    
        // Create a new world with 1018x600 cells with a cell size of 1x1 pixels.
        super(1018, 600, 1); 
        //instance variables and intial setup
        Greenfoot.setSpeed(60);
        gameTime = 0;
        startTime = 6*60;
    }
    /** 
     * Act method
     * Consists of a switch statement which allows for different states of the game
     */
    public void act() {
        switch (gameState) {
            //game starting screen            
            case 0: Button button = new Button ();
                    setBackground(new GreenfootImage("startingImage.PNG"));
                    addObject(new Button("Welcome to Highway Chase"), getWidth()/2, getHeight()/3); 
                    // Creating & adding play button to world
                    addObject (button, getWidth ()/2, getHeight ()/2);
                    showText("Instructions are shown in output box", 509, 400);
                    if (showInstructions) {
                        System.out.println("These are the instructions on how to play Highway Chase");
                        System.out.println("                                                ");
                        System.out.println("You are driving on a dangerous strech of road full of obstacles such as other traffic and bombs.");
                        System.out.println("There is also a red police car chasing you.");
                        System.out.println("Running into these obstacles will damage your car and cause you to lose health.");
                        System.out.println("The number of obstacles and their speed will increase as time goes on.");
                        System.out.println("You will start with 10000 health. To win, you must be able to survive for 30 seconds.");
                        System.out.println("Green cars will cause some damage but not much. They will also slow you down.");
                        System.out.println("Red cars will cause even more damage.");
                        System.out.println("Bombs will cause the most damage so make sure to stay away.");
                        System.out.println("There will be a health bar and timer showing how much health and time is left");
                        System.out.println("To move your car, use the WASD or arrow keys");
                        System.out.println("                                                ");
                        System.out.println("Good Luck!");
                    }                      
                    gameState = 1;
                    break;
                    //checks if user wants to start the game
            case 1: MouseInfo mouse = Greenfoot.getMouseInfo();                    
                   
                    if (mouse != null) {    
                        Actor currentActor = mouse.getActor();
                        if (currentActor != null) {
                            if (currentActor.getClass() == Button.class) {
                                Button currentButton = (Button)currentActor;
                                int mouseButtonPressed = mouse.getButton();
                                int mouseClickCount = mouse.getClickCount();
                                if (mouseClickCount == 1) {
                                    gameState = 2;
                                }
                            }
                        }
                    }                    
                    break;
                    
                    //intializing game  
            case 2: showText(null, 509, 400);
                    removeObjects(getObjects(Button.class));
                    setBackground(new GreenfootImage("road.PNG"));
                    addObject(new ControlledCar(30.0), 545, getHeight ()/2);
                    addObject(new PoliceCar(), 545, 1000);
                    gameState = 3;
                    break;
            case 3: startTime--;
                    if (startTime/60 == 5) {
                        addObject(new Button("3"), getWidth()/2, getHeight()/2);
                    }
                    else if (startTime/60 == 4) {
                        removeObjects(getObjects(Button.class));
                        addObject(new Button("2"), getWidth()/2, getHeight()/2);
                    }
                    else if (startTime/60 == 3) {
                        removeObjects(getObjects(Button.class));
                        addObject(new Button("1"), getWidth()/2, getHeight()/2);
                    }
                    else if (startTime/60 == 2) {
                        removeObjects(getObjects(Button.class));
                        addObject(new Button("GO!"), getWidth()/2, getHeight()/2);
                    }
                    else if (startTime/60 == 1) {
                        removeObjects(getObjects(Button.class));
                        gameTime = 0; //this ensures that gameTime does not start until the countdown is over
                    }
                    
                    gameTime += 1; //start game   
                    int remainingTime = 30*60 - gameTime;
                    String remainder = String.valueOf(remainingTime/60);
                    if (startTime/60 > 1) { //prevents the game timer from starting early
                        remainder = "30";
                    }
                    addObject(new Button(remainder), 900, 40);
                    
                    while (getObjects(TrafficCar.class).size() < 3 && startTime <= 60) { //adds 3 Traffic Cars when the game begins
                        int x = Greenfoot.getRandomNumber(getWidth());
                        int y = Greenfoot.getRandomNumber(getHeight());
                        addObject(new TrafficCar(), x,y);
                    }
                    if (gameTime % (5*60) == 0) { //adds a Traffic Car in a random location every 5 seconds
                        int x = Greenfoot.getRandomNumber(getWidth());
                        int y = Greenfoot.getRandomNumber(getHeight());
                        addObject(new TrafficCar(), x,y);
                    }
                    if (gameTime % (7*60) == 0) { //adds a bomb in a random location every 5 seconds
                        int x = Greenfoot.getRandomNumber(getWidth());
                        int y = Greenfoot.getRandomNumber(getHeight());
                        addObject(new Bomb(), x,y); 
                    }
                    if (gameTime == 60*30) { //game is over after 1 minute
                        Greenfoot.stop();
                        System.out.println("Game Over. You Win!!!");
                    }
                    break;
                    
            default: gameState = 0;
                     break;
        }
    }
}