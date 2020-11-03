
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Random;
public class KeyStrokePrinter implements InputObserver {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    //private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private Player player;
    private int x;
    private int y;
    private int maxHit;
    private Displayable movePlayer = null;
    private int playerMaxHit;
    private int monsterMaxHit;
    private int playerHp;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;

    public KeyStrokePrinter(ObjectDisplayGrid grid, Player _player) {
        //inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
        player = _player;
        x = _player.getPoint().getX();
        y = _player.getPoint().getY();
        playerMaxHit = _player.getMaxHit();
        playerHp = _player.getHp();
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
            if(displayGrid.getObjectGrid()[x-1][y].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x-1][y].peek().getChar().getChar() == ' ')
                return false;
            else if(displayGrid.getObjectGrid()[x-1][y].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x-1][y].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x-1][y].peek().getChar().getChar() == 'H'){
                monsterMaxHit = displayGrid.getObjectGrid()[x-1][y].peek().getMaxHit();
                Random random = new Random();
                int monsterHp = displayGrid.getObjectGrid()[x-1][y].peek().getHp();
                int randomPlayer = random.nextInt(playerMaxHit + 1);
                int randomMonster = random.nextInt(monsterMaxHit + 1);
                displayGrid.getObjectGrid()[x-1][y].peek().setHp(monsterHp - randomPlayer);
                displayGrid.addObjectToDisplay(new Wall(new Char('M')), 6, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 7, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('n')), 8, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('s')), 9, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('t')), 10, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('e')), 11, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('r')), 12, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('T')), 14, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 15, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 16, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('k')), 17, HEIGHT -1 );
                int m_hundreds = randomPlayer / 10;
                int m_tens = 0;
                int m_ones = 0;

                if (m_hundreds == 10){
                    m_hundreds = m_hundreds / 10 + 48;
                    m_tens = 0 + 48;
                    m_ones = 0 + 48;
                }else{
                    m_hundreds = 0 + 48;
                    m_tens = randomPlayer / 10 + 48;
                    m_ones = randomPlayer % 10 + 48;
                }
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_hundreds)), 19,HEIGHT -1);
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_tens)), 20,HEIGHT -1);
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_ones)), 21,HEIGHT -1);

                displayGrid.addObjectToDisplay(new Wall(new Char('D')), 23, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('a')), 24, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('m')), 25, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('a')), 26, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('g')), 27, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('e')), 28, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('!')), 29, HEIGHT -1 );


                System.out.println("Monster Took Damage: " + randomPlayer);
                System.out.println("Monster Hp: " + displayGrid.getObjectGrid()[x-1][y].peek().getHp());
                if(displayGrid.getObjectGrid()[x-1][y].peek().getHp() <= 0)
                    displayGrid.removeObjectFromDisplay(x-1,y);
                else{
                    playerHp -= randomMonster;
                    int p_hundred = playerHp / 10;
                    int p_tens = 0;
                    int p_ones = 0;

                    if (p_hundred == 10){
                        p_hundred = p_hundred / 10 + 48;
                        p_tens = 0 + 48;
                        p_ones = 0 + 48;
                    }else{
                        p_hundred = 0 + 48;
                        p_tens = playerHp / 10 + 48;
                        p_ones = playerHp % 10 + 48;
                    }
                    int dp_hundred = randomMonster / 10;
                    int dp_tens = 0;
                    int dp_ones = 0;

                    if (dp_hundred == 10){
                        dp_hundred = dp_hundred / 10 + 48;
                        dp_tens = 0 + 48;
                        dp_ones = 0 + 48;
                    }else{
                        dp_hundred = 0 + 48;
                        dp_tens = randomMonster / 10 + 48;
                        dp_ones = randomMonster % 10 + 48;
                    }
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_hundred)), 3,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_tens)), 4,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_ones)), 5,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char('P')), 31, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('l')), 32, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 33, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('y')), 34, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('e')), 35, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('r')), 36, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('T')), 38, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('o')), 39, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('o')), 40, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('k')), 41, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_hundred)), 43,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_tens)), 44,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_ones)), 45,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char('D')), 47, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 48, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('m')), 49, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 50, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('g')), 51, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('e')), 52, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('!')), 53, HEIGHT -1 );
                    System.out.println("\n");
                    System.out.println("Player Took Damage: " + randomMonster);
                    System.out.println("Player Hp: " + playerHp);
                    if(playerHp <= 0)
                        System.out.println("PLZ End the Game!");
                }
            }
            else {
                movePlayer = displayGrid.removeObjectFromDisplay(x, y);
                displayGrid.addObjectToDisplay(movePlayer, x - 1, y);
                x -= 1;
            }
        }
        else if(ch == 'l' || ch == 'd'){
            if(displayGrid.getObjectGrid()[x+1][y].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x+1][y].peek().getChar().getChar() == ' ')
                return false;
            else if(displayGrid.getObjectGrid()[x+1][y].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x+1][y].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x+1][y].peek().getChar().getChar() == 'H'){
                monsterMaxHit = displayGrid.getObjectGrid()[x+1][y].peek().getMaxHit();
                Random random = new Random();
                int monsterHp = displayGrid.getObjectGrid()[x+1][y].peek().getHp();
                int randomPlayer = random.nextInt(playerMaxHit + 1);
                int randomMonster = random.nextInt(monsterMaxHit + 1);
                displayGrid.getObjectGrid()[x+1][y].peek().setHp(monsterHp - randomPlayer);
                displayGrid.addObjectToDisplay(new Wall(new Char('M')), 6, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 7, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('n')), 8, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('s')), 9, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('t')), 10, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('e')), 11, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('r')), 12, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('T')), 14, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 15, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 16, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('k')), 17, HEIGHT -1 );
                int m_hundreds = randomPlayer / 10;
                int m_tens = 0;
                int m_ones = 0;

                if (m_hundreds == 10){
                    m_hundreds = m_hundreds / 10 + 48;
                    m_tens = 0 + 48;
                    m_ones = 0 + 48;
                }else{
                    m_hundreds = 0 + 48;
                    m_tens = randomPlayer / 10 + 48;
                    m_ones = randomPlayer % 10 + 48;
                }
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_hundreds)), 19,HEIGHT -1);
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_tens)), 20,HEIGHT -1);
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_ones)), 21,HEIGHT -1);

                displayGrid.addObjectToDisplay(new Wall(new Char('D')), 23, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('a')), 24, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('m')), 25, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('a')), 26, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('g')), 27, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('e')), 28, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('!')), 29, HEIGHT -1 );


                System.out.println("Monster Took Damage: " + randomPlayer);
                System.out.println("Monster Hp: " + displayGrid.getObjectGrid()[x+1][y].peek().getHp());
                if(displayGrid.getObjectGrid()[x+1][y].peek().getHp() <= 0)
                    displayGrid.removeObjectFromDisplay(x+1,y);
                else{
                    playerHp -= randomMonster;
                    int p_hundred = playerHp / 10;
                    int p_tens = 0;
                    int p_ones = 0;

                    if (p_hundred == 10){
                        p_hundred = p_hundred / 10 + 48;
                        p_tens = 0 + 48;
                        p_ones = 0 + 48;
                    }else{
                        p_hundred = 0 + 48;
                        p_tens = playerHp / 10 + 48;
                        p_ones = playerHp % 10 + 48;
                    }
                    int dp_hundred = randomMonster / 10;
                    int dp_tens = 0;
                    int dp_ones = 0;

                    if (dp_hundred == 10){
                        dp_hundred = dp_hundred / 10 + 48;
                        dp_tens = 0 + 48;
                        dp_ones = 0 + 48;
                    }else{
                        dp_hundred = 0 + 48;
                        dp_tens = randomMonster / 10 + 48;
                        dp_ones = randomMonster % 10 + 48;
                    }
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_hundred)), 3,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_tens)), 4,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_ones)), 5,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char('P')), 31, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('l')), 32, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 33, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('y')), 34, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('e')), 35, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('r')), 36, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('T')), 38, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('o')), 39, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('o')), 40, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('k')), 41, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_hundred)), 43,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_tens)), 44,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_ones)), 45,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char('D')), 47, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 48, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('m')), 49, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 50, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('g')), 51, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('e')), 52, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('!')), 53, HEIGHT -1 );
                    if(playerHp <= 0)
                        System.out.println("PLZ End the Game!");
                }
            }
            else {
                movePlayer = displayGrid.removeObjectFromDisplay(x, y);
                displayGrid.addObjectToDisplay(movePlayer, x + 1, y);
                x += 1;
            }
        }
        else if(ch == 'j' || ch == 's'){
            if(displayGrid.getObjectGrid()[x][y+1].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x][y+1].peek().getChar().getChar() == ' ')
                return false;
            else if(displayGrid.getObjectGrid()[x][y+1].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x][y+1].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x][y+1].peek().getChar().getChar() == 'H'){
                monsterMaxHit = displayGrid.getObjectGrid()[x][y+1].peek().getMaxHit();
                Random random = new Random();
                int monsterHp = displayGrid.getObjectGrid()[x][y+1].peek().getHp();
                int randomPlayer = random.nextInt(playerMaxHit + 1);
                int randomMonster = random.nextInt(monsterMaxHit + 1);
                displayGrid.getObjectGrid()[x][y+1].peek().setHp(monsterHp - randomPlayer);
                displayGrid.addObjectToDisplay(new Wall(new Char('M')), 6, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 7, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('n')), 8, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('s')), 9, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('t')), 10, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('e')), 11, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('r')), 12, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('T')), 14, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 15, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 16, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('k')), 17, HEIGHT -1 );
                int m_hundreds = randomPlayer / 10;
                int m_tens = 0;
                int m_ones = 0;

                if (m_hundreds == 10){
                    m_hundreds = m_hundreds / 10 + 48;
                    m_tens = 0 + 48;
                    m_ones = 0 + 48;
                }else{
                    m_hundreds = 0 + 48;
                    m_tens = randomPlayer / 10 + 48;
                    m_ones = randomPlayer % 10 + 48;
                }
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_hundreds)), 19,HEIGHT -1);
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_tens)), 20,HEIGHT -1);
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_ones)), 21,HEIGHT -1);

                displayGrid.addObjectToDisplay(new Wall(new Char('D')), 23, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('a')), 24, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('m')), 25, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('a')), 26, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('g')), 27, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('e')), 28, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('!')), 29, HEIGHT -1 );


                System.out.println("Monster Took Damage: " + randomPlayer);
                System.out.println("Monster Hp: " + displayGrid.getObjectGrid()[x][y+1].peek().getHp());
                if(displayGrid.getObjectGrid()[x][y+1].peek().getHp() <= 0)
                    displayGrid.removeObjectFromDisplay(x,y+1);
                else{
                    playerHp -= randomMonster;
                    int p_hundred = playerHp / 10;
                    int p_tens = 0;
                    int p_ones = 0;

                    if (p_hundred == 10){
                        p_hundred = p_hundred / 10 + 48;
                        p_tens = 0 + 48;
                        p_ones = 0 + 48;
                    }else{
                        p_hundred = 0 + 48;
                        p_tens = playerHp / 10 + 48;
                        p_ones = playerHp % 10 + 48;
                    }
                    int dp_hundred = randomMonster / 10;
                    int dp_tens = 0;
                    int dp_ones = 0;

                    if (dp_hundred == 10){
                        dp_hundred = dp_hundred / 10 + 48;
                        dp_tens = 0 + 48;
                        dp_ones = 0 + 48;
                    }else{
                        dp_hundred = 0 + 48;
                        dp_tens = randomMonster / 10 + 48;
                        dp_ones = randomMonster % 10 + 48;
                    }
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_hundred)), 3,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_tens)), 4,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_ones)), 5,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char('P')), 31, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('l')), 32, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 33, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('y')), 34, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('e')), 35, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('r')), 36, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('T')), 38, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('o')), 39, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('o')), 40, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('k')), 41, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_hundred)), 43,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_tens)), 44,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_ones)), 45,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char('D')), 47, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 48, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('m')), 49, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 50, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('g')), 51, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('e')), 52, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('!')), 53, HEIGHT -1 );
                    if(playerHp <= 0)
                        System.out.println("PLZ End the Game!");
                }
            }
            else {
                movePlayer = displayGrid.removeObjectFromDisplay(x, y);
                displayGrid.addObjectToDisplay(movePlayer, x, y + 1);
                y += 1;
            }
        }
        else if(ch == 'k' || ch == 'w'){
            if(displayGrid.getObjectGrid()[x][y-1].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x][y-1].peek().getChar().getChar() == ' ')
                return false;
            else if(displayGrid.getObjectGrid()[x][y-1].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x][y-1].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x][y-1].peek().getChar().getChar() == 'H'){
                monsterMaxHit = displayGrid.getObjectGrid()[x][y-1].peek().getMaxHit();
                Random random = new Random();
                int monsterHp = displayGrid.getObjectGrid()[x][y-1].peek().getHp();
                int randomPlayer = random.nextInt(playerMaxHit + 1);
                int randomMonster = random.nextInt(monsterMaxHit + 1);
                displayGrid.getObjectGrid()[x][y-1].peek().setHp(monsterHp - randomPlayer);
                displayGrid.addObjectToDisplay(new Wall(new Char('M')), 6, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 7, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('n')), 8, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('s')), 9, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('t')), 10, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('e')), 11, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('r')), 12, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('T')), 14, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 15, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('o')), 16, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('k')), 17, HEIGHT -1 );
                int m_hundreds = randomPlayer / 10;
                int m_tens = 0;
                int m_ones = 0;

                if (m_hundreds == 10){
                    m_hundreds = m_hundreds / 10 + 48;
                    m_tens = 0 + 48;
                    m_ones = 0 + 48;
                }else{
                    m_hundreds = 0 + 48;
                    m_tens = randomPlayer / 10 + 48;
                    m_ones = randomPlayer % 10 + 48;
                }
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_hundreds)), 19,HEIGHT -1);
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_tens)), 20,HEIGHT -1);
                displayGrid.addObjectToDisplay(new Wall(new Char((char) m_ones)), 21,HEIGHT -1);

                displayGrid.addObjectToDisplay(new Wall(new Char('D')), 23, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('a')), 24, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('m')), 25, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('a')), 26, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('g')), 27, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('e')), 28, HEIGHT -1 );
                displayGrid.addObjectToDisplay(new Wall(new Char('!')), 29, HEIGHT -1 );


                System.out.println("Monster Took Damage: " + randomPlayer);
                System.out.println("Monster Hp: " + displayGrid.getObjectGrid()[x][y-1].peek().getHp());
                if(displayGrid.getObjectGrid()[x][y-1].peek().getHp() <= 0)
                    displayGrid.removeObjectFromDisplay(x,y-1);
                else{
                    playerHp -= randomMonster;
                    int p_hundred = playerHp / 10;
                    int p_tens = 0;
                    int p_ones = 0;

                    if (p_hundred == 10){
                        p_hundred = p_hundred / 10 + 48;
                        p_tens = 0 + 48;
                        p_ones = 0 + 48;
                    }else{
                        p_hundred = 0 + 48;
                        p_tens = playerHp / 10 + 48;
                        p_ones = playerHp % 10 + 48;
                    }
                    int dp_hundred = randomMonster / 10;
                    int dp_tens = 0;
                    int dp_ones = 0;

                    if (dp_hundred == 10){
                        dp_hundred = dp_hundred / 10 + 48;
                        dp_tens = 0 + 48;
                        dp_ones = 0 + 48;
                    }else{
                        dp_hundred = 0 + 48;
                        dp_tens = randomMonster / 10 + 48;
                        dp_ones = randomMonster % 10 + 48;
                    }
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_hundred)), 3,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_tens)), 4,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_ones)), 5,0);
                    displayGrid.addObjectToDisplay(new Wall(new Char('P')), 31, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('l')), 32, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 33, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('y')), 34, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('e')), 35, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('r')), 36, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('T')), 38, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('o')), 39, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('o')), 40, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('k')), 41, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_hundred)), 43,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_tens)), 44,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_ones)), 45,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char('D')), 47, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 48, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('m')), 49, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('a')), 50, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('g')), 51, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('e')), 52, HEIGHT -1 );
                    displayGrid.addObjectToDisplay(new Wall(new Char('!')), 53, HEIGHT -1 );
                    if(playerHp <= 0)
                        System.out.println("PLZ End the Game!");
                }
            }
            else {
                movePlayer = displayGrid.removeObjectFromDisplay(x, y);
                displayGrid.addObjectToDisplay(movePlayer, x, y - 1);
                y -= 1;
            }
        }
        else if(ch == 'p'){
            Displayable player = displayGrid.removeObjectFromDisplay(x,y);
            Displayable item = null;
            if(displayGrid.getObjectGrid()[x][y].peek().getChar().getChar() == '.'){
                displayGrid.addObjectToDisplay(player,x,y);
                return false;
            }
            else{
                item = displayGrid.removeObjectFromDisplay(x,y);
                player.setInventory((Item) item);
                displayGrid.addObjectToDisplay(player,x,y);
                System.out.println(player.getInventory().get(0));
            }
        }
        else if(ch == 'q'){
            if(player.getInventory() == null){
                System.out.println("Nothing in Inventory");
                return false;
            }
            else{
                Displayable player = displayGrid.removeObjectFromDisplay(x,y);
                ArrayList<Item> inventory = player.getInventory();
                Item dropped = inventory.get(inventory.size() -1);
                displayGrid.addObjectToDisplay((Displayable) dropped,x,y);
                displayGrid.addObjectToDisplay(player,x,y);
            }
        }
        else {
            System.out.println("character " + ch + " entered on the keyboard");
        }
        return true;

    }
}
