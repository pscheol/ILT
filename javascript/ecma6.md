# ECMA6 기본 문법

- let : 기존 var을 대체하는 키워드로 var은 전역변수로도 이용가능 했지만, let은 자신이 정의한 블록 내에서만 접근 가능

```javascript
let let1 = 1;
function test() {
    if (let1 == 1) {
      let let2 = 2;
    }
    //console.log(let2) //Error
}
```

- const : 기존에는 상수를 표현하려면 var VAR_DATA 이런식으로 대문자로 표현했지만, const로 이용할 수 있다.

```javascript
const COS = {
    "name" : "hongil"
};

console.log(COS.name);


COS.name = "hoho"; //객체 값은 변경할 수 있다.


COS = {}; //변수 자체는 상수값으로 수정되지 않는다.


const PI = 3.141592;

PI = 3.12; //상수이므로 수정불가능


```


- import : 다른 스크립트의 특정 함수, 객체, premitive들을 사용하기 위한 키워드
- export : 반대로 스크립트 내의 특정 함수, 객체, premitive를 내보내는 키워드

```javascript
//test1.js
function plus(x, y) {
    return x + y;
}

const CUSTOM_PI = Math.PI * 2;

export {plus, CUSTOM_PI};

//test.js

import { plus, CUSTOM_PI } from 'plusMath';

console.log(plus(3,4));
console.log(CUSTOM_PI);

```


- arrow function
기존 function보다 간결하게 구문을 보여주는 함수로 항상 익명함수이며 생성자를 사용할 수 없다.
```javascript
//기존
var plus1 = function(a, b) {
  var restul = a + b;
  return result;
}

//arrow function
let plus = (a, b) => {
    let result = a + b;
    return result;
};

console.log(plus(1,5));



let callbacks = [];
for (let i = 0; i <= 2; i++) {
    callbacks[i] = function () {
        return i * 2
    }
}
callbacks.forEach(v=> {
   console.log(v());
});

```


- 확장된 파라미터 조작(Extended parameter handling)
default parameter values
함수의 파라미터를 직관적으로 기본 값을 간단하게 할 수 있다.

```javascript
function f(x=1, y = 5, z = 21) {
    return x + y + z;
}

console.log(f(2));

let ff = (x, y = 2, z = 3) => {
    return x + y + z;
};

console.log(ff(1));
```


파라미터 변수를 집합형식으로
나머지 인수를 가변 인수 함수의 단일 매개 변수로 집합할 수 있다.
```javascript

function fff (x, y, ...a) {
    for (let i = 0; i<a.length; i++) {
        console.log(a[i]);
    }
    return (x + y) * a.length;
}
console.log(fff(1, 2, "hello", true, 7) === 9);

//------

var params = [ "hello", true, 7 ];  //length 3
var other = [ 1, 2, ...params ]; // [ 1, 2, "hello", true, 7 ]

function ffff (x, y, ...a) {
    return (x + y) * a.length;
}
console.log(ffff(1, 2, ...params) === 9);

var str = "foo"
var chars = [ ...str ]; // [ "f", "o", "o" ]
```
