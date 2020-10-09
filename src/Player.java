public class Player extends Creature {
    private int hp;
    private int hpm;
    private CreatureAction da;
    private CreatureAction ha;
    private Item weapon;
    private Item armor;
    private int posX;
    private int posY;
    private int serial;
    private int room;
    private String name;

    public void setRoom(int _room){room = _room;}
    public void setSerial(int _serial){serial = _serial;}

    public void setWeapon(Item _weapon) {
        weapon = _weapon;
    }

    public void setArmor(Item _armor) {
        armor = _armor;
    }
    
    public void setName(String n){name = n;}
}
