package com.example.firemarket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.firemarket.adapters.ProductAdapter;
import com.example.firemarket.connection.FirebaseConnection;
import com.example.firemarket.models.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {
    //Esta baseactivity servirá como guia o template para todas las actividades y no tener que repetir código escribiendo todas estas variables

    protected ProductModel model;
    protected ArrayList<ProductModel> productModelArrayList;
    protected ProductAdapter adapter;

    //Base de datos de firestore
    protected FirebaseFirestore db;
    protected FirebaseAuth mAuth;
    //protected StorageReference mStorageRef;
    protected FirebaseStorage mfirebaseStorage;

    //Query
    protected Query query;
    protected CollectionReference collectionReference;
    protected StorageReference mStorageReference, fileReference;

    protected final String COLLECTION_NAME = "products";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void init(){
        model = new ProductModel();
        db = FirebaseConnection.ConnectionFirestore();
        mAuth = FirebaseConnection.ConnectionAuth();
        mfirebaseStorage = FirebaseConnection.ConnectionStorage();
        collectionReference = db.collection(COLLECTION_NAME);
    }

    protected void makeSimpleToast(String message, int duration){
        Toast.makeText(this, message, duration).show();
    }

    protected void makeSimpleDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void goToList(){

        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    protected void goToCreate(){

        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    protected void goToEdit(){

        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }


    protected void goToSearch(){

        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }

    protected void goToDetail(ProductModel model){

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("model", model);
        startActivity(intent);
    }


}