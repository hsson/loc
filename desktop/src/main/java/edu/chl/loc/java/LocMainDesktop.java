package edu.chl.loc.java;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.chl.loc.LocMain;

public class LocMainDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Legend of Chalmers";
        config.width = 1024;
        config.height = 576;
        config.addIcon("icons/icon-256.png", Files.FileType.Internal);
        config.addIcon("icons/icon-64.png", Files.FileType.Internal);
        config.addIcon("icons/icon-32.png", Files.FileType.Internal);
		new LwjglApplication(new LocMain(), config);
	}
}
