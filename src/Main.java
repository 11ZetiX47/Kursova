import java.util.LinkedList;
import java.util.Queue;

class QueueSystem {
    private Queue<User> queue;
    private boolean isOpen;

    public QueueSystem() {
        this.queue = new LinkedList<>();
        this.isOpen = true;
    }

    public void addUser(User user) {
        if (isOpen) {
            queue.add(user);
            System.out.println(user.getName() + " зайняв місце у черзі.");
        } else {
            System.out.println("Черга закрита для нових записів.");
        }
    }

    public void nextUser() {
        if (!queue.isEmpty()) {
            User user = queue.poll();
            System.out.println(user.getName() + " пройшов далі.");
        } else {
            System.out.println("Черга порожня.");
        }
    }

    public void removeUser(User user) {
        if (queue.remove(user)) {
            System.out.println(user.getName() + " був видалений з черги.");
        } else {
            System.out.println(user.getName() + " не знайдено у черзі.");
        }
    }

    public void closeQueue() {
        isOpen = false;
        System.out.println("Черга закрита для нових записів.");
    }

    public void viewQueue() {
        System.out.println("Поточна черга: " + queue);
    }
}

public class User {
    private String name;
    private boolean owner = false;

    public User(String name) { this.name = name; }

    public String getName() { return name; }

    public boolean isOwner() { return owner; }
    public void setOwner(boolean owner) { this.owner = owner; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        return name.equalsIgnoreCase(((User) o).name);
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}

public class Main {
    public static void main(String[] args) {
        QueueSystem queueSystem = new QueueSystem();
        User user1 = new User("Іван");
        User user2 = new User("Марія");

        queueSystem.addUser(user1);
        queueSystem.addUser(user2);
        queueSystem.viewQueue();
        queueSystem.nextUser();
        queueSystem.removeUser(user1);
        queueSystem.closeQueue();
        queueSystem.addUser(new User("Олег"));
    }
package com.example.queueapp;  // змінити на відповідний пакет

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

    @SpringBootApplication
    public class QueueAppApplication {

        public static void main(String[] args) {
            SpringApplication.run(QueueAppApplication.class, args);
        }
    }
}