public class Armor extends Item{
    private int visible;
    private Point point;
    private String name;
    private int roomLocation;
    private int serial;
    private int intValue;

    public Armor(String _name){
        name = _name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setID(int _room, int _serial){
        roomLocation = _room;
        serial = _serial;
    }
}
