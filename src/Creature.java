import java.util.ArrayList; 
public class Creature extends Displayable {
    private int Hp;
    private int HpMove;
    private int maxHit;
    private int posX;
    private int posY;
    private ArrayList<CreatureAction> creatureActions = new ArrayList<CreatureAction>();
    private CreatureAction dAction;
    private CreatureAction hitAction;
    private Item weapon;
    private Item armor;

    public Creature(){super();}

    @Override
    public void setHp(int _hp){Hp = _hp;}

    @Override
    public void setHpMove(int _hpMoves) {HpMove = _hpMoves;}
    public void setWeapon(Item _weapon) {
        weapon = _weapon;
    }
    public void setArmor(Item _armor) {
        armor = _armor;
    }
    public void setCreatureActions(CreatureAction ca){creatureActions.add(ca);}
    public void setDeathAction(CreatureAction da){dAction = da;}

    public void setHitAction(CreatureAction ha) {hitAction = ha;}


}
