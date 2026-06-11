package smashdash;

public class Player extends Actor {
    int lastX;
    double speed;

    public Player(int lastX, double speed) {
        super(512, 680);
        this.lastX = lastX;
        this.speed = speed;
    }

    @Override
    public String getImage() {
        return "player-medium";
    }

    @Override
    public void update(double deltaTime) {
        lastX = getX();
        int newX = SmashDashGUI.getMouseX();

        if(newX <getWidth() /2) {
            newX = getWidth()/2; 
        }
        if (newX > 1024 - getWidth()/2){
            newX = 1024 - getWidth()/2;
        }

        setX(newX);
        
        speed =(getX() - lastX) / deltaTime;
    }
    @Override
    public int getWidth() {
        return 128;
    }
    @Override
    public int getHeight() {
        return 32;
    }
    
    public double getSpeed() {
        return speed;
    }
}
