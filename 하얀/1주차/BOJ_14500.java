import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {
    private static int row, column, answer;
    private static int[][] board;
    private static int max = 0;
    private static final int TETROMINO_LENTH = 4;
    private static boolean[][] isVisited;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    private static void output() {
        System.out.println(max);
    }

    private static void solve() {
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < column; k++) {
                isVisited[i][k] = true;
                dfs(i, k, board[i][k], 1);
                goSpecialBlock(i, k, board[i][k]);
                isVisited[i][k] = false;
            }
        }
    }

    private static void goSpecialBlock(int x, int y, int total) {
        int[][] dx = {{1, 2, 1}, {1, 2, 1}, {0, 0, 1}, {0, 0, -1}};
        int[][] dy = {{0, 0, 1}, {0, 0, -1}, {1, 2, 1}, {1, 2, 1}};

        for (int i = 0; i < 4; i++) {
            int sum = 0;
            for (int k = 0; k < 3; k++) {
                int nextX = x + dx[i][k];
                int nextY = y + dy[i][k];

                if (nextX < 0 || nextY < 0 || nextX >= row || nextY >= column) {
                    break;
                }

                sum += board[nextX][nextY];
                if (k == 2) max = Math.max(max, total + sum);
            }
        }
    }

    private static void dfs(int x, int y, int total, int depth) {
        if (depth == TETROMINO_LENTH) {
            max = Math.max(max, total);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 && nextY >= 0 && nextX < row && nextY < column) {
                if (!isVisited[nextX][nextY]) {
                    isVisited[nextX][nextY] = true;
                    dfs(nextX, nextY, total + board[nextX][nextY], depth + 1);
                    isVisited[nextX][nextY] = false;
                }
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());

        board = new int[row][column];
        isVisited = new boolean[row][column];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < column; k++) {
                board[i][k] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
