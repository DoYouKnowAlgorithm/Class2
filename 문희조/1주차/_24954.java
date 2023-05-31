package Algorithm.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _24954 {
    static class Sale {
        int index;
        int discount;

        public Sale(StringTokenizer st) {
            this.index = nextToken(st) - 1;
            this.discount = nextToken(st);
        }
    }

    static BufferedReader br = bufferedReader();
    static int min = Integer.MAX_VALUE;
    static StringTokenizer st;
    static List<Sale>[] sales;
    static boolean[] visited;
    static int[] order;
    static int[] prices;
    static int n;

    public static void main(String[] args) throws IOException {
        initStatics();
        permutation(0);
        System.out.println(min);
    }

    private static void permutation(int count) {
        if (count == n) {
            calcMinPrice();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            order[count] = i;
            permutation(count + 1);
            order[count] = 0;
            visited[i] = false;
        }
    }

    private static void calcMinPrice() {
        int sum = 0;
        int[] price = prices.clone();

        for (int idx : order) {
            sum += price[idx];
            for (Sale sale : sales[idx]) {
                price[sale.index] -= sale.discount;
                if (price[sale.index] <= 0) {
                    price[sale.index] = 1;
                }
            }
        }

        min = Math.min(min, sum);
    }

    private static void initStatics() throws IOException {
        n = readInput();
        order = new int[n];
        visited = new boolean[n];
        initPrices();
        initSales();
    }

    private static void initSales() throws IOException {
        sales = new List[n];
        for (int i = 0 ; i < n ; i++) {
            List<Sale> tmp = new ArrayList<>();
            int m = readInput();

            if (m != 0) {
                for (int j = 0; j < m; j++) {
                    st = stringTokenizer();
                    tmp.add(new Sale(st));
                }
            }

            sales[i] = tmp;
        }
    }

    private static void initPrices() throws IOException {
        prices = new int[n];
        st = stringTokenizer();
        for (int i = 0; i < n; i++) {
            prices[i] = nextToken(st);
        }
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
