// Liệt kê tất cả các dãy nhị phân có độ dài n
import java.util.*;

public class ListBinary {

	// Hàm in ra output
	public static void printArray(int arr[], int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// hàm sinh ra toàn bộ giá trị nhị phân
	public static void generateAllBinaryStrings(int n,
			int arr[], int i) {
		if (i == n) {
			printArray(arr, n);
			return;
		}

		// Đầu tiên gán "0" ở vị trí thứ i
		// và thử cho tất cả các hoán vị khác
		// ở các vị trí còn lại
		arr[i] = 0;
		generateAllBinaryStrings(n, arr, i + 1);

		// Và sau đó gán "1" ở vị trí thứ i
		// và thử cho tất cả các hoán vị khác
		// cho các vị trí còn lại
		arr[i] = 1;
		generateAllBinaryStrings(n, arr, i + 1);
	}

	public static void main(String args[]) {
		int n = 4;

		int[] arr = new int[n];

		// In ra toàn bộ chuỗi nhị phân có độ dài n
		generateAllBinaryStrings(n, arr, 0);
	}
}
