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
import java.util.HashMap;
import java.util.StringTokenizer;

import com.lectopia.chunbangsave.vo.Weather;

public class DiaryDAO {
	private HashMap<String, HashMap<Integer, ArrayList<DiaryDTO>>> diary;

	public DiaryDAO() {
		super();
		this.diary = new HashMap<String, HashMap<Integer,ArrayList<DiaryDTO>>>();
	}
	public DiaryDAO(HashMap<String, HashMap<Integer, ArrayList<DiaryDTO>>> diary) {
		super();
		this.diary = diary;
	}
	public HashMap<String, HashMap<Integer, ArrayList<DiaryDTO>>> getDiary() {
		return diary;
	}
	public void setDiary(
			HashMap<String, HashMap<Integer, ArrayList<DiaryDTO>>> diary) {
		this.diary = diary;
	}
	/**
	 * 
	 * @param fileName 로드할 파일 이름
	 * @param registerCode 로드할 등록 코드
	 * @param month 로드할 달
	 * @return 등록코드에 해당하는 사람의 달의 일기정보 목록
	 */
	public HashMap<Integer,ArrayList<DiaryDTO>> loadDiaryFile(String fileName,String registerCode, int month){
		/*
		 * 파일 로드
		 */
		BufferedReader in;
		ArrayList<String> fileString = new ArrayList<String>();
		if(this.diary.get(registerCode)==null)
			this.diary.put(registerCode, new HashMap<Integer, ArrayList<DiaryDTO>>());
		ArrayList<DiaryDTO> innerValue = new ArrayList<DiaryDTO>();
	//tempMap.put(reigsterCode,innerValue);
		try{
			in = new BufferedReader(new FileReader(fileName));
			while(true){
				StringTokenizer temp = new StringTokenizer(in.readLine(),"ㅥ",true);
				/*
				 * 첫번째 단어가 등록코드와 일치할 경우
				 */
					while(temp.hasMoreTokens()){
						String data = temp.nextToken();
						if(!data.equals("ㅥ"))
							fileString.add(data);
					}
					for(int i = 0; i < fileString.size(); i+=6){
						String[] inStr = new String[3];
						inStr[i] = fileString.get(i);
						inStr[i+1] = fileString.get(i+1);
						inStr[i+2] = fileString.get(i+2);
						Weather inWeather = Weather.SUNNY;
						if(fileString.get(i+5)=="2")inWeather =Weather.CLOUDY;
						if(fileString.get(i+5)=="3")inWeather =Weather.RAINY;
						if(fileString.get(i+5)=="4")inWeather =Weather.SNOWY;
						innerValue.add(new DiaryDTO(
								inStr, fileString.get(i+3), fileString.get(i+4),inWeather));
					}
					fileString.clear();
					this.diary.get(registerCode).put(month, innerValue);
				}
		}
		catch(EOFException e){System.out.println("EOFException");}
		catch(FileNotFoundException e){System.out.println("FileNotFoundException");}
		catch(IOException e){System.out.println("IOException");}
		catch(Exception e){}
		
		
		return this.diary.get(registerCode);
	}
	/**
	 * 
	 * @param fileName 저장할 파일이름
	 * @param registerCode 저장할 등록 코드
	 * @param outData 저장할 일기 정보 목록
	 */
/*	public void saveDiaryFile(String fileName, ArrayList<DiaryDTO> outData){

		PrintWriter out2 = null;
		File file = new File(fileName);
		try{
			if(!file.exists())
				file.createNewFile();
			out2 = new PrintWriter(new FileWriter(file));
			for(int i = 0 ; i< outData.size();++i){
				for(int j= 0; j< outData.get(i).getRegisterDate().length; ++j)
					out2.print(outData.get(i).getRegisterDate()[j]+"ㅥ");
				out2.println(outData.get(i).getDiaryTitle()+"ㅥ"+outData.get(i).getDiaryContent()+"ㅥ"+outData.get(i).getWeather()+"ㅥ");
				out2.println("썅!");
			}
		}
		catch(IOException e){
			System.out.println("IOEXCEption");
			out2.close();
		}
		out2.close();
	}
	*/
	/**
	 * 
	 * @param fileName 저장할 파일이름
	 * @param registerCode 저장할 등록 코드
	 * @param outData 저장할 일기 정보 목록
	 */
	public void saveDiaryFile(String fileName, String registerCode, int month){

		PrintWriter out2 = null;
		File file = new File(fileName);
		try{
			if(!file.exists())
				file.createNewFile();
			out2 = new PrintWriter(new FileWriter(file));
			for(int i = 0 ; i< this.diary.get(registerCode).get(month).size();++i){
				for(int j= 0; j< this.diary.get(registerCode).get(month).get(i).getRegisterDate().length; ++j)
					out2.print(this.diary.get(registerCode).get(month).get(i).getRegisterDate()[j]+"ㅥ");
				out2.println(this.diary.get(registerCode).get(month).get(i).getDiaryTitle()+"ㅥ"+this.diary.get(registerCode).get(month).get(i).getDiaryContent()+"ㅥ"+this.diary.get(registerCode).get(month).get(i).getWeather()+"ㅥ");
				out2.println("한줄추가!");
			}
		}
		catch(IOException e){
			System.out.println("IOEXCEption");
			out2.close();
		}
		out2.close();
	}
	
	public HashMap<Integer,ArrayList<DiaryDTO>> put(String registerCode,HashMap<Integer,ArrayList<DiaryDTO>> diary){
		return this.diary.put(registerCode, diary);
	}
	public boolean put(String[] register,HashMap<String,HashMap<Integer,ArrayList<DiaryDTO>>> diary){
		if(register.length<1)
			return false;
		for(int i = 0; i< register.length; ++i)
			if(this.diary.put(register[i], diary.get(register[i]))==null)
				return false;
		return true;
	}
	public ArrayList<DiaryDTO>put(String registerCode, int month, ArrayList<DiaryDTO>diary){
		return this.diary.get(registerCode).put(month, diary);
	}
	public boolean put(String registerCode, int month, DiaryDTO diary){
		return this.diary.get(registerCode).get(month).add(diary);
	}
	public HashMap<Integer, ArrayList<DiaryDTO>> remove(String registerCode){
		return this.diary.remove(registerCode);
	}
	public boolean remove(String[]registerCode){
		if(registerCode.length<1)
			return false;
		for(int i = 0; i< registerCode.length;++i)
			this.diary.remove(registerCode[i]);
		return true;
	}
	public boolean remove(String registerCode,int[]month){
		if(month.length<1)
			return false;
		for(int i = 0; i<month.length;++i)
			this.diary.get(registerCode).remove(month[i]);
		return true;
	}
	public boolean remove(String registerCode[],int month){
		if(registerCode.length<1)
			return false;
		for(int i = 0 ; i< registerCode.length;++i)
			this.diary.get(registerCode[i]).remove(month);
		return true;
	}
	
	public HashMap<Integer, ArrayList<DiaryDTO>> replace(String registerCode,HashMap<Integer, ArrayList<DiaryDTO>>diary){
		return this.diary.replace(registerCode, diary);
	}
	public boolean replace(String registerCode, int month, DiaryDTO diary){
		return this.diary.get(registerCode).get(month).add(diary);
	}
	
	public HashMap<Integer, ArrayList<DiaryDTO>> get(String registerCode){
		return this.diary.get(registerCode); 
	}
	public ArrayList<DiaryDTO> get(String registerCode, int month){
		return this.diary.get(registerCode).get(month);
	}
	public HashMap<String,ArrayList<DiaryDTO>> get(String[] register, int month){
		HashMap<String, ArrayList<DiaryDTO>> getData = new HashMap<String, ArrayList<DiaryDTO>>();
		for(int i = 0; i < register.length; ++i)
			getData.put(register[i], this.diary.get(register[i]).get(month));
		return getData;
	}
	public HashMap<String, HashMap<Integer, ArrayList<DiaryDTO>>> get(String[] register, int[]month){
		if(register.length != month.length || register.length<1)
			return null;
		HashMap<String,HashMap<Integer, ArrayList<DiaryDTO>>> getData = new HashMap<String, HashMap<Integer,ArrayList<DiaryDTO>>>();
		for(int i = 0; i <register.length;++i)
			if(getData.get(register[i])==null){
				getData.put(register[i], new HashMap<Integer, ArrayList<DiaryDTO>>());
			for(int j = 0 ; j < month.length; ++j)
				getData.get(register[i]).put(month[j],this.diary.get(register[i]).get(month[j]));
		}
		return getData;
	}
}
