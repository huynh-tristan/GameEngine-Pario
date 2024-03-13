package GameStuff;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Agent extends Entity{

    private int[][] lvlData;
    private final File file;
    private final float startX;
    private float playerX;
    private float playerY;
    public Agent(Image front, float x, float y, float imageScale, int[][] lvlData, File file) {
        this.startX = x;
        this.frontImage = front;
        this.xDelta = x;
        this.yDelta = y;
        this.scale = imageScale;
        this.speed = 1.5f;
        this.jumpSpeed = -4.25f;
        this.lvlData = lvlData;
        this.file = file;
        this.right = true;
        this.moving = true;
        this.entityCollisionCheckList = new ArrayList<>();

    }
    public class Action{
        private String name;

        public Action(String name) {
            this.name = name;
        }


        public void execute() {
            switch (name){
                case "checkPlayerLocation":
                    checkPlayerLocation();
                    break;
                case "moveBackAndForth":
                    moveBackAndForth();
                    break;
                case "seek":
                    seek();
                    break;
                default:
                    System.out.println("test");
            }

        }
    }

    public void moveRight(){
        xDelta += speed;
    }

    public void moveLeft(){
        xDelta -= speed;
    }

    public void moveBackAndForth(){
        if(this.moving) {
            if (this.right) {
                if (xDelta < startX + 128) {
                    xDelta += speed;
                }
                if (xDelta >= startX + 128) {
                    this.right = false;
                }
            } else {
                xDelta -= speed;
                if (xDelta <= startX) {
                    this.right = true;
                }
            }
        }
    }

    public void seek(){
        if(this.moving) {
            if(playerX <= xDelta - 32) {
                xDelta -= speed;
            }
            else if(playerX >= xDelta + 32){
                xDelta += speed;
            }
            if(playerY <= yDelta - 32){
                yDelta -= speed;
            }
            else if (playerY >= yDelta + 32) {
                yDelta += speed;
            }
        }
    }

    public void checkPlayerLocation(){
        playerX = entityCollisionCheckList.get(0).getXDelta();
        playerY = entityCollisionCheckList.get(0).getYDelta();
        if((playerX >= xDelta + 128 || playerX <= xDelta - 128) || (playerY >= yDelta + 128 || playerY <= yDelta - 128)){
                this.moving = true;
        }
        else{
            this.moving = false;
        }

    }

    public class Sequence {
        private NodeList actions;

        public Sequence(NodeList actions) {
            this.actions = actions;
        }

        public void execute() {
            for (int i = 0; i < actions.getLength(); i++) {
                Element actionElement = (Element) actions.item(i);
                String actionName = actionElement.getAttribute("name");
                Action action = new Action(actionName);
                action.execute();
            }
        }
    }

    //This reads the tree to determine the action to take upon each update
    public void update() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.file);
            doc.getDocumentElement().normalize();

            NodeList sequenceList = doc.getElementsByTagName("Sequence");
            for (int i = 0; i < sequenceList.getLength(); i++) {
                Element sequenceElement = (Element) sequenceList.item(i);
                NodeList actions = sequenceElement.getElementsByTagName("Action");
                Sequence sequence = new Sequence(actions);
                sequence.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawHitbox(Graphics graphics) {

    }

    public boolean entityToEntityCollisionCheck(int numOfEntity) {
        return false;
    }

    public void entityCollisionInteraction() {

    }
}
