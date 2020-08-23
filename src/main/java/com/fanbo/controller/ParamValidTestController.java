package com.fanbo.controller;

import com.fanbo.model.basemodel.Response;
import com.fanbo.model.request.ValidModel;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ParamValidTestController {

    @PostMapping(value = "/param/valid/test")
    public Response paramValid(@Valid ValidModel validModel, BindingResult result) {
        Response response = new Response();
        if (result.hasFieldErrors()) {
            List<FieldError> filedErrorList = result.getFieldErrors();
//                filedErrorList.stream().forEach(item -> Assert.isTrue(false, item.getDefaultMessage()));
            String errorParamInfo = filedErrorList.stream().map(item -> item.getDefaultMessage()).collect(Collectors.joining(","));
            response.setStatus(0);
            response.setMessage(errorParamInfo);
        }

        return response;
    }

}
