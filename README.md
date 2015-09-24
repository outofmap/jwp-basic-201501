#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
1. tomcat의 웹 컨테이너는 배포된 웹 어플리케이션이 어떤 것이 있는지 체크하면서 관련 서블릿 클래스 파일을 검색합니다.
2. 클래스를 로딩합니다.
3. 컨테이너는 알아서 디폴트 생성자를 실행해서 인자가 없는 상태로 인스턴스를 만든다.( 이 상태에서는 아직 클라이언트의 요청을 처리할 수 없는 상태이지만 인스턴스는 생김.)
4. 컨테이너가 init() 메소드를 호출해서 초기합니다. 이 때, 원하는대로 init()메소드를 재정의해서 초기화 코드에 데이터베이스 접속, 다른 객체에 서블릿을 등록하는 것과 같은 설정을 초기화 할 수 있습니다. 따로 재정의 하지 않으면, GenericServlet()의 init()이 실행됩니다. 
* 이후에 클라이언트 요청이 들어오면 컨테이너는 다시 초기화를 해서 서블릿 인스턴스를 새로 만들지 않습니다. 인스턴스는 한번만 생성되고, 다수의 스레드를 실행해서 요청을 처리합닏. 요청 당 하나씩의 스레드가 담당해서 처리합니다. 


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
- 추측 : Listcontroller와 showcontroller가 공유하는 부분에서 문제가 발생할 수 있다.(?)
--------------------------------
## 과제 수행 중 질문. 느낀점    
### dispatcherServlet 은 뭔가요?

[dispatcherServlet](http://egloos.zum.com/springmvc/v/504151)        

- static 성격의 외부 자원 ? ( 무슨 말인지 모르겠다.)
- @MVC 는 어떤 것의 표기인가?
- 담당하는 역할은 F3덕분에 많이 알게 되었다. 

* 추측1 : 스프링에서 사용하는 전략의 일까?(MVC를 쉽게 구현하도록 도와주는 역할?)
이 프로젝트에서 spring이라는 단어도 찾아볼 수 없으므로 dispatcher는 스프링에서 사용하는 서블릿이 아니다. 그렇지만 MVC 구현하도록 도와주는 역할을 하고 있다는 것은 느낄 수 있었다. 
[프론트 컨트롤러?](http://kimddochi.tistory.com/85)
'프론트 컨트롤러(=Front Controller)이다. 자바 서버의 Servlet 컨테이너에서 HTTP 프로토콜을 통해 들어오는 모든 요청을 프리젠테이션 계층의 제일앞에 둬서 중앙집중식으로 처리할 수 있는 컨트롤러이다.'
이 말은 이해가 된다.
'DispatcherServlet은 서블릿 컨테이너가 생성하고 관리하는 오브젝트이다.
즉, 스프링이 관여하는 오브젝트가 아니므로 직접 DI를 해줄 방법이 없다. 대신 web.xml에서 설정한 웹어플리케이션 컨텍스를 참고하여 필요한 전략을 DI하여 사용할 수 있다.'
이 말은 이해가 안된다.

#### 4. 한글 인코딩
[한글인코딩 참조](http://maxim365.tistory.com/?page=162#recentTrackback) tomcat7부터는 web.xml 매핑없이 annotation만으로 처리 가능! (한글 인코딩 문제가 명확해졌다.)

#### 5. JSTL 
jstl 반복문 함수에서는 items 는 서블릿에서 만들어둔 객체와 매핑되는 것을 명확히 알게되었다. 

#### 6.
```var params = "questionId=" + answerForm[0].value + "&writer=" + answerForm[1].value + "&contents=" + answerForm[2].value;```
params가 하나의 긴 string이라고 생각해서 따로 parse해야 하는지, 고민하다가 getparameter 메소드를 쓰니, 원하는 속성값들을 가져올 수 있었다. 
getparameter메소드를 봐도 string을 자르는 코드는 없었는데 어떻게 가능한 것인지 궁금하다. 


#### 7. jsonView 클래스에서 render()메소드의 mapper.writeValueAsString(model)가 어떻게 일하는 것인지 모르겠다. 

#### 9. api와 모바일로 서비스하는 것은 무슨 상관관계가 있을까?

#### 10. 서블릿마다 QuestionDao와 AnswerDao를 생성하면 성능면에서 어떻게 문제가 되는지 궁금하다.




