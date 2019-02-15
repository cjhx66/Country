package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageValidate {
	private static String RegPhone = new String("^[0-9]+[-]?[0-9]+[-]?[0-9]$");
	private static String RegNumber = new String("^[0-9]+$");
	private static String RegNumberSign = new String("^[+-]?[0-9]+$");
	private static String RegDecimal = new String("^[0-9]+[.]?[0-9]+$");
	private static String RegDecimalSign = new String("^[+-]?[0-9]+[.]?[0-9]+$"); //等价于^[+-]?\d+[.]?\d+$
	private static String RegEmail = new String("^[\\w-]+@[\\w-]+\\.(com|net|org|edu|mil|tv|biz|info)$");//w 英文字母或数字的字符串，和 [a-zA-Z0-9] 语法一样 
	private static String RegCHZN = new String("[\u4e00-\u9fa5]");

	public PageValidate()
	{
	}
	 public static Boolean isPhone(String inputData)
     {
		 Pattern pattern = Pattern.compile(RegPhone);
		 Matcher matcher = pattern.matcher(inputData);
		return matcher.find();
     }
	
	 public static boolean isNumber(String inputData)
		{
         if (null==inputData) return false;
         Pattern pattern = Pattern.compile(RegNumber);
		 Matcher matcher = pattern.matcher(inputData);
		 return matcher.find();
		}
	 public static boolean IsNumberSign(String inputData)
		{
		 if (null==inputData) return false;
         Pattern pattern = Pattern.compile(RegNumberSign);
		 Matcher matcher = pattern.matcher(inputData);
		 return matcher.find();
		}
	 public static boolean isDecimal(String inputData)
		{
		 if (null==inputData) return false;
         Pattern pattern = Pattern.compile(RegDecimal);
		 Matcher matcher = pattern.matcher(inputData);
		 return matcher.find();
		}
	 public static boolean isDecimalSign(String inputData)
		{
		 if (null==inputData) return false;
         Pattern pattern = Pattern.compile(RegDecimalSign);
		 Matcher matcher = pattern.matcher(inputData);
		 return matcher.find();
		}
	 public static boolean isHasCHZN(String inputData)
		{
		 if (null==inputData) return false;
         Pattern pattern = Pattern.compile(RegCHZN);
		 Matcher matcher = pattern.matcher(inputData);
		 return matcher.find();
		}	
	 public static boolean isEmail(String inputData)
		{
		 if (null==inputData) return false;
         Pattern pattern = Pattern.compile(RegEmail);
		 Matcher matcher = pattern.matcher(inputData);
		 return matcher.find();
		}		
	 public static String sqlText(String sqlInput, int maxLength)
		{			
			if(sqlInput != null && sqlInput != null)
			{
				sqlInput = sqlInput.trim();							
				if(sqlInput.length() > maxLength)//按最大长度截取字符串
					sqlInput = sqlInput.substring(0, maxLength);
			}
			return sqlInput;
		}
	 public static String inputText(String inputString, int maxLength) 
		{			
			StringBuilder retVal = new StringBuilder();

			// 检查是否为空
			if ((inputString != null) && (inputString != null)) 
			{
				inputString = inputString.trim();
				
				//检查长度
				if (inputString.length() > maxLength)
					inputString = inputString.substring(0, maxLength);
				
				//替换危险字符
				for (int i = 0; i < inputString.length(); i++) 
				{
					switch (inputString.charAt(i)) 
					{
						case '\"':
							retVal.append("&quot;");
							break;
						case '<':
							retVal.append("&lt;");
							break;
						case '>':
							retVal.append("&gt;");
							break;
						default:
							retVal.append(inputString.charAt(i));
							break;
					}
				}
				//retVal.toString().replace('\'', ' ');// 替换单引号
            
			}
			return retVal.toString().replace('\'', ' ');
			
		}
}
