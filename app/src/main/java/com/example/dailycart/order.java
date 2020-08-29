package com.example.dailycart;
/**
 *
 * @author Keyur  Mistry
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

/**
 * order class to take the order details of the customer
 */
public class order extends AppCompatActivity {
    ListView order_cart_detail;
    Button con_ord;
    TextView order_user_name,order_number,order_email;
    TextView add_line_1,add_line_2,add_line_3,pincode_edit,area,city,state;
    DatePicker date;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        order_cart_detail=findViewById(R.id.order_cart_detail);
        order_user_name=findViewById(R.id.order_user_name);
        order_email=findViewById(R.id.order_email);
        order_number=findViewById(R.id.order_number);

        add_line_1=findViewById(R.id.add_line_1_order);
        date=findViewById(R.id.date);
        add_line_2=findViewById(R.id.add_line_2_order);
        add_line_3=findViewById(R.id.add_line_3_order);
        pincode_edit=findViewById(R.id.pincode_edit_order);
        area=findViewById(R.id.area_edit_order);
        state=findViewById(R.id.state_edit_order);
        city=findViewById(R.id.city_edit_order);

        con_ord=findViewById(R.id.con_ord);
    }
}