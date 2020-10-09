public class Sword extends Item{
        private int visible;
        private Point point;
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
}

