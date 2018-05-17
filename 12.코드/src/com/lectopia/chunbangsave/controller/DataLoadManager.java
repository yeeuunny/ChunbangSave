package com.lectopia.chunbangsave.controller;

import java.util.ArrayList;
import java.util.HashMap;

import moduleTest.CashBookDAO;
import moduleTest.CashBookDTO;
import moduleTest.CashBookUniqueDAO;
import moduleTest.CashBookUniqueDTO;
import moduleTest.DiaryDAO;
import moduleTest.DiaryDTO;
import moduleTest.FileNameEdit;
import moduleTest.MemoDAO;
import moduleTest.PocketMoneyDAO;
import moduleTest.PocketMoneyDTO;
import moduleTest.SavingGoalDAO;
import moduleTest.SavingGoalDTO;
import moduleTest.YearlyTotalDAO;

import com.lectopia.chunbangsave.view.RegisterCategory;
import com.lectopia.chunbangsave.vo.CashBookDayVO;
import com.lectopia.chunbangsave.vo.CashBookDetailVO;
import com.lectopia.chunbangsave.vo.DiaryVO;
import com.lectopia.chunbangsave.vo.MemoVO;
import com.lectopia.chunbangsave.vo.MonthCashBookDetailVO;
import com.lectopia.chunbangsave.vo.MonthCashBookVO;
import com.lectopia.chunbangsave.vo.MonthDiaryVO;
import com.lectopia.chunbangsave.vo.MonthMemoVO;
import com.lectopia.chunbangsave.vo.MonthPocketMoneyVO;
import com.lectopia.chunbangsave.vo.PocketMoneyVO;
import com.lectopia.chunbangsave.vo.SavingGoalVO;
import com.lectopia.chunbangsave.vo.Weather;
/**
 * 
 * @author ����
 *	DAO Load Model Manager ( Controller )
 */
public class DataLoadManager {
	/**
	 * ���� ����� ����
	 */
	private CashBookUniqueDAO cashBookUniqueList;
	/**
	 * ����� ����
	 */
	private CashBookDAO cashBookList;
	/**
	 * �޸� ����
	 */
	private MemoDAO memoList;
	/**
	 * ������ ���
	 */
	private YearlyTotalDAO yearlyList;
	/**
	 * �ϱ� ����
	 */
	private DiaryDAO diaryLIst;
	/**
	 * �뵷 ��û ����
	 */
	private PocketMoneyDAO pocketList;
	/**
	 * ���� ��ǥ ����
	 */
	private SavingGoalDAO goalInfo;
	public DataLoadManager(){
		this.cashBookList = new CashBookDAO();
		this.cashBookUniqueList = new CashBookUniqueDAO();
		this.memoList = new MemoDAO();
		this.yearlyList = new YearlyTotalDAO();
		this.diaryLIst = new DiaryDAO();
		this.pocketList = new PocketMoneyDAO();
		this.goalInfo = new SavingGoalDAO();
	}
	public CashBookUniqueDAO getCashBookUniqueList() {
		return cashBookUniqueList;
	}
	public void setCashBookUniqueList(CashBookUniqueDAO cashBookUniqueList) {
		this.cashBookUniqueList = cashBookUniqueList;
	}
	public CashBookDAO getCashBookList() {
		return cashBookList;
	}
	public void setCashBookList(CashBookDAO cashBookList) {
		this.cashBookList = cashBookList;
	}
	public MemoDAO getMemoList() {
		return memoList;
	}
	public void setMemoList(MemoDAO memoList) {
		this.memoList = memoList;
	}
	public YearlyTotalDAO getYearlyList() {
		return yearlyList;
	}
	public void setYearlyList(YearlyTotalDAO yearlyList) {
		this.yearlyList = yearlyList;
	}
	public DiaryDAO getDiaryLIst() {
		return diaryLIst;
	}
	public void setDiaryLIst(DiaryDAO diaryLIst) {
		this.diaryLIst = diaryLIst;
	}
	public PocketMoneyDAO getPocketList() {
		return pocketList;
	}
	public void setPocketList(PocketMoneyDAO pocketList) {
		this.pocketList = pocketList;
	}
	public SavingGoalDAO getGoalInfo() {
		return goalInfo;
	}
	public void setGoalInfo(SavingGoalDAO goalInfo) {
		this.goalInfo = goalInfo;
	}
	
	/**
	 * 
	 * @param familyCode ���� �ڵ�
	 * @param registerCode ��� �ڵ�
	 * @param month �������� 3��������
	 * @return 3���� MonthCashBookDetailVO
	 */
	public ArrayList<MonthCashBookDetailVO> loadThreeMonthCB(String familyCode,String registerCode,int[]month){
		MonthCashBookDetailVO viewList = null;
		ArrayList<MonthCashBookDetailVO> threeMonthList = new ArrayList<MonthCashBookDetailVO>();
		ArrayList<CashBookDTO> fileList = null;
		/*
		 * ���� �� ��ŭ �ݺ�
		 */
		for(int i = 0 ; i < month.length;++i){
			/*
			 * ���Ϸκ��� �� ���� ���� �о�´�. ���ϸ� ���� �־� �־�� �Ѵ�.
			 */
			fileList = this.cashBookList.loadCashBookFile("cash5.txt", registerCode, month[i]);
			//System.out.println("month"+i+"="+month[i]+", fileList.size"+fileList.size());
			/*
			 * ���ο� ���� MonthCashBookDetailVO ����
			 */
			viewList = new MonthCashBookDetailVO();
			if(fileList.size() > 0){
				for(int j = 0; j < fileList.size();++j){
					/*
					 * ���Ϸ� ���� �о�� ��,��,��,��,��,�� ���� 
					 */
					int [] registerTime = {Integer.parseInt(fileList.get(j).getRegisterDate()[0]),//��
											Integer.parseInt(fileList.get(j).getRegisterDate()[1]),//��
												Integer.parseInt(fileList.get(j).getRegisterDate()[2]),//��
													Integer.parseInt(fileList.get(j).getRegisterDate()[3]),//��
													 	Integer.parseInt(fileList.get(j).getRegisterDate()[4]),//��
													 		Integer.parseInt(fileList.get(j).getRegisterDate()[5])//��
													 			};
					/*
					 * key - 3��° index�� ������
					 * value - CashBookDetailVOŸ��
					 */
					RegisterCategory putCategory = RegisterCategory.IMPORT;
					if(fileList.get(j).getRegisterCategory()==1)putCategory = RegisterCategory.IMPORT;
					else if(fileList.get(j).getRegisterCategory()==2)putCategory = RegisterCategory.EXPORT;
					else if(fileList.get(j).getRegisterCategory()==3)putCategory = RegisterCategory.SAVE;
					else if(fileList.get(j).getRegisterCategory()==4)putCategory = RegisterCategory.DONATION;
					viewList.put(registerTime[2],
							new CashBookDetailVO(registerTime, 
									putCategory, 
										fileList.get(j).getDetailCategory(), 
											Integer.toString(fileList.get(j).getAmount()), 
												fileList.get(j).getContent()));
				}
			}
			/*
			 * �������ڷ� ���� month�� �� ������� �;��Ѵ�.
			 */
			threeMonthList.add(viewList);
		}
		return threeMonthList;
	}
	/**
	 * 
	 * @param familyCode �����ڵ�
	 * @param registerCode ����ڵ�
	 * @param month �������� 3��������
	 * @return 3���� MonthCashBookVO
	 */
	public ArrayList<MonthCashBookVO> loadThreeMonthCBUnique(String familyCode,String registerCode,int []month){
		ArrayList<MonthCashBookVO> threeMonthList = new ArrayList<MonthCashBookVO>();
		MonthCashBookVO viewList = null;
		ArrayList<CashBookUniqueDTO> fileList = null;
		/*
		 * ���� �� ��ŭ �ݺ�
		 */
		for(int i = 0; i < month.length; ++i){
			/*
			 * ���Ϸκ��� �� ���� ���� �о�´�. ���ϸ� ���� �־� �־�� �Ѵ�.
			 */
			fileList = this.cashBookUniqueList.loadCashBookUniqueFile(
					FileNameEdit.makeName(familyCode, "U", registerCode, 16, month[i], 16, 1115), registerCode, month[i]);
			if(fileList == null)
				return null;
			
			if(fileList.size()>0){
				/*
				 * �� ���� ��ü ����
				 */
				viewList = new MonthCashBookVO();
				for(int j = 0 ; j < fileList.size(); ++j){
					/*
					 * ���Ϸ� ���� �о�� ��,��,��,��,��,�� ���� 
					 */
					int [] registerDate = {Integer.parseInt(fileList.get(j).getRegisterDate()[0]),//��
											Integer.parseInt(fileList.get(j).getRegisterDate()[1]),//��
												Integer.parseInt(fileList.get(j).getRegisterDate()[2]),//��
													};
					/*
					 * key - 3��° index�� ������
					 * value - CashBookDayVOŸ��
					 */
					viewList.put(registerDate[2], new CashBookDayVO(
													registerDate, // �����
														Integer.toString(fileList.get(j).getDayLimit()), // �����ѵ�
															Integer.toString(fileList.get(j).getIsCompliment()))//Ī���ϱ� ����
																	);
					System.out.println(viewList.get(registerDate[2]));
				}
				/*
				 * �������ڷ� ���� month�� �� ������� �;��Ѵ�.
				 */
				threeMonthList.add(viewList);
			}
		}
		return threeMonthList;		
	}
	/**
	 *  
	 * @param familyCode �����ڵ�
	 * @param registerCode ����ڵ�
	 * @param month ������
	 * @return 1���� �ϱ�����
	 */
	public MonthDiaryVO loadOneMonthDiary(String familyCode,String registerCode,int month){
		MonthDiaryVO monthList = new MonthDiaryVO();
		ArrayList<DiaryDTO>fileList = 
				/*
				 * ���Ϸκ��� �� ���� ���� �о�´�. ���ϸ� ���� �־� �־�� �Ѵ�.
				 */
				this.diaryLIst.loadDiaryFile("F0001_D_p1_1_11_10_1_5.txt", registerCode, month).get(month);
		/*
		 * �� �� ��ŭ �ݺ�
		 */
		for(int i = 0; i < fileList.size(); ++i){
			int registerDate[] = {Integer.parseInt(fileList.get(i).getRegisterDate()[0]),//�� 
									Integer.parseInt(fileList.get(i).getRegisterDate()[1]),//��
										Integer.parseInt(fileList.get(i).getRegisterDate()[2])//��
											};
			if(fileList.get(i).getWeather()==Weather.SUNNY)
				monthList.put(registerDate[2], new DiaryVO(registerDate, fileList.get(i).getDiaryTitle(),
								fileList.get(i).getDiaryContent(), Weather.SUNNY));
			else if(fileList.get(i).getWeather()==Weather.CLOUDY)
				monthList.put(registerDate[2], new DiaryVO(registerDate, fileList.get(i).getDiaryTitle(),
								fileList.get(i).getDiaryContent(), Weather.CLOUDY));
			else if(fileList.get(i).getWeather()==Weather.RAINY)
				monthList.put(registerDate[2], new DiaryVO(registerDate, fileList.get(i).getDiaryTitle(),
								fileList.get(i).getDiaryContent(), Weather.RAINY));
			else if(fileList.get(i).getWeather()==Weather.SNOWY)
				monthList.put(registerDate[2], new DiaryVO(registerDate, fileList.get(i).getDiaryTitle(),
								fileList.get(i).getDiaryContent(), Weather.SNOWY));
		}
		return monthList;
	}
	/**
	 * 3��ġ �޸����� ����� ��ȯ�ϴ� �޼ҵ�
	 * @param familyCode �����ڵ�
	 * @param registerCode ����ڵ�
	 * @param month ��
	 * @return 3�� �޸����� ���
	 */
	public ArrayList<MonthMemoVO> loadThreeMonthMemo(String familyCode, String registerCode,int[] month){//-----------------------
		ArrayList<MonthMemoVO> memo=new ArrayList<MonthMemoVO>();
		
		if(registerCode.equals("C1")){
		memoList.loadMemoFile("test111.txt", registerCode, month[0]);
		//System.out.println(memoList.get(registerCode,month[0]).get(0).getMemoTitle());
		//System.out.println("asdf");
		memoList.loadMemoFile("test222.txt", registerCode, month[1]);
		//System.out.println(memoList.get(registerCode,month[1]).get(0).getMemoTitle());
		memoList.loadMemoFile("test333.txt", registerCode, month[2]);//3��ġ�� �ش��ϴ� �ؽ�Ʈ �ε�
		//System.out.println(memoList.get(registerCode,month[2]).get(0).getMemoTitle());
		}
		else{
			memoList.loadMemoFile("test00.txt", registerCode, month[0]);
			//System.out.println(memoList.get(registerCode,month[0]).get(0).getMemoTitle());
			//System.out.println("asdf");
			memoList.loadMemoFile("test11.txt", registerCode, month[1]);
			//System.out.println(memoList.get(registerCode,month[1]).get(0).getMemoTitle());
			memoList.loadMemoFile("test22.txt", registerCode, month[2]);//3��ġ�� �ش��ϴ� �ؽ�Ʈ �ε�
		}
		//------------3��ġ vo�����ؼ� list�� �ִ� �ڵ�
		
		for(int i=0;i<3;++i){
			int size=memoList.get(registerCode, month[i]).size();
			MonthMemoVO memoVO=new MonthMemoVO();
			for(int j=0;j<size;++j){
				MemoVO vo=new MemoVO(memoList.get(registerCode, month[i]).get(j).getMemoTitle(),
						memoList.get(registerCode, month[i]).get(j).getMemoContent());
				memoVO.put(Integer.parseInt(memoList.get(registerCode,month[i]).get(j).getRegisterDate()[2])
				, vo);
				
			}
			memo.add(memoVO);
		}
		//System.out.println(memo);
		return memo;
	}
	
	/**
	 * �������ڷ� �Ѿ�� ���� �뵷���� ����� ��ȯ�ϴ� �޼ҵ�
	 * @param familyCode �����ڵ�
	 * @param registerCode ����ڵ�
	 * @param month �� 
	 * @return month�� �ش��ϴ� �뵷�������
	 */
	public MonthPocketMoneyVO loadOneMonthPocketMoney(String familyCode,String registerCode,int month){//---------------------
		if(registerCode.equals("C1"))
			pocketList.loadPocketMoneyFile("test31.txt", registerCode, month);
		else
			pocketList.loadPocketMoneyFile("test2.txt",registerCode,month);
		System.out.println("DataLoadManager- loadOneMonthPock"+pocketList.get("P1"));
		//System.out.println(pocketList.get(registerCode).get(month));
		//System.out.println(registerCode);
		//�Ѵ��� �뵷 ���� ����
		MonthPocketMoneyVO vo=new MonthPocketMoneyVO();
		
		int size=this.pocketList.get(registerCode, month).size();
		
		for(int i=0;i<size;++i){
			PocketMoneyDTO dto=this.pocketList.get(registerCode).get(month).get(i);
			String cat=dto.getCategory();
			Integer amount=dto.getAmount();
			Integer is=dto.getIsReceived();
			
			PocketMoneyVO pocket=new PocketMoneyVO(cat,amount.toString(),dto.getContent(),is.toString());

			vo.put(dto.getRegisterDate(),pocket);
		}
		//this.pocketList.put(registerCode, month, pocketMoney)
		//System.out.println(vo.get("10").getContent());
		return vo;
	}
	public SavingGoalVO loadSavingGoal(String familyCode, String registerCode){//-----------------------------------------------------
		//������ ��� vo�� �����ϴ� ������ ������
		//�ε� ����� �޴´�
		HashMap<String, SavingGoalDTO> goal=this.goalInfo.loadGoalFile("test5.txt");
		//System.out.println(goal+" "+goal.size());
		//�����ǥ ����Ʈ ��ȯ �ϱ����� ��� ����Ʈ
		SavingGoalDTO dto=goal.get(registerCode);
		return new SavingGoalVO(dto.getStartDate(),dto.getSavingGoalTitle(),
				Integer.parseInt(dto.getSavingGoalAmount()),Integer.parseInt(dto.getSuccessRate()));
		
	}
	
	/**
	 * ������ ��� �����ǥ ���� ����Ʈ�� ��ȯ�ϴ� �޼ҵ�
	 * @param familyCode �����ڵ�
	 * @param registerCode ����ڵ�
	 * @return �����ǥ��������Ʈ
	 */
	/*
	public QuarterSavingGoalVO loadSavingGoal(String familyCode, String[] registerCode){
		//������ ��� vo�� �����ϴ� ������ ������
		//�ε� ����� �޴´�
		HashMap<String, HashMap<Integer,SavingGoalDTO>> goal=this.goalInfo.loadGoalFile("test5.txt");
		System.out.println(goal+" "+goal.size());
		//�����ǥ ����Ʈ ��ȯ �ϱ����� ��� ����Ʈ
		ArrayList<QuarterSavingGoalVO> vos=new ArrayList<QuarterSavingGoalVO>();
		
		Set key = goal.keySet();
		Iterator iter=key.iterator();
		
		//��� ����Ʈ �� ���빰 ä��
		while(iter.hasNext()){                                                                      
			HashMap<Integer,SavingGoalDTO> dto=goal.get(iter.next()); 
			vos.add(new SavingGoalVO(dto.getStartDate(),
					dto.getSavingGoalTitle(),Integer.parseInt(dto.getSavingGoalAmount())
					,Integer.parseInt(dto.getSuccessRate() )));
		}
		
		return vos;
	}*/
}
