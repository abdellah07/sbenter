package com.polytech.ihm.activities.demand;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.polytech.ihm.R;
import com.polytech.ihm.models.BasketAdapter;
import com.polytech.ihm.models.BasketHelper;
import com.polytech.ihm.models.Extra;
import com.polytech.ihm.models.IBasketAdapterListener;

import java.util.ArrayList;

public class Requests extends AppCompatActivity implements IBasketAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        ArrayList<BasketHelper> baskets = getIntent().getParcelableArrayListExtra(Extra.basketRList);

        //Création et initialisation de l'Adapter pour les diplomes
        BasketAdapter adapter = new BasketAdapter(this, baskets);

        //Récupération du composant ListView
        ListView list = findViewById(R.id.listView);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);

        //écouter si on clique
        adapter.addListner(this);

    }
    @Override
    public void onClickBasket(BasketHelper basketHelper) {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("basket");
        builder.setMessage("Vous avez cliqué sur "+ basketHelper.getTitle());
        builder.setNeutralButton("OK", null);
        builder.show();
    }
}