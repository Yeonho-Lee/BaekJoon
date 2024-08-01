
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2357_minAndMax {
    static int N;
    static int S;
    static int[] minTree;
    static int[] maxTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // N, M
        N = Integer.parseInt(st.nextToken());
        int numbOfOps = Integer.parseInt(st.nextToken());

        S = 1;
        while (S < N){
            S *= 2;
        }

        minTree = new int [S * 2];
        maxTree = new int [S * 2];
        for (int i = 0; i < S * 2; i++){
            minTree[i] = Integer.MAX_VALUE;
        }

        // System.out.println(Arrays.toString(minTree));
        // System.out.println(Arrays.toString(maxTree));
        for (int i = 0; i < N; i++){
            int input = Integer.parseInt(br.readLine());
            updateMin(1, S, 1, i+1, input);
            updateMax(1, S, 1, i+1, input);
        }
        // System.out.println(Arrays.toString(minTree));
        // System.out.println(Arrays.toString(maxTree));
        for (int i = 0; i < numbOfOps; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            sb.append(queryMin(1, S, 1, from, to)).append(" ");
            sb.append(queryMax(1, S, 1, from, to)).append("\n");
        }

        System.out.print(sb);
    }


    static int queryMin(int left, int right, int node, int queryLeft, int queryRight){
        if (right < queryLeft || queryRight < left){
            // 상관없음
            return Integer.MAX_VALUE;
        } else if (queryLeft <= left && right <= queryRight){
            return minTree[node];
        } else {
            int mid = (left + right) / 2;
            return Math.min(queryMin(left, mid, node * 2, queryLeft, queryRight),
                            queryMin(mid + 1, right, node * 2 + 1, queryLeft, queryRight));
        }
    }

    static int queryMax(int left, int right, int node, int queryLeft, int queryRight){
        if (right < queryLeft || queryRight < left){
            // 상관없음
            return 0;
        } else if (queryLeft <= left && right <= queryRight){
            return maxTree[node];
        } else {
            int mid = (left + right) / 2;
            return Math.max(queryMax(left, mid, node * 2, queryLeft, queryRight),
                            queryMax(mid + 1, right, node * 2 + 1, queryLeft, queryRight));
        }
    }
    
    static void updateMin(int left, int right, int node, int target, int input){
        if (target < left || right < target){
            // 상관없음
        } else {
            int mid = (left + right)/2;
            minTree[node] = Math.min(minTree[node], input);
            if (left != right){
                updateMin(left, mid, node * 2, target, input);
                updateMin(mid + 1, right, node * 2 + 1, target, input);
            }
        }
    }
    
    static void updateMax(int left, int right, int node, int target, int input){
        if (target < left || right < target){
            // 상관없음
        } else {
            int mid = (left + right)/2;
            maxTree[node] = Math.max(maxTree[node], input);
            if (left != right){
                updateMax(left, mid, node * 2, target, input);
                updateMax(mid + 1, right, node * 2 + 1, target, input);
            }
        }
    }
}
