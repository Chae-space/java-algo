# @PathVariable vs @RequestParam

실무 환경에서는 매개변수를 받지 않는 메소드가 거의 쓰이지 않는다. 웹 통신의 기본 목적은 데이터를 주고받는 것이기 때문에 대부분 매개변수를 받는 메소드를 작성하게 된다. 매개변수를 받을 때 자주 쓰이는 방법 중 하나는 URL자체에 값을 담아 요청하는 것이고, 다른 하나는 쿼리스트링에 값을 전달하여 요청하는 것이다.       

@PathVariable과 @RequestParam은 스프링 프레임워크에서 웹 애플리케이션의 컨트롤러에서 사용되는 어노테이션이다. 이 두 어노테이션은 URL에서 파라미터 값을 추출하는데 사용되며, 주로 웹 요청을 처리하는 메소드의 매개변수에 적용된다. URL 경로에서 특정 변수 값을 추출하는데, 해당 변수의 값을 컨트롤러 메소드에서 활용할 수 있다.
&nbsp;
&nbsp;
&nbsp;

> ex1) http://localhost:8080/user/email/{email} 
> \
ex2) http://localhost:8080/user/emails?email=test@gmail.com
>
- ex1의 경우 파라미터의 값과 이름을 쿼리스트링으로 함께 전달하는 방식으로 게시판 등에서 페이지 및 검색 정보를 함께 전달하는 방식을 사용할 때 많이 사용한다.
- ex2의 경우 Rest api에서 값을 호출할 때 주로 많이 사용한다. 값을 간단히 전달할 때 주로 사용하는 방법이며, GET 요청에서 많이 사용된다.

ex1번처럼 요청을 할 경우에 추가적인 파라미터가 존재할 경우 /api/v1/pet?id=XXX&param=YYY와 같이 변하고, 2번의 경우에는 추가적인 파라미터가 존재할 경우 /api/v1/pet/XXX/YYY 와 같은 형태로 변형된다. 따라서 2번의 경우에는 입력받을 파라미터가 많이 존재한다면 어떤 값이 입력되는지 알기가 힘들다.


&nbsp;
&nbsp;
&nbsp;
&nbsp;

## @PathVariable 이란?

- ex1의 URL을 처리할 때는 @PathVariable을 사용하게 된다.
- 쿼리스트링 형식으로 들어오는 @RequestParam에 비해 URI가 깔끔하다.
- URL 경로에서 특정 변수 값을 추출하기 위해 사용된다. URL 경로에 특정 변수를 포함하여 해당 변수의 값을 컨트롤러 메소드에서 활용할 수 있다.
- URI의 중괄호 안에 있는 값을 @PathVariable의 name값과 일치시켜준다.
- 주로 RESTful 웹 서비스에서 사용된다.

```java
 @GetMapping("/help/{id}")
    public String renderHelpForm(@PathVariable int id, Model model){
        HelpDTO helpDTO = helpService.getHelp(id);
        model.addAttribute("data",helpDTO);

        return "help_answer";
    }
```






\
-> 이 메소드는 중괄호로 표시된 위치의 id값을 받아 요청하는 것을 알 수 있다. 값을 간단히 전달할 떄 주로 사용하는 방법이며, GET 요청에서 많이 사용된다.




## @RequestParam 이란?
- ex2의 URL을 처리할 때는 @RequestParam을 사용한다.
- URL의 쿼리 파라미터 값을 추출하기 위해 사용된다. URL 뒤에 ?로 시작하는 쿼리 스트링에서 파라미터 값을 추출하며, 주로 웹 페이지의 필터링, 페이징 등에 사용된다.
- GET요청을 구현할 @PathVariable을 사용하는 것처럼 URL경로게 값을 담아 요청을 보내는 방법 외에도 쿼리 형식으로 값을 전달할 수도 있다. 즉, URL에서 '?'를 기준으로 우측에 '{키}={값}' 형태로 구성된 요청을 전송하는 방법이다. 애플리케이션에서 이 같은 형식을 처리하려면 @RequestParam을 활용하면 되는데, 매개변수 부분에 @RequestParam 어노테이션을 명시해 쿼리 값과 매핑하면 된다.


```java
//http://localhost:8080/api/v1/request?name=value1&email=value2
@GetMapping(value = "/request1")
   public String getRequestParam1(@RequestParam String name, @RequestParam String email)  {
      return name + " " + email;
   }
```

-> 1번줄을 보면 '?' 오른쪽에 쿼리스트링(query string)이 명시되어 있다. 쿼리스트링에는 키(변수의 이름)가 모두 적혀 있기 때문에 이 값을 기준으로 메소드의 매개변수에 이름을 매핑하면 값을 가져올 수 있다.




