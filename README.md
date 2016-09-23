BoardWeb
=======
Spring Quick Start 실습 소스 코드 모음.

실습 노트
-----------
* 2016년 09월 24일: Day 05 완료. 책 모두 실습.
* 2016년 09월 22일: Day 05 Class 03까지 실습.
* 2016년 09월 21일: Day 04 완료.
* 2016년 09월 13일: Day 03 Class 07까지 실습. Day3 완료.
* 2016년 09월 10일: Day 03 Class 02까지 실습
* 2016년 09월 09일: Day 02 Class 07까지 실습
* 2016년 09월 07일: Day 01 Class 07까지 실습


Day 05까지 개요
---------------
MyBatis와 JPA를 활용하여 게시판을 운용하는 방법을 실습한다.


Day 04까지의 개요
----------------
모델 2 방식의 스프링을 활용한 MVC 방식의 게시판으로 구현한다. 이전의 XML 구성을 줄이고 어노테이션을 활용하는 방향으로 진행된다. 사용되는 어노테이션의 종류는 다음과 같다.

- @Controller (org.springframework.stereotype.Controller)
- @RequestMapping (org.springframework.web.bind.annotation.ReqeustMapping)
- @ModelAttribute (org.springframework.web.bind.annotation.ModelAttribute)
- @RequestParam (org.springframework.web.bind.annotation.ReqeustParam)
- @SessionAttributes (org.springframework.web.bind.annotation.SessionAttributes)

이제 프레젠테이션 레이어와 비즈니스 레이어를 나눈다. 지금까지 컨트롤러에서 직접 VO와 DAO를 활용했다. 그러나 비즈니스 레이어를 두어 BoardService, BoardServiceImpl이 컨트롤러에서 활용되도록 변경했다.

이 때 applicationContext.xml이 로딩되지 않아 웹 서비스에서 BoardService를 로딩하지 못하는 문제가 발생하는데, 이를 이해 ContextLoaderListener를 등록, 설정하여 이를 해결한다.

407페이지의 그림 3-5는 이와 관련하여 중요한 개념을 보여주고 있다.

Class 04에서 드디어 글 검색 기능이 구현되었다. 왜 이제서야 이것을 구현했는지는 좀 의문.

Class 05에서는 파일 업로드와 예외 화면에 대해 설명한다.


Day 03까지의 개요
-----------------
아주 간단한 게시판 시스템을 Model 1 방식으로 구현했다. 일단 이 방식으로도 모델 코드는 큰 변경 없이 활용할 수 있지만 PHP의 구식 스크립팅처럼 뒤죽박죽인 소스코드를 생성하게 된다. 이것을 점진적으로 Model 2 방식으로 변경해 나가는 것이 이 장의 개요이다.

우선은 아주 나이브한 JSP 파일의 스크립팅에서 자바 서블릿 디스패쳐를 이용한 방식으로 변경한다. 여기서 디스패쳐는 요청받은 URI의 문자열에 따라 적절한 콘트롤러를 붙여주는 식으로 직접 제작을 하게 된다. 처음에는 디스패쳐 로직 안에 모든 것을 집어 넣는 방식으로 변경이 되어 수정이 쉽지 않지만, 차차 매핑 클래스, 뷰 리졸버를 (스프링 프레임워크를 모사하여) 도입해 점진적으로 디스패쳐 코드를 건드리지 않게 된다.

이제 JSP 파일 내부의 <% > 태그로 된 자바 코드를 넣지 않는 과정도 진행된다. EL, JSTL을 이용한 방법을 간단하게 보여준다.

여기까지 모든 MVC 프레임워크의 개요는 직접 코드를 작성하여 진행하였다. 스프링 프레임워크를 사용하기 전에 스프링 프레임워크의 기본 개념을 모두 실습하여 진행해 본 것이다. 이제 Class 05부터는 스프링 프레임워크를 점차 도입해나가기 시작한다. 직접 만든 디스패쳐는 스프링의 디스패쳐로 변경되고, 컨트롤러는 스프링의 컨트롤러 인터페이스를 구현하는 것으로 변경된다.

이 때 314페이지의 그림 5-1은 중요한 개념을 그린다. 컨트롤러는 ModelAndeView라는 객체를 리턴한다.

DispatchServelt 등록 및 컨테이너에 필요한 사항은 xml 설정 파일로 생성된다. DispatchServelet은 web.xml 파일에서 정의된다. 이 xml 파일 안에서 서블릿의 클래스, 이름, 매핑 이름을 정해 줄 수 있다. 이 <이름>-servlet.xml 파일은 스프링이 기본으로 해당 서블릿에 필요한 컨트롤러와 URI의 매핑을 담당한다. 물론 이 파일의 경로는 변경 가능하다. web.xml 파일에서 'contextConfigLocation' 초기화 파라미터를 통해 이 xml 파일은 변경하는 실습도 진행한다.

문자열 인코딩 설정을 예로 필터라는 것을 실습해 보기도 한다. 한글 문자열이 깨지는 일이 있다면 참고할 만한 실습이다.

InternalResourceViewResolver를 이용해 내부적으로만 사용 가능하고, 외부에서는 접근 불가능한 JSP 파일을 관리할 수 있는 방법도 실습니다.


Day 04 개요
-----------
스프링 MVC 개발을 시작한다. 그런데 이전 장에서는 XML 설정으로만 구현을 했는데, 이 경우에는 설정의 양이 지나치게 많아지는 문제가 여전히 발생한다. 그리하여 이 장에서는 XML설정의 양을 경감시키기 위해 어노테이션 기반의 구현으로 변형을 한다.

@Controller 어노테이션은 @Component 어노테이션을 상속받은 것으로, 콘트롤러 클래스에 사용된다.
@RequestMapping은 URI path에 콘트롤러를 대응시켜 준다.

스프링 컨테이너에서 재미있는 점은 VO 클래스를 제작하면, 브라우저를 통해 REQUEST가 전달될 때, 이 안의 키 이름을 분석해 setter 함수의 이름 규칙에 맞는 키의 값은 VO 객체로 자동 생성된다는 점이다. 커맨드 객체라고 부른다.

이 때 @ModelAttribute를 사용하면 템플릿에 사용될 커맨드 객체의 이름을 변경 가능하다. 또 @RequestParam 어노테이션은 커맨드 객체에 등록되지 않은 파라미터를 추출할 수 있다.
@ModelAttribute 어노테이션은 함수에도 사용할 수 있는데, 함수의 리턴된 객체를 템플릿에서 사용할 수 있다. @SessionAttributes 어노테이션은 세션 변수를 조작할 때 사용할 수 있다.

그리고 Class 03에 오면서 우리는 컨트롤러에서 직접적으로 사용한 DAO를 삭제했다. 그리고 DAO대신 이전까지 제작된 Service 객체를 사용하기로 한다. 그런데 지금까지는 이 객체에 대한 서비스 의존성 주입에 대한 설정이 없어 에러가 난다. 의존성 주입 관련 코드는 applicationContext.xml에 존재하기 때문이다. web.xml에서 presentation-layer.xml이라는 웹 설정 파일의 위치를 지정한다. presentation-layer.xml 파일에서 ContextLoaderListener를 등록하여 applicationContext.xml 을 먼저 로딩하는 과정을 거치도록 한다.

파일 업로드 기능을 구현한다. 

- 커맨드 객체에 MultiPartFile 클래스를 멤버로 넣는다.
- pom.xml 파일의 의존성 목록에 commons-fileupload 를 넣는다.
- applicationContext.xml bean 등록을 한다. multipartResolver id로 CommonsMultipartReolver 클래스가 타깃이다


예외 처리를 구현한다. 이따금 자바 기반으로 된 사이트를 방문하면서 본 기억이 있는 듯하다.
어노테이션 기반 구현이 있긴 하지만, 이 부분은 XML 기반의 예외 처리가 더욱 편리한 것으로 보인다.


다국어 처리를 구현한다.

- messageSoure를 아이디로, ResourceBundleMessageSource를 타깃으로 빈 등록한다.
- localeResolver를 아이디로, SessionLocaleResolver를 타깃으로 빈 등록한다.
- mvc:interceptors 태그 안에 LocaleChangeInterceptor를 빈 등록한다. 
- 템플릿에서 <spring:message code="메시지 키 값">으로 메시지를 치환한다.


JSON이나 XML로 데이터를 변환하는 방법을 실습한다. Jackson2와 JAXB2를 이용해 객체를 JSON으로 내보내고, BoardVO를 수정하고, BoardListVO를 생성하여 XML로 내보낸다.

