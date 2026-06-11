package smashdash;

public class HardBrick extends Brick{
    private int state;

    public HardBrick(int x, int y) {
        super(x,y);
        this.state = 0;
    }

    @Override
    public String getImage() {
        if (state == 0) {
            return "brick-red";
        } 
        if (state == 1) {
            return "brick-blue";
        }
        return "brick-green";
    
    }

    @Override
    public boolean onBallCollision() {
        this.state++;

        return this.state < 3;      
    }
}
