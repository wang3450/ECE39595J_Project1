public class ItemAction extends Action {
    private Item item;
    private Creature owner;
    private String message;
    private int intValue;
    private char charValue;

    public ItemAction (Item _owner){
        this.item = _owner;
    }
    public ItemAction (Creature _owner){this.owner = _owner;}
}
