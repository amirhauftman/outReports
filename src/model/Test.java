package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Test {
	static Scanner keyboard = new Scanner(System.in);

	static Model model;

	static final String FILEPATH = "Data.dat";
	static final int EXIT = 3;

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		model = new Model(FILEPATH);
		int pick = inputIntegerInRange(1, EXIT, getMenu());

		while (pick != EXIT) {

			if (pick == 1) {
				model.addExpenseArea(inputString("Add expense name"));
			} else if (pick == 2) {
				int expenseNumber = (inputIntegerInRange(1, model.getNumOfExpenseAreas(), getModelExpensesMenu())) - 1;
				int value = inputInteger("Insert expense amount: ");
				String name = inputString("Insert expense name: ");
				ExpenseCategory expenseCategory = model.getExpenses().get(expenseNumber);
				expenseCategory.addExpense(name, (double) value);
			}
			pick = inputIntegerInRange(1, EXIT, getMenu());
		}
		System.out.println(model.toString());
		model.save(FILEPATH);
	}

	private static String getMenu() {

		StringBuffer sb = new StringBuffer();
		sb.append("Pick from menu: \n");
		sb.append("1) add expense category\n");
		sb.append("2) add expenses\n");
		sb.append("3) exit\n");

		return sb.toString();
	}

	public static String inputString(String message) {
		System.out.println(message);
		return keyboard.nextLine();
	}

	public static String getModelExpensesMenu() {
		int i = 1;
		StringBuffer menu = new StringBuffer("Pick an expense: \n");
		for (ExpenseCategory expense : model.getExpenses()) {
			menu.append(i + ") " + expense.getAreaName() + "\n");
			i++;
		}
		return menu.toString();
	}

	public static int inputIntegerInRange(int min, int max, String messages) {
		int choose;
		do {
			choose = inputInteger(messages);
		} while (choose < min || choose > max);
		return choose;
	}

	public static void calculateIncome() {
		int totalIncome = 0;

		while (inputBoolean("Enter more incomes?")) {
			totalIncome += inputInteger("Insert income: ");
		}

		System.out.println("Total income: " + totalIncome);
	}

	public static boolean inputBoolean(String message) {
		System.out.println(message);
		System.out.println("(Y/any for no)");
		return keyboard.nextLine().toLowerCase().charAt(0) == 'y';
	}

	public static int inputInteger(String message) {
		System.out.println(message);
		int num = Integer.MIN_VALUE;
		try {
			num = Integer.parseInt(keyboard.nextLine());
		} catch (Exception e) {
			return num;
		}
		return num;
	}

}
