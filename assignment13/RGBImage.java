
/**
 * Maman 13: RGBImage.java - 
 * Represents a colored image using a Two-dimensional array which consists of the RGBColor objects.
 * RGBColor object represents a pixel using 3 integers describing a color in the (R,G,B) format,
 * thus resulting in a display of a colored image as a grid of pixels.
 *
 * @version  06/04/2021  
 * @author Ivgeni Dvorkin
 */
public class RGBImage{
    
    // Two-dimensional array that represents a grid of pixels
    RGBColor[][] _image;
    
    

    /**
     * Constrcuts an all black image using a specified size.
     * @param rows - number of rows.
     * @param cols - number of columns .
     */
    public RGBImage(int rows, int cols){
        _image = new RGBColor[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                _image[i][j] = new RGBColor(); 
            }
        }
    }
    
    /**
     * Constructs a new Image using a given pixel grid.
     * @param pixels - pixel grid of type RGBColor.
     */  
    public RGBImage(RGBColor[][] pixels){
        _image = new RGBColor[pixels.length][pixels[0].length];
        for (int i = 0; i < pixels.length; i++){
            for (int j = 0; j < pixels[i].length; j++){
                _image[i][j] = new RGBColor(pixels[i][j]);
            }
        }  
    }
    
    /**
     * Copy constructor.
     * creates a new image object identical to the given image.
     * @param other - RGBImage object.
     */  
    public RGBImage(RGBImage other){
       _image = new RGBColor[other.getArray().length][other.getArray()[0].length];
       for (int i = 0; i < other.getArray().length; i++){
            for (int j = 0; j < other.getArray()[0].length; j++){
                _image[i][j] = new RGBColor(other.getArray()[i][j]);
            }
         }
    }
    
    /**
     * Returns a copy of the pixels array.
     * @return - a copy of the Two-dimensional RGBColor type array.
     */  
    public RGBColor[][] toRGBColorArray(){
        RGBColor[][] copiedArray = new RGBColor[this.getHeight()][this.getWidth()];
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                copiedArray[i][j] = new RGBColor(this.getArray()[i][j]);
            }
        }
        return copiedArray;
    }
    
    /**
     * Returns the height of the grid in pixels.
     * @return - the height of the image.
     */  
    public int getHeight(){
        return this.getArray().length;
    }
    
    /**
     * Returns the width of the grid in pixels.
     * @return - the width of the image.
     */  
    public int getWidth(){
        return this.getArray()[0].length;
    }
    
    /**
     * Gets a specific pixel in an image using specified coordinates.
     * if coordinates out of bounds, a black pixel will be returned.
     * @param row - the row number in which the pixel is located.
     * @param col - the column number in which the pixel is located.
     * @return - a specific pixel in an image.
     */ 
    public RGBColor getPixel(int row, int col){
        RGBColor defaultColor = new RGBColor();
        if (( this.getHeight() < row) || (this.getWidth() < col || (row < 0) || (col < 0)) ){
            return defaultColor;
        }
        else {
            defaultColor = new RGBColor(this.getArray()[row][col]);
            return defaultColor;
        }
    }
    
    /**
     * Sets a specific pixel in an image using specified coordinates and a color.
     * if coordinates out of bounds, nothing will be done.
     * @param row - the row number in which the pixel is located.
     * @param col - the column number in which the pixel is located.
     * @param pixel - the specified color to set the pixel to.
     */ 
    public void setPixel(int row, int col, RGBColor pixel){
        if ( (this.getHeight() > row) && (this.getWidth() > col) && (col >= 0)&& (row >= 0) ){
            this.getArray()[row][col] = new RGBColor(pixel);
        }
    }
    
    /**
     * Checks equality of two images.
     * @param other -  the given image compared to the original image.
     * @return - true if images are the same.
     */ 
    public boolean equals(RGBImage other){
       if ( (this.getHeight() == other.getHeight()) && (this.getWidth() == other.getWidth()) ){
           for (int i = 0; i < this.getHeight(); i++){
               for (int j = 0; j < this.getWidth(); j++){
                   if ( !(this.getArray()[i][j].equals(other.getArray()[i][j])) ){
                       return false;
                   }
               } 
           }    
           return true;
       }
       return false; 
    }
    
    /**
     * Mirror the image in the horizontal direction (left-right).
     */ 
    public void flipHorizontal(){
        RGBColor[][] tempArray = new RGBColor[this.getHeight()][this.getWidth()];
        for (int i = this.getHeight(); i > 0; i--){
            for (int j = this.getWidth(); j > 0; j--){
                tempArray[i - 1][this.getWidth() - j] = new RGBColor(this.getArray()[i - 1][j - 1]);
            }
        }
        this.copyArray(tempArray);
    }
    
    /**
     * Mirror the image in the vertical direction (up-down).
     */ 
    public void flipVertical(){
        RGBColor[][] tempArray = new RGBColor[this.getHeight()][this.getWidth()];
        for (int i = this.getHeight(); i > 0; i--){
            for (int j = this.getWidth(); j > 0; j--){
                tempArray[this.getHeight() - i][j - 1] = new RGBColor(this.getArray()[i - 1][j - 1]);
            }
        }
        this.copyArray(tempArray);
    }
    
    /**
     * Inverts the colors of the image.
     */
    public void invertColors(){
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                this.getArray()[i][j].invert(); 
            }
        }
    }
    
    /**
     * Rotates the image 90 degrees clockwise.
     */
    public void rotateClockwise(){
        RGBColor[][] tempArray = new RGBColor[this.getWidth()][this.getHeight()];    
        for (int i = this.getWidth(); i > 0; i--){
            for(int j = this.getHeight(); j > 0; j--){
                tempArray[this.getWidth() - i][j-1] = new RGBColor(this.getArray()[this.getHeight() - j][this.getWidth() - i]);
            }
        }
        this.copyArray(tempArray);
    }
    
    /**
     * Rotates the image 90 degrees counter-clockwise.
     */
    public void rotateCounterClockwise(){
        RGBColor[][] tempArray = new RGBColor[this.getWidth()][this.getHeight()];    
        for (int i = this.getWidth(); i > 0; i--){
            for(int j = this.getHeight(); j > 0; j--){
                tempArray[i - 1][this.getHeight() - j] = new RGBColor(this.getArray()[this.getHeight() - j][this.getWidth() - i]);
            }
        }
        this.copyArray(tempArray);
    }
    
    /**
     * Offsets the image left or right by a given parameter.
     * @param offset - an integer that indicates by how much to offset the picture. 
     */
    public void shiftCol(int offset){
        if ( !(Math.abs(offset) > this.getWidth()) ){  //checks if the offset is larger then the width of the image
            RGBImage blackImage = new RGBImage(this.getHeight(),this.getWidth());
            if ( offset == this.getWidth()){
                this.copyArray(blackImage.getArray());
            }
            else if (offset > 0){
                this.offsetRight(offset);
            }
            else if (offset < 0){
                this.offsetLeft(offset);
            }
        }
    }
    
    /**
     * Offsets the image up or down by a given number.
     * @param offset - an integer that indicates by how much to offset the picture. 
     */
    public void shiftRow(int offset){
        if ( !(Math.abs(offset) > this.getHeight()) ){ //checks if the offset is larger then the height of the image
            RGBImage blackImage = new RGBImage(this.getHeight(),this.getWidth());
            if ( offset == this.getHeight()){
                this.copyArray(blackImage.getArray());
            }
            else if (offset > 0){
                this.offsetDown(offset);
            }
            else if (offset < 0){
                this.offsetUp(offset);
            }
        }
    }
    
    /**
     * Converts the image to grayscale colors.
     * @return - Two-dimensional array of type double that represents the grayscale of the pixels.
     */
    public double[][] toGrayscaleArray(){
        double[][] grayArray = new double[this.getHeight()][this.getWidth()];
        for (int i = 0; i< this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                grayArray[i][j] = this.toRGBColorArray()[i][j].convertToGrayscale();
            }
        }
        return grayArray;
    }
    
    /**
     * Prints a representation of the pixel grid.
     * @return - a string representation of the grid using the (R,G,B) format for the pixel.
     */
    public String toString(){
        String imageAsNumbers = "";
        for (int i = 0; i < this.getHeight(); i++){
            for(int j = 0; j < this.getWidth(); j++) {
                imageAsNumbers += this.getArray()[i][j].toString();
                if (j < (this.getWidth() - 1)){
                    imageAsNumbers += " ";
                }
            }
            if (i < (this.getHeight() - 1)){
            imageAsNumbers += "\n";
            }
        }
        return imageAsNumbers;
    }
    
    
    /**
     * Overwrites the current RGBColor array with the given array,
     * @param other - Two-dimensional array of type RGBColor - the pixel grid.
     */
    private void copyArray(RGBColor[][] other){
        this._image = new RGBColor[other.length][other[0].length];
        for (int i = 0; i < other.length; i++){
            for (int j = 0; j < other[0].length; j++){
                this._image[i][j] = new RGBColor(other[i][j]);   
            }
        }
    }
    
    /**
     * Returns the RGBColor array of the RGBImage object (the array of the pixels).
     * @return the pixel grid of the image.
     */
    private RGBColor[][] getArray(){
        return _image; 
    }
    
    /**
     * Offsets the image in the array to the left by a given number.
     * @param picture - the array of the pixels.
     * @param offset - an integer that indicates by how much to offset the picture. 
     */
    private void offsetLeft(int offset){
        RGBColor[][] tempArray = new RGBColor[this.getHeight()][this.getWidth()];  
        // Sets a default black array
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                tempArray[i][j] = new RGBColor();
            }
        }
        /* 
         * The following loops run on the original array and copy color to the black array.
         * this method gets called when offset < 0.
         * the first loop is in charge of the width index ,copying columns.
         * the second loop is in charge of the height index - running from top to bottom of the column
         * the copying proccess stops if the colored column will be copied to outside of the black array bounds. 
        */
        for (int i = this.getWidth(); i + offset > 0; i--){
            for (int j = 0; j < this.getHeight(); j++){
                tempArray[j][i - 1 + offset] = new RGBColor(this.getArray()[j][i - 1]);
            }
        }
        this.copyArray(tempArray);
    }
    
    /**
     * Offsets the image in the array to the right by a given number.
     * @param picture - the array of the pixels.
     * @param offset - an integer that indicates by how much to offset the picture. 
     */
    private void offsetRight(int offset){
        // Sets a default black array
        RGBColor[][] tempArray = new RGBColor[this.getHeight()][this.getWidth()];
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                tempArray[i][j] = new RGBColor();
            }
        }
        /* 
         * The following loops run on the original array and copy color to the black array.
         * this method gets called when offset > 0.
         * the first loop is in charge of the width index ,copying columns.
         * the second loop is in charge of the height index - running from top to bottom of the column
         * the copying proccess stops if the colored column will be copied to outside of the black array bounds. 
        */
        for (int i = 0; i + offset < this.getWidth(); i++){
            for (int j = 0; j < this.getHeight(); j++){
                tempArray[j][i + offset] = new RGBColor(this.getArray()[j][i]);
            }
        }
        this.copyArray(tempArray);
    }
    
    /**
     * Offsets the image in the array up by a given number.
     * @param picture - the array of the pixels.
     * @param offset - an integer that indicates by how much to offset the picture. 
     */
    private void offsetUp(int offset){
        // Sets a default black array
        RGBColor[][] tempArray = new RGBColor[this.getHeight()][this.getWidth()];
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                tempArray[i][j] = new RGBColor();
            }
        }
        /* 
         * The following loops run on the original array and copy color to the black array.
         * this method gets called when offset < 0.
         * the first loop is in charge of the heigth index ,copying rows.
         * the second loop is in charge of the width index - running from start to finish of the row.
         * the copying proccess stops if the colored row will be copied to outside of the black array bounds. 
        */
        for (int i = this.getHeight(); i + offset > 0; i--){
            for (int j = 0; j < this.getWidth(); j++){
                tempArray[i - 1 + offset][j] = new RGBColor(this.getArray()[i - 1][j]);
            }
        }
        this.copyArray(tempArray);
    }
    
    /**
     * Offsets the image in the array down by a given number.
     * @param picture - the array of the pixels.
     * @param offset - an integer that indicates by how much to offset the picture.
     */
    private void offsetDown(int offset){
        // Sets a default black array
        RGBColor[][] tempArray = new RGBColor[this.getHeight()][this.getWidth()];
        for (int i = 0; i < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                tempArray[i][j] = new RGBColor();
            }
        }
        /* 
         * The following loops run on the original array and copy color to the black array.
         * this method gets called when offset > 0.
         * the first loop is in charge of the heigth index ,copying rows.
         * the second loop is in charge of the width index - running from start to finish of the row.
         * the copying proccess stops if the colored row will be copied to outside of the black array bounds. 
        */
        for (int i = 0; i + offset < this.getHeight(); i++){
            for (int j = 0; j < this.getWidth(); j++){
                tempArray[i + offset][j] = new RGBColor(this.getArray()[i][j]);
            }
        }
        this.copyArray(tempArray);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
