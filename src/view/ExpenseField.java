package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import view.MyLabel.LabelType;

public class ExpenseField extends HBox {

	private MyLabel lblName;
	private MyLabel lblExpense;
	private MyButton btnDelete;
	public ExpenseField(String name, Double expense) {
		super();
		this.lblName = new MyLabel(name, LabelType.FIELD);
		this.lblName.setPrefWidth(160);
		this.lblExpense = new MyLabel(Math.round(expense * 100) / 100.0 + "", LabelType.FIELD);
		this.btnDelete = new MyButton("x");
		btnDelete.setStyle("-fx-text-fill: "+Util.PURPLE+";-fx-background-color: transparent;-fx-font-size: 16;");
		this.setAlignment(Pos.CENTER_LEFT);
		this.setPadding(new Insets(0,0,20,0));
		this.getChildren().addAll(lblName, lblExpense, btnDelete);
	}
	public MyButton getDeleteButton() {
		return this.btnDelete;
	}
	public MyLabel getLblName() {
		return lblName;
	}
	
	
}
