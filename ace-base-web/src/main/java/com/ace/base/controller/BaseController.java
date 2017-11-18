package com.ace.base.controller;

import com.ace.base.beans.Resp;
import com.ace.base.beans.RespPage;
import com.ace.base.model.Base;
import com.ace.base.model.BaseExample;
import com.ace.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Base")
public class BaseController {

    @Autowired
    private BaseService service;


    @RequestMapping("/list")
    @ResponseBody
    public Resp list(HttpServletRequest request) {
        int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
        BaseExample example = new BaseExample();
        example.setLimitStart((pageNo - 1) * pageSize);
        example.setLimitLength(pageSize);
        long rowcount = service.countByByExample(example);
        List<Base> list = service.findPageByExample(example);
        return new Resp(1, "success", new RespPage<Base>(pageNo, pageSize, rowcount, list));
    }


    @RequestMapping("/get")
    @ResponseBody
    public Resp findById(HttpServletRequest request, long id) {
        return new Resp(1, "save-success", service.findById(id));
    }

    @RequestMapping("/save")
    @ResponseBody
    public Resp save(HttpServletRequest request, Base base) {
        service.save(base);
        return new Resp(1, "save-success");
    }

    @RequestMapping("/update")
    @ResponseBody
    public Resp update(HttpServletRequest request, Base base) {
        service.update(base);
        return new Resp(1, "update-success");
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Resp remove(HttpServletRequest request, long id) {
        service.removeById(id);
        return new Resp(1, "remove-success");
    }
}