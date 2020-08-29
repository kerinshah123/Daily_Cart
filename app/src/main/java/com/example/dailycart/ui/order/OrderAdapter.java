package com.example.dailycart.ui.order;
/**
 *
 * @author Kerin  Shah
 */
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.dailycart.R;


public class OrderAdapter extends BaseAdapter {

    Context context;

    public OrderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return pro_image.length;
    }

    @Override
    public Object getItem(int position) {
        return pro_image.length;
    }

    @Override
    public long getItemId(int position) {
        return pro_image.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.order_custom,null);

        ImageView product_image = convertView.findViewById(R.id.cart_image);
        TextView product_name = convertView.findViewById(R.id.cartproductname);
        TextView product_rates = convertView.findViewById(R.id.cartproductrate);
        TextView product_quantity = convertView.findViewById(R.id.cartproductqty);
        Button show_order_details = convertView.findViewById(R.id.show_order_details);


        product_image.setImageResource(pro_image[position]);
        product_name.setText(pro_name[position]);
        product_rates.setText(rate[position]);
        product_quantity.setText(qty[position]);

        final View finalConvertView = convertView;
        show_order_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,OrderDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finalConvertView.getContext().startActivity(intent);
            }
        });




        return convertView;
    }
    String rate[]={"20","25","15"};
    String qty[]={"2","4","1"};
    String pro_name[]={"Black Forest" , "Cheese Cake" , "Chocolate Walnet Pastry"};
    Integer pro_image[]={R.drawable.blackforest,R.drawable.cheesecake, R.drawable.chocolatewalnetp};

}
