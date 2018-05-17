package moduleTest;

import java.util.StringTokenizer;

public class FileNameEdit {
	
	public static String makeName(String familyCode,String category,String userCode,int year,int month,int userYear,int userMonthDay){
		return familyCode+"_"+category+"_"+userCode+"_"+year+"_"+month+"_"+userYear+"_"+userMonthDay+".txt";
	}
	public static String[] makeName(String familyCode,String category,String userCode,int year,int month[],int userYear,int userMonthDay){
		String[] str=new String[month.length];
		for(int i=0;i<month.length;++i){
			str[i]=familyCode+"_"+category+"_"+userCode+"_"+year+"_"+month[i]+"_"+userYear+"_"+userMonthDay+".txt";
		}
		return str;
	}
	public static String[] makeName(String familyCode,String category,String[] userCode,int year,int month[],int userYear,int userMonthDay){
		String[] str=new String[userCode.length];
		for(int i=0;i<month.length;++i){
			str[i]=familyCode+"_"+category+"_"+userCode[i]+"_"+year+"_"+month[i]+"_"+userYear+"_"+userMonthDay+".txt";
		}
		return str;
	}
	public static String[] makeName(String familyCode,String category,String[] userCode,int year,int month,int userYear,int userMonthDay){
		String[] str=new String[userCode.length];
		for(int i=0;i<userCode.length;++i){
			str[i]=familyCode+"_"+category+"_"+userCode[i]+"_"+year+"_"+month+"_"+userYear+"_"+userMonthDay+".txt";
		}
		return str;
	}
	public static String makeName(String[] codeIdentifier,int[] dayIdentifier){
		return codeIdentifier[0]+"_"+codeIdentifier[1]+"_"+codeIdentifier[2]+"_"+dayIdentifier[0]+"_"+dayIdentifier[1]+"_"+dayIdentifier[2]+"_"+
				dayIdentifier[3]+"_"+dayIdentifier[4]+".txt";
	}
	public static String[] makeName(String[] codeIdentifier,int[] monthIdentifier,int[] dayIdentifier){
		String[] str=new String[monthIdentifier.length];
		for(int i=0;i<monthIdentifier.length;++i){
			str[i]=codeIdentifier[0]+"_"+codeIdentifier[1]+"_"+codeIdentifier[2]+"_"+dayIdentifier[0]+"_"+monthIdentifier[i]+"_"+dayIdentifier[2]+"_"+
					dayIdentifier[3]+"_"+dayIdentifier[4]+".txt";
		}
		return str;
	}
	public static String[] resolveFileName(String fileName){
		StringTokenizer token=new StringTokenizer(fileName,"_.",false);
		int len = token.countTokens();
		String[] str=new String[len];
		for(int i=0;i<len - 1;++i)
			str[i]=token.nextToken();
		return str;
	}
	public static String[][] resolveFileName(String fileName[]){
		String[][] str=new String[fileName.length][];
		for(int i=0;i<fileName.length;++i){
			StringTokenizer token=new StringTokenizer(fileName[i],"_.",false);
			str[i]=new String[token.countTokens()-1];
			for(int j=0;j<token.countTokens()-1;++i){
				str[i][j]=token.nextToken();
			}
		}
		return str;
	}
}
