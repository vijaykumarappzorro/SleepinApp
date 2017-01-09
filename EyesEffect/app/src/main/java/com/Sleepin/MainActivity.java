package com.Sleepin;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.Sleepin.util.IabHelper;
import com.Sleepin.util.IabResult;
import com.Sleepin.util.Inventory;
import com.Sleepin.util.Purchase;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    ImageView imageView, playimage;
    ArrayList<Integer> array_image;

    MediaPlayer mPlayer;
    String playStatus = "pause";
    int currentPos = 0;
    int postion =0;
    private AdView mAdView;
    ImageView removeadds;
   static final String ITEM_SKU = "android.test.purchased";
   // static  final  String ITEM_SKU="android.sleep.in";

    String staus ="";
    InterstitialAd mInterstitialAd;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Timer timer;
    TimerTask task;
    private static final String TAG = "nnn";
    TextView txtmusic;
    IabHelper mHelper;
    ArrayList<String>purchaseid;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        playimage = (ImageView) findViewById(R.id.play);
        removeadds =(ImageView)findViewById(R.id.removeadds);
        purchaseid = new ArrayList<>();
        txtmusic = (TextView)findViewById(R.id.txtmusic);
        sharedPreferences = getSharedPreferences("SKUDETAIL", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        String skudeatil = sharedPreferences.getString("SKU","");
        Log.e("SKU SAVE",""+skudeatil);


        //String base64publickey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlKitUYIurF28R0us5RJWoUC88kFAZvvAbAibHzhgwUSWx8AC7iiKpq20po+n8JX/qTDHCcyUYUp4Bfzewgtmi87U3cCDo+xl5x+fg4ZZ5gOr1YPmymc6BJuvL91ESaUOp3IeWUgFb7N9PHPyFI7MJ0fV4UfOrtKqPg90itYfwzu3nJGpkkEjD3pnE93HsNjm8JDH4bUJuto/HBN79mey4PKYA8IBigsQCtYE4s3dC+w+Xs3fKCVEjiB8RJSLFvEmQU/Ws4MAWZubS55+Tnk51v8SlUnQb7rKrzLV1dmZX6J4mwlVB7aZUh9bCTQy0GbUUBZ8ZK4k2Z/Jv1aU15BhEwIDAQAB";
        array_image = new ArrayList<Integer>();
        mPlayer = new MediaPlayer();

        array_image.add(R.drawable.bg_slider_1);
        array_image.add(R.drawable.bg_slider_2);
        array_image.add(R.drawable.bg_slider_3);
        array_image.add(R.drawable.bg_slider_4);
        array_image.add(R.drawable.bg_slider_5);
        array_image.add(R.drawable.bg_slider_6);
        array_image.add(R.drawable.bg_slider_7);
        array_image.add(R.drawable.bg_slider_8);
        array_image.add(R.drawable.bg_slider_9);
        array_image.add(R.drawable.bg_slider_10);

        array_image.add(R.drawable.bg_slider_11);
        array_image.add(R.drawable.bg_slider_12);
        array_image.add(R.drawable.bg_slider_13);
        array_image.add(R.drawable.bg_slider_14);
        array_image.add(R.drawable.bg_slider_14);
        array_image.add(R.drawable.bg_slider_15);
        array_image.add(R.drawable.bg_slider_16);
        array_image.add(R.drawable.bg_slider_17);
        array_image.add(R.drawable.bg_slider_18);
        array_image.add(R.drawable.bg_slider_19);
        array_image.add(R.drawable.bg_slider_20);

        array_image.add(R.drawable.bg_slider_21);
        array_image.add(R.drawable.bg_slider_22);
        array_image.add(R.drawable.bg_slider_23);
        array_image.add(R.drawable.bg_slider_24);
        array_image.add(R.drawable.bg_slider_25);
        array_image.add(R.drawable.bg_slider_26);
        array_image.add(R.drawable.bg_slider_27);
        array_image.add(R.drawable.bg_slider_28);
        array_image.add(R.drawable.bg_slider_29);
        array_image.add(R.drawable.bg_slider_30);

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlKitUYIurF28R0us5RJWoUC88kFAZvvAbAibHzhgwUSWx8AC7iiKpq20po+n8JX/qTDHCcyUYUp4Bfzewgtmi87U3cCDo+xl5x+fg4ZZ5gOr1YPmymc6BJuvL91ESaUOp3IeWUgFb7N9PHPyFI7MJ0fV4UfOrtKqPg90itYfwzu3nJGpkkEjD3pnE93HsNjm8JDH4bUJuto/HBN79mey4PKYA8IBigsQCtYE4s3dC+w+Xs3fKCVEjiB8RJSLFvEmQU/Ws4MAWZubS55+Tnk51v8SlUnQb7rKrzLV1dmZX6J4mwlVB7aZUh9bCTQy0GbUUBZ8ZK4k2Z/Jv1aU15BhEwIDAQAB";

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAdView = (AdView) findViewById(R.id.adView);
      /*  if (skudeatil.equals(ITEM_SKU)){

            mAdView.setVisibility(View.GONE);
            removeadds.setVisibility(View.GONE);
        }*/
        mInterstitialAd = new InterstitialAd(MainActivity.this);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.instrument);
        removeadds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Do you want to remove ads permanently for $ 0.99 ?.");
                        alertDialogBuilder.setPositiveButton("Proceed",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        try {
                                            mHelper.launchPurchaseFlow(MainActivity.this, ITEM_SKU, 10001,
                                                    mPurchaseFinishedListener, "mypurchasetoken");
                                        } catch (IabHelper.IabAsyncInProgressException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



            }
        });


        playimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playStatus.equals("play")) {

                    playStatus = "pause";
                    playimage.setImageResource(R.drawable.ic_icon_play);
                    mPlayer.seekTo(currentPos);
                    mPlayer.start();

                    //  playStatus="stop";
                } else if (playStatus.equals("pause")) {

                    playStatus = "play";
                    currentPos = mPlayer.getCurrentPosition();
                    mPlayer.pause();
                    playStatus = "play";
                    playimage.setImageResource(R.drawable.ic_icon_pause);
                }
            }
        });

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                Log.i("Completion Listener","Song Complete");
                currentPos =0;
                mp.start();



            }
        });

        mHelper = new IabHelper(MainActivity.this, base64EncodedPublicKey);




        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                                       public void onIabSetupFinished(IabResult result)
                                       {
                                           if (!result.isSuccess()) {
                                               Log.d(TAG, "In-app : " +
                                                       result);



                                           } else {
                                               Log.d(TAG, "In-app Billing is set up OK");
                                               try {
                                                   List<String> itemsku= new ArrayList<String>();
                                                   itemsku.add(ITEM_SKU);

                                                  // mHelper.queryInventoryAsync(mGotInventoryListener);
                                                     mHelper.queryInventoryAsync( true,itemsku,null,mReceivedInventoryListener);
                                               } catch (IabHelper.IabAsyncInProgressException e) {
                                                   e.printStackTrace();
                                               }
                                           }
                                       }

                                   });
    }

    // Listener that's called when we finish querying the items and subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {

            Log.d("PAY", "Query inventory finished.");// Have we been disposed of in the meantime? If so, quit.
             if (mHelper == null)return;
            Purchase purchase = inventory.getPurchase(ITEM_SKU);
            if (purchase != null){

                mAdView.setVisibility(View.GONE);
                removeadds.setVisibility(View.GONE);
               // Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();

            }

            else {
                mAdView.setVisibility(View.VISIBLE);
                removeadds.setVisibility(View.VISIBLE);
               // Toast.makeText(MainActivity.this, "Please make a purchase of the product to get rid off the ads", Toast.LENGTH_SHORT).show();
            }


        }

    };

    @Override
    public void onStart() {
        super.onStart();
        Log.e("on start", "");


    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("on stop", "");

        AppEventsLogger.deactivateApp(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);


        // set the ad unit ID
        if (staus.equals("pause")){
            mPlayer.seekTo(currentPos);
            mPlayer.start();
            currentPos = mPlayer.getCurrentPosition();
            Log.e("Music postion",String.valueOf(currentPos));
            playimage.setImageResource(R.drawable.ic_icon_play);

        }
        else {



            mPlayer.start();
        }


        timer = new Timer();
            Log.e("dfdf", "dfdsf");
            task = new TimerTask() {
                int i  =0;


                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (staus.equals("pause")){
                                Log.e("dfd2233f", "df23233dsf");
                                imageView.setImageResource(array_image.get(postion));
                                postion++;
                                i= postion;
                                Log.e("last postioniiii", "" + String.valueOf(i));

                                if (postion> array_image.size() - 1) {

                                    postion = 0;
                                    Log.e("postion check",String.valueOf(postion));
                                }
                            }
                            else {
                                Log.e("dfd2233f", "df23233dsf");
                                imageView.setImageResource(array_image.get(i));
                                i++;
                                postion = i;
                                Log.e("last postion", "" + String.valueOf(postion));
                                if (i > array_image.size() - 1) {
                                    i = 0;
                                }
                            }

                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(task, 0, 4000);
        }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data)
    {
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result,
                                          Purchase purchase)
        {
            if (result.isFailure()) {
                // Handle error
                return;
            }


            else if (purchase.getSku().equals(ITEM_SKU)) {
                Log.e("campare","itm sku");
                consumeItem();
                //buyButton.setEnabled(false);
            }

        }
    };
    public void consumeItem() {
        try {
            mHelper.queryInventoryAsync(mReceivedInventoryListener);
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
    }
    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {


            try {

                  mHelper.queryInventoryAsync(mGotInventoryListener);
                //  mHelper.queryInventoryAsync( true,itemsku,null,mGotInventoryListener);
            } catch (IabHelper.IabAsyncInProgressException e) {
                e.printStackTrace();
            }

            if (result.isFailure()) {



            } else {
                try {
                    Log.e("Queryfinishedlistner","in app purchase");
                    mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU),
                            mConsumeFinishedListener);

                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase,
                                              IabResult result) {

                    if (result.isSuccess()) {
                        //clickButton.setEnabled(true);
                        Log.e("resultttttttt",String.valueOf(result));
                        Log.e("LIST FINAL",purchase.toString());
                        Log.e("skudetasil",purchase.getSku());
                        Log.e("package name",purchase.getPackageName());
                        editor.putString("SKU",purchase.getSku()).apply();

                        if (purchase.getSku().equals(ITEM_SKU)){

                            mAdView.setVisibility(View.GONE);
                            removeadds.setVisibility(View.GONE);
                        }





                    } else {
                        // handle error
                    }
                }
            };



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("on destroy", "");
        if (mHelper != null) try {
            mHelper.dispose();
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
        mHelper = null;
    }

    @Override
    protected void onPause() {

        Log.e("on pgggause", "rrrr");
        timer.cancel();
        timer.purge();
        staus ="pause";
        currentPos = mPlayer.getCurrentPosition();
        mPlayer.pause();



        super.onPause();

    }
}
