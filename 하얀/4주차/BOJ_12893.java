import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12893 {
    private static int[] enemy;
    private static int countPerson;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void solve() {
        
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        countPerson = Integer.parseInt(st.nextToken());
        int countRelation = Integer.parseInt(st.nextToken());
        enemy = new int[countPerson + 1];
        for (int i = 1; i <= countPerson; i++) {
            enemy[i] = i;
        }

        for (int i = 0; i < countRelation; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            union(first, second);
        }
    }

    private static void union(int first, int second) {
        first = findEnemy(first);
        second = findEnemy(second);

        if (first == second) return;

        if (first <= second) enemy[second] = first;
        else enemy[first] = second;
    }

    private static int findEnemy(int first) {
        if (enemy[first] == first) return first;
        return findEnemy(enemy[first]);
    }
}
