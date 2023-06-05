import java.io.*;
import java.util.StringTokenizer;

public class BOJ_21318 {
    private static int countSheet; // 악보 수
    private static int countInterval; // 질문 구간 수
    private static int[] difficulty; // 난이도 배열
    private static int[] cumulativeMistake; // 누적합 배열
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        input();
        parseToCumulative();
        output();
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < countInterval; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) - 1;

            int result = cumulativeMistake[end] - cumulativeMistake[start - 1];
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }

    // 난이도 배열을 누적 합 배열로 바꾼다.
    private static void parseToCumulative() {
        cumulativeMistake = new int[countSheet + 1];
        for (int i = 1; i < countSheet; i++) {
            int mistake = 0;
            if (difficulty[i] > difficulty[i + 1]) mistake = 1;
            cumulativeMistake[i] = cumulativeMistake[i - 1] + mistake;
        }
    }

    private static void input() throws IOException {
        countSheet = Integer.parseInt(br.readLine());

        difficulty = new int[countSheet + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < countSheet; i++) {
            difficulty[i + 1] = Integer.parseInt(st.nextToken());
        }

        countInterval = Integer.parseInt(br.readLine());
    }
}
