package com.example.dailycart.ui.shoppingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailycart.R;


public class CartAdapter extends BaseAdapter {

    Context context;

    public CartAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return pro_image.length;
    }

    @Override
    public Object getItem(int i) {
        return pro_image[i];
    }

    @Override
    public long getItemId(int i) {
        return pro_image[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.cart,null);



        ImageView cartimage = view.findViewById(R.id.cart_image);
        TextView pname = view.findViewById(R.id.cartproductname);
        TextView price = view.findViewById(R.id.cartproductrate);
        TextView qty = view.findViewById(R.id.cartproductqty);
        Button cartproductdelete = view.findViewById(R.id.cartproductdelete);

        cartproductdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });

        cartimage.setImageResource(pro_image[i]);
        pname.setText(pro_name[i]);
        price.setText(rate[i]);
        qty.setText(qauntity[i]);
        return view;
    }


    String qauntity[]={"1","1"};
    String rate[]={"20","25"};
    String pro_name[]={"Black Forest" , "Cheese Cake" };
    Integer pro_image[]={R.drawable.blackforest,R.drawable.cheesecake};
}
