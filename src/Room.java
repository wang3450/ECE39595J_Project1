import java.util.ArrayList; 
public class Room extends structure{
    public Room(){super();}
    private Point point; 
    private int height;
    private String name;
    private int room;
    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private boolean visible;
    private int IntValue;

    public Room(String _name){name = _name;}
    public void setId(int _room) {room = _room;}
    public void setCreature(Creature Monster){creatures.add(Monster);}


    //getters
    public boolean getVisible(){return visible;}
    public Point getPoint(){return point;}



    public void printAllValues(){
        System.out.println("Visible: " + visible);
        System.out.println("Point: " + point);
        if(point!=null) {
            System.out.println("Point-X: " + point.getX());
            System.out.println("Point-Y: " + point.getY());
        }
        else{
            System.out.println("Point is null");
        }
        System.out.println("Height: " + height);
        System.out.println("Name: " + name);
        System.out.println("Room number: " + room);
        for(Creature c : creatures){
            System.out.println("Creature Array: " + c);
        }
        for(Item i : items){
            System.out.println("Item Array: " + i);
        }

    }
    
}
