package org.msh.ctrl.controller.panel.myGenerics;

import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MyFindController<T> {
    @GetMapping("/get")
    PanelApiResponseWrapper<List<T>> findAllCtrl(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("/get/{id}")
    PanelApiResponseWrapper<T> findByIdCtrl(@PathVariable Long id);
}
