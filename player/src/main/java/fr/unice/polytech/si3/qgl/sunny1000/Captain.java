package fr.unice.polytech.si3.qgl.sunny1000;



import fr.unice.polytech.si3.qgl.sunny1000.game.InitGame;
import fr.unice.polytech.si3.qgl.sunny1000.game.NextRound;
import fr.unice.polytech.si3.qgl.sunny1000.game.Objective;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Sailor;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.Ship;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Oar;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Rudder;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Sail;
import fr.unice.polytech.si3.qgl.sunny1000.game.Ship.entities.Vigie;
import fr.unice.polytech.si3.qgl.sunny1000.game.actions.*;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.Goal;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal.Checkpoint;
import fr.unice.polytech.si3.qgl.sunny1000.game.goal.regattaGoal.RegattaGoal;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.CalculatePath;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Position;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Circle;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Point;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Polygone;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Shapes.Rectangle;
import fr.unice.polytech.si3.qgl.sunny1000.game.properties.Util;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Entities;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.entities.Recif;
import fr.unice.polytech.si3.qgl.sunny1000.game.visible.leverage.Wind;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

import static java.lang.Math.*;

public class Captain {
    private Ship ship;
    final private Goal GOAL;
    final private List<Sailor> SAILORS;
    private List<Entities> externalEntities;
    private Wind wind;
    final private List<Objective> objectiveShip;
    private int iCheckPoint;
    private final double MIN_ANGLE;
    private double rotationAngle;
    private double instantRotation;
    private int activeOar;
    private int activeSail;
    private double rudderAngle;
    boolean orderToLower;
    private List<String> log;


    public int getiCheckPoint() {
        return iCheckPoint;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public int getActiveOar() {
        return activeOar;
    }

    public Ship getShip() {
        return ship;
    }

    public Goal getGOAL() {
        return GOAL;
    }

    public List<Sailor> getSAILORS() {
        return SAILORS;
    }

    public List<Entities> getExternalEntities() {
        return externalEntities;
    }

    public Wind getWind() {
        return wind;
    }

    public List<Objective> getObjectiveShip() {
        return objectiveShip;
    }

    public double getMIN_ANGLE() {
        return MIN_ANGLE;
    }

    public double getInstantRotation() {
        return Math.toRadians(instantRotation);
    }

    public void setInstantRotation(double instantRotation) {
        this.instantRotation = instantRotation;
    }

    public List<String> getLog() {
        return log;
    }

    public Captain(InitGame iGame) {
        this.GOAL =iGame.getGoal();
        this.SAILORS = Arrays.asList(iGame.getSailors());
        this.ship=iGame.getShip();
        objectiveShip =new ArrayList<>();
        iCheckPoint =0;
        MIN_ANGLE= toDegrees( PI/ship.getOars().size());
        this.log=new ArrayList<>();
    }

    public Captain(InitGame iGame,List<String> log) {
        this.GOAL =iGame.getGoal();
        this.SAILORS = Arrays.asList(iGame.getSailors());
        this.ship=iGame.getShip();
        objectiveShip =new ArrayList<>();
        iCheckPoint =0;
        MIN_ANGLE= toDegrees( PI/ship.getOars().size());
        this.log=log;
    }

    public void nextRound(NextRound nRound){
        this.ship=nRound.getShip();
        this.externalEntities= Arrays.asList(nRound.getVisibleEntities());
        this.wind=nRound.getWind();
    }


    public int getActiveSail() {
        return activeSail;
    }


    public Entities detectEntity(double shipOrientation){
        double distanceShipReef = 0;
        int i=0;
        for (Entities e : externalEntities) {
            if(e instanceof Recif){
                //distance between reef and the ship
                    CalculatePath calculatePath = new CalculatePath(ship.getPosition().toPoint(), Math.toRadians(shipOrientation), 5000, new Wind(0,0),0, 0, ship, 5000);
                    List<Point> points = calculatePath.generateNextPos();
                    for (Point p:
                         points) {
                        if(e.getShape() instanceof Circle) {
                            if (Util.belongsToCirle(e.getPosition().toPoint(), ((Circle) e.getShape()).getRadius(), p)) {
                                return e;
                            }
                        }else if(e.getShape() instanceof Polygone){
                            ArrayList<Point> pts=new ArrayList<>();
                            for (Point pt:
                                    ((Polygone) e.getShape()).getVertices()) {
                                Point k=new Point(pt.getX()+e.getPosition().getX(),pt.getY()+e.getPosition().getY());
                                pts.add(k);
                            }
                            /*if(Util.belongsToPolygon(pts.toArray(new Point[]{}),p)){
                                return e;
                            }*/
                        }else if(e.getShape() instanceof Rectangle){
                            if(Util.belongsToRectangle((Rectangle) e.getShape(),e.getPosition(),p)){
                                return e;
                            }
                        }
                    }
            }
        }
        return null;
    }

    private void avoidReef(double shipAngle){
        double shipOrientation = Math.toDegrees(shipAngle);
        double angleRight=shipOrientation;
        double angleLeft=shipOrientation;
        Entities e = detectEntity(shipOrientation);

        boolean a = (e!=null);
        double chosenAngle = 0;
        if(a){
            log.add("Recif at "+e.getPosition().getX()+ "," +e.getPosition().getY());
            double distanceShipReef=Util.calculateDistance(e.getPosition().toPoint(),ship.getPosition().toPoint());
            Point pe=e.getPosition().toPoint();
            while(true) {
                angleRight+=2;
                angleLeft-=2;

                Entities left=detectEntity(angleLeft);
                Entities right=detectEntity(angleRight);

                if(left==null ) {
                    chosenAngle=angleLeft-3;
                    break;
                }else if(right==null) {
                    chosenAngle=angleRight+3;
                    break;
                }
            }
            log.add("Order to turn "+chosenAngle);
            double shipCheck=Util.calculateDistance(ship.getPosition().toPoint(),((RegattaGoal) GOAL).getCheckpoints()[iCheckPoint].getPosition().toPoint());

            if(shipCheck>distanceShipReef) {
                double h = Util.calculateRotationAngle(Math.toDegrees(ship.getPosition().getOrientation()), chosenAngle);
                double distanceToCheckpoint;
                distanceToCheckpoint = distanceShipReef / Math.cos(Math.toRadians(h));

                CalculatePath calculatePath = new CalculatePath(ship.getPosition().toPoint(), Math.toRadians(chosenAngle), 20, new Wind(0, 0), 0, 0, ship, distanceToCheckpoint);
                List<Point> points = calculatePath.generateNextPos();
                Position p = new Position(points.get(points.size() - 1).getX(), points.get(points.size() - 1).getY(), 0);
                createVirtualCheckPoint(p, 100);
            }

        }else{

        }


    }

    /**
     *This methode return the actions to execute
     */
    public List<Actions> choseActions(){
        log.add("before  "+Math.toDegrees(ship.getPosition().getOrientation()));
        List<Actions> actions = new ArrayList<>();
        orderToLower=false;
        deleteOursCheckPoint();
        double checkAngle=Util.angle(((RegattaGoal) GOAL).getCheckpoints()[iCheckPoint].toPoint(),ship.getPosition().toPoint());
        avoidReef(Math.toRadians(checkAngle));
        //validate the checkpoint if it's necessary
        double speed=Util.calculateOarSpeed(activeOar,ship.getOars().size(),ship.getPosition().getOrientation())+Util.calculateWindSpeed(wind,ship.getPosition().getOrientation(),activeSail,1);
        double distanceShipCheck=Util.calculateDistance(((RegattaGoal) GOAL).getCheckpoints()[iCheckPoint].toPoint(),ship.getPosition().toPoint());
        if(speed>distanceShipCheck){
            orderToLower=true;
        }

        if(validateCheckPoint()) {
            iCheckPoint++;
            if (iCheckPoint >= ((RegattaGoal) getGOAL()).getCheckpoints().length)
                return null;
        }

        calculateRotationToCheckPoint();
        //a copy of the sailors
        List<Sailor> sailorsCopy=new ArrayList<>(this.SAILORS);
        //clear objectives to create new ones
        objectiveShip.clear();
        //associate sailor to the rudder
        associateSailorToRudder(sailorsCopy);

        associateSailorToSail(sailorsCopy);
        //associate sailors to the oar
        associateSailorToOar(sailorsCopy);
        //associateSailorToWatch(sailorsCopy);
        associateSailorToWatch(sailorsCopy);

        actions.addAll(moveSailorToEntity());
        //add sail actions
        instantRotation=0;
        activeOar=0;
        activeSail=0;

        actions.addAll(controlSail());
        actions.addAll(makeSailorsOar());

        log.add("after  "+(Math.toDegrees(ship.getPosition().getOrientation())+rotationAngle));
        //list of actions that the sailors should do
        return actions;
    }

    void deleteOursCheckPoint(){
        Checkpoint checkpoints[]=((RegattaGoal)getGOAL()).getCheckpoints();
        for (int i = iCheckPoint; i < checkpoints.length; i++)
            if(checkpoints[i].isOurs())
                iCheckPoint++;
    }

     void createVirtualCheckPoint(Position position,double radius){
        Checkpoint virtualCheckpoint=new Checkpoint(position,new Circle(radius));
        virtualCheckpoint.setOursTrue();
        Checkpoint checkpoints[]=((RegattaGoal)getGOAL()).getCheckpoints();
        ArrayList<Checkpoint> vCheckpoints = new ArrayList<>();
        for (int i = 0; i < iCheckPoint; i++) {
            vCheckpoints.add(checkpoints[i]);
        }
        vCheckpoints.add(virtualCheckpoint);
        for (int i = iCheckPoint; i < checkpoints.length; i++) {
            if(!checkpoints[i].isOurs())
                vCheckpoints.add(checkpoints[i]);
        }
        Checkpoint checks[]=vCheckpoints.toArray(new Checkpoint[]{});
        ((RegattaGoal)getGOAL()).setCheckpoints(checks);
    }

    /**
     * this methode is used to validate a checkpoint
     */
    private boolean validateCheckPoint(){
        if(Util.checkPointCollision(ship,((RegattaGoal) GOAL).getCheckpoints()[iCheckPoint].getPosition().toPoint(),((Circle)(((RegattaGoal) GOAL).getCheckpoints()[iCheckPoint].getShape())).getRadius())){
            System.out.println("checked");
            return true;
        }
        return false;
    }

    /**
     * (nombre de voile ouverte / nombre de voile) x vitesse du vent x cosinus(angle entre la direction du vent et la direction du bateau)
     */
    private List<Actions> controlSail(){
        double angleShipWind=Util.calculateRotationAngle(Math.toDegrees(ship.getPosition().getOrientation()),Math.toDegrees(wind.getOrientation()));
        List<Actions> actions=new ArrayList<>();
        if(Math.abs(angleShipWind)<90 && !orderToLower){
            if(sailSailor()!=null && activeSail!=1) {
                actions.add(new LiftSail(this.sailSailor().getId()));
                activeSail=1;
            }
        }else{
            if(sailSailor()!=null) {
                actions.add(new LowerSail(this.sailSailor().getId()));
                activeSail=0;
            }
        }
        return actions;
    }

    /**
     * This methode associate a Sailor to a specific Rudder
     */
    public void associateSailorToRudder(List<Sailor> sailors){
        Rudder rudder=ship.getRudder();
        if(rudder!=null) {
            Sailor s = rudder.findNearestSailor(sailors);
            if(s!=null) {
                objectiveShip.add(new Objective(s, rudder));
                sailors.remove(s);
            }
        }
    }

    public void associateSailorToWatch(List<Sailor> sailors){
        Vigie watch=ship.getWatch();
        if(watch!=null) {
            Sailor s = watch.findNearestSailor(sailors);
            if(s!=null) {
                objectiveShip.add(new Objective(s, watch));
                sailors.remove(s);
            }
        }
    }

    /**
     * This methode associate a Sailor to the sail
     */
    public void associateSailorToSail(List<Sailor> sailors){
        Sail sail=ship.getSail();
        if(sail!=null) {
            Sailor s = sail.findNearestSailor(sailors);
            objectiveShip.add(new Objective(s, sail));
            sailors.remove(s);
        }
    }

    /**
     *This methode associate a Sailor to a specific Oar
     */
    public void associateSailorToOar(List<Sailor> sailors){
        List<Oar> oars=new ArrayList<>(ship.getLeftOars(sailors.size()/2));
        oars.addAll(ship.getRightOars(sailors.size()/2));
        associateOarsToNearestSailors(oars,sailors);
    }

    private void addObjective(Oar oar,Sailor sailor){
        if(oar!=null && sailor!=null) {
            objectiveShip.add(new Objective(sailor, oar));
        }
    }

    private void associateOarsToNearestSailors(List<Oar> Oars,List<Sailor> sailors){
        for (Oar oar:
             Oars) {
            Sailor s=oar.findNearestSailor(sailors);
            addObjective(oar,s);
            sailors.remove(s);
        }
    }

    public List<Actions> moveSailorToEntity(){
        List<Actions> actions=new ArrayList<>();
        for (Objective o : objectiveShip) {
            if (o.getSailor().getPoint().equals(o.getEntity().getPoint())) {
                o.setDone(true);
            }
            if (!o.isDone()) {
                Point point = Move.validateMove(o.getEntity().getX() - o.getSailor().getX(), o.getEntity().getY() - o.getSailor().getY());
                int xDistance = (int) point.getX();
                int yDistance = (int) point.getY();


                int xSailor = o.getSailor().getX();
                int ySailor = o.getSailor().getY();
                o.getSailor().setX(xSailor + xDistance);
                o.getSailor().setY(ySailor + yDistance);

                actions.add(new Move(o.getSailor().getId(), xDistance, yDistance));
                if (o.getSailor().getPoint().equals(o.getEntity().getPoint())) {
                    o.setDone(true);
                }
            }
        }
        return actions;
    }

    private void calculateRotationToCheckPoint(){
        double checkAngle=Util.angle(((RegattaGoal) GOAL).getCheckpoints()[iCheckPoint].toPoint(),ship.getPosition().toPoint());
        double shipAngle= toDegrees(ship.getPosition().getOrientation());
        //calculate the rotation angle that the ship need to get into the checkpoint
        rotationAngle =Util.calculateRotationAngle(shipAngle, checkAngle);
    }

    private List<Actions> makeSailorsOar(){
        //number Of sailors that the ship needs to turn
        int nSailor= Util.calculateNumberOfSailors(rotationAngle,MIN_ANGLE);
        //this list will be fulfilled with the actions needed
        List<Actions> a=new ArrayList<>();
        if(rotationAngle >45){
            actionsToTurnRight(nSailor,a);
        }else if (rotationAngle <-45){
            actionsToTurnLeft(nSailor,a);
        }else{
            actionsToGoForWard(a, rotationAngle,0,0);
        }
        return a;
    }

    private void actionsToTurnLeft(int nSailor,List<Actions> a){
        List<Sailor> sailorsLeft = findSailorsOarLeft();
        List<Sailor> sailorsRight = findSailorsOarRight();
        int sailorsToUse=min(nSailor,sailorsLeft.size());
        activeOar+=sailorsToUse;
        instantRotation=sailorsToUse*MIN_ANGLE;
        instantRotation*=-1;
        //sailors that we will use to rotate
        makeSailorsOar(sailorsLeft,sailorsToUse,a);
        //sailors that we will use to go forward
        double angleLeft;
        if(rotationAngle>0){
            angleLeft= rotationAngle-sailorsToUse*MIN_ANGLE;
        }else{
            angleLeft= rotationAngle+sailorsToUse*MIN_ANGLE;
        }
        actionsToGoForWard(a,angleLeft,sailorsToUse,0);

    }

    private void actionsToTurnRight(int nSailor,List<Actions> a){
        List<Sailor> sailorsLeft = findSailorsOarLeft();
        List<Sailor> sailorsRight = findSailorsOarRight();
        int sailorsToUse=min(nSailor,sailorsRight.size());
        activeOar+=sailorsToUse;
        instantRotation=sailorsToUse*MIN_ANGLE;
        instantRotation*=1;
        //sailors that we will use to rotate
        makeSailorsOar(sailorsRight,sailorsToUse,a);

        double angleLeft;
        if(rotationAngle>0){
            angleLeft= rotationAngle-sailorsToUse*MIN_ANGLE;
        }else{
            angleLeft= rotationAngle+sailorsToUse*MIN_ANGLE;
        }
        actionsToGoForWard(a,angleLeft,0,sailorsToUse);
    }


    private void makeSailorsOar(List<Sailor> sailors,double sailorsToUse,List<Actions> a){
        for (int i=0; i<sailorsToUse;i++)
            a.add(new OarAction(sailors.get(i).getId()));
    }
    private void actionsToGoForWard(List<Actions> a,double angle,int leftStart,int rightStart){
        if(angle!=0 && pilotingSailor()!=null) {
            Turn t=new Turn(pilotingSailor().getId(), toRadians(angle));
            rudderAngle=t.getRotation();
            a.add(new Turn(pilotingSailor().getId(), toRadians(angle)));
            instantRotation+=Math.toDegrees(t.getRotation());
        }

        List<Sailor> sailorsLeft = findSailorsOarLeft();
        List<Sailor> sailorsRight = findSailorsOarRight();
        while(leftStart<sailorsLeft.size() && rightStart<sailorsRight.size()){
            a.add(new OarAction(sailorsLeft.get(leftStart).getId()));
            a.add(new OarAction(sailorsRight.get(rightStart).getId()));
            leftStart++;
            rightStart++;
            activeOar+=2;
        }
    }

    public List<Sailor> findSailorsOarLeft(){
        List<Sailor> ss=new ArrayList<>();
        for (int i=objectiveShip.size()-1;i>=0;i--){
            if(objectiveShip.get(i).getEntity().getPoint().getY()==0 && objectiveShip.get(i).isDone() && objectiveShip.get(i).getEntity() instanceof Oar){
                ss.add(objectiveShip.get(i).getSailor());
            }
        }
        return ss;
    }

    public List<Sailor> findSailorsOarRight(){
        List<Sailor> ss=new ArrayList<>();
        for (int i=objectiveShip.size()-1;i>=0;i--){
            if(objectiveShip.get(i).getEntity().getPoint().getY()==ship.getY_RIGHT() && objectiveShip.get(i).isDone() && objectiveShip.get(i).getEntity() instanceof Oar){
                ss.add(objectiveShip.get(i).getSailor());
            }
        }
        return ss;
    }


    /**
     *this method returns the sailor who holds the rudder
     */
    public Sailor pilotingSailor(){
        for (Objective o:
             objectiveShip) {
            if(o.getEntity() instanceof Rudder && o.isDone()){
                return o.getSailor();
            }
        }
        return null;
    }

    /**
     * this methode returns the sailors who moved to the sail
     */
    public Sailor sailSailor(){
        for (Objective o:
                objectiveShip) {
            if(o.getEntity() instanceof Sail && o.isDone()){
                return o.getSailor();
            }
        }
        return null;
    }



}
