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
}
