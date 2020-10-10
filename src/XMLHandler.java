import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler{

    //Keeps Track of the current object being parsed
    //private Dungeon d_parsed = null;
    private structure s_parsed = null;
    private Item i_parsed = null;
    private ItemAction ia_parsed = null;
    private CreatureAction ca_parsed = null;
    private Creature c_parsed = null;
    private Point ppointer = null;

    //Keeps Track of Fields
    private boolean visible = false;
    private boolean posX = false;
    private boolean posY = false;
    private boolean width = false;
    private boolean height = false;
    private boolean hp = false;
    private boolean hpm = false;
    private boolean maxhit = false;
    private boolean ItemIntValue = false;
    private boolean actionMessage = false;
    private boolean actionIntVal = false;
    private boolean actionCharValue = false;

    public XMLHandler(){}

    private Dungeon dungeon = new Dungeon("",0,0);
    
    private ArrayList<structure> structures = new ArrayList<structure>();
    private ArrayList<Item> item = new ArrayList<Item>();
    private ArrayList<ItemAction> iAction = new ArrayList<ItemAction>();
    private ArrayList<CreatureAction> cAction = new ArrayList<CreatureAction>();
    private ArrayList<Creature> creature = new ArrayList<Creature>();
    private ArrayList<Point> points = new ArrayList<Point>();
    private StringBuilder data = null;

    public ArrayList<structure> getStructures(){return structures;}
    public ArrayList<Item> getItem(){return item;}
    public ArrayList<ItemAction> getItemAction(){return iAction;}
    public ArrayList<CreatureAction> getCreatureAction(){return cAction;}
    public ArrayList<Creature> getCreature(){return creature;}
    public Dungeon getDungeon(){return dungeon;}
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
        
        if(qName.equalsIgnoreCase("Dungeon")){
            String dungName = attributes.getValue("name");
            int dungWidth = Integer.parseInt(attributes.getValue("width"));
            int dungGameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            dungeon.setName(dungName);
            dungeon.setWidth(dungWidth);
            dungeon.setGameHeight(dungGameHeight);
        }
        else if(qName.equalsIgnoreCase("Room")){
            int roomNum = Integer.parseInt(attributes.getValue("room"));
            Room room = new Room();
            room.setIntValue(roomNum);
            structures.add(room);
            s_parsed = room;
        }
        else if(qName.equalsIgnoreCase("Scroll")){
            String scrollName = attributes.getValue("name");
            int scrollNum = Integer.parseInt(attributes.getValue("room"));
            int scrollSerial = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll = new Scroll(scrollName);
            scroll.setID(scrollNum, scrollSerial);
            item.add(scroll);
            i_parsed = scroll;
        }
        else if(qName.equalsIgnoreCase("Player")){
            String playerName = attributes.getValue("name");
            int playerRoom = Integer.parseInt(attributes.getValue("room"));
            int playerSerial = Integer.parseInt(attributes.getValue("serial"));
            Player player = new Player();
            player.setName(playerName);
            player.setRoom(playerRoom);
            player.setSerial(playerSerial);
            creature.add(player);
            c_parsed = player;
        }
        else if(qName.equalsIgnoreCase("ItemAction")){
            String itemActionName = attributes.getValue("name");
            String itemActionType = attributes.getValue("type");
            ItemAction itemAction = new ItemAction();
            itemAction.setName(itemActionName);
            itemAction.setType(itemActionType);
            iAction.add(itemAction);
            ia_parsed = itemAction;
        }
        else if(qName.equalsIgnoreCase("CreatureAction")){
            String creatureActionName = attributes.getValue("name");
            String creatureActionType = attributes.getValue("type");
            CreatureAction creatureAction = new CreatureAction();
            creatureAction.setName(creatureActionName);
            creatureAction.setType(creatureActionType);
            cAction.add(creatureAction);
            ca_parsed = creatureAction;
        }
        else if(qName.equalsIgnoreCase("Sword")){
            String swordName = attributes.getValue("name");
            int swordRoom = Integer.parseInt(attributes.getValue("room"));
            int swordSerial = Integer.parseInt(attributes.getValue("serial"));
            Sword sword = new Sword(swordName);
            sword.setID(swordRoom, swordSerial);
            item.add(sword);
            i_parsed = sword;
        }
        else if(qName.equalsIgnoreCase("Monster")){
            String monsterName = attributes.getValue("name");
            int monsterRoom = Integer.parseInt(attributes.getValue("room"));
            int monsterSerial = Integer.parseInt(attributes.getValue("serial"));
            Monster monster = new Monster();
            monster.setName(monsterName);
            monster.setID(monsterRoom, monsterSerial);
            creature.add(monster);
            c_parsed = monster;
        }
        else if(qName.equalsIgnoreCase("Armor")){
            String armorName = attributes.getValue("name");
            int armorRoom = Integer.parseInt(attributes.getValue("room"));
            int armorSerial = Integer.parseInt(attributes.getValue("serial"));
            Armor armor = new Armor(armorName);
            armor.setID(armorRoom, armorSerial);
            item.add(armor);
            i_parsed = armor;
        }
        else if(qName.equalsIgnoreCase("Passage")){
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            Passage passage = new Passage();
            passage.setID(room1, room2);
            structures.add(passage);
            s_parsed = passage;
        }
        

        else if(qName.equalsIgnoreCase("visible")){visible = true;}
        else if(qName.equalsIgnoreCase("posX")){posX = true;}
        else if(qName.equalsIgnoreCase("posY")){posY = true;}
        else if(qName.equalsIgnoreCase("width")){width = true;}
        else if(qName.equalsIgnoreCase("height")){height = true;}
        else if(qName.equalsIgnoreCase("hp")){hp = true;}
        else if(qName.equalsIgnoreCase("hpm")){hpm = true;}
        else if(qName.equalsIgnoreCase("maxhit")){maxhit = true;}
        else if(qName.equalsIgnoreCase("actionMessage")){actionMessage = true;}
        else if(qName.equalsIgnoreCase("actionIntValue")){actionIntVal = true;}
        else if(qName.equalsIgnoreCase("actionCharValue")){actionCharValue = true;}
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        
        if(visible){
            if(s_parsed != null && i_parsed==null && c_parsed == null){
                if(data.toString().equals("1")) {s_parsed.setVisible();}
                else{s_parsed.setInvisible();}
                visible = false;
            }
            else if(c_parsed != null && i_parsed==null){
                if(data.toString().equals("1")) {c_parsed.setVisible();}
                else{c_parsed.setInvisible();}
                visible = false;
            }
            else if(i_parsed != null){
                if(data.toString().equals("1")) {i_parsed.setVisible();}
                else{i_parsed.setInvisible();}
                visible = false;
            }
        }
        else if(width){
            if(s_parsed != null && i_parsed==null && c_parsed == null){
                s_parsed.setWidth(Integer.parseInt(data.toString()));
                width = false;
            }
        }
        else if(height){
            if(s_parsed != null && i_parsed==null && c_parsed == null){
                s_parsed.setHeight(Integer.parseInt(data.toString()));
                height = false;
            }
        }
        else if(hp){
            if(c_parsed != null){
                c_parsed.setHp(Integer.parseInt(data.toString()));
                hp = false;
            }
        }
        else if(hpm){
            if(c_parsed != null){
                c_parsed.setHpMove(Integer.parseInt(data.toString()));
                hpm = false;
            }
        }
        else if(maxhit){
            if(c_parsed != null){
                c_parsed.setMaxHit(Integer.parseInt(data.toString()));
                maxhit = false;
            }
        }
        else if(actionMessage){
            if(ca_parsed != null && ia_parsed==null){
                ca_parsed.setMessage(data.toString());
                actionMessage = false;
            }
            else if(ia_parsed != null){
                ia_parsed.setMessage(data.toString());
                actionMessage = false;
            }
        }
        else if(actionIntVal){
            if(ca_parsed != null && ia_parsed == null){
                ca_parsed.setIntValue(Integer.parseInt(data.toString()));
                actionIntVal = false;
            }
            else if(ia_parsed != null){
                ia_parsed.setIntValue(Integer.parseInt(data.toString()));
                actionIntVal = false;
            }
        }
        else if(actionCharValue){
            if(ia_parsed != null){
                ia_parsed.setCharValue(data.toString().charAt(0));
                actionCharValue = false; 
            }
        }
        else if(ItemIntValue){
            if(ia_parsed != null){
                ia_parsed.setIntValue(Integer.parseInt(data.toString()));
                ItemIntValue = false;
            }
        }
        else if(posX){
            if(s_parsed != null && i_parsed==null && c_parsed == null){
                if(s_parsed instanceof Room){
                    Point pointy = new Point(Integer.parseInt(data.toString()),0);
                    points.add(pointy);
                    ppointer = pointy;
                    posX = false;
                }
                else{
                    Point pointypoint = new Point(Integer.parseInt(data.toString()),0);
                    points.add(pointypoint);
                    ppointer = pointypoint;
                    posX = false;
                }
            }
            else if(c_parsed != null && i_parsed==null){
                Point cpoint = new Point(Integer.parseInt(data.toString()),0);
                points.add(cpoint);
                ppointer = cpoint;
                posX = false;
            }
            else if(i_parsed != null){
                Point ipoint = new Point(Integer.parseInt(data.toString()),0);
                points.add(ipoint);
                ppointer = ipoint;
                posX = false;
            }
        }
        else if(posY){
            if(s_parsed != null && i_parsed==null && c_parsed == null){
                if(s_parsed instanceof Room){
                    ppointer.setY(Integer.parseInt(data.toString()));
                    s_parsed.setPoint(ppointer);
                    posY = false;
                }
                else if(s_parsed instanceof Passage){
                    ppointer.setY(Integer.parseInt(data.toString()));
                    s_parsed.setPoints(ppointer);
                    posY = false;
                }
            }
            else if(c_parsed != null && i_parsed == null){
                ppointer.setY(Integer.parseInt(data.toString()));
                s_parsed.setPoint(ppointer);
                posY = false;
            }
            else if(i_parsed != null){
                ppointer.setY(Integer.parseInt(data.toString()));
                s_parsed.setPoint(ppointer);
                posY = false;
            }
        }
        else if(qName.equalsIgnoreCase("Room") || qName.equalsIgnoreCase("Passage")){
            s_parsed = null;
        }
        else if(qName.equalsIgnoreCase("Scroll") || qName.equalsIgnoreCase("Sword") || qName.equalsIgnoreCase("Armor")){
            i_parsed = null;
        }
        else if(qName.equalsIgnoreCase("CreatureAction")){
            ca_parsed = null;
        }
        else if(qName.equalsIgnoreCase("ItemAction")){
            ia_parsed = null;
        }
        else if(qName.equalsIgnoreCase("Player") || qName.equalsIgnoreCase("Monster")){
            c_parsed = null;
        }
    }    
}
