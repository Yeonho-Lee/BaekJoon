import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P2243_candyBox {
	
    static int S; // indexed tree를 만들기 위한
    static int N; // 사탕상자에 손을 댄 횟수
    static int[] tree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		S = 1;
        while (S<1000000){
            S *= 2;
        }
        tree = new int[S * 2];
        
        init();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int action = Integer.parseInt(st.nextToken());
			if (action == 1) {
				// 순위를 받아서 꺼냄
				int ranking = Integer.parseInt(st.nextToken());
				int candy = query(1, S, 1, ranking);
                update(1, S, 1, candy, -1);
                bw.write(candy + "\n");
			} else {
				// 넣거나 꺼냄, 맛과 개수 받음, 개수는 정수
				int flavor = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                update(1, S, 1, flavor, count);
			}
		}
        bw.flush();
        bw.close();
        br.close();		
	}
	
	static void init() {
        // bottomUp
		// 처음 상태가 빈 상자임
        for (int i = 0; i < tree.length; i++){
            tree[i] = 0;
        }
	}
	
	static int query(int left, int right, int node, int ranking) {
		int leftChild = node * 2;
        if (leftChild > tree.length - 1){
            // leaf node, return
            return left;
        }
        int mid = (left + right) / 2;
        if (tree[leftChild] >= ranking){
            return query(left, mid, node * 2, ranking);
        } else {
            return query(mid + 1, right, node * 2 + 1, ranking-tree[leftChild]);
        }
	}
	
	static void update(int left, int right, int node, int target, int diff) {
		if (target < left || right < target){
            // 상관 없는 경우
        } else {
            tree[node] += diff;
            if (left != right){
                int mid = (left+right)/2;
                update(left, mid, node * 2, target, diff);
                update(mid + 1, right, node * 2 + 1, target, diff);  
            }          
        }
	}

}
