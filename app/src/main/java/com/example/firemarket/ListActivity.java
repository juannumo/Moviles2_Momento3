package com.example.firemarket;

import android.os.Bundle;

import com.example.firemarket.adapters.ProductAdapter;
import com.example.firemarket.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {

    private FloatingActionButton fab_list_create;
    private ListView lv_list_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.init();
        init();
        getProducts();

        fab_list_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreate();
            }
        });


        lv_list_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model = productModelArrayList.get(position);
                //makeSimpleDialog("Opening", "Product: " + model.getCategory());
                goToDetail(model);
            }
        });



    }

    protected void init(){
        fab_list_create = findViewById(R.id.fab_list_create);
        lv_list_products = findViewById(R.id.lv_list_products);

    }

    protected void getProducts(){
        if(collectionReference != null){
            collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        if(task.getResult() != null){
                            productModelArrayList = new ArrayList<>();
                            for(QueryDocumentSnapshot snapshot :task.getResult()){
                                model = snapshot.toObject(ProductModel.class);
                                productModelArrayList.add(model);
                            }
                            if(productModelArrayList.size() > 0){
                                paintProducts(productModelArrayList);
                            }
                            else{
                                makeSimpleDialog("Alert", "Product not found");
                            }

                        }
                        else{
                        makeSimpleDialog("Warning", "Product not found");
                        }

                    }else{
                        makeSimpleDialog("Error", task.getException().getMessage());
                    }

                }
            });
        }
        else{
            makeSimpleToast("Database error", 1);
        }

    }

    private void paintProducts(ArrayList<ProductModel> productModelArrayList) {
        adapter = new ProductAdapter(this, productModelArrayList);
        lv_list_products.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getProducts();
    }
}