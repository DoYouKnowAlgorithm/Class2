package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class _20440 {
    static BufferedReader br = bufferedReader();
    static StringTokenizer st;
    static List<Integer> compressed;
    static Time[] times;
    static int[] mosquitoes;
    static int n;

    static class Time {
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        Set<Integer> compression = initCompression();
        initMosquitoes(compression);
        solution();
    }

    private static void solution() {
        int max = 0;
        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < mosquitoes.length; i++) {
            if (mosquitoes[i] > max) {
                max = mosquitoes[i];
                startIndex = i;
                endIndex = i;
            }

            if (mosquitoes[i] == max && i - 1 == endIndex) {
                endIndex = i;
            }
        }

        System.out.println(max);
        System.out.println(compressed.get(startIndex) + " " + compressed.get(endIndex + 1));
    }

    private static void initMosquitoes(Set<Integer> compression) {
        compressed = new ArrayList<>(compression);
        mosquitoes = new int[compressed.size()];

        for (int i = 0; i < n; i++) {
            int start = Collections.binarySearch(compressed, times[i].start);
            int end = Collections.binarySearch(compressed, times[i].end);

            for (int j = start; j < end; j++) {
                mosquitoes[j] += 1;
            }
        }
    }

    private static Set<Integer> initCompression() throws IOException {
        n = readInput();
        times = new Time[n];
        Set<Integer> compression = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            st = stringTokenizer();
            int start = nextToken(st);
            int end = nextToken(st);

            times[i] = new Time(start, end);
            compression.add(start);
            compression.add(end);
        }
        
        return compression;
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
