package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ExpenseCategory  implements Serializable{

	private String areaName;
	private Map<String, Double> expenses;
	
	public ExpenseCategory(String areaName) {
		super();
		this.areaName = areaName;
		this.expenses = new HashMap<String, Double>();
	}
	public String getAreaName() {
		return areaName;
	}
	public Map<String, Double> getExpenses() {
		return expenses;
	}
	
	public void addExpense(String expense) {
		this.expenses.put(expense, 0.0);
	}
	
	public void addExpense(String expense, Double amount) {
		if ( this.expenses.containsKey(expense)) {
			this.expenses.put(expense, this.expenses.get(expense)+amount);
		}else {
			this.expenses.put(expense, amount);
		}
	}
	
	public void removeFromExpense(String expense, Double amount) {
		this.addExpense(expense, -amount);
	}
	
	public void removeFromExpense(String expense) {
		this.expenses.remove(expense);
	}
	
	public Set<Entry<String, Double>> getEntrySet(){
		return this.expenses.entrySet();
	}
	
	public Entry<String, Double> getExpenseByName(String name){
		for (Entry<String, Double> entry : this.expenses.entrySet()) {
			if ( entry.getKey().equals(name))
				return entry;
		}
		return null;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Expense Category: " + areaName + "\n");
		for (Entry<String, Double> entry : this.expenses.entrySet()) {
			sb.append(entry.getKey() + ": "+entry.getValue()+"\n");
		}
		return  sb.toString();
	}
	public double getTotal() {
		double sum = 0;
		for (Entry<String, Double> entry : this.getEntrySet()) {
			sum += entry.getValue();
		}
		return sum;
	}
	
	
}
