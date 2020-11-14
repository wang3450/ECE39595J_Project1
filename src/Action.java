public class Action {
    private String message;
    private int intValue;
    private char charValue;

    public void setMessage(String _message) {
        message = _message;
    }

    public void setIntValue(int _intValue) {
        intValue = _intValue;
    }

    public void setCharValue(char _charValue) {
        charValue = _charValue;
    }
    public char getCharValue(){return charValue;}
    public String getMessage(){return message;}
}
