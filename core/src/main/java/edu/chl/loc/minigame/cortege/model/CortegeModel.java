package edu.chl.loc.minigame.cortege.model;

import edu.chl.loc.minigame.cortege.utilities.ToolType;

import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author Maxim Goretskyy
 */
public class CortegeModel{

    private int score;
    private final int plusPoint = 5;
    private final int minusPoint = 7;
    private PropertyChangeSupport pcs;

    private final int maximalX = 1024-64;
    private final int minimalX = 0;
    private final int spawnY = 576;
    private final int toolWidth = 64;
    private final int toolHeight = 64;
    private final long MAX_TIME_PER_ROUND; //1 minute in milliseconds
    private final Random randomGenerator = new Random();

    private List<Tool> toolsList = new ArrayList<Tool>();
    private Rectangle toolBox = new Rectangle();

    private ToolType[] toolTypes = new ToolType[]{ToolType.HAMMER,
            ToolType.PLANK, ToolType.SAW, ToolType.SCREWER};

    private long lastDropTime;
    private boolean isPlaying;
    private boolean isMoving = false;
    private float velocity = 16f;
    private boolean movingRight = true;

    private Iterator<Tool> iterTool;

    public CortegeModel(){
        pcs = new PropertyChangeSupport(this);
        toolBox.width = 64;
        toolBox.height = 64;
        toolBox.y = 20;
        score = 0;
        MAX_TIME_PER_ROUND = System.currentTimeMillis() + 60000; //1 minute in milliseconds
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

            if (toolBox.x < minimalX) {
                toolBox.x = minimalX;
            } else if (toolBox.x > maximalX) {
                toolBox.x = maximalX;
            }
        }
    }

    private void spawnItem(){

        Tool tempTool;
        if(Math.random() < 0.8){ //80% chance to spawn a tool
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
        if(System.currentTimeMillis()> MAX_TIME_PER_ROUND){ //after 1 minute pause game
            stop();
            if(System.currentTimeMillis() > MAX_TIME_PER_ROUND + 2000) { //show score for 2 seconds
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
                if (tool.y + 64 < 0) {
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
        if(score >= 230){
            return '5';
        }else if(score >= 175){
            return '4';
        }else if(score >= 100){
            return '3';
        }
        return 'U';
    }

    /**
     * Adds a listener to this Cortege, will be notified when the game
     * is over
     */
    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }
}
