import java.io.*;
import java.util.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.geometry.Bounds;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.*;
public class BotPlayer implements Player  {
	Map map;
	List buf = new ArrayList();
	int unit;
	Circle ball;
	Position position;
	int time;
	int[][] fillpane ;
    int[][] pane1;
	ArrayList<Position> path;
	int x,y,a;
	Thread thread;
	public BotPlayer(Map map){
		this.map = map;
		fillpane = new int[map.getSize()][map.getSize()]; 
		pane1 = map.getMap1();
		unit = map.getUnit();
		a = map.getSize();
		MyCircle();
	}
	public void MyCircle(){
		x = map.x * 30 + 15;
		y = map.y * 30 + 15;		
		ball = new Circle(map.x * 30 + 15, map.y * 30 + 15, 15);
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
	void push(Position p, int n){
		if(fillpane[p.getX()][p.getY()]<=n) 
			return; 
		fillpane[p.getX()][p.getY()]=n; 
		buf.add(p); 
	}
	Position pop(){
		if(buf.isEmpty()) 
			return null;
		return (Position)buf.remove(0);
	}
	ArrayList<Position> find(Position start, Position end){
		int tx=0, ty=0, n = 0, t=0;
		Position p;
		for(int i=0;i<fillpane.length;i++)
			Arrays.fill(fillpane[i],Integer.MAX_VALUE);
		push(start, 0);		
		while((p = pop())!=null){ 
			n=fillpane[p.getX()][p.getY()]+pane1[p.getX()][p.getY()];
			if((p.getX()+1<pane1.length)&&pane1[p.getX()+1][p.getY()]!=0) 
				push(new Position(p.getX()+1, p.getY()), n);
			if((p.getX()-1>=0)&&(pane1[p.getX()-1][p.getY()]!=0)) 
				push(new Position(p.getX()-1, p.getY()), n);
			if((p.getY()+1<pane1[p.getX()].length)&&(pane1[p.getX()][p.getY()+1]!=0)) 
				push(new Position(p.getX(), p.getY()+1), n);
			if((p.getY()-1>=0)&&(pane1[p.getX()][p.getY()-1]!=0)) 
				push(new Position(p.getX(), p.getY()-1), n);
		}
		ArrayList<Position> pos = new ArrayList<>();
		pos.add(end);
		int x = end.getX();
		int y = end.getY();
		n = Integer.MAX_VALUE;
		while(!start.equals(new Position(x,y))){ 
				try{
					if(fillpane[x+1][y]<n){
					tx=x+1; ty=y; t=fillpane[x+1][y];
					}
				}catch(Exception ex){}
				try{
					if(fillpane[x-1][y]<n){
						tx=x-1; ty=y; t=fillpane[x-1][y];
						}
				}catch(Exception ex){}
				try{
					if(fillpane[x][y+1]<n){
						tx=x; ty=y+1; t=fillpane[x][y+1];
						}
				}catch(Exception ex){}
				try{
					if(fillpane[x][y-1]<n){
						tx=x; ty=y-1; t=fillpane[x][y-1];
						}
				}catch(Exception ex){}
				x = tx;
				y = ty;
				n = t;
				if(!end.equals(new Position(x,y)))
					pos.add(new Position(x,y));
		}
		return pos;
	}
	public void feed(Food food){
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try{
					Thread.sleep(1000);
				}catch(Exception e){}
				while(true){
					path = find(getPosition(),food.getPosition());
					Collections.reverse(path);
					for(Position p1: path){
						Platform.runLater(new Runnable(){
							public void run(){
								if(p1.getY()-getPosition().getY()<0)
									moveUp();
								else if(p1.getX()-getPosition().getX()>0)
									moveRight();
								else if(p1.getX()-getPosition().getX()<0)
									moveLeft();
								else if(p1.getY()-getPosition().getY()>0)
									moveDown();
							}
						});
						try{
							Thread.sleep(250);
						}catch(Exception e){}
					}
				}
			}
		});
		t1.start();
	}
}