package pl.pizzeria.meal.web;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.pizzeria.meal.domain.Meal;
import pl.pizzeria.meal.domain.MealType;
import pl.pizzeria.order.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RequiredArgsConstructor
@WebMvcTest(MealController.class)
class MealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealServiceImpl mealService;

    @MockBean
    private OrderServiceImpl orderService;

    private List<Meal> init() {
        Meal meal = Meal.builder()
                .id(1L)
                .name("name")
                .price(BigDecimal.valueOf(12))
                .mealType(MealType.DRINKS)
                .build();

        return List.of(meal);
    }

    @Test
    public void shouldGetMenu() throws Exception {
        given(mealService.findAll()).willReturn(init());

        mockMvc.perform(get("/v1/menu"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath(("$.[0].id")).value(1L));
    }

    @Test
    public void shouldGetEmptyResponseNoContent() throws Exception {
        given(mealService.findAll()).willReturn(List.of());

        mockMvc.perform(get("/v1/menu"))
                .andExpect(status().isGone())
                .andExpect(jsonPath("$").doesNotExist());
    }
}