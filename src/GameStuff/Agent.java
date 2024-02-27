package GameStuff;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Agent extends Entity{

    private int[][] lvlData;
    private final File file;
    public Agent(Image front, float x, float y, float imageScale, int[][] lvlData, File file) {
        this.frontImage = front;
        this.xDelta = x;
        this.yDelta = y;
        this.scale = imageScale;
        this.speed = 2.0f;
        this.jumpSpeed = -4.25f;
        this.lvlData = lvlData;
        this.file = file;
    }
    public class Action{
        private String name;

        public Action(String name) {
            this.name = name;
        }

        public void execute() {
            System.out.println("Executing action: " + name);
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
