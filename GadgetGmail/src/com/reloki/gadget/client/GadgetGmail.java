package com.reloki.gadget.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

@com.google.gwt.gadgets.client.Gadget.ModulePrefs(title = "Simple Gadget", author = "nycodes9", author_email = "nycodes9@gmail.com")
@com.google.gwt.gadgets.client.Gadget.UseLongManifestName(false)
@com.google.gwt.gadgets.client.Gadget.AllowHtmlQuirksMode(false)
public class GadgetGmail extends Gadget<UserPreferences> {

	@Override
	protected void init(UserPreferences preferences) {
		Button simpleButton = new Button("Login with Github");
		simpleButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("Hello World");
			}
		});
		RootPanel.get().add(simpleButton);
		
	}
}
