package com.gustavo.controller;

import com.gustavo.po.Item;
import com.gustavo.po.ItemCategory;
import com.gustavo.service.ItemCategoryService;
import com.gustavo.service.ItemService;
import com.gustavo.utils.Pager;
import com.gustavo.utils.SystemContext;
import com.gustavo.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemCategoryService itemCategoryService;

    /**
     * 分页查询商品列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model, Item item){
        String sql = "select * from item where isDelete = 0 ";
        if(!isEmpty(item.getName())){
            sql += " and name like '%" + item.getName() + "%' ";
        }
        sql += " order by id desc";
        Pager<Item> pagers = itemService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",item);
        return "item/item";
    }


    /**
     *转到商品添加页面
     * @return
     */
    @RequestMapping("/add")
    public String add(Model model){
        String sql = "select * from item_category where isDelete = 0 and pid is not null order by id";
        List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
        model.addAttribute("types",listBySqlReturnEntity);
        return "item/add";
    }



    /**
     * 执行添加商品
     */
    @RequestMapping("/exAdd")
    public String exAdd(Item item, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        itemCommon(item, files, request);
        item.setGmNum(0);
        item.setIsDelete(0);
        item.setScNum(0);
        itemService.insert(item);
        return "redirect:/item/findBySql.action";
    }



    /**
     * 新增和更新的公共方法
     */
    private void itemCommon(Item item, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws IOException {
        if(files.length>0) {
            for (int s = 0; s < files.length; s++) {
                String n = UUIDUtils.create();
                String path = SystemContext.getRealPath() + "\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename();
                File newFile = new File(path);
                //通过CommonsMultipartFile的方法直接写文件
                files[s].transferTo(newFile);
                if (s == 0) {
                    item.setUrl1(request.getContextPath()+"\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename());
                }
                if (s == 1) {
                    item.setUrl2(request.getContextPath()+"\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename());
                }
                if (s == 2) {
                    item.setUrl3(request.getContextPath()+"\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename());
                }
                if (s == 3) {
                    item.setUrl4(request.getContextPath()+"\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename());
                }
                if (s == 4) {
                    item.setUrl5(request.getContextPath()+"\\resource\\ueditor\\upload\\" + n + files[s].getOriginalFilename());
                }
            }
        }
        ItemCategory byId = itemCategoryService.getById(item.getCategoryIdTwo());
        item.setCategoryIdOne(byId.getPid());
    }

    /**
     * 转到修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/update")
    public String edit(int id,Model model){
        Item obj = itemService.load(id);
        String sql = "select * from item_category where isDelete = 0 and pid is not null order by id";
        List<ItemCategory> itemCategoryList = itemCategoryService.listBySqlReturnEntity(sql);
        model.addAttribute("obj",obj);
        model.addAttribute("types",itemCategoryList);
        return "item/update";
    }

    /**
     * 修改商品
     * @param item
     * @param files
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(Item item,@RequestParam("file")CommonsMultipartFile[] files,HttpServletRequest request) throws IOException {
        itemCommon(item,files,request);
        itemService.updateById(item);
        return "redirect:/item/findBySql.action";
    }



    /**
     * 商品删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String del(int id){
        Item load = itemService.load(id);
        load.setIsDelete(1);
        itemService.updateById(load);
        return "redirect:/item/findBySql.action";
    }


}
