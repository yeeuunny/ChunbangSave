package com.lectopia.chunbangsave.vo;

import java.util.HashMap;

public class QuarterSavingGoalVO {
	/**
	 * Key - 분기 정보
	 * Value - 저축 목표 정보
	 */
	private HashMap<Integer,SavingGoalVO> goalInfos;
	
	public QuarterSavingGoalVO() {
		super();
		this.goalInfos = new HashMap<Integer, SavingGoalVO>();
	}

	public QuarterSavingGoalVO(HashMap<Integer, SavingGoalVO> goalInfos) {
		super();
		this.goalInfos = goalInfos;
	}

	public HashMap<Integer, SavingGoalVO> getGoalInfos() {
		return goalInfos;
	}

	public void setGoalInfos(HashMap<Integer, SavingGoalVO> goalInfos) {
		this.goalInfos = goalInfos;
	}
	
	public SavingGoalVO put(Integer quarter, SavingGoalVO addData){
		return this.goalInfos.put(quarter, addData);
	}
	public SavingGoalVO get(Integer quarter){
		return this.goalInfos.get(quarter);
	}
}
