package com.lectopia.chunbangsave.vo;

public class QuarterAdminManager {
	/**
	 * 4분기 QuarterManager 
	 */
	private static QuarterManager[] totManager;

	public QuarterAdminManager() {
		super();
		/*
		 * 4분기 생성
		 */
		if(totManager==null)
			this.totManager = new QuarterManager[4];
	}

	public static QuarterManager[] getTotManager() {
		return totManager;
	}

	public static void setTotManager(QuarterManager[] totManager) {
		QuarterAdminManager.totManager = totManager;
	}

	
}
