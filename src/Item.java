public class Item extends Displayable {
    private Creature owner;
    private Creature c = new Creature();
    private ItemAction action = new ItemAction(c);
    private Char displayChar;

    public Item(Char ch){
        super(ch);
    }
    public Item(){}

    public void setOwner(Creature _owner){owner = _owner;}
    public void setItemAction(ItemAction ia){action = ia;}

}
