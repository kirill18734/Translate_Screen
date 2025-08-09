package com.example.my_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class TTS_Speed {
    private final Context context;
    private final String PREFS_NAME = "settings";
    private final String KEY_DELAY = "tts_speed";

    public TTS_Speed(Context context) {
        this.context = context;
    }

    void onProgressChanged(SeekBar seekBar, TextView valueText ) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // Загружаем сохранённое значение (если есть)
        int savedProgress = prefs.getInt(KEY_DELAY, 0);
        seekBar.setProgress(savedProgress);
        updateText(valueText, savedProgress);
        // Слушатель изменений

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateText(valueText, progress);

                // Сохраняем новое значение
                prefs.edit().putInt(KEY_DELAY, progress).apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }
    private void updateText(TextView valueText, int progress) {
        if (progress < 49) {
            float seconds = 1 + progress;
            valueText.setText(String.format(Locale.US, "%d%%", (int)seconds));
        } else {
            valueText.setText("\u221E");
        }
    }
}
