package com.seadog.connectmysql.controllers;

import com.seadog.connectmysql.models.Todo;
import com.seadog.connectmysql.repository.AssigneeRepository;
import com.seadog.connectmysql.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    AssigneeRepository assigneeRepository;

    @RequestMapping(value = {"/", "/list"})
    public String list(Model model, @RequestParam(required = false) Boolean isActive) {
        if (isActive != null) {
            model.addAttribute("todos", (List<Todo>) todoRepository.findByDone(!isActive) );
            return "todo/todolist";
        } else {
            model.addAttribute("todos", (List<Todo>) todoRepository.findAll());
        }
        return "todo/todolist";
    }

    @GetMapping("/search")
    public String search(@RequestParam String search, Model model) {

        model.addAttribute("todos",todoRepository.
                findByTitleContainingOrContentContainingOrDescriptionContainingAllIgnoreCase(search,search,search));
        model.addAttribute("search", search);
        return "todo/todolist";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todo/create";
    }

    @PostMapping("/create")
    public String createSubmit(@ModelAttribute Todo todo) {
        todoRepository.save(todo);
        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String deleteById(@ModelAttribute("id") long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editByIdForm(@ModelAttribute("id") long id, Model model) {
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "todo/edit";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute Todo todo) {
        todoRepository.save(todo);
        return "redirect:/";
    }
}
