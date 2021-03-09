package com.gustavo.controller;

import com.gustavo.base.BaseController;
import com.gustavo.po.Manage;
import com.gustavo.service.ManageService;
import com.gustavo.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    ManageService manageService;

    /**
     * 管理员登录页面
     * @return
     */
    @RequestMapping("login")
    public String login(){
        return "/login/mLogin";
    }

    /**
     * 登录验证
     */
    @RequestMapping("toLogin")
    public String toLogin(Manage manage, HttpServletRequest request){
        Manage byEntity = manageService.getByEntity(manage);
        if (byEntity==null){
         return "redirect:/login/mtuichu";
        }
        request.getSession().setAttribute(Consts.MANAGE,byEntity);
        return "/login/mIndex";
    }


    /**
     * 管理员退出
     */
    @RequestMapping(value = "mtuichu")
    public String mtuichu(HttpServletRequest request){
        request.getSession().setAttribute(Consts.MANAGE,null);
        return "/login/mLogin";
    }

}
