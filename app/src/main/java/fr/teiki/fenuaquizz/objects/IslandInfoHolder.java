package fr.teiki.fenuaquizz.objects;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Antoine GALTIER on 07/05/2020.
 */

public class IslandInfoHolder {

	private final String DATA_PATH = "R.id.";

	private String name;
	private String archipel;

	private static String[] mangareva_islands = {"akamaru", "aukena", "taravai", "rikitea"};
	private static List<String> mangareva_islands_list = Arrays.asList(mangareva_islands);

	public IslandInfoHolder(String name, String archipel) {
		this.name = name;
		this.archipel = archipel;
	}

	public String getName() {
		return name;
	}

	public String getArchipel() {
		return archipel;
	}

	public Bitmap getImg_archipel_island(Context context) {
		AssetManager assetManager = context.getAssets();
		try {
			InputStream is = assetManager.open("archipels/"+archipel+"_"+name.toLowerCase()+".png");
			return BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Bitmap getImg_island(Context context) {
		AssetManager assetManager = context.getAssets();
		try {
			String temp_name = name;
			if (mangareva_islands_list.contains(name)){
				temp_name = "mangareva";
			}
			InputStream is = assetManager.open("island/"+archipel+"/"+temp_name.toLowerCase()+".png");
			return BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	//Use in adapter
	public static Bitmap getImg_archipel(Context context, String archipel_name) {
		AssetManager assetManager = context.getAssets();
		try {
			InputStream is = assetManager.open("archipels/"+archipel_name+".png");
			return BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Bitmap getImg_drapeau(Context context, String archipel_name) {
		AssetManager assetManager = context.getAssets();
		try {
			InputStream is = assetManager.open("drapeau/drapeau_"+archipel_name+".png");
			return BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
