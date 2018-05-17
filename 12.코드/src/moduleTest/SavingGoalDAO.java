package moduleTest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;


public class SavingGoalDAO 
{
	private HashMap<String, SavingGoalDTO> goalMap; //키:등록코드 
	
	public SavingGoalDAO() 
	{
		this(new HashMap<String, SavingGoalDTO>());
	}

	public SavingGoalDAO(HashMap<String, SavingGoalDTO> goalMap) 
	{
		super();
		this.goalMap = goalMap;
	}

	public HashMap<String, SavingGoalDTO> getGoalMap() 
	{
		return goalMap;
	}

	public void setGoalMap(HashMap<String, SavingGoalDTO> goalMap) 
	{
		this.goalMap = goalMap;
	}
	
	/******/
	public HashMap<String, SavingGoalDTO> loadGoalFile(String fileName)
	{
		BufferedReader goalReader = null;
		try
		{
			goalReader = new BufferedReader(new FileReader(fileName));
			
			String str = null;
			while((str = goalReader.readLine()) != null)
			{
				StringTokenizer token = new StringTokenizer(str, "/");
				SavingGoalDTO goalDTO = new SavingGoalDTO();
				while(token.hasMoreTokens())
				{
					goalDTO.setRegisterCode(token.nextToken());
					goalDTO.setQuarter(token.nextToken());
					goalDTO.setSavingGoalTitle(token.nextToken());
					goalDTO.setSavingGoalAmount(token.nextToken());
					
					StringTokenizer dateToken = new StringTokenizer(token.nextToken(), "-");
					goalDTO.setStartDate(new String[]{ dateToken.nextToken(), dateToken.nextToken(), dateToken.nextToken() });
					goalDTO.setSuccessRate(token.nextToken());
					
				}
				//System.out.println("loadGoalFileㅁㄴㅇ : "+goalDTO);
				put(goalDTO.getRegisterCode(), goalDTO);
			}
			System.out.println("1"+this.goalMap);
		}
		catch(IOException e)
		{
			System.out.println("파일 입력 오류");
			e.getStackTrace();
			System.out.println("2");
		}
		finally
		{
			try { goalReader.close(); }
			catch(Exception e) { e.getStackTrace(); }
			System.out.println("3");
		}
		System.out.println(this.goalMap);
		return this.goalMap;
	}
	public void saveGoalFile(String fileName, HashMap<String, SavingGoalDTO> goalMap)
	{
		this.goalMap = goalMap;
		PrintWriter goalWriter = null;
		String[] keys = (String[])(goalMap.keySet().toArray(new String[goalMap.size()]));
		try
		{
			goalWriter = new PrintWriter(new FileWriter(fileName));
			int i = 0;
			while(i != goalMap.size())
			{
				SavingGoalDTO goal = get(keys[i]);
				goalWriter.println(goal.getRegisterCode() + "/" + goal.getSavingGoalTitle() + "/" 
						+ goal.getSavingGoalAmount() + "/" + goal.getStartDate()[0] + "-" 
						+ goal.getStartDate()[1] + "-" + goal.getStartDate()[2] + "/" + goal.getSuccessRate());
				i++;		
			}
			goalWriter.flush();
		}
		catch(Exception IOException)
		{
			System.out.println("파일 출력 오류");
		}
		finally
		{
			try { goalWriter.close(); }
			catch(Exception e) { }
		}
	}
	/** 필요한듯 **/
	public void saveGoalFile(String fileName)
	{
		PrintWriter goalWriter = null;
		try
		{
			goalWriter = new PrintWriter(new FileWriter(fileName));
			String[] keys = (String[])goalMap.keySet().toArray();
			int i = 0;
			
			while(!goalMap.isEmpty())
			{
				SavingGoalDTO goal = get(keys[i]);
				goalWriter.println(goal.getRegisterCode() + "/" + goal.getSavingGoalTitle() + "/" 
						+ goal.getSavingGoalAmount() + "/" + goal.getStartDate()[0] + "-" 
						+ goal.getStartDate()[1] + "-" + goal.getStartDate()[2] + "/" + goal.getSuccessRate());
				i++;		
			}
		}
		catch(Exception IOException)
		{
			System.out.println("파일 출력 오류");
		}
		finally
		{
			try { goalWriter.close(); }
			catch(Exception e) { }
		}
	}
	public SavingGoalDTO put(String registerCode, SavingGoalDTO savingGoal)
	{
		return this.goalMap.put(registerCode, savingGoal);
	}
	public SavingGoalDTO remove(String registerCode)
	{
		return this.goalMap.remove(registerCode);
	}
	/*
	public ArrayList<SavingGoalDTO> remove(String registerCode)
	{
	}
	*/
	public SavingGoalDTO replace(String registerCode, SavingGoalDTO goalList)
	{
		return this.goalMap.replace(registerCode, goalList);
	}
	/*
	public ArrayList<SavingGoalDTO> replace(String registerCode, ArrayList<SavingGoalDTO> goalList)
	{
	}
	*/
	public SavingGoalDTO get(String registerCode)
	{
		return this.goalMap.get(registerCode);
	}
	/*
	public SavingGoalDTO get(String registerCode)
	{
		this.goalMap.get(registerCode);
	}
	*/
	public HashMap<String, SavingGoalDTO> get(String[] registerCode)
	{
		HashMap<String, SavingGoalDTO> newMap = new HashMap<String, SavingGoalDTO>();
		for(String i : registerCode)
			newMap.put(i, get(i));
		return newMap;
	}

	@Override
	public String toString() {
		return "SavingGoalDAO [goalMap=" + goalMap + "]";
	}
	
	
}
