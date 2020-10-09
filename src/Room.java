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

    public Room(String _name){name = _name;}
    public void setId(int _room) {room = _room;}
    public void setCreature(Creature Monster){creatures.add(Monster);}


    //getters
    public boolean getVisible(){return visible;}
    public Point getPoint(){return point;}
    
}
