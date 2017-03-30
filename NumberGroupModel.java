
public class NumberGroupModel {
	
	private static String NUMBER = "一二三四五六七八九";
	private static String NUMBER_SPECIAL = "十";
	/*
	 *  分离出来的数据位置
	 *   0 万亿级，替换0~4
	 *   1 亿级，替换 4~8
	 *   2 万级，替换 8~12
	 *   3 个级，替换 12~16
	 */
     private int level;
     private String keyword;
     public NumberGroupModel(int level, String keyword) {
		this.keyword = keyword;
		this.level = level;
	}
     
     public int getLevel() {
		return level;
	}
     public String getTransformateNumber() {
		return transformateNumber(keyword);
	} 
     
     // 九千九百九
     private String transformateNumber(String keyword) {
    	   int count = 0;
    	   for (int i = 0; i < keyword.length(); i++) {
			 String temp = keyword.substring(i, i+1);
			 if (NUMBER.indexOf(temp) >= 0) {
				 String lastString;
				 String nextString = null;
				if (i == 0) {
					lastString = "万";
				} else {
					lastString = keyword.substring(i - 1, i);
				}
				if (i < keyword.length() - 1) {
					nextString = keyword.substring(i + 1, i + 2);
				}
				NumberModel model = new NumberModel(temp, lastString, nextString);
				count += model.getValue();
			} else if (temp.equals(NUMBER_SPECIAL)) {
				if (i == 0) {
					count+= 10;
				} else {
					String lastTemp = keyword.substring(i - 1, i);
					if (NUMBER.indexOf(lastTemp) >= 0) {
						continue;
					} else {
						count+= 10;
					}
				}
			}
		}
		String numString = Integer.toString(count);
		while (numString.length() < 4) {
			numString = "0" + numString;
		}
		return numString;
	}
     
}
