package smashdash;

public class Ball extends Actor{
    private double speed;
    private BallMechanics ballMechanics;

    public Ball() {
        super(512,500);
        this.speed = 500.0;             
        this.ballMechanics = new BallMechanics(this);           

    }

    @Override
    public String getImage() {
        return "ball";
    }

    //delegiert an ballMechanics
    @Override 
    public void update(double deltaTime) {
        ballMechanics.update(deltaTime);
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 16;
    }

    public double getSpeed() {
        return this.speed;
    }

    //Veränderung der Geschwindigkeit des Balls
    public void changeSpeed(double deltaSpeed) {
        this.speed += deltaSpeed;
        if(this.speed <100) {
            this.speed = 100;
        }
    }

    //Bewegungsrichtung anhand von Geschwindigkeit --> Delegiert an ballMechanics.
    public void applyPlayerFriction(Player player) {
        ballMechanics.applyPlayerFriction(player);

    }

    public boolean checkCollision(Actor other) {
        return ballMechanics.checkCollision(other);
    }
}
