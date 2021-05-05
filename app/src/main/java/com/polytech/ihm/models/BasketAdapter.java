package com.polytech.ihm.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.polytech.ihm.R;
import java.util.ArrayList;

public class BasketAdapter extends BaseAdapter {

    private ArrayList<BasketHelper> baskets;
    private IBasketAdapterListener listener;
    private LayoutInflater layoutInflater;

    public BasketAdapter(Context context, ArrayList<BasketHelper>  baskets) {
        this.baskets = baskets;
        System.out.println(baskets);
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return baskets.size();
    }

    @Override
    public Object getItem(int position) {
        return baskets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout layoutItem;

        //(1) : Réutilisation des layouts
        layoutItem = (LinearLayout) (convertView == null ? layoutInflater.inflate(R.layout.basket_item, parent, false) : convertView);

        //(2) : Récupération des TextView de notre layout
        TextView title = layoutItem.findViewById(R.id.basketTitle);
        TextView description = layoutItem.findViewById(R.id.basketDescription);
        ImageView basketPicture = layoutItem.findViewById(R.id.basketPicture);

        //(3) : Renseignement des valeurs
        title.setText( baskets.get(position).getTitle());
        description.setText(Float.toString(this.baskets.get(position).getWeight() )+"kg");  //TODO change uggly text format
        basketPicture.setImageResource(baskets.get(position).getPicture());
        //(4) Changement de la couleur du fond de notre item
        // tvPrice.setTextColor( baskets.get(position).getDescription());

        //écouter si clic sur la vue
        layoutItem.setOnClickListener( click -> {
            listener.onClickBasket(baskets.get(position));
        });

        //On retourne l'item créé.
        return layoutItem;
    }

    //abonnement à un écouteur de Pizza
    public void addListner(IBasketAdapterListener listener){
        this.listener = listener;
    }
}
