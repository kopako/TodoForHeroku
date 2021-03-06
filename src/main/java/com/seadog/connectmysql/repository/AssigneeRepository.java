package com.seadog.connectmysql.repository;

import com.seadog.connectmysql.models.Assignee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigneeRepository extends CrudRepository<Assignee, Long> {

    List<Assignee> findByNameOrEmailAllIgnoreCase(String name, String email);

}
