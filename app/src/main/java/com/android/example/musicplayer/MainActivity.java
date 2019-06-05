package com.android.example.musicplayer;

import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    ImageView playPauseIcon;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);
        playPauseIcon = findViewById(R.id.playButton);
        int colorTextTitle = ContextCompat.getColor(this, R.color.title_color);
        seekBar = findViewById(R.id.seekBar);
        seekBar.getProgressDrawable().setColorFilter(colorTextTitle, PorterDuff.Mode.SRC_ATOP);
        seekBar.getThumb().setColorFilter(colorTextTitle, PorterDuff.Mode.SRC_ATOP);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);

    }
    public void playButton(View view) {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            playPauseIcon.setImageResource(R.drawable.ic_play_arrow_orange_24dp);
        }else{

            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.ic_pause_orange_24dp);
        }
    }

    public void skipPrev(final View view) {
        seekBar.setProgress(0);
        mediaPlayer.seekTo(0);
        mediaPlayer.pause();
        playPauseIcon.setImageResource(R.drawable.ic_play_arrow_orange_24dp);
    }

    public void skipForw(View view) {
        seekBar.setProgress(seekBar.getMax());
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        mediaPlayer.seekTo(0);
        playPauseIcon.setImageResource(R.drawable.ic_play_arrow_orange_24dp);
        mediaPlayer.pause();


    }

    public void showText(View view) {
        String songText = "Время за полночь, и мне осталось три пути." + "\n"
                + "Не один ли бес, куда ступать, зачем идти?" + "\n"
                + "Опустились руки, поднялись корни." + "\n"
                + "Монету на удачу. Руку чёрт не дёрни!!!" + "\n"
                + "Первая дорога – сидеть, ждать смирно." + "\n"
                +  "Быть каменно спокойным, быть абсолютно мирным," + "\n"
                + "Только ветер знает, сколько нужно силы," + "\n"
                + "Чтобы не слететь с катушек и ждать судьбы красиво!!!" + "\n"
                + "Второй выбор мне – стряхнуть пыль, сорваться!" + "\n"
                + "Извините, мол, но не могу остаться!" + "\n"
                + "И лететь, мчаться в неизвестность зимы," + "\n"
                + "А как замёрзнуть просто знают звери и мы!!!" + "\n"
                + "А про третий путь не скажу ни слова." + "\n"
                + "Он у каждого свой – раз и готово!" + "\n"
                + "О том, как всё просто знают телефона провода." + "\n"
                + "Этот третий путь будет всем всегда-всегда!";
        Toast toast = Toast.makeText(getApplicationContext(), songText, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0 , 0);
        toast.show();
    }
}
