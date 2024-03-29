package object;

import game.Player;

public class Shipwreck {
	private boolean wood;
	private boolean hinge;
	private boolean nail;
	private boolean rope;
	private boolean canvas;
	private boolean steering;
	private boolean engine;
	private boolean petrol;
	private boolean titanium;
	private boolean receiver;
	private boolean antenna;

	public Shipwreck() {
		wood = false;
		hinge = false;
		nail = false;
		rope = false;
		canvas = false;
		steering = false;
		engine = false;
		petrol = false;
		titanium = false;
		receiver = false;
		antenna = false;
	}

	public boolean repair(String object, Player player) {
		int n;
		switch (object) {
		case "Wood":
			n = player.getWood();
			if (n >= 50 && !wood) {
				wood = true;
				player.setWood(n - 50);
				break;
			}
			return false;
		case "Hinge":
			n = player.getHinge();
			if (n >= 10 && !hinge) {
				hinge = true;
				player.setHinge(n - 10);
				break;
			}
			return false;
		case "Nail":
			n = player.getNail();
			if (n >= 15 && !nail) {
				nail = true;
				player.setNail(n - 15);
				break;
			}
			return false;
		case "Rope":
			n = player.getRope();
			if (n >= 20 && !rope) {
				rope = true;
				player.setRope(n - 20);
				break;
			}
			return false;
		case "Canvas":
			if (player.hasCanvas() && !canvas) {
				canvas = true;
				player.setCanvas(false);
				break;
			}
			return false;
		case "Steering":
			if (player.hasSteering() && !steering) {
				steering = true;
				player.setSteering(false);
				break;
			}
			return false;
		case "Engine":
			if (player.hasEngine() && !engine) {
				engine = true;
				player.setEngine(false);
				break;
			}
			return false;
		case "Petrol":
			if (player.hasPetrol() && !petrol) {
				petrol = true;
				player.setPetrol(false);
				break;
			}
			return false;
		case "Titanium":
			n = player.getTitanium();
			if (player.getTitanium() >= 1 && !titanium) {
				titanium = true;
				player.setTitanium(n - 1);
				break;
			}
			return false;
		case "Receiver":
			if (player.hasReceiver() && !receiver) {
				receiver = true;
				player.setReceiver(false);
				break;
			}
			return false;
		case "Antenna":
			if (player.hasAnthena() && !antenna) {
				antenna = true;
				player.setAnthena(false);
				break;
			}
			return false;
		}
		return true;
	}

	public boolean checkWinCondition() {
		if (wood && hinge && nail && rope && canvas && steering && engine && petrol && titanium && receiver
				&& antenna) {
			return true;
		}
		return false;
	}

	public boolean haswood() {
		return wood;
	}

	public boolean hasHinge() {
		return hinge;
	}

	public boolean hasNail() {
		return nail;
	}

	public boolean hasRope() {
		return rope;
	}

	public boolean hasCanvas() {
		return canvas;
	}

	public boolean hasSteering() {
		return steering;
	}

	public boolean hasEngine() {
		return engine;
	}

	public boolean hasPetrol() {
		return petrol;
	}

	public boolean hasTitanium() {
		return titanium;
	}

	public boolean hasReceiver() {
		return receiver;
	}

	public boolean hasAntenna() {
		return antenna;
	}
}
