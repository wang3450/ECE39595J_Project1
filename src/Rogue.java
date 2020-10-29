import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class Rogue{

    //field vars
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static ObjectDisplayGrid displayGrid = null;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    private Dungeon dungeon = null;
    private ArrayList<structure> structures = new ArrayList<structure>();

    //constructor
    public Rogue(int width, int height) { displayGrid = new ObjectDisplayGrid(width, height);}

    //static draw functions
    public int drawDungeon(Dungeon d){
        String name;
        int width, topHeight, gameHeight,bottomHeight;
        name = d.getName();
        width = d.getWidth();
        topHeight = d.getTopHeight();
        gameHeight = d.getGameHeight();
        bottomHeight = d.getBottomHeight();

        int tempX, tempY;
        int x = 0;
        int y = 0;
        for(y = topHeight; y< topHeight + gameHeight; y++){
            displayGrid.addObjectToDisplay(new Char('|'), x, y );
        }
        tempY = y -1;
        tempX = x;
        for(x = tempX; x < width + tempX; x++){
            displayGrid.addObjectToDisplay(new Char('-'), x, tempY);
        }
        tempX = x -1;
        for(y = tempY; y >= topHeight; y--){
            displayGrid.addObjectToDisplay(new Char('|'), tempX, y);
        }
        tempY = y + 1;
        for(x = tempX; x > 0; x--){
            displayGrid.addObjectToDisplay(new Char('-'), x, tempY);
        }
        return topHeight;
    }

    public void drawRoom(structure s, int topHeight){
        int x;
        int y;
        int width;
        int height;
        Room r = (Room) s;

        width = r.getWidth();
        height = r.getHeight();

        Point p = r.getPoint();
        x = p.getX();
        y = p.getY() + topHeight;

        int tempX = x;
        int tempY = y;

        for(y = tempY; y < tempY + height; y++){
            displayGrid.addObjectToDisplay(new Char('x'), x, y);
        }
        tempY = y -1 ;
        for(x = tempX; x < width + tempX; x++){
            displayGrid.addObjectToDisplay(new Char('x'), x, tempY);
        }
        tempX = x -1;
        for(y = tempY; y >= p.getY() + topHeight; y--){
            displayGrid.addObjectToDisplay(new Char('x'), tempX, y);
        }
        tempY = y + 1;
        for(x = tempX; x >= p.getX(); x--){
            displayGrid.addObjectToDisplay(new Char('x'), x, tempY);
        }

        ArrayList<Item> items = r.getItems();
        ArrayList<Creature> creatures = r.getCreatures();

        for(Item i: items){
            if(i instanceof Scroll) {
                Scroll scroll = (Scroll) i;
                int a = scroll.getPoint().getX() + r.getPoint().getX();
                int b = scroll.getPoint().getY() + r.getPoint().getY() + topHeight;
                displayGrid.addObjectToDisplay(new Char('?'), a, b);
            }
            else if(i instanceof Armor) {
                Armor armor = (Armor) i;
                int a = armor.getPoint().getX() + r.getPoint().getX();
                int b = armor.getPoint().getY() + r.getPoint().getY() + topHeight;
                displayGrid.addObjectToDisplay(new Char(']'), a, b);
            }
            else if(i instanceof Sword){
                Sword sword = (Sword) i;
                int a = sword.getPoint().getX() + r.getPoint().getX();
                int b = sword.getPoint().getY() + r.getPoint().getY() + topHeight;
                displayGrid.addObjectToDisplay(new Char(')'), a, b);
            }
        }
        for(Creature c: creatures){
            if(c instanceof Player){
                Player player = (Player) c;
                int a = player.getPoint().getX() + r.getPoint().getX();
                int b = player.getPoint().getY() + r.getPoint().getY() + topHeight;
                displayGrid.addObjectToDisplay(new Char('@'), a, b);
            }
            else if(c instanceof Monster){
                Monster monster = (Monster) c;
                if(monster.getName().equals("Troll")){
                    int a = monster.getPoint().getX() + r.getPoint().getX();
                    int b = monster.getPoint().getY() + r.getPoint().getY() + topHeight;
                    displayGrid.addObjectToDisplay(new Char('T'), a, b);
                }
                else if(monster.getName().equals("Snake") || monster.getName().equals("S")){
                    int a = monster.getPoint().getX() + r.getPoint().getX();
                    int b = monster.getPoint().getY() + r.getPoint().getY() + topHeight;
                    displayGrid.addObjectToDisplay(new Char('S'), a, b);
                }
                else if(monster.getName().equals("Hobgoblin")){
                    int a = monster.getPoint().getX() + r.getPoint().getX();
                    int b = monster.getPoint().getY() + r.getPoint().getY() + topHeight;
                    displayGrid.addObjectToDisplay(new Char('H'), a, b);
                }
            }
        }
    }

    public void drawPassage(structure s, int topheight){
        Passage passage = (Passage) s;
        ArrayList<Point> points = passage.getPoints();
        int count = points.size();
        System.out.println(count);

        for(int j = 0; j < count-1; j++){

            int x1 = points.get(j).getX();
            int x2 = points.get(j+1).getX();
            int y1 = points.get(j).getY() + topheight;
            int y2 = points.get(j+1).getY() + topheight;

            if(x1 - x2 == 0){
                if(y1 - y2 < 0){
                    for(int k = y1; k <= y2; k++){
                        displayGrid.addObjectToDisplay(new Char('#'),x1, k);
                    }
                }
                else{
                    for(int m = y2; m <= y1; m++){
                        displayGrid.addObjectToDisplay(new Char('#'),x1, m);
                    }
                }
            }
            else{
                if(x1 - x2 <0){
                    for(int l = x1; l <= x2; l++){
                        displayGrid.addObjectToDisplay(new Char('#'),l, y1);
                    }
                }
                else{
                    for(int n = x2; n <= x1; n++){
                        displayGrid.addObjectToDisplay(new Char('#'),n, y1);
                    }
                }
            }
        }
        for(int i = 0; i < count; i++){
            if(i == 0 || i == count -1){
                displayGrid.addObjectToDisplay(new Char('+'), points.get(i).getX(), points.get(i).getY() + topheight );
            }
        }

    }

    public static void main(String[] args){

        String fileName = null;
        switch (args.length) {
            case 1:
                fileName = "xmlFiles/" + args[0];
                break;
            default:
                System.out.println("Invalid Argument");
                return;
        }

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            saxParser.parse(new File(fileName), handler);
            ArrayList<structure> structures = new ArrayList<structure>();
            Dungeon dungeon = null;
            structures = handler.getStructures();
            dungeon = handler.getDungeon();
            Rogue rogue = new Rogue(dungeon.getWidth(), dungeon.getGameHeight() + dungeon.getTopHeight() + dungeon.getBottomHeight());
            int topHeight = rogue.drawDungeon(dungeon);

            for(structure s: structures){
                System.out.println(s);
                if(s instanceof Room){
                    rogue.drawRoom(s, topHeight);
                }
                else if(s instanceof Passage){
                    rogue.drawPassage(s, topHeight);
                }
            }



        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
