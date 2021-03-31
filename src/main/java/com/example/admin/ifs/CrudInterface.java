package com.example.admin.ifs;

import com.example.admin.model.network.Header;

public interface CrudInterface {

    Header create(); // 추후에 어떠한 매개변수 받을 건지 추가
    Header read(Long id);
    Header update();
    Header delete(Long id);
}
