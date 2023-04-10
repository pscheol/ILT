

# [ES6] 클래스(class)

클래스는 프로토타입 기반 객체 지향 패턴을 더 쉽게 사용할 수 있게 할 수 있는 대체제로 클래스 패턴 생성을 더 쉽고 간단하게 생성할 수 있다.

```javascript

//클래스 선언
class Person {

    //constructor 생성자
    constructor(name) {
        this._name = name;
    }

    Hello() {
        console.log('Hello! ' + this._name);
    }
}

const me = new Person("Paik");
const friends = new Person("Lee");
me.Hello();
friends.Hello();
```


## 인스턴스 생성

new 연산자를 이용하여 클래스 인스턴트를 생성한다.


```javascript
class Foo {}

const foo = new Foo(); //Foo는 Constructor
```

만약 new 를 사용하지 않고 Foo() 만 입력할 경우 TypeError가 발생한다.

```javascript
class Foo {}

const foo = new Foo() // Uncaught TypeError: Class constructor Foo cannot be invoked without 'new'

```

## constructor(생성자)

constructor는 인스턴트스를 생성하고 클래스 필드를 초기화 할 수 있는 메소드이다. 

- constructor는 클래스 내에 한 개만 존재할 수 있다.


```javascript
//클래스 선언
class Person {
    //생성자
    constructor(name) {
        //this는 클래스가 생성할 인스턴스를 가리킴
        //_name은 클래스 필드
        this._name = name;
    }

}

const me = new Person('Paik');
console.log(me);

```

constructor는 생략할 수 있고 `class Foo {}`와 같이 표현한 것과 동일하게 동작하고 빈 객체를 생성한다. 인스턴스에 프로퍼티를 추가하려면 인스턴스를 생성 한 후 프로퍼티를 동적으로 추가해야 한다.

```javascript
class Foo {} 

const foo = new Foo();
console.log(foo); // Foo {}


//동적 프로퍼티 할당 및 초기화

foo.num = 1;
console.log(foo); //Foo {num: 1}

```


## 클래스 필드

Class Body에는 메서드만 선언 할 수 있다.

```javascript
class Foo {
    name = ''; //SyntaxError
    constructor() { }

}
```

필드 선언과 초기화는 constructor 안에서 선언한 클래스 필드를 생성할 인스턴스를 가리키는 this에 바인딩한다. 그러면 클래스 필드는 클래스가 생성할 때 인스턴스의 프로퍼티가 되고, 인스턴스를 통해 클래스 외부에서 언제나 참조가 가능한다. 언제나 `public`

> ES6는 private, public protected 키워드를 지원하지 않는다.

```javascript

class Foo {
    constructor(name) {
        this.name = name;
     }

}

```


```javascript

class Foo {
    constructor(name = '') {
        this.name = name; //public class field
     }
}

const foo = new Foo('Paik');
console.log(foo.name); //field는 클래스 외부에서 참조 할 수 있음

```


## Class Field declarations proposal

-    Field declarations
-    Private field
-    Static public fields


이 코드는 최신브라우저(Chrome 72 이상), Node.js (버전 12 이상)에서 항상 작동
```javascript

class Person {
    age = 1;           // Field declaration
    #height = 0;        // Private field
    static y = 20;      // Static field;
    static #sp = 30;    // Static private field

    change() {
        this.#height = 10; //private 필드 참조
        return this.#height;
    }
}

const me = new Person();

console.log(me); // {age:10, #height: 0}

console.log(me.age);

console.log(me.y); 

console.log(me.change());

```


## getter, setter

### getter

getter는 클래스 필드에 접근할 때 클래스 필드의 값을 가져온다. `get` 키워드를 사용할 수 있고 메서드 이름은 클래스 필드 이름처럼 사용할 수 있다. 즉


```javascript

class Foo {
    //생성자
    constructor(arr = []) {
        this._arr = arr;
    }

    get firstElem() {
        return this._arr.length ? this._arr[0] : null;
    }
}

const foo = new Foo([1,2,3,4,5]);

console.log(foo.firstElem); //get 키워드를 사용한 메서드를 불러올 때 필드명을 가져올 때처럼 사용

```

### setter

setter는 클래스 필드에 해당 값을 할당 할 때 사용하고 `set`키워드를 사용한다. setter도 메서드 이름을 클래스 필드처럼 사용된다.

```javascript
class Foo {
    //생성자
    constructor(arr = []) {
        this._arr = arr;
    }

    get firstElem() {
        return this._arr.length ? this._arr[0] : null;
    }
    set firstElem(elem) {
        this._arr = [elem, ...this._arr]; //... this._arr은 this._arr을 개별 요소로 분리
    }
}

const foo = new Foo([1,2,3,4,5]);

foo.firstElem = 100; //set 키워드를 사용한 메서드로 데이터를 할당 할 때 필드명을 사용.

console.log(foo.firstElem); //get 키워드를 사용한 메서드를 불러올 때 필드명을 가져올 때처럼 사용

```

## 정적 메서드 (static method)

정적 메서드를 정의 하려면 `static` 키워드를 사용하면 된다. 정적 메서드는 클래스의 인스턴스가 아닌 클래스 이름으로 호출한다. 그래서 클래스의 인스턴스를 생성하지 않아도 호출할 수 있다.

- 정적 메서드는 `this`를 사용할 수 없으며, 정적 메서드 내부에서 클래스 인스턴스를 가리키는 것이 아니라 자기 자신을 기리킨다.


```javascript
class Person {
    constructor(age) {
        this._age = age;
    }
    static staticMethod() {
        /**
            정적 메서드는 this를 사용할 수 없으며, 정적 메서드 내부에서 클래스 인스턴스를 가리키는 것이 아니라 자기 자신을 기리킨다.
        **/
        return 'staticMethod age: ' + this._age;
    }

    prototypeMethod() {
        return this._age
    }
}
//정적 메서드는 클래스 이름을 호출
console.log(Person.staticMethod());  //staticMethod age: undefined

const me = new Person(34);

//정적 메서드는 인스턴스로 호출할 수 없다.
console.log(me.staticMethod()); //Uncaught TypeError: me.staticMethod is not a function

```

**클래스도 function이고 기존 prototype 기반 패턴의 Syntatic suger일 뿐이다.**


```javascript
class Person { }
console.log(typeof Person); //function
```


[ES5로 표현]

```javascript

var Person = (function() {
    //constructor
    function Person(age) {
        this._age = age;
    }
    Person.staticMethod = function() {
      return 'staticMethod age: ' + this._age;
    };

    Person.prototype.prototypeMethod = function() {
        return this._age;
    }
    return Person;
}());

var me = new Person(34);
console.log(me.prototypeMethod()); //34
console.log(Person.staticMethod()); //staticMethod age: undefined
console.log(me.staticMethod());//Uncaught TypeError: me.staticMethod is not a function
console.log(Person.prototype === me.__proto__); // true
console.log(Person.prototype.constructor === Person); //true
```

## 클래스 상속(Class Inheritance)

클래스 상속은 새롭게 정의할 클래스가 기존 클래스와 유사할 경우 상속을 통해 그대로 사용하고 다른 점만 구현할 수 있다. 즉 하위 클래스에서 상속받아 상위 클래스의 정보를 다시 재사용할 수 있는 것이다. 코드의 재사용할 경우 개발 비용을 줄일 수 있다.

### extends 키워드

extends는 부모 클래스를 상속받는 자식클래스를 정의 할 때 사용한다

```javascript
//부모클래스 
class Parents {
    constructor(name) {
        this.name = name;
    }
    get getName() {
        return this.name;
    }
    set setName(name) {
        return this.name = name;
    }
}

//자식 클래스  부모 클래스를 상속 받는다.
class Child extends Parents {

    constructor(name, age) {
        super(name); //부모클래스 생성자
        this.age = age;
    }

    get getAge() {
        return this.age;
    }
    set setAge(age) {
        return this.age = age;
    }
    getInfo() {
        return 'My name is ' + this.name + 'and age is ' + this.age;
    }
}

const child = new Child('hello', 33);
console.log(child.getInfo()); //My name is helloand age is 33
child.setAge = 20;
console.log(child.getAge); 20
console.log(child.getInfo()); //My name is helloand age is 20
child.setName = 'World';
console.log(child.getName); //World

console.log(child instanceof Child); //true
console.log(child instanceof Parents); //trye


```

### super 키워드

super 는 부모클래스를 참조하거나 생성자를 호출할 때 사용한다.

```javascript
class Parents {
    constructor(name) {
        this.name = name;
    }
    get getName() {
        return this.name;
    }
    set setName(name) {
        return this.name = name;
    }
}

//자식 클래스  부모 클래스를 상속 받는다.
class Child extends Parents {

    constructor(name, age) {
        super(name); //부모클래스 생성자
        this.age = age;
    }

    get getAge() {
        return this.age;
    }
    set setAge(age) {
        return this.age = age;
    }
    getInfo() {
        return 'My name is ' + super.getName + ' and age is ' + this.age; //super를 통해 getName 참조
    }
}

const child = new Child('hello', 33);
console.log(child.getInfo()); //My name is hello and age is 33
child.setAge = 20;
console.log(child.getAge); 20
console.log(child.getInfo()); //My name is hello and age is 20
child.setName = 'World';
console.log(child.getName); //World

console.log(child instanceof Child); //true
console.log(child instanceof Parents); //trye


```

super 메서드는 자식 클래스의 생성자 내부에서 부모 클래스 생성자(슈퍼클래스)를 호출한다. 즉 부모 클래스의 인스턴스를 생성한다. 그래서 자식 클래스의 생성자에서 `super()`를 호출하지 않으면 this에 대한 참조 에러(ReferenceError)가 발생한다.

```javascript
class Parents {}

class Child extends Parents {
    constructor() {

    }
}

const child = new Child(); //Uncaught ReferenceError: Must call super constructor in derived class before accessing 'this' or returning from derived constructor

```

자식 클래스의 생성자에 `super()`를 추가해야한다. 

```javascript

class Parents {}

class Child extends Parents {
    constructor() {
        super(); 
    }
}   

const child = new Child();
```

## 참조

**https://poiemaweb.com/es6-class**

**https://jsdev.kr/t/es6/2944**