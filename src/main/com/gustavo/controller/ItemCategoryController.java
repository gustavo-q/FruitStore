package com.gustavo.controller;

import com.gustavo.base.BaseController;
import com.gustavo.po.ItemCategory;
import com.gustavo.service.ItemCategoryService;
import com.gustavo.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itemcategory")
public class ItemCategoryController extends BaseController {

    @Autowired
    private ItemCategoryService itemCategoryService;


    //分页查询类目列表
    @RequestMapping("findBySql")
    public String findBySql(Model model, ItemCategory itemCategory){
        String sql = "select * from item_category where isDelete = 0 and pid is null order by id";
        Pager<ItemCategory> pagers = itemCategoryService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",itemCategory);
        return "itemcategory/itemCategory";
    }

    /*
    转到一级类目添加页面
     */
    @RequestMapping("add")
    public String add(){
        return "itemcategory/add";
    }

    /**
     * 新增一级类目保存功能
     */
    @RequestMapping("/exAdd")
    public String exAdd(ItemCategory itemCategory){
        itemCategory.setIsDelete(0);
        itemCategoryService.insert(itemCategory);
        return "redirect:/itemcategory/findBySql.action";
    }

    /**
     * 修改一级类目
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/update")
    public String update(Integer id,Model model){
        ItemCategory obj = itemCategoryService.load(id);
        model.addAttribute("obj",obj);
        return "itemcategory/update";
    }


    /**
     * 转到一级类目添加页面
     * @param itemCategory
     * @return
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(ItemCategory itemCategory){
        itemCategoryService.updateById(itemCategory);
        return "redirect:/itemcategory/findBySql.action";
    }


    /**
     * 删除一级类目
     * @param id
     * @return
     */
    @RequestMapping("/del")
    public String del(Integer id){
        //删除本身
        ItemCategory load = itemCategoryService.load(id);
        load.setIsDelete(1);
        itemCategoryService.updateById(load);
        //将下级也删除
        String sql = "update item_category set isDelete=1 where pid="+id;
        itemCategoryService.updateBysql(sql);
        return "redirect:/itemcategory/findBySql.action";
    }

    /**
     * 查看二级类目
     */
    @RequestMapping("/findBySql2")
    public String findBySql2(ItemCategory itemCategory,Model model){
        String sql = "select * from item_category where isDelete=0 and pid="+itemCategory.getPid()+" order by id";
        Pager<ItemCategory> pagers = itemCategoryService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",itemCategory);
        return "itemcategory/itemCategory2";
    }


    /**
     * 转到二级类目添加页面
     * @return
     */
    @RequestMapping("/add2")
    public String add2(int pid,Model model){
        model.addAttribute("pid",pid);
        return "itemcategory/add2";
    }

    /**
     * 新增二级类目保存功能
     * @return
     */
    @RequestMapping("/exAdd2")
    public String exAdd2(ItemCategory itemCategory){
        itemCategory.setIsDelete(0);
        itemCategoryService.insert(itemCategory);
        return "redirect:/itemcategory/findBySql2.action?pid="+itemCategory.getPid();
    }

    /**
     * 转到修改二级类目页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/update2")
    public String update2(int id,Model model){
        ItemCategory obj = itemCategoryService.load(id);
        model.addAttribute("obj",obj);
        return "itemcategory/update2";
    }


    /**
     * 修改二级类目
     * @param itemCategory
     * @return
     */
    @RequestMapping("/exUpdate2")
    public String exUpdate2(ItemCategory itemCategory){
        itemCategoryService.updateById(itemCategory);
        return  "redirect:/itemcategory/findBySql2.action?pid="+itemCategory.getPid();
    }

    /**
     * 删除二级类目
     * @param id
     * @param pid
     * @return
     */
    @RequestMapping("/del2")
    public String del2(int id,int pid){
        ItemCategory load = itemCategoryService.load(id);
        load.setIsDelete(1);
        itemCategoryService.updateById(load);
        return  "redirect:/itemcategory/findBySql2.action?pid="+pid;
    }

}
