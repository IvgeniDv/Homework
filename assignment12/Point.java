
/**
 * Point.java represents a 2-Dimensional point in the Euclidean space.
 * Maman 12 question 1
 * @version 01.16/03/2021
 * @author Dvorkin Ivgeni
 */

public class Point{
    private double _x; // represents the horizontal value of the coordinate
    private double _y; // represents the vertical value of the coordinate
    public static final double EPSILON = 0.001; // represents a negligible difference
    
     /**
      * Constructs a new Point
      * @param x  horizontal value of the coordinate
      * @param y  vertical value of the coordinate
      */     
    public Point(double x, double y){
        _x = x;
        _y = y;
    }
    
    
    /**
     * Copy constructor Creates a new Point identical to the given point
     * @param other  point to be copied
     */
    public Point(Point other){
        this(other.getX(), other.getY());
    }
    
    
    /** 
     * Returns the x coordinate.
     * @return a double
     */     
    public double getX(){
        return _x;
    }
    
    
    /**  
     * Returns the y coordinate.
     * @return a double
     */     
    public double getY(){
        return _y;
    }
    
    
    /** 
     * This method sets the x coordinate of the point.
     * @param num  The new x coordinate
     */
    public void setX(double num){
        _x = num;
    }
    
    
     /** 
      * This method sets the y coordinate of the point.
      * @param num  The new y coordinate
      */
    public void setY(double num){
        _y = num;
    }
    
    
    /** 
     * Return true if the given point is equal to this point.
     * @param other  The point we are checking equality with
     * @return true if the point other equals this point
     */
    public boolean equals(Point other){
        return ((Math.abs(_x - other.getX()) < EPSILON)  && (Math.abs(_y - other.getY()) < EPSILON));
    }
    
    
     /** 
      * Check if this point is above a received point.
      * @param other  The point to check if this point is above
      * @return True if this point is above the other point
      */
    public boolean isAbove(Point other){
        if (Math.abs(_y - other.getY()) < EPSILON){
            return false;
        }
        else if( _y > other.getY()){
            return true;
        }
        else {
            return false;
        }
    }
    
    
    /** 
     * Check if this point is below a received point.
     * @param other  The point to check if this point is below
     * @return True if this point is below the other point
     */
    public boolean isUnder(Point other){
        return other.isAbove(this);
    }
    
    
    /** 
     * Check if this point is left of a received point.
     * @param other  The point to check if this point is left of
     * @return True if this point is left of the other point
     */
    public boolean isLeft(Point other){
        if (Math.abs(_x - other.getX()) < EPSILON){
            return false;
        }
        else if( _x < other.getX()){
            return true;
        }
        return false;
    }
    
    
    /** 
     * Check if this point is right of a received point.
     * @param other  The point to check if this point is right of
     * @return True if this point is right of the other point
     */
    public boolean isRight(Point other){
        return other.isLeft(this);
    }
    
    
    /** 
     * Check the distance between this point and a received point.
     * @param other  The point to check the distance from
     * @return The distance between this point and a received point
     */
    public double distance(Point p){
        double d; // temp var to hold the calculations of the distance
        if ( this.equals(p) ){
            d = 0;
        }
        else if ( Math.abs(_x - p.getX()) < EPSILON ){
            d = Math.pow((_y - p.getY()),2);
        }
        else if (Math.abs(_y - p.getY()) < EPSILON){
            d = Math.pow((_x - p.getX()),2);
        }
        else{
            d = Math.pow((_y - p.getY()),2) + Math.pow((_x - p.getX()),2);
        }
    
        return Math.sqrt(d);
    
    }
    
    
    /** 
     * Return number of quadrant or 0 if the point is on an axis
     * @return an int representing the quadrant number
     */
    public int quadrant(){
        Point defaultQuad = new Point(0,0);
        int quadNum = 0;
        if (this.isRight(defaultQuad) && this.isAbove(defaultQuad)){
        quadNum = 1;
        }
        else if (this.isLeft(defaultQuad) && this.isAbove(defaultQuad)) {
        quadNum = 2;
        }
        else if (this.isLeft(defaultQuad) && this.isUnder(defaultQuad)){
        quadNum = 3;
        }
        else if (this.isRight(defaultQuad) && this.isUnder(defaultQuad)){
        quadNum = 4;
        }
        
        return quadNum;
        
    }
    

    /** 
     * Returns a string representation of this Point. 
     * The format of the string should be: (x,y)
     * @return A string representation of a Point object
     */
    public String toString(){
        return "("+_x+","+_y+")";
    }


}
