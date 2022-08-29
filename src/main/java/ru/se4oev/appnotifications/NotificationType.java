package ru.se4oev.appnotifications;

/**
 * Created by karpenko on 25.08.2022.
 * Description:
 */
public enum NotificationType {

    INFO("Информация", "info"),
    ERROR("Ошибка", "error"),
    WARNING("Внимание", "warning");

    private String text;
    private String style;

    NotificationType(String text, String style) {
        this.text = text;
        this.style = style;
    }

    public String text() {
        return text;
    }

    public String style() {
        return style;
    }

}
