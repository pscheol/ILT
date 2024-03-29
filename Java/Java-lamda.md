

# [Java] Lamda Expressions(람다식)

람다식은 `Anonymous Founction(익명 함수)`를 생성하기 위한 식으로 함수지향 언어이다. 

## 장점

- 자바 코드가 간결해진다.
- 컬렉션의 요소를 필터링하거나 매핑해서 원하는 결과를 쉽게 집계할 수 있다.


## 람다식의 형태

람다식의 형태는 매개변수를 가진 코드블록이지만 런타임 시에는 익명 구현 객체를 생성한다.

> `람다식 -> 매개변수를 가진 코드 블록 -> 익명 구현 객체`

**기존 Runnable의 인터페이스 익명 구현 객체 생성 방법**

```java
Runnable runnable = new Runnable() {
    public void run() {
        //...
    }
}
```

**람다식을 이용한 Runnable의 인터페이스 익명 구현 객체 생성 방법**

```java
Runnable runnable = () -> {
    //...
}
```

람다식은 `(매개변수)->{실행코드}` 형태로 작성할 수 있다.

## 기본문법

**`(매개변수, ...) -> {실행문; ...};`**

- 매개변수의 이름은 자유롭게 정의할 수 있으면 `->`는 매개변수룰 이용하여 `{...}` 를 실행한다는 의미가 된다.

    `(int a) -> { System.out.prinltn(a); }`

- 개개변수 타입은 런타임 시 대입되는 값에 따라서 자동으로 인식될 수 있으므로 타입을 언급할 필요는 없다.

    `(a) -> { System.out.println(a); }`

- 하나의 매개변수만 있다면 `()`를 생략할 수 있으며, 하나의 실행문만 있다면 `{}`도 생략 가능하다.

    `a -> System.out.println(a)` OR `(a) -> System.out.println(a)`

- 매개변수가 없을 경우 `()-> {실행문}`형식으로 사용한다.

- 중괄호 `{}`를 실행하고 결과를 리턴해야한다면 return문으로 결과 값을 지정할 수 있다.
    `(x, y) -> { return x + y }`
- 중괄호 `{}`에 return 문만 있다면, return문 없이 리턴 할 수 있다.
    `(x, y) -> x + y`
