package com.seadog.connectmysql.services;

import com.seadog.connectmysql.models.Assignee;
import com.seadog.connectmysql.models.Todo;
import com.seadog.connectmysql.repository.AssigneeRepository;
import com.seadog.connectmysql.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class TodoService {


    @Autowired
    TodoRepository todoRepository;

    @Autowired
    AssigneeRepository assigneeRepository;

    public void addAssignee(long todoId, long assigneeId) {
        Todo todo = todoRepository.findById(todoId).get();
        Assignee assignee = assigneeRepository.findById(assigneeId).get();

        Set<Assignee> assignees = todo.getAssignees();
        assignees.add(assignee);

        todo.setAssignees(assignees);
        todoRepository.save(todo);

    }

    public void addAssignee(Todo todo, Assignee assignee) {
        Set<Assignee> assignees = todo.getAssignees();
        assignees.add(assignee);

        todo.setAssignees(assignees);
        todoRepository.save(todo);
    }
}