package org.msh.ctrl.controller.panel.myGenerics;

import org.msh.ctrl.wrapper.PanelApiResponseWrapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MyDeleteController<T> {
    @DeleteMapping("/del/{id}")
    PanelApiResponseWrapper<Boolean> deleteByIdCtrl(@PathVariable Long id);
}
