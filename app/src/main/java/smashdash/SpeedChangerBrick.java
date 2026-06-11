package smashdash;

public class SpeedChangerBrick extends Brick implements GameChanger {
    private boolean speedUp;
    public SpeedChangerBrick(int x, int y) {
        super(x,y);
        this.speedUp = Math.random() > 0.5;
    }

    @Override
    public String getImage() {
        if(speedUp)  {       //falls beschleinigung
            return "brick-speedup";
        }
        return "brick-slowdown";
    }


    @Override
    public boolean onBallCollision() {
        return false;               //Ball wird getroffen
    }

    @Override
    public void changeGame(Player player, Ball ball) {
        if (speedUp) {
            ball.changeSpeed(100);
        } else {
            ball.changeSpeed(-100);
        }
    }
}
