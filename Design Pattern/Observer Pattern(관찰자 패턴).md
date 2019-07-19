# Observer Pattern(관찰자 패턴)

Observer Pattern은 Observer 라는 Child List를 가지고 있는 주체를 필요로 하는데, 주체는 자신의 메서드 중 하나를 호출해 Observer에게 상태 변경을 알린다. 즉, 주체(Subject)는 이벤트를 발생시키는 역할을 하고, 관찰자(Observer)는 이벤트 수신하는 역할을 한다. 이 패턴은은 이벤트 처리를 기반으로 시스템을 구현할 때 필수고, MVC패턴의 중요한 부분이기도 하다. 대부분의 UI라이브러리는 Observer Pattern을 사용한다. 그리고 런타임 중 객체 사이에 일 대 다(1:N) 의존성을 등록할 수 있다.

**장점**

- 응용 프로그램 사이의 결합도를 낮출 수 있다.
- 효율적으로 이벤트를 배포하는데 도움이 된다.


**UML 다이어그램**

![Observer Pattern](https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Observer.svg/854px-Observer.svg.png)

위 다이어그램 처럼 Subject와 2개의 Observer 인터페이스로 구성된다. Observer는 Subject에 등록되고 Subject로부터 알림을 수신한다. Subject 스스로 이벤트를 발생시키거나 다른 구성요소에 의해 호출될 수 있다.

구독을 발생시키는 인터페이스로 Subject는 모든 인스턴스를 찾고 각 Subject 인스턴스에 Observer를 등록하는 역할을 담당한다.
[Subject.java]

```java
public interface Subject<T> {
    public void registerObserver(Observer<T> observer);

    public void unregisterObserver(Observer<T> observer);

    public void notifyObservers(T event);

}

```

이벤트를 수신하는 인터페이스로 Observer 의 구현체에서 구독절차를 담당할 수 있고 Subject의 존재롤 인식할 필요도 없다.

[Observer.java]

```java
public interface Observer <T> {
    public void observe(T event);
}
```

Observer 구현체

[ConcerteObserverA.java]

```java
public class ConcerteObserverA implements Observer<String> {

    @Override
    public void observe(String event) {
        System.out.println("Observer A : "+ event);
    }
}
```

[ConcerteObserverB.java]

```java
public class ConcerteObserverB implements Observer<String> {

    @Override
    public void observe(String event) {
        System.out.println("Observer B : "+ event);
    }
}
```

Subject 구현체

[ConcreteSubject.java]
```java
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConcreteSubject implements Subject<String> {
    private final Set<Observer<String>> observers = new CopyOnWriteArraySet<>();

    @Override
    public void registerObserver(Observer<String> observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer<String> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        observers.forEach(observer -> observer.observe(event));
    }
}
```

Main class

[Main.java]

```java
public class Main {
    public static void main(String[] args) {
        Subject<String> subject = new ConcreteSubject();
        Observer<String> observerA = new ConcerteObserverA();
        Observer<String> observerB = new ConcreteObserverB();

        subject.registerObserver(observerA);
        subject.registerObserver(observerB);

        subject.notifyObservers("Hello");

        subject.unregisterObserver(observerA);

        subject.notifyObservers("Event B");

        subject.unregisterObserver(observerB);

        subject.notifyObservers("No event");

        subject.registerObserver(observerA);

        subject.notifyObservers("Event A");
    }
}
```
