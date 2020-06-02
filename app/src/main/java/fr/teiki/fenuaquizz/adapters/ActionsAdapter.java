package fr.teiki.fenuaquizz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.teiki.fenuaquizz.R;
import fr.teiki.fenuaquizz.objects.IslandInfoHolder;


public class ActionsAdapter extends BaseAdapter {

	private final Context mContext;
	private String[] main_descriptions;
	private String[] archipel_names;

	// 1
	public ActionsAdapter(Context context) {
		this.mContext = context;
		this.main_descriptions = context.getResources().getStringArray(R.array.main_list_deciptions);
		this.archipel_names = context.getResources().getStringArray(R.array.main_list_img_archipels);
	}

	// 2
	@Override
	public int getCount() {
		return main_descriptions.length;
	}

	// 3
	@Override
	public long getItemId(int position) {
		return 0;
	}

	// 4
	@Override
	public Object getItem(int position) {
		return main_descriptions[position];
	}

	@Override public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		if (view != null) {
			holder = (ViewHolder) view.getTag();
		} else {
			view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		holder.img_item_archipel.setImageBitmap(IslandInfoHolder.getImg_archipel(mContext,archipel_names[position]));
		holder.img_item_drapeau.setImageBitmap(IslandInfoHolder.getImg_drapeau(mContext,archipel_names[position]));
		holder.txt_desc.setText(main_descriptions[position]);


		return view;
	}

	static class ViewHolder {
		@BindView(R.id.img_item_archipel)
		ImageView img_item_archipel;
		@BindView(R.id.img_item_drapeau)
		ImageView img_item_drapeau;
		@BindView(R.id.txt_desc)
		TextView txt_desc;

		private ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}
}

