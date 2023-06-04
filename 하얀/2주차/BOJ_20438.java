import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20438 {
    private static final int MIN_NUMBER = 3; // 출석번호가 3번부터 시작
    private static final int NOT_ATTEND = 1; // 출석 안 함
    private static final int ATTEND = 0; // 출석 함

    private static int totalStudents; // 총 학생 수
    private static int countSleepStudents; // 조는 학생 수
    private static int codeReceiveStudents; // 출석코드를 받을 학생 수
    private static int intervalCount; // 구간 수

    private static boolean[] isReceiveCode; // 코드를 받는 학생만 true
    private static int[] totalNotAttend; // 누적합 배열
    private static List<Integer> sleepStudentsNumber; // 조는 학생 번호

    public static void main(String[] args) throws IOException {
        run();
    }

    private static void run() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        totalStudents = Integer.parseInt(st.nextToken());
        countSleepStudents = Integer.parseInt(st.nextToken());
        codeReceiveStudents = Integer.parseInt(st.nextToken());
        intervalCount = Integer.parseInt(st.nextToken());

        isReceiveCode = new boolean[totalStudents + MIN_NUMBER];
        totalNotAttend = new int[totalStudents + MIN_NUMBER];
        sleepStudentsNumber = new ArrayList<>();

        // 조는 학생 번호를 리스트에 담아준다.
        // 출석 코드를 받는 학생 중에 조는 학생이 있는지 판단하기 위한 용도
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int number = Integer.parseInt(st.nextToken());
            sleepStudentsNumber.add(number);
        }

        // 만약 조는 학생에 포함되지 않는다면 출석코드를 받는다고 체크하고 sendCode() 호출한다.
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int number = Integer.parseInt(st.nextToken());

            if (sleepStudentsNumber.contains(number)) {
                continue;
            } else {
                isReceiveCode[number] = true;
                sendCode(number);
            }
        }

        // 누적합을 구한다.
        // 이전 값 + 현재 학생의 출석 여부에 따라 0 혹은 1을 더해준다.
        for (int i = MIN_NUMBER; i < totalStudents + MIN_NUMBER; i++) {
            int isAttend = ATTEND;
            if (!isReceiveCode[i]) isAttend = NOT_ATTEND;
            totalNotAttend[i] = totalNotAttend[i - 1] + isAttend;
        }

        // 구간별로 출석 안한 학생 수 출력
        for (int i = 0; i < intervalCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            bw.write(totalNotAttend[end] - totalNotAttend[start - 1] + "\n");
        }

        bw.flush();
        bw.close();
    }

    // 최초의 출석코드 받은 학생이 배수 번호 학생들에게 전달하는 메서드
    private static void sendCode(int number) {
        int i = 2;
        while (number * i < totalStudents + MIN_NUMBER) {
            int next = number * i++;
            if (!sleepStudentsNumber.contains(next)) {
                isReceiveCode[next] = true;
            }
        }
    }
}