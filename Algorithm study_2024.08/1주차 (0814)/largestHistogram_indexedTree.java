import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class largestHistogram_indexedTree {

    static int N, S;
    static int[] histogram, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0)
                break;
            histogram = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                histogram[i] = Integer.parseInt(st.nextToken());
            }
            S = 1;
            while (S < N) {
                S = S << 1;
            }
            tree = new int[S * 2];

            init(1, N, 1);
            sb.append(findMaxArea(1, N)).append("\n");
        }
        System.out.println(sb.toString());
    }

    /*
     * indexed tree를 초기 세팅
     * 저장될 값? start ~ end 범위에서 가장 작은 높이를 가진 인덱스
     */
    static int init(int start, int end, int node) {
        // top-down 방식으로 구현해본 init
        if (start == end)
            return tree[node] = start;

        int mid = (start + end) / 2;
        int left = init(start, mid, node * 2);
        int right = init(mid + 1, end, node * 2 + 1);

        // 둘 중 작은 것의 index return
        return tree[node] = (histogram[left] > histogram[right]) ? right : left;
    }

    /*
     * start ~ end 범위에서 최대 넓이를 구하는 메소드
     * 최소 높이를 기준으로 양 옆으로 탐색하며 최대값을 갱신한다.
     */
    static long findMaxArea(int start, int end) {
        int minIndex = findMinHeight(1, N, 1, start, end);
        long maxArea = ((long) end - start + 1) * histogram[minIndex];

        // 최소 높인 인덱스 왼쪽에 히스토그램이 존재하면
        // 해당 범위에서의 최대 넓이를 구해 비교 - 재귀적으로
        if (start < minIndex) {
            maxArea = Math.max(maxArea, findMaxArea(start, minIndex - 1));
        }

        if (end > minIndex) {
            maxArea = Math.max(maxArea, findMaxArea(minIndex + 1, end));
        }

        return maxArea;
    }

    /*
     * start ~ end 범위에서 가장 작은 높이를 가진 인덱스를 찾는 메소드
     */
    static int findMinHeight(int start, int end, int node, int left, int right) {
        if (right < start || end < left)
            return -1;
        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;
        int mLeft = findMinHeight(start, mid, node * 2, left, right);
        int mRight = findMinHeight(mid + 1, end, node * 2 + 1, left, right);

        if (mLeft == -1)
            return mRight;
        if (mRight == -1)
            return mLeft;
        return (histogram[mLeft] > histogram[mRight]) ? mRight : mLeft;
    }
}
