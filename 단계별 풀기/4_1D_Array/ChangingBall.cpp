#include <iostream>
#include <vector>
using namespace std;

int main() {
    // 학생 배열 초기화
    vector<int> students(30);
    for (int i = 0; i < students.size(); i++) {
        students[i] = i + 1;
    }
    // 입력 처리
    for (int i = 0; i < 28; i++) {
        int index;
        cin >> index;
        students[index - 1] = 0;
    }
    // 출력 처리
    for (int i = 0; i < students.size(); i++) {
        if (students[i] != 0) {
            cout << students[i] << endl;
        }
    }
    return 0;
}
