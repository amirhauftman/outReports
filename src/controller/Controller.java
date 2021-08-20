package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map.Entry;

import listeners.ReportsModelListener;
import listeners.ReportsUIListener;
import model.ExpenseCategory;
import model.Model;
import model.MonthlyIncome;
import run.Main;
import view.AbstractReportsView;

public class Controller implements ReportsModelListener, ReportsUIListener{

	private Model model;
	private AbstractReportsView view;
	
	public Controller(Model model, AbstractReportsView view) {
		this.model = model;
		this.view = view;
		
		this.model.registerListener(this);
		this.view.registerListener(this);
	}

	@Override
	public void addMonthlyIncomeFromView(String name, String amount) {
		try {
			this.model.addMonthlyIncome(name, Integer.parseInt(amount));
		}catch(NumberFormatException e) {
			this.view.showErrorMessage(e.getMessage());
		}
		
	}
	
	@Override
	public void addedMonthlyIncomeFromModel(MonthlyIncome income) {
		this.view.addMonthlyIncomeToView(income);
	}

	@Override
	public void addExpenseCategoryFromView(String name) {
		try {
			this.model.addExpenseArea(name);
		}catch(Exception e) {
			this.view.showErrorMessage(e.getMessage());
		}
	}

	@Override
	public void addedExpenseCategoryFromModel(ExpenseCategory category) {
		this.view.addExpenseCategoryToView(category);
		
	}

	@Override
	public void addedExpenseFromModel(ExpenseCategory category, Entry<String, Double> entry) {
		this.view.addExpenseToView(category, entry);
	}

	@Override
	public void addExpenseFromView(String areaName, String name, String amount) {
		try {
			this.model.addExpense(areaName, name, Double.parseDouble(amount));
		}catch ( NumberFormatException e) {
			this.view.showErrorMessage(e.getMessage());
		}
		
	}

	@Override
	public void removedExpenseFromModel(ExpenseCategory category, String expenseName) {
		this.view.removeExpenseInView(category, expenseName);
	}

	@Override
	public void removeExpenseFromView(String areaName, String key) {
		this.model.removeExpense(areaName, key);
		
	}

	@Override
	public void updateIncomeFromView(String name, String amount) {
		try {
			this.model.updateIncome(name, Integer.parseInt(amount));
		}catch(NumberFormatException e) {
			this.view.showErrorMessage(e.getMessage());
		}
		
	}

	@Override
	public void updatedIncomeFromModel(MonthlyIncome income) {
		this.view.updateIncomeInView(income);
		
	}

	public void initFromFile() {
		for (MonthlyIncome income : this.model.getIncomes()) {
			this.view.addMonthlyIncomeToView(income);
		}
		
		for (ExpenseCategory expenseCategory : this.model.getExpenses()) {
			this.view.addExpenseCategoryToView(expenseCategory);
			for (Entry<String, Double> entry : expenseCategory.getEntrySet()) {
				this.view.addExpenseToView(expenseCategory, entry);
			}
		}
		
	}

	@Override
	public void save() throws FileNotFoundException, IOException {
		this.model.save(Main.FILEPATH);
		
	}
	
	
	
}
