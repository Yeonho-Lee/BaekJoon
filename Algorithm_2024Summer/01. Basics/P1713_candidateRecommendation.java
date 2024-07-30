import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P1713_candidateRecommendation {
	
	static int[] votes = new int [101];
	static PhotoFrame[] PhotoFrames;
	static PhotoFrame[] students = new PhotoFrame[101];
	

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int R = Integer.parseInt(br.readLine());
		String [] input = br.readLine().trim().split(" "); 
		PhotoFrames = new PhotoFrame[N];
		// input needs Integer.parseInt
		
		for (int iter = 0; iter < N; iter++) {
			PhotoFrames[iter] = new PhotoFrame(-1, -1, -1);
		}
		for (int iter = 0; iter < R; iter++) {
			int recommendation = Integer.parseInt(input[iter]);
			putStudent(recommendation, iter);
			//System.out.println(Arrays.toString(PhotoFrames));
		}
		Arrays.sort(PhotoFrames, (o1, o2)->o1.student-o2.student);
		//System.out.println(Arrays.toString(PhotoFrames));
		
		for (int iter = 0; iter < N; iter++) {
			// 없는 경우 처리
			if (PhotoFrames[iter].student == -1) continue;
			bw.write(PhotoFrames[iter].student+ " ");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void putStudent(int recommendation, int time) {
		if (students[recommendation] != null && students[recommendation].count != 0) {
			// 있다면?
			// recommendation 번호의 학생이 게시된 사진의 count ++
			students[recommendation].count++;
		} else {
			if (PhotoFrames[0].count == -1) {
				// 빈 곳 존재 => 거기 게시
			} else {
				// 추천받은 횟수가 가장 적은, 중복이라면 가장 오래된 사진 삭제 => 게시
				// 내려간 학생의 추천 수 초기화	
				PhotoFrames[0].count = 0;
			}
			PhotoFrames[0] = new PhotoFrame(recommendation, 1, time);
			students[recommendation] = PhotoFrames[0];
		}
		// sort
		Arrays.sort(PhotoFrames);
	}
}

class PhotoFrame implements Comparable<PhotoFrame>{
	int student;
	int count;
	int timeStamp;
	
	public PhotoFrame(int student, int count, int timeStamp) {
		this.student = student;
		this.count = count;
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "PhotoFrame [st=" + student + ", c=" + count + ", t=" + timeStamp + "]";
	}
	@Override
	public int compareTo(PhotoFrame o2) {
		// count, timestamp - count 정렬 => 동률이 있으면 timestamp순으로 결정	
		int recommendation = count - o2.count;
		if (recommendation == 0) {
			return timeStamp - o2.timeStamp;
		}
		return recommendation;
	}
}
