package model;

import java.io.Serializable;

public class MonthlyIncome  implements Serializable{

	private String name;
	private int amount;
	// private char currency;

	public MonthlyIncome(String name, int amount) {
		super();
		this.setName(name);
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Monthly Income: "+name+", amount of " + amount +"[nis]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
