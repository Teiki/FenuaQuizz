package fr.teiki.fenuaquizz.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import fr.teiki.fenuaquizz.R;
import fr.teiki.fenuaquizz.models.ButterKnifeFragment;
import fr.teiki.fenuaquizz.objects.IslandInfoHolder;

public class GameFragment extends ButterKnifeFragment {



	@BindView(R.id.img_island)
	ImageView imageViewIsland;

	@BindView(R.id.img_archipel)
	ImageView imageViewArchipel;

	@BindView(R.id.txt_score)
	TextView txt_score;

	@BindViews({R.id.btn_haut_gauche, R.id.btn_haut_droit, R.id.btn_bas_gauche, R.id.btn_bas_droit})
	List<Button> btnResponses;

	private ArrayList<IslandInfoHolder> islandInfoHolders = new ArrayList<>();
	private ArrayList<IslandInfoHolder> islandInfoHoldersToGuess = new ArrayList<>();


	private WeakReference<PlayActivity> weakReference;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_game, container, false);
		unbinder = ButterKnife.bind(this, view);
		return view;
	}

	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		weakReference = new WeakReference<>((PlayActivity) getActivity());

		setGame(weakReference.get().archipel_choosen);
		launchGame();

	}


	private void launchGame() {
		if (!islandInfoHoldersToGuess.isEmpty()) {
			IslandInfoHolder islandInfoHolder = islandInfoHoldersToGuess.remove(0);

			imageViewArchipel.setImageBitmap(islandInfoHolder.getImg_archipel_island(weakReference.get()));
			imageViewIsland.setImageBitmap(islandInfoHolder.getImg_island(weakReference.get()));


			List<IslandInfoHolder> islandInfoHoldersPossibleResponses = pick3RandomOtherThan(islandInfoHolder);
			islandInfoHoldersPossibleResponses.add(islandInfoHolder);
			Collections.shuffle(islandInfoHoldersPossibleResponses);
			setButtonResponse(islandInfoHoldersPossibleResponses);

			setlistener(islandInfoHolder);
		} else {
			Navigation.findNavController(btnResponses.get(0)).navigate(R.id.action_FirstFragment_to_SecondFragment);
		}
	}

	private void setButtonResponse(List<IslandInfoHolder> islandInfoHoldersPossibleResponses) {
		for (int i = 0; i < 4; i++){
			btnResponses.get(i).setText(islandInfoHoldersPossibleResponses.get(i).getName());
		}
	}

	private void setlistener(final IslandInfoHolder islandInfoHolder) {
		for (Button b : btnResponses){
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Button b = (Button) v;
					onResponseChoosen(islandInfoHolder, b, (String) b.getText());

				}
			});
		}
	}

	private void onResponseChoosen(IslandInfoHolder islandInfoHolder, Button b, String choosen_name){
		if (choosen_name.equals(islandInfoHolder.getName())){
			weakReference.get().score++;
			b.setBackgroundColor(getResources().getColor(R.color.green, null));
			Toast.makeText(weakReference.get(),"Bravo!",Toast.LENGTH_SHORT).show();
		} else {
			//Lose 2 points if a mistake is made
			weakReference.get().score-=1;
			b.setBackgroundColor(getResources().getColor(R.color.red_error, null));
			for (Button b2 : btnResponses){
				if (b2.getText().equals(islandInfoHolder.getName())) {
					b2.setBackgroundColor(getResources().getColor(R.color.green, null));
				}
			}
			Toast.makeText(weakReference.get(),"Raté, réessaie!",Toast.LENGTH_SHORT).show();
			//islandInfoHoldersToGuess.add(islandInfoHolder);
		}
		txt_score.setText(String.format(Locale.getDefault(), "%s %d", getText(R.string.score_label), weakReference.get().score));

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				for (Button b2 : btnResponses){
					b2.setBackgroundColor(getResources().getColor(R.color.colorPrimary,null));
				}
				launchGame();
			}
		}, 1500);
	}

	private void setGame(String archipel_choosen){
		islandInfoHolders.clear();
		islandInfoHoldersToGuess.clear();
		for (int i = 0; i < weakReference.get().islandsNames.length; i++){
			islandInfoHolders.add(new IslandInfoHolder(weakReference.get().islandsNames[i], archipel_choosen));
		}
		islandInfoHoldersToGuess.addAll(islandInfoHolders);
		Collections.shuffle(islandInfoHoldersToGuess);
	}




	private  List<IslandInfoHolder> pick3RandomOtherThan(IslandInfoHolder islandInfoHolder) {
		islandInfoHolders.remove(islandInfoHolder);
		List<IslandInfoHolder> copy = new LinkedList<IslandInfoHolder>(islandInfoHolders);
		Collections.shuffle(copy);
		islandInfoHolders.add(islandInfoHolder);
		return copy.subList(0, 3);
	}
}
