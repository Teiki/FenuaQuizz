package fr.teiki.fenuaquizz.models;

import androidx.fragment.app.Fragment;

import butterknife.Unbinder;

/**
 * Created by Antoine GALTIER on 11/05/2020.
 */

public class ButterKnifeFragment extends Fragment {


	//ButterKnife tool for fragment managing
	protected Unbinder unbinder;


	//Don't forget to bind in the fragment like this
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		// Inflate the layout for this fragment
//		View view = inflater.inflate(R.layout.fragment_game, container, false);
//		unbinder = ButterKnife.bind(this, view);
//		return view;
//	}


	@Override public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

}
