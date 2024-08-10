import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P9202_minHeap {
	// 오른쪽, 왼쪽, 위, 아래, 대각선 오위, 오아, 왼아, 왼위
	static int[] moveX = { 1, -1, 0, 0, 1, 1, -1, -1 };
	static int[] moveY = { 0, 0, -1, 1, -1, 1, 1, -1 };

	static char[][] map;
	static boolean[][] isVisited;
	static Node root = new Node(false, false);

	static long score = 0;
	static String longestWord = "";
	static long wordCount = 0;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// read dictionary words
		int numbOfWords = Integer.parseInt(br.readLine());
		for (int i = 0; i < numbOfWords; i++) {
			String word = br.readLine().trim();
			insert(word);
		}
		// read maps
		br.readLine();
		int numbOfMaps = Integer.parseInt(br.readLine());
		map = new char[4][4];
		isVisited = new boolean[4][4];
		for (int m = 0; m < numbOfMaps; m++) {
			for (int i = 0; i < 4; i++) {
				String inputMap = br.readLine();
				for (int j = 0; j < 4; j++) {
					// map
					map[i][j] = inputMap.charAt(j);
					isVisited[i][j] = false;
				}
			}
			// 맵 읽어들이기 완료! 여기에 탐색 로직 추가
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					int cIndex = map[i][j] - 'A';
					if (root.children[cIndex] != null) {
						dfs(i, j, root.children[cIndex]);
					}
				}
			}
			bw.write(score +" "+ longestWord+ " "+wordCount+ "\n");
			// 다음 map으로...
			if (m == numbOfMaps - 1) break;
			score = 0;
			wordCount = 0;
			longestWord = "";
			root.clearHit();
			sb.setLength(0);
			br.readLine();
		}
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static int currentScore(String str) {
		switch(str.length()) {
			case 3:
			case 4:
				return 1;
			case 5:
				return 2;
			case 6:
				return 3;
			case 7:
				return 5;
			case 8:
				return 11;
			default:
				return 0;
		}
	}
	
	static boolean comesFirst(String str) {
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) -longestWord.charAt(i)<0) {
				return true;
			} else if (str.charAt(i) == longestWord.charAt(i)) {
				continue;
			} else break;
		}
		return false;
	}
	
	static void dfs (int y, int x, Node current) {
		// 1. check - in
		isVisited[y][x] = true;
		sb.append(map[y][x]);
		// 2. Destination?
		if (current.isWord && !current.isHit) {
			// add score // longest word? // add wordCount
			String str = sb.toString();
			current.isHit = true;
			int currentWordScore = currentScore(str);
			score += currentWordScore;
			if (longestWord.length() < str.length()) {
				longestWord = str;
			} else if (longestWord.length() == str.length()) {
				if (comesFirst(str)) {
					longestWord = str;
				}
			}
			wordCount++;
		}
		// 3. loop the neighbors
		// 4. 	can visit?
		// 5. 		go visit!
		for (int i = 0; i < 8 ;i++) {
			int ty = y + moveY[i];
			int tx = x + moveX[i];
			
			if (0 <= ty&& ty <= 3 && 0<= tx && tx <= 3 && !isVisited[ty][tx]) {
				// is in the map & not visited
				// is it in the dictionary?
				int charIndex = map[ty][tx] - 'A';
				if (current.children[charIndex] != null) {
					dfs(ty, tx, current.children[charIndex]);
				}
			}
		}
		// 6. check-out
		isVisited[y][x] = false;
		sb.deleteCharAt(sb.length()-1);
	}
	
	static void insert (String word) {
		Node current = root;
		for (int i = 0; i < word.length(); i++) {
			int charIndex = word.charAt(i) - 'A';
			if (current.children[charIndex] == null) {
				current.children[charIndex] = new Node(false, false);
			}
			current = current.children[charIndex];
		}
		current.isWord = true;
	}

}

class Node {
	boolean isWord;
	boolean isHit;
	Node[] children;

	public Node(boolean isWord, boolean isHit) {
		this.isWord = isWord;
		this.isHit = isHit;
		children = new Node[26];
	}

	@Override
	public String toString() {
		return "Node [isWord=" + isWord + ", isHit=" + isHit + ", children=" + Arrays.toString(children) + "]";
	}

	void clearHit() {
		isHit = false;
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null) {
				children[i].clearHit();
			}
		}
	}
}
