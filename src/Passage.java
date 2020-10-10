import java.util.ArrayList; 
public class Passage extends structure {
    private int room1;
    private int room2;
    private ArrayList<Point> points = new ArrayList<Point>();
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
    public void setVisible(boolean b){visible =b;}



    public void printAllValues(){
        System.out.println("Room number 1: " + room1);
        System.out.println("Room number 2: " + room2);
        System.out.println("Visible: " + visible);
        for(Point p : points){
            System.out.println("Point Array Point: " + p);
            if(p!=null) {
                System.out.println("Point-X: " + p.getX());
                System.out.println("Point-Y: " + p.getY());
            }else{
                System.out.println("Point is null.");
            }
        }


    }
}
