package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _14500 {
    private static final String BLANK = " ";
    private static final int MAX_COUNT = 4;
    private static final int T = 2;
    private static final int[] dx = new int[]{0, -1, 0, 1};
    private static final int[] dy = new int[]{-1, 0, 1, 0};
    static BufferedReader br = bufferedReader();
    static StringTokenizer st;
    static int[][] board;
    static boolean[][] visited;
    static int n, m;
    static int maximum = 0;

    public static void main(String[] args) throws IOException {
        initStatics();
        solution();
        System.out.println(maximum);
    }

    private static void solution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, board[i][j], 1);
                visited[i][j] = false;
            }
        }
    }

    private static void dfs(int x, int y, int sum, int count) {
        if (count == MAX_COUNT) {
            maximum = Math.max(maximum, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isAccessible(nx, ny)) {
                if (count == T) {
                    visited[nx][ny] = true;
                    dfs(x, y, sum + board[nx][ny], count + 1);
                    visited[nx][ny] = false;
                }
                visited[nx][ny] = true;
                dfs(nx, ny, sum + board[nx][ny], count + 1);
                visited[nx][ny] = false;
            }
        }
    }

    private static boolean isAccessible(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m && !visited[x][y];
    }

    private static void initStatics() throws IOException {
        st = stringTokenizer();
        n = nextToken(st);
        m = nextToken(st);

        visited = new boolean[n][m];
        board = new int[n][];
        for (int i = 0; i < n; i++) {
            board[i] = readInputs();
        }
    }

    private static int[] readInputs() throws IOException {
        String inputs = br.readLine();
        return Arrays.stream(inputs.split(BLANK))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int nextToken(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }

    private static StringTokenizer stringTokenizer() throws IOException {
        return new StringTokenizer(br.readLine());
    }

    private static BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
