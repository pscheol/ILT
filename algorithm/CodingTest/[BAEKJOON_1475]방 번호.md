# [BAEKJOON:1475] 방 번호

####문제
다솜이는 은진이의 옆집에 새로 이사왔다. 다솜이는 자기 방 번호를 예쁜 플라스틱 숫자로 문에 붙이려고 한다.

다솜이의 옆집에서는 플라스틱 숫자를 한 세트로 판다. 한 세트에는 0번부터 9번까지 숫자가 하나씩 들어있다. 다솜이의 방 번호가 주어졌을 때, 필요한 세트의 개수의 최솟값을 출력하시오. (6은 9를 뒤집어서 이용할 수 있고, 9는 6을 뒤집어서 이용할 수 있다.)

####입력
첫째 줄에 다솜이의 방 번호 N이 주어진다. N은 1,000,000보다 작거나 같은 자연수 또는 0이다.

####출력
첫째 줄에 필요한 세트의 개수를 출력한다.

####해결

- 한 세트 0 ~ 9, 6과 9는 뒤집어서 사용할 수 있다.

배열에 숫자카드를 하나씩 넣고 6과 9에대한 처리를 해주고 최대값으로 비교해준다.



####코드

```java
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int t = scan.nextInt();
		int[] nums = new int[10];
		int result = 0;

		do {
			nums[t % 10]++;
		} while((t /= 10) > 0);

		for (int i = 0; i < nums.length; i++) {
			result = (i == 6 || i == 9) ? Math.max(result, (nums[6] + nums[9] + 1) / 2) : Math.max(result, nums[i]);
		}
		System.out.println(result);

		scan.close();
	}
}

```
