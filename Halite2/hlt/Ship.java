package hlt;

import java.util.LinkedList;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Ship extends Entity {

    public enum DockingStatus { Undocked, Docking, Docked, Undocking }

    private final DockingStatus dockingStatus;
    private final int dockedPlanet;
    private final int dockingProgress;
    private final int weaponCooldown;

    public Ship(final int owner, final int id, final double xPos, final double yPos,
                final int health, final DockingStatus dockingStatus, final int dockedPlanet,
                final int dockingProgress, final int weaponCooldown) {

        super(owner, id, xPos, yPos, health, Constants.SHIP_RADIUS);

        this.dockingStatus = dockingStatus;
        this.dockedPlanet = dockedPlanet;
        this.dockingProgress = dockingProgress;
        this.weaponCooldown = weaponCooldown;
    }

    public Planet getLargestFreePlanet(GameMap gameMap) {
        LinkedList<Planet> result = new LinkedList<>();

        Map<Double, Entity> entitiesByDistance = gameMap.nearbyEntitiesByDistance(this);
        SortedSet<Double> keys = new TreeSet<>(entitiesByDistance.keySet());
        for (Double key : keys) {
            Entity entity = entitiesByDistance.get(key);
            if (entity instanceof Planet) {
                result.add((Planet) entity);
            }
        }

        Double size = 0d;
        Planet returnPlanet = null;
        for(Planet planet : result)
            if (planet.getRadius() >= size && !planet.isOwned()) {
                returnPlanet = planet;
                size = planet.getRadius();
            }

        return returnPlanet;
    }

    public Planet getLargestEnemyPlanet(){
        
    }

    public int getWeaponCooldown() {
        return weaponCooldown;
    }

    public DockingStatus getDockingStatus() {
        return dockingStatus;
    }

    public int getDockingProgress() {
        return dockingProgress;
    }

    public int getDockedPlanet() {
        return dockedPlanet;
    }

    public boolean canDock(final Planet planet) {
        return getDistanceTo(planet) <= Constants.SHIP_RADIUS + Constants.DOCK_RADIUS + planet.getRadius();
    }

    @Override
    public String toString() {
        return "Ship[" +
                super.toString() +
                ", dockingStatus=" + dockingStatus +
                ", dockedPlanet=" + dockedPlanet +
                ", dockingProgress=" + dockingProgress +
                ", weaponCooldown=" + weaponCooldown +
                "]";
    }
}
