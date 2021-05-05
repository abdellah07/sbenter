package com.polytech.ihm.activities.give;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.polytech.ihm.R;
import com.polytech.ihm.fragment.PictureFragment;
import com.polytech.ihm.models.BasketHelper;
import com.polytech.ihm.models.IPictureActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class Donate extends AppCompatActivity implements IPictureActivity {
    private PictureFragment pictureFragment;
    private Bitmap picture;
    private BasketHelper.Type[] types;
    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        types = new BasketHelper.Type[4];
        types[0]= BasketHelper.Type.HONEYCOOB;
        types[1]= BasketHelper.Type.WOODEN;
        types[2]= BasketHelper.Type.CORRUGATED;
        types[3]= BasketHelper.Type.PLAT;

        ArrayAdapter<BasketHelper.Type> adapter = new ArrayAdapter<>(
                getApplicationContext(),R.layout.list_item,types
        );

        autoCompleteTextView = findViewById(R.id.dropDown);
        autoCompleteTextView.setAdapter(adapter);
        //(textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        pictureFragment = (PictureFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentPicture);
        if (pictureFragment == null) {
            pictureFragment = new PictureFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace( R.id.fragmentPicture, pictureFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }



    // When results returned from PictureFragment
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                picture = (Bitmap) data.getExtras().get("data");
                pictureFragment.setImage(picture);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action canceled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action Failed", Toast.LENGTH_LONG).show();
            }
        }
    }


}
