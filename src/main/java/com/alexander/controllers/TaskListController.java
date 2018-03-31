package com.alexander.controllers;

import com.alexander.models.Task;
import com.alexander.services.interfaces.ISecurityService;
import com.alexander.services.interfaces.ITaskService;
import com.alexander.validators.TaskValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TaskListController {

    private static Logger log = LogManager.getLogger(TaskListController.class);

    @Autowired
    private ITaskService taskService;

    @Autowired
    private TaskValidator taskValidator;


    @GetMapping({"/", "/home"})
    public String home(Model model) {
        log.info("Get request / ");
        SecurityContext context = SecurityContextHolder.getContext();
        model.addAttribute("taskList", taskService.findAll(context.getAuthentication().getName()));
        model.addAttribute("taskObj", new Task());
        return "tasklist";
    }

    @PostMapping("/")
    public String home(@ModelAttribute("taskObj") Task task, BindingResult result, Model model) {
        log.info("Post request / ");
        taskValidator.validate(task, result);

        if (result.hasErrors()) {
            log.info("Error while adding new task. Redirect to / .");
            return "redirect:/";
        }

        task.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
        taskService.save(task);
        return "redirect:/";
    }

}
