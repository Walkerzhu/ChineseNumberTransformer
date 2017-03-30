import java.util.HashMap;
import java.util.Map;

public class NumberModel {
	/*
	 *  前置单位，例如一万五，五的前置单位是万，五需要当做5000来转换，如果前置单位为零，例如
	 * 一万零五，
	 */
     private String  lastUnit;
     
     /*
      *  后置点位，此单位优先考虑，例如一万五百，五的后置单位是百，需要当做五百处理
      */
     private String nextUnit;
     
     private String keyWord;
     
     static private Map<String, Integer> numMap = new HashMap<>();
     static {
    	    numMap.put("零", 0);
    	    numMap.put("一", 1);
    	    numMap.put("二", 2);
    	    numMap.put("三", 3);
    	    numMap.put("四", 4);
    	    numMap.put("五", 5);
    	    numMap.put("六", 6);
    	    numMap.put("七", 7);
    	    numMap.put("八", 8);
    	    numMap.put("九", 9);
    	    numMap.put("十", 10);
    	    numMap.put("百", 100);
    	    numMap.put("千", 1000);
    	    numMap.put("万", 10000);
     }
     public NumberModel(String keyword, String lastUnit, String nextUnit) {
		this.keyWord = keyword;
		this.lastUnit = lastUnit;
		this.nextUnit = nextUnit;
	}
     public int getValue() {
    	     if (nextUnit != null) {
				return numMap.get(keyWord) * numMap.get(nextUnit);
			} else {
				if (lastUnit != null) {
				    if (lastUnit.equals("零")) {
					return numMap.get(keyWord);
				     } else {
						return numMap.get(keyWord) * numMap.get(lastUnit) / 10;
			          }
			
			} else {
				return numMap.get(keyWord);
			}
		}
	}
}
