package lowerBound;

public class BinarySearch_Recursive {
    public static void main(String[] args) {
        /* 문제에 주어진 값을 입력 받고
         * 입력받을 값을 인자로 이진탐색 메서드를 호출하여
         * 반환된 값을 출력하는 부분 */
    }

    // while문으로 Lower Bound 구현을 먼저 학습했다는 가정하에 차이점만 보자.
    private static int BinarySearch_LowerBound(int[] sortedNums, int left, int right, int target) {
        // [ mid 선언 및 초기화 ]
        // left와 right는 while문과는 달리 매개변수(인자)로 받아야 한다.
        int mid = left + (right - left) / 2;

        // [ 재귀의 종료 조건 ]
        // 종료 조건 : 성립 조건
        if (left <= right) {
            return right;
        }

        // [ 재귀 호출 및 구간 조정 ]
        if (isLowerBound(sortedNums, mid, target)) {
            return BinarySearch_LowerBound(sortedNums, left, mid, target);
        } else {
            return BinarySearch_LowerBound(sortedNums, mid + 1, right, target);
        }
    }

    // [ 조건 구현 부분 ]
    private static boolean isLowerBound(int[] sortedNums, int mid, int target) {
        if (sortedNums[mid] >= target) {
            return true;
        }
        return false;
    }
}