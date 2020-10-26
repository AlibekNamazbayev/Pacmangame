import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
public class Map extends Pane{
	public int unit = 30;
	public int size;
	public int[][] map;
	public int[][] map1;
	private Position start;
	public int x;
	public int y;
	public Map(String str)throws FileNotFoundException{
		File file  = new File(str);
		Scanner scanner = new Scanner(file);
		String s = scanner.nextLine();		
		String[] stm = s.split("");
		size = Integer.parseInt(stm[0]);
		map = new int[size][size];
		map1 = new int[size][size];
		GridPane gridPane = new GridPane();
		for(int i = 0; i < size; i++){			
			s = scanner.nextLine();
			stm = s.split("");
			int a = 0;
			for(int j = 0; j < size; j++){
				map[j][i]= Integer.parseInt(stm[j + a]);
				a +=1;
				Rectangle rectangle = new Rectangle(unit - 1, unit - 1);
				rectangle.setStroke(Color.BLACK);
				if(map[j][i] == 1){
					rectangle.setFill(Color.BLACK);
				}
				else if(map[j][i] != 1){
					rectangle.setFill(Color.WHITE);
				}
				if(map[j][i] == 2 ){					
					x = j;
					y = i;
				}
				gridPane.add(rectangle,j,i);
			}
		}
		getChildren().addAll(gridPane);
	}

	int getUnit(){
		return unit;
	}
	int getSize(){
		return size;
	}
	int[][] getMap(){
		return map;
	}
	public Position getStartPosition(){
		start = new Position(x,y);
		return start;
	}
	public int[][] getMap1(){
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(map[j][i] == 1)
					map1[j][i] = 0;
				else map1[j][i] = 1;
			}
		}
		return map1;
	}
}