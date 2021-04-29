package IntoTheGalaxy;

import java.util.*;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;

public class GameDashboard extends Dashboard {
	ArrayList<TextObject> textList;
	
	public GameDashboard(float x, float y, float width, float height) {
		super(x, y, width, height);
		textList = new ArrayList<TextObject>();
		
		TextObject text1 = new TextObject("hoi", 100);
		text1.setForeColor(0, 0, 255, 1);
		addGameObject(text1, 0, 0);
	}
}
