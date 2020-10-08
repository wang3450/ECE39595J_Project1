public class Player extends Creature {
    private int hp;
    private int hpm;
    private CreatureAction da;
    private CreatureAction ha;
    private Item weapon;
    private Item armor;
    private int posX;
    private int posY;

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Item armor) {
        this.armor = armor;
    }
}
