import asciiPanel.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ObjectDisplayGrid extends JFrame implements KeyListener, InputSubject {

    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";

    private static AsciiPanel terminal;
    //private Char[][] objectGrid = null;
    private Stack<Displayable>[][] objectGrid = null;

    private List<InputObserver> inputObservers = new ArrayList<InputObserver>();

    private static int height;
    private static int width;

    public Stack<Displayable>[][] getObjectGrid(){return objectGrid;}

    public ObjectDisplayGrid(int _width, int _height) {
        width = _width;
        height = _height;

        terminal = new AsciiPanel(width, height);

        objectGrid = new Stack[width][height];
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                objectGrid[i][j] = new Stack<Displayable>();
            }
        }

        initializeDisplay();

        super.add(terminal);
        super.setSize(740, 680);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super.repaint();
        // terminal.repaint( );
        super.setVisible(true);
        terminal.setVisible(true);
        super.addKeyListener(this);
        inputObservers = new ArrayList<>();
        super.repaint();
    }

    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
//        inputObservers.add(observer);
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }

    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            boolean flag = observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }
        }
    }

    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public final void initializeDisplay() {
        Char ch = new Char(' ');
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                addObjectToDisplay(new Displayable(ch), i, j);
            }
        }
        terminal.repaint();
    }

    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void addObjectToDisplay(Displayable ch, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                objectGrid[x][y].push(ch);
                writeToTerminal(x, y);
            }
        }
    }
    public Displayable removeObjectFromDisplay(int x, int y) {
        Displayable displayable = null;
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                displayable = objectGrid[x][y].pop();
                writeToTerminal(x, y);
            }
        }
        return displayable;
    }
    public void addStringToDisplay(String s, int x, int y){
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                terminal.write(s,x,y);
                terminal.repaint();
            }
        }
    }

    private void writeToTerminal(int x, int y) {
        Displayable d = objectGrid[x][y].peek();
        if(d.getChar().getChar() == '@'){
            terminal.write(d.getChar().getChar(),x,y, AsciiPanel.brightCyan);
            terminal.repaint();
        }
        else if(d.getChar().getChar() == 'x') {
            terminal.write(d.getChar().getChar(),x,y,AsciiPanel.brown);
            terminal.repaint();
        }
        else if(d.getChar().getChar() == 'S' && d instanceof Monster) {
            terminal.write(d.getChar().getChar(),x,y,AsciiPanel.newGreen);
            terminal.repaint();
        }
        else if(d.getChar().getChar() == 'T' && d instanceof Monster) {
            terminal.write(d.getChar().getChar(),x,y,AsciiPanel.brightYellow);
            terminal.repaint();
        }
        else if(d.getChar().getChar() == 'H' && d instanceof Monster) {
            terminal.write(d.getChar().getChar(),x,y,AsciiPanel.pinkOrange);
            terminal.repaint();
        }
        else if(d.getChar().getChar() == '#') {
            terminal.write(d.getChar().getChar(),x,y,AsciiPanel.dirtBrown);
            terminal.repaint();
        }
        else if(d.getChar().getChar() == '+') {
            terminal.write(d.getChar().getChar(),x,y,AsciiPanel.brown);
            terminal.repaint();
        }
        else if(d.getChar().getChar() == 'i'){
            terminal.write(d.getChar().getChar(),x,y);
            terminal.repaint();
        }
        else{
            terminal.write(d.getChar().getChar(),x,y);
            terminal.repaint();
        }
        terminal.repaint();
    }
}
