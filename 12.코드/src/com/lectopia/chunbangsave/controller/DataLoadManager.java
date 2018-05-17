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
 * @author 동익
 *	DAO Load Model Manager ( Controller )
 */
public class DataLoadManager {
	/**
	 * 일일 가계부 정보
	 */
	private CashBookUniqueDAO cashBookUniqueList;
	/**
	 * 가계부 내역
	 */
	private CashBookDAO cashBookList;
	/**
	 * 메모 정보
	 */
	private MemoDAO memoList;
	/**
	 * 연도별 결산
	 */
	private YearlyTotalDAO yearlyList;
	/**
	 * 일기 정보
	 */
	private DiaryDAO diaryLIst;
	/**
	 * 용돈 요청 정보
	 */
	private PocketMoneyDAO pocketList;
	/**
	 * 저축 목표 정보
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
	 * @param familyCode 가족 코드
	 * @param registerCode 등록 코드
	 * @param month 오름차순 3개월정보
	 * @return 3개월 MonthCashBookDetailVO
	 */
	public ArrayList<MonthCashBookDetailVO> loadThreeMonthCB(String familyCode,String registerCode,int[]month){
		MonthCashBookDetailVO viewList = null;
		ArrayList<MonthCashBookDetailVO> threeMonthList = new ArrayList<MonthCashBookDetailVO>();
		ArrayList<CashBookDTO> fileList = null;
		/*
		 * 개월 수 만큼 반복
		 */
		for(int i = 0 ; i < month.length;++i){
			/*
			 * 파일로부터 한 달의 정보 읽어온다. 파일명 만들어서 넣어 주어야 한다.
			 */
			fileList = this.cashBookList.loadCashBookFile("cash5.txt", registerCode, month[i]);
			//System.out.println("month"+i+"="+month[i]+", fileList.size"+fileList.size());
			/*
			 * 새로운 월의 MonthCashBookDetailVO 생성
			 */
			viewList = new MonthCashBookDetailVO();
			if(fileList.size() > 0){
				for(int j = 0; j < fileList.size();++j){
					/*
					 * 파일로 부터 읽어온 년,월,일,시,분,초 정보 
					 */
					int [] registerTime = {Integer.parseInt(fileList.get(j).getRegisterDate()[0]),//년
											Integer.parseInt(fileList.get(j).getRegisterDate()[1]),//월
												Integer.parseInt(fileList.get(j).getRegisterDate()[2]),//일
													Integer.parseInt(fileList.get(j).getRegisterDate()[3]),//시
													 	Integer.parseInt(fileList.get(j).getRegisterDate()[4]),//분
													 		Integer.parseInt(fileList.get(j).getRegisterDate()[5])//초
													 			};
					/*
					 * key - 3번째 index의 일정보
					 * value - CashBookDetailVO타입
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
			 * 전달인자로 받은 month가 월 순서대로 와야한다.
			 */
			threeMonthList.add(viewList);
		}
		return threeMonthList;
	}
	/**
	 * 
	 * @param familyCode 가족코드
	 * @param registerCode 등록코드
	 * @param month 오름차순 3개월정보
	 * @return 3개월 MonthCashBookVO
	 */
	public ArrayList<MonthCashBookVO> loadThreeMonthCBUnique(String familyCode,String registerCode,int []month){
		ArrayList<MonthCashBookVO> threeMonthList = new ArrayList<MonthCashBookVO>();
		MonthCashBookVO viewList = null;
		ArrayList<CashBookUniqueDTO> fileList = null;
		/*
		 * 개월 수 만큼 반복
		 */
		for(int i = 0; i < month.length; ++i){
			/*
			 * 파일로부터 한 달의 정보 읽어온다. 파일명 만들어서 넣어 주어야 한다.
			 */
			fileList = this.cashBookUniqueList.loadCashBookUniqueFile(
					FileNameEdit.makeName(familyCode, "U", registerCode, 16, month[i], 16, 1115), registerCode, month[i]);
			if(fileList == null)
				return null;
			
			if(fileList.size()>0){
				/*
				 * 월 단위 객체 생성
				 */
				viewList = new MonthCashBookVO();
				for(int j = 0 ; j < fileList.size(); ++j){
					/*
					 * 파일로 부터 읽어온 년,월,일,시,분,초 정보 
					 */
					int [] registerDate = {Integer.parseInt(fileList.get(j).getRegisterDate()[0]),//년
											Integer.parseInt(fileList.get(j).getRegisterDate()[1]),//월
												Integer.parseInt(fileList.get(j).getRegisterDate()[2]),//일
													};
					/*
					 * key - 3번째 index의 일정보
					 * value - CashBookDayVO타입
					 */
					viewList.put(registerDate[2], new CashBookDayVO(
													registerDate, // 등록일
														Integer.toString(fileList.get(j).getDayLimit()), // 일일한도
															Integer.toString(fileList.get(j).getIsCompliment()))//칭찬하기 여부
																	);
					System.out.println(viewList.get(registerDate[2]));
				}
				/*
				 * 전달인자로 받은 month가 월 순서대로 와야한다.
				 */
				threeMonthList.add(viewList);
			}
		}
		return threeMonthList;		
	}
	/**
	 *  
	 * @param familyCode 가족코드
	 * @param registerCode 등록코드
	 * @param month 월정보
	 * @return 1개월 일기정보
	 */
	public MonthDiaryVO loadOneMonthDiary(String familyCode,String registerCode,int month){
		MonthDiaryVO monthList = new MonthDiaryVO();
		ArrayList<DiaryDTO>fileList = 
				/*
				 * 파일로부터 한 달의 정보 읽어온다. 파일명 만들어서 넣어 주어야 한다.
				 */
				this.diaryLIst.loadDiaryFile("F0001_D_p1_1_11_10_1_5.txt", registerCode, month).get(month);
		/*
		 * 일 수 만큼 반복
		 */
		for(int i = 0; i < fileList.size(); ++i){
			int registerDate[] = {Integer.parseInt(fileList.get(i).getRegisterDate()[0]),//년 
									Integer.parseInt(fileList.get(i).getRegisterDate()[1]),//월
										Integer.parseInt(fileList.get(i).getRegisterDate()[2])//일
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
	 * 3달치 메모정보 목록을 반환하는 메소드
	 * @param familyCode 가족코드
	 * @param registerCode 등록코드
	 * @param month 달
	 * @return 3달 메모정보 목록
	 */
	public ArrayList<MonthMemoVO> loadThreeMonthMemo(String familyCode, String registerCode,int[] month){//-----------------------
		ArrayList<MonthMemoVO> memo=new ArrayList<MonthMemoVO>();
		
		if(registerCode.equals("C1")){
		memoList.loadMemoFile("test111.txt", registerCode, month[0]);
		//System.out.println(memoList.get(registerCode,month[0]).get(0).getMemoTitle());
		//System.out.println("asdf");
		memoList.loadMemoFile("test222.txt", registerCode, month[1]);
		//System.out.println(memoList.get(registerCode,month[1]).get(0).getMemoTitle());
		memoList.loadMemoFile("test333.txt", registerCode, month[2]);//3달치에 해당하는 텍스트 로드
		//System.out.println(memoList.get(registerCode,month[2]).get(0).getMemoTitle());
		}
		else{
			memoList.loadMemoFile("test00.txt", registerCode, month[0]);
			//System.out.println(memoList.get(registerCode,month[0]).get(0).getMemoTitle());
			//System.out.println("asdf");
			memoList.loadMemoFile("test11.txt", registerCode, month[1]);
			//System.out.println(memoList.get(registerCode,month[1]).get(0).getMemoTitle());
			memoList.loadMemoFile("test22.txt", registerCode, month[2]);//3달치에 해당하는 텍스트 로드
		}
		//------------3달치 vo생성해서 list에 넣는 코드
		
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
	 * 전달인자로 넘어온 달의 용돈정보 목록을 반환하는 메소드
	 * @param familyCode 가족코드
	 * @param registerCode 등록코드
	 * @param month 달 
	 * @return month에 해당하는 용돈정보목록
	 */
	public MonthPocketMoneyVO loadOneMonthPocketMoney(String familyCode,String registerCode,int month){//---------------------
		if(registerCode.equals("C1"))
			pocketList.loadPocketMoneyFile("test31.txt", registerCode, month);
		else
			pocketList.loadPocketMoneyFile("test2.txt",registerCode,month);
		System.out.println("DataLoadManager- loadOneMonthPock"+pocketList.get("P1"));
		//System.out.println(pocketList.get(registerCode).get(month));
		//System.out.println(registerCode);
		//한달의 용돈 정보 리텬
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
		//가족의 모든 vo를 리턴하는 것으로 수정함
		//로드 결과를 받는다
		HashMap<String, SavingGoalDTO> goal=this.goalInfo.loadGoalFile("test5.txt");
		//System.out.println(goal+" "+goal.size());
		//저축목표 리스트 반환 하기위한 어레이 리스트
		SavingGoalDTO dto=goal.get(registerCode);
		return new SavingGoalVO(dto.getStartDate(),dto.getSavingGoalTitle(),
				Integer.parseInt(dto.getSavingGoalAmount()),Integer.parseInt(dto.getSuccessRate()));
		
	}
	
	/**
	 * 가족의 모든 저축목표 정보 리스트를 반환하는 메소드
	 * @param familyCode 가족코드
	 * @param registerCode 등록코드
	 * @return 저축목표정보리스트
	 */
	/*
	public QuarterSavingGoalVO loadSavingGoal(String familyCode, String[] registerCode){
		//가족의 모든 vo를 리턴하는 것으로 수정함
		//로드 결과를 받는다
		HashMap<String, HashMap<Integer,SavingGoalDTO>> goal=this.goalInfo.loadGoalFile("test5.txt");
		System.out.println(goal+" "+goal.size());
		//저축목표 리스트 반환 하기위한 어레이 리스트
		ArrayList<QuarterSavingGoalVO> vos=new ArrayList<QuarterSavingGoalVO>();
		
		Set key = goal.keySet();
		Iterator iter=key.iterator();
		
		//어레이 리스트 에 내용물 채움
		while(iter.hasNext()){                                                                      
			HashMap<Integer,SavingGoalDTO> dto=goal.get(iter.next()); 
			vos.add(new SavingGoalVO(dto.getStartDate(),
					dto.getSavingGoalTitle(),Integer.parseInt(dto.getSavingGoalAmount())
					,Integer.parseInt(dto.getSuccessRate() )));
		}
		
		return vos;
	}*/
}
