package io.nixer.springplugin.demo.task;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {

    private final ConcurrentHashMap<String, Task> taskRepository = new ConcurrentHashMap<>();

    public TodoController() {
        addTask("first task");
        addTask("second task");
        addTask("third task");
    }

    private void addTask(String name) {
        final String id = UUID.randomUUID().toString();
        this.taskRepository.put(id, new Task(id, name));
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.values());

        return "listTasks";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task) {

        addTask(task.getName());

        return "redirect:/tasks";
    }

    @PostMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") String id) {

        this.taskRepository.remove(id);

        return "redirect:/tasks";
    }

}
