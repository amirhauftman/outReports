package view;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.MyInputField.InputType;
import view.MyLabel.LabelType;

public class Util {

	public static final String BLUE = "#37355e";
	public static final String PURPLE = "#4b0082";
	public static final String YELLOW = "#ecaf80";
	
	
	public static ArrayList<Node> getIncomeStageItems(){
		ArrayList<Node> items = new ArrayList<>();
		
		items.add(new MyLabel("Add a Monthly income", LabelType.SUB_HEADER));
		items.add(new MyInputField("Insert name: ", InputType.TEXT));
		items.add(new MyInputField("Insert amount: ", InputType.NUMBER));
		Button ok = new Button("Ok");
		items.add(ok);
//		ok.setOnAction(e -> stage.close());
		
		return items;
	}
}
