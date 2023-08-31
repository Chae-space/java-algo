# 스프링 부트3와 테스트
테스트 코드는 작성한 코드가 의도대로 잘 동작하고 예상치 못한 문제가 없는지 확인할 목적으로 작성하는 코드이다. 보통 테스트 코드 관련 공부는 본 개발 공부를 하느라 미루는 경우가 많다. 하지만 유지보수에도 매우 좋고, 코드 수정 시 기존 기능이 제대로 작동하지 않을까 봐 걱정하지 않아도 된다는 장점이 있는 테스트 코드 공부를 꼭 추천한다.      
&nbsp;
&nbsp;
&nbsp;
## 테스트 코드란?

테스트 코드는 test 디렉터리 작업한다. 우리의 프로젝트에도 이미 test디렉터리가 있다.

![image](https://github.com/Chae-space/java-algo/assets/90403366/c9819158-b678-4086-9937-ad48cef76a90)


테스트 코드에도 다양한 패턴이 있다. 여기서 사용 **given-when-then** 패턴은 테스트 코드를 세 단계로 구분해 작성하는 방식을 말한다. **(1)given**은 테스트 실행을 준비하는 단계, **(2)when**은 테스트를 진행하는 단계, **(3)then**은 테스트 결과를 검증하는 단계이다. 예를 들어 새로운 메뉴를 저장하는 코드를 테스트한다고 가정했을 때 테스트 코드를 다음과 같이 given, when, then을 적용해 구현한다.

*ex) given-when-then 패턴의 테스트 코드 예*
```java
@DisplayName("새로운 메뉴를 저장한다.")
@Test
public void saveMenuTest(){
//given : 메뉴를 저장하기 위한 준비 과정
final String name= "아메리카노";
final int price = 2000;

final Menu americano = new Ment(name, price);

//when : 실제로 메뉴를 저장
final long saveId = menuService.save(americano);
//then : 메뉴가 잘 추가되었는지 검증
final Menu saveMenu = menuService.findById(saveId).get();
assertThat(savedMenu.getName()).isEqualTo(name);
assertThat(savedMenu.getPrice()).isEqualTO(price);
}
```

-> 코드를 보면 세 부분으로 나누어져 있다. 메뉴를 저장하기 이해 준비하는 과정인 given절, 실제로 메뉴를 저장하는 when절, 메뉴가 잘 추가되었는지 검증하는 then절로 나누어져 있다. 우선은 이정도로 테스트 코드가 무엇인지 감만 잡아두고 본격적인 내용은 아래의 실습을 통해 공부해보자.

&nbsp;
&nbsp;
&nbsp;
&nbsp;

## 스프링 부트3와 테스트

- 스프링 부트는 애플리케이션을 테스트하기 위한 도구와 어노테이션을 제공한다. spring-boot-starter-test 스타터에 테스트를 위한 도구가 모여 있다.



#### 스프링 부트 스타터 테스트 목록
- JUnit : 자바 프로그래밍 언어용 단위 테스트 프레임워크
- Spring Test & Spring Boot Test : 스프링 부트 애플리케이션을 위한 통합 테스트 지원
- AssertJ : 검증문인 어설션을 작성하는 데 사용되는 라이브러리
- Hamcrest : 표현식을 이해하기 쉽게 만드는 데 사용하는 Matcher 라이브러리 
- Mockito : 테스트에 사용할 가짜 객체인 목 객체를 쉽게 만들고, 관리하고, 검증할 수 있게 지원하는 테스트 프레임워크
- JSONassert : JSON용 어설션 라이브러리
- JsonPath : JSON 데이터에서 특정 데이터를 선택하고 검색하기 위한 라이브러리

-> 이 중에서는 *JUnit*과 *AssertJ*를 가장 많이 사용하므로, 이 도구를 자세히 알아보.




## JUnit이란?

JUnit은 **자바 언어를 위한 단위 테스트 프레임워크**이다. 단위 테스트라는 말이 생소할 수 있는데 단위테스트란, 작성한 코드가 의도대로 작동하는지 작은 단위로 검증하는 것을 의미한다. 이때 단위는 보통 메서드가 된다. JUnit을 사용하면 단위 테스트를 작성하고 테스트하는 데 도움을 준다. 사용법도 간단하고 테스트 결과가 직관적이기 때문에 JUnit을 사용하는 걸 추천한다. 구체적인 JUnit의 특징은 다음과 같다.


#### <JUnit의 특징>
- 테스트 방식을 구분할 수 있는 어노테이션을 제공
- @Test 어노테이션으로 메소드를 호출할 때마다 새 인스턴스를 생성, 독립 테스트 가능
- 예상 결과를 검증하는 어설션 메소드 제공
- 사용 방법이 단순, 테스트 코드 작성 시간이 적음
- 자동 실행, 자체 결과를 확인하고 즉각적인 피드백을 제공

&nbsp;
&nbsp;
&nbsp;
&nbsp;
#### JUnit으로 단위 테스트 코드 만들기

**<1단계>** [src->test->java] 폴더에 JUnitTest.java 파일을 생성하고 코드를 따라해보자.
```java
public class JUnitTest {
@DisplayName("1+2는 3이다")   //테스트 이름
 @Test  //테스트 메소드
   public void junitTest(){
    int a = 1;
    int b = 2;
    int sum =3;

    Assertions.assertEquals(sum, a+b);  //값이 같은지 확인
   }
 }
```
- **@DisplayName** 어노테이션은 테스트 이름을 명시한다.
- **@Test** 어노테이션을 붙인 메소드는 테스트를 수행하는 메소드가 된다. JUnit은 테스트끼리 영향을 주지 않도록 각 테스트를 실행할 떄마다 테스트를 위한 실행 객체를 만들고 테스트가 종료되면 실행 객체를 삭제한다. 
- **junitTest() 메소드**에 작성한 테스트 코드 설명을 하자면, 이 테스트에서는 JUnit에서 제공하는 검증 메소드인 assertEquals()로 a+b와 sum의 값이 같은지 확인한다. 이를 통해 assertEquals() 메소드의 사용법을 자연스럽게 알았을 것이다. assertEquals() 메소드의 첫번째 인수에는 기대하는 값, 두번째 인수에는 실제로 검증할 값을 넣어준다.



**<2단계>** 실제로 테스트 코드가 잘 동작하는지 확인하려면 재생버튼(![image](https://github.com/Chae-space/java-algo/assets/90403366/001dd0a4-840e-45c2-9c50-2b7b7f10c4cd))을
 클릭하고 [Run 'JunitTest']를 클릭해서 테스트를 실행해보자.
테스트가 끝나면 콘솔창에 테스트 성공/실패 결과가 출력된다. 실패했을 경우엔 원인이 로그로 뜨므로 그부분을 수정하려고 노력해야 한다.


*ex) 실패하는 테스트 코드의 예*
```java
public class JUnitTest {
@DisplayName("1 + 3는 4이다")
    @Test
    public void junitFailedTest() {
        int a = 1;
        int b = 3;
        int sum = 3;

        Assertions.assertEquals(a + b, sum);  //실패하는 케이스
    }
}
```

-> 실패용 테스트 케이스를 실행하면 테스트가 실패했다는 표시와 함께 기댓값과 실제로 받은 값을 비교해서 알려준다. 이렇게 JUnit은 테스트 케이스가 하나라도 실패하면 전체 테스트를 실패한 것으로 보여준다.


#### JUnit 어노테이션 설명

```java
import org.junit.jupiter.api.*;

public class JUnitCycleTest {

    @BeforeAll  //전체 테스트를 시작하기 전에 1회 실행하므로 메소드는 static으로 선언
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach  //테스트 케이스를 시작하기 전마다 실행
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    @AfterAll   //전체 테스트를 마치고 종료하기 전에 1회 실행하므로 메소드는 static으로 선언
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}
```

- @BeforeAll
\
전체 테스트를 시작하기 전에 처음으로 한 번만 실행한다. 예를 들어 데이터베이스를 연결해야 하거나 테스트 환경을 초기화할 떄 사용된다. 이 어노테이션은 전체 테스트 실행 주기에서 한 번만 호출되어야 하기 때문에 메소들ㄹ static으로 선언해야 한다.



- @BeforeEach
\

테스트 케이스를 시작하기 전에 매번 실행한다. 예를 들어 테스트 메소드에서 사용하는 객체를 초기화하거나 테스트에 필요한 값을 미리 넣을 때 사용할 수 있다. 각 인스턴스에 대해 메소드를 호출해야 하므로 메소드는 static이 아니어야 한다.


- @AferAll
\
전체 테스트를 마치고 종료하기 전에 한 번만 실행한다. 예를 들어 데이터베이스 연결을 종료할 때나 공통적으로 사용하는 자원을 해제할 때 사용할 수 있다. 전체 테스트 실행 주기에서 한 번만 호출되어야 하므로 메소드를 static으로 선언해야 한다.

