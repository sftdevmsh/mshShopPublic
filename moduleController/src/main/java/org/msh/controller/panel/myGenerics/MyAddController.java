package org.msh.controller.panel.myGenerics;

import org.msh.exceptions.MyExc;
import org.msh.wrapper.PanelApiResponseWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MyAddController<T> {
    @PostMapping("/add")
    PanelApiResponseWrapper<T> addCtrl(@RequestBody T t) throws MyExc;
}
