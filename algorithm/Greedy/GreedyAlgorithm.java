package algorithm;

import java.util.Scanner;

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
