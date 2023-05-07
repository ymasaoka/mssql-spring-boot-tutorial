package jp.mappiekochi.sample.firstmssqlproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jp.mappiekochi.sample.firstmssqlproject.domain.dao.impl.CustomerDAOImpl;
import jp.mappiekochi.sample.firstmssqlproject.entity.Customer;
import jp.mappiekochi.sample.firstmssqlproject.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class HomeController {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerDAOImpl dao;

    @RequestMapping("/")
    public ModelAndView index(@ModelAttribute("formModel") Customer customer,ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("title", "[AdventureWorksLT2019].[SalesLT].[Customer]");
        mav.addObject("msg", "テーブルデータを取得しました。");
//        Iterable<Customer> data = repository.findAll();
        Iterable<Customer> data = repository.findAllOrderByCustomerId();
        mav.addObject("data", data);
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(ModelAndView mav) {
        mav.setViewName("search");
        mav.addObject("title", "Customer 情報 - 検索");
        mav.addObject("msg", "Customer テーブル情報を検索します。");
        Iterable<Customer> data = dao.getAll();
        mav.addObject("data", data);
        return mav;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
        mav.setViewName("search");
        String param = request.getParameter("search_str");
        if (param.isEmpty()) {
            mav = new ModelAndView("redirect:/search");
        } else {
            mav.addObject("title", "Customer 情報 - 検索結果");
            mav.addObject("msg", "「" + param + "」の検索結果を表示します。");
            mav.addObject("value", param);
//            List<Customer> result = dao.findByFirstName(param);
            List<Customer> result = dao.find(param);
//            List<Customer> result = repository.findByEmail(param);
            mav.addObject("data", result);
        }
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(ModelAndView mav) {
        mav.setViewName("create");
        mav.addObject("title", "Customer 新規作成");
        mav.addObject("msg", "新しい Customer 情報を登録します。");
        Customer data = new Customer();
        mav.addObject("formModel", data);
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Transactional
    public ModelAndView save(@ModelAttribute("formModel") @Validated Customer customer,
                             BindingResult result,
                             ModelAndView mav) {
        ModelAndView res = null;
        System.out.println(result.getFieldErrors());
        if (!result.hasErrors()) {
            customer.setRowguid(UUID.randomUUID().toString());
            customer.setModifiedDate(Calendar.getInstance());
            repository.saveAndFlush(customer);
            res = new ModelAndView("redirect:/");
        } else {
            mav.setViewName("create");
            mav.addObject("title", "Customer 新規作成");
            mav.addObject("msg", "入力内容に不正があります。入力内容を確認してください。");
            mav.addObject("formModel", customer);
            res = mav;
        }

        return res;
    }

    @RequestMapping(value = "/edit/{customerId}", method = RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute Customer customer, @PathVariable int customerId, ModelAndView mav) {
        mav.setViewName("edit");
        mav.addObject("title", "Customer 情報更新");
        mav.addObject("msg", "Customer 情報を更新します。");
        Optional<Customer> data = repository.findById(customerId);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional
    public ModelAndView update(@ModelAttribute Customer customer, ModelAndView mav) {
        customer.setModifiedDate(Calendar.getInstance());
        repository.saveAndFlush(customer);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/delete/{customerId}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int customerId, ModelAndView mav) {
        mav.setViewName("delete");
        mav.addObject("title", "Customer 情報削除");
        mav.addObject("msg", "Customer 情報を削除しますか？");
        Optional<Customer> data = repository.findById(customerId);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional
    public ModelAndView remove(@RequestParam int customerId, ModelAndView mav) {
        repository.deleteById(customerId);
        return new ModelAndView("redirect:/");
    }
}
