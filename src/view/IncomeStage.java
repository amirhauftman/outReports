package view;

import java.util.ArrayList;

import javafx.scene.Node;
import model.MonthlyIncome;

public class IncomeStage extends MyStage {

	private static ArrayList<Node> items = Util.getIncomeStageItems();

	private MonthlyIncome income;

	public IncomeStage(ArrayList<Node> items) {
		super(items);
	}

	public MonthlyIncome getIncome() {
		return income;
	}

	public void setIncome(MonthlyIncome income) {
		this.income = income;
	}


}
