
# [Java] Generic(제네릭)

Generic(제네릭)은 class와 interface, method를 정의할 때 `Type(타입)`을 `Parameter(파라미터)`로 사용할 수 있도록 하는 역할을 한다. 
그래서 타입 파라미터는 코드를 작성할 때 구체적일 타입으로 대체되어 다양한 코드를 생성할 수 있도록 한다.

## 장점

1. 컴파일 시 정확한 타입 체크를 할 수 있다.
     - 컴파일러에서 코드에서 잘못 사용한 타입 때문에 에러가 발생하는 상황을 예방할 수 있다.
2. Casting(형변환)을 사용하지 않는다.
     - Casting을 사용하면 불필요한 타입 변환을 하므로 성능에 영향을 줄 수 있다.

        형변환을 이용한 경우

        ```java
        List list = new ArrayList();
        list.add("work");
        String data = (String) list.get(0); //casting을 해야함
        ```

        Generic(제네릭)을 이용한 경우

        ```java
        List<String> list = new ArrayList<>();
        list.add("work");
        String data = list.get(0); //generic을 사용하면 casting을 안함
        ```

## Generic(제네릭) 종류

### Generic Type(제네릭 타입) `(class<T>, interface<T>)`
   
제네릭 타입은 타입을 파라미터로 가지는 클래스와 인터페이스를 말하고, class, interface 뒤에 `<>` 붙이고 사이에 Type Parameter를 넣는다.

```java
public class clasName<T> {
    ...
}
public interface InterfaceName<T> {
    ...
}
```

#### Example(에제)

**Generic Class**

```java 
class Box<T> {
    private T t;
    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return this.t;
    }
}
```

**Test**

```java 
public class Main {
    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.add("Flower");
        System.out.println("Box name : "+ box.get());
    }
}
```

### Multi Type Parameter(멀티 타입 파라미터) `(class<K,V,...>, interface<K,V,...>)`

Generic Type은 두 개 이상 Multi Type Parameter를 사용할 수 있다.

```java
public class ClassName<K, V> {

}
```

#### Example(예제)

- Multi Type Generic Class
  
```java
class Product<K, M> {
    private K kind;
    private M model;

    public Product() {
    }

    public Product(K kind, M model) {
        this.kind = kind;
        this.model = model;
    }

    public K getKind() {
        return kind;
    }

    public M getModel() {
        return model;
    }

    public void add(K kind, M model) {
        this.kind = kind;
        this.model = model;
    }
}
```

**Test**

```java
public class Main {
    public static void main(String[] args) {
        Product<String, String> p1 = new Product();
        p1.add("Tool", "망치");
        System.out.println("kind=" + p1.getKind() + ", model=" + p1.getModel());
    }
}
```

### Generic Method(제네릭 메소드) `(<T, R> R method(<T t>))`

Generic Method는 Type Parameter(타입 파라미터)와 Return Type(리턴타입)으로 타입 파라미터를 갖는 메소드 이다.

**표현방법**

```java
public <TypeParameter, ...> returnType methodName(Parameter, ...) {
    ...
}
```

#### Example(예제)

**제네릭 클래스**

```java 
class Box<T> {
    private T t;
    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return this.t;
    }
}
```

**제네릭 메소드**

```java
public static <T> Box<T> boxing(T t) {
        Box<T> box = new Box<>();
        box.set(t);
        return box;
}
```

**Test**

```java
public class Main {
    public static void main(String[] args) {
        Box<Integer> box = boxing(10);
        System.out.println("Box=" + box.get());
    }

    public static <T> Box<T> boxing(T t) {
        Box<T> box = new Box<>();
        box.set(t);
        return box;
    }
}
```

### Limited Type Parameter(제한된 파라미터)) `(T extends superType)`

Limited Type Parameter는 상위 타입이거나 상위 타입을 상속받고 있는 하위 타입만 사용하겠다라는 것이다.

**상위 타입은 클래스 뿐만 아니라 인터페이스도 사용가능하다.**


`public <T extends superType> returnType method(args, ...) { ...}`


#### Example(예제)

**Limited Type Parameter**
  
```java
public <T extends Number> int compare(T t1, T t2) {
    long v1 = t1.longValue();
    long v2 = t2.longValue();
    return Long.compare(v1, v2);
}
```

**Test**

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("compare=" + compare(10,20));
        System.out.println("compare=" + compare(20,20));
    }

    public static <T extends Number> int compare(T t1, T t2) {
        long v1 = t1.longValue();
        long v2 = t2.longValue();
        return Long.compare(v1, v2);
    }   
}

```

### Wildcard Type(와일드카드 타입) `(<?>, <? extends ...), <? super ...>`

`?`를 Wildcard(와일드 카드) 라고 부른다.

1. `GenericType<?>` : Unbounded Wildcards(제한없음)
    - 모든 클래스, 인터페이스 타입이 올수 있다.

2. `GenericType<? extends SuperType>` : Upper Bounded Wildcards(상위클래스 제한)
    - 상위 타입이나 하위 타입만 올 수 있다.

3. `GenericType<? super ChildType>` : Lower Bounded Wildcards(하위 클래스 제한)
   - 하위타입이나 나 상위 타입만 올 수 있다.


### Example(예제) 

**일반인, 직장인, 학생, 고등학생 클래스**

1. 일반인(Person)은 최상위 부모객체이다.

2. 직장(worker)은 일반인(Person)을 상속 받는다. 
    
3. 학생(Student)는 일반인(Person)을 상속 받는다.

4. 고등학생(HighStudent)은 학생(Student)를 상속 받는다.


```java
class Person {
    private String course;

    public Person(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return course;
    }
}
class Worker extends  Person {

    public Worker(String course) {
        super(course);
    }
}

class Student extends Person {
    public Student(String course) {
        super(course);
    }
}
class HighStudent extends Student {
    public HighStudent(String course) {
        super(course);
    }
}
```


**코스 클래스**

- 수강생을 배정하는 클래스

```java
class Course<T> {
    private String name;
    private List<T> students;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getStudents() {
        return students;
    }

    public void add(T t) {
        students.add(t);
    }
}
```

**Test**

1. `Course<?>` : 모든 타입(Person, Worker, Student, HighStudent)를 받을 수 있다.
2. `Course<? extends Student>` : 학생(Student), 고등학생(HighStudent)만 받을 수 있다.
3. `Course<? super Worker>` : 직장인(Worker) 일반인(Person)만 받을 수 있다.


```java
public static void main(String[] args) {
        Course<Person> personCourse = new Course<>("일반인 과정");
        personCourse.add(new Person("일반인"));
        personCourse.add(new Worker("직장인"));
        personCourse.add(new Student("학생"));
        personCourse.add(new HighStudent("고등학생"));

        Course<Worker> workerCourse = new Course<>("직장인 과정");
        workerCourse.add(new Worker("직장인"));
        Course<Student> studentCourse = new Course<>("학생 과정");
        studentCourse.add(new Student("학생"));
        studentCourse.add(new HighStudent("고등학"));

        Course<HighStudent> highStudentCourse = new Course<>("고등학생과정");
        highStudentCourse.add(new HighStudent("고등학생"));

        //모든과정
        registerCourse(personCourse);
        registerCourse(workerCourse);
        registerCourse(studentCourse);
        registerCourse(highStudentCourse);

        //학생
//        registerCourseStudent(personCourse); //학생만가능
//        registerCourseStudent(workerCourse); //학생만가능
        registerCourseStudent(studentCourse);
        registerCourseStudent(highStudentCourse);
        //직장인
        registerCourseWorker(personCourse);
        registerCourseWorker(workerCourse);
//        registerCourseWorker(studentCourse); //직장인 일반인만가능
//        registerCourseWorker(highStudentCourse); //직장인 일반인만가능
    }

    public static void registerCourse(Course<?> course) {
        System.out.println(course.getName() + " 수강생 : " + course.getStudents());
    }

    public static void registerCourseStudent(Course<? extends Student> course) {
        System.out.println(course.getName() + " 학생 수강생 : " + course.getStudents());
    }
    public static void registerCourseWorker(Course<? super Worker> course) {
        System.out.println(course.getName() + " 직장인 수강생 : " + course.getStudents());
    }

}
```

### Generic inheritance and implementation(제네릭 상속과 구현)

제네릭 타입도 다른타입과 마찬가지로 부모 클래스가 될 수 있다.

**부모 클래스**

`public class Parent<K, V> { ... }`

**자식 클래스**

`public class Child<K, V, C> extends Product<K, V> { ... }`


### Example(예제)

**부모 클래스**

```java
class Product<K, M> {
    private K kind;
    private M model;

    public Product() {
    }

    public Product(K kind, M model) {
        this.kind = kind;
        this.model = model;
    }

    public K getKind() {
        return kind;
    }

    public void setKind(K kind) {
        this.kind = kind;
    }

    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
    }
}
```

**자식 클래스**

```java
class ChildProduct<K, M, C extends Product<K, M>> {
    private C company;

    public ChildProduct() {
    }

    public ChildProduct(C company) {
        this.company = company;
    }

    public C getCompany() {
        return company;
    }

    public void setCompany(C company) {
        this.company = company;
    }
}
```

**Test**

```java
public class Main {
    public static void main(String[] args) {
        ChildProduct<String, String, String> smProduct = new ChildProduct<>();
        smProduct.setKind("TV");
        smProduct.setCompany("삼성전자");
        smProduct.setModel("OLED1234");
        ChildProduct<String, String, String> lgProduct = new ChildProduct<>();
        lgProduct.setKind("TV");
        lgProduct.setCompany("LG전자");
        lgProduct.setModel("OLED5678");
        System.out.println("Product[Company=" + smProduct.getCompany() + ", kind=" + smProduct.getKind() + ", Model=" + smProduct.getModel() + "]");
        System.out.println("Product[Company=" + lgProduct.getCompany() + ", kind=" + lgProduct.getKind() + ", Model=" + lgProduct.getModel() + "]");
    }
}

class ChildProduct<K, M, C extends Product<K, M>> {
    private C company;

    public ChildProduct() {
    }

    public ChildProduct(C company) {
        this.company = company;
    }

    public C getCompany() {
        return company;
    }

    public void setCompany(C company) {
        this.company = company;
    }
}

class Product<K, M> {
    private K kind;
    private M model;

    public Product() {
    }

    public Product(K kind, M model) {
        this.kind = kind;
        this.model = model;
    }

    public K getKind() {
        return kind;
    }

    public void setKind(K kind) {
        this.kind = kind;
    }

    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
    }
}
```