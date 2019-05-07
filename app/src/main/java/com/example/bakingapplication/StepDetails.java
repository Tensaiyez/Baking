package com.example.bakingapplication;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bakingapplication.Adapters.StepDetailAdapter;
import com.example.bakingapplication.Adapters.StepsAdapter;
import com.example.bakingapplication.Utils.Recipe;
import com.example.bakingapplication.Utils.Steps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.w3c.dom.Text;

public class StepDetails extends AppCompatActivity {
 private PlayerView mPlayerView;
 private SimpleExoPlayer mExoPlayer;
 private TextView mDescription;

 Steps steps;
    Context mContext;
    Recipe recipe;

 StepDetailAdapter stepDetailAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_step_details);
        mContext =this;
        mDescription=(TextView) findViewById(R.id.stepDescription1);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            steps =(Steps) bundle.getSerializable("stepsDetails");
            recipe = (Recipe) bundle.getSerializable("recipeDetails");


        }
        mDescription.setText(steps.getDescription());
        mPlayerView=(PlayerView)findViewById(R.id.playerView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mExoPlayer= ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());
        mPlayerView.setPlayer(mExoPlayer);
        DefaultDataSourceFactory defaultDataSourceFactory=new DefaultDataSourceFactory( this, Util.getUserAgent(this,"Project4"));
        if(steps.getVideoURL().isEmpty()){
            mPlayerView.setVisibility(View.GONE);
        }
        ExtractorMediaSource mediaSource=new ExtractorMediaSource.Factory(defaultDataSourceFactory).createMediaSource(Uri.parse(steps.getVideoURL()));
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //mPlayerView.setPlayer(null);
        mExoPlayer.release();
        mExoPlayer=null;
    }

}
