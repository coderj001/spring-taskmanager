package com.coderj001.taskmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskRepository taskRepository;


    @BeforeEach
    public void setUp() {
        taskRepository.deleteAll();
    }


    @Test
    void shouldCreateNewTask() throws Exception {
        Task newTask = new Task();
        newTask.setTitle("Test Task");
        newTask.setDescription("This is a test task");

        String taskJson = objectMapper.writeValueAsString(newTask);

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Task"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is a test task"));

    }

    @Test
    void shouldReturnAllTaskList() throws Exception {
        Task task1 = new Task();
        task1.setTitle("Test Task 1");
        task1.setDescription("This is task 1");

        Task task2 = new Task();
        task2.setTitle("Test Task 2");
        task2.setDescription("This is task 2");

        taskRepository.save(task1);
        taskRepository.save(task2);

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Task 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("This is task 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Test Task 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("This is task 2"));
    }

    @Test
    void shouldFilterTasksByTitle() throws Exception {
        Task task1 = new Task();
        task1.setTitle("Urgent meeting");
        task1.setDescription("Discuss important topics");
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Grocery shopping");
        task2.setDescription("Buy food for the week");
        taskRepository.save(task2);

        Task task3 = new Task();
        task3.setTitle("Test the API");
        task3.setDescription("Write integration tests");
        taskRepository.save(task3);

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks")
                        .param("title", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test the API"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Write integration tests"));
    }


    @Test
    void shouldReturnAllTaskGivenID() throws Exception {
        Task task1 = new Task();
        task1.setTitle("Test Task 1");
        task1.setDescription("This is task 1");

        Task savedTask = taskRepository.save(task1);

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", savedTask.getID()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Task 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is task 1"));
    }

    @Test
    void shouldDeleteTaskGivenID() throws Exception {
        Task task1 = new Task();
        task1.setTitle("Test Task 1");
        task1.setDescription("This is task 1");

        Task savedTask = taskRepository.save(task1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/{id}", savedTask.getID()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", savedTask.getID()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldUpdateTaskGivenID() throws Exception {
        Task task1 = new Task();
        task1.setTitle("Test Task 1");
        task1.setDescription("This is task 1");

        Task savedTask = taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Test Task 2");
        task2.setDescription("This is task 2");

        String taskJson = objectMapper.writeValueAsString(task2);

        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/{id}", savedTask.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Task 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is task 2"));

    }

}
