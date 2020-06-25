# 호이스팅 (Hoisting)

자바스크립트의 인터프리터가 함수선언과 변수선언의 선언들이 속해 있는 스코프의 최상단으로 끌어올릴수 있는 방법에 대한 일종의 비유이다. 이로인해 자바에서 작성했다면 분명히 잘못된 것을 컴파일하면 실패하는 것들이 자바스크립트에서는 전혀 문제가 되지 않은 것을 볼 수 있다.

```javascript
travel = 'No Plan';
var traval;
console.log(traval); //undefined

function travel() {
    console.log('Traveling');
}
travel(); //TypeError: travel is not a function

function workout() {
    goToGym();
    var goToGym = function () {  //TypeError: goToGym is not a function
        console.log('workout in gym A');
    };
    return;
    function goToGyn() {
        console.log('workout in gym B');
    }
}
workout();
```