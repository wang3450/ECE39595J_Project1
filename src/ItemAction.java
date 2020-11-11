public class ItemAction extends Action {
    private Item item;
    private Creature owner;
    private String message;
    private int intValue;
    private char charValue;
    private String name;
    private String type;

    public ItemAction(){}
    public ItemAction (Item _owner){
        item = _owner;
    }
    public ItemAction (Creature _owner){this.owner = _owner;}

    public void setOwner(Creature _owner){owner = _owner;}
    public void setItem(Item _item){item = _item;}
    public void setName(String _name){name = _name;}
    public void setType(String _type){type = _type;}
    public void setIntValue(int _intValue){intValue = _intValue;}
    public void setCharValue(char c){charValue = c;}
    public void setMessage(String s){message = s;}

    public String getName(){return name;}
    public void printAllValues(){
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Item: " + item);
        System.out.println("Owner: " + owner);
        System.out.println("Message: " + message);
        System.out.println("IntValue: " + intValue);
        System.out.println("CharValue: " + charValue);
    }

}
