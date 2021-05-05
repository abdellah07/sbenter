package com.polytech.ihm.models;

import android.view.View;
import android.widget.ImageView;

import com.polytech.ihm.R;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

public class MapCustomInfoBubble extends InfoWindow {

    public MapCustomInfoBubble(MapView mapView) {
        super(R.layout.map_infobubble_black, mapView);//my custom layout and my mapView
    }

    @Override
    public void onClose() {
        //by default, do nothing
    }

    @Override
    public void onOpen(Object item) {
        Marker marker = (Marker)item; //the marker on which you click to open the bubble

        String title = marker.getTitle();
        if (title == null)
            title = "";

        ImageView call = (ImageView) mView.findViewById(R.id.bubble_call);//the button that I have in my XML;
        call.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

}

