package edu.chl.loc.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.chl.loc.LocMain;

public class LocMainDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Legend of Chalmers";
        config.width = 1024;
        config.height = 576;
		new LwjglApplication(new LocMain(), config);
	}
}
