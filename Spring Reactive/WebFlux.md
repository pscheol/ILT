# WebFlux

Webflux는 Reactive system을 개발하기 위하여 Spring에서 제공하는 라이브러리이다. Spring Framework는 원래 Servlet API, 컨테이너용을 간단하고 편리하게 사용하기 위해 개발된 프레임워크였다(spring-webmvc를 제공). 그리고 근본 Blocking, Synchronous(동기)방식이다. 추후 Servlet 3.1에서 비동기 처리 방식을 지원했지만, 서블릿은 응답을 기다리는 동안 Pool안에 있는 스레드를 지연시킬 수 있어서 Reactive해야 하는 요구를 충족하지 못햇다. 그래서 스프링 5.x 버전부터 Spring WebFlux가 추가되었고 웹 요청 처리를 Reactive하게 다루고 Non-Blocking을 구현할 수 있도록 초점을 맞추고 있다(spring-webflux를 제공).


![](https://static.packt-cdn.com/products/9781788995979/graphics/d2af6e5b-5d26-448d-b54c-64b42d307736.png)

아래 다이어그램은 이 두가지가 어떤 공통점과 차이점을 가지고 있는지 알 수있다. 

![MVC_WebFlux](https://docs.spring.io/spring/docs/current/spring-framework-reference/images/spring-mvc-and-webflux-venn.png)




참조

https://subscription.packtpub.com/book/application_development/9781788995979/5/ch05lvl1sec40/spring-mvc-versus-webflux