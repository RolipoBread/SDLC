package com.example.laba1.model;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class ExcuseGenerator {

    private final Random random = new Random();

    private final Map<Situation, List<String>> excuses = Map.of(
            Situation.УЧЕБА, List.of(
                    "Забыл тетрадь дома, а в телефоне не было заметок.",
                    "Не понял, что задавали — никто не объяснил.",
                    "Готовился всю ночь, но всё вылетело из головы.",
                    "Заболел и не успел доделать."
            ),
            Situation.РАБОТА, List.of(
                    "Проект не собрался из-за конфликта зависимостей.",
                    "Интернет отвалился ровно в момент деплоя.",
                    "Ждал ответа от коллеги, а он ушёл на встречу.",
                    "VPN не работал, не смог подключиться к серверу."
            ),
            Situation.ОПОЗДАНИЕ, List.of(
                    "Автобус уехал прямо перед носом.",
                    "Пробка на мосту из-за аварии.",
                    "Будильник не сработал — телефон разрядился.",
                    "Лифт застрял между этажами.",
                    "Собака отказалась выходить на улицу."
            ),
            Situation.ЗАБЫВЧИВОСТЬ, List.of(
                    "Записал, но заметка удалилась.",
                    "Думал, что это на следующей неделе.",
                    "Мне никто не напомнил.",
                    "Всё было в голове, но утром исчезло."
            )
    );

    public String generate(Situation situation) {
        List<String> pool = excuses.get(situation);
        return pool.get(random.nextInt(pool.size()));
    }
}