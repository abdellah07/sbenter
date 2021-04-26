package fr.unice.polytech.si3.qgl.sunny1000.game.actions;

public class Turn extends Actions{
    private double rotation;
    /**
     * Constructor.
     *
     * @param sailorId the id of the sailor who will execute the action
     * @param rotation the angle of turn
     */
    public Turn(int sailorId,double rotation) {
        super(sailorId, "TURN");
        if(Math.abs(rotation)>=(double)(Math.PI/4)){
            if (rotation >= 0)
                this.rotation=(double)(Math.PI/4);
            else
                this.rotation=(double)(Math.PI/4)*(-1);
        }else {
            this.rotation=rotation;
        }
    }

    public double getRotation() {
        return rotation;
    }


    @Override
    public String toString() {
        return super.toString() +
                ",rotation=" + rotation +
                '}';
    }
}
