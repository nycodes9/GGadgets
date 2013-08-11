package com.reloki.gadget.client;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.io.GadgetsIo;
import com.google.gwt.gadgets.client.io.IoProvider;
import com.google.gwt.gadgets.client.io.ResponseReceivedHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

@com.google.gwt.gadgets.client.Gadget.ModulePrefs(title = "Simple Gadget", author = "nycodes9", author_email = "nycodes9@gmail.com")
@com.google.gwt.gadgets.client.Gadget.UseLongManifestName(false)
@com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode(false)
public class GadgetGmail extends Gadget<UserPreferences> {

	private static final String GITHUB_AUTH_URL = "https://github.com/login/oauth/authorize";
	private static final String GITHUB_CLIENT_ID = "8830365b0bebb11b76a3";

	
	@Override
	protected void init(UserPreferences preferences) {
		Button simpleButton = new Button("Click Me");
		simpleButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Hello Gmail");
			}
		});
		RootPanel.get().add(simpleButton);
	
//		addDailymotionAuth();
	}
	
	private void addDailymotionAuth() {
	    Button button = new Button("Login with Github");
	    button.addClickHandler(new ClickHandler() {
	      @Override
	      public void onClick(ClickEvent event) {
	        final AuthRequest req = new AuthRequest(GITHUB_AUTH_URL, GITHUB_CLIENT_ID);
	        Auth.get().login(req, new Callback<String, Throwable>() {
	          @Override
	          public void onSuccess(String token) {
	            Window.alert("Got an OAuth token:\n" + token + "\n"
	                + "Token expires in " + Auth.get().expiresIn(req) + " ms\n");
	          }

	          @Override
	          public void onFailure(Throwable caught) {
	            Window.alert("Error:\n" + caught.getMessage());
	          }
	        });
	      }
	    });
	    RootPanel.get().add(button);
	  }
}
