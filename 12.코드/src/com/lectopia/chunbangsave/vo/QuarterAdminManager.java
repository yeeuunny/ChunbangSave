package com.lectopia.chunbangsave.vo;

public class QuarterAdminManager {
	/**
	 * 4�б� QuarterManager 
	 */
	private static QuarterManager[] totManager;

	public QuarterAdminManager() {
		super();
		/*
		 * 4�б� ����
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
