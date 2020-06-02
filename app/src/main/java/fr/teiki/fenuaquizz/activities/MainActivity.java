package fr.teiki.fenuaquizz.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.teiki.fenuaquizz.R;
import fr.teiki.fenuaquizz.adapters.ActionsAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

	@BindView(R.id.list_archipels)
	ListView listViewActions;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.action_bar);

		listViewActions.setAdapter(new ActionsAdapter(this));
		listViewActions.setOnItemClickListener( this);
	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(getBaseContext(), PlayActivity.class);
		intent.putExtra(PlayActivity.TAG_DATA, getResources().getStringArray(R.array.main_list_img_archipels)[position]);
		startActivity(intent);
	}
}
