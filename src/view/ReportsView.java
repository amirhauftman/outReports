package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import listeners.ReportsUIListener;
import model.ExpenseCategory;
import model.MonthlyIncome;
import view.MyInputField.InputType;
import view.MyLabel.LabelType;
import view.WindowButton.WindowButtonType;

public class ReportsView implements AbstractReportsView {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;

	private static double xOffset = 0;
	private static double yOffset = 0;

	private BorderPane root;

	// categories data
	private VBox vbIncomes;
	private HBox hbIncomesCategories;
	private HBox hbExpensesCategories;

	// graphes
	MyBarChart<String, Number> bcIncomes;
//	MyBarChart<String, Number> bc1;

	// add amount stage data
	private MyInputField inAddMonthlyIncomeName;
	private MyInputField inAddMonthlyIncomeAmount;

	// add expese category data
	private MyInputField inAddExpenseCategoryName;

	// add expense data
	private MyInputField inAddExpenseName;
	private MyInputField inAddExpenseAmount;

	// result data
	private MyLabel totalIncome;
	private MyLabel totalExpense;
	private MyLabel totalSaving;

	private CenterVBox vbDashboard;
	private CenterVBox vbCategories;
	private CenterVBox vbOverview;
	private MyStage is;

	private ArrayList<ReportsUIListener> listeners = new ArrayList<>();
	private ArrayList<MonthlyIncome> incomes = new ArrayList<>();

	private ArrayList<ExpenseCategory> expenses = new ArrayList<>();
	private ArrayList<ExpenseCenterVBox> expensesCenters = new ArrayList<>();

	public ReportsView(Stage stage) {
		stage.initStyle(StageStyle.TRANSPARENT);

		root = new BorderPane();
		root.setStyle("-fx-background-color: white; -fx-background-radius: 50 0 50 0");

		// top
		HBox hbCenter = agentsPanel(stage);
		WindowButton exit = new WindowButton(WindowButtonType.EXIT);
		WindowButton minimize = new WindowButton(WindowButtonType.MINIMIZE);

		hbCenter.setAlignment(Pos.CENTER_RIGHT);
		hbCenter.setStyle("-fx-background-color:" + Util.PURPLE + "; -fx-background-radius: 30 0 0 0");

		hbCenter.getChildren().addAll(minimize, exit);

		// center
		// Dashboard
		vbDashboard = new CenterVBox();
		MyLabel lblDashboard = new MyLabel("Welcome, Matanel", MyLabel.LabelType.HEADER);
		VBox vbBarCharts = new VBox(10);

		Axis<String> xAxis = new CategoryAxis();
		Axis<Number> yAxis = new NumberAxis();
		bcIncomes = new MyBarChart<String, Number>(xAxis, yAxis);
		bcIncomes.setPrefHeight(150);
		bcIncomes.setData(getIncomeData(), "", "Income's name", "Amount[$]");

		Axis<String> xAxis1 = new CategoryAxis();
		Axis<Number> yAxis1 = new NumberAxis();
		MyBarChart<String, Number> bc2 = new MyBarChart<String, Number>(xAxis1, yAxis1);
		bc2.setPrefHeight(150);
		vbBarCharts.getChildren().addAll(new MyLabel("Incomes: ", LabelType.SUB_HEADER), bcIncomes,
				new MyLabel("Expenses: ", LabelType.SUB_HEADER), bc2);

		HBox hbSave = new HBox();
		hbSave.setPrefHeight(HBox.BASELINE_OFFSET_SAME_AS_HEIGHT);
		hbSave.setAlignment(Pos.CENTER_RIGHT);
		CategoryButton btnSave = new CategoryButton("Save");
		btnSave.setOnAction(e ->{
			for (ReportsUIListener l : listeners) {
				try {
					l.save();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		hbSave.getChildren().add(btnSave);

		HBox hbResults = new HBox();
		hbResults.setPadding(new Insets(0, 0, 40, 0));
		hbResults.setPrefWidth(1000 - 80 - 160);
		VBox vbIncome = new VBox();
		vbIncome.setPrefWidth(260);
		vbIncome.setAlignment(Pos.CENTER);

		totalIncome = new MyLabel("0", LabelType.DATA);
		MyLabel lblTotalIncome = new MyLabel("Incomes", LabelType.SUB_HEADER);
		vbIncome.getChildren().addAll(totalIncome, lblTotalIncome);
		vbIncome.setAlignment(Pos.CENTER);
		VBox vbExpense = new VBox();
		totalExpense = new MyLabel("0", LabelType.DATA);
		vbExpense.setPrefWidth(260);
		vbExpense.setAlignment(Pos.CENTER);
		MyLabel lblTotalExpense = new MyLabel("Expenses", LabelType.SUB_HEADER);
		vbExpense.getChildren().addAll(totalExpense, lblTotalExpense);

		VBox vbSaving = new VBox();
		vbSaving.setAlignment(Pos.CENTER);
		vbSaving.setPrefWidth(260);
		totalSaving = new MyLabel("0", LabelType.DATA);
		MyLabel lblTotalSaving = new MyLabel("Savings", LabelType.SUB_HEADER);
		vbSaving.getChildren().addAll(totalSaving, lblTotalSaving);

		hbResults.getChildren().addAll(vbIncome, vbExpense, vbSaving);

		vbDashboard.getChildren().addAll(lblDashboard, vbBarCharts, hbResults, hbSave);

		// categories
		vbCategories = new CenterVBox();
		MyLabel lblCategories = new MyLabel("Categories", MyLabel.LabelType.HEADER);

		vbIncomes = new VBox(2);
		vbIncomes.setPadding(new Insets(0, 0, 40, 0));
		MyLabel lblIncomes = new MyLabel("Incomes", MyLabel.LabelType.SUB_HEADER);
		hbIncomesCategories = new HBox(2);
		CategoryButton btnAddIncome = new CategoryButton("Add");

		btnAddIncome.setOnAction(e -> {
			ArrayList<Node> items = new ArrayList<>();
			MyLabel lblAddMonthlyIncome = new MyLabel("Add a Monthly income", LabelType.SUB_HEADER);
			items.add(lblAddMonthlyIncome);
			inAddMonthlyIncomeName = new MyInputField("Insert name: ", InputType.TEXT);
			items.add(inAddMonthlyIncomeName);
			inAddMonthlyIncomeAmount = new MyInputField("Insert amount: ", InputType.NUMBER);
			items.add(inAddMonthlyIncomeAmount);
			CategoryButton btnAddMonthlyIncome = new CategoryButton("Ok");
			is = new MyStage(items);
			items.add(btnAddMonthlyIncome);
			btnAddMonthlyIncome.setOnAction(e1 -> {
				is.close();
				String name = inAddMonthlyIncomeName.getValue();
				String amount = inAddMonthlyIncomeAmount.getValue();
				// add the amount
				if (!(name.equals("") || amount.equals(""))) {
					for (ReportsUIListener l : listeners) {
						l.addMonthlyIncomeFromView(name, amount);
					}
				} else {
					showErrorMessage("insert data to the required fields");
				}
			});
			btnAddMonthlyIncome.setPrefWidth(60);
			is.addItem(btnAddMonthlyIncome);
		});
		hbIncomesCategories.getChildren().addAll(btnAddIncome);

		vbIncomes.getChildren().addAll(lblIncomes, hbIncomesCategories);

		VBox vbExpenses = new VBox(2);
		vbExpenses.setPadding(new Insets(0, 0, 40, 0));
		MyLabel lblExpenses = new MyLabel("Expenses", MyLabel.LabelType.SUB_HEADER);
		hbExpensesCategories = new HBox(2);
		CategoryButton btnAddExpense = new CategoryButton("Add");
		btnAddExpense.setOnAction(e -> {
			ArrayList<Node> items = new ArrayList<>();
			MyLabel lblAddExpenseCategory = new MyLabel("Add an Expense Category", LabelType.SUB_HEADER);
			items.add(lblAddExpenseCategory);
			inAddExpenseCategoryName = new MyInputField("Insert name: ", InputType.TEXT);
			items.add(inAddExpenseCategoryName);
			CategoryButton btnAddExpenseCategory = new CategoryButton("Ok");
			is = new MyStage(items);
			items.add(btnAddExpenseCategory);
			btnAddExpenseCategory.setOnAction(e1 -> {
				is.close();
				String name = inAddExpenseCategoryName.getValue();
				// add the amount
				if (!(name.equals(""))) {
					for (ReportsUIListener l : listeners) {
						l.addExpenseCategoryFromView(name);
					}
				} else {
					// show error message, ask for details
				}
			});
			btnAddExpenseCategory.setPrefWidth(60);
			is.addItem(btnAddExpenseCategory);
		});

		hbExpensesCategories.getChildren().addAll(btnAddExpense);
		vbExpenses.getChildren().addAll(lblExpenses, hbExpensesCategories);

		vbCategories.getChildren().addAll(lblCategories, vbIncomes, vbExpenses);

		// Overview
		vbOverview = new CenterVBox();
		vbOverview.getChildren().addAll(new MyLabel("Overview", LabelType.HEADER));

		// left
		Map<String, ImageView> buttons = new LinkedHashMap<>();
		buttons.put("Dashboard", new ImageView(new Image("file:assets/dashboard.png")));
		buttons.put("Categories", new ImageView(new Image("file:assets/categories.png")));
		buttons.put("Overview", new ImageView(new Image("file:assets/overview.png")));

		SideBar sideBar = new SideBar(5, "Reports App", buttons);
		sideBar.getButtons().get(0).setOnAction(e -> switchCenterTo(vbDashboard));
		sideBar.getButtons().get(1).setOnAction(e -> switchCenterTo(vbCategories));
		sideBar.getButtons().get(2).setOnAction(e -> switchCenterTo(vbOverview));

		// set root
		root.setTop(hbCenter);
		root.setLeft(sideBar);
		root.setCenter(vbDashboard);
		Scene scene = new Scene(root, WIDTH, HEIGHT);

		scene.setFill(Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}

	private void switchCenterTo(VBox vb) {
		root.setCenter(vb);
	}

	@Override
	public void registerListener(ReportsUIListener listener) {
		this.listeners.add(listener);
	}

	public static HBox agentsPanel(final Stage primaryStage) {

		HBox thb = new HBox(2);
		thb.setPadding(new Insets(5));

		thb.setOnMousePressed(e -> {
			xOffset = e.getSceneX();
			yOffset = e.getSceneY();
		});
		thb.setOnMouseDragged(e -> {
			primaryStage.setX(e.getScreenX() - xOffset);
			primaryStage.setY(e.getScreenY() - yOffset);
		});

		return thb;
	}

	@Override
	public void showErrorMessage(String message) {
		MyLabel lblMessage = new MyLabel(message, LabelType.FIELD);
		lblMessage.setPadding(new Insets(20,0,20,0));
		ArrayList<Node> items = new ArrayList<>();
		items.add(lblMessage);
		
		is = new MyStage(items, 160);
		CategoryButton btn = new CategoryButton("Ok");
		btn.setOnAction(e -> {
			is.close();
		});
		is.addItem(btn);
		is.setWindowTitle("Error");
	}
	
	
	

	@Override
	public void addMonthlyIncomeToView(MonthlyIncome income) {
		this.incomes.add(income);
		CategoryButton btnIncome = new CategoryButton(income.getName());
		btnIncome.setOnAction(e -> {
			ArrayList<Node> items = new ArrayList<>();
			MyLabel lblIncomeName = new MyLabel(income.getName() + "'s Income:", LabelType.SUB_HEADER);
			items.add(lblIncomeName);
			MyLabel lblIncomeAmount = new MyLabel(income.getAmount() + "[nis]", LabelType.SUB_HEADER);
			items.add(lblIncomeAmount);
			MyInputField newAmount = new MyInputField("New amount", InputType.NUMBER);
			items.add(newAmount);
			is = new MyStage(items, 250);
			CategoryButton btnUpdate = new CategoryButton("Update");
			btnUpdate.setOnAction(e1 -> {
				is.close();
				String amount = newAmount.getValue();
				if (!(amount.equals(""))) {
					for (ReportsUIListener l : listeners) {
						l.updateIncomeFromView(income.getName(), amount);
					}
				} else {
					// show error message
				}

			});
			is.addItem(btnUpdate);
		});
		hbIncomesCategories.getChildren().add(hbIncomesCategories.getChildren().size() - 1, btnIncome);
		updateIncomeGraph();
		updateTotalIncome();
		updateTotalSavings();
	}

	private void updateIncomeGraph() {
		bcIncomes.setData(getIncomeData());
	}

	private void updateTotalIncome() {
		int sum = 0;
		for (MonthlyIncome income : incomes) {
			sum += income.getAmount();
		}
		this.totalIncome.setText(sum + "");
	}

	private void updateTotalSavings() {
		int income = Integer.parseInt(this.totalIncome.getText());
		int expenses = Integer.parseInt(this.totalExpense.getText());
		this.totalSaving.setText((income - expenses) + "");
	}

	private void updateTotalExpenses() {
		int sum = 0;
		for (ExpenseCategory expenseCategory : expenses) {
			for (Entry<String, Double> entry : expenseCategory.getEntrySet()) {
				sum += entry.getValue();
			}
		}
		this.totalExpense.setText(sum + "");
	}

	@Override
	public void addExpenseCategoryToView(ExpenseCategory category) {
		this.expenses.add(category);
		CategoryButton btnCategory = new CategoryButton(category.getAreaName());
		ExpenseCenterVBox vbExpense = new ExpenseCenterVBox(category);
		this.expensesCenters.add(vbExpense);
		for (Entry<String, Double> entry : category.getEntrySet()) {
			vbExpense.addNode(new ExpenseField(entry.getKey(), entry.getValue()));
		}
		CategoryButton btnAddExpense = new CategoryButton("Add");
		btnAddExpense.setOnAction(e -> {
			ArrayList<Node> items = new ArrayList<>();
			MyLabel lblAddExpense = new MyLabel("Add an Expense", LabelType.SUB_HEADER);
			items.add(lblAddExpense);
			inAddExpenseName = new MyInputField("Insert name: ", InputType.TEXT);
			items.add(inAddExpenseName);
			inAddExpenseAmount = new MyInputField("Insert amount: ", InputType.NUMBER);
			items.add(inAddExpenseAmount);
			Button btnAdd = new Button("Ok");
			is = new MyStage(items);
			items.add(btnAdd);
			btnAdd.setOnAction(e1 -> {
				is.close();
				String name = inAddExpenseName.getValue();
				String amount = inAddExpenseAmount.getValue();
				// add the amount
				if (!(name.equals("") || amount.equals(""))) {
					for (ReportsUIListener l : listeners) {
						l.addExpenseFromView(category.getAreaName(), name, amount);
					}
				} else {
					// show error message, ask for details
				}
			});
			is.addItem(btnAdd);
		});
		vbExpense.addNode(btnAddExpense);
		btnCategory.setOnAction(e -> switchCenterTo(vbExpense));
		hbExpensesCategories.getChildren().add(hbExpensesCategories.getChildren().size() - 1, btnCategory);

	}

	@Override
	public void addExpenseToView(ExpenseCategory category, Entry<String, Double> entry) {
		int index = this.expenses.indexOf(category);
		ExpenseCenterVBox vbExpense = this.expensesCenters.get(index);
		ExpenseField field = new ExpenseField(entry.getKey(), entry.getValue());
		field.getDeleteButton().setOnAction(e -> {
			for (ReportsUIListener l : listeners) {
				l.removeExpenseFromView(category.getAreaName(), entry.getKey());
			}
		});
		vbExpense.addNodeBeforeLast(field);
		updateTotalExpenses();
		updateTotalSavings();
	}

	@Override
	public void removeExpenseInView(ExpenseCategory category, String expenseName) {
		int index = this.expenses.indexOf(category);
		ExpenseCenterVBox vbExpense = this.expensesCenters.get(index);
		int i = 0;
		for (Node n : vbExpense.getChildren()) {
			if (i == 0 || i == vbExpense.getChildren().size()) {
				i++;
				continue;
			}
			ExpenseField f = (ExpenseField) n;
			if (f.getLblName().getText().equals(expenseName)) {
				vbExpense.getChildren().remove(f);
				return;
			}
			i++;
		}
	}

	// mapping data
	private Map<String, Number> getExpensesData() {
		Map<String, Number> expensesMap = new HashMap<String, Number>();
		for (ExpenseCategory expense : this.expenses) {
			for (Entry<String, Double> entry : expense.getEntrySet()) {
				if (expensesMap.containsKey(entry.getKey())) {
					expensesMap.put(entry.getKey(), (double) expensesMap.get(entry.getKey()) + entry.getValue());
				} else {
					expensesMap.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return expensesMap;
	}

	private Map<String, Number> getExpenseCategoryData() {
		Map<String, Number> expenseCategoryMap = new HashMap<String, Number>();
		for (ExpenseCategory expense : this.expenses) {
			expenseCategoryMap.put(expense.getAreaName(), expense.getTotal());
		}
		return expenseCategoryMap;
	}

	private Map<String, Number> getIncomeData() {
		Map<String, Number> incomeMap = new HashMap<String, Number>();
		for (MonthlyIncome income : this.incomes) {
			if (incomeMap.containsKey(income.getName())) {
				incomeMap.put(income.getName(), (double) incomeMap.get(income.getName()) + income.getAmount());
			} else {
				incomeMap.put(income.getName(), income.getAmount());
			}
		}
		return incomeMap;
	}

	private void reddit(Node node) {
		node.setStyle("-fx-background-color: red");
	}

	@Override
	public void updateIncomeInView(MonthlyIncome income) {
		updateTotalIncome();
		updateTotalSavings();
	}

}
