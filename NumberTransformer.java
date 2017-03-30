import java.util.ArrayList;
import java.util.List;

/*
 *  思路：
 *  1.判断数字中是否有亿，如果有拆分两部分
 *   2.判断每部分的是否有万，如果有继续拆分
 *   3.如此每部分都可作为四位字符串去处理，不足，则高位补0
 *   4.然后拼接
 *   4. 去除高位0
 */
public class NumberTransformer {
	private static String NUMBER_UNIT_YI = "亿";
	private static String NUMBER_UNIT_WAN = "万";
	
	private StringBuffer numberString = new StringBuffer("0000000000000000");
    private  List<NumberGroupModel> list = new ArrayList<>();
    
	public void transformerString(String string) {
		if (string.indexOf(NUMBER_UNIT_YI) >= 0) {
			String[] array = string.split(NUMBER_UNIT_YI);
			for (int i = 0; i < array.length; i++) {
				String childString = array[i];
				if (childString.contains(NUMBER_UNIT_WAN)) {
					String[] childArray = childString.split(NUMBER_UNIT_WAN);
					for (int j = 0; j < childArray.length; j++) {
						NumberGroupModel model = new NumberGroupModel(i * 2 + j, childArray[j]);
						list.add(model);
					}
				} else {
					int level = 1;
					if (i == 1) {
						if (childString.length() == 1) {
							level = 0;
						}
					}
					NumberGroupModel model = new NumberGroupModel(i * 2 + level, childString);
					list.add(model);
				}
			}
		} else  {
			if (string.indexOf(NUMBER_UNIT_WAN) >= 0) {
				String[] childArray = string.split(NUMBER_UNIT_WAN);
				for (int j = 0; j < childArray.length; j++) {
					NumberGroupModel model = new NumberGroupModel(2 + j, childArray[j]);
					list.add(model);
				}
			} else {
				NumberGroupModel model = new NumberGroupModel(3, string);
				list.add(model);
			}
		}
               if (list.size() > 0) {
			NumberGroupModel model = list.get(0);
			model.setFirst(true);
		}
		handleNumberModelList();
	}
	
	private void handleNumberModelList() {
		for (int i = 0; i < list.size(); i++) {
			NumberGroupModel model = list.get(i);
			int startIndex = model.getLevel() * 4;
			numberString.replace(startIndex, startIndex + 4,model.getTransformateNumber());
		}
		
		while (numberString.substring(0, 1).equals("0")) {
		   numberString.delete(0, 1);
		}
	}
	
	public String getResult() {
		return numberString.toString();
	}
}
