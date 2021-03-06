import java.util.ArrayList;

public class Player extends Creature {
    private int hp;
    private int hpm;
    private CreatureAction da;
    private CreatureAction ha;
    private Item weapon;
    private Item armor;
    private Point point = new Point(0,0);
    private int serial;
    private int room;
    private String name;
    private boolean visible;
    private int maxHit;
    private ArrayList<CreatureAction> creatureActions = new ArrayList<CreatureAction>();
    private Char displayChar = new Char('@');
    private ArrayList<Item> inventory = new ArrayList<Item>();

    public Player(){}

    public void setRoom(int _room){room = _room;}
    public void setSerial(int _serial){serial = _serial;}
    public void setVisible(boolean b){visible =b;}
    public void setWeapon(Item _weapon) {
        weapon = _weapon;
    }
    public void setArmor(Item _armor) {
        armor = _armor;
    }
    public void setHp(int _hp){hp = _hp;}
    public void setHpMove (int _hpm){hpm = _hpm;}
    public void setPoint(Point p){point = p;}
    public void setName(String n){name = n;}
    public void setMaxHit(int mh){maxHit = mh;}
    public void setCreatureActions(CreatureAction ca){creatureActions.add(ca);}
    public void setInventory(ArrayList<Item> i){inventory = i;}
    public void setInventory(Item i){inventory.add(i);}

    public Point getPoint(){return point;}
    public int getHp(){return hp;}
    public int getMaxHit(){return maxHit;}
    public Char getChar(){return displayChar;}
    public Item getArmor(){return armor;}
    public Item getSword(){return weapon;}
    public ArrayList<Item> getInventory(){return inventory;}
    public int getHpm(){return hpm;}
    public void dropItem(int i){
        inventory.remove(i-1);
    }
    public ArrayList<CreatureAction> getCreatureActions(){return creatureActions;}


    public void printAllValues(){
        System.out.println("Name: " + name);
        System.out.println("Room Number: " +room);
        System.out.println("Serial: " + serial);
        System.out.println("Visible: " + visible);
        System.out.println("Point: " + point);
        if(point!=null){
            System.out.println("Point-X: " +point.getX());
            System.out.println("Point-Y: " +point.getY());
        }else{
            System.out.println("Point is null.");
        }
        System.out.println("Armor: " + armor);
        System.out.println("Weapon: " + weapon);
        System.out.println("Hit Action: " + ha);
        System.out.println("Death Action: " + da);
        System.out.println("HP: " + hp);
        System.out.println("HPM: " + hpm);
        System.out.println("Max Hit " +maxHit);
        for(CreatureAction ca: creatureActions){
            System.out.println(ca);
        }

    }
}
