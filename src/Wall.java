public class Wall extends Displayable {
    private Char displayChar;

    public Wall (Char ch){
        displayChar = ch;
    }

    public Char getChar(){return displayChar;}
    public void setChar(Char ch){displayChar = ch;}
}
