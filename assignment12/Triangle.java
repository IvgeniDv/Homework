
/**
 * Triangle.java represents a triangle in the Euclidean space.
 * Maman 12 question 2
 * @version 01.21/03/2021
 * @author Dvorkin Ivgeni
 */

public class Triangle{
    
    // instance variables- represents the vertices of the triangle
    Point _point1;
    Point _point2;
    Point _point3;
    
    private Point defaultPoint1 = new Point(1,0);
    private Point defaultPoint2 = new Point(-1,0);
    private Point defaultPoint3 = new Point(0,1);
    
    public static final double EPSILON = 0.001; // represents a negligible difference
    
    
    /**
      * Default constructor
      * Constructs a new Triangle from 3 default Points:(1,0),(-1,0),(0,1)
      */     
    public Triangle(){   
        _point1 = new Point(defaultPoint1);
        _point2 = new Point(defaultPoint2);
        _point3 = new Point(defaultPoint3);
    }
    
     /**
      * Construct a new Triangle (from 3 points)
      * @param x - the first Point
      * @param y - the second Point
      * @param z - the third Point
      */ 
    public Triangle(Point x, Point y, Point z){     // sets to default if isValid is false
        if ( this.isValid(x,y,z) ){
            _point1 = new Point(x);
            _point2 = new Point(y);
            _point3 = new Point(z);
        }
        else{
            _point1 = new Point(defaultPoint1);
            _point2 = new Point(defaultPoint2);
            _point3 = new Point(defaultPoint3);
        }
    }
    
     /**
      * Construct a new Triangle (from 6 reals)
      * @param a1 - the x coordinate of the first Point
      * @param a2 - the y coordinate of the first Point
      * @param b1 - the x coordinate of the second Point
      * @param b2 - the y coordinate of the second Point
      * @param c1 - the x coordinate of the third Point
      * @param c2 - the y coordinate of the third Point
      */ 
    public Triangle(double a1, double a2, double b1, double b2, double c1, double c2){    // each two doubles represent one vertex
        Point p1 = new Point(a1,a2);
        Point p2 = new Point(b1,b2);
        Point p3 = new Point(c1,c2);
        if ( this.isValid(p1,p2,p3) ){
            _point1 = new Point(p1);
            _point2 = new Point(p2);
            _point3 = new Point(p3);
        }
        else{
            _point1 = new Point(defaultPoint1);
            _point2 = new Point(defaultPoint2);
            _point3 = new Point(defaultPoint3);
        }
    }
    
    /**
      * Copy constructor 
      * Creates a new triangle identical to the given triangle
      * @param other - the triangle to be copied
      */ 
    public Triangle(Triangle other){ 
        this(other.getPoint1(),other.getPoint2(),other.getPoint3());
    }
    
    //get methods:
    /**
      * This method returns the triangle's first point
      * @return The first point of the triangle
      */ 
    public Point getPoint1(){
        return _point1;
    }
    
    /**
      * This method returns the triangle's third point
      * @return The third point of the triangle
      */ 
    public Point getPoint2(){
        return _point2;
    }
    
    /**
      * This method returns the triangle's second point
      * @return The second point of the triangle
      */ 
    public Point getPoint3(){
        return _point3;
    }
    
    // set methods
    // for each point check if the triangle isValid. if not - no change will be done.
    /**
      * This method sets the first point of the triangle. 
      * @param p - The new first point
      */
    public void setPoint1(Point p){
        if ( this.isValid(p,this.getPoint2(),this.getPoint3()) ){
            _point1 = new Point(p);
        }
    }
    
    /**
      * This method sets the second point of the triangle.
      * @param p - The new second point
      */
    public void setPoint2(Point p){
        if ( this.isValid(this.getPoint1(),p,this.getPoint3()) ){
            _point2 = new Point(p);
        }
    }
    
    /**
      * This method sets the third point of the triangle.
      * @param p - The new third point
      */
    public void setPoint3(Point p){
        if ( this.isValid(this.getPoint1(),this.getPoint2(),p) ){
            _point3 = new Point(p);
        }
    }
    
    /**
      * This method checks if 3 points make a valid triangle. 
      * A triangle is valid if no sum of any two sides equals the third side (with precision EPSILON)
      * @param p1 - the first point
      * @param p2 - the second point
      * @param p3 - the third point
      * @return true if the triangle(p1,p2,p3) is valid
      */
    public boolean isValid(Point p1, Point p2, Point p3){
        // temp parameters for calculations of length of sides 
        double x = p1.distance(p2);
        double y = p2.distance(p3);
        double z = p3.distance(p1);
        if ( (Math.abs((x - (y + z))) < EPSILON) || (Math.abs((y - (x + z))) < EPSILON) || (Math.abs((z - (x + y))) < EPSILON) ){
             return false;
        }
        else {
            return true;
    
        }
    }
    
    /**
      * This method returns a String representation of thetTriangle. 
      * The format of the string should be: {(x1,y1),(x2,y2),(x3,y3)}
      * @return a String representation of the triangle
      */ 
    public String toString(){
       return "{" + _point1.toString() + "," + _point2.toString() + "," + _point3.toString() + "}";
    }
    
    /**
      * This method returns the triangle's perimeter. 
      * @return the triangle's perimeter
      */ 
    public double getPerimeter(){
        return ( _point1.distance(_point2) + _point2.distance(_point3) + _point3.distance(_point1) );
    }
    
    /**
      * This method returns the triangle's area (using Heron's formula). 
      * @return the triangle's area
      */ 
    public double getArea(){
        double area;
        double s = this.getPerimeter()/2;    // half the perimeter. 
        // calculating the area: 
        area = s * (s - _point1.distance(_point2)) * (s - _point2.distance(_point3)) * (s - _point3.distance(_point1));  
        return Math.sqrt(area);
    }
    
    /**
      * This method returns true if the triangle is an isosceles triangle(with precision EPSILON).
      * @return true if the triangle is an isosceles triangle
      */
    public boolean isIsosceles(){
        // temp variables to hold the sides length
        double a = _point1.distance(_point2);
        double b = _point2.distance(_point3);
        double c = _point3.distance(_point1);
        if ( (Math.abs(a - b) < EPSILON) || (Math.abs(b - c) < EPSILON) || (Math.abs(a - c) < EPSILON) ){
            return true;
        }
        return false;
    }
    
    /**
      * This method returns true if the triangle is a right-angled triangle.
      * @return true if the triangle is a right-angled triangle
      */
    public boolean isPythagorean(){
        // Pythagorean theorem : c = sqrt(a^2 +b^2)
        // temp variables to hold the sides length
        double a = _point1.distance(_point2);
        double b = _point2.distance(_point3);
        double c = _point3.distance(_point1);
        if ( Math.abs(c - Math.sqrt(Math.pow(a,2) + Math.pow(b,2))) < EPSILON ){
            return true;
        }
        else if ( Math.abs(a - Math.sqrt(Math.pow(b,2) + Math.pow(c,2))) < EPSILON ){
            return true;
        }
        else if ( Math.abs(b - Math.sqrt(Math.pow(a,2) + Math.pow(c,2))) < EPSILON){
            return true;
        }
        return false;
    }
    
    /**
      * This method returns true if the triangle is in a given circle.
      * @param x - the x value of the point which is the center of the circle
      * @param y - the y value of the point which is the center of the circle
      * @param r - the radius of the circle
      * @return true if the triangle is in a given circle.
      */
    public boolean isContainedInCircle(double x, double y, double r){
        Point circCenter = new Point(x,y);
        // temp vars to compare distances with the radius
        double a = _point1.distance(circCenter);
        double b = _point2.distance(circCenter);
        double c = _point3.distance(circCenter);
        
        if ( Math.abs(a - r) < EPSILON ){
            if ( Math.abs(b - r) < EPSILON ){
                if ( Math.abs(c - r) < EPSILON ){
                    return true;
                }
                else if (c < r){
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (b < r){
                if ( Math.abs(c - r) < EPSILON ){
                    return true;
                }
                else if (c < r){
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else if (a < r){
            if ( Math.abs(b - r) < EPSILON ){
                if (Math.abs(c - r) < EPSILON){
                    return true;
                }
                else if (c < r){
                    return true;
                }
                else {
                    return false;
                }
            }
            else if (b < r){
                if ( Math.abs(c - r) < EPSILON ){
                    return true;
                }
                else if (c < r){
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    /**
      * This method returns the lowest vertex of the triangle. 
      * @return the lowest vertex
      */
    public Point lowestPoint(){
        if (_point1.isUnder(_point2)){
            if (_point1.isUnder(_point3)){
                return _point1;
            }
            else if(_point1.isAbove(_point3)){
                return _point3;
            }
            else if(_point1.isLeft(_point3)){
                return _point1;
            }
            else {
                return _point3;
            }
        }
        else if(_point1.isAbove(_point2)){
            if (_point2.isUnder(_point3)){
                return _point2;
            }
            else if(_point2.isAbove(_point3)){
                return _point3;
            }
            else if (_point2.isLeft(_point3)){
                return _point2;
            }
            else {
                return _point3;
            }
        }
        else if(_point1.isUnder(_point3)){
            if (_point1.isLeft(_point2)){
                return _point1;
            }
            else {
                return _point2;
            }
        }
        else {
            return _point3;
        }
        
    }
    
    /**
      * This method returns the highest vertex of the triangle.
      * @return the highest Vertex
      */ 
    public Point highestPoint(){
        Point lowestP = new Point(0,0);
        lowestP = this.lowestPoint();
        
        if (_point1.equals(lowestP)){
            if (_point2.isAbove(_point3)){
                return _point2;
            }
            else if (_point2.isUnder(_point3)){
                return _point3;
            }
            else if (_point2.isLeft(_point3)){
                return _point2;
            }
            else {
                return _point3;
            }
        }
        else if (_point2.equals(lowestP)){
            if (_point1.isAbove(_point3)){
                return _point1;
            }
            else if (_point1.isUnder(_point3)){
                return _point3;
            }
            else if (_point1.isLeft(_point3)){
                return _point1;
            }
            else {
                return _point3;
            }
        }
        else {
            if (_point2.isAbove(_point1)){
                return _point2;
            }
            else if (_point2.isUnder(_point1)){
                return _point1;
            }
            else if (_point2.isLeft(_point1)){
                return _point2;
            }
            else {
            return _point1;
            }
        }
    }
    
    /**
      * This method returns true if the triangle is entirely in one quadrant.
      * @return true if the triangle is entirely in one quadrant
      */
    public boolean isLocated(){
        // vars to hold the points quadrant number
        int p1 = _point1.quadrant();
        int p2 = _point2.quadrant();
        int p3 = _point3.quadrant();
        
        if ( (p1 == p2) && (p1 == p3) && (p1 != 0) ){
            return true;
        }
        return false;
    }
    
    /**
      * Check if this triangle is completely above a received triangle.
      * @param other- the triangle to check if this triangle is above
      * @return true if this triangle is above the other triangle
      */ 
    public boolean isAbove(Triangle other){
        // temp points to hold lowest and highest points.
        Point myPoint = new Point(0,0);  // will hold the lowest point of the triangle we check to be higher
        Point otherPoint = new Point(0,0);  //will hold the highest point of the triangle given as param.
        myPoint = this.lowestPoint();
        otherPoint = other.highestPoint();
        if (myPoint.isAbove(otherPoint)){
            return true;
        }
        return false;
    }
    
    /**
      * Check if this triangle is completely below a received triangle.
      * @param other - the triangle to check if this triangle is below
      * @return true if this triangle is below the other triangle
      */
    public boolean isUnder(Triangle other){
        return other.isAbove(this);
    }
    
    /**
      * Check if the given triangle is equal to this triangle. 
      * @param other - the triangle we are checking equality with
      * @return true if the triangle other is equal to this triangle
      */ 
    public boolean equals(Triangle other){
        if ( ( _point1.equals(other.getPoint1()) ) && ( _point2.equals(other.getPoint2()) ) && ( _point3.equals(other.getPoint3()) ) ){
            return true;
        }
        return false;
    }
    
    /**
      * Check if the given triangle is congruent to this triangle.
      * @param other- the triangle we are checking congruency with
      * @return true if the triangle other is congruent to this triangle
      */
    public boolean isCongruent(Triangle other){
        // vars that hold the sides of the "other" triangle
        double a = other.getPoint1().distance(other.getPoint2());
        double b = other.getPoint2().distance(other.getPoint3()); 
        double c = other.getPoint3().distance(other.getPoint1()); 
        // vars that hold the sides of the current triangle
        double x = _point1.distance(_point2);
        double y = _point2.distance(_point3);
        double z = _point3.distance(_point1);
        
        if ( Math.abs(a - x) < EPSILON ){
            if ( Math.abs(b - y) < EPSILON ){
                if ( Math.abs(c - z) < EPSILON ){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if ( Math.abs(b - z) < EPSILON ){
                if ( Math.abs(c - y) < EPSILON ){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else if ( Math.abs(a - y) < EPSILON ){
            if ( Math.abs(b - x) < EPSILON ){
                if ( Math.abs(c - z) < EPSILON ){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if ( Math.abs(b - z) < EPSILON ){
                if ( Math.abs(c - x) < EPSILON ){
                    return true;
                }
                else{
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            if ( Math.abs(a - z) < EPSILON ){
                if ( Math.abs(b - x) < EPSILON){
                    if ( Math.abs(c - y)  <EPSILON ){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else if ( Math.abs(b - y) < EPSILON ){
                    if ( Math.abs(c - z) < EPSILON ){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
    }

    
}
