import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DungeonXMLHandler extends DefaultHandler{

    private StringBuilder data = null;

    private Dungeon dungeon1 = new Dungeon("",0,0);

    private Dungeon dungeonBeingParsed = null;
    private Room roomBeingParsed = null;
    private Scroll scrollBeingParsed = null;
    private Player playerBeingParsed = null;
    private ItemAction itemActionBeingParsed = null;
    private Sword swordBeingParsed = null;
    private CreatureAction creatureActionBeingParsed = null;

    private boolean visible = false;
    private boolean posX = false;
    private boolean posY = false;
    private boolean width = false;
    private boolean height = false;
    private boolean hp = false;
    private boolean hpm = false;
    private boolean maxhit = false;
    private boolean ItemIntValue = false;

    private String dungName;
    private int dungWidth;
    private int dungGameHeight;
    private int roomNum;
    public DungeonXMLHandler(){ 
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if(qName.equalsIgnoreCase("Dungeon")){
            dungName = attributes.getValue("name");
            dungWidth = Integer.parseInt(attributes.getValue("width"));
            dungGameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            dungeon1.setName(dungName);
            dungeon1.setWidth(dungWidth);
            dungeon1.setGameHeight(dungGameHeight);
        }
        else if(qName.equalsIgnoreCase("room")){
            roomNum = Integer.parseInt(attributes.getValue("room"));
            Room room1 = new Room();
            room1.setIntValue(roomNum);
            dungeon1.addRoom(room1);
        }
        else if(qName.equalsIgnoreCase("visible")){visible = true;}
        else if(qName.equalsIgnoreCase("posX")){posX = true;}
        else if(qName.equalsIgnoreCase("posY")){posY = true;}
        else if(qName.equalsIgnoreCase("width")){width = true;}
        else if(qName.equalsIgnoreCase("height")){height = true;}
        else if(qName.equalsIgnoreCase("hp")){hpm = true;}
        else if(qName.equalsIgnoreCase("maxhit")){maxhit = true;}
        else if(qName.equalsIgnoreCase("Scroll")){
            String scrollName1 = attributes.getValue("name");
            int scrollRoomNum1 = Integer.parseInt(attributes.getValue("room"));
            int scrollSerial1 = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll1 = new Scroll(scrollName1);
            scroll1.setID(scrollRoomNum1, scrollSerial1);
        }

    }


    
}