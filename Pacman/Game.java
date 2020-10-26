import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.io.*;
public class Game extends Application {
	public Map map;
	BotPlayer player;
	private Food food;
	public void start(Stage stage)throws FileNotFoundException{
		Pane pane = new Pane();
		map = new Map("map0.txt");
		player = new BotPlayer(map);
		food = new Food(map,player);
		pane.getChildren().addAll(map);		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		scene.setOnKeyPressed(event ->{
			if(event.getCode().equals(KeyCode.RIGHT)){
				player.moveRight();
			}
			else if(event.getCode().equals(KeyCode.LEFT)){
				player.moveLeft();
			}			
			else if(event.getCode().equals(KeyCode.UP)){
				player.moveUp();
			}
			else if(event.getCode().equals(KeyCode.DOWN)){
				player.moveDown();
			}
			else if(event.getCode().equals(KeyCode.W)){
				player.feed(food);
			}			
		});
		stage.setTitle("Packman Game");
		stage.show();
	}
}