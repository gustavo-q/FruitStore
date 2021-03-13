package com.gustavo.controller;

import com.gustavo.base.BaseController;
import com.gustavo.po.CategoryDto;
import com.gustavo.po.Item;
import com.gustavo.po.ItemCategory;
import com.gustavo.po.Manage;
import com.gustavo.service.ItemCategoryService;
import com.gustavo.service.ItemService;
import com.gustavo.service.ManageService;
import com.gustavo.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    ManageService manageService;

    @Autowired
    ItemCategoryService itemCategoryService;

    @Autowired
    ItemService itemService;

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

    /**
     * 前台首页
     * @return
     */
    @RequestMapping("/uIndex")
    public String uIndex(Model model, Item item,HttpServletRequest request){
        String sql1 = "select * from item_category where isDelete=0 and pid is null order by name";
        List<ItemCategory> fatherList = itemCategoryService.listBySqlReturnEntity(sql1);
        List<CategoryDto> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(fatherList)){
            for (ItemCategory ic:fatherList){
                CategoryDto dto = new CategoryDto();
                dto.setFather(ic);
                //查询二级leimu
                String sql2 = "select * from item_category where isDelete=0 and pid="+ic.getId();
                List<ItemCategory> childrens = itemCategoryService.listBySqlReturnEntity(sql2);
                dto.setChildrens(childrens);
                list.add(dto);
                model.addAttribute("lbs",list);

            }
        }
        //折扣商品
        List<Item> zks = itemService.listBySqlReturnEntity("select * from item where isDelete=0 and zk is not null order by zk desc limit 0,10");
        model.addAttribute("zks",zks);

        //热销商品
        List<Item> rxs = itemService.listBySqlReturnEntity("select * from item where isDelete=0 order by gmNum desc limit 0,10");
        model.addAttribute("rxs",rxs);

        return "login/uIndex";
    }

}
