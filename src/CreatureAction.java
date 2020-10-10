public class CreatureAction extends Action {
    private Creature creature;
    private String message;
    private int intValue;
    private char charValue;
    private String name;
    private String type;

    public void setName(String n){name = n;}
    public void setType(String t){type = t;}
    public void setMessage(String s){message = s;}
    public void setIntValue(int i){intValue = i;}
    public void setCharValue(char c){charValue = c;}
    public CreatureAction (Creature owner){
        creature = owner;
    }

    public CreatureAction(){}

    public void printAllValues(){
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Creature: " + creature);
        System.out.println("Message: " + message);
        System.out.println("IntValue: " + intValue);
        System.out.println("CharValue: " + charValue);
    }
    
}
