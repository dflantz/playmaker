import java.io.Serializable;
import java.util.ArrayList;

public class Play implements Serializable{
	private static ArrayList<Stage> stages;
	
	public Play(){
		stages = new ArrayList<Stage>();
		stages.add(new Stage());
	}

	public static ArrayList<Stage> getStages() {
		return stages;
	}
	
	public void setStages(ArrayList<Stage> stages){
		this.stages = stages;
	}
}
