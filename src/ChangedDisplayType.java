public class ChangedDisplayType extends CreatureAction {
    
    private String name;
    private Creature owner;
    public ChangedDisplayType(String _name, Creature _owner){
        super(_owner);
        name = _name;
    }
}