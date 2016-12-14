package com.adrianjaime.calmatumente;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by emaneff on 01/12/2016.
 */

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private ImageButton btnInicio, btnMeditacion, btnConfiguracion;
    private Button btnWebLink, btnMinutoMeditacion, btnMeditacionMenteCuerpo, btnMeditacionLiberaEstres, btnMeditacionInsomnio;
    private TextView tvAppName, tvDoctorName;
    private ImageView imgViewLogo;
    private VideoView vvInicio;
    private LinearLayout layoutButton, layoutLogo, layoutText, layoutMeditacion;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_home);

        initControls();

        Uri path = Uri.parse("android.resource://com.adrianjaime.calmatumente/" + R.raw.presentacion);
        //AMPLIACIÓN DE LOS CONTROLES
        MediaController mediaCtrl = new MediaController(this);
        vvInicio.setMediaController(mediaCtrl);
        vvInicio.setVideoURI(path);


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.slide_1,
                R.layout.slide_2,
                R.layout.slide_3,
                R.layout.slide_4};

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        final Animation animationFadeIn_1 = AnimationUtils.loadAnimation(this, R.anim.fadein_1);
        final Animation animationFadeIn_2 = AnimationUtils.loadAnimation(this, R.anim.fadein_2);
        final Animation animationFadeIn_3 = AnimationUtils.loadAnimation(this, R.anim.fadein_3);

        btnInicio.startAnimation(animationFadeIn_1);
        btnMeditacion.startAnimation(animationFadeIn_1);
        btnConfiguracion.startAnimation(animationFadeIn_1);
        imgViewLogo.startAnimation(animationFadeIn_2);
        tvDoctorName.startAnimation(animationFadeIn_2);
        tvAppName.startAnimation(animationFadeIn_3);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Light.ttf");
        tvAppName.setTypeface(customFont);


        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutLogo.setVisibility(View.GONE);
                layoutMeditacion.setVisibility(View.GONE);

                viewPager.setCurrentItem(1);

                layoutButton.setVisibility(View.VISIBLE);
                layoutText.setVisibility(View.VISIBLE);
                //vvInicio.start();
            }
        });

        btnMeditacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if(vvInicio.isPlaying()){
                    vvInicio.stopPlayback();
                }
                */
                layoutLogo.setVisibility(View.GONE);
                layoutText.setVisibility(View.GONE);

                viewPager.setCurrentItem(2);
                layoutButton.setVisibility(View.VISIBLE);
                layoutMeditacion.setVisibility(View.VISIBLE);
            }
        });

        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                if(vvInicio.isPlaying()){
                    vvInicio.stopPlayback();
                }
                */
                layoutButton.setVisibility(View.GONE);
                layoutLogo.setVisibility(View.GONE);
                layoutText.setVisibility(View.GONE);
                layoutMeditacion.setVisibility(View.GONE);

                viewPager.setCurrentItem(3);
            }
        });

        btnWebLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.adrianjaime.com.ar/"));
                startActivity(i);
            }
        });

        btnMinutoMeditacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeActivity.this, "Abrir ventana Minuto Meditacion", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, MinMeditacionActivity.class);
                startActivity(intent);
            }
        });

        btnMeditacionMenteCuerpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeActivity.this, "Abrir ventana Meditacion Mente Cuerpo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, MeditacionActivity.class);
                intent.putExtra("meditacion", 1);
                startActivity(intent);
            }
        });

        btnMeditacionLiberaEstres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeActivity.this, "Abrir ventana Meditacion Libera Estres", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, MeditacionActivity.class);
                intent.putExtra("meditacion", 2);
                startActivity(intent);
            }
        });

        btnMeditacionInsomnio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HomeActivity.this, "Abrir ventana Meditacion Insomnio", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, MeditacionActivity.class);
                intent.putExtra("meditacion", 3);
                startActivity(intent);
            }
        });

    }

    private void initControls() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        btnInicio = (ImageButton) findViewById(R.id.btnInicio);
        btnMeditacion = (ImageButton) findViewById(R.id.btnMeditacion);
        btnConfiguracion = (ImageButton) findViewById(R.id.btnConfiguracion);
        btnWebLink = (Button) findViewById(R.id.btnWebLink);
        btnMinutoMeditacion = (Button) findViewById(R.id.btnMinutoMeditacion);
        btnMeditacionMenteCuerpo = (Button) findViewById(R.id.btnMeditacionMenteCuerpo);
        btnMeditacionLiberaEstres = (Button) findViewById(R.id.btnMeditacionLiberaEstres);
        btnMeditacionInsomnio = (Button) findViewById(R.id.btnMeditacionInsomnio);
        imgViewLogo = (ImageView) findViewById(R.id.imgViewLogo);
        tvAppName = (TextView) findViewById(R.id.tvAppName);
        tvDoctorName = (TextView) findViewById(R.id.tvDoctorName);
        vvInicio = (VideoView) findViewById(R.id.vvInicio);
        layoutButton = (LinearLayout) findViewById(R.id.layoutButton);
        layoutLogo = (LinearLayout) findViewById(R.id.layoutLogo);
        layoutText = (LinearLayout) findViewById(R.id.layoutText);
        layoutMeditacion = (LinearLayout) findViewById(R.id.layoutMeditacion);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
        finish();
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            if(position == 0) {
                /*
                if(vvInicio.isPlaying()){
                    vvInicio.stopPlayback();
                }
                */
                layoutMeditacion.setVisibility(View.GONE);
                layoutText.setVisibility(View.GONE);
                layoutLogo.setVisibility(View.VISIBLE);

            } else if(position == 1) {
                /*
                if(!vvInicio.isPlaying()){
                    vvInicio.start();
                }
                */
                layoutLogo.setVisibility(View.GONE);
                layoutMeditacion.setVisibility(View.GONE);
                layoutText.setVisibility(View.VISIBLE);

            } else if(position == 2) {
                /*
                if(vvInicio.isPlaying()){
                    vvInicio.stopPlayback();
                }
                */
                layoutLogo.setVisibility(View.GONE);
                layoutText.setVisibility(View.GONE);
                layoutButton.setVisibility(View.VISIBLE);
                layoutMeditacion.setVisibility(View.VISIBLE);

            } else if(position == 3) {
                layoutButton.setVisibility(View.GONE);
                layoutLogo.setVisibility(View.GONE);
                layoutText.setVisibility(View.GONE);
                layoutMeditacion.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


}
