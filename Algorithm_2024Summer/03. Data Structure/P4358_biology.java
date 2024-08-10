import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P4358_biology {
	static List<String> list = new ArrayList<>();
	static Map<String, Integer> trees = new HashMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		int numbOfTrees = 0;
		while(s != null && !s.isEmpty()) {
			numbOfTrees ++;
			trees.put(s, (trees.get(s) == null)? 1: trees.get(s)+1);
			s = br.readLine();
		}
		
		List <String> treeNames = new ArrayList<>(trees.keySet());
		Collections.sort(treeNames);

		for (String tree: treeNames) {
			double percentage = (Math.round((trees.get(tree)*1000000.0)/numbOfTrees))/10000.0;
			String percent = String.format("%.4f", percentage);
			sb.append(tree).append(" ").append(percent).append("\n");
		}
		System.out.print(sb.toString());
	}
}
