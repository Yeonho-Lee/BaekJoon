import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// The main method must be in a class named "Main".
public class P1759_makingPassword {
	
	static int L, C;
	static char[] c;
	//static boolean[] visited;
	static int strLength = 0;
	static int vowelCount = 0;
	static int consonantCount = 0;
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		String[] input = br.readLine().trim().split(" ");
		L = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		input = br.readLine().trim().split(" ");
		c = new char[C];
		//visited = new boolean[C];
		for (int i = 0; i < C; i++) {
			c[i] = input[i].charAt(0);
		}
		Arrays.sort(c);
		
		for (int idx = 0; idx < C; idx++) {
			dfs(idx, "");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int idx, String str) throws Exception{
		// 1. check-in
		String newStr = str+c[idx];
		//visited[idx] = true;
		strLength ++;
		if (isVowel(c[idx])) {
			vowelCount++;
		} else {
			consonantCount ++;
		}
		// 2. is it destination?
		
		if (strLength == L && vowelCount > 0 && consonantCount > 1) {
			bw.write(newStr+"\n");
		}
		// 3. for loop
		for (int next = idx+1; next<C; next++) {
			// 4. 	can go?
			// 5. 		go!
			if (/*!visited[next] &&*/ newStr.length() < L) {
				dfs(next, newStr);
			}
		}
		// 6. check-out
		//visited[idx] = false;
		strLength--;
		if (isVowel(c[idx])) {
			vowelCount--;
		} else {
			consonantCount--;
		}
	}
	
	static boolean isVowel(char c) {
		if (c == 'a' || c=='e' || c=='i' || c=='o'||c=='u') {
			return true;
		}
		return false;
	}
}