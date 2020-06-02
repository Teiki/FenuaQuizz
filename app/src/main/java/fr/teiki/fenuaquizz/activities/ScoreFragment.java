package fr.teiki.fenuaquizz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.teiki.fenuaquizz.R;
import fr.teiki.fenuaquizz.models.ButterKnifeFragment;

public class ScoreFragment extends ButterKnifeFragment {

	@BindView(R.id.textview_second)
	TextView label;
	private WeakReference<PlayActivity> weakReference;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_score, container, false);
		unbinder = ButterKnife.bind(this, view);
		return view;
	}

	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		weakReference = new WeakReference<>((PlayActivity) getActivity());

		label.setText(String.format(Locale.getDefault(),"Score : %d / %d", weakReference.get().score, weakReference.get().islandsNames.length));

		view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getContext(), MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
