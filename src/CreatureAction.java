public class CreatureAction extends Action {
    private Creature creature;
    private String message;
    private int intValue;
    private char charValue;
    private String name;
    private String type;

    public void setName(String n){name = n;}
    public void setType(String t){type = t;}

    public CreatureAction (Creature owner){
        creature = owner;
    }

    public CreatureAction(){}
    
}
