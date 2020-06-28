package com.example.firemarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.example.firemarket.R;
import com.example.firemarket.models.ProductModel;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ProductModel> modelArrayList;

    public ProductAdapter(Context context, ArrayList<ProductModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public ProductModel getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.product_list_item, parent, false);
        }

        TextView tv_product_list_item_categoria = convertView.findViewById(R.id.tv_product_list_item_categoria);
        TextView tv_product_list_item_nombre = convertView.findViewById(R.id.tv_product_list_item_nombre);
        TextView tv_product_list_item_ref = convertView.findViewById(R.id.tv_product_list_item_ref);
        TextView  tv_product_list_item_precio  = convertView.findViewById(R.id.tv_product_list_item_precio);
        ImageView iv_product_list_item_image = convertView.findViewById(R.id.iv_product_list_item_image);

        tv_product_list_item_categoria.setText(getItem(position).getCategory());
        tv_product_list_item_nombre.setText(getItem(position).getName());
        tv_product_list_item_ref.setText(getItem(position).getRef());
        tv_product_list_item_precio.setText(getItem(position).getPrice());
        iv_product_list_item_image.setImageResource(R.drawable.ic_widgets_24px);

        return convertView;
    }
}
