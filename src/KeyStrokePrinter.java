
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
    private int hpm;
    private int moveCounter = 0;
    private boolean dropFlag = false;
    private boolean wear = false;
    private boolean wield = false;
    private boolean read = false;
    private boolean help = false;
    private boolean endGame = false;
    private boolean actualEndGame = false;
    private int hallucinateCount = 0;
    private String[] randomChar = {".", "x", "#", "+", "?", ")", "]", "@", "H", "T", "S"};


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
        hpm= _player.getHpm();
    }

    public int getDigits(int x){
        int count = 0;
        if(x == 0)
            return 1;
        else
            for(; x!= 0; x /= 10, ++ count){}

        return count;
    }

    public void teleport(int i, int j,Displayable monster){
        ArrayList<CreatureAction> creatureActions = monster.getCreatureActions();
        Player tempPlayer = (Player) player;
        ArrayList<CreatureAction> playerCreatureActions = tempPlayer.getCreatureActions();
        System.out.println(playerCreatureActions);
        int old_i = i;
        int old_j = j;
        for(CreatureAction c : creatureActions){
            if(c.getName().equalsIgnoreCase("Teleport")){
                System.out.println("I will teleport");
                Displayable temp = displayGrid.removeObjectFromDisplay(old_i,old_j);
                Random random = new Random();
                i = random.nextInt((20-4)+1) + 4;
                j = random.nextInt((20-4)+1) + 4;
                while(displayGrid.getObjectGrid()[i][j].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[i][j].peek().getChar().getChar() == ' ') {
                    i = random.nextInt((20-4)+1) + 4;
                    j = random.nextInt((20-4)+1) + 4;
                }
                displayGrid.addObjectToDisplay(temp,i,j);
                displayGrid.addStringToDisplay("A creature has teleported somewhere else in the dungeon", 6, HEIGHT-1);
            }
        }
        for(CreatureAction ca: playerCreatureActions){
            if(ca.getName().equalsIgnoreCase("DropPack")){
                ArrayList<Item> inventory = player.getInventory();
                if(inventory.size() == 0){
                    System.out.println("fuck u");
                }
                else{
                    Displayable dropItem = inventory.get(0);
                    inventory.remove(0);
                    player.setInventory(inventory);
                    displayGrid.removeObjectFromDisplay(x,y);
                    displayGrid.addObjectToDisplay(dropItem,x,y);
                    displayGrid.addObjectToDisplay(player,x,y);
                    displayGrid.addStringToDisplay("You have dropped an item from your pack!            ",6,HEIGHT-1);
                }
            }
        }

    }

    public void hallucinate(){
        Random random = new Random();
        for(int x = 0; x<80; x++){
            for(int y = 2; y <HEIGHT-4;y++){
                if(displayGrid.getObjectGrid()[x][y].peek().getChar().getChar() == ' '){
                    System.out.println("hi");
                }
                else {
                    displayGrid.addStringToDisplay(randomChar[random.nextInt(11)], x, y);
                }
            }
        }
        hallucinateCount--;
    }

    public void doneHallucinate(){
        displayGrid.repaintStack();
    }
    @Override
    public boolean observerUpdate(char ch) {
        while(playerHp >0 && actualEndGame == false) {
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
            }
            if (ch == 'X') {
                System.out.println("got an X, ending input checking");
                return false;
            }
            else if(ch == 'H'){
                help = true;
            }
            else if(help){
                help = false;
                char command = ch;
                if(command == 'w' || command =='a' || command =='s' || command =='d'){
                    displayGrid.addStringToDisplay("This command moves the player. w:up  a:left  s:down  d:right",6,HEIGHT-1);
                }
                else if(command == 'p'){
                    displayGrid.addStringToDisplay("\"p\": This command picks up an item if the player is directly on top of it.",6,HEIGHT-1);
                }
                else if (command == 'q'){
                    displayGrid.addStringToDisplay("\"q\": This command drops an item where the player is currently standing.   ",6,HEIGHT-1);
                }
                else if(command == 'i'){
                    displayGrid.addStringToDisplay("\"i\": This command displays current inventory. Must press again to update. ",6,HEIGHT-1);
                }
                else if(command == 'c'){
                    displayGrid.addStringToDisplay("\"c\": Takes off armor that is being worn. Puts back into inventory.        ",6,HEIGHT-1);
                }
                else if(command == 'z'){
                    displayGrid.addStringToDisplay("\"z\" followed by a number puts on armor from that inventory spot number.   ",6,HEIGHT-1);
                }
                else if(command == 't'){
                    displayGrid.addStringToDisplay("\"t\" followed by a number weilds a sword from that inventory spot number.  ",6,HEIGHT-1);
                }
                else if(command == 'r'){
                    displayGrid.addStringToDisplay("\"r\" followed by a number reads a scroll from that inventory spot number.  ",6,HEIGHT-1);
                }
                else if(command == '?'){
                    displayGrid.addStringToDisplay("\"?\": This command displays all commands and their key bindings.           ",6,HEIGHT-1);
                }
            }
            //move left
            else if (ch == 'h' || ch == 'a') {
                if(hallucinateCount!= 0)
                    hallucinate();
                else
                    doneHallucinate();
                int playerArmor;
                int playerSword;
                if(armor == null)
                    playerArmor = 0;
                else
                    playerArmor = armor.getIntValue();
                if(sword == null)
                    playerSword = 0;
                else
                    playerSword = sword.getIntValue();

                if (displayGrid.getObjectGrid()[x - 1][y].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x - 1][y].peek().getChar().getChar() == ' ')
                    return false;
                else if (displayGrid.getObjectGrid()[x - 1][y].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x - 1][y].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x - 1][y].peek().getChar().getChar() == 'H') {
                    monsterMaxHit = displayGrid.getObjectGrid()[x - 1][y].peek().getMaxHit();
                    Random random = new Random();
                    int monsterHp = displayGrid.getObjectGrid()[x - 1][y].peek().getHp();
                    int randomPlayer = random.nextInt(playerMaxHit + 1);
                    int randomMonster = random.nextInt(monsterMaxHit + 1);
                    displayGrid.addStringToDisplay("                                                       ",6, HEIGHT-1);
                    displayGrid.getObjectGrid()[x - 1][y].peek().setHp(monsterHp - randomPlayer - playerSword);
                    displayGrid.addStringToDisplay("Monster Took", 6, HEIGHT - 1);

                    if(getDigits(randomPlayer) == 2)
                        displayGrid.addStringToDisplay("0"+Integer.toString(randomPlayer + playerSword),19,HEIGHT-1);
                    else if(getDigits(randomPlayer) == 1)
                        displayGrid.addStringToDisplay("00"+Integer.toString(randomPlayer + playerSword),19,HEIGHT-1);

                    displayGrid.addStringToDisplay("Damage!", 23, HEIGHT - 1);

                    if (displayGrid.getObjectGrid()[x - 1][y].peek().getHp() <= 0) {
                        Displayable monster = displayGrid.removeObjectFromDisplay(x - 1, y);
                        if(monster.getChar().getChar() == 'T')
                            displayGrid.addStringToDisplay("You have defeated the Troll!            ", 6,HEIGHT-1);
                        else if(monster.getChar().getChar() == 'S')
                            displayGrid.addStringToDisplay("You have defeated the Snake!            ", 6,HEIGHT-1);
                        else if(monster.getChar().getChar() == 'H')
                            displayGrid.addStringToDisplay("You have defeated the Hobgoblin!            ", 6,HEIGHT-1);
                    }
                    else {
                        if(playerArmor > randomMonster) {
                            playerHp += 0;
                            randomMonster = 0;
                        }
                        else{
                            playerHp = playerHp - randomMonster + playerArmor;
                            randomMonster -= playerArmor;
                        }
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);

                        displayGrid.addStringToDisplay("Player Took", 31, HEIGHT - 1);

                        if(getDigits(randomMonster) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(randomMonster),43,HEIGHT-1);
                        else if(getDigits(randomMonster) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(randomMonster),43,HEIGHT-1);

                        displayGrid.addStringToDisplay("Damage!", 47, HEIGHT - 1);


                        teleport(x-1, y,displayGrid.getObjectGrid()[x-1][y].peek());

                        if (playerHp <= 0) {
                            System.out.println("PLZ End the Game!");
                            displayGrid.addStringToDisplay("HP:000 Score:0",0,0);
                            displayGrid.addStringToDisplay("Game Over! Player has died.                                           ", 6, HEIGHT - 1);
                            displayGrid.addObjectToDisplay(new Wall(new Char('+')),x,y);
                        }
                    }
                    moveCounter++;
                    if(moveCounter == hpm){
                        playerHp++;
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);
                        moveCounter = 0;
                    }
                }
                else {
                    player = displayGrid.removeObjectFromDisplay(x, y);
                    displayGrid.addObjectToDisplay(player, x - 1, y);
                    x -= 1;
                    moveCounter++;
                    if(moveCounter == hpm){
                        playerHp++;
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);
                        moveCounter = 0;
                    }
                }
            }
            //move right
            else if (ch == 'l' || ch == 'd') {
                if(hallucinateCount!= 0)
                    hallucinate();
                else
                    doneHallucinate();
                int playerArmor;
                int playerSword;
                if(armor == null)
                    playerArmor = 0;
                else
                    playerArmor = armor.getIntValue();
                if(sword == null)
                    playerSword = 0;
                else
                    playerSword = sword.getIntValue();

                if (displayGrid.getObjectGrid()[x + 1][y].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x + 1][y].peek().getChar().getChar() == ' ')
                    return false;
                else if (displayGrid.getObjectGrid()[x + 1][y].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x + 1][y].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x + 1][y].peek().getChar().getChar() == 'H') {
                    monsterMaxHit = displayGrid.getObjectGrid()[x + 1][y].peek().getMaxHit();
                    Random random = new Random();
                    int monsterHp = displayGrid.getObjectGrid()[x + 1][y].peek().getHp();
                    int randomPlayer = random.nextInt(playerMaxHit + 1);
                    int randomMonster = random.nextInt(monsterMaxHit + 1);

                    displayGrid.addStringToDisplay("                                                       ",6, HEIGHT-1);
                    displayGrid.getObjectGrid()[x + 1][y].peek().setHp(monsterHp - randomPlayer - playerSword);
                    displayGrid.addStringToDisplay("Monster Took", 6, HEIGHT - 1);

                    if(getDigits(randomPlayer) == 2)
                        displayGrid.addStringToDisplay("0"+Integer.toString(randomPlayer + playerSword),19,HEIGHT-1);
                    else if(getDigits(randomPlayer) == 1)
                        displayGrid.addStringToDisplay("00"+Integer.toString(randomPlayer + playerSword),19,HEIGHT-1);

                    displayGrid.addStringToDisplay("Damage!", 23, HEIGHT - 1);

                    if (displayGrid.getObjectGrid()[x + 1][y].peek().getHp() <= 0) {
                        Displayable monster = displayGrid.removeObjectFromDisplay(x + 1, y);
                        if(monster.getChar().getChar() == 'T')
                            displayGrid.addStringToDisplay("You have defeated the Troll!            ", 6,HEIGHT-1);
                        else if(monster.getChar().getChar() == 'S')
                            displayGrid.addStringToDisplay("You have defeated the Snake!            ", 6,HEIGHT-1);
                        else if(monster.getChar().getChar() == 'H')
                            displayGrid.addStringToDisplay("You have defeated the Hobgoblin!            ", 6,HEIGHT-1);
                    }

                    else {
                        if(playerArmor > randomMonster) {
                            playerHp += 0;
                            randomMonster = 0;
                        }
                        else{
                            playerHp = playerHp - randomMonster + playerArmor;
                            randomMonster -= playerArmor;
                        }
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);

                        displayGrid.addStringToDisplay("Player Took", 31, HEIGHT - 1);

                        if(getDigits(randomMonster) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(randomMonster),43,HEIGHT-1);
                        else if(getDigits(randomMonster) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(randomMonster),43,HEIGHT-1);

                        displayGrid.addStringToDisplay("Damage!", 47, HEIGHT - 1);

                        teleport(x+1, y,displayGrid.getObjectGrid()[x+1][y].peek());

                        if (playerHp <= 0) {
                            System.out.println("PLZ End the Game!");
                            displayGrid.addStringToDisplay("HP:000 Score:0",0,0);
                            displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT - 1);
                            displayGrid.addObjectToDisplay(new Wall(new Char('+')),x,y);
                        }
                    }
                    moveCounter++;
                    if(moveCounter == hpm){
                        playerHp++;
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);
                        moveCounter = 0;
                    }
                }
                else {
                    player = displayGrid.removeObjectFromDisplay(x, y);
                    displayGrid.addObjectToDisplay(player, x + 1, y);
                    x += 1;
                    moveCounter++;
                    if(moveCounter == hpm){
                        playerHp++;
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);
                        moveCounter = 0;
                    }
                }
            }
            //move down
            else if (ch == 'j' || ch == 's') {
                if(hallucinateCount!= 0)
                    hallucinate();
                else
                    doneHallucinate();
                int playerArmor;
                int playerSword;
                if(armor == null)
                    playerArmor = 0;
                else
                    playerArmor = armor.getIntValue();
                if(sword == null)
                    playerSword = 0;
                else
                    playerSword = sword.getIntValue();

                if (displayGrid.getObjectGrid()[x][y + 1].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x][y + 1].peek().getChar().getChar() == ' ')
                    return false;
                else if (displayGrid.getObjectGrid()[x][y + 1].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x][y + 1].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x][y + 1].peek().getChar().getChar() == 'H') {
                    monsterMaxHit = displayGrid.getObjectGrid()[x][y + 1].peek().getMaxHit();
                    Random random = new Random();
                    int monsterHp = displayGrid.getObjectGrid()[x][y + 1].peek().getHp();
                    int randomPlayer = random.nextInt(playerMaxHit + 1);
                    int randomMonster = random.nextInt(monsterMaxHit + 1);

                    displayGrid.addStringToDisplay("                                                       ",6, HEIGHT-1);
                    displayGrid.getObjectGrid()[x][y + 1].peek().setHp(monsterHp - randomPlayer - playerSword);
                    displayGrid.addStringToDisplay("Monster Took", 6, HEIGHT - 1);

                    if(getDigits(randomPlayer) == 2)
                        displayGrid.addStringToDisplay("0"+Integer.toString(randomPlayer + playerSword),19,HEIGHT-1);
                    else if(getDigits(randomPlayer) == 1)
                        displayGrid.addStringToDisplay("00"+Integer.toString(randomPlayer + playerSword),19,HEIGHT-1);

                    displayGrid.addStringToDisplay("Damage!", 23, HEIGHT - 1);

                    if (displayGrid.getObjectGrid()[x][y + 1].peek().getHp() <= 0) {
                        Displayable monster = displayGrid.removeObjectFromDisplay(x, y+1);
                        if(monster.getChar().getChar() == 'T')
                            displayGrid.addStringToDisplay("You have defeated the Troll!            ", 6,HEIGHT-1);
                        else if(monster.getChar().getChar() == 'S')
                            displayGrid.addStringToDisplay("You have defeated the Snake!            ", 6,HEIGHT-1);
                        else if(monster.getChar().getChar() == 'H')
                            displayGrid.addStringToDisplay("You have defeated the Hobgoblin!            ", 6,HEIGHT-1);
                    }
                    else {
                        if(playerArmor > randomMonster) {
                            playerHp += 0;
                            randomMonster = 0;
                        }
                        else{
                            playerHp = playerHp - randomMonster + playerArmor;
                            randomMonster -= playerArmor;
                        }
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);

                        displayGrid.addStringToDisplay("Player Took", 31, HEIGHT - 1);

                        if(getDigits(randomMonster) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(randomMonster),43,HEIGHT-1);
                        else if(getDigits(randomMonster) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(randomMonster),43,HEIGHT-1);

                        displayGrid.addStringToDisplay("Damage!", 47, HEIGHT - 1);

                        teleport(x, y + 1,displayGrid.getObjectGrid()[x][y+1].peek());

                        if (playerHp <= 0) {
                            System.out.println("PLZ End the Game!");
                            displayGrid.addStringToDisplay("HP:000 Score:0",0,0);
                            displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT - 1);
                            displayGrid.addObjectToDisplay(new Wall(new Char('+')),x,y);
                        }
                    }
                    moveCounter++;
                    if(moveCounter == hpm){
                        playerHp++;
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);
                        moveCounter = 0;
                    }
                }
                else {
                    player = displayGrid.removeObjectFromDisplay(x, y);
                    displayGrid.addObjectToDisplay(player, x, y+1);
                    y += 1;
                    moveCounter++;
                    if(moveCounter == hpm){
                        playerHp++;
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);
                        moveCounter = 0;
                    }
                }
            }
            //move up
            else if (ch == 'k' || ch == 'w') {
                if(hallucinateCount!= 0)
                    hallucinate();
                else
                    doneHallucinate();
                int playerArmor;
                int playerSword;
                if(armor == null)
                    playerArmor = 0;
                else
                    playerArmor = armor.getIntValue();
                if(sword == null)
                    playerSword = 0;
                else
                    playerSword = sword.getIntValue();

                if (displayGrid.getObjectGrid()[x][y - 1].peek().getChar().getChar() == 'x' || displayGrid.getObjectGrid()[x][y - 1].peek().getChar().getChar() == ' ')
                    return false;
                else if (displayGrid.getObjectGrid()[x][y - 1].peek().getChar().getChar() == 'T' || displayGrid.getObjectGrid()[x][y - 1].peek().getChar().getChar() == 'S' || displayGrid.getObjectGrid()[x][y - 1].peek().getChar().getChar() == 'H') {
                    monsterMaxHit = displayGrid.getObjectGrid()[x][y -1].peek().getMaxHit();
                    Random random = new Random();
                    int monsterHp = displayGrid.getObjectGrid()[x][y - 1].peek().getHp();
                    int randomPlayer = random.nextInt(playerMaxHit + 1);
                    int randomMonster = random.nextInt(monsterMaxHit + 1);

                    displayGrid.addStringToDisplay("                                                       ",6, HEIGHT-1);
                    displayGrid.getObjectGrid()[x][y - 1].peek().setHp(monsterHp - randomPlayer - playerSword);
                    displayGrid.addStringToDisplay("Monster Took", 6, HEIGHT - 1);

                    if(getDigits(randomPlayer) == 2)
                        displayGrid.addStringToDisplay("0"+Integer.toString(randomPlayer + playerSword),19,HEIGHT-1);
                    else if(getDigits(randomPlayer) == 1)
                        displayGrid.addStringToDisplay("00"+Integer.toString(randomPlayer + playerSword),19,HEIGHT-1);

                    displayGrid.addStringToDisplay("Damage!", 23, HEIGHT - 1);

                    if (displayGrid.getObjectGrid()[x][y - 1].peek().getHp() <= 0) {
                        Displayable monster = displayGrid.removeObjectFromDisplay(x, y-1);
                        if(monster.getChar().getChar() == 'T')
                            displayGrid.addStringToDisplay("You have defeated the Troll!            ", 6,HEIGHT-1);
                        else if(monster.getChar().getChar() == 'S')
                            displayGrid.addStringToDisplay("You have defeated the Snake!            ", 6,HEIGHT-1);
                        else if(monster.getChar().getChar() == 'H')
                            displayGrid.addStringToDisplay("You have defeated the Hobgoblin!            ", 6,HEIGHT-1);
                    }
                    else {
                        if(playerArmor > randomMonster) {
                            playerHp += 0;
                            randomMonster = 0;
                        }
                        else{
                            playerHp = playerHp - randomMonster + playerArmor;
                            randomMonster -= playerArmor;
                        }
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);

                        displayGrid.addStringToDisplay("Player Took", 31, HEIGHT - 1);

                        if(getDigits(randomMonster) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(randomMonster),43,HEIGHT-1);
                        else if(getDigits(randomMonster) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(randomMonster),43,HEIGHT-1);

                        displayGrid.addStringToDisplay("Damage!", 47, HEIGHT - 1);

                        teleport(x, y-1,displayGrid.getObjectGrid()[x][y-1].peek());

                        if (playerHp <= 0) {
                            System.out.println("PLZ End the Game!");
                            displayGrid.addStringToDisplay("HP:000 Score:0",0,0);
                            displayGrid.addStringToDisplay("Game Over! Player has died.                                         ", 6, HEIGHT - 1);
                            displayGrid.addObjectToDisplay(new Wall(new Char('+')),x,y);
                        }
                    }
                    moveCounter++;
                    if(moveCounter == hpm){
                        playerHp++;
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);
                        moveCounter = 0;
                    }
                }
                else {
                    player = displayGrid.removeObjectFromDisplay(x, y);
                    displayGrid.addObjectToDisplay(player, x, y - 1);
                    y -= 1;
                    moveCounter++;
                    if(moveCounter == hpm){
                        playerHp++;
                        if(getDigits(playerHp) == 3)
                            displayGrid.addStringToDisplay(Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 2)
                            displayGrid.addStringToDisplay("0"+Integer.toString(playerHp),3,0);
                        else if(getDigits(playerHp) == 1)
                            displayGrid.addStringToDisplay("00"+Integer.toString(playerHp),3,0);
                        moveCounter = 0;
                    }
                }
            }
            //pick up item
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
            //drop item
            else if (ch == 'q')
                dropFlag = true;
            else if(dropFlag){
                int index = Character.getNumericValue(ch);
                if(index-1 >= 0 && index-1 < player.getInventory().size()){
                    displayGrid.removeObjectFromDisplay(x,y);
                    displayGrid.addObjectToDisplay(player.getInventory().get(index-1),x,y);
                    displayGrid.addObjectToDisplay(player,x,y);

                    if(player.getInventory().get(index-1) == armor)
                        armor = null;
                    else if(player.getInventory().get(index-1) == sword)
                        sword = null;
                    player.dropItem(index);

                }
                else
                    displayGrid.addStringToDisplay("Invalid Index To Drop!",6,HEIGHT-1);
                dropFlag = false;
            }
            //display inventory
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
                    displayGrid.addStringToDisplay("                                           ",6,HEIGHT-3);
                    displayGrid.addStringToDisplay(str + "                       ", 6, HEIGHT - 3);
                }
            }
            //take off armor
            else if (ch == 'c'){
                if(armor == null)
                    displayGrid.addStringToDisplay("Player does not have any armor that can be taken off!",6,HEIGHT-1);
                else{
                    armor = null;
                    displayGrid.addStringToDisplay("Removed armor and placed it in player inventory",6, HEIGHT-1);
                }
            }
            //wear a piece of armor
            else if( ch == 'z')
                wear = true;
            else if(wear){
                int index = Character.getNumericValue(ch);
                if(index-1 >= 0 && index-1 < player.getInventory().size()){
                    armor = player.getInventory().get(index-1);
                }
                else
                    displayGrid.addStringToDisplay("Invalid Index To Wear Armor!",6,HEIGHT-1);
                wear = false;
            }
            //wield a sword
            else if(ch == 't')
                wield = true;
            else if(wield){
                int index = Character.getNumericValue(ch);
                if(index-1 >= 0 && index-1 < player.getInventory().size()){
                    sword = player.getInventory().get(index-1);
                }
                else
                    displayGrid.addStringToDisplay("Invalid Index To Wield Sword!",6,HEIGHT-1);
                wield = false;
            }
            else if(ch == 'r')
                read = true;
            else if(read){
                read = false;
                int index = Character.getNumericValue(ch);
                if(index-1 >= 0 && index-1 < player.getInventory().size()){
                    if(player.getInventory().get(index-1).getItemAction().getCharValue() == 'a'){
                        if(armor == null){
                            displayGrid.addStringToDisplay("Scroll did nothing because armor is not worn!", 6, HEIGHT-1);
                        }
                        else{
                            armor.setIntValue(armor.getIntValue()+player.getInventory().get(index-1).getItemAction().getIntValue());
                            System.out.println(armor.getIntValue());
                            displayGrid.addStringToDisplay("Armor was buffed by: " + player.getInventory().get(index-1).getItemAction().getIntValue() +"                    ",6,HEIGHT-1);
                            player.dropItem(index);
                        }
                    }
                    else if(player.getInventory().get(index-1).getItemAction().getName().equalsIgnoreCase("Hallucinate")) {
                        hallucinateCount = player.getInventory().get(index-1).getItemAction().getIntValue();
                        ArrayList<Item> inventory = player.getInventory();
                        inventory.remove(index-1);
                        player.setInventory(inventory);
                        hallucinate();



                    }
                }
            }
            else if(ch == '?'){
                displayGrid.addStringToDisplay("Move: wasd Pick/Drop: p/q Inventory:i Armor-on/off:z/c Sword/Scroll:t/r",6,HEIGHT-1);
            }
            else if(ch == 'E')
                endGame = true;
            else if(endGame){
                if(ch == 'y' || ch == 'Y') {
                    endGame = false;
                    actualEndGame = true;
                    displayGrid.addStringToDisplay("Player has wished to end the game!                         ", 6, HEIGHT-1);
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
