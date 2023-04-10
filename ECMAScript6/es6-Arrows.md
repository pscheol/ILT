

# [ES6]-Arrows

Arrows 함수는 function 키워드 대신 화살표('=>') 를 사용하여 함수를 선언하는 축약형 함수이다. Arrows는 표현식 본문(Expression Bodies)와 상태 블럭 본문(Statement block bodies)를 지원한다. 

- 콜백 함수에서 사용하면 간결하게 표현이 가능.

```javascript
//매개변수 지정 방법
() => {...} // 매개변수가 없는 경우
x => {...} // 매개변수가 한 개인 경우, 소괄호를 생략할 수 있다.
(x,y) => {...} //매개변수가 여러 개의 경우, 소괄호를 생략할 수 없다.

//함수 몸체의 지정 방법
x => { return x * x} //signle line block
x => x * x //함수의 몸체가 한줄구문이라면 중괄호를 생략할 수 있고 암묵적인 리턴이 된다.
 
() => { return { a:1 }; }
() -> ({a:1}) //위 표현과 동일하고 객체 반환시 소괄호를 사용한다.

//Multi line Block
() => {
    const x = 10;
    return x * x;
}
```

화살표 함수는 익명함수로만 사용할 수 있다. 화살표를 호출 하기 위해서는 함수 호출 식을 사용한다.

```javascript
//ES6
var pow = function(x) { return x * x };
console.log(pow(10)); //100;

//ES6
const pow = x => x * x;
console.log(pow(10)); //100;

```

콜백함수로도 사용할 수 있다.

```javascript
//ES5
var arr [1,2,3];
var pow = arr.map(function(x) {
    return x * x;
});
console.log(pow); //[1, 4, 9]


//ES6
const arr2 = [1,2,3];
const pow2 arr2.map(x => x * x);
console.log(pow2); //[1, 4, 9]
```

```javascript
var evens = [2, 4 ,6 ,8];


//Expression Bodies(표현식의 결과가 반환됨)
var odds = evens.map(v => v + 1); //[3, 5, 7, 9]
var nums = evens.map((v, i) => v + i);  //[2, 5, 8, 11]
var pairs = evens.map(v => ({even: v, odd: v +  1 })); // {even: 2, odd: 3}
```

## this

### 일반함수의 this 
function 키워드로 생성한 일반함수와 화살표 함수의 가장 큰 차이는 `this`이다.

자바스크립트는 함추 호출 방식에 의해 this를 바인딩할 어떤 객체가 동적으로 결정된다. 즉 함수를 호출할 때 함수가 어떻게 호출되었는지에 따라 this에 바인딩할 객체가 동적으로 결정

### 화살표 함수의 this 
화살표 함수는 함수를 선언 할 때 this에 바인딩할 객체가 정적으로 결정된다. 즉, 언제나 상위 스코프의 this를 가리킨다. 이를 `Lexical this`라고 한다.



```javascript
function Prefixer(prefix) {
    this.prefix = prefix;
}

Prefixer.prototype.prefixArray = function (arr) {
    //this는 상위 스코프인 prefixArray 메소드 내의 this를 가리킨다.
    return arr.map(x => `${this.prefix} ${x}`);
}

const pre = new Prefixer('Hello');
console.log(pre.prefixArray(['Hong','Paik']));
```

화살표 함수는 call, apply, bind메소드를 사용하여 this를 변경할 수 없다.

```javascript
window.x = 1;
const normal = function() {
    return this.x;
}
const arrow = () => this.x;

console.log(normal.call({x: 10})); //10
console.log(arrow.call({x: 10})); //1
```

```javascript

//Statement bodies(내부 블럭을 실행만 함, 반환을 위해선 return을 명시)
nums.forEach(v => {
    if (v % 5 === 0)
        console.log(v);
});

//Loxical this
//출력결과 : Bob knows John, Brian

var bob = {
    _name: "Bob",
    _friends: ["John", "Brian"],
    printFriends() {
        this._friends.forEach(f => console.log(this._name + " knows " + f));
    }
}

bob.printFriends();

const materials = [
    'Hydrogen',
    'Helium',
    'Lithium',
    'Beryllium'
];
console.log(materials.map(m => m.length));



const pow = (x) => x * x;
console.log(pow(10));

```

## 화살표 함수를 사용해서 안되는 경우

### 메서드
화살표 함수로 메소드를 정의하는 것은 피해야 한다.

```javascript
//Bad Code
const person = {
    name: 'Paik',
    sayHello: () => console.log(`Hello ${this.name}`); // 상위 컨텍스트 전역객체인 window를 가리킴
}
person.sayHello(); //undefined

//Good 

const person = {
    name: 'Paik',
    sayHello: function() {
        console.log(`Hello ${this.name}`);
    }
}

person.sayHello(); //Hello Paik
```

### prototype

화살표로 정의된 메소드를 prototype에 할당할 경우 일반함수를 할당한다.

### 생성자 함수

생성자 함수는 prototype 프로퍼티를 가지며 prototype 프로퍼티가 가리키는 프로토타입 객체의 constructor를 사용한다. 그러나 화살표 함수는 prototype 프로퍼티를 가지고 있지 않다.

### addEventListener 함수의 콜백 함수

addEventListener 함수의 콜백함수를 화살표로 정의 하면 this가 상위 컨택스트인 전역객체 window를 가리킨다. 그래서 function 키워드로 정의한 일반함수를 사용해야 하며, 이런 일반함수로 정의된 addEventListener 함수의 콜백 내부 this는 이벤트 리스너의 바인딩 요소를 가리킨다.


## 참조

https://poiemaweb.com/es6-arrow-function

https://jsdev.kr/t/es6/2944