// Chương trình Java để in tất cả các hoán vị của một
// chuỗi đã cho.
public class ListPermutations {

	// Hàm in ra các hoán vị
	public static void permute(String str, int l, int r) {
		if (l == r)
			System.out.println(str);
		else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i);
				permute(str, l + 1, r);
				str = swap(str, l, i);
			}
		}
	}

	// Hàm hoán đổi vị trí các phần tử
	public static String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}

	public static void main(String[] args) {
		String str = "ABC";
		int n = str.length();
		permute(str, 0, n - 1);
	}
}
