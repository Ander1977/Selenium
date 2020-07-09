package test.java.order;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class OrderTest {

    @Test
    void shouldInputIsCurrent() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Копатилов Андрей");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[class='Success_successBlock__2L3Cw']").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInputNameNotCurrent() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Kopatilov Andrey");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $(By.className("input_invalid")).shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        $("[data-test-id='name']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldInputPhoneNotCorrect() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Копатилов Андрей");
        $("[data-test-id='phone'] input").setValue("89098765432");
        $("[data-test-id=agreement]").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='phone']").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        $("[data-test-id='phone']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldChecboxNotClick() {
        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Копатилов Андрей");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[class='checkbox__text']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldNameNotInput() {
        open("http://localhost:9999");
        $("[data-test-id='phone'] input").setValue("+79098765432");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id='name']").shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id='name']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

}

