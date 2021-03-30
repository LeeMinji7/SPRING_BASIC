package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;
// controller : 사용자로 부터 접속 받기 위한 주소들의 묶음들을 모아놓는 곳

@RestController // spring에게 여기에 controller 로 활용할거야 라고 표시
@RequestMapping("/api") // Localhost:8080/api 로 매핑
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // Localhost:8080/api/getMethod
    public String getRequest(){

        return "Hi getMethod";
    } // 사용자의 요청이 이 메소드로 들어옴

    // ## query-parameter 인자 받는 방법 1 : requestparam 지정 ##
    @GetMapping("/getParameter") // Localhost:8080/api/getParameter?id=1234&password=abcd // [의미]  get에 대해 처리, method 지정 안하고 주소만 지정
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){ // 요청으로 부터 인자를 받음
        // requestparam(pwd)이랑 password랑 다르면 name을 씀 => password라는 이름으로 들어올 것이라는 알려줌
        String password = "bbbb";  // requestparam로 들어올 수 있는 것은 local변수로 사용하지 않는 것이 좋음
        System.out.println("id: " + id );
        System.out.println("password: " + pwd );

        return id + pwd;
    }

    //Localhost:8080/api/multiParameter?account=abcd&email=study@gmail.com&page=10
    // ## query-parameter 인자 받는 방법 2 : 객체로 받기 ##
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){

        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());
        System.out.println(searchParam); // SearchParam{account='abcd', email='study@gmail.com', page=10}

        return searchParam; // 객체 리턴 => JSON형식으로 변환 : {"account":"abcd","email":"study@gmail.com","page":10}

    }
}
