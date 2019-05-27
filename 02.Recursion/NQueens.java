/* N-Queens
  : N * N 체스판에 N개의 퀸을 서로 다른 대각선, 직선 상에 놓을 수 있는가?

  [BackTracking]
  1) 이전 말들과 충돌하면 즉시 탐색을 멈추고 돌아감
  2) 충돌이 없다면 내가 찾고 있는 답인지 확인 후 리턴
  3) 충돌도 없고 답도 아니라면 자식 노드들을 계속 탐색
*/

// level : 현재 노드의 레벨 (현재까지 놓인 말의 개수를 의미)
// cols  : level번째 말이 어디에 놓여있는지 표시 (cols[i] = j : level(i)번째 말이 (i행, j열)에 놓여 있음)

public class NQueens {
    private final static int N = 8;
    // 현재 노드 정보 1) 매개변수 2) 전역변수
    private static int[] cols = new int[N + 1];   // 전역변수 
    public static void main(String[] args) {
        queens(0);
    }

    // * 자식 노드들을 Recursive하게 탐색
    public static boolean queens(int level) {
        if(!promising(level))
            // 1) 충돌 : 탐색 멈추고 false 리턴
            return false;
        else if(level == N) {
            // 2) 충돌이 없고, 모든 말이 놓였으면 true 리턴
            for(int i = 1; i <= N; i++) {
                System.out.println("(" + i + ", " + cols[i] + ")");
            }
            return true;
        }
        // 3) level+1번째 말을 첫번째 ~ N번째 열에 놓아보며 탐색
        for(int j = 1; j <= N; j++)  {
            cols[level + 1] = j;
            if(queens(level + 1))
                return true;
        }
        return false;
    }   

    // * level번째 말이 이전(1 ~ level-1)에 놓인 말과 충돌하는지 체크
    public static boolean promising(int level) {    
        for(int i = 1; i < level; i++) {
            if(cols[i] == cols[level])  
                // 같은 열인지 체크
                return false;
            else if(level - i == Math.abs(cols[level] - cols[i]))
                // 같은 대각선인지 체크
                return false;
        }
        return true;
    }
}