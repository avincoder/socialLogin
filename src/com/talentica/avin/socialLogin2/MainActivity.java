package com.talentica.avin.socialLogin2;

import java.util.zip.Inflater;

import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.loopj.android.image.SmartImageView;
import com.loopj.android.image.WebImage;
import com.talentica.avin.socialLogin2.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements DialogListener, OnClickListener, SocialAuthListener<Integer>, ProfileEventReceiver {

	SocialAuthAdapter adpater;
	Profile mProfile;
	ProfileListner mProfileListner;
	LinearLayout parentLayout;
	SmartImageView image;
	TextView userName;
	TextView country;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        initializeLayoutVariables();
        initializeSlidingMenu();
        initializeSocialAuth();
      
        
        Log.d("","container got");
        
        
       
        	ImageButton fb_Button= (ImageButton)(findViewById(R.id.fb_button));
            if(fb_Button==null){
            	Log.d("", "fb_button   NOT got");
            }
            else{
            	Log.d("","fb_button got");
            	fb_Button.setOnClickListener(this);
            }
    	
        
//        ViewGroup container= ((ViewGroup)findViewById(R.id.container_fragment));
//        if(container==null){
//        	Log.d("", "container NOT got");
//        }
//        else{
//        	
//        }
        
        
       
     
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

	@Override
	public void onBack() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onComplete(Bundle arg0) {
		Log.d("", "facebook login complete");
		adpater.getUserProfileAsync(mProfileListner);
		LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout vg = (LinearLayout) inflater.inflate(R.layout.profile_fragment, null);
        image= (SmartImageView)vg.findViewById(R.id.imageView1);
        userName= (TextView) vg.findViewById(R.id.name_textview);
        country= (TextView) vg.findViewById(R.id.country_textview);
        parentLayout.addView(vg);
        
        
		
		
		
	}


	@Override
	public void onError(SocialAuthError arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onClick(View v) {
		adpater.authorize(this, Provider.FACEBOOK);
		
	}


	@Override
	public void onExecute(String provider, Integer t) {
		// TODO Auto-generated method stub
		
	}
	
	private void initializeSlidingMenu(){
		SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.sliding_menu);
	}
	
	private void initializeSocialAuth(){
		adpater= new SocialAuthAdapter(this);
        mProfileListner= new ProfileListner();
        mProfileListner.addEventListner(this);
	}
	
	private void initializeLayoutVariables(){
		parentLayout= (LinearLayout) findViewById(R.id.parent_container);
	}


	@Override
	public void onProfileEventSuccess(Profile mProfile) {
		Log.d("@@@@@@@", "Social Login Success+++\n"+mProfile);
		Log.d("Smar image view url", mProfile.getProfileImageURL());
		image.setImageUrl(mProfile.getProfileImageURL());
		userName.setText(mProfile.getFullName());
		country.setText(mProfile.getCountry());
	}

	@Override
	public void onProfileEventFailure() {
		Log.d("@@@@@@@@", "Social Login Failure");
	}

}
