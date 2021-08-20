package listeners;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReportsUIListener {

	void addMonthlyIncomeFromView(String name, String amount);

	void addExpenseCategoryFromView(String name);

	void addExpenseFromView(String areaName, String name, String amount);

	void removeExpenseFromView(String areaName, String key);

	void updateIncomeFromView(String name, String amount);

	void save() throws FileNotFoundException, IOException;


}
