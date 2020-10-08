public class Displayable {
    private boolean visible;
    private int MaxHit;
    private int HpMove;
    private int Hp;
    private char Type;
    private int IntValue;
    private int posX;
    private int posY;
    private int width;
    private int height;


    public void setInvisible(){this.visible = false;}
    public void setVisible(){this.visible = true;}

    public void setMaxHit(int maxHit) {
        MaxHit = maxHit;
    }

    public void setHpMove(int hpMove) {
        HpMove = hpMove;
    }

    public void setHp(int hp) {
        Hp = hp;
    }

    public void setType(char type) {
        Type = type;
    }

    public void setIntValue(int intValue) {
        IntValue = intValue;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
