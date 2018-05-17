package moduleTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import com.lectopia.chunbangsave.vo.QuarterManager;

public class CashBookUniqueDAO {
	private HashMap<String,HashMap<Integer,ArrayList<CashBookUniqueDTO>>> cashBookUniq;//키값 등록코드 , 월

	public CashBookUniqueDAO() {
		super();
		cashBookUniq=new HashMap<String,HashMap<Integer,ArrayList<CashBookUniqueDTO>>>();
	}
	public CashBookUniqueDAO(
			HashMap<String, HashMap<Integer, ArrayList<CashBookUniqueDTO>>> cashBookUniq) {
		super();
		this.cashBookUniq = cashBookUniq;
	}
	public HashMap<String,HashMap<Integer,ArrayList<CashBookUniqueDTO>>> loadCashBookUniqueFileThreeMonth
	(String fileName[],String registerCode,Integer month){
		BufferedReader read=null;
		try{
			if(this.cashBookUniq.get(registerCode)==null){
				this.cashBookUniq.put(registerCode, new HashMap<Integer,ArrayList<CashBookUniqueDTO>>());
			}
			for(int i=0;i<fileName.length;++i){
				read=new BufferedReader(new FileReader(fileName[i]));
				//ArrayList<CashBookUniqueDTO> dt=new ArrayList<CashBookUniqueDTO>();;
				
				CashBookUniqueDTO.setCurrentBalance(Integer.parseInt(read.readLine()));
			
				//this.cashBookUniq.get(registerCode).get(month+i).get(0).setCurrentBalance(Integer.parseInt(amount));
				if(this.cashBookUniq.get(registerCode).get(month+i)==null){
					this.cashBookUniq.get(registerCode).put(month+i,new ArrayList<CashBookUniqueDTO>());
				}
				while(true){
					String info=read.readLine();
					StringTokenizer token=new StringTokenizer(info,",",false);
					CashBookUniqueDTO cash=new CashBookUniqueDTO();
					String[]inDate = {token.nextToken(),token.nextToken(),token.nextToken()};
					cash.setRegisterDate(inDate);
					cash.setDayLimit(Integer.parseInt(token.nextToken()));
					cash.setIsCompliment(Integer.parseInt(token.nextToken()));
					
					this.cashBookUniq.get(registerCode).get(month+i).add(cash);
					if(!token.hasMoreTokens())break;
				}
			}
			return this.cashBookUniq;
			
		}catch(IOException io){
			
		}finally{
			try{
				read.close();
			}catch(Exception e){
			}
		}
		return null;
		
	}
	public HashMap<String,HashMap<Integer,ArrayList<CashBookUniqueDTO>>> loadCashBookUniqueFile(String fileName){
		BufferedReader read=null;
		try{
			read=new BufferedReader(new FileReader(fileName));
			
			ArrayList<CashBookUniqueDTO> list=new ArrayList<CashBookUniqueDTO>();
			String amount=read.readLine();
			
			CashBookUniqueDTO.setCurrentBalance(Integer.parseInt(amount));
			String registerCode="C1";//파일이름에서 등록코드 번호 따오는 코드 필요
			Integer month=1;//파일 이름에서 달수 읽어오는 코드 필요
			
			if(this.cashBookUniq.get(registerCode)==null){
				this.cashBookUniq.get(registerCode).put(month, new ArrayList<CashBookUniqueDTO>());
			}
			
			while(true){
				String emp=read.readLine();
				StringTokenizer token=new StringTokenizer(emp,",",false);
				CashBookUniqueDTO dt=new CashBookUniqueDTO();
				String[]inDate = {token.nextToken(),token.nextToken(),token.nextToken()};
				dt.setRegisterDate(inDate);
				dt.setDayLimit(Integer.parseInt(token.nextToken()));
				dt.setIsCompliment(Integer.parseInt(token.nextToken()));
				this.cashBookUniq.get(registerCode).get(month).add(dt);
				if(!token.hasMoreTokens())break;
			}
			return this.cashBookUniq;
		}catch(FileNotFoundException fnfe){
			
		}catch(IOException io){
			
		}finally{
			try{
				read.close();
			}catch(Exception e){
				
			}
		}
		return null;
	}
	public ArrayList<CashBookUniqueDTO> loadCashBookUniqueFile(String fileName,String registerCode,Integer month){
		BufferedReader read=null;
		
		try{
			read=new BufferedReader(new FileReader(fileName));
			//ArrayList<CashBookUniqueDTO> dt=new ArrayList<CashBookUniqueDTO>();;
			if(this.cashBookUniq.get(registerCode)==null)
				this.cashBookUniq.put(registerCode, new HashMap<Integer,ArrayList<CashBookUniqueDTO>>());
			
			String amount=read.readLine();
			/*
			 * 현재 잔액
			 */
			CashBookUniqueDTO.setCurrentBalance(Integer.parseInt(amount));
			QuarterManager.setCurrentAmount(amount);
			ArrayList<CashBookUniqueDTO> list;
			if(this.cashBookUniq.get(registerCode).get(month)==null)
				list=new ArrayList<CashBookUniqueDTO>();
			else
				list=this.cashBookUniq.get(registerCode).get(month);
			while(true){
				String info=read.readLine();
				if(info==null)break;
				StringTokenizer token=new StringTokenizer(info,",",false);
				CashBookUniqueDTO cash=new CashBookUniqueDTO();
				String[]inDate = {token.nextToken(),token.nextToken(),token.nextToken()};
				cash.setRegisterDate(inDate);
				cash.setDayLimit(Integer.parseInt(token.nextToken()));
				cash.setIsCompliment(Integer.parseInt(token.nextToken()));
				list.add(cash);
			}
			if(this.cashBookUniq.get(registerCode).get(month)==null)
				this.cashBookUniq.get(registerCode).put(month,list);
			return this.cashBookUniq.get(registerCode).get(month);
			
		}catch(IOException io){
			
		}finally{
			try{
				read.close();
			}catch(Exception e){
			
			}
		}
		return null;
	}
	public void saveCashBookUniqueFile(String fileName,String registerCode){
		PrintWriter write=null;
		try{
			write=new PrintWriter(new FileWriter(fileName));
			write.println(CashBookUniqueDTO.getCurrentBalance());
			
			//Integer[] key=(Integer[])this.cashBookUniq.get(registerCode).keySet().toArray();
			Set key=this.cashBookUniq.get(registerCode).keySet();
			Iterator iter=key.iterator();
			while(iter.hasNext()){
				Integer inti=(Integer)iter.next();
			
				ArrayList<CashBookUniqueDTO> dt=this.cashBookUniq.get(registerCode).get(inti);
				for(int j=0;j<dt.size();++j)
					if(dt.get(j).getRegisterDate()[1].equals("11"))
						write.println(dt.get(j).getRegisterDate()[0]+","+dt.get(j).getRegisterDate()[1]+","+dt.get(j).getRegisterDate()[2]
							+","+dt.get(j).getDayLimit()+","+dt.get(j).getIsCompliment());
				
			
			}
		}catch(IOException io){
			
		}finally{
			try{
				write.close();
			}catch(Exception e){
				
			}
		}
	}
	public ArrayList<CashBookUniqueDTO> put(String registerCode,Integer month,ArrayList<CashBookUniqueDTO> cashBookUniq){
		if(this.cashBookUniq.get(registerCode)==null){
			this.cashBookUniq.put(registerCode, new HashMap<Integer,ArrayList<CashBookUniqueDTO>>());
		}
		
		return this.cashBookUniq.get(registerCode).put(month, cashBookUniq);
	}
	public boolean putAll(HashMap<String,HashMap<Integer,ArrayList<CashBookUniqueDTO>>> cashBookUniq){
		int size=this.cashBookUniq.size();
		this.cashBookUniq.putAll(cashBookUniq);
		if(this.cashBookUniq.size()-size!=cashBookUniq.size()){
			return false;
		}
		return true;
	}
	public HashMap<Integer,ArrayList<CashBookUniqueDTO>> remove(String registerCode){
		return this.cashBookUniq.remove(registerCode);
	}
	public HashMap<String ,ArrayList<CashBookUniqueDTO>> remove(String[] registerCode,Integer month){
		HashMap<String, ArrayList<CashBookUniqueDTO>> hash=new HashMap<String, ArrayList<CashBookUniqueDTO>>();
		for(int i=0;i<registerCode.length;++i){
			hash.put(registerCode[i],this.cashBookUniq.get(registerCode[i]).get(month));
			this.cashBookUniq.get(registerCode[i]).remove(month);
		}
		return hash;
	}
	public HashMap<Integer,ArrayList<CashBookUniqueDTO>> replace(String registerCode, HashMap<Integer,ArrayList<CashBookUniqueDTO>> cashBookUniq){
		return this.cashBookUniq.replace(registerCode,cashBookUniq);
	}
	public HashMap<Integer,ArrayList<CashBookUniqueDTO>> get(String registerCode){
		return this.cashBookUniq.get(registerCode);
	}
	public ArrayList<CashBookUniqueDTO> get(String registerCode,Integer month){
		return this.cashBookUniq.get(registerCode).get(month);
	}
	public HashMap<String, ArrayList<CashBookUniqueDTO>> get(String[] registerCode,Integer month){
		HashMap<String, ArrayList<CashBookUniqueDTO>> cash=new HashMap<String, ArrayList<CashBookUniqueDTO>>();
		for(int i=0;i<registerCode.length;++i){
			cash.put(registerCode[i],this.cashBookUniq.get(registerCode[i]).get(month));
		}
		return cash;
	}
	public HashMap<String,HashMap<Integer,ArrayList<CashBookUniqueDTO>>> get(String[] registerCode,Integer[] month){
		HashMap<String, HashMap<Integer,ArrayList<CashBookUniqueDTO>>> hash=new HashMap<String, HashMap<Integer,ArrayList<CashBookUniqueDTO>>>();
		for(int i=0;i<registerCode.length;++i){
			HashMap<Integer,ArrayList<CashBookUniqueDTO>> mon=new HashMap<Integer,ArrayList<CashBookUniqueDTO>>();
			for(int j=0;j<registerCode.length;++j){
				ArrayList<CashBookUniqueDTO> cas=new ArrayList<CashBookUniqueDTO>(cashBookUniq.get(registerCode[i]).get(month[i]));
				mon.put(month[j], cas);
			}
			hash.put(registerCode[i], mon);
		}
		return hash;
	}
}
