package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map.Entry;

import controller.Controller;
import listeners.ReportsModelListener;

public class Model implements Serializable {

	private ArrayList<MonthlyIncome> incomes;
	private ArrayList<ExpenseCategory> expenses;

	private transient ArrayList<ReportsModelListener> listeners;

	public Model() {
		this.incomes = new ArrayList<>();
		this.expenses = new ArrayList<>();

		this.listeners = new ArrayList<>();
	}

	public Model(String filepath) throws FileNotFoundException, ClassNotFoundException, IOException {
		this.load(filepath);
	}

	public ArrayList<MonthlyIncome> getIncomes() {
		return incomes;
	}

	public ArrayList<ExpenseCategory> getExpenses() {
		return expenses;
	}

	public int getNumOfIncomes() {
		return this.incomes.size();
	}

	public int getNumOfExpenseAreas() {
		return this.expenses.size();
	}

	public int getTotalIncomes() {
		int sum = 0;
		for (MonthlyIncome in : incomes) {
			sum += in.getAmount();
		}
		return sum;
	}

	public double getTotalExpenses() {
		double sum = 0;
		for (ExpenseCategory expenseArea : expenses) {
			for (Entry<String, Double> expense : expenseArea.getEntrySet()) {
				sum += expense.getValue();
			}
		}
		return sum;
	}

	public double getIncomeMinusExpenses() {
		return this.getTotalIncomes() - this.getTotalExpenses();
	}

	public void callItAMonth() {
		// flush month
	}

	public void addMonthlyIncome(String name, int amount) {
		MonthlyIncome income = new MonthlyIncome(name, amount);
		this.incomes.add(income);
		this.fireAddedMonthlyIncome(income);
	}

	private void fireAddedMonthlyIncome(MonthlyIncome income) {
		for (ReportsModelListener l : listeners) {
			l.addedMonthlyIncomeFromModel(income);
		}
	}

	public void addExpenseArea(String name) {
		ExpenseCategory category = new ExpenseCategory(name);
		this.expenses.add(category);
		this.fireAddedExpenseCategory(category);
	}

	private void fireAddedExpenseCategory(ExpenseCategory category) {
		for (ReportsModelListener l : listeners) {
			l.addedExpenseCategoryFromModel(category);
		}

	}

	public void addExpense(String expenseCategory, String expenseName, double expenseAmount) {
		ExpenseCategory category = getExpenseCateogryByName(expenseCategory);
		category.addExpense(expenseName, expenseAmount);
		this.fireAddedExpense(category, category.getExpenseByName(expenseName));
	}

	private void fireAddedExpense(ExpenseCategory category, Entry<String, Double> entry) {
		for (ReportsModelListener l : listeners) {
			l.addedExpenseFromModel(category, entry);
		}

	}
	
	public ExpenseCategory getExpenseCateogryByName(String name) {
		for (ExpenseCategory expenseCategory : expenses) {
			if ( expenseCategory.getAreaName().equals(name))
				return expenseCategory;
		}
		return null;
	}

	public void removeExpense(String expenseCategory, String expenseName) {
		ExpenseCategory category = this.getExpenseCateogryByName(expenseCategory);
		category.removeFromExpense(expenseName);
		fireRemovedExpense(category, expenseName);
		
	}
	
	private void fireRemovedExpense(ExpenseCategory category, String expenseName) {
		for (ReportsModelListener l : listeners) {
			l.removedExpenseFromModel(category, expenseName);
		}
		
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Expenses: ");
		for (ExpenseCategory expenseCategory : expenses) {
			sb.append(expenseCategory.toString() + "\n");
		}
		sb.append("Incomes: ");
		for (MonthlyIncome income : incomes) {
			sb.append(income.toString() + "\n");
		}
		return sb.toString();

	}

	public void save(String filepath) throws FileNotFoundException, IOException {

		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(filepath));
		outFile.writeObject(this);
		outFile.close();

	}

	public void load(String filepath) throws FileNotFoundException, IOException, ClassNotFoundException {
		if ((new File(filepath)).exists()) {

			ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(filepath));
			Model model = (Model) inFile.readObject();

			inFile.close();

			this.expenses = model.expenses;
			this.incomes = model.incomes;
		} else {
			this.incomes = new ArrayList<>();
			this.expenses = new ArrayList<>();
		}
		this.listeners = new ArrayList<>();
	}

	public void registerListener(ReportsModelListener listener) {
		this.listeners.add(listener);
	}

	public void updateIncome(String name, int amount) {
		MonthlyIncome income = getIncomeByName(name);
		income.setAmount(amount);
		fireUpdatedIncome(income);
	}

	private void fireUpdatedIncome(MonthlyIncome income) {
		for (ReportsModelListener l : listeners) {
			l.updatedIncomeFromModel(income);
		}
	}

	private MonthlyIncome getIncomeByName(String name) {
		for (MonthlyIncome income : incomes) {
			if ( income.getName().equals(name))
				return income;
		}
		return null;
	}
	
	

}
