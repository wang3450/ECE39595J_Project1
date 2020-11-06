
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Random;
public class KeyStrokePrinter implements InputObserver {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    //private static Queue<Character> inputQueue = null;
    private ObjectDisplayGrid displayGrid;
    private Displayable player;
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
                displayGrid.addStringToDisplay("Monster Took", 6, HEIGHT -1);
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
                displayGrid.addStringToDisplay("Damage!",23, HEIGHT -1);


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
                    displayGrid.addStringToDisplay("Player Took",31,HEIGHT-1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_hundred)), 43,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_tens)), 44,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_ones)), 45,HEIGHT -1);
                    displayGrid.addStringToDisplay("Damage!",47,HEIGHT-1);
                    System.out.println("\n");
                    System.out.println("Player Took Damage: " + randomMonster);
                    System.out.println("Player Hp: " + playerHp);
                    if(playerHp <= 0){
                        System.out.println("PLZ End the Game!");
                        displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT-1);
                    }

                }
            }
            else {
                player = displayGrid.removeObjectFromDisplay(x, y);
                displayGrid.addObjectToDisplay(player, x - 1, y);
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
                displayGrid.addStringToDisplay("Monster Took",6,HEIGHT-1);
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
                displayGrid.addStringToDisplay("Damage!",23,HEIGHT-1);


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
                    displayGrid.addStringToDisplay("Player Took",31, HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_hundred)), 43,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_tens)), 44,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_ones)), 45,HEIGHT -1);
                    displayGrid.addStringToDisplay("Damage!", 47, HEIGHT-1);
                    System.out.println("\n");
                    System.out.println("Player Took Damage: " + randomMonster);
                    System.out.println("Player Hp: " + playerHp);
                    if(playerHp <= 0){
                        System.out.println("PLZ End the Game!");
                        displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT-1);
                    }
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
                displayGrid.addStringToDisplay("Monster Took",6,HEIGHT-1);
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
                displayGrid.addStringToDisplay("Damage!",23,HEIGHT-1);


                System.out.println("Monster Took Damage: " + randomPlayer);
                System.out.println("Monster Hp: " + displayGrid.getObjectGrid()[x][y+1].peek().getHp());
                if(displayGrid.getObjectGrid()[x][y+1].peek().getHp() <= 0)
                    displayGrid.removeObjectFromDisplay(x,y+1);
                else {
                    playerHp -= randomMonster;
                    int p_hundred = playerHp / 10;
                    int p_tens = 0;
                    int p_ones = 0;

                    if (p_hundred == 10) {
                        p_hundred = p_hundred / 10 + 48;
                        p_tens = 0 + 48;
                        p_ones = 0 + 48;
                    } else {
                        p_hundred = 0 + 48;
                        p_tens = playerHp / 10 + 48;
                        p_ones = playerHp % 10 + 48;
                    }
                    int dp_hundred = randomMonster / 10;
                    int dp_tens = 0;
                    int dp_ones = 0;

                    if (dp_hundred == 10) {
                        dp_hundred = dp_hundred / 10 + 48;
                        dp_tens = 0 + 48;
                        dp_ones = 0 + 48;
                    } else {
                        dp_hundred = 0 + 48;
                        dp_tens = randomMonster / 10 + 48;
                        dp_ones = randomMonster % 10 + 48;
                    }
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_hundred)), 3, 0);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_tens)), 4, 0);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) p_ones)), 5, 0);
                    displayGrid.addStringToDisplay("Player Took", 31, HEIGHT - 1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_hundred)), 43, HEIGHT - 1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_tens)), 44, HEIGHT - 1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_ones)), 45, HEIGHT - 1);
                    displayGrid.addStringToDisplay("Damage!", 47, HEIGHT - 1);
                    System.out.println("\n");
                    System.out.println("Player Took Damage: " + randomMonster);
                    System.out.println("Player Hp: " + playerHp);
                    if (playerHp <= 0) {
                        System.out.println("PLZ End the Game!");
                        displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT-1);
                    }
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
                displayGrid.addStringToDisplay("Monster Took",6,HEIGHT-1);
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
                displayGrid.addStringToDisplay("Damage!",23,HEIGHT-1);


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
                    displayGrid.addStringToDisplay("Player Took", 31, HEIGHT - 1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_hundred)), 43,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_tens)), 44,HEIGHT -1);
                    displayGrid.addObjectToDisplay(new Wall(new Char((char) dp_ones)), 45,HEIGHT -1);
                    displayGrid.addStringToDisplay("Damage!", 47, HEIGHT - 1);
                    System.out.println("\n");
                    System.out.println("Player Took Damage: " + randomMonster);
                    System.out.println("Player Hp: " + playerHp);
                    if (playerHp <= 0) {
                        System.out.println("PLZ End the Game!");
                        displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT-1);
                    }
                }
            }
            else {
                movePlayer = displayGrid.removeObjectFromDisplay(x, y);
                displayGrid.addObjectToDisplay(movePlayer, x, y - 1);
                y -= 1;
            }
        }
        else if(ch == 'p') {
            System.out.println(displayGrid.removeObjectFromDisplay(x,y));
            if(displayGrid.getObjectGrid()[x][y].peek().getChar().getChar() == '.'){
                displayGrid.addObjectToDisplay(player,x,y);
                displayGrid.addStringToDisplay("There is nothing to pick up here                                      ",6,HEIGHT-1);
            }
            else{
                player.setInventory((Item) displayGrid.removeObjectFromDisplay(x,y));
                displayGrid.addObjectToDisplay(player,x,y);
            }

        }
        else if(ch == 'q'){
            System.out.println(player.getInventory());
            if(player.getInventory().isEmpty()){
                displayGrid.addStringToDisplay("Nothing In Inventory To Drop!                                ",6, HEIGHT-1);
            }
            else{
                displayGrid.removeObjectFromDisplay(x,y);
                displayGrid.addObjectToDisplay(player.getInventory().get(player.getInventory().size() -1),x,y );
                player.dropItem();
                System.out.println(player.getInventory());
                displayGrid.addObjectToDisplay(player,x,y);
                displayGrid.addStringToDisplay("Player dropped the last item picked up                      ",6,HEIGHT-1);
            }
        }
        else if(ch == 'i') {
            if(player.getInventory().isEmpty())
                displayGrid.addStringToDisplay("Player Inventory is Empty!                        ",6,HEIGHT-3);
            else{
                String str ="";
                for(int i = 0; i < player.getInventory().size();i++){
                    if (player.getInventory().get(i) instanceof Scroll)
                        str += "(" + (i + 1) + ")" + "Scroll, ";
                    else if (player.getInventory().get(i) instanceof Armor)
                        str += "(" + (i + 1) + ")" + "Armor, ";
                    else if (player.getInventory().get(i) instanceof Sword)
                        str += "(" + (i + 1) + ")" + "Sword, ";
                }
                str = str.replaceAll(", $", "");
                displayGrid.addStringToDisplay(str+"                       ",6,HEIGHT-3);
            }
        }

        else {
            System.out.println("character " + ch + " entered on the keyboard");
        }
        return true;

    }
}
