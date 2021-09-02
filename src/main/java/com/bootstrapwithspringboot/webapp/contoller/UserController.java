package com.bootstrapwithspringboot.webapp.contoller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.bootstrapwithspringboot.webapp.common.CodeUtil;
import com.bootstrapwithspringboot.webapp.model.School;
import com.bootstrapwithspringboot.webapp.model.Schools;
import com.bootstrapwithspringboot.webapp.model.User;
import com.bootstrapwithspringboot.webapp.service.SchoolService;
import com.bootstrapwithspringboot.webapp.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Resource
    UserService userService;

    @Resource
    SchoolService schoolService;
    //TODO change
    String password="nono";

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
                        @RequestParam(value = "size", defaultValue = "500") Integer size,String pass){
        //Page<User> datas = userService.findUserNoCriteria(page, size);
        Page<User> datas = userService.findUserCriteria(page, size,"1");
        modelMap.addAttribute("datas", datas);
        if(password.equalsIgnoreCase(pass)){
            modelMap.addAttribute("isAdmin", "yes");
        }
        return "index";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest req,User user,Model model) {
        if (!CodeUtil.checkVerifyCode(req)) {
            return "redirect:/error";
        } 
        user.setAge("");
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        user.setUpdateDate(formattedDate);
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
            return "redirect:/error"; 
        }
        
    }

    @RequestMapping("/editSave")
    public String edit(User user) {
        user.setAge("");
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        user.setUpdateDate(formattedDate);
        userService.edit(user);
        if(user.getType().equalsIgnoreCase("1")){
            return "redirect:/listPage";
        }else if(user.getType().equalsIgnoreCase("2")){
            return "redirect:/citizen";
        }else if(user.getType().equalsIgnoreCase("3")){
            return "redirect:/ep";
        }else if(user.getType().equalsIgnoreCase("4")){
            return "redirect:/sp";
        }else if(user.getType().equalsIgnoreCase("5")){
        return "redirect:/job";
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
                        @RequestParam(value = "size", defaultValue = "500") Integer size,String pass){
        Page<User> datas = userService.findUserCriteria(page, size,"2");
        modelMap.addAttribute("datas", datas);
        if(password.equalsIgnoreCase(pass)){
            modelMap.addAttribute("isAdmin", "yes");
        }
        return "citizenIndex";
    }

    @RequestMapping("/ep")
    public String findBookNoQueryEP(ModelMap modelMap,@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "500") Integer size,String pass){
        Page<User> datas = userService.findUserCriteria(page, size,"3");
        modelMap.addAttribute("datas", datas);
        if(password.equalsIgnoreCase(pass)){
            modelMap.addAttribute("isAdmin", "yes");
        }
        return "epIndex";
    }
    @RequestMapping("/schools")
    public String findBookNoQuerySP(Model modelMap){
        //List<String> datas = schoolService.findSchoolName();
        List<Schools> datas = schoolService.findAll();
        modelMap.addAttribute("datas", datas);
        return "spIndex";
    }

    @RequestMapping("/school/{school}")
    public String findSchoolsBySchool(Model modelMap,@PathVariable("school") String school){
        List<School> datas = schoolService.findSchoolBySchool(school);
        modelMap.addAttribute("datas", datas);
        modelMap.addAttribute("schoolName", school);

        return "schoolIndex";
    }

    @RequestMapping("/region/{region}")
    public String findSchoolsByRegion(Model modelMap,@PathVariable("region") String region){
        List<School> datas = schoolService.findSchoolByRegion(region);
        modelMap.addAttribute("datas", datas);
        modelMap.addAttribute("schoolName", region);
        return "regionIndex";
    }
}
