//let 지역변수
var name = "global val";

function home() {
    var homeVar = "homeVar";
    for (var i = 0; i < 100; i++) {

    }

    console.log(i);

    if (true) {
        let myif = "myif";
    }

    //console.log(myif);

}

home();


var list = ['hello', 'javascript'];

for (var i = 0; i < list.length; i++) {
    console.log(list[i]);
}

console.log(i);


//const 상수

function home() {
    const homename = "my house";
    //homename = "your house";  Exception!!
    console.log(homename);
}

home();

//const는 기본으로 사용하고, 변경이 필요한 경우 let을 사용하는 것을 추천.

function hello() {
    const list = ["apple", "orange", "watermelon"];
    list.push("dsfds"); // const를 사용하더라도 배열과 오브젝트의 값을 변경하는 것은 가능하다.
    console.log(list);
}

hello();

//immutable array 만들기

const arry = ["apple", "orange", "watermelon"];
arry2 = [].concat(arry, "banan");
console.log("list1=" + arry + ", list2=" + arry2);
console.log(arry === arry2)


참고

- https
://poiemaweb.com/es6-block-scope

-http
://es6-features.org/#Constants