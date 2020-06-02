package fr.teiki.fenuaquizz.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import fr.teiki.fenuaquizz.R;

public class PlayActivity extends AppCompatActivity {

	String archipel_choosen;
	String[] islandsNames;
	static final String TAG_DATA = "TAG_DATA_GAME_ACTIVITY";
	int score = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		setArrays(Objects.requireNonNull(getIntent().getStringExtra(TAG_DATA)));

		Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
		getSupportActionBar().setCustomView(R.layout.action_bar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void setArrays(String archipel_choosen){
		if (archipel_choosen.contains("societe")) {
			islandsNames = getResources().getStringArray(R.array.societe_islands);
			this.archipel_choosen = "societe";
		} else if (archipel_choosen.contains("tuamotu")) {
			islandsNames = getResources().getStringArray(R.array.tuamotu_islands);
			this.archipel_choosen = "tuamotu";
		} else if (archipel_choosen.contains("gambier")) {
			islandsNames = getResources().getStringArray(R.array.gambier_islands);
			this.archipel_choosen = "gambier";
		} else if (archipel_choosen.contains("australes")) {
			islandsNames = getResources().getStringArray(R.array.australes_islands);
			this.archipel_choosen = "australes";
		} else if (archipel_choosen.contains("marquises")) {
			islandsNames = getResources().getStringArray(R.array.marquises_islands);
			this.archipel_choosen = "marquises";
		}

		if (islandsNames == null || islandsNames.length == 0){
			Toast.makeText(this, "Pas encore prêt", Toast.LENGTH_SHORT).show();
			finish();
		}

	}

}
