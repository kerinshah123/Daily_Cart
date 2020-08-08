package com.example.dailycart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SubcategoryAdapter extends BaseAdapter {

    Context context;

    public SubcategoryAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return sub_cat_image.length;
    }

    @Override
    public Object getItem(int position) {
        return sub_cat_image.length;
    }

    @Override
    public long getItemId(int position) {
        return sub_cat_image.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.subcatagory,null);

        RelativeLayout relativeLayout = convertView.findViewById(R.id.subid);
        ImageView image_sub = convertView.findViewById(R.id.image_sub);
        TextView sub_name = convertView.findViewById(R.id.sub_name);
        sub_name.setText(sub_cat_name[position]);
        image_sub.setImageResource(sub_cat_image[position]);
        final View finalConvertView = convertView;

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,product_list.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finalConvertView.getContext().startActivity(intent);
            }
        });

        return convertView;

    }

    String sub_cat_name[] = {"cake and pastries","chocolate","dairy products","ice-cream and frozen dessert","youghurt and shrikhand"};

    Integer sub_cat_image[] = {R.drawable.cakeandpastry,R.drawable.chocolate,R.drawable.dairyproducts,R.drawable.icecreamandfrozendessert,R.drawable.youghurtandshrikhand};
}
