package moduleTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MemoDAO 
{
	private HashMap<String, HashMap<Integer, ArrayList<MemoDTO>>> memoMap;

	public MemoDAO()
	{
		this(new HashMap<String, HashMap<Integer, ArrayList<MemoDTO>>>());
	}
	public MemoDAO(
			HashMap<String, HashMap<Integer, ArrayList<MemoDTO>>> memoMap) 
	{
		super();
		this.memoMap = memoMap;
	}
	public HashMap<String, HashMap<Integer, ArrayList<MemoDTO>>> getMemoMap() 
	{
		return memoMap;
	}
	public void setMemoMap(
			HashMap<String, HashMap<Integer, ArrayList<MemoDTO>>> memoMap) 
	{
		this.memoMap = memoMap;
	}
	/* 한 사람, 하나의 월 load */
	public ArrayList<MemoDTO> loadMemoFile(String fileName, String registerCode, int month)
	{
		BufferedReader memoReader = null;
		ArrayList<MemoDTO> list = new ArrayList<MemoDTO>();
		try
		{
			memoReader = new BufferedReader(new FileReader(fileName));
			String str = null;
			String contentStr = null;
			
			while((str = memoReader.readLine()) != null)
			{
				MemoDTO tempDTO = new MemoDTO();
				StringTokenizer token = new StringTokenizer(str, "/");
				StringTokenizer dateToken = new StringTokenizer(token.nextToken(), "-");
				String[] temp = new String[3];
				
				for(int i = 0; i < 3; i++)
					temp[i] = dateToken.nextToken();
				
				tempDTO.setRegisterDate(temp);
				tempDTO.setMemoTitle(token.nextToken());
				contentStr = "";
				while(!(str = memoReader.readLine()).equals("***"))
					contentStr += str + "\n";
				
				tempDTO.setMemoContent(contentStr);
				list.add(tempDTO);
			}
			if(this.memoMap.get(registerCode) == null)
			{
				HashMap<Integer, ArrayList<MemoDTO>> newMap = new HashMap<Integer, ArrayList<MemoDTO>>();
				newMap.put(month, list);
				this.memoMap.put(registerCode, newMap);			
			}
			else
				this.memoMap.get(registerCode).put(month, list);
		}
		catch(IOException e)
		{
			System.out.println("파일 입력 오류");
			e.getStackTrace();
		}
		finally
		{
			try{ memoReader.close(); }
			catch(Exception e) { e.getStackTrace(); }
		}
		return list;
	}
	/* 한 사람의 여러개 월 load */
	public HashMap<Integer, ArrayList<MemoDTO>> loadMemoFile(
			String[] fileName, String registerCode, int[] month)
	{
		for(int i = 0; i < fileName.length; i++)
			loadMemoFile(fileName[i], registerCode, month[i]);
		return this.get(registerCode);
	}
	/* 여러 사람, 하나의 월 load */
	public HashMap<String, HashMap<Integer, ArrayList<MemoDTO>>> loadMemoFile(
			String[] fileName, String[] registerCode, int month)
	{
		for(int i = 0; i < fileName.length; i++)
			loadMemoFile(fileName[i], registerCode[i], month);
		return this.memoMap;
	}
	/* 여러 사람, 여러개 월 load */
	public HashMap<String, HashMap<Integer, ArrayList<MemoDTO>>> loadMemoFile(
			String[] fileName, String[] registerCode, int[] month)
	{
		for(int i = 0; i < fileName.length; i++)
			loadMemoFile(fileName[i], registerCode[i], month[i]);
		return this.memoMap;
	}
	/* 한 사람, 하나의 월 save */
	public void saveMemoFile(String fileName, String registerCode, int month)
	{
		PrintWriter memoWriter = null;
		ArrayList<MemoDTO> memoList = new ArrayList<MemoDTO>();
		System.out.println(memoMap);
		memoList = this.memoMap.get(registerCode).get(month);
		try
		{ 
			memoWriter = new PrintWriter(new FileWriter(fileName));
			
			for(int i = 0; i < memoList.size(); i++)
			{
				for(int j = 0; j < 3; j++)
				{
					memoWriter.print(memoList.get(i).getRegisterDate()[j]);
					if(j != 2)
						memoWriter.print("-");
				}
				memoWriter.println("/" + memoList.get(i).getMemoTitle());
				memoWriter.println(memoList.get(i).getMemoContent());
				memoWriter.println("***");
			}
		}
		catch(IOException e)
		{
			System.out.println("파일 출력 오류");
			e.getStackTrace();
		}
		finally
		{
			try { memoWriter.close(); } 
			catch(Exception e) { e.getStackTrace(); }
		}
	}
	/* 한 사람, 여러 월 save */
	public void saveMemoFile(String[] fileName, String registerCode, int[] month)
	{
		for(int i = 0; i < fileName.length; i++)
			saveMemoFile(fileName[i], registerCode, month[i]);
	}
	/* 한 사람 메모 전체  put */
	public HashMap<Integer, ArrayList<MemoDTO>> put(
			String registerCode, HashMap<Integer, ArrayList<MemoDTO>> memoList)
	{
		return this.memoMap.put(registerCode, memoList);
	}
	/* 한 사람의 월 메모 put - 해쉬맵, 어레이리스트 무엇으로 반환하지???? */
	public HashMap<Integer, ArrayList<MemoDTO>> put(
			String registerCode, int month, ArrayList<MemoDTO> memoList)
	{
		HashMap<Integer, ArrayList<MemoDTO>> inMap = new HashMap<Integer, ArrayList<MemoDTO>>();
		inMap.put(month, memoList);
		
		return this.memoMap.put(registerCode, inMap);
	}
	/* 한 사람의 월 메모 하나 put **/
	public MemoDTO put(String registerCode, int month, MemoDTO memo)
	{
		this.memoMap.get(registerCode).get(month).add(memo);
		return memo; /****** boolean */
	}
	/* 한 사람의 remove */
	public HashMap<Integer, ArrayList<MemoDTO>> remove(String registerCode)
	{
		return this.memoMap.remove(registerCode);
	}
	/* 한 사람의 월 remove */
	public ArrayList<MemoDTO> remove(String registerCode,int month)
	{
		return this.memoMap.get(registerCode).remove(month);
	}
	/* 한 사람의 여러개의 월 remove */
	public HashMap<Integer, ArrayList<MemoDTO>> remove(String registerCode, int[] month)
	{
		HashMap<Integer, ArrayList<MemoDTO>> newMap = new HashMap<Integer, ArrayList<MemoDTO>>();
		for(int i : month)
			newMap.put(i, remove(registerCode, i));
		return newMap;
	}
	
	/* 한 사람 한달~여러달 몽땅 replace */
	public HashMap<Integer, ArrayList<MemoDTO>> replace(
			String registerCode, HashMap<Integer, ArrayList<MemoDTO>> memo)
	{
		return this.memoMap.replace(registerCode, memo);
	}
	
	/* 한 사람의 모든 월 get */
	public HashMap<Integer, ArrayList<MemoDTO>> get(String registerCode)
	{
		return this.memoMap.get(registerCode);
	}
	/* 한 사람의 하나의 월 get */
	public ArrayList<MemoDTO> get(String registerCode, int month)
	{
		return get(registerCode).get(month);
	}
	/* 여러 사람의 하나의 월 get */
	public HashMap<String, ArrayList<MemoDTO>> get(String[] registerCode, int month)
	{
		HashMap<String, ArrayList<MemoDTO>> newMap = new HashMap<String, ArrayList<MemoDTO>>();
		for(String i : registerCode)
			newMap.put(i, get(i, month));
		return newMap;	
	}
	/* 여러 사람의 여러개의 월 get */
	public HashMap<String, HashMap<Integer, ArrayList<MemoDTO>>> get(
			String[] registerCode, int[] month)
	{
		HashMap<String, HashMap<Integer, ArrayList<MemoDTO>>> newMap = 
				new HashMap<String, HashMap<Integer, ArrayList<MemoDTO>>>();
		for(int i : month)
			get(registerCode, i);
		return newMap;
	}
	@Override
	public String toString() 
	{
		return "MemoDAO [memoMap=" + memoMap + "]";
	}
}

