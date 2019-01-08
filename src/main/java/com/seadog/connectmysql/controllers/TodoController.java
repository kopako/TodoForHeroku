package com.seadog.connectmysql.controllers;

import com.seadog.connectmysql.models.Assignee;
import com.seadog.connectmysql.models.Todo;
import com.seadog.connectmysql.repository.AssigneeRepository;
import com.seadog.connectmysql.repository.TodoRepository;
import com.seadog.connectmysql.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @Autowired
    private AssigneeRepository assigneeRepository;

    @RequestMapping(value = {"/", "/list"})
    public String list(Model model, @RequestParam(required = false) Boolean isActive) {
        if (isActive != null) {
            model.addAttribute("todos", todoRepository.findByDone(!isActive));
            return "todo/todolist";
        } else {
            model.addAttribute("todos", todoRepository.findAll());
        }
        return "todo/todolist";
    }

    @GetMapping("/search")
    public String search(@RequestParam String search, Model model) {

        model.addAttribute("todos", todoRepository.
                findByTitleContainingOrContentContainingOrDescriptionContainingAllIgnoreCase(search, search, search));
        model.addAttribute("search", search);
        return "todo/todolist";
    }

    @GetMapping("/{id}/info")
    public String todoInfo(
            @PathVariable(name = "id") Long todoId,
            Model model) {
        Todo todo = todoRepository.findById(todoId).get();
        model.addAttribute("todo", todo);
        return "todo/info";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("todo", new Todo());
        model.addAttribute("assignees", assigneeRepository.findAll());
        return "todo/edit";
    }

    @GetMapping("/{id}/edit")
    public String editByIdForm(@PathVariable long id, Model model) {
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        model.addAttribute("assignees", assigneeRepository.findAll());
        return "todo/edit";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute Todo todo) {
        Set<Assignee> assignees= todoRepository.findById(todo.getId()).get().getAssignees();
        todo.setAssignees(assignees);

        todoRepository.save(todo);
        return "redirect:/";
    }

    @GetMapping("/{id}/addassignee")
    public String addAssigneeForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("todo", todoRepository.findById(id).get());
        model.addAttribute("assigneesselect", assigneeRepository.findAll());

        return "todo/addassignee";
    }

    @PostMapping("/addassignee")
    public String addAssigneeSubmit(@ModelAttribute("todoId") Long todoId,
                                    @ModelAttribute("assigneeId") long assigneeId) {

        todoService.addAssignee(todoId, assigneeId);

        return "redirect:/" + todoId + "/addassignee";
    }


    @PostMapping("/{id}/delete")
    public String deleteById(@ModelAttribute("id") long id) {
        todoRepository.deleteById(id);

        return "redirect:/";
    }
}
