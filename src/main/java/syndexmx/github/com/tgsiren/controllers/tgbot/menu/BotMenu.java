package syndexmx.github.com.tgsiren.controllers.tgbot.menu;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class BotMenu {

    public static ReplyKeyboardMarkup prepareKeyboard(List<String> itemList) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboard.add(keyboardRow);
        for (String item : itemList) {
                keyboardRow.add(item);
            keyboard.add(keyboardRow);
        }
        keyboardRow = new KeyboardRow();
        keyboardRow.add("Подписки");
        keyboardRow.add("Последнее");
        keyboard.add(keyboardRow);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
