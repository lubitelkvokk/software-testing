package se.ifmo.part3.tech.buttons;

import lombok.Getter;

@Getter
public enum ButtonType {
    EXPLOSION_BUTTON("Стоит только спасти людей от взрыва — и они сразу станут твоими друзьями."),
    UNKNOWN_BUTTON("Жизнь — как папка 11111, никогда не знаешь, что там. С этой кнопкой так же..."),
    MOVE_FORWARD_BUTTON("Просто вперед"), MOVE_BACKWARD_BUTTON("Просто назад"), MOVE_RIGHT_BUTTON("Просто вправо"), MOVE_LEFT_BUTTON("Налево мы не ходим");
    final String description;

    ButtonType(String description) {
        this.description = description;
    }
}
