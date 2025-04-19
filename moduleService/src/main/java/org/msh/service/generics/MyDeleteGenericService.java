package org.msh.service.generics;

public interface MyDeleteGenericService<T> {

    Boolean deleteByIdSrv(Long id);
}
