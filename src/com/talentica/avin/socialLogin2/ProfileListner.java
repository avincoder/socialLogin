package com.talentica.avin.socialLogin2;

import java.util.ArrayList;
import java.util.List;

import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.util.Log;

public class ProfileListner implements SocialAuthListener<Profile>{

	Profile mProfile;
	List<ProfileEventReceiver> eventReceivers;
	
	public ProfileListner() {
		Log.d("Listner", "Created the profile");
		eventReceivers= new ArrayList<ProfileEventReceiver>();
	}
	
	@Override
	public void onExecute(String provider, Profile t) {
		mProfile= t;
		dispatchEvent();
	}

	@Override
	public void onError(SocialAuthError e) {
		Log.d("ERROR", "Could Not get the profile");
		
	}
	
	public void addEventListner(ProfileEventReceiver receiver){
		eventReceivers.add(receiver);
	}
	
	public void removeEventListner(ProfileEventReceiver receiver){
		eventReceivers.remove(receiver);
	}
	
	public void dispatchEvent(){
		for(ProfileEventReceiver p: eventReceivers){
			p.onProfileEventSuccess(mProfile);
		}
	}
	
	public Profile getProfile(){
		return mProfile;
	}

}
