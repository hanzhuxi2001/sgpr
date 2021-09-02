package com.bootstrapwithspringboot.webapp.contoller;

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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class JobController {

    @Resource
    UserService userService;

    @Resource
    SchoolService schoolService;

    //TODO change
    String password="nana";

    @RequestMapping("/listJob")
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

    @RequestMapping("/editSaveJob")
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
        }
        return "redirect:/listPage";
    }


    @RequestMapping("/deleteJob")
    public String delete(Long id,String pass) {
        if(password.equalsIgnoreCase(pass)){
            userService.delete(id);
        }
        return "redirect:/listPage";
    }
    @RequestMapping("/editJob")
    public String toEdit(Model model,Long id,String birth) {
        System.out.print("editJob id"+id);
        System.out.print("birth"+birth);
        User user=userService.findUserById(id);
        model.addAttribute("user", user);
        if(birth!=null&&birth.equalsIgnoreCase(user.getPassword())){
            return "editJob";
        }else{
            return "redirect:/error";
        }

    }

    @RequestMapping("/job")
    public String findBookNoQueryCitizen(ModelMap modelMap,@RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "20") Integer size,String pass){
        Page<User> datas = userService.findUserCriteria(page, size,"5");
        modelMap.addAttribute("datas", datas);
        if(password.equalsIgnoreCase(pass)){
            modelMap.addAttribute("isAdmin", "yes");
        }
        return "jobIndex";
    }

}
