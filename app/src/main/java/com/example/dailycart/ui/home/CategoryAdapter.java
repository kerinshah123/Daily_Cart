package com.example.dailycart.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.dailycart.R;

public class CategoryAdapter extends BaseAdapter {

    Context context;
    public CategoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return cat_Image.length;
    }

    @Override
    public Object getItem(int position) {
        return cat_Image.length;
    }

    @Override
    public long getItemId(int position) {
        return cat_Image.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.categorylayout,null);

        CardView cardView;
        ImageView catimage;
        TextView cattxt;

        catimage=convertView.findViewById(R.id.cat1);
        cattxt=convertView.findViewById(R.id.txtcat1);

        catimage.setImageResource(cat_Image[position]);
        cattxt.setText(cat_name[position]);
        catimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context,sub_category.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                finalConvertView.getContext().startActivity(intent);
            }
        });


        return convertView;
    }

    String cat_name[] = {"Fruit and Vegetables","Milk","Medicine","Milk Product","Grocery"};

    Integer cat_Image[] = {R.drawable.fruitandvegetable,R.drawable.milk,R.drawable.medicine,R.drawable.milkproduct,R.drawable.grocery};
}
