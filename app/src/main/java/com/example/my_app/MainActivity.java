package com.example.my_app;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Задержка закрытия
        ConstraintLayout container_pinning_delay = findViewById(R.id.container_pinning_delay);
        SeekBar container_seekbar_pinning_delay = findViewById(R.id.container_seekbar_pinning_delay);
        container_seekbar_pinning_delay.setVisibility(View.GONE);
        container_pinning_delay.setOnClickListener(v -> show_seekbar(v, container_seekbar_pinning_delay));
        TextView container_value_pinning_delay = findViewById(R.id.container_value_pinning_delay);
        PinningDelay pinningDelay = new PinningDelay(this);
        pinningDelay.onProgressChanged(container_seekbar_pinning_delay, container_value_pinning_delay);
        //Прозрачность меню
        ConstraintLayout container_menu_transparency = findViewById(R.id.container_menu_transparency);
        SeekBar container_seekbar_menu_transparency = findViewById(R.id.container_seekbar_menu_transparency);
        container_seekbar_menu_transparency.setVisibility(View.GONE);
        container_menu_transparency.setOnClickListener(v -> show_seekbar(v, container_seekbar_menu_transparency));
        TextView container_value_menu_transparency = findViewById(R.id.container_value_menu_transparency);
        MenuTransparency menu_transparency = new MenuTransparency(this);
        menu_transparency.onProgressChanged(container_seekbar_menu_transparency, container_value_menu_transparency);
        //Функции меню
        ConstraintLayout container_menu_functions = findViewById(R.id.container_menu_functions);
        SeekBar container_seekbar_menu_functions = findViewById(R.id.container_seekbar_menu_functions);
        container_seekbar_menu_functions.setVisibility(View.GONE);
        container_menu_functions.setOnClickListener(v -> show_seekbar(v, container_seekbar_menu_functions));
        //Прозрачность перевода
        ConstraintLayout container_transparency_of_the_transfer = findViewById(R.id.container_transparency_of_the_transfer);
        SeekBar container_seekbar_transparency_of_the_transfer = findViewById(R.id.container_seekbar_transparency_of_the_transfer);
        container_seekbar_transparency_of_the_transfer.setVisibility(View.GONE);
        container_transparency_of_the_transfer.setOnClickListener(v -> show_seekbar(v, container_seekbar_menu_transparency));
        TextView container_value_transparency_of_the_transfer = findViewById(R.id.container_value_transparency_of_the_transfer );
        TransparencyOfTheTransfer transparency_of_the_transfer = new TransparencyOfTheTransfer(this);
        transparency_of_the_transfer.onProgressChanged(container_seekbar_transparency_of_the_transfer, container_value_transparency_of_the_transfer);
        //Задержка закрытия
        //Прозрачность ответа
        //Голоса TTS
        //Скорость TTS
    }

    void show_seekbar(View v, SeekBar seekBar){
        if (seekBar.getVisibility() == View.GONE) {
            // Показать SeekBar
            seekBar.setAlpha(1f);
            seekBar.setVisibility(View.VISIBLE);

            // Через 3 секунды запустить анимацию скрытия
            new Handler().postDelayed(() -> {
                // Анимация плавного затухания (альфа 1 -> 0)
                AlphaAnimation fadeOut = new AlphaAnimation(1f, 0f);
                fadeOut.setDuration(500); // длительность анимации 0.5 секунды
                fadeOut.setFillAfter(true); // сохранить состояние после анимации
                fadeOut.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(android.view.animation.Animation animation) {}

                    @Override
                    public void onAnimationEnd(android.view.animation.Animation animation) {
                        seekBar.setVisibility(View.GONE); // после анимации скрываем view
                        seekBar.setAlpha(1f); // сбрасываем прозрачность для следующего показа
                    }

                    @Override
                    public void onAnimationRepeat(android.view.animation.Animation animation) {}
                });

                seekBar.startAnimation(fadeOut);

            }, 3000); // задержка 3000 мс = 3 секунды

        } else {
            // Если уже виден — просто скрываем без анимации
            seekBar.setVisibility(View.GONE);
            seekBar.setAlpha(1f); // сброс прозрачности
        }
        }
}
