import java.util.ArrayList; 
public class Passage extends structure {
    private int room1;
    private int room2;
    private ArrayList<Point> points;
    private boolean visible;
    private String name;

    public Passage(){super();}
    public void setID(int _room1, int _room2){
        room1 = _room1;
        room2 = _room2;
    }
    public void setName(String _name){
        name = _name;
    }
    public void setPoints(Point p){points.add(p);}
}
