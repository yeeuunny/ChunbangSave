package com.lectopia.chunbangsave.vo;

import java.util.Arrays;

public class SavingGoalVO {
	private String[] startDate;
	private String goalTitle;
	private int goalAmount;
	private int successRate;
	public SavingGoalVO() {
		super();
	}
	public SavingGoalVO(String[] startDate, String goalTitle, int goalAmount,
			int successRate) {
		super();
		this.startDate = startDate;
		this.goalTitle = goalTitle;
		this.goalAmount = goalAmount;
		this.successRate = successRate;
	}
	public String[] getStartDate() {
		return startDate;
	}
	public void setStartDate(String[] startDate) {
		this.startDate = startDate;
	}
	public String getGoalTitle() {
		return goalTitle;
	}
	public void setGoalTitle(String goalTitle) {
		this.goalTitle = goalTitle;
	}
	public int getGoalAmount() {
		return goalAmount;
	}
	public void setGoalAmount(int goalAmount) {
		this.goalAmount = goalAmount;
	}
	public int getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(int successRate) {
		this.successRate = successRate;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SavingGoalVO other = (SavingGoalVO) obj;
		if (goalAmount != other.goalAmount)
			return false;
		if (goalTitle == null) {
			if (other.goalTitle != null)
				return false;
		} else if (!goalTitle.equals(other.goalTitle))
			return false;
		if (!Arrays.equals(startDate, other.startDate))
			return false;
		if (successRate != other.successRate)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SavingGoalVO [startDate=" + Arrays.toString(startDate)
				+ ", goalTitle=" + goalTitle + ", goalAmount=" + goalAmount
				+ ", successRate=" + successRate + "]";
	}
	
}

