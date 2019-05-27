/* Powers 멱집합
  : 임의의 집합의 모든 부분집합 구하기
    (전체에서 크기가 1 줄어든 집합의 모든 부분집합을 찾을 수 있으면
     원래 집합의 모든 부분집합도 찾을 수 있음)
  
  ex) {a, b, c, d, e, f}의 모든 부분집합을 나열하려면
  1) a를 제외한 {b, c, d, e, f}의 모든 부분집합을 나열하고
  2) a를 제외한 {b, c, d, e, f}의 모든 부분집합(1)에 {a}를 추가한 집합들을 나열
      => 2-1) b를 제외한 {c, d, e, f}의 모든 부분집합들에 {a}를 추가한 집합 나열
      => 2-2) b를 제외한 {c, d, e, f}의 모든 부분집합들에 {a, b}를 추가한 집합들 나열
      ...
*/


/* 
   S : k번째부터 마지막 원소까지 연속된 원소들 (data[k], ..., data[n-1])
   P : 처음부터 k-1번째 원소들 중 일부 (include[i]=true (i=0,...,k)인 원소들)
   include : 처음부터 k-1번째 원소들 중 어떤 원소들이 P에 속하는지 나타냄

   * powerSet(P, S)  : S의 멱집합을 구한 후, 각각에 P를 합집화하여 출력
   - S가 공집합이면 P를 출력
   - S가 공집합이 아니면, (1) powerSet(P, S - {t}), 
                      (2) powerSet(P U {t}, S - {t})
*/
public class PowerSet {
    private static char[] data = {'a', 'b', 'c', 'd', 'e', 'f'};
    private static int n = data.length;
    private static boolean[] include = new boolean[n];

    public static void main(String[] args) {
        powerSet(0);    
    }

    // * S의 부분집합들을 구한 후, P를 합집합해 출력
    public static void powerSet(int k) {
        if(k == n) {
            // Base case : 집합 S가 공집합인 경우 P만 출력 (상태공간트리에서 리프노드)
            for(int i = 0; i < n; i++) 
                if(include[i]) 
                    System.out.print(data[i] + " ");
            System.out.println();
            return;
        }
        // 1) 집합 S에서 k번재 원소를 뺀 부분집합
        include[k] = false; // 트리 왼쪽
        powerSet(k + 1);
        // 2) 집합 S에서 k번재 원소를 넣은 부분집합
        include[k] = true;  // 트리 오른쪽
        powerSet(k + 1);
    }
}
