public class Item extends Displayable {
    private Creature owner;
    private Creature c = new Creature();
    private ItemAction action = new ItemAction(c);
    public void setOwner(Creature _owner){owner = _owner;}
    public void setItemAction(ItemAction ia){action = ia;}

}
