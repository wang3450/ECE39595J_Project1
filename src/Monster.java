import java.util.ArrayList;

public class Monster extends Creature{
    private boolean visible;
    private Point point = new Point(0,0);
    private int hp;
    private int hpm;
    private int maxHit;
    private String name;
    private int room_location;
    private int serial;
    private ArrayList<CreatureAction> creatureActions = new ArrayList<CreatureAction>();
    private Char displayChar;

    public void setName(String _name) {
        name = _name;
    }
    public void setVisible(boolean b){visible = b;}
    public void setPoint(Point p){point = p;}
    public void setHp(int _hp){hp = _hp;}
    public void setHpMove(int _hpm){hpm = _hpm;}
    public void setMaxHit(int _maxHit){maxHit = _maxHit;}
    public void setID (int _room, int _serial){
        room_location = _room;
        serial = _serial;
    }
    public void setCreatureActions(CreatureAction ca){creatureActions.add(ca);}

    public Point getPoint(){return point;}
    public String getName(){return name;}
    public void setChar(Char ch){ displayChar = ch;}
    public Char getChar(){return displayChar;}


    public void printAllValues(){
        System.out.println("Name: " + name);
        System.out.println("Room Number: " +room_location);
        System.out.println("Serial: " + serial);
        System.out.println("Visible: " +visible);
        System.out.println("Point: " + point);
        if(point!=null){
            System.out.println("Point-X: " +point.getX());
            System.out.println("Point-Y: " +point.getY());
        }else{
            System.out.println("Point is null.");
        }
        for(CreatureAction ca: creatureActions){
            System.out.println(ca);
        }
        System.out.println("HP: " + hp);
        System.out.println("MaxHit: " + maxHit);
    }
}
