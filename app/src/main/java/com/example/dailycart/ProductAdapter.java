package com.example.dailycart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class ProductAdapter extends BaseAdapter {

    Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return pro_image.length;
    }

    @Override
    public Object getItem(int position) {
        return pro_image[position];
    }

    @Override
    public long getItemId(int position) {
        return pro_image[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.product_custom,null);

        CardView cardView = convertView.findViewById(R.id.cardViewproduct_list);
        ImageView product_image = convertView.findViewById(R.id.product_image);
        TextView product_name = convertView.findViewById(R.id.txtproduct1);
        TextView product_rates = convertView.findViewById(R.id.txtproductprice);

        product_image.setImageResource(pro_image[position]);
        product_name.setText(pro_name[position]);
        product_rates.setText(rate[position]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return convertView;
    }

    String rate[]={"20","25","15","30","50"};
    String pro_name[]={"Black Forest" , "Cheese Cake" , "Chocolate Walnet Pastry" , "Chocolate Walnet Cake" , "Orange Pastry"};
    Integer pro_image[]={R.drawable.blackforest,R.drawable.cheesecake,R.drawable.chocolatewalnetp,R.drawable.chocolatewalnetcake,R.drawable.orangepastry};
}
