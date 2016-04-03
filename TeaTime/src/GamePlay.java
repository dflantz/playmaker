import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GamePlay{

	private static GameFrame frame;
	private static ArrayList<Character> characters = new ArrayList<>();
	public static boolean isSimulating;
	public static int currentStage;
	private static Play play;
	private static boolean isLoading;

	public static void main(String[] args){
		currentStage = 0;
		isSimulating = false;
		play = new Play();
		Stage stage = new Stage();

		frame = new GameFrame();
		frame.setVisible(true);

		characters = new ArrayList<>();
		characters.add(new Character(true, 100, 100, Color.RED));
		GameBoard.placeCharacters(characters);


		while(true){
			try {
				Thread.sleep(50);
				frame.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void newPlay(){
		currentStage = 0;
		isSimulating = false;
		play = new Play();
		Stage stage = new Stage();

		frame = new GameFrame();
		frame.setVisible(true);

		characters = new ArrayList<>();
		characters.add(new Character(true, 100, 100, Color.RED));
		GameBoard.placeCharacters(characters);


		while(true){
			try {
				Thread.sleep(50);
				frame.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void readData(String fileName){
		isLoading = true;
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			play = (Play) objectInputStream.readObject();
			play.setStages((ArrayList<Stage>) objectInputStream.readObject());
			characters = play.getStages().get(0).getCharacters();
			objectInputStream.close();
			fileInputStream.close();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		for(int i=0; i<getCharacters().size(); i++){
//			System.out.println(getCharacters().get(i));
//		}

		currentStage = 0;
		isSimulating = false;

		frame = new GameFrame();
		GameBoard.setCharacters(getCharacters());
		frame.repaint();
		frame.setVisible(true);

		while(true){
			try {
				Thread.sleep(50);
				frame.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Play getPlay(){
		return play;
	}
	public static JFrame getFrame(){
		return frame;
	}
	public static ArrayList<Character> getCharacters(){
		return characters;
	}
	public static void addCharacter(Point point){
		characters.add(new Character(true, point.x, point.y, Color.RED));
		GameBoard.placeCharacters(characters);
	}


	public static void clear() {
		characters = new ArrayList<>();
		GameBoard.clear();
	}

	public static void simulate() {
		//System.out.println("Attempting to Simulate");
		if(isSimulating)
			return;
		else
			isSimulating = true;

		for(int i=0; i<characters.size(); i++){
			Character c = characters.get(i);
			characters.get(i).execute();
		}
	}

	public static void setCharacters(ArrayList<Character> characters) {
		GamePlay.characters = characters;
	}

	public static void previousStage(){
		//Save to stage arraylist
		ArrayList<Stage> stages = Play.getStages();
		if(Play.getStages().size() == currentStage)
			Play.getStages().add(new Stage(getCharacters()));
		else
			Play.getStages().set(currentStage, new Stage(getCharacters()));


		if(GamePlay.currentStage > 0)
			GamePlay.currentStage--;

		for(int i=0; i<GamePlay.getCharacters().size(); i++){
			GamePlay.setCharacters(Play.getStages().get(GamePlay.currentStage).getCharacters());
		}
		GameBoard.setCharacters(GamePlay.getCharacters());
		GameFrame.update(currentStage+"");
	}

	public static void nextStage() {

		//setCharacters(Play.getStages().get(currentStage).getCharacters());

//		for(int i=0; i<getCharacters().size(); i++){
//			System.out.println(GamePlay.getCharacters().get(i));
//		}

		//Save to stage arraylist
		if(Play.getStages().size() == currentStage){
			ArrayList<Character> temp = new ArrayList<Character>();
			for(int i=0; i<getCharacters().size(); i++){
				Character tempChar = new Character();
				tempChar.setxLoc(getCharacters().get(i).getxLoc());
				tempChar.setyLoc(getCharacters().get(i).getyLoc());
				tempChar.setColor(getCharacters().get(i).getColor());
				ArrayList<Point> tempAction = new ArrayList<Point>();
				for(int k=0; k<getCharacters().get(i).getAction().size(); k++){
					tempAction.add(getCharacters().get(i).getAction().get(k));
				}
				tempChar.setAction(tempAction);
				temp.add(tempChar);
			}
			if(!isLoading)
				Play.getStages().add(new Stage(temp));

		} else {
			ArrayList<Character> temp = new ArrayList<Character>();
			for(int i=0; i<getCharacters().size(); i++){
				Character tempChar = new Character();
				tempChar.setxLoc(getCharacters().get(i).getxLoc());
				tempChar.setyLoc(getCharacters().get(i).getyLoc());
				tempChar.setColor(getCharacters().get(i).getColor());
				ArrayList<Point> tempAction = new ArrayList<Point>();
				for(int k=0; k<getCharacters().get(i).getAction().size(); k++){
					tempAction.add(getCharacters().get(i).getAction().get(k));
				}
				tempChar.setAction(tempAction);
				temp.add(tempChar);
			}
			
			if(!isLoading)
				Play.getStages().set(currentStage, new Stage(temp));
		}


		ArrayList<Stage> stages = Play.getStages();
		ArrayList<Character> chars = stages.get(0).getCharacters();

		currentStage++;


		//Move all to to end of action
		for(int i=0; i<getCharacters().size(); i++){		
			ArrayList<Point> action = getCharacters().get(i).getAction();
			getCharacters().get(i).execute();
		}


	}

	public static void initNextStage(Character c){
		//System.out.println(c);

		//System.out.println("Init Next Stage: " + GamePlay.getCharacters().size());

		if(GamePlay.getCharacters().size()==0 && !GamePlay.getCharacters().get(0).isDone())
			return;
		for(int i=0; i<GamePlay.getCharacters().size(); i++){
			if(!GamePlay.getCharacters().get(i).isDone()){
				return;
			} 
		}

		ArrayList<Character> newCharacters = new ArrayList<Character>();

		if(isLoading){
			
			for(int i=0; i<GamePlay.getCharacters().size() && i<Play.getStages().get(GamePlay.currentStage).getCharacters().size(); i++){
				if(GameBoard.hasChanged)
					newCharacters.add(i, new Character(GamePlay.getCharacters().get(i).getxLoc(), GamePlay.getCharacters().get(i).getyLoc(), GamePlay.getCharacters().get(i).getColor()));
				else {
					Play.getStages().trimToSize();
					if(currentStage < Play.getStages().size()-1)
						newCharacters.add(new Character(GamePlay.getCharacters().get(i).getxLoc(), GamePlay.getCharacters().get(i).getyLoc(), Play.getStages().get(GamePlay.currentStage).getCharacters().get(i).getAction(), GamePlay.getCharacters().get(i).getColor()));
					else 
						GamePlay.previousStage();
				}

			}
		} else {
			for(int i=0; i<GamePlay.getCharacters().size(); i++){
				if(GameBoard.hasChanged)
					newCharacters.add(i, new Character(GamePlay.getCharacters().get(i).getxLoc(), GamePlay.getCharacters().get(i).getyLoc(), GamePlay.getCharacters().get(i).getColor()));
				else {
					if(currentStage < Play.getStages().size()-1)
						newCharacters.add(new Character(GamePlay.getCharacters().get(i).getxLoc(), GamePlay.getCharacters().get(i).getyLoc(), Play.getStages().get(GamePlay.currentStage).getCharacters().get(i).getAction(), GamePlay.getCharacters().get(i).getColor()));
				}

			}
		}


		Character temp = null;
		for(int i=0; i<GamePlay.getCharacters().size(); i++){
			temp = GamePlay.getCharacters().get(i);
		}



		GameBoard.placeCharacters(newCharacters);
		GamePlay.setCharacters(newCharacters);

		Play.getStages().add(new Stage());
		Play.getStages().get(currentStage).getCharacters().add(c);

		GameFrame.update(currentStage+"");
		GameBoard.hasChanged = false;
		//		GameBoard.clear();
	}
	public static void sameStage() {
		// new characters are exactly the same as the old.

		for(int i=0; i<getCharacters().size(); i++){
			if(!getCharacters().get(i).isDone())
				return;
		}

		ArrayList<Character> temp = new ArrayList<Character>();
		for(int i=0; i<getCharacters().size(); i++){
			Character tempChar = new Character();
			tempChar.setxLoc((int)getCharacters().get(i).getFirst().getX());
			tempChar.setyLoc((int)getCharacters().get(i).getFirst().getY());
			tempChar.setColor(getCharacters().get(i).getColor());
			ArrayList<Point> tempAction = new ArrayList<Point>();
			for(int k=0; k<getCharacters().get(i).getAction().size(); k++){
				tempAction.add(getCharacters().get(i).getAction().get(k));
			}
			tempChar.setAction(tempAction);
			temp.add(tempChar);
		}

		GameBoard.setCharacters(temp);
		GamePlay.setCharacters(temp);

		GamePlay.isSimulating = false;
	}
}
