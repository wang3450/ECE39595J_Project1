public class Sword extends Item{
        private int visible;
        private int posX;
        private int posY;
        private String name;
        private int roomLocation;
        private int serial;
        //need ItemIntValue

        public Sword(String _name){
            this.name = _name;
        }

        public void setID(int _room, int _serial){
            this.roomLocation = _room;
            this.serial = _serial;
        }
}

