public class Scroll extends Item {
    private int roomLocation;
    private int serial;
    private String name;
    private boolean visible;
    private Point point = new Point(0,0);
    private Creature c;
    private ItemAction action = new ItemAction(c);
    private Char displayChar = new Char('?');
    //Need Item intvalue, item character value, and message from action

    public Scroll(String _name){
        name = _name;
    }

    public void setID(int room, int _serial){
        roomLocation = room;
        serial = _serial;
    }
    public void setVisible(boolean b){visible = b;}
    public void setMessage(String s){
        action.setMessage(s);
    }
    public void setPoint(Point p){point = p;}
    public void setItemAction(ItemAction ia){action = ia;}
    public String getAction(){
        return action.getMessage();
    }
    public String getName(){return name;}
    public Point getPoint(){return point;}
    public Char getChar(){return displayChar;}


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
