package com.popova.jobtest.controller;


import com.popova.jobtest.service.JsonXmlFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xml.sax.SAXException;

import java.io.IOException;

@Controller
public class MainController {

    private JsonXmlFileService jsonXmlFileService;

    @Autowired
    public void setJsonXmlFileService(JsonXmlFileService jsonXmlFileService) {
        this.jsonXmlFileService = jsonXmlFileService;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView getMain(ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/file/post", method = RequestMethod.POST)
    public String postFile(@RequestParam("xml_file") MultipartFile xml,
                           RedirectAttributes redirectAttributes) {
        try {
            String jsonFromXmlObject = jsonXmlFileService.parseXmlToJson(xml);
            redirectAttributes.addFlashAttribute("jsonFromXmlObject", jsonFromXmlObject);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
            return "error";
        }
        return "redirect:/";
    }
}
