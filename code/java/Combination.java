import java.util.*;
public class Combination{
    public static void main(String[] args) {
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		char[] c = "01234".toCharArray();
		int a = 1;
		while (a <= 3) {
			ArrayList<String> list = null;
			if (a > 1) {
				list = result.get(a - 2);
			}
			ArrayList<String> value = new ArrayList<>();
			for (int i = 0; i < c.length; i++) {
				if (list != null) {
					for (String string : list) {
						string = c[i] + string;
						value.add(string);
					}
				} else {
					value.add(String.valueOf(c[i]));
				}
			}
			result.add(value);
			a++;

		}
		System.out.println(result);
	
    }
}