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



    public void printAllValues(){
        System.out.println("Name: " + name);
        System.out.println("Visible: " + visible);
        System.out.println("Room Location: " + roomLocation);
        System.out.println("Serial: " + serial);
        System.out.println("Point: " + point);
        if(point != null) {
            System.out.println("Point-X: " + point.getX());
            System.out.println("Point-Y: " + point.getY());
        }else{
            System.out.println("Point is null.");
        }
        System.out.println("Creature: " + c);
        System.out.println("ItemAction: " + action);

    }

}
