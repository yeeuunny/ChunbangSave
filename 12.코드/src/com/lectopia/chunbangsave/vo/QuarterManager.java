package com.lectopia.chunbangsave.vo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import com.lectopia.chunbangsave.controller.DataLoadManager;
import com.lectopia.chunbangsave.controller.DataSaveManager;
/**
 * 
 * @author ����
 *	- �б⺰ ������ ����
 */
public class QuarterManager {
	/**
	 * �б� ��ȣ
	 */
	private final QuarterNo key;
	/**
	 * Key - ����ڵ�
	 * Value - 3���� ����� ���� ���� ��� ( index : �ش� �б� �� ���� )
	 */
	private HashMap<String, ArrayList<MonthCashBookDetailVO>> detailInfoList;
	/**
	 * Key - ��� �ڵ�
	 * Value - 3���� ���� ����� ���� ( index : �ش� �б� �� ���� )
	 */
	private HashMap<String,ArrayList<MonthCashBookVO>> dayInfo;
	/**
	 * Key - ����ڵ�
	 * Value - 1���� �ϱ� ���� ( index : �ش� �б� �� ���� )
	 */
	private HashMap<String,MonthDiaryVO> diaryList;
	/**
	 * Key - ����ڵ�
	 * Value - �Ѹ��� ��� �б� ��ǥ ����
	 */
	private HashMap<String,QuarterSavingGoalVO> savingGoalList;
	/**
	 * Key - ����ڵ�
	 * Value - 3���� �޸� ���� ���� ���(index : �ش� ��¥ �� ����)
	 */
	private HashMap<String,ArrayList<MonthMemoVO>> memoList;
	/**
	 * Key - ����ڵ�
	 * Value -1���� �뵷 ���� ���(index : �ش� ��¥ ���� ) 
	 */
	private HashMap<String, MonthPocketMoneyVO> pocketMoneyList;
	/**
	 * ������ �ʱ�ȭ
	 */
	private static DataLoadManager dataLoadManager;
	/**
	 * ������ ����
	 */
	private static DataSaveManager dataSaveManager;
	/**
	 * ���� ���� �ܾ� 
	 */
	private static String currentAmount;
	/**
	 * 
	 * @param key �б��ȣ
	 */
	public QuarterManager(QuarterNo key){
		this.key = key;
		/*
		 * member field null�ʱ�ȭ
		 */
		this.detailInfoList = new HashMap<String, ArrayList<MonthCashBookDetailVO>>();
		this.dayInfo = new HashMap<String, ArrayList<MonthCashBookVO>>();
		this.diaryList = new HashMap<String, MonthDiaryVO>();
		QuarterManager.dataLoadManager = new DataLoadManager();
		QuarterManager.dataSaveManager = new DataSaveManager();
		this.savingGoalList = new HashMap<String, QuarterSavingGoalVO>();
		this.pocketMoneyList=new HashMap<String,MonthPocketMoneyVO>();		
		this.memoList=new HashMap<String,ArrayList<MonthMemoVO>>();
	
	}
	/**
	 * 
	 * @return �б��ȣ
	 */
	public QuarterNo getKey() {
		return key;
	}
	/**
	 * 
	 * @return 3���� ����� ���� ����
	 */
	public HashMap<String, ArrayList<MonthCashBookDetailVO>> getDetailInfoList() {
		return detailInfoList;
	}
	/**
	 * 
	 * @param detailInfoList ���ο� 3���� ����� ���� ����
	 */
	public void setDetailInfoList(
			HashMap<String, ArrayList<MonthCashBookDetailVO>> detailInfoList) {
		this.detailInfoList = detailInfoList;
	}
	/**
	 * 
	 * @return file load manager
	 */
	public static DataLoadManager getDataLoadManager() {
		return dataLoadManager;
	}
	/**
	 * 
	 * @param dataLoadManager ���ο� file load manager
	 */
	public static void setDataLoadManager(DataLoadManager dataLoadManager) {
		QuarterManager.dataLoadManager = dataLoadManager;
	}
	/**
	 * 
	 * @return 3���� ���� ����� ����
	 */
	public HashMap<String, ArrayList<MonthCashBookVO>> getDayInfo() {
		return dayInfo;
	}
	/**
	 * 
	 * @param dayInfo ���ο� ���� ����� ����
	 */
	public void setDayInfo(HashMap<String, ArrayList<MonthCashBookVO>> dayInfo) {
		this.dayInfo = dayInfo;
	}
	/**
	 * 
	 * @return 1�� �ϱ� ����
	 */
	public HashMap<String, MonthDiaryVO> getDiaryList() {
		return diaryList;
	}
	/**
	 * 
	 * @param diaryList ���ο� 1�� �ϱ� ����
	 */
	public void setDiaryList(HashMap<String, MonthDiaryVO> diaryList) {
		this.diaryList = diaryList;
	}
	/**
	 * 
	 * @param reigsterCode ��� �ڵ�
	 * @param detailInfoList 3���� ����� ���� ���� ���( index : �ش� �б� �� ���� )
	 * @return ���� �߰� ����
	 */
	public boolean putDetailInfoList(String reigsterCode,ArrayList<MonthCashBookDetailVO> detailInfoList){
		if(this.detailInfoList.get(reigsterCode)==null)
			this.detailInfoList.put(reigsterCode,new ArrayList<MonthCashBookDetailVO>());
		if(this.detailInfoList.put(reigsterCode, detailInfoList)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param registerCode ��� �ڵ�
	 * @param dayInfoList 3���� ����� ���� ���� ( index : �ش� �б� �� ���� )
	 * @return ���� �߰� ���� 
	 */
	public boolean putDayInfo(String registerCode, ArrayList<MonthCashBookVO> dayInfoList){
		if(this.dayInfo.get(registerCode)==null)
			this.dayInfo.put(registerCode, new ArrayList<MonthCashBookVO>());
		if(this.dayInfo.put(registerCode, dayInfoList)!=null)
			return true;
		return false;
	}
	/**
	 * 
	 * @param reigsterCode ����ڵ�
	 * @param month ���� ����
	 * @param diaryList 1�� �ϱ� ���� ���
	 * @return ���� �߰� ����
	 */
	public boolean putDiaryList(String reigsterCode, int month, MonthDiaryVO diaryList){
		if(this.diaryList.get(reigsterCode)==null)
			this.diaryList.put(reigsterCode, new MonthDiaryVO());
		if(this.diaryList.put(reigsterCode, diaryList)!=null)
			return true;
		return false;
	}
	public boolean putSavingGoalList(String registerCode,QuarterNo quarter,SavingGoalVO goal){
		if(this.savingGoalList.get(registerCode)==null)
			this.savingGoalList.put(registerCode, new QuarterSavingGoalVO());
		if(this.savingGoalList.get(registerCode).put(quarter.value(), goal)!=null){
			System.out.println(this.savingGoalList.get(registerCode).get(quarter.value()).getGoalTitle());
			System.out.println(this.savingGoalList.get(registerCode).get(quarter.value()).getSuccessRate());
			System.out.println(this.savingGoalList.get(registerCode).get(quarter.value()).getGoalAmount()+"��");
			return true;
		}
		return false;
	}
	/**
	 * �޸𸮽�Ʈ �Ѻб� ������ �������� �޼ҵ�
	 * @param registerCode ����ڵ�
	 * @param memoList 3���� �޸� ���� ���(index : �ش� �� ��¥)
	 * @return ���� �߰� ����
	 */
	public boolean putMemoList(String registerCode,ArrayList<MonthMemoVO> memoList){//--------------------------------
		if(this.memoList.get(registerCode)==null){
			ArrayList<MonthMemoVO> inData=new ArrayList<MonthMemoVO>();
			this.memoList.put(registerCode, inData);
		}
		if(this.memoList.put(registerCode, memoList)!=null){
			//System.out.println(this.memoList.get(registerCode).get(0).get(40).getMemoContent());
			return true;
		}
		return false;		
	}
	/**
	 * 
	 * @param registerCode ����ڵ�
	 * @param pocketMoneList 1�� �뵷 ����
	 * @return ���� �߰� ����
	 */
	public boolean putPocketMoneyList(String registerCode, MonthPocketMoneyVO pocketMoneList){//-------------------------
		if(this.pocketMoneyList.get(registerCode)==null){
			MonthPocketMoneyVO vo=new MonthPocketMoneyVO();
			this.pocketMoneyList.put(registerCode, vo);
		}
		if(this.pocketMoneyList.put(registerCode, pocketMoneList)!=null){
			System.out.println(this.pocketMoneyList.get(registerCode).get("12").getContent());
			return true;
		}
		
		return false;
	}
	public PocketMoneyVO getDailyPocketMoney(String registerCode,int day){
		Integer days=day;
		
		return this.pocketMoneyList.get(registerCode).get(days.toString());
	}
	public CategoryTotalVO[] calculateCategoryTotal(
			String registerCode, int month)
	{
		GregorianCalendar cal = new GregorianCalendar();
		
		int[] detailAmount = new int[11];
		int totAmount = 0; //�ۼ�Ʈ ��� ���� ���� 

		System.out.println("detailInfoList :" + detailInfoList);
		System.out.println("quarter : " + (int)(Math.ceil(month+1)/3.0));
	
		 MonthCashBookDetailVO monthDetailVO
				= this.detailInfoList.get(registerCode).get(month + 3 - 3*(int)Math.ceil((month+1)/3.0));

		 System.out.println("monthDetailVO: " + monthDetailVO);
		for(int i = 0; i < cal.getActualMaximum(month) - 1; i++)
		{
			if(monthDetailVO.getMonthCBDMap().get(i) != null)
			{
				for(int j = 0; j < monthDetailVO.get(i).size(); j++)
				{
					String category = monthDetailVO.get(i).get(j).getDetailCategory();
					int amount = Integer.parseInt(monthDetailVO.get(i).get(j).getAmount());
					System.out.println("amount:" + amount);
					
					if(category.equals("�����Ÿ"))
						detailAmount[0] += amount;
					else if(category.equals("�ĺ�") || category.equals("�п�ǰ"))
						detailAmount[1] += amount;
					else if(category.equals("����") || category.equals("����"))
						detailAmount[2] += amount;
					else if(category.equals("������") || category.equals("���"))
						detailAmount[3] += amount;
					else if(category.equals("����") || category.equals("����"))
						detailAmount[4] += amount;
					else if(category.equals("���Ա�Ÿ"))
						detailAmount[5] += amount;
					else if(category.equals("����") || category.equals("�뵷"))
						detailAmount[6] += amount;
					else if(category.equals("���ʽ�"))
						detailAmount[7] += amount;
					else if(category.equals("����"))
						detailAmount[8] += amount;
					else if(category.equals("����"))
						detailAmount[9] += amount;
					else if(category.equals("���"))
						detailAmount[10] += amount;
					
					totAmount += amount;
				}
			}
		}
		System.out.println("totAmount :" + totAmount);
		//�θ��� ��� 
		if(registerCode.charAt(0) == 'p' && totAmount != 0)
			return new CategoryTotalVO[] { 
				new CategoryTotalVO("����", "�����Ÿ", Integer.toString(detailAmount[0]), Integer.toString(detailAmount[0]*100/totAmount)),
					new CategoryTotalVO("����", "�ĺ�", Integer.toString(detailAmount[1]), Integer.toString(detailAmount[1]*100/totAmount)),
						new CategoryTotalVO("����", "����", Integer.toString(detailAmount[2]), Integer.toString(detailAmount[2]*100/totAmount)),
							new CategoryTotalVO("����", "������", Integer.toString(detailAmount[3]), Integer.toString(detailAmount[3]*100/totAmount)),
								new CategoryTotalVO("����", "����", Integer.toString(detailAmount[4]), Integer.toString(detailAmount[4]*100/totAmount)),
									new CategoryTotalVO("����", "���Ա�Ÿ", Integer.toString(detailAmount[5]), Integer.toString(detailAmount[5]*100/totAmount)),
										new CategoryTotalVO("����", "����", Integer.toString(detailAmount[6]), Integer.toString(detailAmount[6]*100/totAmount)),
											new CategoryTotalVO("����", "���ʽ�", Integer.toString(detailAmount[7]), Integer.toString(detailAmount[7]*100/totAmount)),
												new CategoryTotalVO("����	", "����", Integer.toString(detailAmount[8]), Integer.toString(detailAmount[8]*100/totAmount)),
													new CategoryTotalVO("����", "����", Integer.toString(detailAmount[9]), Integer.toString(detailAmount[9]*100/totAmount)),
														new CategoryTotalVO("���", "���", Integer.toString(detailAmount[10]), Integer.toString(detailAmount[10]*100/totAmount)) };
	
		else if(registerCode.charAt(0) == 'c' && totAmount != 0)
			return new CategoryTotalVO[] { 
				new CategoryTotalVO("����", "�����Ÿ", Integer.toString(detailAmount[0]), Integer.toString(detailAmount[0]*100/totAmount)),
					new CategoryTotalVO("����", "�п�ǰ", Integer.toString(detailAmount[1]), Integer.toString(detailAmount[1]*100/totAmount)),
						new CategoryTotalVO("����", "����", Integer.toString(detailAmount[2]), Integer.toString(detailAmount[2]*100/totAmount)),
							new CategoryTotalVO("����", "���", Integer.toString(detailAmount[3]), Integer.toString(detailAmount[3]*100/totAmount)),
								new CategoryTotalVO("����", "����", Integer.toString(detailAmount[4]), Integer.toString(detailAmount[4]*100/totAmount)),
									new CategoryTotalVO("����", "���Ա�Ÿ", Integer.toString(detailAmount[5]), Integer.toString(detailAmount[5]*100/totAmount)),
										new CategoryTotalVO("����", "�뵷", Integer.toString(detailAmount[6]), Integer.toString(detailAmount[6]*100/totAmount)),
													new CategoryTotalVO("����", "����", Integer.toString(detailAmount[9]), Integer.toString(detailAmount[9]*100/totAmount)),
														new CategoryTotalVO("���", "���", Integer.toString(detailAmount[10]), Integer.toString(detailAmount[10]*100/totAmount)) };
		else
			return null;
}
	/*
	public ArrayList<CashBookDetailVO> getDailyDetailInfoList(String registerCode, int month, int day){
		//return this.detailInfoList.get(registerCode).g
				
	}*/
	public HashMap<String, QuarterSavingGoalVO> getSavingGoalList() {
		return savingGoalList;
	}
	public void setSavingGoalList(
			HashMap<String, QuarterSavingGoalVO> savingGoalList) {
		this.savingGoalList = savingGoalList;
	}
	public static String getCurrentAmount() {
		return currentAmount;
	}
	public static void setCurrentAmount(String currentAmount) {
		QuarterManager.currentAmount = currentAmount;
	}
	public HashMap<String, ArrayList<MonthMemoVO>> getMemoList() {
		return memoList;
	}
	public void setMemoList(HashMap<String, ArrayList<MonthMemoVO>> memoList) {
		this.memoList = memoList;
	}
	public HashMap<String, MonthPocketMoneyVO> getPocketMoneyList() {
		return pocketMoneyList;
	}
	public void setPocketMoneyList(
			HashMap<String, MonthPocketMoneyVO> pocketMoneyList) {
		this.pocketMoneyList = pocketMoneyList;
	}
	/**
	 * 
	 * @return file save manager
	 */
	public static DataSaveManager getDataSaveManager() {
		return dataSaveManager;
	}
	/**
	 * 
	 * @param dataSaveManager ���ο� file save manager
	 */
	public static void setDataSaveManager(DataSaveManager dataSaveManager) {
		QuarterManager.dataSaveManager = dataSaveManager;
	}
	
}
