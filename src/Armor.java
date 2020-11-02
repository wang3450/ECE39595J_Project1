public class Armor extends Item{
    private boolean visible;
    private Point point = new Point(0,0);
    private String name;
    private int roomLocation;
    private int serial;
    private int intValue;
    private Char displayChar = new Char(']');

    public Armor(String _name){
        name = _name;
    }
    public void setIntValue(int i){intValue = i;}
    public void setName(String _name) {
        name = _name;
    }
    public void setVisible(boolean b){visible = b;}
    public void setPoint(Point p) { point = p;}
    public void setID(int _room, int _serial){
        roomLocation = _room;
        serial = _serial;
    }
    public Char getChar(){return displayChar;}
    public Point getPoint(){return point;}


    public void printAllValues(){
        System.out.println("Name: " + name);
        System.out.println("Visible: " + visible);
        System.out.println("Room Location: " + roomLocation);
        System.out.println("Serial: " + serial);
        System.out.println("IntValue: " + intValue);
        System.out.println("Point: " + point);
        if(point!=null) {
            System.out.println("Point-X: " + point.getX());
            System.out.println("Point-Y: " + point.getY());
        }else{
            System.out.println("Point is null");
        }

    }
}
