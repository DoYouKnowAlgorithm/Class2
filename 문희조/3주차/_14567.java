package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class _14567 {
    static BufferedReader br = bufferedReader();
    static StringTokenizer st;
    static List<List<Integer>> graph;
    static int[] inDegree;
    static int n, m;

    public static void main(String[] args) throws IOException {
        initInputs();
        initGraph();
        initInDegree();
        int[] result = topologySort();

        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    private static int[] topologySort() {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int year = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                Integer node = queue.poll();
                result[node] = year;

                for (Integer pre : graph.get(node)) {
                    inDegree[pre]--;

                    if (inDegree[pre] == 0) {
                        queue.add(pre);
                    }
                }
            }

            year++;
        }

        return result;
    }

    private static void initInDegree() throws IOException {
        inDegree = new int[n];
        for (int i = 0; i < m; i++) {
            st = stringTokenizer();
            int pre = nextToken(st) - 1;
            int post = nextToken(st) - 1;

            graph.get(pre).add(post);
            inDegree[post]++;
        }
    }

    private static void initGraph() {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    private static void initInputs() throws IOException {
        st = stringTokenizer();
        n = nextToken(st);
        m = nextToken(st);
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
