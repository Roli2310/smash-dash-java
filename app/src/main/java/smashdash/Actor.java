package smashdash;

public abstract class Actor {
    private int x;
    private int y;

    public Actor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract String getImage();

    public abstract void update(double deltatime);

    public abstract int getWidth();

    public abstract int getHeight();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addX(int delta) {
        x +=delta;
    }

    public void addY(int delta) {
        y +=delta;
    }
    

    


}
