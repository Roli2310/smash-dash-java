package smashdash;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class SmashDashGameManager {

  private static final int BRICKS_ROWS = 5;
  private static final int BRICKS_COLUMNS = 8;

  private SmashDashGUI smashDashGUI;      //Referenz

  private Player player;
  private Ball ball;

  public SmashDashGameManager() {
    // DON'T CHANGE
    this.smashDashGUI = new SmashDashGUI(this);
    // /DON'T CHANGE
    player = new Player(512,0);
    addActor(player);
    ball = new Ball();
    addActor(ball);
    fillWithBricks();

  }
  
  //Aktualisierung des Spiels
  public void update(double deltaTime) {
    for (Actor actor: actors) {
      if (actor != null) {
        actor.update(deltaTime);          
        

        smashDashGUI.draw(actor.getImage(),actor.getX(),actor.getY());
        
      }
    }
    checkCollissions();

    //Spielende
    if(ball.getY() > 768) {
      smashDashGUI.gameOver();
    }

    boolean hasBrick = false; 

    for(Actor actor :actors) {
      if (actor instanceof Brick) {
        hasBrick = true;
        break;
      }
    }
    if (!hasBrick) {
      smashDashGUI.win();
    }

  }

  
  private Actor[] actors = new Actor[1024];

  private void addActor(Actor actor) {
    if (actor == null)
      throw new IllegalArgumentException("actor must not be null");

    for (int i = 0; i < actors.length; i++) {
      if (actors[i] == null) {
        actors[i] = actor;
        break;
      }
    }
  }

  
  private void fillWithBricks() {
    Random random = new Random();
    for (int row = 0; row < BRICKS_ROWS; row++) {
      for (int column = 0; column < BRICKS_COLUMNS; column++) {
    
        final int x = 512 + (column - BRICKS_COLUMNS / 2) * 64;
        final int y = 128 + row * 32;
        
        List<Supplier<Brick>> brickSuppliers = List.of(
            
        () -> new SimpleBrick(x, y)
          
          
        , () -> new HardBrick(x, y)
          
          
        , () -> new SpeedChangerBrick(x, y)


        //, () -> new PlayerChangerBrick(x, y)//
          
        );

        if (!brickSuppliers.isEmpty()) {
          this.addActor(brickSuppliers.get(random.nextInt(brickSuppliers.size())).get());
        }
      }
    }
  }
  
  //prüft nach Kollision mit bestimmten Sachen
  private void checkCollissions() {
    for (int index = 0; index < actors.length; index++) {
      Actor actor = actors[index];
      if (actor == null) {
        continue;
      }
      if (actor == ball) {
          continue;
        }
        
          
      boolean collision = ball.checkCollision(actor);

      if(collision == true && actor == player) {
        ball.applyPlayerFriction(player);
      }

      if(collision == true && actor instanceof Brick) {
        Brick brick = (Brick) actor;      
        if(!brick.onBallCollision() ) {   
          actors[index] = null;           
        }
      }

      if(collision == true && actor instanceof GameChanger) {
        GameChanger gameChanger = (GameChanger) actor;
        gameChanger.changeGame(player, ball);
      }


    }
  }

  

}
