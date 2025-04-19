package org.msh.service.generics;

import org.msh.exceptions.MyExc;

public interface MyUpdateGenericService<T> {

    T updateSrv(T dto) throws MyExc;
}
