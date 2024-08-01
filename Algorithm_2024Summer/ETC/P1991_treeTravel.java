
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1991_treeTravel {

    static StringBuilder sb = new StringBuilder();
    static Node[] tree = new Node [26];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        


        for (int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char data = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            int leftIndex = (left == '.')? -1 : left - 'A';
            int rightIndex = (right == '.')? -1 : right - 'A';

            tree[data - 'A'] = new Node (data, leftIndex, rightIndex);
        }

        preOrder(0);
        sb.append("\n");
        inOrder(0);
        sb.append("\n");
        postOrder(0);

        System.out.println(sb);
    }

    static void preOrder(int index){
        if (index == -1) return;
        sb.append(tree[index].data);
        preOrder(tree[index].leftIndex);
        preOrder(tree[index].rightIndex);
    }

    static void inOrder (int index){
        if (index == -1) return;
        inOrder(tree[index].leftIndex);
        sb.append(tree[index].data);
        inOrder(tree[index].rightIndex);
    }

    static void postOrder (int index){
        if (index == -1) return;
        postOrder(tree[index].leftIndex);
        postOrder(tree[index].rightIndex);
        sb.append(tree[index].data);
    }
}

class Node {
    char data;
    int leftIndex;
    int rightIndex;

    Node (char data, int leftIndex, int rightIndex){
        this.data = data;
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
    }
}