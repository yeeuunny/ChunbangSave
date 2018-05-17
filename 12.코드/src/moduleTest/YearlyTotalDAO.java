package moduleTest;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * 
 * @author ����
 *   ������� ����
 */
public class YearlyTotalDAO {
	private HashMap<Integer, HashMap<String, ArrayList<YearlyTotalDTO>>> finalAccount;

	public YearlyTotalDAO() {
		super();
		this.finalAccount = new HashMap<Integer, HashMap<String,ArrayList<YearlyTotalDTO>>>();
		//ArrayList<YearlyTotalDTO> innerValue = new ArrayList<YearlyTotalDTO>();
		//HashMap<String, ArrayList<YearlyTotalDTO>> tempMap = new HashMap<String, ArrayList<YearlyTotalDTO>>();
	}

	public YearlyTotalDAO(
			HashMap<Integer, HashMap<String, ArrayList<YearlyTotalDTO>>> finalAccount) {
		super();
		this.finalAccount = finalAccount;
	}

	public HashMap<Integer, HashMap<String, ArrayList<YearlyTotalDTO>>> getFinalAccount() {
		return finalAccount;
	}

	public void setFinalAccount(
			HashMap<Integer, HashMap<String, ArrayList<YearlyTotalDTO>>> finalAccount) {
		this.finalAccount = finalAccount;
	}
	/**
	 * @ �Ѹ��� �� �� ������ ���Ϸκ��� �о��
	 * @param fileName �ε��� �����̸�
	 * @param year �ε��� �⵵
	 * @param reigsterCode ����ڵ�
	 * @return �ε��Ͽ� �ϼ��� ���� HashMap ������
	 */
	public ArrayList<YearlyTotalDTO> loadFinalAccountFile(String fileName,int year,String reigsterCode){
		/*
		 * ���� �ε�
		 */
		BufferedReader in = null;
		ArrayList<String> fileString = new ArrayList<String>();
		ArrayList<YearlyTotalDTO> innerValue = new ArrayList<YearlyTotalDTO>();
		if(this.finalAccount.get(year)==null)
			this.finalAccount.put(year, new HashMap<String, ArrayList<YearlyTotalDTO>>());
	//	HashMap<String, ArrayList<YearlyTotalDTO>> tempMap = new HashMap<String, ArrayList<YearlyTotalDTO>>();
	//tempMap.put(reigsterCode,innerValue);
		try{
			in = new BufferedReader(new FileReader(fileName));
		}
		catch(Exception e){}
		while(true){
			try{
					StringTokenizer temp = new StringTokenizer(in.readLine(),"/");
					/*
					 * ù��° �ܾ ����ڵ�� ��ġ�� ���
					 */
					String fileRegisterCode = temp.nextToken();
					if(reigsterCode.equals(fileRegisterCode)){
						while(temp.hasMoreTokens()){
							String data = temp.nextToken();
							if(!data.equals("/"))
								fileString.add(data);
						}
						System.out.println("���Ͽ��� �ҷ����� ���Դϴ�..");
						for(int i = 0; i < fileString.size(); ++i)
							System.out.print(fileString.get(i)+"/");
						System.out.println();
						for(int i = 0; i < fileString.size(); i+=3)
							innerValue.add(new YearlyTotalDTO(Integer.parseInt(fileString.get(i)), Integer.parseInt(fileString.get(i+1)), Integer.parseInt(fileString.get(i+2))));
						fileString.clear();
						
						this.finalAccount.get(year).put(reigsterCode, innerValue);
						System.out.println(reigsterCode+finalAccount.get(year).get(reigsterCode).get(0));
					}
			}
			catch(EOFException e){System.out.println("EOFException");}
			catch(FileNotFoundException e){System.out.println("FileNotFoundException");}
			catch(IOException e){System.out.println("IOException");}
			catch(Exception e){System.out.println("Exception");break;}
		}
		
		return this.finalAccount.get(year).get(reigsterCode);
	}
	/**
	 * @ �������� ���� ������ �� ���Ϸκ��� �ϰſ�
	 * @param fileName �ε��� �Ѱ��� ����
	 * @param year �ε��� �⵵
	 * @param reigsterCode �ε��� ����ڵ� ���
	 * @return �ε��Ͽ� �ϼ��� ���� HashMap ������
	 */
	public HashMap<String, ArrayList<YearlyTotalDTO>> loadFinalAccountFile(String fileName,int year,String reigsterCode[]){
		/*
		 * ���� �ε�
		 */
		BufferedReader in = null;
		ArrayList<String> fileString = new ArrayList<String>();
		if(this.finalAccount.get(year)==null)
			this.finalAccount.put(year, new HashMap<String, ArrayList<YearlyTotalDTO>>());
	//	HashMap<String, ArrayList<YearlyTotalDTO>> tempMap = new HashMap<String, ArrayList<YearlyTotalDTO>>();
	//tempMap.put(reigsterCode,innerValue);
		try{
			in = new BufferedReader(new FileReader(fileName));
		}
		catch(Exception e){}
		while(true){
			try{
					StringTokenizer temp = new StringTokenizer(in.readLine(),"/");
					/*
					 * ù��° �ܾ ����ڵ�� ��ġ�� ���
					 */
					String fileRegisterCode = temp.nextToken();
					for(int i = 0 ; i< reigsterCode.length;++i){
						System.out.println(i+1+"��°");
						if(reigsterCode[i].equals(fileRegisterCode)){
							while(temp.hasMoreTokens()){
								String data = temp.nextToken();
								if(!data.equals("/"))
									fileString.add(data);
							}
							ArrayList<YearlyTotalDTO> innerValue = new ArrayList<YearlyTotalDTO>();
							System.out.println("���Ͽ��� �ҷ����� ���Դϴ�..");
							for(int j = 0; j < fileString.size(); ++j)
								System.out.print(fileString.get(j)+"/");
							System.out.println();
							for(int j = 0; j < fileString.size(); j+=3)
								innerValue.add(new YearlyTotalDTO(Integer.parseInt(fileString.get(j)), Integer.parseInt(fileString.get(j+1)), Integer.parseInt(fileString.get(j+2))));
							System.out.println("innerValue: "+innerValue.get(0));
							this.finalAccount.get(year).put(reigsterCode[i], innerValue);
							fileString.clear();
							System.out.println(reigsterCode[i]+finalAccount.get(year).get(reigsterCode[i]).get(0));
						}
					}
			}
			catch(EOFException e){System.out.println("EOFException");}
			catch(FileNotFoundException e){System.out.println("FileNotFoundException");}
			catch(IOException e){System.out.println("IOException");}
			catch(Exception e){System.out.println("Exception");break;}
		}
		
		return this.finalAccount.get(year);
	}
	/**
	 * @ �Ѹ��� ���� �� ������ ���� ���Ϸκ��� �ϰſ�
	 * @param fileName ������ ���� ���
	 * @param year ������ �⵵ ���
	 * @param reigsterCode ������ ��� ��� �ڵ�
	 * @return
	 */
	public HashMap<Integer, HashMap<String, ArrayList<YearlyTotalDTO>>> loadFinalAccountFile(String fileName[],int year[],String reigsterCode){
		if(fileName.length != year.length || fileName.length<1)
			return null;
		/*
		 * ���� �ε�
		 */
		BufferedReader in;
		ArrayList<String> fileString = new ArrayList<String>();
		ArrayList<YearlyTotalDTO> innerValue = new ArrayList<YearlyTotalDTO>();
		HashMap<String, ArrayList<YearlyTotalDTO>> tempMap = new HashMap<String, ArrayList<YearlyTotalDTO>>();
	//tempMap.put(reigsterCode,innerValue);
		for(int i = 0 ; i < fileName.length;++i){
			try{
				in = new BufferedReader(new FileReader(fileName[i]));
				while(true){
					StringTokenizer temp = new StringTokenizer(in.readLine(),"/");
					/*
					 * ù��° �ܾ ����ڵ�� ��ġ�� ���
					 */
					if(reigsterCode.equals(temp.nextToken())){
						while(temp.hasMoreTokens()){
							String data = temp.nextToken();
							if(!data.equals("/"))
								fileString.add(data);
						}
						System.out.println("���Ͽ��� �ҷ����� ���Դϴ�..");
						for(int j = 0; j < fileString.size(); ++i)
							System.out.print(fileString.get(j)+"/");
						System.out.println();
						for(int j = 0; j < fileString.size(); j+=3)
							innerValue.add(new YearlyTotalDTO(Integer.parseInt(fileString.get(j)), Integer.parseInt(fileString.get(j+1)), Integer.parseInt(fileString.get(j+2))));
						tempMap.put(reigsterCode, innerValue);;
						this.finalAccount.put(year[i], tempMap);
					}
				}
			}
			
			catch(EOFException e){System.out.println("EOFException");}
			catch(FileNotFoundException e){System.out.println("FileNotFoundException");}
			catch(IOException e){System.out.println("IOException");}
			catch(Exception e){System.out.println("Exception");}
		}
		
		return this.finalAccount;
	}
	/**
	 * @ �Ѹ��� ���� ������ �� ���Ͽ� ����
	 * @param fileName ������ ���� �̸�
	 * @param year ������ ��� �⵵
	 * @param registerCode ������ ��� ��� �ڵ� ���
	 */
	public void saveFinalAccountFile(String fileName,int year, String registerCode[]){

		PrintWriter out2 = null;
		File file = new File(fileName);
		try{
			if(!file.exists())
				file.createNewFile();
			out2 = new PrintWriter(new FileWriter(file));
			for(int index = 0; index< registerCode.length;++index){
				out2.print(registerCode[index]+"/");
				ArrayList<YearlyTotalDTO>inDataList = this.finalAccount.get(year).get(registerCode[index]);
				System.out.println(this.finalAccount.get(year).get(registerCode[index]).get(0));
				System.out.println("inData��");
				for(int i = 0 ; i< inDataList.size();++i)
					out2.print(inDataList.get(i).getRegisterCategory()+"/"+inDataList.get(i).getDetailCategory()+"/"+inDataList.get(i).getAmount()+"/");
				out2.println();
			}
		}
		catch(IOException e){
			System.out.println("IOEXCEption");
			out2.close();
		}
		out2.close();
	}
	/**
	 * @ �������� ���� ������ ���� ���Ͽ� ����
	 * @param fileName ������ ���� ���
	 * @param year ������ �⵵
	 * @param registerCode ������ ����� ����ڵ� ���
	 */
	public void saveFinalAccountFile(String fileName[],int year,String[]registerCode){
		GregorianCalendar temp = new GregorianCalendar();
		if(year <= temp.get(Calendar.YEAR)-2015 && year >= temp.get(Calendar.YEAR)-2010)
			return;
		if(fileName.length!=registerCode.length||fileName.length<1)
			return;
		PrintWriter out2 = null;
		for(int i = 0 ; i < fileName.length; ++i){
			File file = new File(fileName[i]);
			try{
				if(!file.exists())
					file.createNewFile();
				out2 = new PrintWriter(new FileWriter(file));
				out2.print(registerCode[i]+"/");
				ArrayList<YearlyTotalDTO>inDataList = this.finalAccount.get(year).get(registerCode[i]);
				for(int j = 0 ; j< inDataList.size();++j)
					out2.println(inDataList.get(j).getRegisterCategory()+"/"+inDataList.get(j).getDetailCategory()+"/"+inDataList.get(j).getAmount()+"/");
			}
			catch(IOException e){
				System.out.println("IOEXCEption");
				out2.close();
			}
			out2.close();
		}
	}
	/**
	 * @ �Ѹ��� ���� �� ������ ���� ���Ͽ� ����
	 * @param fileName ������ ���� ���
	 * @param year ������ �⵵ ���
	 * @param registerCode ������ ��� ����ڵ�
	 */
	public void saveFinalAccountFile(String fileName[],int year[],String registerCode){
		GregorianCalendar temp = new GregorianCalendar();
		for(int i = 0 ; i<year.length;++i)
			if(year[i] <= temp.get(Calendar.YEAR)-2015 && year[i] >= temp.get(Calendar.YEAR)-2010)
				return;
		if(fileName.length!=year.length||fileName.length<1)
			return;
		PrintWriter out2 = null;
		for(int i = 0 ; i < fileName.length; ++i){
			File file = new File(fileName[i]);
			try{
				if(!file.exists())
					file.createNewFile();
				out2 = new PrintWriter(new FileWriter(file));
				out2.print(registerCode+"/");
				ArrayList<YearlyTotalDTO>inDataList = this.finalAccount.get(year[i]).get(registerCode);
				for(int j = 0 ; j< inDataList.size();++j)
					out2.println(inDataList.get(j).getRegisterCategory()+"/"+inDataList.get(j).getDetailCategory()+"/"+inDataList.get(j).getAmount()+"/");
			}
			catch(IOException e){
				System.out.println("IOEXCEption");
				out2.close();
			}
			out2.close();
		}
	}
	/*public void saveFinalAccountFile(String fileName,HashMap<Integer, HashMap<String, ArrayList<YearlyTotalDTO>>> outData){

		PrintWriter out2 = null;
		File file = new File(fileName);
		try{
			//if(!file.exists())
			//	file.createNewFile();
			out2 = new PrintWriter(new FileWriter(file));
			Set<String> tempSet = outData.keySet();
			String[]tempIntAry = (String[])(tempSet.toArray());
	
			Set<String> tempSet2;
			String[]tempStringAry;
			for(int i = 0; i < tempIntAry.length; ++i){
				tempSet2 = outData.get(tempIntAry[i]).keySet();
				tempStringAry = (String[])(tempSet2.toArray());
				System.out.println(tempStringAry[i]);
			}
		}
		catch(IOException e){}
	
	}*/
	public HashMap<Integer, ArrayList<YearlyTotalDTO>> put(int year,HashMap<Integer, ArrayList<YearlyTotalDTO>> inFinalAccount){
		//this.finalAccount.put(year, inFinalAccount);
		return null;
	}
	/**
	 * 
	 * @param year put�� �⵵
	 * @param registerCode ��� �ڵ�
	 * @param finalAccount �⺰ ��� ����
	 * @return �߰��� �⺰ ��� ����
	 */
	public ArrayList<YearlyTotalDTO> put(int year, String registerCode,ArrayList<YearlyTotalDTO> finalAccount){
		GregorianCalendar temp = new GregorianCalendar();
		if(year <= temp.get(Calendar.YEAR)-2015 && year >= temp.get(Calendar.YEAR)-2010)
			return null;
		if(registerCode.equals("")||registerCode==null)
			return null;
		if(finalAccount.isEmpty())
			return null;
		return this.finalAccount.get(year).put(registerCode,finalAccount);
	}
	public boolean put(int year, String registerCode,YearlyTotalDTO finalAccount){
		GregorianCalendar temp = new GregorianCalendar();
		if(year <= temp.get(Calendar.YEAR)-2015 && year >= temp.get(Calendar.YEAR)-2010)
			return false;
		if(registerCode.equals("")||registerCode==null)
			return false;
		if(finalAccount==null)
			return false;
		return this.finalAccount.get(year).get(registerCode).add(finalAccount);
	}
	public boolean put(int year, String[]registerCode,HashMap<String, ArrayList<YearlyTotalDTO>> finalAccount){
		GregorianCalendar temp = new GregorianCalendar();
		if(year <= temp.get(Calendar.YEAR)-2015 && year >= temp.get(Calendar.YEAR)-2010)
			return false;
		if(registerCode.length < 1 )
			return false;
		/*
		 * �Ѿ�� ������ �� DTO list �˻�
		 */
		for(int i = 0; i< registerCode.length;++i)
			if( finalAccount.get(registerCode[i]).isEmpty())
				return false;
		/*
		 * �߰��Ǿ����� �˻�
		 */
		for(int i = 0; i< registerCode.length;++i)
			if(this.finalAccount.get(year).put(registerCode[i], finalAccount.get(registerCode[i]))==null)
				return false;
		return true;
	}
	/**
	 * 
	 * @param year ������ �⵵
	 * @return ������ �⵵ ���� ����
	 */
	public HashMap<String, ArrayList<YearlyTotalDTO>> remove(int year){
		GregorianCalendar temp = new GregorianCalendar();
		if(year <= temp.get(Calendar.YEAR)-2015 && year >= temp.get(Calendar.YEAR)-2010)
			return null;
		return this.finalAccount.remove(year);
	}
	public boolean remove(int year[]){
		GregorianCalendar temp = new GregorianCalendar();
		for(int i = 0 ; i < year.length;++i)
			if(year[i] <= temp.get(Calendar.YEAR)-2015 && year[i] >= temp.get(Calendar.YEAR)-2010)
				return false;
		for(int i = 0; i < year.length; ++i)
			if(this.finalAccount.remove(year[i])==null)
				return false;
		return true;
	}
	public ArrayList<YearlyTotalDTO> remove(int year,String registerCode){
		GregorianCalendar temp = new GregorianCalendar();
		if(year <= temp.get(Calendar.YEAR)-2015 && year >= temp.get(Calendar.YEAR)-2010)
			return null;
		if(registerCode.equals("")||registerCode==null)
			return null;
		/*
		 * ���� ���� ����
		 */
		return this.finalAccount.get(year).remove(registerCode);
	}
	public boolean remove(int year,String[]registerCode){
		GregorianCalendar temp = new GregorianCalendar();
		if(year <= temp.get(Calendar.YEAR)-2015 && year >= temp.get(Calendar.YEAR)-2010)
			return false;
		if(registerCode.length<1)
			return false;
		for(int i = 0 ; i < registerCode.length;++i)
			if(this.finalAccount.get(year).get(registerCode[i])==null)
				return false;
		/*
		 * ���� ���� ����
		 */
		for(int i = 0 ; i < registerCode.length;++i)
			if(this.finalAccount.get(year).remove(registerCode[i])==null)
				return false;
		return true;
	}
	
	public HashMap<String, ArrayList<YearlyTotalDTO>> replace(int year, HashMap<String , ArrayList<YearlyTotalDTO>> fianlAccount){
		return this.finalAccount.replace(year, fianlAccount);
	}
	public boolean replace(int year[], HashMap<String , ArrayList<YearlyTotalDTO>> infianlAccount){
		for(int i = 0; i < year.length; ++i)
			if(this.finalAccount.replace(year[i],infianlAccount)==null)
				return false;
		return true;
	}
	//get method
	public HashMap<String,ArrayList<YearlyTotalDTO>> get(int year){
		return this.finalAccount.get(year);
	}
	public ArrayList<YearlyTotalDTO> get(int year,String registerCode){
		return this.finalAccount.get(year).get(registerCode);
	}
	public HashMap<Integer, ArrayList<YearlyTotalDTO>> get(int[]year,String registerCode){
		HashMap<Integer, ArrayList<YearlyTotalDTO>> temp = new HashMap<Integer, ArrayList<YearlyTotalDTO>>(year.length);
		for(int i = 0 ; i < year.length; ++i)
			temp.put(year[i],this.finalAccount.get(year[i]).get(registerCode));
		return temp;
	}
	public HashMap<Integer, HashMap<String,ArrayList<YearlyTotalDTO>>> get(int[]year,String[] registerCode){
		HashMap<Integer, HashMap<String,ArrayList<YearlyTotalDTO>>> temp = new HashMap<Integer, HashMap<String,ArrayList<YearlyTotalDTO>>>(year.length);
		
		for(int i = 0 ; i < year.length; ++i)
			for(int j = 0 ; j< registerCode.length; ++j)
				if(null==temp.get(year[i]).put(registerCode[j],this.finalAccount.get(year[i]).get(registerCode[j])))
					return null;
		return temp;
	}
	public HashMap<String, ArrayList<YearlyTotalDTO>> get(int year, String[]registerCode){
		HashMap<String, ArrayList<YearlyTotalDTO>> temp = new HashMap<String,ArrayList<YearlyTotalDTO>>(registerCode.length);
		for(int i = 0; i < registerCode.length; ++i)
			temp.put(registerCode[i], this.finalAccount.get(year).get(registerCode[i]));
		return temp;
	}
}
