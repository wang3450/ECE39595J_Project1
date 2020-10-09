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
                System.out.println(s);
                
            }
            for(Item it: item){System.out.println(it);}
            for(ItemAction ia :iAction){System.out.println(ia);}
            for(CreatureAction ca: cAction){System.out.println(ca);}
            for(Creature c: creature){System.out.println(c);}
        } catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace(System.out);
        }
    }
        
}
