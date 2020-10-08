public class ObjectDisplayGrid {
    
    private int gameHeight;
    private int width;
    private int topHeight;

    public ObjectDisplayGrid(int _gameHeight, int _width, int _topHeight){
        gameHeight = _gameHeight;
        width = _width;
        topHeight = _topHeight;
    }

    public ObjectDisplayGrid getObjectDisplayGrid(int _gameHeight, int _width, int _topHeight){
        if(gameHeight == _gameHeight && width == _width && topHeight == _topHeight){
            return this;
        }
        else{
            return new ObjectDisplayGrid(_gameHeight, _width, _topHeight);
        }
    }

    public void setTopMessageHeight(int _topHeight) {topHeight = _topHeight;}

}
