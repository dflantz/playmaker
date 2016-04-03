import java.io.Serializable;
import java.util.ArrayList;

public class Stage implements Serializable{
	private ArrayList<Character> characters;
	
	public Stage(){
		characters = new ArrayList<Character>();
	}
	
	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(ArrayList<Character> characters) {
		this.characters = characters;
	}

	public Stage(ArrayList<Character> characters){
		this.characters = characters;
	}
	public String toString(){
		StringBuilder s = new StringBuilder();
		
		for(int i=0; i<characters.size(); i++){
			s.append("("+characters.get(i).getxLoc()+","+characters.get(i).getyLoc()+")");
		}
		
		return s.toString();
	}
}
