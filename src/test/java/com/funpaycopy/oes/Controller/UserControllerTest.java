package com.funpaycopy.oes.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funpaycopy.oes.Model.BuyList;
import com.funpaycopy.oes.Model.BuyStatus;
import com.funpaycopy.oes.Model.User;
import com.funpaycopy.oes.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private UserService userService;

    @Test
    void givenUserObject_whenCreateUser_thenReturn200() throws Exception {
        User user = new User(1L, "jUnit_test1", "jUnitTest1!", "junit@test.one", BigDecimal.valueOf(1.11), true, "photoPath1");

        when(userService.saveUser(user)).thenReturn(Optional.of(user).orElseThrow());

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.ALL)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    void givenUserObject_whenCreateUser_thenReturn415() throws Exception {
        User user = new User(1L, null, null, null, BigDecimal.valueOf(1.11), true, "photoPath1");

        when(userService.saveUser(user)).thenReturn(Optional.of(user).orElseThrow());

        mockMvc.perform(post("/api/users")
                        .accept(MediaType.ALL)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    void givenTwoUserObjects_whenGetAllUsers_thenReturn200AndParamsMatch() throws Exception {
        User user1 = new User(1L, "jUnit_test1", "jUnitTest1!", "junit@test.one", BigDecimal.valueOf(1.11), true, "photoPath1");
        User user2 = new User(2L, "jUnit_test2", "jUnitTest2@", "junit@test.two", BigDecimal.valueOf(2.22), false, "photoPath2");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].idUser", containsInAnyOrder(1,2)))
                .andExpect(jsonPath("$[*].login", containsInAnyOrder("jUnit_test1", "jUnit_test2")))
                .andExpect(jsonPath("$[*].password", containsInAnyOrder("jUnitTest1!", "jUnitTest2@")))
                .andExpect(jsonPath("$[*].email", containsInAnyOrder("junit@test.one", "junit@test.two")))
                .andExpect(jsonPath("$[*].balance", containsInAnyOrder(1.11, 2.22)))
                .andExpect(jsonPath("$[*].active", containsInAnyOrder(true, false)))
                .andExpect(jsonPath("$[*].profilePhoto", containsInAnyOrder("photoPath1", "photoPath2")));
    }

    @Test
    void givenTwoUserObjects_whenGetAllUsers_thenReturn200AndParamsNotMatch() throws Exception {
        User user1 = new User(1L, "jUnit_test", "jUnitTest", "junit@test.on", BigDecimal.valueOf(1.11), true, "photoPath1");
        User user2 = new User(2L, "jUnit_test2", "jUnitTest2@", "junit@test.two", BigDecimal.valueOf(2.22), true, "photoPath2_");
        
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].idUser", containsInAnyOrder(1,2)))
                .andExpect(jsonPath("$[*].login", containsInAnyOrder("jUnit_test1", "jUnit_test2")))
                .andExpect(jsonPath("$[*].password", containsInAnyOrder("jUnitTest1!", "jUnitTest2@")))
                .andExpect(jsonPath("$[*].email", containsInAnyOrder("junit@test.one", "junit@test.two")))
                .andExpect(jsonPath("$[*].balance", containsInAnyOrder(1.11, 2.22)))
                .andExpect(jsonPath("$[*].active", containsInAnyOrder(true, false)))
                .andExpect(jsonPath("$[*].profilePhoto", containsInAnyOrder("photoPath1", "photoPath2")));
    }

    @Test
    void givenUserObject_whenGetUserByID_thenReturn200AndParamsMatch() throws Exception {
        User user1 = new User(1L, "jUnit_test1", "jUnitTest1!", "junit@test.one", BigDecimal.valueOf(1.11), true, "photoPath1");

        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user1).get());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser", equalTo(1)))
                .andExpect(jsonPath("$.login", equalTo("jUnit_test1")))
                .andExpect(jsonPath("$.password", equalTo("jUnitTest1!")))
                .andExpect(jsonPath("$.email", equalTo("junit@test.one")))
                .andExpect(jsonPath("$.balance", equalTo(1.11)))
                .andExpect(jsonPath("$.active", equalTo(true)))
                .andExpect(jsonPath("$.profilePhoto", equalTo("photoPath1")));
    }

    @Test
    void givenUserObject_whenGetUserByID_thenReturn200AndParamsNotMatch() throws Exception {
        User user2 = new User(2L, "jUnit_test2", "jUnitTest2@", "junit@test.two", BigDecimal.valueOf(2.22), true, "photoPath2_");

        when(userService.getUserById(anyLong())).thenReturn(Optional.of(user2).get());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser", equalTo(1)))
                .andExpect(jsonPath("$.login", equalTo("jUnit_test1")))
                .andExpect(jsonPath("$.password", equalTo("jUnitTest1!")))
                .andExpect(jsonPath("$.email", equalTo("junit@test.one")))
                .andExpect(jsonPath("$.balance", equalTo(1.11)))
                .andExpect(jsonPath("$.active", equalTo(true)))
                .andExpect(jsonPath("$.profilePhoto", equalTo("photoPath1")));
    }

    @Test()
    void givenUserID_whenDeleteUserByID_thenReturn200() throws Exception {
        long idUser = 1L;
        willDoNothing().given(userService).deleteUser_(idUser);

        mockMvc.perform(delete("/api/users/{id}", idUser)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

    }

    @Test
    void given2UserParams_setUser1ByUser2_thenReturn200() throws Exception {

        User user1 = new User(1L, "jUnit_test1", "jUnitTest1!", "junit@test.one", BigDecimal.valueOf(1.11), true, "photoPath1");

        User user2 = new User(2L, "jUnit_test2", "jUnitTest2@", "junit@test.two", BigDecimal.valueOf(2.22), false, "photoPath2");

        when(userService.getUserById(user1.getIdUser())).thenReturn(user1);
        doNothing().when(userService).update(user1.getIdUser(), user2);

        mockMvc.perform(post("/api/Uusers/{id}", user1.getIdUser())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUser", equalTo(2)));
    }

}