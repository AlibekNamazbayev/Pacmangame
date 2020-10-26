public class Position{
	private int x;
	private int y;
	Position(int a,int b){
		this.x = a;
		this.y = b;}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int a){
		this.x = a;
	}
	public void setY(int b){
		this.y = b;
	}
	public boolean equals(Position pos){
		if(this.getX() == pos.getX() && this.getY() == pos.getY())
			return true;
		else
			return false;
	}
}