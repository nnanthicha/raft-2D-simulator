package object;

import java.util.Timer;
import java.util.TimerTask;

import game.Cell;
import game.Map;
import game.Player;
import game.base.Removeable;
import object.base.ApplicationType;
import object.base.MaterialType;

public class Metal extends Material implements Removeable {

	public Metal(Cell position) {
		super(position);
		
	}

	@Override
	public void beRemoved() {
		if (Player.getCurrentShovel() == null)
			return;
		if (Player.getCurrentPosition().isNextTo(position) && present
				&& (Player.getCurrentShovel().getMaterial() == MaterialType.METAL)) {
			Player.setMetal(Player.getMetal()+1);
			present = false;
			Player.decreaseLifetime(ApplicationType.SHOVEL);
			Player.decreaseHP();
			position.setStatus(true);
		}
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 15; // (second) can change

			@Override
			public void run() {
				// TODO Auto-generated method stub
				i--;
				if (i < 0) {
					timer.cancel();
					refresh();
				}
			}
		}, 0, 1000);
		// TODO Auto-generated method stub
	}

	public void refresh() {
		if (!present) { 
			present = true;
			position.setStatus(false);
		}
	}
	
}