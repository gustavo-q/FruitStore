package com.gustavo.controller;


import com.gustavo.po.Message;
import com.gustavo.service.MessageService;
import com.gustavo.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.util.StringUtils.isEmpty;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 留言列表
     * @param message
     * @param model
     * @return
     */
    @RequestMapping("/findBySql")
    public String findBySql(Message message, Model model){
        String sql = "select * from message where 1=1";
        if (!isEmpty(message.getName())){
            sql+="  and name like '%"+message.getName()+"%'";
        }
        sql+=" order by id desc";
        Pager<Message> pagers = messageService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",message);
        return "message/message";
    }

        @RequestMapping("/delete")
        public String delete(Integer id){
            messageService.deleteById(id);
            return "redirect:/message/findBySql";
        }







}
