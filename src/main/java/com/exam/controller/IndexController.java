package com.exam.controller;

import com.exam.entity.TrExam;
import com.exam.mapper.TrExamMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Resource
    private TrExamMapper trExamMapper;
    @Resource
    private HttpServletRequest request;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("ID", "");
        model.addAttribute("Question", "");
        model.addAttribute("Answer", "");
        return "index";
    }

    @RequestMapping("/Next")
    public String Next(Model model) {
        String maxId = trExamMapper.selectMaxId();
        if (maxId == null){
            model.addAttribute("WarnInfo", "没有可用数据");
            return "index";
        }
        Integer MaxId = Integer.parseInt(maxId);
        int randomId = (int) (1 + Math.random() * (MaxId - 1 + 1));
        TrExam trExamDB = trExamMapper.selectById(randomId);
        model.addAttribute("ID", trExamDB.getId());
        model.addAttribute("Question", trExamDB.getQuestion());
        model.addAttribute("Answer", "");
        return "index";
    }

    @RequestMapping("/Answer/{ID}")
    public String Answer(@PathVariable String ID, Model model) {
        String trId = ID;
        TrExam trExamDB = trExamMapper.selectById(trId);
        model.addAttribute("ID", trExamDB.getId());
        model.addAttribute("Question", trExamDB.getQuestion());
        model.addAttribute("Answer", trExamDB.getAnswer());
        return "index";
    }
    @RequestMapping("/Delete/{ID}")
    public String Delete(@PathVariable String ID, Model model) {
        trExamMapper.deleteById(ID);
        model.addAttribute("ID","");
        model.addAttribute("Question","");
        model.addAttribute("Answer", "");
        return "index";
    }

    @RequestMapping("/Add")
    public String add() {
        return "add";
    }

    @PostMapping("/AddQuestion")
    public String AddQuestion(String Question,String Answer,Model model) {
        try{
            TrExam trExam = new TrExam(Question, Answer);
            trExamMapper.insert(trExam);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("WarnInfo", e.getMessage());
        }
        return "add";
    }
}
