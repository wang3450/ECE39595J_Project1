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

    public Creature(){super();}

    @Override
    public void setHp(int _hp){Hp = _hp;}

    @Override
    public void setHpMove(int _hpMoves) {HpMove = _hpMoves;}

    public void setDeathAction(CreatureAction da){dAction = da;}

    public void setHitAction(CreatureAction ha) {hitAction = ha;}


}
