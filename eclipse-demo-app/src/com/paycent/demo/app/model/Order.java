package com.paycent.demo.app.model;

public class Order {

	private String iconName;

	private String title;

	private String number;

	private String amount;

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Order{" +
				"iconName='" + iconName + '\'' +
				", title='" + title + '\'' +
				", number='" + number + '\'' +
				", amount='" + amount + '\'' +
				'}';
	}

}
