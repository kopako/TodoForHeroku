package com.seadog.connectmysql.controllers;

import com.seadog.connectmysql.models.Assignee;
import com.seadog.connectmysql.repository.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AssigneeController {

    @Autowired
    AssigneeRepository assigneeRepository;

    @GetMapping("/assignees")
    public String assignees(Model model) {
        model.addAttribute("assignees", assigneeRepository.findAll());
        return "assignee/assigneelist";
    }

    @GetMapping("/searchAssignee")
    public String search(@RequestParam String searchAssignee, Model model) {
        model.addAttribute("assignees", assigneeRepository.findByNameOrEmailAllIgnoreCase(searchAssignee, searchAssignee));
        model.addAttribute("searchAssignee", searchAssignee);
        return "assignee/assigneelist";
    }

    @GetMapping("/createAssignee")
    public String createForm(Model model) {
        model.addAttribute("assignee", new Assignee());
        return "assignee/createAssignee";
    }

    @PostMapping("/createAssignee")
    public String createSubmit(@ModelAttribute Assignee assignee) {
        assigneeRepository.save(assignee);
        return "redirect:/assignees";
    }

    @PostMapping("/assignee/{id}/delete")
    public String deleteById(@ModelAttribute("id") long id) {
        assigneeRepository.deleteById(id);
        return "redirect:/assignees";
    }

    @GetMapping("/assignee/{id}/edit")
    public String editByIdForm(@ModelAttribute("id") long id, Model model) {
        Assignee assignee = assigneeRepository.findById(id).get();
        model.addAttribute("assignee", assignee);
        return "assignee/editAssignee";
    }

    @PostMapping("/assignee/edit")
    public String editSubmit(@ModelAttribute Assignee assignee) {
        assigneeRepository.save(assignee);
        return "redirect:/assignees";
    }
}
