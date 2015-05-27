package edu.chl.loc.minigame.cortege.model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author Maxim Goretskyy
 */
public class CortegeModel{

    private int score;
    private final static int plusPoint = 5;
    private final static int minusPoint = 7;
    private PropertyChangeSupport pcs;

    private final static int maximalX = 1024-64;
    private final static int minimalX = 0;
    private final static int spawnY = 576;
    private final static int toolWidth = 64;
    private final static int toolHeight = 64;
    private final long MAX_TIME_PER_ROUND; //1 minute in milliseconds
    private final static Random randomGenerator = new Random();

    private List<Tool> toolsList = new ArrayList<Tool>();
    private Rectangle toolBox = new Rectangle();

    private ToolType[] toolTypes = new ToolType[]{ToolType.HAMMER,
            ToolType.PLANK, ToolType.SAW, ToolType.SCREWER};

    private long lastDropTime;
    private boolean isPlaying;
    private boolean isMoving = false;
    private float velocity = 16f;
    private boolean movingRight = true;
    private long currentTime;

    private Iterator<Tool> iterTool;

    public CortegeModel(){
        pcs = new PropertyChangeSupport(this);
        toolBox.width = 64;
        toolBox.height = 64;
        toolBox.y = 20;
        score = 0;
        MAX_TIME_PER_ROUND = System.currentTimeMillis() + 20000; //1 minute in milliseconds
        currentTime = System.currentTimeMillis();
        spawnItem();
        isPlaying= true;


    }

    public void setIsMoving(boolean moving) {
        this.isMoving = moving;
    }

    public void updatePosition(float delta) {
        if (isPlaying && isMoving) {
            if (movingRight) {
                toolBox.x += velocity;
            } else {
                toolBox.x -= velocity;

            }

            if (toolBox.getX() < minimalX) {
                toolBox.x = minimalX;
            } else if (toolBox.getX() > maximalX) {
                toolBox.x = maximalX;
            }
        }
    }

    private void spawnItem(){

        Tool tempTool;
        if(Math.random() < 0.7){ //70% chance to spawn a tool
            tempTool = new Tool(toolTypes[randomGenerator.nextInt(toolTypes.length)]);
        }else{
            tempTool = new Tool(ToolType.POOP);
        }
        tempTool.x = randomGenerator.nextInt(maximalX+1);
        tempTool.y = spawnY; //Spawn from top
        tempTool.width = toolWidth;
        tempTool.height = toolHeight;
        toolsList.add(tempTool);

        //time since last dropped item
        lastDropTime = System.nanoTime();
    }


    public void updatingGame(float deltatime){
        currentTime = System.currentTimeMillis();//updating current time
        if(currentTime> MAX_TIME_PER_ROUND){ //after 1 minute pause game
            stop();
            if(currentTime > MAX_TIME_PER_ROUND + 3000) { //show score for 2 seconds
                close();
            }

        }
        if(isPlaying) {

            updatePosition(deltatime);


            if (System.nanoTime() - lastDropTime > 1000000000) {

                spawnItem();

            }
            //iterate through list of tools and keep moving them down or catch them
            iterTool = toolsList.iterator();
            while (iterTool.hasNext()) {
                Tool tool = iterTool.next();
                tool.y -= 200 * deltatime;
                if (tool.getY() + 64 < 0) {
                    iterTool.remove();
                }
                //if not poop, and you pick it, then you get points
                if (isOverlapping(tool, toolBox) && tool.getType() != ToolType.POOP) {
                    iterTool.remove();
                    score += plusPoint;
                    //if it overlaps and is poop you get minus points
                }else if(isOverlapping(tool, toolBox) && tool.getType() == ToolType.POOP){
                    iterTool.remove();
                    score -= minusPoint;
                }
            }
        }

    }

    public void moveLeft(){
        movingRight = false;
    }

    public void moveRight(){
        movingRight = true;
    }
    public int getScore(){
        return score;
    }

    private boolean isOverlapping(Rectangle rect, Rectangle rect2){
        Rectangle tempRect = rect.intersection(rect2);
        return !tempRect.isEmpty();
    }

    public Rectangle getToolBox(){
        return toolBox;
    }

    public List<Tool> getToolsList(){
        return this.toolsList;
    }
    public void reset(){
        this.score = 0;
        isPlaying = true;
        spawnItem();
    }

    public boolean isPlaying(){
        return this.isPlaying;
    }

    public void stop(){
        isPlaying = false;
        toolsList.clear();
    }

    public void close(){
        pcs.firePropertyChange("gameFinished", null, null);
    }

    public char getGrade(){
        int score = getScore();
        if(score >= 50){
            return '5';
        }else if(score >= 30){
            return '4';
        }else if(score >= 15){
            return '3';
        }
        return 'U';
    }
    public double getTimeLeft(){
        if(isPlaying()){ //update time if you are playing
            return (MAX_TIME_PER_ROUND - currentTime)/1000;
        }
        return 0; //if you arent playing the timer is at 0

    }
    /**
     * Adds a listener to this Cortege, will be notified when the game
     * is over
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }
}
