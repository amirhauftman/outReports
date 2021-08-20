package view;

import java.util.Map.Entry;

import listeners.ReportsUIListener;
import model.ExpenseCategory;
import model.MonthlyIncome;

public interface AbstractReportsView {

	void registerListener(ReportsUIListener listener);

	void showErrorMessage(String message);

	void addMonthlyIncomeToView(MonthlyIncome income);

	void addExpenseCategoryToView(ExpenseCategory category);

	void addExpenseToView(ExpenseCategory category, Entry<String, Double> entry);

	void removeExpenseInView(ExpenseCategory category, String expenseName);

	void updateIncomeInView(MonthlyIncome income);
	
}
