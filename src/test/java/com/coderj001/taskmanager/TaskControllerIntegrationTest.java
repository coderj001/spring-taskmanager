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


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class TaskControllerIntegrationTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private TaskController taskController;
//
//    @BeforeEach
//    public void setUp() {}
//
//
//    @Test
//    void shouldCreateNewTask() throws Exception {
//        Task newTask = new Task();
//        newTask.setTitle("Test Task");
//        newTask.setDescription("This is a test task");
//
//        String taskJson = objectMapper.writeValueAsString(newTask);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(taskJson))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()) // Check if ID is generated
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Task"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is a test task"));
//
//    }
//
//    @Test
//    void shouldReturnAllTaskList() throws Exception {
//        Task task1 = new Task();
//        task1.setID(1L);
//        task1.setTitle("Test Task 1");
//        task1.setDescription("This is task 1");
//
//        Task task2 = new Task();
//        task2.setID(2L);
//        task2.setTitle("Test Task 2");
//        task2.setDescription("This is task 2");
//
//        taskController.getTasks().put(1L, task1);
//        taskController.getTasks().put(2L, task2);
//
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(2))) // Assert the array size
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L)) // Assert the ID of the first task
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Task 1"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("This is task 1"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L)) // Assert the ID of the second task
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Test Task 2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("This is task 2"));
//    }
//
//
//    @Test
//    void shouldReturnAllTaskGivenID() throws Exception {
//        Task task1 = new Task();
//        task1.setID(1L);
//        task1.setTitle("Test Task 1");
//        task1.setDescription("This is task 1");
//
//        Task task2 = new Task();
//        task2.setID(2L);
//        task2.setTitle("Test Task 2");
//        task2.setDescription("This is task 2");
//
//        taskController.getTasks().put(1L, task1);
//        taskController.getTasks().put(2L, task2);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", 1))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()) // Check if ID is generated
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Task 1"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is task 1"));
//    }
//
//    @Test
//    void shouldDeleteTaskGivenID() throws Exception {
//        Task task1 = new Task();
//        task1.setID(1L);
//        task1.setTitle("Test Task 1");
//        task1.setDescription("This is task 1");
//
//        taskController.getTasks().put(1L, task1);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/{id}", 1))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", 1))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//    @Test
//    void shouldUpdateTaskGivenID() throws Exception {
//        Task task1 = new Task();
//        task1.setID(1L);
//        task1.setTitle("Test Task 1");
//        task1.setDescription("This is task 1");
//
//        Task task2 = new Task();
//        task2.setTitle("Test Task 2");
//        task2.setDescription("This is task 2");
//
//        taskController.getTasks().put(1L, task1);
//
//        String taskJson = objectMapper.writeValueAsString(task2);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/{id}", 1)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(taskJson))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()) // Check if ID is generated
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Task 2"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("This is task 2"));
//    }
//
//}
