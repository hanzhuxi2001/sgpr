package com.bootstrapwithspringboot.webapp.contoller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import com.bootstrapwithspringboot.webapp.model.User;
import com.bootstrapwithspringboot.webapp.service.UserService;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Resource
    UserService userService;

    String password="hanhui.citi";

    @RequestMapping("/")
    public String index() {
        return "redirect:/listPage";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users=userService.getUserList();
        model.addAttribute("users", users);
        return "index";
    }

    @RequestMapping("/listPage")
    public String findBookNoQuery(ModelMap modelMap,@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "20") Integer size,String pass){
        //Page<User> datas = userService.findUserNoCriteria(page, size);
        Page<User> datas = userService.findUserCriteria(page, size,"1");
        modelMap.addAttribute("datas", datas);
        if(password.equalsIgnoreCase(pass)){
            modelMap.addAttribute("isAdmin", "yes");
        }
        return "index";
    }

    @RequestMapping("/add")
    public String add(User user) {
        user.setAge("2018-08-08");
        user.setUpdateDate("2018-08-08");
        userService.save(user);
        if(user.getType().equalsIgnoreCase("1")){
            return "redirect:/listPage";
        }else if(user.getType().equalsIgnoreCase("2")){
            return "redirect:/citizen";
        }else if(user.getType().equalsIgnoreCase("3")){
            return "redirect:/ep";
        }else if(user.getType().equalsIgnoreCase("4")){
            return "redirect:/sp";
        }
        return "redirect:/listPage";
    }

    @RequestMapping("/edit")
    public String toEdit(Model model,Long id,String birth) {
        System.out.print("id"+id);
        System.out.print("birth"+birth);
        User user=userService.findUserById(id);
        model.addAttribute("user", user);
        if(birth!=null&&birth.equalsIgnoreCase(user.getPassword())){
            return "edit";
        }else{
            return "redirect:/listPage"; 
        }
        
    }

    @RequestMapping("/editSave")
    public String edit(User user) {
        user.setAge("2018-08-08");
        user.setUpdateDate("2018-08-08");
        userService.edit(user);
        if(user.getType().equalsIgnoreCase("1")){
            return "redirect:/listPage";
        }else if(user.getType().equalsIgnoreCase("2")){
            return "redirect:/citizen";
        }else if(user.getType().equalsIgnoreCase("3")){
            return "redirect:/ep";
        }else if(user.getType().equalsIgnoreCase("4")){
            return "redirect:/sp";
        }
        return "redirect:/listPage";
    }


    @RequestMapping("/delete")
    public String delete(Long id,String pass) {
        if(password.equalsIgnoreCase(pass)){
            userService.delete(id);
        }
        return "redirect:/listPage";
    }

    @RequestMapping("/citizen")
    public String findBookNoQueryCitizen(ModelMap modelMap,@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "20") Integer size,String pass){
        Page<User> datas = userService.findUserCriteria(page, size,"2");
        modelMap.addAttribute("datas", datas);
        if(password.equalsIgnoreCase(pass)){
            modelMap.addAttribute("isAdmin", "yes");
        }
        return "citizenIndex";
    }

    @RequestMapping("/ep")
    public String findBookNoQueryEP(ModelMap modelMap,@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "20") Integer size,String pass){
        Page<User> datas = userService.findUserCriteria(page, size,"3");
        modelMap.addAttribute("datas", datas);
        if(password.equalsIgnoreCase(pass)){
            modelMap.addAttribute("isAdmin", "yes");
        }
        return "epIndex";
    }
    @RequestMapping("/sp")
    public String findBookNoQuerySP(ModelMap modelMap,@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "20") Integer size,String pass){
        Page<User> datas = userService.findUserCriteria(page, size,"4");
        modelMap.addAttribute("datas", datas);
        if(password.equalsIgnoreCase(pass)){
            modelMap.addAttribute("isAdmin", "yes");
        }
        return "spIndex";
    }
}
