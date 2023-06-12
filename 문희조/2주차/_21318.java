package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _21318 {
    static BufferedReader br = bufferedReader();
    static StringTokenizer st;
    static int[] difficulties;
    static int[] mistakes;
    static int[] prefix;
    static int n, m;

    public static void main(String[] args) throws IOException {
        initStatics();
        solution();
    }

    private static void solution() throws IOException {
        m = readInput();
        for (int i = 0; i < m; i++) {
            st = stringTokenizer();
            int start = nextToken(st);
            int end = nextToken(st);

            int result = prefix[end] - prefix[start - 1];
            if (result > 0 && mistakes[end] == 1) {
                result--;
            }
            System.out.println(result);
        }
    }

    private static void initStatics() throws IOException {
        n = readInput();
        difficulties = new int[n + 1];
        mistakes = new int[n + 1];
        prefix = new int[n + 1];

        st = stringTokenizer();
        for (int i = 1; i <= n; i++) {
            difficulties[i] = nextToken(st);
            mistakes[i - 1] = (difficulties[i - 1] > difficulties[i]) ? 1 : 0;
        }

        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + mistakes[i];
        }
    }

    private static int nextToken(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }

    private static StringTokenizer stringTokenizer() throws IOException {
        return new StringTokenizer(br.readLine());
    }

    private static int readInput() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private static BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
