# WebFlux

Webflux는 Reactive system을 개발하기 위하여 Spring에서 제공하는 라이브러리이다. Spring Framework는 원래 Servlet API, 컨테이너용을 간단하고 편리하게 사용하기 위해 개발된 프레임워크였다(spring-webmvc를 제공). 그리고 근본은 Blocking, Synchronous(동기)방식이다. 추후 Servlet 3.1에서 비동기 처리 방식을 지원했지만, 서블릿은 응답을 기다리는 동안 Pool안에 있는 스레드를 지연시킬 수 있어서 Reactive해야 하는 요구를 충족하지 못햇다. 그래서 스프링 5.x 버전부터 Spring WebFlux가 추가되었고 웹 요청 처리를 Reactive하게 다루고 Non-Blocking을 구현할 수 있도록 초점을 맞추고 있다(spring-webflux를 제공).


![](https://static.packt-cdn.com/products/9781788995979/graphics/d2af6e5b-5d26-448d-b54c-64b42d307736.png)

아래 다이어그램은 이 두가지가 어떤 공통점과 차이점을 가지고 있는지 알 수있다. 

![MVC_WebFlux](https://docs.spring.io/spring/docs/current/spring-framework-reference/images/spring-mvc-and-webflux-venn.png)



이와같이 Webflux는 스프링 MVC의 일부 기능을 가져와서 별도의 리액티브 웹 프로그래밍을 구현할 수 있다.
![WebFlux Stack](data/webflux-overview.png)

위 그림과 같이 스프링 MVC는 컨테이너는 서블릿 컨테이너가 필요한 자바 서블릿 API의 상위 계층이다. 스프링 Webflux는 서블릿 API와 서블릿 컨테이너를 연계하지 않고 Netty가 기본이며 Undertow, Tomcat, Jetty, Servlet3.1 이상을 지원한다.


**리액티브 스프링 MVC?**

> 스프링 Webflux에서 컨트롤러가 Mono, Flux같은 리액티브 타입을 반환하지만, 기존 스프링 MVC에서도 리액티브 타입을 반환할 수 있다. 차이점으로 Webflux는 요청이 이벤트 루프로 처리되는 리액티브 웹 프레임워크인 반면, 스프링 MVC는 다중 스레드에 의존하여 다수의 요청을 처리하는 서블릿 기반 웹 프레임워크이다.

## 1.스프링 Webflux 의존성 추가

* maven - pom.xml
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
    <version>2.3.5.RELEASE</version>
</dependency>
```

* gradle - build.gradle
```gradle
implementation 'org.springframework.boot:spring-boot-starter-webflux'
```


## 2. 리액티브 컨트롤러

```java
@GetMapping("/fruits")
public Flux<Fruit> getFruits() {
    String[] fruits = new String[]{"Apple", "Banana", "Grape", "Orange", "Melon"};
    List<Fruit> fruitList = new ArrayList<>();
    for (String s : fruits) {
        fruitList.add(new Fruit(s));
    }
    return Flux.fromIterable(fruitList);
}
```

## 3. 리액티브 API를 정의하기 위한 새로운 함수형 프로그래밍 모델

- RouterFunction : 일치하는 요청이 어떻게 핸들러에게 전달되어야 하는지 선언
-  RouterPredicates : 처리될 요청의 종류를 선언
- ServerRequest : HTTP 요청을 나타내며, Header 와 Body 의 정보를 사용할 수 있다.
- ServerResponse : HTTP 응답을 나타내며, Header 와 Body 의 정보를 포함한다.
      
```java
   /**
     * RouterFunction : 일치하는 요청이 어떻게 핸들러에게 전달되어야 하는지 선언
     * RouterPredicates : 처리될 요청의 종류를 선언
     * ServerRequest : HTTP 요청을 나타내며, Header 와 Body 의 정보를 사용할 수 있다.
     * ServerResponse : HTTP 응답을 나타내며, Header 와 Body 의 정보를 포함한다.
     * @return RouterFunction
     */
    @Bean
    public RouterFunction<?> helloRouterFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/")
                        , request -> ServerResponse.ok().body(Mono.just("hello world!"), String.class))
                .andRoute(RequestPredicates.GET("/hello")
                        , request -> ServerResponse.ok().body(Mono.just("Say Hello"), String.class));
    }
```