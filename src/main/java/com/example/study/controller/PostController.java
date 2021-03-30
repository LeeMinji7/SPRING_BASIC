package com.example.study.controller;


import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // class에 대한 requestmapping은 주소 겹쳐도 상관 없음
public class PostController {
    // post 사용 => html <Form>태그를 사용할 때 / AJAX에서 비동기화하여 검색할 때 사용
    // post : http할 때 post body에 data 집어넣어 보내겠음
    // request할 때 form형태 => json, xml, multipart-from / text-plain

//    @RequestMapping(method = RequestMethod.POST, path = "/postMethod")
//    @PostMapping(value = "/postMethod", produces = {"application-json"}) // produces : 형태 지정, 기본: json
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

    @PutMapping("/putMethod")
    public void put(){

    }

    @PatchMapping("/patchMethod")
    public void patch(){

    }

}
