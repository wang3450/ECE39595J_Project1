public class Scroll extends Item {
    private int roomLocation;
    private int serial;
    private String name;
    private int visible;
    private Point point;
    private Creature c = new Creature();
    private ItemAction action = new ItemAction(c);
    //Need Item intvalue, item character value, and message from action

    public Scroll(String _name){
        name = _name;
    }

    public void setID(int room, int _serial){
        roomLocation = room;
        serial = _serial;
    }

    public void setMessage(String s){
        action.setMessage(s);
    }
    
    public String getAction(){
        return action.getMessage();
    }

    public String getName(){return name;}

}
