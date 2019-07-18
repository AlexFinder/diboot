package com.diboot.example.controller;

import com.diboot.core.controller.BaseController;
import com.diboot.core.exception.RestException;
import com.diboot.core.exception.WebException;
import com.diboot.core.vo.JsonResult;
import com.diboot.core.vo.Status;
import com.diboot.example.exception.ExampleRestException;
import com.diboot.example.exception.ExampleWebException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 错误异常展示
 * @author : wee
 * @version : v2.0
 * @Date 2019-07-11  15:02
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends BaseController {

    /**
     * 测试自定义
     * @param num
     * @return
     */
    @GetMapping("/rest/{num}")
    @ResponseBody
    public JsonResult testRest(@PathVariable("num") Integer num) {
        if (num == 1) {
            throw new RestException();
        }
        if (num == 2) {
            throw new RestException(Status.FAIL_EXCEPTION);
        }
        if (num == 3) {
            throw new RestException(Status.FAIL_EXCEPTION, "自定义描述");
        }

        if (num == 4) {
            throw new RestException(Status.FAIL_NO_PERMISSION, num, "将传入的数据返回数据");
        }

        if (num == 5) {
            throw new ExampleRestException(Status.FAIL_NO_PERMISSION, num, "继承rest异常");
        }

        if (num == 6) {
            throw new RuntimeException("eeeeeee");
        }
        return new JsonResult();
    }

    @GetMapping("/web/{num}")
    public ModelAndView testWeb(@PathVariable("num") Integer num) {
        if (num == 1) {
            throw new WebException(HttpStatus.BAD_REQUEST);
        }
        if (num == 2) {
            throw new WebException(HttpStatus.FORBIDDEN);
        }
        if (num == 3) {
            throw new WebException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (num == 4) {
            throw new ExampleWebException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (num == 5) {
            throw new ExampleWebException(HttpStatus.BAD_GATEWAY);
        }
        return new ModelAndView("redirect:/index.html");
    }
}