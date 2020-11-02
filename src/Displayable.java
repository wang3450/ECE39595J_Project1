import java.util.ArrayList;
public class Displayable {
    private boolean visible;
    private int MaxHit;
    private int HpMove;
    private int Hp;
    private char Type;
    private int IntValue;
    private Point point;
    private ArrayList <Point> points = new ArrayList<Point>(); 
    private int width;
    private int height;

    private Char displayChar;

    public Displayable(Char ch){
        displayChar = ch;
    }
    public Displayable(){}

    public void setVisible(boolean _visible){visible = _visible;}

    public void setMaxHit(int maxHit) {
        MaxHit = maxHit;
    }

    public void setHpMove(int hpMove) {
        HpMove = hpMove;
    }

    public void setHp(int hp) {
        Hp = hp;
    }

    public void setType(char type) {
        Type = type;
    }

    public void setIntValue(int intValue) {
        IntValue = intValue;
    }

    public void setPoint(Point p){ point = p;}

    public void setWidth(int _width) {
        width = _width;
    }

    public void setHeight(int _height) {
        height = _height;
    }
    public void setPoints(Point p){points.add(p);}

    public void setChar(Char ch){displayChar = ch;}
    public Char getChar(){return displayChar;}
    public int getMaxHit(){return MaxHit;}
    public int getHp(){return Hp;}
}
