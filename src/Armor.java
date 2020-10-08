public class Armor extends Item{
    private int visible;
    private int posX;
    private int posY;
    private String name;
    private int roomLocation;
    private int serial;
    //need ItemIntValue

    public Armor(String _name){
        this.name = _name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int _room, int _serial){
        this.roomLocation = _room;
        this.serial = _serial;
    }
}
