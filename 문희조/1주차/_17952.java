package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class _17952 {
    private static final int NEW_TASK = 1;
    private static final int FINISHED = 0;
    static BufferedReader br = bufferedReader();
    static Deque<Integer> stack = new ArrayDeque<>();
    static StringTokenizer st;
    static int n;
    static int[] scores;
    static int[] times;

    public static void main(String[] args) throws IOException {
        initStatics();
        int totalScore = calculateScore();
        System.out.println(totalScore);
    }

    private static int calculateScore() throws IOException {
        int sum = 0;
        int currentTask = 0;
        for (int i = 1; i <= n; i++) {
            st = stringTokenizer();
            if (isNewTask(st)) {
                scores[i] = nextToken(st);
                times[i] = nextToken(st);
                stack.addLast(currentTask);
                currentTask = i;
            }
            times[currentTask]--;
            if (isTaskFinished(currentTask)) {
                sum += scores[currentTask];
                currentTask = popStack();
            }
        }
        return sum;
    }

    private static int popStack() {
        return stack.isEmpty() ? 0 : stack.removeLast();
    }

    private static boolean isTaskFinished(int currentTask) {
        return times[currentTask] == FINISHED;
    }

    private static boolean isNewTask(StringTokenizer st) {
        return nextToken(st) == NEW_TASK;
    }

    private static void initStatics() throws IOException {
        n = readInput();
        scores = new int[n+1];
        times = new int[n+1];
    }

    private static int nextToken(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }

    private static int readInput() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private static StringTokenizer stringTokenizer() throws IOException {
        return new StringTokenizer(br.readLine());
    }

    private static BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
