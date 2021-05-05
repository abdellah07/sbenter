package com.polytech.ihm.activities.demand;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.polytech.ihm.R;
import com.polytech.ihm.activities.Basket;
import com.polytech.ihm.activities.give.Donate;
import com.polytech.ihm.activities.give.MyList;
import com.polytech.ihm.activities.give.Request;
import com.polytech.ihm.activities.stats.TweetActivity;
import com.polytech.ihm.activities.stats.statistics;
import com.polytech.ihm.models.BasketHelper;
import com.polytech.ihm.models.Extra;
import com.polytech.ihm.models.Util;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Demand extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String CHANNEL_1_ID = "ch1";
    private static final int PERMISSION_FINE_LOCATION = 99;
    private MapView map;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private ArrayList<BasketHelper> markers;
    private ArrayList<BasketHelper> favoriteBaskets;
    private ArrayList<BasketHelper> reqBaskets;
    //coordonée
    private double myLongitude;
    private double myLattitude;
    private MyLocationNewOverlay mLocationOverlay;
    //menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button menuIcon;
    private static final String TAG = "tag";
    //location variables, google location
    FusedLocationProviderClient fusedLocationProviderClient;
    //--Location request is a config file for all the settings related to fusedLocationProviderClient
    LocationRequest locationRequest;
    private SwitchMaterial sw_gps;
    private int notificationId = 1;
    private LocationCallback locationCallBack;
    private Marker localisationM;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().
                load(
                        getApplicationContext(),
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                );
        setContentView(R.layout.activity_demand);
        createNotificationChannel();
        //initialise baskets
        favoriteBaskets = new ArrayList<>();
        reqBaskets = new ArrayList<>();

        map = findViewById(R.id.map);
        // Hooks Drawer Menu
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu);
        //TileSourceFactory.PUBLIC_TRANSPORT
        //http://leaflet-extras.github.io/leaflet-providers/preview/
        map.setTileSource(TileSourceFactory.MAPNIK); //render
        map.setBuiltInZoomControls(true);            //zoomable
        //should be Localisation
        //configure location
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000 * 30);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        sw_gps = findViewById(R.id.sw_gps);

        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location location = locationResult.getLastLocation();
                System.out.println("+++++++++++>> " + location);
                myLattitude = location.getLatitude();
                myLongitude = location.getLongitude();
            }
        };

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw_gps.isChecked()) {
                    //Use GPS
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    startLocalisationUpdates();
                    localisationM = new Marker(map);
                    localisationM.setPosition(new GeoPoint(myLattitude,myLongitude));
                    localisationM.setTitle("my current location");
                    localisationM.setDraggable(false);
                    localisationM.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                    localisationM.setIcon(getResources().getDrawable(R.drawable.osm_ic_follow_me));

                    map.getOverlayManager().add(localisationM);

                    localisationM.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker, MapView mapView) {
                            if (localisationM.isInfoWindowShown())
                                localisationM.closeInfoWindow();
                            else {
                                localisationM.showInfoWindow();
                            }
                            return true;
                        }
                    });
                } else {
                    // using Towers + wifi
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    stopLocalisationUpdates();
                    map.getOverlays().remove(localisationM);
                }
            }
        });
        updateGPS();
        //--demo 43.675050, 7.058324

        GeoPoint startPoint = new GeoPoint(43.675050, 7.058324);
        IMapController mapController = map.getController();
        mapController.setZoom(14.0);
        mapController.setCenter(startPoint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            generateMarkers();
        }
        //map.getOverlays().add(addMarker(new GeoPoint(43.61572296415799, 7.071842570348114)));
        map.invalidate();
        //navigation Drawer
        navifationDrawer();
    }

    private void stopLocalisationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);
    }

    private void startLocalisationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateGPS();
    }

    //markers
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void generateMarkers() {
        markers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            addMarker(
                    new BasketHelper(generateTitle(), generateDescription(), generateWeight(), generatePNumber(), generateType(), generateGeoPoint(), generatePicture())
            );
        }
    }

    private int generatePicture() {
        Random rand = new Random();
        switch (rand.nextInt(5)) {
            case 0:
                return R.drawable.image1;
            case 1:
                return R.drawable.image2;
            case 2:
                return R.drawable.image3;
            case 3:
                return R.drawable.image4;
            case 4:
                return R.drawable.image5;
        }
        return R.drawable.image1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String generateDescription() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        //Util.print(getApplicationContext(),generatedString);
        return generatedString;
    }

    private float generateWeight() {
        return new Random().nextInt(100);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String generatePNumber() {
        return "06" + (10000000 + new Random().nextInt(90000000));
    }

    private BasketHelper.Type generateType() {
        switch (new Random().nextInt(4)) {
            case 0:
                return BasketHelper.Type.HONEYCOOB;
            case 1:
                return BasketHelper.Type.CORRUGATED;
            case 2:
                return BasketHelper.Type.PLAT;
            case 3:
                return BasketHelper.Type.WOODEN;
        }
        return BasketHelper.Type.HONEYCOOB;
    }

    private GeoPoint generateGeoPoint() {
        Random rand = new Random();
        return new GeoPoint(rand.nextDouble() * 0.1 + 43.6, rand.nextDouble() * 0.1 + 7);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String generateTitle() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        //Util.print(getApplicationContext(),"title:"+generatedString);
        return generatedString;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Marker addMarker(BasketHelper basketHelper) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("basket").child(basketHelper.getPhoneNumber()).setValue(basketHelper);


        markers.add(basketHelper);
        Marker m = new Marker((map));
        m.setPosition(basketHelper.getMyGeoPoint());
        m.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        m.setIcon(getResources().getDrawable(R.drawable.icons8_trolley_100));
        m.setDraggable(false);
        m.setInfoWindow(new MapCustomInfoBubble(map, basketHelper));
        m.setInfoWindowAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_TOP);

        map.getOverlayManager().add(m);
        //OnClick
        m.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                if (m.isInfoWindowShown())
                    m.closeInfoWindow();
                else {
                    m.showInfoWindow();
                }
                return true;
            }
        });
        return m;

    }

    //Menu
    private void navifationDrawer() {
        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        //by default the nav_profile is selected
        navigationView.setCheckedItem(R.id.demandG);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.demand:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.requests:
                Intent intentRequest = new Intent(this, Requests.class);
                intentRequest.putParcelableArrayListExtra(Extra.basketRList, reqBaskets);
                startActivity(intentRequest);
                break;
            case R.id.favorite:
                Intent intentFavorite = new Intent(this, Favorite.class);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("favoriteBasket");
                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        int length = 10;
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            //System.out.println(dataSnapshot.getChildren().iterator().next());
                            //(String title, String description, float weight, String phoneNumber, Type type,GeoPoint myGeoPoint,int picture)
                            favoriteBaskets.add(
                                    new BasketHelper(
                                            dataSnapshot1.child("title").getValue(String.class),
                                            dataSnapshot1.child("description").getValue(String.class),
                                            dataSnapshot1.child("weight").getValue(Integer.class),
                                            dataSnapshot1.getKey(),
                                            dataSnapshot1.child("type").getValue(BasketHelper.Type.class),
                                            new GeoPoint(dataSnapshot1.child("myGeoPoint").child("latitude").getValue(Double.class), dataSnapshot1.child("myGeoPoint").child("longitude").getValue(Double.class)),
                                            dataSnapshot1.child("picture").getValue(Integer.class)
                                    )
                            );
                            System.out.println(favoriteBaskets.size());
                        }
                        Log.d(TAG, "Value is: ");
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
                intentFavorite.putParcelableArrayListExtra(Extra.basketFList, favoriteBaskets);
                startActivity(intentFavorite);
                break;
            case R.id.new_requests:
                Intent intentReq = new Intent(this, Request.class);
                startActivity(intentReq);
                break;
            case R.id.give:
                Intent intentD = new Intent(this, Donate.class);
                startActivity(intentD);
                break;
            case R.id.myList:
                Intent intentL = new Intent(this, MyList.class);
                startActivity(intentL);
                break;
            case R.id.stats:
                Intent intentS = new Intent(this, statistics.class);
                startActivity(intentS);
                break;
            case R.id.tweet:
                Intent intentT = new Intent(this, TweetActivity.class);
                startActivity(intentT);
                break;


        }
        return true;
    }

    //Map lifeCycle
    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }


    //voir profile.xml
    public void toProfile(View view) {
        Intent intentProfile = new Intent(this, com.polytech.ihm.activities.Profile.class);
        startActivity(intentProfile);
    }

    public void makeCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE);
        if (result == PackageManager.PERMISSION_GRANTED) {

            startActivity(intent);

        } else {

            requestPermission();
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Demand.this, Manifest.permission.CALL_PHONE)) {
        } else {

            ActivityCompat.requestPermissions(Demand.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall(null);
                }
                break;
            case PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                } else {
                    Util.print(getApplicationContext(), "this app require permission to be granted in order to work properly");
                    finish();
                }
                break;
        }
    }

    //set location all the stuff you need is here
    //step 1: add implementation 'com.google.android.gms:play-services-location:18.0.0' to gradle dependencies
    //step 2: code
    private void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Demand.this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //the user provided the permission
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    //we got the permissions. Put the values of location. XXX into the UI component
                    System.out.println("myLocalisation ------------> "+location);

                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_FINE_LOCATION);
            }
        }
    }

    //costume the marker
    class MapCustomInfoBubble extends InfoWindow {
        private BasketHelper basketHelper;

        public MapCustomInfoBubble(MapView mapView, BasketHelper basketHelper) {
            super(R.layout.map_infobubble_black, mapView);//my custom layout and my mapView
            this.basketHelper = basketHelper;
        }

        @Override
        public void onClose() {
            //by default, do nothing
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onOpen(Object item) {
            Marker marker = (Marker) item; //the marker on which you click to open the bubble
            //marker infos
            TextView title = mView.findViewById(R.id.name);
            TextView desc = mView.findViewById(R.id.bubble_desc);
            TextView weight = mView.findViewById(R.id.weight);
            TextView type = mView.findViewById(R.id.type);
            ImageView favorite = (ImageView) mView.findViewById(R.id.bubble_favorie);
            ImageView agenda = (ImageView) mView.findViewById(R.id.bubble_agenda);
            ImageView call = (ImageView) mView.findViewById(R.id.bubble_call);
            Button request = (Button) mView.findViewById(R.id.request);

            title.setText("title: " + basketHelper.getTitle());
            desc.setText("description: " + basketHelper.getDescription());
            weight.setText("weight: " + basketHelper.getWeight());
            type.setText("type :" + basketHelper.getType());

            call.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    makeCall(basketHelper.getPhoneNumber());
                }
            });
            agenda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Event is on January 23, 2021 -- from 7:30 AM to 10:30 AM.
                    Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
                    Calendar beginTime = Calendar.getInstance();
                    beginTime.set(2021, 0, 23, 7, 30);
                    Calendar endTime = Calendar.getInstance();
                    endTime.set(2021, 0, 23, 10, 30);
                    calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                    calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                    calendarIntent.putExtra(CalendarContract.Events.TITLE, "Basket");
                    calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo");
                    startActivity(calendarIntent);
                }
            });
            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Util.print(getApplicationContext(),"test");
                    boolean isfilled = Util.checkImageResource(v.getContext(), favorite, R.drawable.icons8_heart_24);
                    if (isfilled) {
                        favorite.setImageResource(R.drawable.icons8_heart_48);
                        favoriteBaskets.add(basketHelper);
                        //System.out.println(basketHelper);
                    } else {
                        favorite.setImageResource(R.drawable.icons8_heart_24);
                        favoriteBaskets.remove(basketHelper);
                    }


                }
            });
            request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Util.print(getApplicationContext(),"request clicked");
                    reqBaskets.add(basketHelper);
                    v.setEnabled(false);
                    //add notification to the
                    addNotification();
                }
            });

        }

    }

    private void addNotification() {

        Intent intent = new Intent(this, Demand.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.avatar)
                .setContentTitle("New request ! ")
                .setContentText("A request has been sent, please check your request list")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder.build());

        notificationId++;

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = ("Channel_1");
            String description = ("Channel_1_description");
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_1_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
