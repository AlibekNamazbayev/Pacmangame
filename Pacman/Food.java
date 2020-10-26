import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.scene.Node;
public class Food {
    private Map map;
    private Player player;
    private Circle circle;
    private Pane foodPane;    
    private Position foodPosition;
    private Label second;
    private final int timerTime = 5;
    private int numCircle = 10;
    private int time;
    private int points;
    private int[][] cell;
    private int size;    
    public Food(Map map1, Player player1) {
        this.map = map1;
        this.foodPane = new Pane();
        this.map.getChildren().add(this.foodPane);
        this.player = player1;
        this.size = this.map.getSize();
        this.cell = this.map.getMap();
        Thread var3 = new Thread(() -> {
            while(this.numCircle > 0) {
                this.createFood();
                Platform.runLater(() -> {
                    this.foodPane.getChildren().addAll(new Node[]{this.circle, this.second});
                });

                for(this.time = 5; this.time > 0; --this.time) {
                    Platform.runLater(() -> {
                        this.second.setText("" + this.time);
                    });
                    if(this.player.getPosition().equals(this.foodPosition)) {
                        this.points += this.time;
                        break;
                    }

                    try {
                        Thread.sleep(1000L);
                    } catch (Exception v) {
                        ;
                    }
                }

                try {
                    Thread.sleep(10L);
                } catch (Exception v) {
                    ;
                }

                Platform.runLater(() -> {
                    this.foodPane.getChildren().clear();
                });
                --this.numCircle;
            }

            System.out.println(this.getPoints());
        });
        var3.start();
    }
    public int getPoints() {
        return this.points;
    }
    public Position getPosition(){
        return foodPosition;
    }
    private void createFood() {
        Random rand = new Random();        
        double unit1 = (double)this.map.getUnit();        
        int size1;
        int size2;
        do {
            do {
                size1 = rand.nextInt(this.size);
                size2 = rand.nextInt(this.size);
            } while(this.player.getPosition().equals(new Position(size1, size2)));
        } while(this.map.getMap()[size1][size2] == 1);
        this.circle = new Circle((double)size * unit1 + unit1 / 2.0D, (double)size2 * unit1 + unit1 / 2.0D, unit1 / 4.0D);
        this.circle.setFill(Color.GREEN);
        this.foodPosition = new Position(size1, size2);
        this.second = new Label("5");
        this.second.setTranslateX((double)size1 * unit1);
        this.second.setTranslateY((double)size2 * unit1);
    }    
}