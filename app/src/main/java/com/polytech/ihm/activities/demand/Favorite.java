package com.polytech.ihm.activities.demand;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polytech.ihm.R;
import com.polytech.ihm.activities.Basket;
import com.polytech.ihm.models.BasketAdapter;
import com.polytech.ihm.models.BasketHelper;
import com.polytech.ihm.models.Extra;
import com.polytech.ihm.models.IBasketAdapterListener;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity implements IBasketAdapterListener {
    private ImageView back;
    private ArrayList<BasketHelper> baskets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        baskets = getIntent().getParcelableArrayListExtra(Extra.basketFList);

        //hooks
        back = findViewById(R.id.back_nav);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //Création et initialisation de l'Adapter pour les diplomes
        BasketAdapter adapter = new BasketAdapter(this, baskets);

        //Récupération du composant ListView
        ListView list = findViewById(R.id.listView);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);

        //écouter si on clique
        adapter.addListner(this);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClickBasket(BasketHelper basketHelper) {
        Intent intentBasket = new Intent(this, Basket.class);
        intentBasket.putExtra(Extra.moreInfo,(Parcelable) basketHelper);
        startActivity(intentBasket);
    }

}