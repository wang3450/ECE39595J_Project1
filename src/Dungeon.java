import java.util.ArrayList; 
public class Dungeon {
    
    private String name;
    private int width;
    private int gameHeight;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private ArrayList<Passage> passages = new ArrayList<Passage>();
    private ArrayList<Item> items = new ArrayList<Item>();

    public Dungeon(String _name, int _width, int _gameHeight){
        name = _name;
        width = _width;
        gameHeight = _gameHeight;
    }

    public Dungeon getDungeon(String _name, int _width, int _gameHeight){
        if(name == _name && width == _width && gameHeight == _gameHeight){
            return this;
        }
        else{
            return new Dungeon(_name, _width, _gameHeight);
        }
    }

    public void addRoom(Room r){rooms.add(r);}
    public void addCreature(Creature c){creatures.add(c);}
    public void addPassage(Passage p){passages.add(r);}
    public void addItem(Item i){items.add(i);}

}
