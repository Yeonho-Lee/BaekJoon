import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1202_jewelThief {

	static long[] bags;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparing(Jewel::getValue).reversed());
		Jewel[] jewels;
		long result = 0;

		// 가방 오름차순 정렬
		// 보석 무게 오름차순 정렬
		// 가방을 순회
		// 가방에 넣을 수 있는 보석을 pq에 넣음
		// 이때 pq의 top의 의미는 가방에 넣을 수 있는 가장 비싼 보석.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int numbOfJewels = Integer.parseInt(st.nextToken());
		int numbOfBags = Integer.parseInt(st.nextToken());
		int jewelWeight;
		int jewelValue;
		jewels = new Jewel[numbOfJewels];
		bags = new long[numbOfBags];

		for (int j = 0; j < numbOfJewels; j++) {
			st = new StringTokenizer(br.readLine());
			jewelWeight = Integer.parseInt(st.nextToken());
			jewelValue = Integer.parseInt(st.nextToken());
			jewels[j] = new Jewel(jewelWeight, jewelValue);
		}

		for (int bag = 0; bag < numbOfBags; bag++) {
			bags[bag] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(jewels, (o1, o2) -> o1.weight - o2.weight);
		Arrays.sort(bags);

		int j = 0;
		for (int b = 0; b < numbOfBags; b++) {
			long bag = bags[b];
			while (j < jewels.length && jewels[j].getWeight() <= bag) {
				pq.add(jewels[j++]);
			}
			if (!pq.isEmpty()) {
				Jewel intoBag = pq.poll();
				result += intoBag.getValue();
			}
		}
		System.out.println(result);
	}

}

class Jewel {
	int weight;
	int value;

	public Jewel(int weight, int value) {

		this.weight = weight;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Jewel [weight=" + weight + ", value=" + value + "]";
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
