package org.msh.serv.service.generics;

import org.msh.common.exceptions.MyExc;

public interface MyUpdateGenericService<T> {

    T updateSrv(T dto) throws MyExc;
}
