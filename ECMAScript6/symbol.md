# symbol

변경 불가능한 원시타입의 값이다. 주로 변수명이 충돌 위험이 없는 유일한 객체의 Property Key를 만들기 위해 사용되며 Symbol 함수를 호출하여 생성한다.

```javascript
//symbol key는 이름 충돌 위험이 없는 유일한 객체의 property key 이다.
let key = Symbol('key');
console.log(key);
console.log(typeof key); //symbol

var obj = {};
obj[key] = 'value';
console.log(obj[key]); //value
```

[결과]
```
Symbol(key)
symbol
value
```
