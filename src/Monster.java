public class Monster extends Creature{
    private boolean visible;
    private Point point;
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
        System.out.println("HPM: " + hpm);
        System.out.println("MaxHit: " + maxHit);
    }
}
