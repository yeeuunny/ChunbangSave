package com.lectopia.chunbangsave.vo;

import java.util.ArrayList;

import moduleTest.CashBookDTO;

public class TestLoad {

	public static void main(String[] args) {
		QuarterManager temp = new QuarterManager(QuarterNo.FIRST_QUARTER);
		int month[] = {1,2,3};
		// 가계부 상세 정보
		System.out.println(temp.putDetailInfoList("p1", temp.getDataLoadManager().loadThreeMonthCB("F0001", "p1", month)));
				ArrayList<MonthCashBookDetailVO> list2 = temp.getDetailInfoList().get("p1");
		for(int i = 0 ; i< list2.size(); ++i)
			for(int j = 0 ; j< list2.get(i).get(9).size();++j)
				System.out.println(list2.get(i).get(9).get(j));
		System.out.println();
		// 가계부 일일 정보
		System.out.println(temp.putDayInfo("p1", temp.getDataLoadManager().loadThreeMonthCBUnique("F0001", "p1", month)));
			ArrayList<MonthCashBookVO> list3 = temp.getDayInfo().get("p1");
			for(int i = 0 ; i< list3.size(); ++i)
					System.out.println(list3.get(i).get(23));
		System.out.println();
		// 일기 정보
		System.out.println(temp.putDiaryList("p1", 1, temp.getDataLoadManager().loadOneMonthDiary("F0001", "p1", 1)));
			MonthDiaryVO list4 = temp.getDiaryList().get("p1");
			System.out.println(list4.get(13));
		System.out.println();
			
	}

}
