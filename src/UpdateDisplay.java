public class UpdateDisplay extends CreatureAction {
    
    private String name;
    private Creature owner;
    public UpdateDisplay(String _name, Creature _owner){
        super(_owner);
        name = _name;
    }
}