package com.fanbo.controller;


import com.fanbo.model.basemodel.ListResponse;
import com.fanbo.model.basemodel.Response;
import com.fanbo.model.request.PizzaRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//《Vue2.x全家桶实战点餐系统(axios/fetch/vuex/router/导航守卫)》接口
@RestController
@RequestMapping(value = "/pizza")
public class PizzaController {

    //数据仓库
    private List<PizzaRequest> pizzaRepository = new ArrayList<>();

    //添加pizza
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response add(@RequestBody PizzaRequest request){
        Response response = new Response();
        pizzaRepository.add(request);
        return response;
    }

    //pizza列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ListResponse list(){
        ListResponse response = new ListResponse();
        response.setDataList(pizzaRepository);
        return response;
    }

    //删除pizza
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Response delete(@RequestParam(value = "name", required = false) String name){
        Response response = new Response();
        if (!CollectionUtils.isEmpty(pizzaRepository)) {
            for (PizzaRequest request : pizzaRepository) {
                if (request.getName().equals(name)) {
                    pizzaRepository.remove(request);
                    break;
                }
            }
        }
        return response;
    }
}
