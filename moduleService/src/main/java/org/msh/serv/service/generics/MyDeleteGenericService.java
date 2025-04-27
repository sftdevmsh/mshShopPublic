package org.msh.serv.service.generics;

public interface MyDeleteGenericService<T> {

    Boolean deleteByIdSrv(Long id);
}
