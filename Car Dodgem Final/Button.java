import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author Soham Basu 
 * @version 1.0
 */
public class Button extends Actor
{
    //instance variables
    String buttonText = "START GAME";
    /**
     * Constructor for Start Game button only
     */
    public Button() {
        button1(); //the START GAME button
    }
    
    /**
     * Constructor for all other buttons
     */
    public Button(String textIn) {
        buttonText = textIn; 
        button2();  //the Welcome message
    }
    /**
     * Appearance of Start Game button
     */
    public void button1() { //the START GAME button
        GreenfootImage buttonImage = new GreenfootImage(buttonText, 30, Color.GREEN, Color.BLACK);
        setImage(buttonImage);
    }    
    /**
     * Appearance for all other buttons
     */
    public void button2() { //all other buttons
        GreenfootImage buttonImage = new GreenfootImage(buttonText, 50, Color.CYAN, Color.RED);
        setImage(buttonImage);
    }
}