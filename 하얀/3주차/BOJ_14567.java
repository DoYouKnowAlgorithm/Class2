import java.io.*;
import java.util.*;

public class BOJ_14567 {
    private static int cntSubject;
    private static List<List<Integer>> graph;
    private static int[] degree;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        input();
        topologicalSort();
        output();
    }

    private static void topologicalSort() {
        result = new int[cntSubject + 1];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= cntSubject; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
                result[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i : graph.get(current)) {
                degree[i]--;
                if (degree[i] == 0) {
                    queue.offer(i);
                    result[i] = result[current] + 1;
                }
            }
        }
    }

    private static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= cntSubject; i++) {
            bw.write(result[i] + " ");
        }
        bw.flush();
        bw.close();
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        cntSubject = Integer.parseInt(st.nextToken());
        int cntEdge = Integer.parseInt(st.nextToken());

        degree = new int[cntSubject + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= cntSubject; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < cntEdge; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            graph.get(first).add(second);
            degree[second]++;
        }
    }
}
