Greedy Algorithm(탐욕 알고리즘)
===============================

1.	#### 정의

	-	탐욕 알고리즘은 최적해를 구하는 데에 사용되는 근사적인 방법으로, 여러 경우 중 하나를 결정해야 할 때마다 그 순간에 최적이라고 생각되는 것을 선택해 나가는 방식으로 진행하여 최종적인 해답에 도달한다. 순간마다 하는 선택은 그 순간에 대해 지역적으로는 최적이지만, 그 선택들을 계속 수집하여 최종적(전역적)인 해답을 만들었다고 해서, 그것이 최적이라는 보장은 없다.
2.	#### 동적 계획법과 탐욕 알고리즘의 차이

	-	동적 계획법은 최적의 해를 구하긴 하지만 탐욕 알고리즘 보다는 덜 효율적이다.  
	-	반면 탐욕 알고리즘은 동적 계획법보다 효율적이긴 하지만 반드시 최적의 해를 구해준다는 보장은 하지 못한다.(최적 해가 나오기를 바랄 뿐)
3.	#### 동작과정

	1.	동적 계획법처럼 대상 문제가 최적 부분 구조를 갖고 있어야한다.
	2.	해 선택 : 현재 상태에서 부분 문제의 최적 해를 구한 뒤, 이를 부분해 집합(Solution Set)에 추가
	3.	실행 가능성 검사 : 새로운 부분해 집합이 실행가능한지를 확인, 문제의 제약 조건을 위반하지 않는지 검사
	4.	해 검사 : 새로운 부분해 집합이 문제의 해가 되는지를 확인, 아직 전체 문제의 해가 완성되지 않았다면 1.의 해 선택부터 다시 시작
4.	#### 거스름돈 줄이기

	-	물건 가격이 1200원이고 고객이 1000원 지폐 두 개를 지불하면 거스름돈 800원을 고객에게 줘야 한다. 이때 100원짜리 8개를 줄 수도 있지만 동전의 개수를 최소한으로 하려면 500원 짜리 한개와 100원 짜리 3개를 줘야한다.

	-	알고리즘

		1.	해 선택 : 가장 좋은 해를 선택. 단위가 큰 동전으로만 거스름 돈을 만들면 동전의 개수가 줄어듬. 그러므로 현재 고를 수 있는 가장 단위가 큰 동전을 하나 골라 거스름돈에 추가

		2.	실행 가능성 검사 : 거스름돈이 손님에게 내드려야 할 액수를 초과하는지 검사. 초과한다면 마지막에 추가한 동전을 거스름돈에서 빼고 1.로 돌아가 현재보다 한 단계 작은 단위의 동전을 추가

		3.	해 검사 : 거스름돈 문제의 해는 당연히 거스름돈이 손님에게 내드려야하는 액수와 일치하는 것으로 액수가 일치하지 않으면 1.을 다시 돌아가서 추가할 동전 선택

```java
public class GreedyAlgorithm {
	private int[] coinUnits;
	private int[] change;

	public static void main(String[] args) {
		GreedyAlgorithm al = new GreedyAlgorithm();

		al.coinTest();
	}
	void coinTest() {
		Scanner scan = new Scanner(System.in);
		System.out.print("동전의 가짓수를 입력 : ");
		int unitCount = scan.nextInt();
		coinUnits = new int[unitCount];
		change = new int[unitCount];

		for (int i =0; i<unitCount; i++) {
			System.out.print("[" + i+"] 번째 동전 단위를 입력 : ");
			coinUnits[i] = scan.nextInt();
		}
		System.out.print("물건 가격 입력 : ");
		int price = scan.nextInt();

		System.out.println("손님이 지불한 금액 : ");
		int pay = scan.nextInt();
		getChange(price,pay,unitCount);
		printChange(unitCount);
	}
	/**
	 * 거스름돈 계산을 위한 func 선언
	 * @param price
	 * @param pay
	 * @param coinUnits
	 * @param change
	 * @param size
	 */
	void getChange(int price, int pay, int size) {
		int changeAmount = pay - price;

		for (int i=0; i< size; i++) {
			change[i] = countCoins(changeAmount, coinUnits[i]);
			changeAmount = changeAmount - (coinUnits[i] * change[i]);
		}
	}

	/**
	 * 거스름돈 계산을 위한 func 구현
	 * @param amount
	 * @param coinUnit
	 * @return
	 */
	int countCoins(int amount, int coinUnit) {
		int coinCount = 0;
		int currentAmount = amount;

		while(currentAmount >= coinUnit) {
			coinCount++;
			currentAmount = currentAmount - coinUnit;
		}
		return coinCount;
	}

	/**
	 * 결과출력
	 * @param coinUnits
	 * @param change
	 * @param size
	 */
	void printChange (int size) {
		for(int i =0; i<size; i++) {
			System.out.printf("%8d원 : %d개\n", coinUnits[i], change[i]);
		}
	}
}
```

800원의 거스름돈을 돌려줘야 하는 상황에서 400원자리 동전이 있다고 치면 400원 짜리 2개를 주면되지만, 위 알고리즘 대로 하면 500원짜리 한개와 100원짜리 3개를 주게된다. 그러므로 거스름돈을 만드는 탐욕 알고리즘은 항상 최적이지 않다. 거스름돈 알고리즘 처럼 항상 최적의 결과를 보장하지 못한다는 것도 탐욕 알고리즘의 중요한 속성
