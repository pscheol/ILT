Dynamic Programming(동적계획법)
===============================

-	풀고자하는 문제가 여러단계의 반복되는 부분문제로 이루어졌을 때 각 단계 있는 부분 문제의 답을 기반으로 전체 문제의 답을 구하는 방법
-	문제를 부분 문제로 단계적으로 나누고, 가장 작은 부분 문제의 답부터 구해 올라오면서 전체 문제의 해를 구하는 것.
-	계획법(Programming)이란 용어는 코딩(cooding)이 아니라 테이블을 채운다는 문자적 의미(선형 계획법과 유사)

#### 동적계획법 전략

-	동적 계획법과 메모하기는 함께 동작
-	메모하기(이미 푼부속문제들의 테이블)를 이용하는 방법으로 많은 문제에서 지수적 복잡도를 다항적 복잡도로 감소시킬 수 있다.

#### 주요 요소

-	재귀 : 부속 문제들을 재귀적으로 푼다.
-	메모하기 : 이미 계산된 값들을 테이블에 저장(메모하기는 Cashing(캐싱)을 뜻함)
-	동적계획법 = 재귀 + 메모하기

#### 분할정복과 동적계획법 차이

| 분할정복                                                                                | 동적계획법                                                                                     |
|-----------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|
| 분할정복은 위에서 아래로 쪼갬(Top-down)                                                 | 제일 작은 부분 문제부터 상위에 있는 문제로 풀어올라감(Bottom Up)                               |
| 분할 정복으로 쪼갠 각 부분 문제는 완전히 새로운 하나의 문제로 다룸(부분문제들이 독립적) | 문제의 각 단계에 있는 부분 문제들은 그 이전 단계에 있는 문제들의 답에 의존(부속 문제들이 겹침) |
| 한번 푼적이 있는 부분문제의 답은 다시 품                                                | 한번 푼적이 있는 부분문제의 답은 다시푸는 일이 없도록 테이블등에 저장                          |

#### 전략속성

-	**최적 부속 구조(Optimal Substructure)**: 문제의 정답을 작은 문제의 정답에서 구할 수 있다. ex) 서울에서 부산까지 가는 가장 빠른 길이 대전과 대구를 순서대로 거쳐야 한다면 대전에서 부산을 가는 가장 빠른 길은 대구를 거쳐야한다.

-	**겹치는 부속 문제(Overlapping Subproblems)** : 여러 번 반복되는 몇 가지 부속문제들을 포함하는 재귀적 해법.

	-	큰 문제와 작은 문제를 같은 방법으로 풀 수 있다.
	-	문제를 작은 문제로 쪼갤 수 있다.

#### 동작 방식

1.	문제를 부분문제로 나눔
2.	가장 작은 부분문제로부터 해를 구한 뒤 테이블에 저장
3.	테이블에 저장되어 있는 부분문제의 해를 이용하여 점차적으로 상위 부분 문제의 최적해를 구한다.

##### 동적 계획법으로 설계된 피보니치 수

```java
public static long fibonacci2(int n) {
  long result = 0;
  long[] table = new long[n + 1];
  if (n == 0 || n == 1) {
    return n;
  }
  table[0] = 0;
  table[1] = 1;
  for (int i = 2; i <= n; i++) {
    table[i] = table[i - 1] + table[i - 2];
  }
  result = table[n];
  return result;
}
```

##### 최장 공통 부분 순서(Longer Common SubSequence : LCS)

-	두 수열의 가장 긴 공통 부분 수열을 찾아내는 문제.

	1.	X, Y 두 수열 마지막이 공통 부분 수열인 경우

		-	LCS(Xi-1, Yj-1) + 1, Xi = Yj
		-	X와 Y의 문자가 같다(X[i] == Y[j])
		-	X와 Y의 LCS의 길이는 마지막 문자를 하나 줄인 수열 X와 마지막을 하나 줄인 수열 Y간의 LCS에 공통 문자의 길이 1을 추가한 것과 같다.
		-	일반화 하면, 마지막 index가 각각 i, j일 경우 X[i] == Y[j]이면 LCS[i][j] = LCS[i-1][j-1] + 1이다.

	2.	수열 X 마지막이 공통 부분 수열에 속하지 않는 경우

		-	LCS(Xi-1,Yj) > LCS(Xi, Yj-1)
		-	X와 Y의 문자가 다르다(X[i] != Y[j])
		-	X와 Y의 LCS는 마지막 문자를 하나 줄인 수열 X와 수열 Y간의 LCS와 동일하다.
		-	일반화 : LCS[i][j] = LCS[i-1][j]이다.

	3.	수열 Y 마지막이 공통 부분 수열에 속하지 않는 경우

		-	LCS(Xi-1, Yj) < LCS(Xi,Yj-1)
		-	X와 Y의 문자가 다르다(X[i] != Y[j])
		-	X와 Y의 LCS는 수열 X와 마지막 문자를 하나 줄인 수열 Y간의 LCS와 동일하다.
		-	일반화 : LCS[i][j] = LCS[i][j-1]이다.

##### 해결방법

1.	문제를 부분문제로 나눈다.
2.	가장 작은 부분문제부터 해를 구하고 뒤 테이블에 저장
3.	테이블에 저장되어 있는 부분문제의 해를 이용하여 점차적으로 상위 부분 문제의 최적해를 구한다.

```java
public static int LCS(String X, String Y, int[][] table) {
  for (int i = 0; i < X.length(); i++) {
    table[i][0] = 0;
  }
  for (int j = 0; j < Y.length(); j++) {
    table[0][j] = 0;
  }

  for (int i = 1; i <= X.length(); i++) {
    for (int j = 1; j <= Y.length(); j++) {
      if (X.charAt(i-1) == Y.charAt(j-1)) {
          table[i][j] = table[i-1][j-1] + 1;
      } else {
          table[i][j] = table[i-1][j] >  table[i][j-1] ? table[i-1][j] : table[i][j-1];
      }
    }
  }
  return table[X.length()-1][Y.length()-1];
}
```

```java
public static void LCS_TraceBack(String x, String y,int i, int j,  int[][] table, String lcs) {
  if (i == 0  || j == 0) return;

  if (table[i][j] > table[i][j-1] && table[i][j] > table[i-1][j] && table[i][j] > table[i-1][j-1]) {
    String tmp = lcs;
    lcs = String.format("%c%s ", x.charAt(i-1), tmp);
    System.out.println(lcs);
    LCS_TraceBack(x,y, i-1 ,j-1, table, lcs);
  } else if (table[i][j] > table[i-1][j] && table[i][j] == table[i][j-1]) {
    LCS_TraceBack(x,y,i, j-1, table, lcs);
  } else {
    LCS_TraceBack(x,y, i-1, j, table, lcs);
  }
}
```
