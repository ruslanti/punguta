package com.punguta.rest;

import static com.punguta.rest.RestEventFixtures.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.punguta.services.ExpenseService;
import com.punguta.services.events.expense.ExpenseCreateEvent;
import com.punguta.services.events.expense.ExpenseRequestReadEvent;

/**
 * Created by ruslanti on 21.05.2014.
 */
public class ExpenseControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    ExpenseController controller;

    @Mock
    ExpenseService expenseService;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

        when(expenseService.create(any(ExpenseCreateEvent.class))).thenReturn(expenseCreated(1L));
    }


    @Test
    public void testListExpenses() throws Exception {

        when(expenseService.list(any(ExpenseRequestReadEvent.class))).thenReturn(generateExpenses(10));

        this.mockMvc.perform(
                get("/expenses")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void testCreateExpense() throws Exception {
        this.mockMvc.perform(
                post("/expenses")
                        .content(standardExpenseJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
