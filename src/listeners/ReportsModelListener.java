package listeners;

import java.util.Map.Entry;

import model.ExpenseCategory;
import model.MonthlyIncome;

public interface ReportsModelListener {

	void addedMonthlyIncomeFromModel(MonthlyIncome income);

	void addedExpenseCategoryFromModel(ExpenseCategory category);

	void addedExpenseFromModel(ExpenseCategory category, Entry<String, Double> entry);

	void removedExpenseFromModel(ExpenseCategory category, String expenseName);

	void updatedIncomeFromModel(MonthlyIncome income);

}
