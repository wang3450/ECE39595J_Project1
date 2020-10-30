
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class KeyStrokePrinter implements InputObserver {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    //private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private Player player;
    private int x;
    private int y;
    public KeyStrokePrinter(ObjectDisplayGrid grid, Player _player) {
        //inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
        player = _player;
        x = _player.getPoint().getX();
        y = _player.getPoint().getY();
    }

    @Override
    public boolean observerUpdate(char ch) {

        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        if (ch == 'X') {
            System.out.println("got an X, ending input checking");
            return false;
        }
        else if(ch == 'h' || ch == 'a'){
            if(displayGrid.getObjectGrid()[x-1][y].peek().getChar() == 'x')
                return false;
            else if(displayGrid.getObjectGrid()[x-1][y].peek().getChar() == ' '){
                System.out.println("invalid left");
                return false;
            }
            else {
                displayGrid.removeObjectFromDisplay(new Char('t'), x, y);
                x -= 1;
                displayGrid.addObjectToDisplay(new Char('@'), x, y);
            }
        }
        else if(ch == 'l' || ch == 'd'){
            if(displayGrid.getObjectGrid()[x+1][y].peek().getChar() == 'x')
                return false;
            else if(displayGrid.getObjectGrid()[x+1][y].peek().getChar() == ' '){
                System.out.println("invalid right");
                return false;
            }
            else {
                displayGrid.removeObjectFromDisplay(new Char('t'), x, y);
                x += 1;
                displayGrid.addObjectToDisplay(new Char('@'), x, y);
            }
        }
        else if(ch == 'j' || ch == 's'){
            if(displayGrid.getObjectGrid()[x][y+1].peek().getChar() == 'x')
                return false;
            else if(displayGrid.getObjectGrid()[x][y+1].peek().getChar() == ' '){
                System.out.println("invalid down");
                return false;
            }
            else {
                displayGrid.removeObjectFromDisplay(new Char('t'), x, y);
                y += 1;
                displayGrid.addObjectToDisplay(new Char('@'), x, y);
            }
        }
        else if(ch == 'k' || ch == 'w'){
            if(displayGrid.getObjectGrid()[x][y-1].peek().getChar() == 'x')
                return false;
            else if(displayGrid.getObjectGrid()[x][y-1].peek().getChar() == ' '){
                System.out.println("invalid up");
                return false;
            }
            else {
                displayGrid.removeObjectFromDisplay(new Char('t'), x, y);
                y -= 1;
                displayGrid.addObjectToDisplay(new Char('@'), x, y);
            }
        }

        else {
            System.out.println("character " + ch + " entered on the keyboard");
        }
        return true;

    }
}