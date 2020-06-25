```javascript
// new를 활용한 Object 생성자 호출
var user = new Object();
user.name = 'Paik';
user.interests = ['Traveling','Swimming'];
user.greeting = function () {
    console.log(`Hi!, I\'m ${this.name}.`);
};
user.greeting();
//객체 리터럴을 통한 user 생성
var usr = {
    name: 'SeungCheol',
    interests: ['Traveling', 'Swimming'],
    greeting: function () {
        console.log(`Hi!, I\'m ${this.name}.`);
    }
};
usr.greeting();

//getter setter 접근자
var user2 = {
    get role() {
        return 'Enginner';
    }
};
console.log(user2.role);

//생성자 함수 생성
function User(name, interests) {
    this.name = name;
    this.interests = interests;
    this.greeting = function() {
        console.log(`Hi!, I\'m ${this.name}.`);
    }
};

var user3 = new User('Hello', ['Jump, Study']);
user3.greeting();

//User 생성자 함수의 프로토 타입과 Object.create() 메서드 활용
var user4= Object.create(User.prototype, {
    name: {value: 'Help'},
    interests: { value: ['Travel', "Jump"]}
});
User.prototype.greeting = function() {
    console.log(`Hi!, I\'m ${this.name}.`);
};
user4.greeting();

//ES6 클래스이용
class Usr {
    //constructor 생성
    constructor(name, interests) {
        this.name = name;
        this.interests = interests;
    }
    //greeting 생성
    greeting() {
        console.log(`Hi!, I\'m ${this.name}.`);
    }
}

const usr1 = new Usr('Paik',['Travel','Shopping']);
usr1.greeting();
```