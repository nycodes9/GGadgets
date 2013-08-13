package com.reloki.gadget.client;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.io.AuthorizationType;
import com.google.gwt.gadgets.client.io.GadgetsIo;
import com.google.gwt.gadgets.client.io.IoProvider;
import com.google.gwt.gadgets.client.io.MethodType;
import com.google.gwt.gadgets.client.io.OAuthUseTokenOptions;
import com.google.gwt.gadgets.client.io.RequestOptions;
import com.google.gwt.gadgets.client.io.Response;
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
				Window.alert("getModuleBaseURL : " + GWT.getModuleBaseURL() 
						+ " getHostPageBaseURL : " + GWT.getHostPageBaseURL() 
						+ " isScript : " + GWT.isScript());
			}
		});
		RootPanel.get().add(simpleButton);
	
//		addDailymotionAuth();
		addGithubAuthGadget();
	}
	
	private void addGithubAuthGadget() {
	    Button button = new Button("Login Gadget Github");
	    button.addClickHandler(new ClickHandler() {
	      @Override
	      public void onClick(ClickEvent event) {
	    	  GadgetsIo io = IoProvider.get();
	    	  
	    	  RequestOptions ro = RequestOptions.newInstance();
	    	  ro.setAuthorizationType(AuthorizationType.OAUTH);
	    	  ro.setContentType(com.google.gwt.gadgets.client.io.ContentType.JSON);
	    	  ro.setOAuthServiceName("github");
	    	  ro.setOAuthUseToken(OAuthUseTokenOptions.ALWAYS);
	    	  ro.setMethodType(MethodType.GET);
	    	  
	    	  io.makeRequest("https://api.github.com/user", respReceiveHandler , ro);
	      }
	    });
	    RootPanel.get().add(button);
	  }

	final ResponseReceivedHandler<Object> respReceiveHandler = new ResponseReceivedHandler<Object>() {
		public void onResponseReceived(ResponseReceivedEvent<Object> event) {
			Response<Object> response = event.getResponse();
			if (response.getStatusCode() == 200) {
				Window.alert("Response Received !!");
			} else {
				Window.alert("Response failed w/ " + response.getOauthErrorText());
				Window.alert("Response failed w/ " + response.getOauthApprovalUrl());
			}
		}
	};
	
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
