package org.msh.serv.service.generics;

import org.springframework.data.domain.Page;

import java.util.List;

public interface MyFindGenericService<T> {

    List<T> findAllSrv();
    Page<T> findAllSrv(Integer page, Integer size);

    T findByIdSrv(Long id);
}
