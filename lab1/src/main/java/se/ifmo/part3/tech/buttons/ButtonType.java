package se.ifmo.part3.tech.buttons;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ButtonType {
    EXPLOSION_BUTTON("Стоит только спасти людей от взрыва — и они сразу станут твоими друзьями."),
    UNKNOWN_BUTTON("Жизнь — как папка 11111, никогда не знаешь, что там. С этой кнопкой так же...");
    final String description;

    ButtonType(String description) {
        this.description = description;
    }
}
