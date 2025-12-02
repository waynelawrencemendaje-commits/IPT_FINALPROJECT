package com.example.ipt_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;


public class MainDashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Top Icons
        findViewById(R.id.iv_watch_later).setOnClickListener(v -> startActivity(new Intent(this, MainWatchLater.class)));
        findViewById(R.id.iv_account).setOnClickListener(v -> startActivity(new Intent(this, MainAccount.class)));

        // Movie Posters → Fixed IDs (iv_ prefix!)
        findViewById(R.id.iv_pacific_rim).setOnClickListener(v -> openPacificRim(v));
        findViewById(R.id.iv_arrival).setOnClickListener(v -> openArrival(v));
        findViewById(R.id.iv_matrix).setOnClickListener(v -> openMatrix(v));
        findViewById(R.id.iv_inception).setOnClickListener(v -> openInception(v));
        findViewById(R.id.iv_terminator).setOnClickListener(v -> openTerminator(v));
        findViewById(R.id.iv_johnwick).setOnClickListener(v -> openJohnWick(v));
        findViewById(R.id.iv_three).setOnClickListener(v -> openThree(v));
        findViewById(R.id.iv_wonderwoman).setOnClickListener(v -> openWonderWoman(v));
        findViewById(R.id.iv_extraction).setOnClickListener(v -> openExtraction(v));
        findViewById(R.id.iv_superman).setOnClickListener(v -> openSuperman(v));
        findViewById(R.id.iv_lordoftherings).setOnClickListener(v -> openLordOfTheRings(v));
        findViewById(R.id.iv_fiftyshades).setOnClickListener(v -> openFiftyShadesofGrey(v));
        findViewById(R.id.iv_blindside).setOnClickListener(v -> openBlindSide(v));
        findViewById(R.id.iv_mebeforeyou).setOnClickListener(v -> openMeBeforeYou(v));
        findViewById(R.id.iv_impossible).setOnClickListener(v -> openImpossible(v));
        findViewById(R.id.hangover).setOnClickListener(v -> openTheHangover(v));
        findViewById(R.id.central_intelligence).setOnClickListener(v -> openCentralIntelligence(v));
        findViewById(R.id.three_idiots).setOnClickListener(v -> openThreeIdiots(v));
        findViewById(R.id.iv_jumnanji).setOnClickListener(v -> openJumanji(v));
        findViewById(R.id.iv_pitchperfect).setOnClickListener(v -> openPitchPerfect(v));
        findViewById(R.id.it).setOnClickListener(v -> openIT(v));
        findViewById(R.id.saw).setOnClickListener(v -> openSaw(v));
        findViewById(R.id.us).setOnClickListener(v -> openUs(v));
        findViewById(R.id.years_later).setOnClickListener(v -> openYearsLater(v));
        findViewById(R.id.smile).setOnClickListener(v -> openSmile(v));


    }

    public void openPacificRim(View view) {
        startActivity(new Intent(this, MainPacificRim.class));
    }

    public void openArrival(View view) {
        startActivity(new Intent(this, MainArrival.class));
    }

    public void openMatrix(View view) {
        startActivity(new Intent(this, MainMatrix.class));
    }

    public void openInception(View view) {
        startActivity(new Intent(this, MainInception.class));
    }

    public void openTerminator(View view) {
        startActivity(new Intent(this, MainTerminator.class));
    }

    public void openJohnWick(View view) {startActivity(new Intent(this, MainJohnWick.class));
    }

    public void openThree(View view) {startActivity(new Intent(this, MainThree.class));
        }

    public void openWonderWoman(View view) {startActivity(new Intent(this, MainWonderWoman.class));
    }

    public void openExtraction(View view) {startActivity(new Intent(this, MainExtraction.class));
    }

    public void openSuperman(View view) {startActivity(new Intent(this, MainSuperman.class));
    }

    public void openLordOfTheRings(View view) {startActivity(new Intent(this, MainLordOfTheRings.class));
    }

    public void openFiftyShadesofGrey(View view) {startActivity(new Intent(this, MainFiftyShadesOfGrey.class));
    }

    public void openBlindSide(View view) {startActivity(new Intent(this, MainBlindSide.class));
    }

    public void openMeBeforeYou(View view) {startActivity(new Intent(this, MainMeBeforeYou.class));
    }

    public void openImpossible(View view) {startActivity(new Intent(this, MainImpossible.class));
    }

    public void openTheHangover(View view) {startActivity(new Intent(this, MainTheHangover.class));
    }

    public void openCentralIntelligence(View view) {startActivity(new Intent(this, MainCentralIntelligence.class));
    }

    public void openThreeIdiots(View view) {startActivity(new Intent(this, MainThreeIdiots.class));
    }

    public void openJumanji(View view) {startActivity(new Intent(this, MainJumanji.class));
    }

    public void openPitchPerfect(View view) {startActivity(new Intent(this, MainIT.class));
    }

    public void openIT(View view) {startActivity(new Intent(this, MainIT.class));
    }

    public void openSaw(View view) {startActivity(new Intent(this, MainSaw.class));
    }

    public void openUs(View view) {startActivity(new Intent(this, MainUs.class));
    }

    public void openYearsLater(View view) {startActivity(new Intent(this, MainYearsLater.class));
    }

    public void openSmile(View view) {startActivity(new Intent(this, MainSmile.class));
    }

}