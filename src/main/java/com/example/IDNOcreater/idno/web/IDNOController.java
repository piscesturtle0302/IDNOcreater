package com.example.IDNOcreater.idno.web;

import com.example.IDNOcreater.common.enums.WebErrCode;
import com.example.IDNOcreater.common.util.ResultHelper;
import com.example.IDNOcreater.idno.entity.IDNOForm;
import com.example.IDNOcreater.idno.service.IDNOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/IDNO")
public class IDNOController {

    @Autowired
    IDNOService idnoService = new IDNOService();

    @RequestMapping(value = "/creatNewRecidenceIdNo", method = RequestMethod.GET, consumes = "application/json")
    public Map<String,Object> creatNewRecidenceIdNo(){

        Map<String,Object> result = new HashMap<>();

        result.put("newResidenceIdNo",idnoService.createNewResidenceIdNo());

        return ResultHelper.returnResult(WebErrCode.err200, result);
    }

    @RequestMapping(value = "/checkNewResidenceIdNo", method = RequestMethod.POST, consumes = "application/json")
    public Map<String,Object> checkNewResidenceIdNo(@RequestBody IDNOForm idnoform){

        Map<String,Object> result = new HashMap<>();
        result.put("newResidenceIdNo",idnoform.getNewResidenceIdNo());
        result.put("result",idnoService.checkNewResidenceIdNo(idnoform.getNewResidenceIdNo()));

        return ResultHelper.returnResult(WebErrCode.err200, result);
    }

}
