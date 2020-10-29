public class Sword extends Item{
        private int visible;
        private Point point = new Point(0,0);
        private String name;
        private int roomLocation;
        private int serial;
        private int IntValue;

        public Sword(String _name){
            name = _name;
        }

        public void setID(int _room, int _serial){
            this.roomLocation = _room;
            this.serial = _serial;
        }
        public void setPoint(Point p){point = p;}
        public Point getPoint(){return point;}


    public void printAllValues(){
        System.out.println("Name: " + name);
        System.out.println("Visible: " + visible);
        System.out.println("Room Location: " + roomLocation);
        System.out.println("Serial: " + serial);
        System.out.println("Point: " + point);
        if(point!=null) {
            System.out.println("Point-X: " + point.getX());
            System.out.println("Point-Y: " + point.getY());
        }else{
            System.out.println("Point is null.");
        }
        System.out.println("IntValue: " + IntValue);

    }
}

