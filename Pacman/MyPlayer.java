import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
public class MyPlayer implements Player{
	private Circle ball;
	private Map map;
	private Position position;
	int x;
	int y;
	public MyPlayer(Map map1){
		this.map = map1;
		x = map.x * 30 + 15;
		y = map.y * 30 + 15;
		ball = new Circle(map1.x * 30 + 15, map1.y * 30 + 15, 15);
		ball.setFill(Color.RED);
		map.getChildren().add(ball);
	}
	public void moveRight(){
		if(x < map.getSize() * map.getUnit() - 16){
			if(map.getMap()[map.x + 1][map.y] != 1){
				map.x++;
		x +=30;
		ball.setCenterX(x);}
		}
	}
	public void moveLeft(){
		if(x > 16){
			if(map.getMap()[map.x - 1][map.y] != 1){
				map.x--;
		x -=30;
		ball.setCenterX(x);}
		}
	}
	public void moveUp(){
		if(y > 16){
			if(map.getMap()[map.x][map.y - 1] != 1){
				map.y--;
		y -=30;
		ball.setCenterY(y);}
		}
	}
	public void moveDown(){
		if(y < map.getSize() * map.getUnit() - 16){
			if(map.getMap()[map.x][map.y + 1] != 1){
				map.y++;
		y +=30;
		ball.setCenterY(y);}
		}
	}
	public Position getPosition(){
		position = new Position(map.x,map.y);
		return position;
	}
}