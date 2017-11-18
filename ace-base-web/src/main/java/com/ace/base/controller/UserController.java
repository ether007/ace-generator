package com.ace.base.controller;

import com.ace.base.beans.Resp;
import com.ace.base.beans.RespPage;
import com.ace.base.model.User;
import com.ace.base.model.UserExample;
import com.ace.base.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/list")
    @ResponseBody
    public Resp list(HttpServletRequest request) {
        int pageNo = ServletRequestUtils.getIntParameter(request, "pageNo", 1);
        int pageSize = ServletRequestUtils.getIntParameter(request, "pageSize", 10);
        UserExample example = new UserExample();
        example.setLimitStart((pageNo - 1) * pageSize);
        example.setLimitLength(pageSize);
        long rowcount = service.countByByExample(example);
        List<User> list = service.findPageByExample(example);
        return new Resp(1, "success", new RespPage<User>(pageNo, pageSize, rowcount, list));
    }

    @RequestMapping("/get")
    @ResponseBody
    public Resp findById(HttpServletRequest request, long id) {
        return new Resp(1, "save-success", service.findById(id));
    }

    @RequestMapping("/save")
    @ResponseBody
    public Resp save(HttpServletRequest request, User base) {
        service.save(base);
        return new Resp(1, "save-success");
    }

    @RequestMapping("/update")
    @ResponseBody
    public Resp update(HttpServletRequest request, User base) {
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