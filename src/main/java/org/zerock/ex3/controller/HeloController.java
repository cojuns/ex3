package org.zerock.ex3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.ex3.entity.MyData;
import org.zerock.ex3.repository.MyDataRepository;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex3.repository.MyEm;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Controller
public class HeloController {

    @Autowired
    MyDataRepository repository;

    @Autowired
    MyEm myEm;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(
            @ModelAttribute("formModel") MyData mydata,
            ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg","this is sample content.");
      //  Iterable<MyData> list = repository.findAll();

        List<MyData> list = myEm.findAll();
        mav.addObject("datalist",list);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView form(
            @ModelAttribute("formModel") MyData mydata,
            ModelAndView mav) {
       // repository.saveAndFlush(mydata);
        myEm.insert(mydata);
        return new ModelAndView("redirect:/");
    }

    @PostConstruct
    public void init(){
        MyData d1 = new MyData();
        d1.setName("kim");
        d1.setAge(123);
        d1.setMail("kim@gilbut.co.kr");
        d1.setMemo("this is my data!");
        repository.saveAndFlush(d1);
        MyData d2 = new MyData();
        d2.setName("lee");
        d2.setAge(15);
        d2.setMail("lee@flower");
        d2.setMemo("my girl friend.");
        repository.saveAndFlush(d2);
        MyData d3 = new MyData();
        d3.setName("choi");
        d3.setAge(37);
        d3.setMail("choi@happy");
        d3.setMemo("my work friend...");
        repository.saveAndFlush(d3);
    }


    @RequestMapping(value = "/edit/{id}" // 수정 1
            , method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute MyData mydata,
                             @PathVariable Long id, ModelAndView mav) {
        mav.setViewName("edit");
        mav.addObject("title","edit mydata.");

//        Optional<MyData> data = repository.findById((long)id);
//        mav.addObject("formModel",data.get());

        MyData data = myEm.findById(id);
        mav.addObject("formModel", data);

        return mav;
    }

    @GetMapping("/edit") // 수정 2
    public String edit2(@ModelAttribute MyData myData,@RequestParam int id,
                        Model model){
        Optional<MyData> data = repository.findById((long)id);
        model.addAttribute("title", "edit mydata");
        model.addAttribute("formModel", data.get());
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView update(@ModelAttribute MyData mydata,
                               ModelAndView mav) {
//        repository.saveAndFlush(mydata);
        myEm.update(mydata);
        return new ModelAndView("redirect:/");
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long id,
                               ModelAndView mav) {
        mav.setViewName("delete");
        mav.addObject("title","delete mydata.");
//        Optional<MyData> data = repository.findById((long)id);
//        mav.addObject("formModel",data.get());// Optional => get()넣어야 됨
        MyData data = myEm.findById(id);
        mav.addObject("formModel", data);

        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView remove(@RequestParam long id,
                               ModelAndView mav) {
//        repository.deleteById(id);
        myEm.deleteById(id);
        return new ModelAndView("redirect:/");
    }



}