package ru.se4oev.appnotifications;

/**
 * Created by karpenko on 25.08.2022.
 * Description:
 */
public enum NotificationType {

    INFO("Информация", "info.png"),
    ERROR("Ошибка", "error.png"),
    WARNING("Внимание", "warning.png");

    private String text;
    private String image;

    NotificationType(String text, String image) {
        this.text = text;
        this.image = image;
    }

    public String text() {
        return text;
    }

    public String image() {
        return image;
    }

}
