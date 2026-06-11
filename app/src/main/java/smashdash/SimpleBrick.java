package smashdash;

public class SimpleBrick extends Brick{

    public SimpleBrick(int x, int y) {
        super(x,y);
    }

    @Override
    public String getImage(){
        return "brick-simple";
    }

    @Override
    public boolean onBallCollision() {
        return false;
    }
    
}
