package com.example.aop.controller;

import com.example.aop.authority.Auth;
import com.example.aop.mapper.StudentMapper;
import com.example.aop.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author caiyq
 */
@RestController
public class StudentController {

    static public String[] lastName={"Li","Liu","Wang","Zhang","Zhao","Cao","Chen","Lin","Jia","Wu","Jin","Ou","Ruan","Lu"};
    static public String[] firstName={"Xixi","Qingyu","Zhiyuan","Mingxi","Jie","wen","Yuyu","Xin","Qi","Weiwei","Lulu","Nana","Kiko","Peter","Ruining"};
    static public String[] genders={"male","female"};
    static public String[] cities={"BeiJing","HangZhou","GuangZhou","ShenZhen","WuHan","ShangHai","HongKong","ChongQin","ChangSha","TaiWan"};
    static public String[] workplaces={"BaiDu","ALibaba","Tencent","HuaWei","NetDragon","Tik Tok","360"};
    static public String[] jobs={"Java develop","Python develop","C++ develop","Data Mining","Big Data","Natural Network"};
    static public String[] phonesFront={"133","189","180","152","181","192","137","188"};

    @Autowired
    private StudentMapper studentMapper;

    @Auth(role_name = {"Read"})
    @RequestMapping(value = "/select1")
    public ModelAndView select1(){
        ModelAndView mv = new ModelAndView("select");
        return mv;
    }

    @Auth(role_name = {"Read","Update"})
    @RequestMapping(value = "/create1")
    public ModelAndView create1(){
        ModelAndView mv = new ModelAndView("create");
        return mv;
    }

    @Auth(role_name = {"Read","Update"})
    @RequestMapping(value = "/update1")
    public ModelAndView update1(){
        ModelAndView mv = new ModelAndView("update_first");
        return mv;
    }

    @Auth(role_name = {"Read","Update"})
    @RequestMapping(value = "/delete1")
    public ModelAndView delete1(){
        ModelAndView mv = new ModelAndView("delete");
        return mv;
    }

    @Auth(role_name = {"Aggregate"})
    @RequestMapping(value = "/count1")
    public ModelAndView count1(){
        ModelAndView mv = new ModelAndView("count");
        mv.addObject("num","0");
        return mv;
    }

    @Auth(role_name = {"Aggregate"})
    @RequestMapping(value = "/download1")
    public ModelAndView download1(){
        ModelAndView mv = new ModelAndView("download");
        return mv;
    }


    @RequestMapping(value = "/loginPage")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("loginPage");
        modelAndView.addObject("msg","success");
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        System.out.println(account + "  "+password);

        String realPassword = studentMapper.login(account);
        System.out.println(realPassword);
        ModelAndView modelAndView = new ModelAndView();

        if(realPassword != null && realPassword.equals(password)){
            //登陆成功
            String role = role = studentMapper.getRole(account);
            if(role == ""||role ==null){
                role = "";
            }
            modelAndView.setViewName("index");
            modelAndView.addObject("role",role);
            modelAndView.addObject("account",account);
        }
        else{
            modelAndView.setViewName("loginPage");
            modelAndView.addObject("msg","登录失败");
        }
        System.out.println(modelAndView);
        return modelAndView;
    }


    @Auth
    @RequestMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    /**
     * 新建用户
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createStudent(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        int num = Integer.valueOf(request.getParameter("num"));
        boolean success = true;
        Random random=new Random();
        for(int j=0;j<num;j++){
            Student student = new Student();
            student.setStudentName(lastName[random.nextInt(lastName.length)]+" "+firstName[random.nextInt(firstName.length)]);
            student.setGender(genders[random.nextInt(genders.length)]);
            int birthYear = random.nextInt(40)+1960;
            student.setBirthday(String.valueOf(birthYear)+"-" +
                    String.valueOf(random.nextInt(12)+1)+"-"+
                    String.valueOf(random.nextInt(27)+1));
            student.setEnterYear(String.valueOf(birthYear+19));
            student.setGraduateYear(String.valueOf(Integer.parseInt(student.getEnterYear())+4));
            student.setCity(cities[random.nextInt(cities.length)]);
            student.setWorkplace(workplaces[random.nextInt(workplaces.length)]);
            student.setJob(jobs[random.nextInt(jobs.length)]);
            student.setPhone(phonesFront[random.nextInt(phonesFront.length)] +
                    String.valueOf(random.nextInt(6432)+2345)+
                    String.valueOf(random.nextInt(8800)+1100));
            student.setEmail(student.getPhone()+"@163.com");
            student.setWechat(student.getPhone());

            if(!insertStudent(student)){
                success=false;
                break;
            }
        }
        ModelAndView mv = new ModelAndView("result");
        return mv;
    }

    /**
     * 插入用户
     * @param student
     * @return
     */
    public boolean insertStudent(@RequestBody Student student){
        if(studentMapper.insert(student)>0) {
            return true;
        }
        return false;
    }

    /**
     * 修改用户
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateStudent(){
        Student student = new Student();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        student.setId(new BigInteger(request.getParameter("id")));
        student.setStudentName(request.getParameter("studentName"));
        student.setGender(request.getParameter("gender"));
        student.setBirthday(request.getParameter("birthday"));
        student.setEnterYear(request.getParameter("enterYear"));
        student.setGraduateYear(request.getParameter("graduateYear"));
        student.setCity(request.getParameter("city"));
        student.setWorkplace(request.getParameter("workplace"));
        student.setJob(request.getParameter("job"));
        student.setPhone(request.getParameter("phone"));
        student.setWechat(request.getParameter("wechat"));
        student.setEmail(request.getParameter("email"));

        System.out.println(student);
        if(studentMapper.update(student)>0){
            return new ModelAndView("result");
        }
        return new ModelAndView("result_fail");
    }

    @RequestMapping(value = "/update_select", method = RequestMethod.GET)
    public ModelAndView update_select(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String item = request.getParameter("item");
        String value = request.getParameter("value");
        System.out.println(item + "  "+value);

        ModelAndView mv = new ModelAndView("update");
        List<Student> students = new ArrayList<>();
        if("*".equals(item)){
            students=studentMapper.getAll();
        }
        else{
            students = studentMapper.select(item, value);
        }
        if(students!=null){
            mv.addObject("s",students.get(0));
        }
        else{
            mv.addObject("s","null");
        }
        System.out.println(mv);
        return mv;
    }

    /**
     * 删除用户
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteStudent(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String name = request.getParameter("name");

        if(studentMapper.delete(name)>0){
            return new ModelAndView("result");
        }
        return new ModelAndView("result_fail");
    }

    /**
     * 查询用户
     * @return
     */
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public ModelAndView selectStudent(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String item = request.getParameter("item");
        String value = request.getParameter("value");
        System.out.println(item + "  "+value);

        ModelAndView mv = new ModelAndView("selectResult");
        List<Student> students = new ArrayList<>();
        if("*".equals(item)){
            students=studentMapper.getAll();
        }
        else{
            students = studentMapper.select(item, value);
        }
        if(!students.isEmpty()){
            mv.addObject("stuList",students);
        }
        System.out.println(mv);
        return mv;
    }

    /**
     * 统计
     * @return
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ModelAndView countStudent(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String item = request.getParameter("item");
        String value = request.getParameter("value");
        System.out.println(item + "  "+value);

        ModelAndView mv = new ModelAndView("count");
        int count =0;
        System.out.println("*".equals(item));
        if("*".equals(item)){
            count = studentMapper.getAll().size();
        }
        else{
            count = studentMapper.select(item,value).size();
        }
        mv.addObject("num",String.valueOf(count));
        return mv;
    }

    /**
     * 下载
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView download(){
        ModelAndView mv = new ModelAndView("result");
        List<Student> students = studentMapper.getAll();
        System.out.println(students);
        FileOutputStream fop = null;
        File file;
        try {

            file = new File("Student_records.txt");
            fop = new FileOutputStream(file);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            for(Student student: students){
                byte[] contentInBytes = student.toString().getBytes();
                fop.write(contentInBytes);
            }
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mv;
    }
}
