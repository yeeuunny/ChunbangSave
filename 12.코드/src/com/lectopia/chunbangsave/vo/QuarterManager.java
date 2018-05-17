package com.lectopia.chunbangsave.vo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import com.lectopia.chunbangsave.controller.DataLoadManager;
import com.lectopia.chunbangsave.controller.DataSaveManager;
/**
 * 
 * @author 동익
 *	- 분기별 데이터 정보
 */
public class QuarterManager {
	/**
	 * 분기 번호
	 */
	private final QuarterNo key;
	/**
	 * Key - 등록코드
	 * Value - 3개월 가계부 내역 정보 목록 ( index : 해당 분기 월 순서 )
	 */
	private HashMap<String, ArrayList<MonthCashBookDetailVO>> detailInfoList;
	/**
	 * Key - 등록 코드
	 * Value - 3개월 일일 가계부 정보 ( index : 해당 분기 월 순서 )
	 */
	private HashMap<String,ArrayList<MonthCashBookVO>> dayInfo;
	/**
	 * Key - 등록코드
	 * Value - 1개월 일기 정보 ( index : 해당 분기 월 순서 )
	 */
	private HashMap<String,MonthDiaryVO> diaryList;
	/**
	 * Key - 등록코드
	 * Value - 한명의 모든 분기 목표 정보
	 */
	private HashMap<String,QuarterSavingGoalVO> savingGoalList;
	/**
	 * Key - 등록코드
	 * Value - 3개월 메모 내역 정보 목록(index : 해당 날짜 월 순서)
	 */
	private HashMap<String,ArrayList<MonthMemoVO>> memoList;
	/**
	 * Key - 등록코드
	 * Value -1개월 용돈 정보 목록(index : 해당 날짜 순서 ) 
	 */
	private HashMap<String, MonthPocketMoneyVO> pocketMoneyList;
	/**
	 * 데이터 초기화
	 */
	private static DataLoadManager dataLoadManager;
	/**
	 * 데이터 저장
	 */
	private static DataSaveManager dataSaveManager;
	/**
	 * 현재 보유 잔액 
	 */
	private static String currentAmount;
	/**
	 * 
	 * @param key 분기번호
	 */
	public QuarterManager(QuarterNo key){
		this.key = key;
		/*
		 * member field null초기화
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
	 * @return 분기번호
	 */
	public QuarterNo getKey() {
		return key;
	}
	/**
	 * 
	 * @return 3개월 가계부 내역 정보
	 */
	public HashMap<String, ArrayList<MonthCashBookDetailVO>> getDetailInfoList() {
		return detailInfoList;
	}
	/**
	 * 
	 * @param detailInfoList 새로운 3개월 가계부 내역 정보
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
	 * @param dataLoadManager 새로운 file load manager
	 */
	public static void setDataLoadManager(DataLoadManager dataLoadManager) {
		QuarterManager.dataLoadManager = dataLoadManager;
	}
	/**
	 * 
	 * @return 3개월 일일 가계부 정보
	 */
	public HashMap<String, ArrayList<MonthCashBookVO>> getDayInfo() {
		return dayInfo;
	}
	/**
	 * 
	 * @param dayInfo 새로운 일일 가계부 정보
	 */
	public void setDayInfo(HashMap<String, ArrayList<MonthCashBookVO>> dayInfo) {
		this.dayInfo = dayInfo;
	}
	/**
	 * 
	 * @return 1달 일기 정보
	 */
	public HashMap<String, MonthDiaryVO> getDiaryList() {
		return diaryList;
	}
	/**
	 * 
	 * @param diaryList 새로운 1달 일기 정보
	 */
	public void setDiaryList(HashMap<String, MonthDiaryVO> diaryList) {
		this.diaryList = diaryList;
	}
	/**
	 * 
	 * @param reigsterCode 등록 코드
	 * @param detailInfoList 3개월 가계부 내역 정보 목록( index : 해당 분기 월 순서 )
	 * @return 정상 추가 여부
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
	 * @param registerCode 등록 코드
	 * @param dayInfoList 3개월 가계부 일일 정보 ( index : 해당 분기 월 순서 )
	 * @return 정상 추가 여부 
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
	 * @param reigsterCode 등록코드
	 * @param month 개월 정보
	 * @param diaryList 1달 일기 정보 목록
	 * @return 정상 추가 여부
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
			System.out.println(this.savingGoalList.get(registerCode).get(quarter.value()).getGoalAmount()+"원");
			return true;
		}
		return false;
	}
	/**
	 * 메모리스트 한분기 데이터 가져오는 메소드
	 * @param registerCode 등록코드
	 * @param memoList 3개월 메모 정보 목록(index : 해당 월 날짜)
	 * @return 정상 추가 여부
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
	 * @param registerCode 등록코드
	 * @param pocketMoneList 1달 용돈 정보
	 * @return 정상 추가 여부
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
		int totAmount = 0; //퍼센트 계산 위한 변수 

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
					
					if(category.equals("지출기타"))
						detailAmount[0] += amount;
					else if(category.equals("식비") || category.equals("학용품"))
						detailAmount[1] += amount;
					else if(category.equals("보험") || category.equals("간식"))
						detailAmount[2] += amount;
					else if(category.equals("경조사") || category.equals("취미"))
						detailAmount[3] += amount;
					else if(category.equals("세금") || category.equals("오락"))
						detailAmount[4] += amount;
					else if(category.equals("수입기타"))
						detailAmount[5] += amount;
					else if(category.equals("월급") || category.equals("용돈"))
						detailAmount[6] += amount;
					else if(category.equals("보너스"))
						detailAmount[7] += amount;
					else if(category.equals("매출"))
						detailAmount[8] += amount;
					else if(category.equals("저축"))
						detailAmount[9] += amount;
					else if(category.equals("기부"))
						detailAmount[10] += amount;
					
					totAmount += amount;
				}
			}
		}
		System.out.println("totAmount :" + totAmount);
		//부모일 경우 
		if(registerCode.charAt(0) == 'p' && totAmount != 0)
			return new CategoryTotalVO[] { 
				new CategoryTotalVO("지출", "지출기타", Integer.toString(detailAmount[0]), Integer.toString(detailAmount[0]*100/totAmount)),
					new CategoryTotalVO("지출", "식비", Integer.toString(detailAmount[1]), Integer.toString(detailAmount[1]*100/totAmount)),
						new CategoryTotalVO("지출", "보험", Integer.toString(detailAmount[2]), Integer.toString(detailAmount[2]*100/totAmount)),
							new CategoryTotalVO("지출", "경조사", Integer.toString(detailAmount[3]), Integer.toString(detailAmount[3]*100/totAmount)),
								new CategoryTotalVO("지출", "세금", Integer.toString(detailAmount[4]), Integer.toString(detailAmount[4]*100/totAmount)),
									new CategoryTotalVO("수입", "수입기타", Integer.toString(detailAmount[5]), Integer.toString(detailAmount[5]*100/totAmount)),
										new CategoryTotalVO("수입", "월급", Integer.toString(detailAmount[6]), Integer.toString(detailAmount[6]*100/totAmount)),
											new CategoryTotalVO("수입", "보너스", Integer.toString(detailAmount[7]), Integer.toString(detailAmount[7]*100/totAmount)),
												new CategoryTotalVO("수입	", "매출", Integer.toString(detailAmount[8]), Integer.toString(detailAmount[8]*100/totAmount)),
													new CategoryTotalVO("저축", "저축", Integer.toString(detailAmount[9]), Integer.toString(detailAmount[9]*100/totAmount)),
														new CategoryTotalVO("기부", "기부", Integer.toString(detailAmount[10]), Integer.toString(detailAmount[10]*100/totAmount)) };
	
		else if(registerCode.charAt(0) == 'c' && totAmount != 0)
			return new CategoryTotalVO[] { 
				new CategoryTotalVO("지출", "지출기타", Integer.toString(detailAmount[0]), Integer.toString(detailAmount[0]*100/totAmount)),
					new CategoryTotalVO("지출", "학용품", Integer.toString(detailAmount[1]), Integer.toString(detailAmount[1]*100/totAmount)),
						new CategoryTotalVO("지출", "간식", Integer.toString(detailAmount[2]), Integer.toString(detailAmount[2]*100/totAmount)),
							new CategoryTotalVO("지출", "취미", Integer.toString(detailAmount[3]), Integer.toString(detailAmount[3]*100/totAmount)),
								new CategoryTotalVO("지출", "오락", Integer.toString(detailAmount[4]), Integer.toString(detailAmount[4]*100/totAmount)),
									new CategoryTotalVO("수입", "수입기타", Integer.toString(detailAmount[5]), Integer.toString(detailAmount[5]*100/totAmount)),
										new CategoryTotalVO("수입", "용돈", Integer.toString(detailAmount[6]), Integer.toString(detailAmount[6]*100/totAmount)),
													new CategoryTotalVO("저축", "저축", Integer.toString(detailAmount[9]), Integer.toString(detailAmount[9]*100/totAmount)),
														new CategoryTotalVO("기부", "기부", Integer.toString(detailAmount[10]), Integer.toString(detailAmount[10]*100/totAmount)) };
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
	 * @param dataSaveManager 새로운 file save manager
	 */
	public static void setDataSaveManager(DataSaveManager dataSaveManager) {
		QuarterManager.dataSaveManager = dataSaveManager;
	}
	
}
