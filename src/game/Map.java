package game;

import java.util.ArrayList;
import java.util.Random;

import game.base.Coordinate;
import game.base.Database;
import game.base.Direction;
import object.Animal;
import object.Bird;
import object.Eagle;
import object.Fish;
import object.MangoTree;
import object.Material;
import object.Metal;
import object.PalmTree;
import object.PineTree;
import object.Plastic;
import object.Scrap;
import object.Stone;
import object.Tree;

public class Map {
	private static ArrayList<Cell> area;
	private static ArrayList<Cell> plantableArea;
	private static ArrayList<Cell> moveableForEagleArea;
	private static ArrayList<Animal> availableFish;
	private static ArrayList<Cell> randomFishCell;
	private static ArrayList<Animal> availableBird;
	private static ArrayList<Cell> randomBirdCell;
	private static ArrayList<Material> availableMetal;
	private static ArrayList<Material> availableScrap;
	private static ArrayList<Material> availablePlastic;
	private static ArrayList<Material> availableStone;
	private static ArrayList<Cell> middleIslandArea;
	private static ArrayList<Cell> repairableArea;
	private static ArrayList<Tree> trees;
	private static Eagle eagle;

	public static Eagle getEagle() {
		return eagle;
	}

	public Map() {
		Database database = new Database();
		area = database.getArea();
		plantableArea = database.getPlantableArea();
		moveableForEagleArea = database.getMoveableForEagleArea();
		middleIslandArea = database.getMiddleIslandArea();
		repairableArea = database.getRepairableArea();
		randomFishCell = database.getRandomFishCell();
		randomBirdCell = database.getRandomBirdCell();
		availableFish = database.getAvailableFish();
		availableBird = database.getAvailableBird();
		availableMetal = database.getAvailableMetal();
		availableScrap = database.getAvailableScrape();
		availablePlastic = database.getAvailablePlastic();
		availableStone = database.getAvailableStone();
		trees = database.getTrees();
		eagle = new Eagle();
	}

	public static ArrayList<Animal> getAvailableFish() {
		return availableFish;
	}

	public static void setAvailableFish(ArrayList<Animal> availableFish) {
		Map.availableFish = availableFish;
	}

	public static ArrayList<Animal> getAvailableBird() {
		return availableBird;
	}

	public static void setAvailableBird(ArrayList<Animal> availableBird) {
		Map.availableBird = availableBird;
	}

	public static ArrayList<Cell> getArea() {
		return area;
	}

	public static ArrayList<Cell> getPlantableArea() {
		return plantableArea;
	}

	public static ArrayList<Cell> getMoveableForEagleArea() {
		return moveableForEagleArea;
	}

	public static ArrayList<Cell> getRandomFishCell() {
		return randomFishCell;
	}

	public static ArrayList<Cell> getRandomBirdCell() {
		return randomBirdCell;
	}

	public static ArrayList<Material> getAvailableMetal() {
		return availableMetal;
	}

	public static ArrayList<Material> getAvailableScrape() {
		return availableScrap;
	}

	public static ArrayList<Material> getAvailableStone() {
		return availableStone;
	}

	public static ArrayList<Material> getAvailablePlastic() {
		return availablePlastic;
	}

	public static ArrayList<Cell> getMiddleIslandArea() {
		return middleIslandArea;
	}

	public static ArrayList<Cell> getRepairableArea() {
		return repairableArea;
	}

	public static ArrayList<Tree> getTrees() {
		return trees;
	}

	public static void removeTree(Tree tree) {
		trees.remove(tree);
	}

	public static Cell getCellFromCoordinate(Coordinate other) {
		for (Cell x : area) {
			if (x.getCoCell().isSamePosition(other))
				return x;
		}
		return null;
	}

	public static Object getObjectFromCoordinate(Coordinate coordinate) {
		Cell cell = getCellFromCoordinate(coordinate);
		if (cell.getStatus())
			return null;
		for (Animal x : Map.getAvailableFish()) {
			if (x.getPosition().equals(cell)) {
				Fish fish = (Fish) x;
				return fish;
			}
		}
		for (Animal x : Map.getAvailableBird()) {
			if (x.getPosition().equals(cell)) {
				Bird bird = (Bird) x;
				return bird;
			}
		}
		for (Material x : Map.getAvailableMetal()) {
			if (x.getPosition().equals(cell)) {
				Metal metal = (Metal) x;
				return metal;
			}
		}
		for (Material x : Map.getAvailablePlastic()) {
			if (x.getPosition().equals(cell)) {
				Plastic plastic = (Plastic) x;
				return plastic;
			}
		}
		for (Material x : Map.getAvailableScrape()) {
			if (x.getPosition().equals(cell)) {
				Scrap scrape = (Scrap) x;
				return scrape;
			}
		}
		for (Material x : Map.getAvailableStone()) {
			if (x.getPosition().equals(cell)) {
				Stone stone = (Stone) x;
				return stone;
			}
		}
		for (Tree x : Map.getTrees()) {
			if (x.getPosition().equals(cell)) {
				if (x instanceof PalmTree) {
					return (PalmTree) x;
				}
				if (x instanceof PineTree) {
					return (PineTree) x;
				}
				if (x instanceof MangoTree) {
					return (MangoTree) x;
				}
			}
		}
		if (Map.getEagle().getPosition().equals(cell)) {
			return Map.getEagle();
		}
		return null;

	}

	public static Cell getCellFromDirection(Direction direction, Coordinate coordinate) {

		Coordinate c = coordinate;
		if (direction == Direction.UP)
			c.setY(c.getY() - 1);
		if (direction == Direction.DOWN)
			c.setY(c.getY() + 1);
		if (direction == Direction.RIGHT)
			c.setX(c.getX() + 1);
		if (direction == Direction.LEFT)
			c.setX(c.getX() - 1);

		return getCellFromCoordinate(c);

	}

	public static int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

	public static boolean readyForRandomAnimal(ArrayList<Animal> animals) {
		for (int i = 0; i < animals.size(); i++) {
			if (animals.get(i).isAlive()) {
				return false;
			}
		}
		return true;
	}

	public static boolean readyForRandomMaterial(ArrayList<Material> material) {
		for (int i = 0; i < material.size(); i++) {
			if (material.get(i).isPresent()) {
				return false;
			}
		}
		return true;
	}

	public static void refreshFish(int random) {
		if (!readyForRandomAnimal(availableFish))
			return;
		Random rand = new Random();
		ArrayList<Cell> givenList = new ArrayList<Cell>();
		for (Cell x : randomFishCell) {
			if (x.getStatus() && !x.isClosed() && x.isSea()) {
				givenList.add(x);
			}
		}

		for (int i = 0; i < random; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			Cell randomCell = givenList.get(randomIndex);
			if (i < availableFish.size()) {
				((Fish) availableFish.get(i)).refresh(randomCell);
			} else {
				Fish e = new Fish(randomCell);
				availableFish.add(e);
			}
		}
	}

	public static void refreshBird(int random) {
		if (!readyForRandomAnimal(availableBird))
			return;
		Random rand = new Random();
		ArrayList<Cell> givenList = new ArrayList<Cell>();
		for (Cell x : randomBirdCell) {
			if (x.getStatus() && !x.isClosed()) {
				givenList.add(x);
			}
		}

		for (int i = 0; i < random; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			Cell randomCell = givenList.get(randomIndex);
			if (i < availableBird.size()) {
				((Bird) availableBird.get(i)).refresh(randomCell);
			} else {
				Bird e = new Bird(randomCell);
				availableBird.add(e);
			}
		}

	}

	public static void refreshScrape(int random) {
		if (!readyForRandomMaterial(availableScrap))
			return;
		Random rand = new Random();
		ArrayList<Cell> givenList = new ArrayList<Cell>();
		for (Cell x : area) {
			if (x.getStatus() && !x.isClosed() && !Map.getPlantableArea().contains(x)) {
				givenList.add(x);
			}
		}

		for (int i = 0; i < random; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			Cell randomCell = givenList.get(randomIndex);
			if (i < availableScrap.size()) {
				((Scrap) availableScrap.get(i)).refresh(randomCell);
			} else {
				Scrap e = new Scrap(randomCell);
				availableScrap.add(e);
			}
		}
	}

	public static void refreshPlastic(int random) {
		if (!readyForRandomMaterial(availablePlastic))
			return;
		Random rand = new Random();
		ArrayList<Cell> givenList = new ArrayList<Cell>();

		for (Cell x : area) {
			if (x.getStatus() && !x.isClosed() && !Map.getPlantableArea().contains(x)) {
				givenList.add(x);
			}
		}

		for (int i = 0; i < random; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			Cell randomCell = givenList.get(randomIndex);
			if (i < availablePlastic.size()) {
				((Plastic) availablePlastic.get(i)).refresh(randomCell);
			} else {
				Plastic e = new Plastic(randomCell);
				availablePlastic.add(e);
			}
		}
	}

	public static void refreshStone(int random) {
		if (!readyForRandomMaterial(availableStone))
			return;
		Random rand = new Random();
		ArrayList<Cell> givenList = new ArrayList<Cell>();
		for (Cell x : area) {
			if (x.getStatus() && !x.isClosed() && !Map.getPlantableArea().contains(x)) {
				givenList.add(x);
			}
		}

		for (int i = 0; i < random; i++) {
			int randomIndex = rand.nextInt(givenList.size());
			Cell randomCell = givenList.get(randomIndex);
			if (i < availableStone.size()) {
				((Stone) availableStone.get(i)).refresh(randomCell);
			} else {
				Stone e = new Stone(randomCell);
				availableStone.add(e);
			}
		}

	}

	public static void setEagle(Cell next) {
		eagle = new Eagle(next);
	}

}
