package com.fanbo.controller;

import com.fanbo.domain.EsEntity;
import com.fanbo.model.basemodel.ObjectResponse;
import com.fanbo.model.basemodel.Response;
import com.fanbo.service.EsEntityService;
import com.fanbo.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/es")
public class EsEntityController {

    @Autowired
    private EsEntityService esEntityService;

    private Logger logger = LoggerFactory.getLogger(EsEntityController.class);

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Response save(long id, String name){
        logger.info("start save data to es.");
        Response response = new Response();

        if (id > 0 && StringUtil.isNotNull(name)) {
            EsEntity esEntity = new EsEntity(id, name);
            esEntityService.saveEntity(esEntity);
        }else {
            response.setMessage("param error");
            response.setStatus(0);
        }
        return response;
    }

    @RequestMapping(value = "/saveList", method = RequestMethod.GET)
    public Response saveList(long id, String name){
        logger.info("Started saveList data to es.");
        Response response = new Response();

        if (id > 0 && StringUtil.isNotNull(name)) {
            List<EsEntity> esEntityList = new ArrayList<>();
            esEntityList.add(new EsEntity(id, name));
            esEntityList.add(new EsEntity(id + 1, name + "2"));
            esEntityList.add(new EsEntity(id + 2, name + "3"));
            esEntityService.saveEntity(esEntityList);
        }else {
            response.setMessage("param error");
            response.setStatus(0);
        }
        return response;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ObjectResponse search(String name){
        ObjectResponse response = new ObjectResponse();
        if (StringUtil.isNotNull(name)){
            response.setData(esEntityService.searchEntity(name));
        }else {
            response.setMessage("param error");
            response.setStatus(0);
        }
        return response;

    }

}
