import hlt.*;

import java.util.*;

public class MyBot {

    private static List<Entity> getTwoEntityPlanets(Ship m_ship, GameMap gameMap) {
        List<Entity> twoEntityPlanets = new LinkedList<>();
        Map<Double, Entity> entities_by_distance = gameMap.nearbyEntitiesByDistance(m_ship);
        LinkedList<Double> keys = new LinkedList<>(entities_by_distance.keySet());
        Collections.sort(keys);
        Entity entity = entities_by_distance.get(keys.get(0));
        twoEntityPlanets.add(entity);
        entity = entities_by_distance.get(keys.get(1));
        twoEntityPlanets.add(entity);
        return twoEntityPlanets;
    }

    private static Ship getEnemyShip(Planet planet, GameMap gameMap){
        LinkedList<Ship> result = new LinkedList<>();
        Map<Double, Entity> entitiesByDistance = gameMap.nearbyEntitiesByDistance(planet);
        SortedSet<Double> keys = new TreeSet<Double>(entitiesByDistance.keySet());

        for (Double key: keys) {
            Entity entity = entitiesByDistance.get(key);
            if (entity instanceof Ship) {
                result.add((Ship) entity);
            }
        }

        return result.get(0);
    }


    public static void main(final String[] args) {
        final Networking networking = new Networking();
        final GameMap gameMap = networking.initialize("Tamagocchi");

        // We now have 1 full minute to analyse the initial map.
        final String initialMapIntelligence =
                "width: " + gameMap.getWidth() +
                        "; height: " + gameMap.getHeight() +
                        "; players: " + gameMap.getAllPlayers().size() +
                        "; planets: " + gameMap.getAllPlanets().size();
        Log.log(initialMapIntelligence);

        final ArrayList<Move> moveList = new ArrayList<>();
        for (; ; ) {
            moveList.clear();
            networking.updateMap(gameMap);
            for (final Ship ship : gameMap.getMyPlayer().getShips().values()) {

                if (ship.getDockingStatus() != Ship.DockingStatus.Undocked) {
                    continue;
                }

                for (final Planet planet : gameMap.getAllPlanets().values()) {
                    if (planet.isOwned()) {
                        continue;
                    }

                    if (ship.canDock(planet)) {
                        moveList.add(new DockMove(ship, planet));
                        break;
                    }

                    final ThrustMove newThrustMove = Navigation.navigateShipToDock(gameMap, ship, planet, Constants.MAX_SPEED);
                    if (newThrustMove != null) {
                        moveList.add(newThrustMove);
                    }

                    break;
                }
            }
            Networking.sendMoves(moveList);
        }
    }
}
