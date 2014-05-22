package com.talentica.avin.socialLogin2;

import org.brickred.socialauth.Profile;

public interface ProfileEventReceiver {
	
	public void onProfileEventSuccess(Profile mProfile);
	public void onProfileEventFailure();
}
