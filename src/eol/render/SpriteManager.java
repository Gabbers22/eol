package eol.render;

import javax.swing.*;
import java.util.HashMap;

public class SpriteManager {
	HashMap<String,ImageIcon> Sprites = new HashMap<>();
	
	public void add(String name,String nameimage) {
		Sprites.put(name,new ImageIcon(nameimage));
	}
	  public ImageIcon getImageIcon(String name) {
		return  Sprites.get(name);
	}
	
}