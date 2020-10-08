public class CreatureAction extends Action {
    private Creature creature;
    private String message;
    private int intValue;
    private char charValue;

    public CreatureAction (Creature owner){
        this.creature = owner;
    }
}
