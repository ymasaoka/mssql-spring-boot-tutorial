package jp.mappiekochi.sample.firstmssqlproject.controller;

import jp.mappiekochi.sample.firstmssqlproject.entity.Customer;
import jp.mappiekochi.sample.firstmssqlproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @Autowired
    private CustomerRepository repository;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("title", "[AdventureWorksLT2019].[SalesLT].[Customer]");
        mav.addObject("msg", "テーブルデータを取得しました。");
        Iterable<Customer> data = repository.findAll();
        mav.addObject("data", data);
        return mav;
    }
}
