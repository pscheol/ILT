

# 템플릿 리터럴(Template Literals)

ES6는 템플릿 리터럴(Template Literals)은 일반 문자열과 비슷해보이지만 `'` 또는 `"` 같은 통상적인 따옴표 문자 대신 백틱(Backtick) (**`**) 문자를 사용한다.

```javascript
const template = `템플릿 리터럴은 '작은따옴표(single quotes)'과 "큰따옴표(double quotes)"를 혼용할 수 있다.`;

console.log(template);
```

## 방법1 

일반적인 문자열에서 줄바꿈은 허용되지 않고 공백(white-spce)를 표현하기 위해 백슬래시(\\)로 시작하는 이스케이프 시퀀스(Escape Sequence)를 사용해야한다. 템플릿 리터럴은 일반적인 문자열과 다르게 여러 줄에 걸쳐 문자열을 작성할 수 있으며 템플릿 리터럴 내의 모든 white-space는 있는 그대로 적용된다.


```javascript
const template = `<ul>
    <li><a href="#home">Home</a></li>
    <li><a href="#news">News</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#about">About</a></li>
</ul>`;

console.log(template);
```

[결과]

```
<ul>
    <li><a href="#home">Home</a></li>
    <li><a href="#news">News</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#about">About</a></li>
</ul>
```

## 방법2

템플릿 리터럴은 + 연산자를 사용하지 않고 간단한 방법으로 문자열을 삽입할 수 있는데 이를 문자열 인터폴레이션(String Interpolation)이라고 한다.

```javascript
const first = 'GildDong';
const last = 'Hong';

//ES5 String
console.log('My name is ' + first + ' ' + last + '.');
//문자열 인터폴레이션(String Interpolation)
console.log(`My name is ${first} ${last}.`);
```

문자열 인터폴레이션(String Interpolation)은 `${...}` 으로 표현식을 감싸고 인터폴레이션 내의 표현식은 문자열로 강제 타입 변환된다.

```javascript
console.log(`2 * 9 = ${2 * 9}`); //18
```


## 참조

https://poiemaweb.com/es6-template-literals

https://jsdev.kr/t/es6/2944