package marti.com.example.exampleapp.view.gameDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import marti.com.example.exampleapp.R;
import marti.com.example.exampleapp.entity.GameIgdbDetail;

/**
 * Created by mferrando on 28/06/16.
 */

public class GameDetailsActivity extends AppCompatActivity {

    private static String EXTRA_GAME = "EXTRA_GAME";

    public static Intent newIntent(Context context, GameIgdbDetail game) {
        Intent intent = new Intent(context, GameDetailsActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable("EXTRA_GAME", game);
        //mBundle.putString("COVER",urlCover);
        intent.putExtras(mBundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_games_detail);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Game g = getIntent().get(EXTRA_GAME);
        GameIgdbDetail gameIgdbDetail = (GameIgdbDetail)getIntent().getParcelableExtra(EXTRA_GAME);
        String provisionalCover = getIntent().getStringExtra("COVER");


        GameDetailsFragment fragment = GameDetailsFragment.newInstance(gameIgdbDetail, provisionalCover);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_game, fragment).commit();

        // launch fragment
     //   getSupportFragmentManager().beginTransaction().replace(R.id.frame, new SearchFragment()).commit();

    }
}

