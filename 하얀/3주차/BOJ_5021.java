import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5021 {
    private static final Double KINGBLOOD = 1.0;
    private static List<String> candidates = new ArrayList<>(); // 후보
    private static Map<String, List<String>> info = new HashMap<>(); // 이름, 부모
    private static Map<String, Double> lineage = new HashMap<>(); // 이름, 혈통
    private static String closetPerson;

    public static void main(String[] args) throws IOException {
        input();
        topologicalSort();
        output();
    }

    private static void output() {
        System.out.println(closetPerson);
    }

    private static void topologicalSort() {

    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cntInfo = Integer.parseInt(st.nextToken());
        int cntCandidate = Integer.parseInt(st.nextToken());

        String king = br.readLine();
        lineage.put(king, KINGBLOOD);

        for (int i = 0; i < cntInfo; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String mommy = st.nextToken();
            String daddy = st.nextToken();

            if (info.get(child) == null) {
                info.put(child, new ArrayList<>());
            }
            info.get(child).add(mommy);
            info.get(child).add(daddy);
        }

        for (int i = 0; i < cntCandidate; i++) {
            candidates.add(br.readLine());
        }
    }
}
