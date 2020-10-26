import java.util.ArrayList; 
public class Room extends structure{
    public Room(){super();}
    private Point point = new Point(0,0);
    private int height;
    private int width;
    private String name;
    private int roomNum;
    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private boolean visible;
    private int IntValue;

    public Room(String _name){name = _name;}
    public void setRoomNum(int _roomNum) {roomNum = _roomNum;}
    public void setCreature(Creature Monster){creatures.add(Monster);}
    public void setVisible(boolean _visible){visible = _visible;}
    public void setWidth(int _width){width = _width;}
    public void setHeight(int _height){height = _height;}
    public void setPoint(Point p){ point = p; }
    public void addItem(Item i){items.add(i);}
    public void addCreature(Creature c) {creatures.add(c);}

    //getters
    public boolean getVisible(){return visible;}
    public Point getPoint(){return point;}



    public void printAllValues(){
        System.out.println("Room number: " + roomNum);
        System.out.println("Visible: " + visible);
        System.out.println("Point: " + point);
        if(point!=null) {
            System.out.println("Point-X: " + point.getX());
            System.out.println("Point-Y: " + point.getY());
        }
        else{
            System.out.println("Point is null");
        }
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);


        for(Creature c : creatures){
            System.out.println("Creature Array: " + c);
        }
        for(Item i : items){
            System.out.println("Item Array: " + i);
        }

    }
    
}
