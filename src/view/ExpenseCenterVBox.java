package view;

import javafx.scene.Node;
import model.ExpenseCategory;
import view.MyLabel.LabelType;

public class ExpenseCenterVBox extends CenterVBox {

	public ExpenseCenterVBox(ExpenseCategory category) {
		super();
		this.getChildren().addAll(new MyLabel(category.getAreaName(), LabelType.HEADER));
	}
	
	public void addNode(Node node) {
		this.getChildren().add(node);
	}

	public void addNodeBeforeLast(Node node) {
		this.getChildren().add(this.getChildren().size() -1, node);
		
	}

	
	
}
