package moduleTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class PocketMoneyDAO {
	private HashMap<String,HashMap<Integer,ArrayList<PocketMoneyDTO>>> pocketMoney;//키값은 등록코드와 월단위
	public PocketMoneyDAO() {
		super();
		this.pocketMoney=new HashMap<String, HashMap<Integer,ArrayList<PocketMoneyDTO>>>();
	}
	public PocketMoneyDAO(
			HashMap<String, HashMap<Integer, ArrayList<PocketMoneyDTO>>> pocketMoney) {
		super();
		this.pocketMoney = pocketMoney;
	}
	public HashMap<Integer,ArrayList<PocketMoneyDTO>> loadPocketMoneyFile(String fileName,String registerCode){
		BufferedReader read = null;
		//this.pocketMoney=new HashMap<String,HashMap<Integer,ArrayList<PocketMoneyDTO>>>();
		//HashMap<Integer, ArrayList<PocketMoneyDTO>> person;
		try{
			read=new BufferedReader(new FileReader(fileName));
			if(this.pocketMoney.get(registerCode)==null){
				this.pocketMoney.put(registerCode,new HashMap<Integer,ArrayList<PocketMoneyDTO>>());
			}
			ArrayList<PocketMoneyDTO> pocket=new ArrayList<PocketMoneyDTO>();;
			PocketMoneyDTO pocketMoney;
			Integer month=1;
			
			while(true){//한달치만큼 반복 
				//person=new HashMap<Integer, ArrayList<PocketMoneyDTO>>();
		
				String str=read.readLine();// 한줄씩 읽어들인다 .
				if(str==null)break;
				
				StringTokenizer token=new StringTokenizer(str,",",false);
				
				String emp=token.nextToken();// 가장 첫번째에 위치한 등록코드를 읽음
				
				if(emp.equals(registerCode)){//등록코드가 일치할 때 실행 
					pocketMoney=new PocketMoneyDTO();
					pocketMoney.setRegisterCode(emp);// 등록코드가 일치하니 dto에 넣음
					
					//순서대로 토근값 읽어서 시작
					//emp=token.nextToken();//,나온다 거름
					String date=token.nextToken();//해당 날짜
					pocketMoney.setRegisterDate(date);
					//token.nextToken();//콤마 나옴 거른다.
					//System.out.println(token.nextToken());
					pocketMoney.setCategory(token.nextToken());//카테고리
					//token.nextToken();//
					pocketMoney.setAmount(Integer.parseInt(token.nextToken()));//금액
					pocketMoney.setContent(token.nextToken());//지금내용
					pocketMoney.setIsReceived(Integer.parseInt(token.nextToken()));//지금여부.
					
					pocket.add(pocketMoney);
				}else{
					token.nextToken();
					token.nextToken();
					token.nextToken();
					token.nextToken();
					token.nextToken();
				}
				
			}
			this.pocketMoney.get(registerCode).put(month,pocket);
			//this.pocketMoney.put(registerCode, person);
			return this.pocketMoney.get(registerCode);
			//System.out.println(person.get(1).get(0).getAmount());
		}catch (FileNotFoundException fnfe){
			System.out.println("aaaa");
		}catch(IOException e){
			System.out.println("bbbbb");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{
				read.close();
			}catch(Exception e){
				
			}
		}
		return null;
	}
	public HashMap<String,HashMap<Integer,ArrayList<PocketMoneyDTO>>> loadPocketMoneyFileThreeMonth(String fileName[],String registerCode,Integer month){
		BufferedReader read = null;
		//this.pocketMoney=new HashMap<String,HashMap<Integer,ArrayList<PocketMoneyDTO>>>();
		//HashMap<Integer, ArrayList<PocketMoneyDTO>> person;
		
		try{
			if(this.pocketMoney.get(registerCode)==null){
				this.pocketMoney.put(registerCode, new HashMap<Integer,ArrayList<PocketMoneyDTO>>());
			}
			for(int i=0;i<fileName.length;++i){
			read=new BufferedReader(new FileReader(fileName[i]));
			
			ArrayList<PocketMoneyDTO> pocket=new ArrayList<PocketMoneyDTO>();
			PocketMoneyDTO pocketMoney;
			
			while(true){//한달치만큼 반복 
				//person=new HashMap<Integer, ArrayList<PocketMoneyDTO>>();
		
				String str=read.readLine();// 한줄씩 읽어들인다 .
				if(str==null)break;
				
				StringTokenizer token=new StringTokenizer(str,",",false);
				
				String emp=token.nextToken();// 가장 첫번째에 위치한 등록코드를 읽음
				
				if(emp.equals(registerCode)){//등록코드가 일치할 때 실행 
					pocketMoney=new PocketMoneyDTO();
					pocketMoney.setRegisterCode(emp);// 등록코드가 일치하니 dto에 넣음
					
					//순서대로 토근값 읽어서 시작
					//emp=token.nextToken();//,나온다 거름
					String date=token.nextToken();//해당 날짜
					pocketMoney.setRegisterDate(date);
					//token.nextToken();//콤마 나옴 거른다.
					pocketMoney.setCategory(token.nextToken());//카테고리
					//token.nextToken();//
					pocketMoney.setAmount(Integer.parseInt(token.nextToken()));//금액
					pocketMoney.setContent(token.nextToken());//지금내용
					pocketMoney.setIsReceived(Integer.parseInt(token.nextToken()));//지금여부.
					
					pocket.add(pocketMoney);
				}else {
					token.nextToken();
					token.nextToken();
					token.nextToken();
					token.nextToken();
					token.nextToken();
				}
			}

				//this.pocketMoney.put(registerCode, person);
			this.pocketMoney.get(registerCode).put(month+i,pocket);
			}
			return this.pocketMoney;
		}catch (FileNotFoundException fnfe){

		}catch(IOException e){
			
		}catch(Exception e){
			
		}finally{
			try{
				read.close();
			}catch(Exception e){
				
			}
		}
		
	
	return null;
	}
	public ArrayList<PocketMoneyDTO> loadPocketMoneyFile(String fileName,String registerCode,Integer month){
			BufferedReader read = null;
			//this.pocketMoney=new HashMap<String,HashMap<Integer,ArrayList<PocketMoneyDTO>>>();
			//HashMap<Integer, ArrayList<PocketMoneyDTO>> person;
			
			try{
				if(this.pocketMoney.get(registerCode)==null){
					this.pocketMoney.put(registerCode, new HashMap<Integer,ArrayList<PocketMoneyDTO>>());
				}
				read=new BufferedReader(new FileReader(fileName));
				
				ArrayList<PocketMoneyDTO> pocket=new ArrayList<PocketMoneyDTO>();;
				PocketMoneyDTO pocketMoney=null;
				
				while(true){//한달치만큼 반복 
					//person=new HashMap<Integer, ArrayList<PocketMoneyDTO>>();
			
					String str=read.readLine();// 한줄씩 읽어들인다 .
					if(str==null)break;
					
					StringTokenizer token=new StringTokenizer(str,",",false);
					
					String emp=token.nextToken();// 가장 첫번째에 위치한 등록코드를 읽음
					
					if(emp.equals(registerCode)){//등록코드가 일치할 때 실행 
						pocketMoney=new PocketMoneyDTO();
						pocketMoney.setRegisterCode(emp);// 등록코드가 일치하니 dto에 넣음
						
						//순서대로 토근값 읽어서 시작
						//emp=token.nextToken();//,나온다 거름
						String date=token.nextToken();//해당 날짜
						pocketMoney.setRegisterDate(date);
						//token.nextToken();//콤마 나옴 거른다.
						pocketMoney.setCategory(token.nextToken());//카테고리
						//token.nextToken();//
						pocketMoney.setAmount(Integer.parseInt(token.nextToken()));//금액
						pocketMoney.setContent(token.nextToken());//지금내용
						pocketMoney.setIsReceived(Integer.parseInt(token.nextToken()));//지금여부.
						
						pocket.add(pocketMoney);
					}else {
						token.nextToken();
						token.nextToken();
						token.nextToken();
						token.nextToken();
						token.nextToken();
					}
					System.out.println("PocketMoney-loadPocketMoneyFile:"+pocketMoney);
				}
				//person.put(month, pocket);
				//this.pocketMoney.put(registerCode, person);
				this.pocketMoney.get(registerCode).put(month, pocket);
				return this.pocketMoney.get(registerCode).get(month);
			}catch (FileNotFoundException fnfe){

			}catch(IOException e){
				
			}catch(Exception e){
				
			}finally{
				try{
					read.close();
				}catch(Exception e){
					
				}
			}
			
		
		return null;
	}
	
	/*public HashMap<String,HashMap<Integer,ArrayList<PocketMoneyDTO>>> loadPocketMoneyFile(String fileName,String[] registerCode){
		BufferedReader read=null;
		
		try{
			read=new BufferedReader(new FileReader(fileName));
			
			while(true){
				String str=read.readLine();
				StringTokenizer token=new StringTokenizer(str,",",false);
				
				if(!token.nextToken().equals(read)
			}
		}catch(FileNotFoundException fnfe){
			
		}catch(IOException e){
			
		}finally{
			try{
				read.close();
			}catch(Exception e){
				
			}
		}
		
	}*/
	public void savePocketMoneyFile(String fileName,ArrayList<PocketMoneyDTO> pocketMoney){
		PrintWriter write=null;
		try{
			write=new PrintWriter(new FileWriter(fileName));
			
			for(int i=0;i<pocketMoney.size();++i){
				write.println(pocketMoney.get(i).getRegisterCode()+","+pocketMoney.get(i).getRegisterDate()+","+pocketMoney.get(i).getCategory()+","
			+pocketMoney.get(i).getAmount()+","+pocketMoney.get(i).getContent()+","+pocketMoney.get(i).getIsReceived());
			}
		}
		catch (IOException e){
			
		}finally{
			try{
				write.close();
			}catch(Exception e){
				
			}
		}
	}
	public void savePocketMoneyFile(String fileName,String registerCode){
		PrintWriter write=null;
		try{
			write=new PrintWriter(new FileWriter(fileName));
			
			Integer[] key=(Integer[])this.pocketMoney.get(registerCode).keySet().toArray();
			for(int i=0;i<key.length;++i){
				ArrayList<PocketMoneyDTO> dt=this.pocketMoney.get(registerCode).get(key[i]);
				
				for(int j=0;j<dt.size();++j){
					write.println(dt.get(i).getRegisterCode()+","+dt.get(i).getRegisterDate()+","+dt.get(i).getCategory()+","+
							dt.get(i).getAmount()+","+dt.get(i).getContent()+","+dt.get(i).getIsReceived());
				}
			}
		}
		catch(IOException e){
			
		}
		finally{
			try{
				write.close();
			}catch(Exception e){}
		}
	}
	public boolean put(HashMap<String,HashMap<Integer,ArrayList<PocketMoneyDTO>>> pocketMoney){
		this.pocketMoney.putAll(pocketMoney);
		if(this.pocketMoney.size()==pocketMoney.size()){
			return true;
		}
		return false;
	}
	public ArrayList<PocketMoneyDTO> put(String registerCode, Integer month, ArrayList<PocketMoneyDTO> pocketMoney){
		//HashMap<Integer, ArrayList<PocketMoneyDTO>> emp=new HashMap<Integer, ArrayList<PocketMoneyDTO>>();
		if(this.pocketMoney.get(registerCode)==null){
			return null;
		}
		if(pocketMoney.size()==0)return null;
		if(!pocketMoney.get(0).equals(registerCode)){
			return null;
		}
		return this.pocketMoney.get(registerCode).put(month, pocketMoney);
	}
	public HashMap<Integer, ArrayList<PocketMoneyDTO>> remove(String registerCode){
		return pocketMoney.remove(registerCode);
	}
	public HashMap<String, HashMap<Integer, ArrayList<PocketMoneyDTO>>> remove(String[] registerCode){
		HashMap<String, HashMap<Integer,ArrayList<PocketMoneyDTO>>> emp=new HashMap<String, HashMap<Integer,ArrayList<PocketMoneyDTO>>>();
		
		for(int i=0;i<registerCode.length;++i){
			emp.put(registerCode[i],pocketMoney.remove(registerCode[i]));
		}
		return emp;
	}
	public HashMap<Integer,ArrayList<PocketMoneyDTO>> replace(String registerCode, HashMap<Integer,ArrayList<PocketMoneyDTO>> pocketMoney){
		
		return this.pocketMoney.replace(registerCode, pocketMoney);
	}
	public HashMap<Integer,ArrayList<PocketMoneyDTO>> get(String registerCode){
		//System.out.println(this.pocketMoney);
		return pocketMoney.get(registerCode);
	}
	public ArrayList<PocketMoneyDTO> get(String registerCode,Integer month){
		return pocketMoney.get(registerCode).get(month);
	}
}
