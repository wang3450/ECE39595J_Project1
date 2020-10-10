import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Test {
    public static void main(String[] args){
        String fileName = null;
        switch(args.length){
            case 1:
                fileName = "xmlFiles/"+args[0];
                break;
            default:
                System.out.println("Invalid Argument");
                return;
        }

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try{
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            saxParser.parse(new File(fileName), handler);
            ArrayList<structure> structures = handler.getStructures();
            ArrayList<Item> item = handler.getItem();
            ArrayList<ItemAction> iAction = handler.getItemAction();
            ArrayList<CreatureAction> cAction = handler.getCreatureAction();
            ArrayList<Creature> creature = handler.getCreature();

            int i;
            for(structure s : structures){
                if(s instanceof Room){
                    System.out.println(s);
                    ((Room) s).printAllValues();
                    System.out.println("\n");
                }
                else if (s instanceof Passage){
                    System.out.println(s);
                    ((Passage) s).printAllValues();
                    System.out.println("\n");
                }
                
            }

            for(Item it: item){
                if(it instanceof Armor){
                    System.out.println(it);
                    ((Armor) it).printAllValues();
                    System.out.println("\n");
                }
                else if (it instanceof Scroll){
                    System.out.println(it);
                    ((Scroll) it).printAllValues();
                    System.out.println("\n");
                }
                else if(it instanceof Sword){
                    System.out.println(it);
                    ((Sword) it).printAllValues();
                    System.out.println("\n");
                }
            }

            for(ItemAction ia :iAction){
                System.out.println(ia);
                ia.printAllValues();
                System.out.println("\n");
            }

            for(CreatureAction ca: cAction){
                System.out.println(ca);
                ca.printAllValues();
                System.out.println("\n");
            }
            for(Creature c: creature){
                System.out.println(c);
                if(c instanceof Player){
                    ((Player) c).printAllValues();
                    System.out.println("\n");
                }
                else if (c instanceof Monster){
                    ((Monster) c).printAllValues();
                    System.out.println("\n");
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace(System.out);
        }
    }
        
}
