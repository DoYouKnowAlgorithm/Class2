import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17352 {
    private static int countIsland;
    private static int[] parent;
    private static int answer1, answer2;

    public static void main(String[] args) throws IOException {
        input();
        findNotConnected();
        output();
    }

    private static void output() {
        System.out.println(answer1 + " " + answer2);
    }

    private static void findNotConnected() {
        answer1 = 1;
        for (int i = 2; i < parent.length; i++) {
            if (findParent(i) != answer1) {
                answer2 = i;
                break;
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        countIsland = Integer.parseInt(br.readLine());

        parent = new int[countIsland + 1];
        for (int i = 1; i <= countIsland; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < countIsland - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int firstIsland = Integer.parseInt(st.nextToken());
            int secondIsland = Integer.parseInt(st.nextToken());

            union(firstIsland, secondIsland);
        }
    }

    private static void union(int firstIsland, int secondIsland) {
        firstIsland = findParent(firstIsland);
        secondIsland = findParent(secondIsland);

        if (firstIsland == secondIsland) return;

        if (firstIsland <= secondIsland) parent[secondIsland] = firstIsland;
        else parent[firstIsland] = secondIsland;
    }

    private static int findParent(int island) {
        if (parent[island] == island) return island;
        return findParent(parent[island]);
    }
}
