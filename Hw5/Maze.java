/*  Chương trình java 
    giải quyết bài toán "Đường đi trong mê cung"
    sủ dụng thuật toán Backtracking
 */

public class Maze {

    // Kích thước mê cung cỡ N
    static int N;

    /* In ra output sol[N][N] */
    void printSolution(int sol[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    /* Hàm kiểm tra x, y có hợp lệ cho me cung N*N không */
    boolean isSafe(int maze[][], int x, int y) {
        // Nếu x,y vượt quá chỉ số, trả về false
        return (x >= 0 && x < N && y >= 0
                && y < N && maze[x][y] == 1);
    }

    /*
     * Hàm giải tìm đường đi trong mê cung sử dụng giải thuật quy lui
     * Nó chủ yếu sử dụng hàm solveMazeUtil() để giải quyết vấn đề
     * to s. Nó trả về falsse nếu không
     * nếu đường đi có thể, ngược lại trả về true
     * và in ra đường đi dưới dạng 1s. 
     * Hàm in ra đường đi khả thi
     */
    boolean solveMaze(int maze[][]) {
        int sol[][] = new int[N][N];

        if (solveMazeUtil(maze, 0, 0, sol) == false) {
            System.out.print("Solution doesn't exist");
            return false;
        }

        printSolution(sol);
        return true;
    }

    // Hàm đệ quy tìm đường đi trong mê cung
    boolean solveMazeUtil(int maze[][], int x, int y, int sol[][]) {
        // if (x, y thoả mãn) trả về true
        if (x == N - 1 && y == N - 1
                && maze[x][y] == 1) {
            sol[x][y] = 1;
            return true;
        }

        // Kiểm tra nếu ma trận maze[x][y] là hợp lệ
        if (isSafe(maze, x, y) == true) {
            // Check if the current block is already part of solution path.
            if (sol[x][y] == 1)
                return false;

            // Đánh dấu x, y như 1 phần của đường đi
            sol[x][y] = 1;

            // Di chuyển về phía trước theo hướng x
            if (solveMazeUtil(maze, x + 1, y, sol))
                return true;

            /*
             * Nếu di chuyển theo hướng x không được
             * Thì di chuyển xuống theo hướng y
             */
            if (solveMazeUtil(maze, x, y + 1, sol))
                return true;

            /*
             * Nếu di chuyển theo hướng y không được
             * Thì di chuyển ngược lại theo hướng x
             */
            if (solveMazeUtil(maze, x - 1, y, sol))
                return true;

            /*
             * Nếu di chuyển ngược lại theo hướng x không được
             * Thì di chuyển lên trên theo hướng y
             */
            if (solveMazeUtil(maze, x, y - 1, sol))
                return true;

            /*
             * Nếu không có chuyển động nào ở trên hoạt động thì
             * BACKTRACK: bỏ đánh dấu x, y như một phần của giải pháp
             */
            sol[x][y] = 0;
            return false;
        }

        return false;
    }

    public static void main(String args[]) {
        Maze point = new Maze();
        int maze[][] = { { 1, 1, 0, 0 },
                { 1, 1, 1, 1 },
                { 0, 0, 1, 1 },
                { 1, 1, 1, 1 } };

        N = maze.length;
        point.solveMaze(maze);
    }
}
