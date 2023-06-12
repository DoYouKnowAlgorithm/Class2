package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class _20438 {
    private static final int ENTRANCE_GAP = 2;
    private static final int CHECKED = 0;
    private static final int UNCHECKED = 1;
    static BufferedReader br = bufferedReader();
    static StringTokenizer st;
    static int n, k, q, m;
    static List<Integer> sleepings;
    static List<Integer> checks;
    static int[] students;
    static int[] attendances;

    public static void main(String[] args) throws IOException {
        initCondition();
        initSleeping();
        initCheck();
        calculatePrefix();
        solution();
    }

    private static void solution() throws IOException {
        for (int i = 0; i < m; i++) {
            int count = countAttendance();
            System.out.println(count);
        }
    }

    private static void calculatePrefix() {
        for (int i = 1; i <= n + ENTRANCE_GAP; i++) {
            attendances[i] = attendances[i - 1] + students[i];
        }
    }

    private static int countAttendance() throws IOException {
        st = stringTokenizer();
        int s = nextToken(st);
        int e = nextToken(st);
        return attendances[e] - attendances[s - 1];
    }

    private static void initCheck() throws IOException {
        checks = new ArrayList<>();
        st = stringTokenizer();
        for (int i = 0; i < q; i++) {
            checks.add(nextToken(st));
        }
        checkAttendance();
        checkSleepingsAttendance();
    }

    private static void checkSleepingsAttendance() {
        for (int sleeping : sleepings) {
            students[sleeping] = UNCHECKED;
        }
    }

    private static void checkAttendance() {
        for (int check : checks) {
            if (sleepings.contains(check)) continue;
            int attendanceRequest = check;
            int times = 2;
            while (attendanceRequest <= n + ENTRANCE_GAP) {
                students[attendanceRequest] = CHECKED;
                attendanceRequest = check * times++;
            }
        }
    }

    private static void initSleeping() throws IOException {
        sleepings = new ArrayList<>();
        st = stringTokenizer();
        for (int i = 0; i < k; i++) {
            sleepings.add(nextToken(st));
        }
    }

    private static void initCondition() throws IOException {
        st = stringTokenizer();
        n = nextToken(st);
        students = new int[n + ENTRANCE_GAP + 1];
        Arrays.fill(students, 1);
        attendances = new int[n + ENTRANCE_GAP + 1];

        k = nextToken(st);
        q = nextToken(st);
        m = nextToken(st);
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
