public class Player extends Creature {
    private int hp;
    private int hpm;
    private CreatureAction da;
    private CreatureAction ha;
    private Item weapon;
    private Item armor;
    private Point point;
    private int serial;
    private int room;
    private String name;
    private boolean visible;

    public void setRoom(int _room){room = _room;}
    public void setSerial(int _serial){serial = _serial;}

    public void setWeapon(Item _weapon) {
        weapon = _weapon;
    }

    public void setArmor(Item _armor) {
        armor = _armor;
    }
    
    public void setName(String n){name = n;}


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
    }
}
