package com.example.firemarket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firemarket.models.ProductModel;

public class DataDetailFragment extends Fragment {

    private static String category, name, reference, price;
    private Boolean active;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_data_detail_category, tv_data_detail_name, tv_data_detail_ref, tv_data_detail_Price;

        tv_data_detail_category = view.findViewById(R.id.tv_data_detail_category);
        tv_data_detail_name = view.findViewById(R.id.tv_data_detail_name);
        tv_data_detail_ref = view.findViewById(R.id.tv_data_detail_ref);
        tv_data_detail_Price = view.findViewById(R.id.tv_data_detail_Price);

        tv_data_detail_category.setText((category));
        tv_data_detail_name.setText(name);
        tv_data_detail_ref.setText(reference);
        tv_data_detail_Price.setText(price);


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DataDetailFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    static void receiveData(Bundle bundle){
        ProductModel model = (ProductModel) bundle.getSerializable("model");
        if(model != null){
            category = model.getCategory();
            name = model.getName();
            reference = model.getRef();
            price = model.getPrice();
        }
    }
}