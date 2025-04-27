package org.msh.serv.service.generics;

import org.msh.common.exceptions.MyExc;

public interface MyAddGenericService<T> {

    T addSrv(T dto) throws MyExc;
}
