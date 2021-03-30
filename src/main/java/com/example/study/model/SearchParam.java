package com.example.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 1) lombok 설정O

@Data // 기본생성자, get/set메소드 생성
@AllArgsConstructor // 모든 매개변수 가지는 생성자 추가
@NoArgsConstructor // 기본 생성자 추가
public class SearchParam {

    private String account;
    private String email;
    private int page;

    // 2) lomlok 설정X
   /* public SearchParam(){

    }
    public SearchParam(String account, String email, int page){

    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "SearchParam{" +
                "account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", page=" + page +
                '}';
    }*/
}
