import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;

/**
 * Write a description of class HealthBar here.
 * 
 * @author Soham Basu 
 * @version 1.0
 */
public class HealthBar extends Actor
{
    //instance variables
    private int barWidth = 100; // the width of the colour portion of the bar
    private int barHeight = 10; // the height of the colour portion of the bar
    private int breakPercent = 20; // the percentage amount that changes the colour of the bar
    private int breakValue = 20; 
    private boolean usingBreakValue = false;
    private boolean breakLow = true; 
    private int maximumValue = 10000; // the maximum value of the bar
    private int minimumValue = 0; // the minimum value of the bar
    private int value = 0; //the current value of the bar 
    private int initialValue = 10000; //the amount of health the user starts the game with
    private int yellowValue = 5000; //at this value, the colour of the bar turns from green to yellow
    private int redValue = 2000; //at this value, the colour of the bar turns from yellow to red
    private Color textColour = Color.ORANGE; //the color of all text and the frame of the bar 
    private Color safeColour = Color.GREEN; //the colour of the bar when value > 2000
    private Color dangerColour = Color.RED; //the color of the bar when value <= 2000
    private Color backgroundColor = new Color(0, 0, 0, 0); // the background color of the health bar
    private String title = ""; //the title of the health bar, to be chosen by user
    private String unit = "HP"; //the unit of measurement is health points
    private float fontSize = 18.0f; // the size of the text
    private boolean showTextualUnits = true;
    /**
     * Constructor for Health Bar
     */
    public HealthBar(String name) {
        title = name;
        setValue(initialValue);
    }
    /**
     * Image of health bar
     */
    private void newImage() {
        int barValue = (int) (barWidth * (value - minimumValue) / (maximumValue - minimumValue));
        GreenfootImage leftImg = new GreenfootImage(title + " ", (int) fontSize, textColour, backgroundColor);
        GreenfootImage rightImg = (showTextualUnits) ? new GreenfootImage(" " + value + " " + unit, (int) fontSize, textColour, backgroundColor) : new GreenfootImage(1, 1);
        int maxX = (leftImg.getWidth() > rightImg.getWidth()) ? leftImg.getWidth() : rightImg.getWidth();
        GreenfootImage barImg = new GreenfootImage(barWidth + 4, barHeight + 4);
        barImg.setColor(backgroundColor);
        barImg.fill();
        barImg.setColor(textColour);
        barImg.drawRect(0, 0, barImg.getWidth() - 1, barImg.getHeight() - 1);
        if (value > minimumValue)
        {
            if (breakLow)
            {
                if (value > (usingBreakValue ? breakValue : (int) (breakPercent * (maximumValue - minimumValue) / 100 + minimumValue))) barImg.setColor(safeColour);
                else barImg.setColor(dangerColour);
            }
            else
            {
                if (value < (usingBreakValue ? breakValue : (int) (breakPercent * (maximumValue - minimumValue) / 100 + minimumValue))) barImg.setColor(safeColour);
                else barImg.setColor(dangerColour);
            }
            if (value == (usingBreakValue ? breakValue : (int) (breakPercent * (maximumValue - minimumValue) / 100 + minimumValue)))
            {
                int r = (int) ((safeColour.getRed() + dangerColour.getRed()) / 2);
                int g = (int) ((safeColour.getGreen() + dangerColour.getGreen()) / 2);
                int b = (int) ((safeColour.getBlue() + dangerColour.getBlue()) / 2);
                barImg.setColor(new Color(r, g, b));
            }
            barImg.fillRect(2, 2, barValue, barHeight);
        }
        int sumX = 2 * maxX + barImg.getWidth();
        int maxY = 0;
        if (leftImg.getHeight() > maxY) maxY = leftImg.getHeight();
        if (barImg.getHeight() > maxY) maxY = barImg.getHeight();
        if (rightImg.getHeight() > maxY) maxY = rightImg.getHeight();
        GreenfootImage image = new GreenfootImage(sumX, maxY);
        image.setColor(backgroundColor);
        image.fill();
        image.drawImage(leftImg, maxX - leftImg.getWidth(), (image.getHeight() - leftImg.getHeight()) / 2);
        image.drawImage(barImg, maxX, (image.getHeight() - barImg.getHeight()) / 2);
        image.drawImage(rightImg, maxX + barImg.getWidth(), (image.getHeight() - rightImg.getHeight()) / 2);
        setImage(image);
    }
    /**
     * sets value of health bar
     */
    public void setValue(int valueln) { 
        value = valueln; 
        checkValue(); 
        newImage(); 
    }
    /**
     * confirms value does not exceed the min or max values
     */
    private void checkValue()
    {
        if (value < minimumValue) value = minimumValue;
        if (value > maximumValue) value = maximumValue;
    }
}
