public class BinarySearch {
    public static int binarySearch(int arr[], int left, int right, int x) {
        if (right >= left) {
            // tìm chỉ số ở giữa của mảng
            int mid = left + (right - left) / 2;

            // Nếu tìm thấy phần tử đó ở giữa thì trả về vị trí mid
            if (arr[mid] == x)
                return mid;

            // Nếu phần tử ở giữa lớn hơn x thì tìm mảng con bên trái
            if (arr[mid] > x)
                return binarySearch(arr, left, mid - 1, x);

            // Nếu không thì tìm ở mảng con bên phải
            return binarySearch(arr, mid + 1, right, x);
        }

        // Nếu không tìm thấy phần tử thì trả về -1
        return -1;
    }

    // In mảng
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {15, 19, 31, 35, 37, 37, 43, 46, 53, 68, 69, 73, 77, 84, 89 };
        System.out.println("Input:");
        printArray(arr);
        System.out.println("\nOutput:");
        System.out.println(binarySearch(arr, 0, arr.length - 1, 19));
    }
}