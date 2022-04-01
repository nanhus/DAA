// Đối tượng chứa value min mã cần tìm
class Pair {
    public int max, min;

    public Pair(int max, int min) {
        this.max = max;
        this.min = min;
    }
}

public class MinMax {
    public static void findMinMax(int[] arr, int left, int right, Pair p) {
        // Nếu mảng chỉ có 1 phần tử

        if (left == right) {
            if (p.max < arr[left]) { // so sánh 1
                p.max = arr[left];
            }

            if (p.min > arr[right]) { // so sánh 2
                p.min = arr[right];
            }

            return;
        }

        // Nếu mảng gồm 2 phần tử

        if (right - left == 1)
        {
            if (arr[left] < arr[right]) 
            {
                if (p.min > arr[left]) { 
                    p.min = arr[left];
                }

                if (p.max < arr[right]) {
                    p.max = arr[right];
                }
            } else {
                if (p.min > arr[right]) {
                    p.min = arr[right];
                }

                if (p.max < arr[left]) { 
                    p.max = arr[left];
                }
            }

            return;
        }
        //Mảng có 2 phần tử trở lên
        // tìm vị trí phần tử ở giữa
        int mid = (left + right) / 2;

        // Đệ quy cho mảng con bên trái
        findMinMax(arr, left, mid, p);

        // Đệ quy cho mảng con bên phải
        findMinMax(arr, mid + 1, right, p);
    }

    public static void main(String[] args) {
        int[] arr = { 37, 89, 70, 93, 91, 6, 99, 3, 78, 10, 18, 73, 80, 40, 70, 66, 19, 35, 76, 10 };

        // Khởi tạo phần tử tối thiểu bằng INFINITY
        // và phần tử tối đa bằng -INFINITY
        Pair p = new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE);
        findMinMax(arr, 0, arr.length - 1, p);

        System.out.println("The minim: " + p.min);
        System.out.println("The maximum: " + p.max);
    }
}