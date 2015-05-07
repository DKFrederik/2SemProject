package modelLayer;
public class Vertex {
	
	private String name;
	private int color;
	
	public Vertex(String name){
		this.name = name;
	}
	public String toString() { //For debugging purposes
		return name + "\t" + color;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getColor(){
		return color;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public boolean equals(Object obj) {
		Vertex other = (Vertex)obj;
		return name.equals(other.getName());
	}
	
	public int getHashCode() {
		return name.hashCode();
	}
}