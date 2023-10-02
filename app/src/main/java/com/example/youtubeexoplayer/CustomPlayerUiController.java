package com.example.youtubeexoplayer;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.utils.FadeViewHelper;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBar;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBarListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class CustomPlayerUiController extends AbstractYouTubePlayerListener {
    private final YouTubePlayerTracker playerTracker;
    private final YouTubePlayer youTubePlayer;
    private final YouTubePlayerView youTubePlayerView;
    private boolean fullscreen = false;

    CustomPlayerUiController(View customPlayerUi, YouTubePlayer youTubePlayer, YouTubePlayerView youTubePlayerView) {
        this.youTubePlayer = youTubePlayer;
        this.youTubePlayerView = youTubePlayerView;

        playerTracker = new YouTubePlayerTracker();
        youTubePlayer.addListener(playerTracker);

        initViews(customPlayerUi);
    }

    private void initViews(View playerUi) {
        View panel = playerUi.findViewById(R.id.panel);
        RelativeLayout relativeLayout = playerUi.findViewById(R.id.root);
        YouTubePlayerSeekBar youTubePlayerSeekBar = playerUi.findViewById(R.id.youtube_player_seekbar);
        ImageButton playPauseButton = playerUi.findViewById(R.id.play_pause_button);
        ImageButton enterExitFullscreenButton = playerUi.findViewById(R.id.enter_exit_fullscreen_button);
        youTubePlayer.addListener(youTubePlayerSeekBar);

        youTubePlayerSeekBar.setYoutubePlayerSeekBarListener(new YouTubePlayerSeekBarListener() {
            @Override
            public void seekTo(float time) {
                youTubePlayer.seekTo(time);
            }
        });

        playPauseButton.setOnClickListener((view) -> {
            if (playerTracker.getState() == PlayerConstants.PlayerState.PLAYING) {
                playPauseButton.setImageResource(R.drawable.baseline_play_circle_filled_24);
                youTubePlayer.pause();
            } else {
                playPauseButton.setImageResource(R.drawable.baseline_pause_circle_filled_24);
                youTubePlayer.play();
            }
        });

        enterExitFullscreenButton.setOnClickListener((view) -> {
            if (fullscreen) youTubePlayerView.wrapContent();
            else youTubePlayerView.matchParent();

            fullscreen = !fullscreen;
        });

        FadeViewHelper fadeViewHelper = new FadeViewHelper(panel);
        fadeViewHelper.setAnimationDuration(FadeViewHelper.DEFAULT_ANIMATION_DURATION);
        fadeViewHelper.setFadeOutDelay(FadeViewHelper.DEFAULT_FADE_OUT_DELAY);
        youTubePlayer.addListener(fadeViewHelper);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fadeViewHelper.toggleVisibility();
            }
        });

        panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fadeViewHelper.toggleVisibility();
            }
        });
    }

    @Override
    public void onReady(@NonNull YouTubePlayer youTubePlayer) {

    }

    @Override
    public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCurrentSecond(@NonNull YouTubePlayer youTubePlayer, float second) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onVideoDuration(@NonNull YouTubePlayer youTubePlayer, float duration) {

    }
}