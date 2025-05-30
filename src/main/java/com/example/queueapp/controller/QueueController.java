package com.example.queueapp.controller;

import com.example.queueapp.model.Queue;
import com.example.queueapp.service.QueueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("queues", queueService.findAll());
        return "queues";
    }

    @PostMapping("/queues")
    public String create(@RequestParam String name,
                         @RequestParam String password) {
        Queue q = new Queue();
        q.setName(name);
        q.setOwnerPassword(password);
        q.setOpen(true);
        queueService.save(q);
        return "redirect:/";
    }

    @GetMapping("/queues/{id}/manage")
    public String manage(@PathVariable Long id, Model model) {
        model.addAttribute("queue", queueService.findById(id));
        return "queue-manage";
    }

    @PostMapping("/queues/{id}/close")
    public String close(@PathVariable Long id,
                        @RequestParam String password,
                        Model model) {
        Queue q = queueService.findById(id);
        if (!q.getOwnerPassword().equals(password)) {
            model.addAttribute("queue", q);
            model.addAttribute("error", "Неправильний пароль");
            return "queue-manage";
        }
        q.setOpen(false);
        queueService.save(q);
        return "redirect:/queues/" + id + "/manage";
    }

    @PostMapping("/queues/{id}/open")
    public String open(@PathVariable Long id,
                       @RequestParam String password,
                       Model model) {
        Queue q = queueService.findById(id);
        if (!q.getOwnerPassword().equals(password)) {
            model.addAttribute("queue", q);
            model.addAttribute("error", "Неправильний пароль");
            return "queue-manage";
        }
        q.setOpen(true);
        queueService.save(q);
        return "redirect:/queues/" + id + "/manage";
    }

    @PostMapping("/queues/{id}/join")
    public String join(@PathVariable Long id,
                       @RequestParam String participant) {
        queueService.addParticipant(id, participant);
        return "redirect:/queues/" + id + "/manage";
    }

    @PostMapping("/queues/{id}/next")
    public String next(@PathVariable Long id,
                       @RequestParam String password,
                       Model model) {
        Queue q = queueService.findById(id);
        if (!q.getOwnerPassword().equals(password)) {
            model.addAttribute("queue", q);
            model.addAttribute("error", "Неправильний пароль");
            return "queue-manage";
        }
        queueService.next(id);
        return "redirect:/queues/" + id + "/manage";
    }

    @PostMapping("/queues/{id}/remove")
    public String remove(@PathVariable Long id,
                         @RequestParam String participant,
                         @RequestParam String password,
                         Model model) {
        Queue q = queueService.findById(id);
        if (!q.getOwnerPassword().equals(password)) {
            model.addAttribute("queue", q);
            model.addAttribute("error", "Неправильний пароль");
            return "queue-manage";
        }
        queueService.deleteParticipant(id, participant);
        return "redirect:/queues/" + id + "/manage";
    }
}