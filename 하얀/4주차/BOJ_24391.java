import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24391 {
    private static int countLecture;
    private static int[] parent;
    private static int answer = 0;
    private static String schedule;

    public static void main(String[] args) throws IOException {
        input();
        findAnswer();
        output();
    }

    private static void output() {
        System.out.println(answer);
    }

    private static void findAnswer() {
        StringTokenizer st = new StringTokenizer(schedule);
        int firstLecture = Integer.parseInt(st.nextToken());
        int start = findParent(firstLecture);

        while (st.hasMoreTokens()) {
            int lecture = Integer.parseInt(st.nextToken());
            int parent = findParent(lecture);
            if (parent != start) {
                answer++;
                start = parent;
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        countLecture = Integer.parseInt(st.nextToken());
        int countLink = Integer.parseInt(st.nextToken());
        parent = new int[countLecture + 1];

        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < countLink; i++) {
            st = new StringTokenizer(br.readLine());
            int firstBuilding = Integer.parseInt(st.nextToken());
            int secondBuilding = Integer.parseInt(st.nextToken());

            union(firstBuilding, secondBuilding);
        }

        schedule = br.readLine();
    }

    private static void union(int firstBuilding, int secondBuilding) {
        firstBuilding = findParent(firstBuilding);
        secondBuilding = findParent(secondBuilding);

        if (firstBuilding == secondBuilding) return;

        if (firstBuilding <= secondBuilding) parent[secondBuilding] = firstBuilding;
        else parent[firstBuilding] = secondBuilding;
    }

    private static int findParent(int firstBuilding) {
        if (parent[firstBuilding] == firstBuilding) return firstBuilding;
        return findParent(parent[firstBuilding]);
    }
}
