#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* Servlet container의 초기화 과정이 무엇인지 모르겠다.


#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* client : http://localhost:8080은
http 통신규악에 따라 localhost라는 주소,port번호는 8080으로 접근을 요청함.

* server : 
1. /이하에 요청하는 resource의 path가 없으므로 index.jsp를 요청한 것으로 간주함.   
2. index.jsp 에서 301 Moved Permanently 라는 응답코드를 client에 보내고, Redirectiont URI가 "/list.next"라고 client에 알려준다.

* client : 
서버가 알려준 URI로 다시 요청을 보낸다. 

* sever : 

1. dispatcher servlet에 도착함.

2. findController 메소드로 요청 URI(/list.next)에 맞는
controller에 mapping함. 
controller를 Listcontroller 객체가 생성해 초기화한다.


3. ListController의 execute 메소드가 실행됨. viewname이 "list.jsp"인 view를 인스턴트로 생성해 ModelAndView mav 변수에 초기화 한다.

4. mav의 view(3에서의 list.jsp)를 가져와서 render method를 실행해 client에 보낸다.

* client : 
서버한테 index.jsp를 받아서 브라우저에 보여준다.

###Questions
- 왜 dispatcher에 먼저 도착할까? 
- View class에서 render메소드는 어떻게 페이지를 보여주는걸까? 
- list.next가 주소인데 list.jsp를 주소로 바로 쓰지 않는 이유는 뭘까? 
- 요청한 자원을 client에 보내기까지 ModelAndView를 만들고 다시 View를 가져오는 과정이 길게 느껴지는데 이렇게 하는 이유가 무엇일까?

#### 8. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* OS시간에 쓰레드라는 개념을 배웠는데, JAVA에서의 쓰레드와 같은 것인지 모르겠다. 

--------------------------------
#### dispatcherServlet 은 뭔가요?

[dispatcherServlet](http://egloos.zum.com/springmvc/v/504151)        

- static 성격의 외부 자원 ? ( 무슨 말인지 모르겠다.)
- @MVC 는 어떤 것의 표기인가?
- 담당하는 역할은 F3덕분에 많이 알게 되었다. 

* 추측1 : 스프링에서 사용하는 전략의 같은 건가????(MVC를 쉽게? 구현하도록 도와주는?)      
[프론트 컨트롤러?](http://kimddochi.tistory.com/85)
'프론트컨트롤러(=Front Controller)이다. 자바 서버의 Servlet 컨테이너에서 HTTP 프로토콜을 통해 들어오는 
모든 요청을 프리젠테이션 계층의 제일앞에 둬서 중앙집중식으로 처리할 수 있는 컨트롤러이다.'
이 말은 이해가 된다.
'DispatcherServlet은 서블릿 컨테이너가 생성하고 관리하는 오브젝트이다.
즉, 스프링이 관여하는 오브젝트가 아니므로 직접 DI를 해줄 방법이 없다. 대신 web.xml에서 설정한 웹어플리케이션 컨텍스를 참고하여 필요한 전략을 DI하여 사용할 수 있다.'
이 말은 이해가 안된다.

#### 4. 한글 인코딩
[한글인코딩 참조](http://maxim365.tistory.com/?page=162#recentTrackback) tomcat7부터는 web.xml 매핑없이 annotation만으로 처리 가능! ( 한글 인코딩 문제가 명확해졌다.)

#### 5. JSTL 
jstl 반복문 함수에서는 items 는 서블릿에서 만들어둔 객체와 매핑되는 것을 명확히 알게되었다. 

#### 6.7. AJAX를 구현하면서 client에서 보내준 data를 servlet에서 어떻게 가져오는지 잘 모르겠다.

#### 9. api를 추가하는 것과 다른 문제에서 기능을 구현하거나 추가하는 것과 의미가 다른 것인지 모르겠다. 

#### 10. 서블릿마다 QuestionDao와 AnswerDao를 생성하면 성능면에서 어떻게 문제가 되는지 궁금하다.




