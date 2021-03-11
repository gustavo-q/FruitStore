package com.gustavo.controller;

import com.gustavo.po.ItemOrder;
import com.gustavo.service.ItemOrderService;
import com.gustavo.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.util.StringUtils.isEmpty;


@Controller
@RequestMapping("/itemorder")
public class ItemOrderController {

    @Autowired
    private ItemOrderService itemOrderService;

    /**
     * 订单管理列表
     * @return
     */
    @RequestMapping("/findBySql")
    public String findBySql(ItemOrder itemOrder, Model model){
        //分页查询
        String sql = "select * from item_order where 1=1";
        if (!(isEmpty(itemOrder.getCode()))){
            sql += " and code like '%"+itemOrder.getCode()+"%'";
        }
        sql +=" order by id desc";
        Pager<ItemOrder> pagers = itemOrderService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        //存储查询条件
        model.addAttribute("obj",itemOrder);
        return "itemorder/itemorder";

    }



}
