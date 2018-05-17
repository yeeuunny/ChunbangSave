package moduleTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CashBookDAO 
{
	private HashMap<String, HashMap<Integer, ArrayList<CashBookDTO>>> cashBookMap;

	public CashBookDAO()
	{
		this(new HashMap<String, HashMap<Integer, ArrayList<CashBookDTO>>>());
	}
	public CashBookDAO(
			HashMap<String, HashMap<Integer, ArrayList<CashBookDTO>>> cashBookMap) 
	{
		super();
		this.cashBookMap = cashBookMap;
	}
	public HashMap<String, HashMap<Integer, ArrayList<CashBookDTO>>> getCashBookMap() 
	{
		return cashBookMap;
	}
	public void setCashBookMap(
			HashMap<String, HashMap<Integer, ArrayList<CashBookDTO>>> cashBookMap) 
	{
		this.cashBookMap = cashBookMap;
	}
	/* 한 사람, 하나의 월 load */
	public ArrayList<CashBookDTO> loadCashBookFile(String fileName, String registerCode, int month)
	{
		BufferedReader cashBookReader = null;
		ArrayList<CashBookDTO> list = new ArrayList<CashBookDTO>();
		try
		{
			cashBookReader = new BufferedReader(new FileReader(fileName));
			String str = null;
			while((str = cashBookReader.readLine()) != null)
			{
				CashBookDTO tempDTO = new CashBookDTO();
				StringTokenizer token = new StringTokenizer(str, "/");
				StringTokenizer dateToken = new StringTokenizer(token.nextToken(), "-");
				String[] temp = new String[6];
				
				for(int i = 0; i < 6; i++)
					temp[i] = dateToken.nextToken();

				if(Integer.parseInt(temp[1])==month){
					tempDTO.setRegisterDate(temp);
					tempDTO.setRegisterCategory(Integer.parseInt(token.nextToken()));
					tempDTO.setDetailCategory(token.nextToken());
					tempDTO.setAmount(Integer.parseInt(token.nextToken()));
					tempDTO.setContent(token.nextToken());	
					//System.out.println("CashBookDAO-loadCashBookFile : "+tempDTO);
					list.add(tempDTO);
				}
			}
			if(this.cashBookMap.get(registerCode) == null)
			{
				HashMap<Integer, ArrayList<CashBookDTO>> newMap = new HashMap<Integer, ArrayList<CashBookDTO>>();
				newMap.put(month, list);
				this.cashBookMap.put(registerCode, newMap);			
			}
			//else
				this.cashBookMap.get(registerCode).put(month, list);
		}
		catch(IOException e)
		{
			System.out.println("파일 입력 오류");
			e.getStackTrace();
		}
		finally
		{
			try{ cashBookReader.close(); }
			catch(Exception e) { e.getStackTrace(); }
		}
		return list;
	}
	/* 한 사람의 여러개 월 load */
	public HashMap<Integer, ArrayList<CashBookDTO>> loadCashBookFile(
			String[] fileName, String registerCode, int[] month)
	{
		for(int i = 0; i < fileName.length; i++)
			loadCashBookFile(fileName[i], registerCode, month[i]);
		return this.get(registerCode);
	}
	/* 여러 사람, 하나의 월 load */
	public HashMap<String, HashMap<Integer, ArrayList<CashBookDTO>>> loadCashBookFile(
			String[] fileName, String[] registerCode, int month)
	{
		for(int i = 0; i < fileName.length; i++)
			loadCashBookFile(fileName[i], registerCode[i], month);
		return this.cashBookMap;
	}
	/* 여러 사람, 여러개 월 load */
	public HashMap<String, HashMap<Integer, ArrayList<CashBookDTO>>> loadCashBookFile(
			String[] fileName, String[] registerCode, int[] month)
	{
		for(int i = 0; i < fileName.length; i++)
			loadCashBookFile(fileName[i], registerCode[i], month[i]);
		return this.cashBookMap;
	}
	/* 한 사람, 하나의 월 save */
	public void saveCashBookFile(String fileName, String registerCode, int month)
	{
		PrintWriter cashBookWriter = null;
		ArrayList<CashBookDTO> cashBookList = new ArrayList<CashBookDTO>();
		cashBookList = this.cashBookMap.get(registerCode).get(month);
		try
		{ 
			cashBookWriter = new PrintWriter(new FileWriter(fileName));
			
			for(int i = 0; i < cashBookList.size(); i++)
			{
				for(int j = 0; j < 6; j++)
				{
					cashBookWriter.print(cashBookList.get(i).getRegisterDate()[j]);
					if(j != 5)
						cashBookWriter.print("-");
				}
				cashBookWriter.println("/" + cashBookList.get(i).getRegisterCategory() + "/" 
						+ cashBookList.get(i).getDetailCategory() + "/" 
						+ cashBookList.get(i).getAmount() + "/" + cashBookList.get(i).getContent());
			}
		}
		catch(IOException e)
		{
			System.out.println("파일 출력 오류");
			e.getStackTrace();
		}
		finally
		{
			try { cashBookWriter.close(); } 
			catch(Exception e) { e.getStackTrace(); }
		}
	}
	/* 한 사람, 여러 월 save */
	public void saveCashBookFile(String[] fileName, String registerCode, int[] month)
	{
		for(int i = 0; i < fileName.length; i++)
			saveCashBookFile(fileName[i], registerCode, month[i]);
	}
	/* 한 사람의 가계부 put */
	public HashMap<Integer, ArrayList<CashBookDTO>> put(
			String registerCode, HashMap<Integer, ArrayList<CashBookDTO>> cashBookList)
	{
		return this.cashBookMap.put(registerCode, cashBookList);
	}
	/* 한 사람의 월 가계부 put - 해쉬맵, 어레이리스트 무엇으로 반환하지???? */
	public HashMap<Integer, ArrayList<CashBookDTO>> put(
			String registerCode, int month, ArrayList<CashBookDTO> cashBookList)
	{
		HashMap<Integer, ArrayList<CashBookDTO>> inMap = new HashMap<Integer, ArrayList<CashBookDTO>>();
		inMap.put(month, cashBookList);
		
		return this.cashBookMap.put(registerCode, inMap);
	}
	/* 한 사람의 월 가계부 하나 put **/
	public CashBookDTO put(String registerCode, int month, CashBookDTO cashBook)
	{
		this.cashBookMap.get(registerCode).get(month).add(cashBook);
		return cashBook; /****** boolean */
	}
	/* 한 사람의 가계부 remove */
	public HashMap<Integer, ArrayList<CashBookDTO>> remove(String registerCode)
	{
		return this.cashBookMap.remove(registerCode);
	}
	/* 한 사람의 월 가계부 remove */
	public ArrayList<CashBookDTO> remove(String registerCode,int month)
	{
		return this.cashBookMap.get(registerCode).remove(month);
	}
	/* 한 사람의 여러개의 월 가계부 remove */
	public HashMap<Integer, ArrayList<CashBookDTO>> remove(String registerCode, int[] month)
	{
		HashMap<Integer, ArrayList<CashBookDTO>> newMap = new HashMap<Integer, ArrayList<CashBookDTO>>();
		for(int i : month)
			newMap.put(i, remove(registerCode, i));
		return newMap;
	}
	/* 한 사람의 하나의 가계부 remove ???? */
	
	/* 한 사람 한달~여러달의 가계부 몽땅 replace */
	public HashMap<Integer, ArrayList<CashBookDTO>> replace(
			String registerCode, HashMap<Integer, ArrayList<CashBookDTO>> cashBook)
	{
		return this.cashBookMap.replace(registerCode, cashBook);
	}
	
	/* 한 사람의 모든 월 가계부 get */
	public HashMap<Integer, ArrayList<CashBookDTO>> get(String registerCode)
	{
		return this.cashBookMap.get(registerCode);
	}
	/* 한 사람의 하나의 월 가계부 get */
	public ArrayList<CashBookDTO> get(String registerCode, int month)
	{
		return get(registerCode).get(month);
	}
	/* 여러 사람의 하나의 월 가계부 get */
	public HashMap<String, ArrayList<CashBookDTO>> get(String[] registerCode, int month)
	{
		HashMap<String, ArrayList<CashBookDTO>> newMap = new HashMap<String, ArrayList<CashBookDTO>>();
		for(String i : registerCode)
			newMap.put(i, get(i, month));
		return newMap;	
	}
	/* 여러 사람의 여러개의 월 가계부 get */
	public HashMap<String, HashMap<Integer, ArrayList<CashBookDTO>>> get(
			String[] registerCode, int[] month)
	{
		HashMap<String, HashMap<Integer, ArrayList<CashBookDTO>>> newMap = 
				new HashMap<String, HashMap<Integer, ArrayList<CashBookDTO>>>();
		for(int i : month)
			get(registerCode, i);
		return newMap;
	}
	@Override
	public String toString() 
	{
		return "CashBookDAO [cashBookMap=" + cashBookMap + "]";
	}
}

