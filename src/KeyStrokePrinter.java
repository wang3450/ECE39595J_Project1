
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
    private int playerMaxHit;
    private int monsterMaxHit;
    private int playerHp;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;
    private Item armor;
    private Item sword;
    private boolean dropFlag = false;

    public KeyStrokePrinter(ObjectDisplayGrid grid, Player _player) {
        //inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
        player = _player;
        x = _player.getPoint().getX();
        y = _player.getPoint().getY();
        playerMaxHit = _player.getMaxHit();
        playerHp = _player.getHp();
        armor = _player.getArmor();
        sword = _player.getSword();
    }

    public int getDigits(int x){
        int count = 0;
        if(x == 0)
            return 1;
        else
            for(; x!= 0; x /= 10, ++ count){}

        return count;
    }

    @Override
    public boolean observerUpdate(char ch) {
        while(playerHp >0) {
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
            }
            if (ch == 'X') {
                System.out.println("got an X, ending input checking");
                return false;
            }
            else if (ch == 'h' || ch == 'a') {
                if (displayGrid.getObjectGrid()[x - 1][y].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x - 1][y].peek().getChar().getChar() == ' ')
                    return false;
                else if (displayGrid.getObjectGrid()[x - 1][y].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x - 1][y].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x - 1][y].peek().getChar().getChar() == 'H') {
                    monsterMaxHit = displayGrid.getObjectGrid()[x - 1][y].peek().getMaxHit();
                    Random random = new Random();
                    int monsterHp = displayGrid.getObjectGrid()[x - 1][y].peek().getHp();
                    int randomPlayer = random.nextInt(playerMaxHit + 1);
                    int randomMonster = random.nextInt(monsterMaxHit + 1);
                    displayGrid.getObjectGrid()[x - 1][y].peek().setHp(monsterHp - randomPlayer);
                    displayGrid.addStringToDisplay("Monster Took", 6, HEIGHT - 1);
                    if(getDigits(randomPlayer) == 2)
                        displayGrid.addStringToDisplay("0"+Integer.toString(randomPlayer),19,HEIGHT-1);
                    else if(getDigits(randomPlayer) == 1)
                        displayGrid.addStringToDisplay("00"+Integer.toString(randomPlayer),19,HEIGHT-1);
                    displayGrid.addStringToDisplay("Damage!", 23, HEIGHT - 1);
                    System.out.println("Monster Took Damage: " + randomPlayer);
                    System.out.println("Monster Hp: " + displayGrid.getObjectGrid()[x - 1][y].peek().getHp());
                    if (displayGrid.getObjectGrid()[x - 1][y].peek().getHp() <= 0)
                        displayGrid.removeObjectFromDisplay(x - 1, y);
                    else {
                        playerHp -= randomMonster;
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);

                        displayGrid.addStringToDisplay("Player Took", 31, HEIGHT - 1);

                        if(getDigits(randomMonster) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(randomMonster),43,HEIGHT-1);
                        else if(getDigits((randomMonster)) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(randomMonster),43,HEIGHT-1);

                        displayGrid.addStringToDisplay("Damage!", 47, HEIGHT - 1);
                        System.out.println("\n");
                        System.out.println("Player Took Damage: " + randomMonster);
                        System.out.println("Player Hp: " + playerHp);

                        if (playerHp <= 0) {
                            System.out.println("PLZ End the Game!");
                            displayGrid.addStringToDisplay("HP:000 Score:0",0,0);
                            displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT - 1);
                        }
                    }
                }
                else {
                    player = displayGrid.removeObjectFromDisplay(x, y);
                    displayGrid.addObjectToDisplay(player, x - 1, y);
                    x -= 1;
                }
            }
            else if (ch == 'l' || ch == 'd') {
                if (displayGrid.getObjectGrid()[x + 1][y].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x + 1][y].peek().getChar().getChar() == ' ')
                    return false;
                else if (displayGrid.getObjectGrid()[x + 1][y].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x + 1][y].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x + 1][y].peek().getChar().getChar() == 'H') {
                    monsterMaxHit = displayGrid.getObjectGrid()[x + 1][y].peek().getMaxHit();
                    Random random = new Random();
                    int monsterHp = displayGrid.getObjectGrid()[x + 1][y].peek().getHp();
                    int randomPlayer = random.nextInt(playerMaxHit + 1);
                    int randomMonster = random.nextInt(monsterMaxHit + 1);
                    displayGrid.getObjectGrid()[x + 1][y].peek().setHp(monsterHp - randomPlayer);
                    displayGrid.addStringToDisplay("Monster Took", 6, HEIGHT - 1);

                    if(getDigits(randomPlayer) == 2)
                        displayGrid.addStringToDisplay("0"+Integer.toString(randomPlayer),19,HEIGHT-1);
                    else if(getDigits(randomPlayer) == 1)
                        displayGrid.addStringToDisplay("00"+Integer.toString(randomPlayer),19,HEIGHT-1);

                    displayGrid.addStringToDisplay("Damage!", 23, HEIGHT - 1);

                    System.out.println("Monster Took Damage: " + randomPlayer);
                    System.out.println("Monster Hp: " + displayGrid.getObjectGrid()[x + 1][y].peek().getHp());

                    if (displayGrid.getObjectGrid()[x + 1][y].peek().getHp() <= 0)
                        displayGrid.removeObjectFromDisplay(x + 1, y);
                    else {
                        playerHp -= randomMonster;

                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);

                        displayGrid.addStringToDisplay("Player Took", 31, HEIGHT - 1);

                        if(getDigits(randomMonster) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(randomMonster),43,HEIGHT-1);
                        else if(getDigits((randomMonster)) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(randomMonster),43,HEIGHT-1);

                        displayGrid.addStringToDisplay("Damage!", 47, HEIGHT - 1);
                        System.out.println("\n");
                        System.out.println("Player Took Damage: " + randomMonster);
                        System.out.println("Player Hp: " + playerHp);
                        if (playerHp <= 0) {
                            System.out.println("PLZ End the Game!");
                            displayGrid.addStringToDisplay("HP:000 Score:0",0,0);
                            displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT - 1);
                        }
                    }
                } else {
                    displayGrid.removeObjectFromDisplay(x, y);
                    displayGrid.addObjectToDisplay(player, x + 1, y);
                    x += 1;
                }
            }
            else if (ch == 'j' || ch == 's') {
                if (displayGrid.getObjectGrid()[x][y + 1].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x][y + 1].peek().getChar().getChar() == ' ')
                    return false;
                else if (displayGrid.getObjectGrid()[x][y + 1].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x][y + 1].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x][y + 1].peek().getChar().getChar() == 'H') {
                    monsterMaxHit = displayGrid.getObjectGrid()[x][y + 1].peek().getMaxHit();
                    Random random = new Random();
                    int monsterHp = displayGrid.getObjectGrid()[x][y + 1].peek().getHp();
                    int randomPlayer = random.nextInt(playerMaxHit + 1);
                    int randomMonster = random.nextInt(monsterMaxHit + 1);
                    displayGrid.getObjectGrid()[x][y + 1].peek().setHp(monsterHp - randomPlayer);
                    displayGrid.addStringToDisplay("Monster Took", 6, HEIGHT - 1);

                    if(getDigits(randomPlayer) == 2)
                        displayGrid.addStringToDisplay("0"+Integer.toString(randomPlayer),19,HEIGHT-1);
                    else if(getDigits(randomPlayer) == 1)
                        displayGrid.addStringToDisplay("00"+Integer.toString(randomPlayer),19,HEIGHT-1);

                    displayGrid.addStringToDisplay("Damage!", 23, HEIGHT - 1);


                    System.out.println("Monster Took Damage: " + randomPlayer);
                    System.out.println("Monster Hp: " + displayGrid.getObjectGrid()[x][y + 1].peek().getHp());
                    if (displayGrid.getObjectGrid()[x][y + 1].peek().getHp() <= 0)
                        displayGrid.removeObjectFromDisplay(x, y + 1);
                    else {
                        playerHp -= randomMonster;
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);

                        displayGrid.addStringToDisplay("Player Took", 31, HEIGHT - 1);

                        if(getDigits(randomMonster) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(randomMonster),43,HEIGHT-1);
                        else if(getDigits((randomMonster)) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(randomMonster),43,HEIGHT-1);

                        displayGrid.addStringToDisplay("Damage!", 47, HEIGHT - 1);
                        System.out.println("\n");
                        System.out.println("Player Took Damage: " + randomMonster);
                        System.out.println("Player Hp: " + playerHp);
                        if (playerHp <= 0) {
                            System.out.println("PLZ End the Game!");
                            displayGrid.addStringToDisplay("HP:000 Score:0",0,0);
                            displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT - 1);
                        }
                    }
                } else {
                    displayGrid.removeObjectFromDisplay(x, y);
                    displayGrid.addObjectToDisplay(player, x, y + 1);
                    y += 1;
                }
            }
            else if (ch == 'k' || ch == 'w') {
                if (displayGrid.getObjectGrid()[x][y - 1].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x][y - 1].peek().getChar().getChar() == ' ')
                    return false;
                else if (displayGrid.getObjectGrid()[x][y - 1].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x][y - 1].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x][y - 1].peek().getChar().getChar() == 'H') {
                    monsterMaxHit = displayGrid.getObjectGrid()[x][y - 1].peek().getMaxHit();
                    Random random = new Random();
                    int monsterHp = displayGrid.getObjectGrid()[x][y - 1].peek().getHp();
                    int randomPlayer = random.nextInt(playerMaxHit + 1);
                    int randomMonster = random.nextInt(monsterMaxHit + 1);
                    displayGrid.getObjectGrid()[x][y - 1].peek().setHp(monsterHp - randomPlayer);
                    displayGrid.addStringToDisplay("Monster Took", 6, HEIGHT - 1);

                    if(getDigits(randomPlayer) == 2)
                        displayGrid.addStringToDisplay("0"+Integer.toString(randomPlayer),19,HEIGHT-1);
                    else if(getDigits(randomPlayer) == 1)
                        displayGrid.addStringToDisplay("00"+Integer.toString(randomPlayer),19,HEIGHT-1);

                    displayGrid.addStringToDisplay("Damage!", 23, HEIGHT - 1);

                    System.out.println("Monster Took Damage: " + randomPlayer);
                    System.out.println("Monster Hp: " + displayGrid.getObjectGrid()[x][y - 1].peek().getHp());
                    if (displayGrid.getObjectGrid()[x][y - 1].peek().getHp() <= 0)
                        displayGrid.removeObjectFromDisplay(x, y - 1);
                    else {
                        playerHp -= randomMonster;

                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);

                        displayGrid.addStringToDisplay("Player Took", 31, HEIGHT - 1);

                        if(getDigits(randomMonster) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(randomMonster),43,HEIGHT-1);
                        else if(getDigits((randomMonster)) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(randomMonster),43,HEIGHT-1);

                        displayGrid.addStringToDisplay("Damage!", 47, HEIGHT - 1);
                        System.out.println("\n");
                        System.out.println("Player Took Damage: " + randomMonster);
                        System.out.println("Player Hp: " + playerHp);
                        if (playerHp <= 0) {
                            System.out.println("PLZ End the Game!");
                            displayGrid.addStringToDisplay("HP:000 Score:0",0,0);
                            displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT - 1);
                        }
                    }
                } else {
                    displayGrid.removeObjectFromDisplay(x, y);
                    displayGrid.addObjectToDisplay(player, x, y - 1);
                    y -= 1;
                }
            }
            else if (ch == 'p') {
                System.out.println(displayGrid.removeObjectFromDisplay(x, y));
                if (displayGrid.getObjectGrid()[x][y].peek().getChar().getChar() == '.') {
                    displayGrid.addObjectToDisplay(player, x, y);
                    displayGrid.addStringToDisplay("There is nothing to pick up here                                      ", 6, HEIGHT - 1);
                } else {
                    player.setInventory((Item) displayGrid.removeObjectFromDisplay(x, y));
                    displayGrid.addObjectToDisplay(player, x, y);
                }

            }
            else if (ch == 'q')
                dropFlag = true;
            else if(dropFlag){
                int index = Character.getNumericValue(ch);
                if(index-1 >= 0 && index-1 < player.getInventory().size()){
                    displayGrid.removeObjectFromDisplay(x,y);
                    displayGrid.addObjectToDisplay(player.getInventory().get(index-1),x,y);
                    displayGrid.addObjectToDisplay(player,x,y);
                    player.dropItem(index);

                }
                else
                    displayGrid.addStringToDisplay("Invalid Index To Drop!",6,HEIGHT-1);
                dropFlag = false;
            }
            else if (ch == 'i') {
                if (player.getInventory().isEmpty())
                    displayGrid.addStringToDisplay("Player Inventory is Empty!                        ", 6, HEIGHT - 3);
                else {
                    String str = "";
                    for (int i = 0; i < player.getInventory().size(); i++) {
                        if (player.getInventory().get(i) instanceof Scroll)
                            str += (i+1) + "." + " Scroll: " + ((Scroll) player.getInventory().get(i)).getName() + ", ";
                        else if (player.getInventory().get(i) instanceof Armor) {
                            if(player.getInventory().get(i) == armor)
                                str += (i+1) + "." + " Armor +" + Integer.toString(player.getInventory().get(i).getIntValue()) + " (a), ";
                            else
                                str += (i+1) + "." + " Armor +" + Integer.toString(player.getInventory().get(i).getIntValue()) +", ";
                        }
                        else if (player.getInventory().get(i) instanceof Sword){
                            if(player.getInventory().get(i) == sword)
                                str += (i+1) + "." + " Sword +" + Integer.toString(player.getInventory().get(i).getIntValue()) + " (w), ";
                            else
                                str += (i+1)+"."+" Sword +" + Integer.toString(player.getInventory().get(i).getIntValue()) + ", ";
                        }

                    }
                    str = str.replaceAll(", $", "");
                    displayGrid.addStringToDisplay(str + "                       ", 6, HEIGHT - 3);
                }
            }
            else if (ch == 'c'){
                if(armor == null)
                    displayGrid.addStringToDisplay("Player does not have any armor that can be taken off!",6,HEIGHT-1);
                else{
                    player.setInventory(armor);
                    armor = null;
                    displayGrid.addStringToDisplay("Removed armor and placed it in player inventory",6, HEIGHT-1);
                }
            }
            else {
                System.out.println("character " + ch + " entered on the keyboard");
            }
            return true;
        }
        return false;
    }
}
