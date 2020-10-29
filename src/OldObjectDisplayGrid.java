public class OldObjectDisplayGrid {
    
    private int gameHeight;
    private int width;
    private int topHeight;

    public OldObjectDisplayGrid(int _gameHeight, int _width, int _topHeight){
        gameHeight = _gameHeight;
        width = _width;
        topHeight = _topHeight;
    }

    public OldObjectDisplayGrid getObjectDisplayGrid(int _gameHeight, int _width, int _topHeight){
        if(gameHeight == _gameHeight && width == _width && topHeight == _topHeight){
            return this;
        }
        else{
            return new OldObjectDisplayGrid(_gameHeight, _width, _topHeight);
        }
    }

    public void setTopMessageHeight(int _topHeight) {topHeight = _topHeight;}

}
