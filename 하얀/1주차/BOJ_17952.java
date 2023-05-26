import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17952 {
    private static int totalScore = 0;
    static class Assignment {
        private int score;
        private int time;

        public Assignment(int score, int time) {
            this.score = score;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        solve();
        output();
    }

    private static void output() {
        System.out.println(totalScore);
    }

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int minute = Integer.parseInt(br.readLine());

        Stack<Assignment> stack = new Stack<>();
        StringTokenizer st;

        for (int i = 0; i < minute; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());

            // 과제가 없는 시간
            if (check == 0) {
                if (stack.isEmpty()) continue;

                Assignment current = stack.pop();
                if (current.time - 1 == 0) totalScore += current.score;
                else stack.push(new Assignment(current.score, current.time - 1));
            }

            // 과제가 있는 시간
            else {
                int score = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                if (time == 1) {
                    totalScore += score;
                } else {
                    stack.push(new Assignment(score, time - 1));
                }
            }
        }
    }
}
