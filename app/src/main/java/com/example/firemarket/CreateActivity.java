package com.example.firemarket;

import android.os.Bundle;

import com.example.firemarket.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.collect.MapMaker;
import com.google.firebase.firestore.DocumentReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateActivity extends BaseActivity {


    FloatingActionButton fab_create_save, fab_create_clear, fab_create_back;
    ImageView iv_create_image;
    TextView tv_create_click_image;
    EditText et_create_category_product, et_create_name_product, et_create_ref_product, et_create_price_product;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();
        iv_create_image.setImageResource(R.drawable.ic_widgets_24px);

        fab_create_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToList();
            }
        });

        fab_create_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });

        fab_create_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category, name, reference, price;
                boolean active;

                category = et_create_category_product.getText().toString();
                name = et_create_name_product.getText().toString();
                reference = et_create_ref_product.getText().toString();
                price = et_create_price_product.getText().toString();

                if(category.isEmpty() || name.isEmpty() || reference.isEmpty() || price.isEmpty()){
                    makeSimpleDialog("Info", "please fill all fields");

                }else{

                    model = new ProductModel();
                    model.setActive(true);
                    model.setCategory(category);
                    model.setName(name);
                    model.setRef(reference);
                    model.setPrice(price);

                    save(model);

                }


            }
        });

    }

    private void save(ProductModel model) {
        if(collectionReference != null){
            collectionReference.add(model)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                if(task.getResult() != null){
                                    makeSimpleDialog("Success", "Product saved");
                                    clear();
                                }
                                else{
                                    makeSimpleDialog("Warning", "Product not saved");
                                }


                            }else{
                                makeSimpleDialog("Error", task.getException().getMessage());
                            }
                        }
                    });

        }
        else{
            makeSimpleDialog("Error", "Database connection failed");
        }
    }


    protected void init(){
        fab_create_save = findViewById(R.id.fab_create_save);
        fab_create_clear = findViewById(R.id.fab_create_clear);
        fab_create_back = findViewById(R.id.fab_create_back);
        iv_create_image = findViewById(R.id.iv_create_image);
        tv_create_click_image = findViewById(R.id.tv_create_click_image);
        et_create_category_product = findViewById(R.id.et_create_category_product);
        et_create_name_product = findViewById(R.id.et_create_name_product);
        et_create_ref_product = findViewById(R.id.et_create_ref_product);
        et_create_price_product = findViewById(R.id.et_create_price_product);

    }

    private void clear(){
        et_create_name_product.setText("");
        et_create_category_product.setText("");
        et_create_ref_product.setText("");
        et_create_price_product.setText("");

        et_create_category_product.requestFocus();

        iv_create_image.setImageResource(R.drawable.ic_widgets_24px);


    }

}