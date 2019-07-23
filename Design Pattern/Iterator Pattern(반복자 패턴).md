# Iterator Pattern(반복자 패턴)

데이터 집합체(Aggregate)에서 순서대로 데이터를 꺼내기 위한 패턴이다.

#### 처리절차

1. 데이터 집합체에서 데이터를 꺼내는 Iterator를 생성
2. Iterator로 데이터를 하나씩 순서대로 얻을 수 있게 한다. (데이터 집합체는 어떤 형태로 데이터를 가지고 있는지는 Iterator가 알 필요가 없다.)
3. Iterator는 데이터가 더 존재하는지 판단하는 hasNext 메서드를 호출해 데이터가 있다면 next메서드를 통해 데이터를 얻고 처리하는 작업을 반복한다.

[Iterator 패턴 UML]
![Iterator Pattern UML](https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Iterator_UML_class_diagram.svg/1920px-Iterator_UML_class_diagram.svg.png)

#### Iterator Pattern에서 사용하는 클래스

- Aggregate : 데이터를 담고 있는 집합체를 나타내는 인터페이스
- Iterator : 데이터를 순서대로 받을 수 있게하는 인터페이스
- ConcreteAggregate : 실제 데이터를 넣어 두는 데이터 집합체를 구현한 클래스
- ConcreteIterator : 실제로 데이터를 받을 수 있게 Iterator를 구현한 클래스

**자바 라이브러리에서 사용되고 있는 Iterator Pattern**

```java
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorExm {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C", "D");

        Iterator<String> it = list.iterator();

        while (it.hasNext()) { //받을 데이터가 남아있는지 체크
            String value = it.next(); //데이터를 얻는다
            System.out.println("Data : " + value);
        }
    }
}

```

**Iterator Pattern 코드**

[Iterator.java]

```java

public interface Iterator<E> {

    public boolean hasNext();

    public E next();
}

```

[Aggregate.java]
```java
public interface Aggregate<E> {
    public Iterator<E> iterator();
}

```

[ArrayList.java]

```java

public class ArrayList<E> implements Aggregate<E> {

    private Object[] elements;
    private int size = 10;
    private int index = 0;

    public ArrayList(int size) {
        this.size = size;
        this.elements = new Object[this.size];
    }

    public ArrayList() {
        this.elements = new Object[this.size];
    }

    public Iterator<E> iterator() {
        return new ArrayListIterator<E>(this);
    }

    public void add(E t) {
        if (index >= size) {
            this.size = (int) Math.ceil(this.size * 1.5);
            Object[] tmp = new Object[this.size];
            for (int i = 0; i < elements.length; i++) {
                tmp[i] = elements[i];
            }
            elements = tmp;
        }
        this.elements[index++] = t;
    }

    public int getSize() {
        return index;
    }

    private Object getIndexAt(int idx) {
        return this.elements[idx];
    }

    private class ArrayListIterator<E> implements Iterator<E> {
        private ArrayList arrayList;
        private int idx;

        private ArrayListIterator(ArrayList arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public boolean hasNext() {
            return (this.idx < arrayList.getSize());
        }

        @Override
        public E next() {
            return (E) arrayList.getIndexAt(idx++);
        }
    }
}
```
