import java.util.ArrayList; 
public class Creature {
    private int hp;
    private int hpMoves;
    private int maxHit;
    private int posX;
    private int posY;
    private ArrayList<CreatureAction> creatureActions = new ArrayList<CreatureAction>();
    private CreatureAction dAction;
    private CreatureAction hitAction;

    public Creature(){super();}

    @override 
    public void setHp(int _hp){hp = _hp;}
    
    @override
    public void setHpMoves(int _hpMoves) {hpMoves = _hpMoves;}

    public void setDeathAction(CreatureAction da){dAction = da;}

    public void setHitAction(CreatureAction ha) {hitAction = ha;}


}
