import java.util.Random;

public class QuickSort {
    // hàm giải quyết việc sắp xếp các phần tử ở hai đầu của mảng
    // dựa vào phần tử chốt là phần tử cuối mảng
    public static int partition(int arr[], int low, int high) {
        // chốt được chọn ở đây là phần tử cuối mảng
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            // nếu phần tử nhỏ hơn hoặc bằng với chốt
            if (arr[j] <= pivot) {
                i++;

                // đổi chỗ arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // đổi chỗ arr[i+1] và arr[high] (chốt)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // trả về chỉ số của chốt
        return i + 1;
    }

    // Hàm thực hiện quicksort
    public static void sort(int arr[], int low, int high) {
        // nếu chỉ số của đầu mảng nhỏ hơn chỉ số cuối mảng
        if (low < high) {
            // tìm chỉ số của chốt sau khi đã thực hiện sắp xếp
            int pi = partition(arr, low, high);

            // lặp lại các bước với mảng từ phần tử đầu tiên đến chốt - 1
            // và từ chốt + 1 đến phần tử cuối cùng của mảng.
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    public static void random(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[20];
        System.out.println("Input:");
        random(arr);
        printArray(arr);
        System.out.println("\nOutput:");
        sort(arr, 0, arr.length - 1);
        printArray(arr);
    }
}