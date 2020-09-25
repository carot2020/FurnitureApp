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

public class FurnitureAdapter extends ArrayAdapter<Furniture> {
    Utils utils ;

    ArrayList<Furniture> arrayList;
    public FurnitureAdapter(@NonNull Context context, @NonNull ArrayList<Furniture> objects) {
        super(context, 0, objects);
        arrayList = objects;
        utils = new Utils(getContext());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView = layoutInflater.inflate(R.layout.layout_item,null);

        Furniture furniture = arrayList.get(position);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textView = convertView.findViewById(R.id.textView2);
        TextView textView2 = convertView.findViewById(R.id.textView4);

        textView.setText(furniture.getName());
        textView2.setText(furniture.getDescription());
        imageView.setImageBitmap(utils.convertStringToBitmapFromAccess(furniture.getImage()));

        return convertView;
    }
}
