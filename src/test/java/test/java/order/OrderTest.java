package test.java.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.className;

public class OrderTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        $("[data-test-id='name']").shouldHave(text("Укажите точно как в паспорте"));
        $$(className("input__sub")).get(1).shouldHave(text("На указанный номер моб. тел. будет отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[class='input__sub']").shouldHave(cssValue("color", "rgba(11, 31, 53, 0.6)"));
        $$(className("input__sub")).get(1).shouldHave(cssValue("color", "rgba(11, 31, 53, 0.6)"));
    }

    @Test
    void shouldInputIsCurrent() {
        $("[data-test-id='name'] input").setValue("Копатилов Андрей");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[class='Success_successBlock__2L3Cw']").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInputNotCurrent() {
        $("[data-test-id='name'] input").setValue("Kopatilov Andrey");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $(By.className("input_invalid")).shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        $("[data-test-id='name']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

}

