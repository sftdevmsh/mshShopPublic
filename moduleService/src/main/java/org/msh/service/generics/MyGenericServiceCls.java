package org.msh.service.generics;

public class MyGenericServiceCls {

    public Integer validatePage(Integer page) {
        if(page < 1)
            page = 1;
        return page;
    }

    public Integer validateSize(Integer size) {
        if(size < 10)
            size = 10;
        return size;
    }


}
