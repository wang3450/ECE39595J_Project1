public class Monster extends Creature{
    private boolean visible;
    private Point point = new Point(0,0);
    private int hp;
    private int hpm;
    private int maxHit;
    private String name;
    private int room_location;
    private int serial;
    private CreatureAction da;
    private CreatureAction ha;

    public void setName(String _name) {
        name = _name;
    }
    public void setVisible(boolean b){visible = b;}
    public void setPoint(Point p){point = p;}
    public void setHp(int _hp){hp = _hp;}
    public void setHpMove(int _hpm){hpm = _hpm;}
    public void setMaxHit(int _maxHit){maxHit = _maxHit;}
    public void setID (int _room, int _serial){
        this.room_location = _room;
        this.serial = _serial;
    }



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
        System.out.println("Hit Action: " + ha);
        System.out.println("Death Action: " + da);
        System.out.println("HP: " + hp);
        System.out.println("MaxHit: " + maxHit);
    }
}
