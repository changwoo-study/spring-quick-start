BoardWeb
=======
Spring Quick Start 실습 소스 코드 모음.

실습 노트
-----------
* 2016년 09월 13일: Day 03 Class 07까지 실습. Day3 완료.
* 2016년 09월 10일: Day 03 Class 02까지 실습
* 2016년 09월 09일: Day 02 Class 07까지 실습
* 2016년 09월 07일: Day 01 Class 07까지 실습


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


