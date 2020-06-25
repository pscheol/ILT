# Scope 연산자 let, const


기존 ES6 변수들을 선언할 수 있는 방법은 **var** 키워드이다. var키워드를 사용할 때 주의를 기울이지 않으면 **심각한 문제**를 일으킨다.

[문제점]

1. Function-level scope(함수레벨 스코프)
  - 함수의 코드 블록만을 스코프로 인정한다. 그러므로 전역함수 외부에서 생성한 변수는 모두 전역 변수이다. 이는 전역변수를 남발할 가능성이 크다.
  - for문 변수 선언문에서 선언한 변수를 블록 외부에서 참조할 수 있다.

2. var 키워드 생량 허용 : 암묵적 전역 변수를 만들어 낼 가능성이 크다.

3. 변수 중복 선언 허용 : 의도하지 않은 변수값의 변경이 일어날 가능성이 크다.

4. 변수 호이스팅 : 변수를 선언하기 이전에 참조할 수 있는 것.

> 대부분의 문제는 전역변수로 인해 발생한다.

**이를 개선하기 위해 ES6에서 도입한 let과 const !**

### let

대부분 프로그래밍 언어는 Block-level scope(블록 레벨 스코프)와 Function-level scope)가 있다.

- Block-level scope(블록 레벨 스코프) : 코드 블록 내에서 선언된 변수로 코드 블록 내에서만 유효하고 코드 블록 외부에서는 참조할 수 없다. (지역변수라고 보면됨)
- Function-level scope(함수 레벨 스코프) : 함수 내에서만 선언된 변수로 함수 안에서만 사용가능하고 외부에서 참조할 수 없다. (함수외부 : 전역변수, 함수내부 : 지역변수 [자바스크립트는 함수 레벨 스코프])

기존 var을 대체하는 키워드로 var은 전역변수로도 이용가능 했지만, let은 자신이 정의한 블록 내에서만 접근 가능하다

> var 키워드는 변수선언,초기화 단계가 한번에 이루어지지만, let 키워드는 변수선언, 초기화 단계가 분리되어 진행된다. 그래서 초기화 이전에 변수에 접근하면 에러가 발생한다.
>
```javascript
let let1 = 1;
function test() {
    if (let1 == 1) {
      let let2 = 2;
    }
    //console.log(let2) //Error
}
```

```javascript
//전역변수
let name ="global val";

function home() {
    var homeVar = "homeVar";
    for (var i = 0; i < 100; i++) {

    }
    console.log(i); //100이 출력된다.

    if (true) {
        let myif = "myif";
    }

    //console.log(myif); //Exception 이 발생한다 myif의 변수는 let으로 되어있으므로 지역변수다.
}

home();
```

#### const

const는 상수를 사용하기 위해 사용한다. 기존에는 상수를 표현하려면 var VAR_DATA 이런식으로 대문자로 표현했지만, const로 이용할 수 있다.(그러나 반드시 상수만을 위해 사용되진 않는다.)

```javascript
const COS = {
    "name" : "hongil"
};

console.log(COS.name);


COS.name = "hoho"; //객체 값은 변경할 수 있다.


COS = {}; //변수 자체는 상수값으로 수정되지 않는다.


const PI = 3.141592;

PI = 3.12; //상수이므로 수정불가능


{
    const TEN = 10;
    console.log(TEN);
}
//console.log(TEN) //Exception TEN is not defined

//객체에서 데이터를 재할당을 해도 에러가 발생한다.
const obj = { data : 123}

//obj = {data : 122} //TypeError

```

> const는 재할당은 할 수 없다. 그래서 const 변수가 객체인경우 참조를 변경하지 못한다. 하지만, 객체의 프로퍼티는 보호하지 않는다. 즉, 객체의 내용(추가,삭제, 값변경)은 변경할 수 있다.

```javascript
const data = { name : "hongil" }

console.log(data); //name : hongil

//data = {} //재할당 금지!! Exception 발생

data.name = 'dong';

console.log(data); //name : dong

```

- https://poiemaweb.com/es6-block-scope

- http://es6-features.org/#Constants
