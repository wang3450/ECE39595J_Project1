import java.util.ArrayList;

public class structure extends Displayable {

    private ArrayList<Creature> creatures = new ArrayList<Creature>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private Armor armor;
    public structure(){super();}
    public void setArmor(Armor a) {armor = a;}
    public void addItem(Item i){items.add(i);}
    public void addCreature(Creature c) {creatures.add(c);}

}
