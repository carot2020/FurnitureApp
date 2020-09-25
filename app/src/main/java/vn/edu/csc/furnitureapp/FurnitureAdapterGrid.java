package vn.edu.csc.furnitureapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FurnitureAdapterGrid extends ArrayAdapter<Categories> {
    ArrayList<Categories> arrayList;
    Utils utils ;
    public FurnitureAdapterGrid(@NonNull Context context, @NonNull ArrayList<Categories> objects) {
        super(context, 0, objects);
        arrayList = objects;
        utils = new Utils(getContext());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView = layoutInflater.inflate(R.layout.layout_cell, null);

        Categories categories = arrayList.get(position);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textView = convertView.findViewById(R.id.textView);


        textView.setText(categories.getName());

        imageView.setImageBitmap(utils.convertStringToBitmapFromAccess(categories.getImage()));

        return convertView;
    }
}