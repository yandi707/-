package com.kuangstudy.controller.err;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description:
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/11/18 23:24.
 * Update Date Time:
 *
 * @see
 */
@Controller
public class ErrorPageController implements ErrorController {

    @GetMapping("/error")
    public String errorpage() {
        return "error/index";
    }
}
