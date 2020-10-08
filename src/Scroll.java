public class Scroll extends Item {
    private int roomLocation;
    private int serial;
    private String name;
    private int visible;
    private int posX;
    private int posY;
    private ItemAction action;
    //Need Item intvalue, item character value, and message from action

    public Scroll(String name){
        this.name = name;
    }

    public void setID(int room, int serial){
        this.roomLocation = room;
        this.serial = serial;
    }
}
