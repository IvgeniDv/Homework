
/**
 * Ex14 
 *
 * @author Ivgeni Dvorkin
 * @version 20/05/2021
 */


public class Ex14
{
    /*
     * ********************************************************
     * Question 1 - start
     * ********************************************************
     */
    
    /**
     * MaximalDrop finds the maximal drop between two points - a summit and a valley.
     * MaximalDrop receives an array of integers that represent the hight of the summit/valley.
     * @param drop - the longest drop.
     * @param highestSummit - the highest current summit.
     * @param lowestValley - the lowest current valley.
     * @return the highest possible drop from a summit to the nearest and lowest valley.
     */
    
    /*
     * The efficiency of this method is O(n).
     * we have 3 basic steps while declaring our variables.
     * then we have a "for" loop which runs on the entire array once.
     * inside the "for" loop we have 2 "if's". 
     * The first "if" consists of 3 steps in total. 
     * The second "if" consists of 2 steps and another "if" which also consists of 2 steps in total.
     * Worst case scenario well use the last "if" statment which will be in total 4 steps.
     * In conclusion we have 3+(n*4) steps thus making the efficiency O(n).
     */

    public static int maximalDrop(int[] a){
        int highestSummit = a[0];
        int lowestValley = a[0];
        int drop = 0;
        for (int i = 1; i < a.length; i++){
            if (a[i] > highestSummit){
                highestSummit = a[i];
                lowestValley = a[i];
            }
            if (a[i] < lowestValley){
                lowestValley = a[i];
                if ( (highestSummit - lowestValley) > drop){
                        drop = highestSummit - lowestValley;
                }
            }
        }
        return drop;
    }
    /*
     * ********************************************************
     * Question 1 - end
     * ********************************************************
     */
    
    /*
     * ********************************************************
     * Question 2 - start
     * ********************************************************
     */
    
    /**
     * isSink finds in a two dimensional array if there is an index of a row that consists only of 0's,
     * and if on the same index, there is a column that consists only of 1's, minus the [k][k] index which is obviously 0,
     * @param sinkIndex - holds the index indicator that we suspect that might be a sink.
     * @param sinkIndicator - a boolean array which represents an indication of a sink in the possible index.
     * @param NO_SINK - a final parameter which will be returned if no sink is found.
     * @return the index of the sink or -1 if no sink is found.
     */
    
    /*
     * The efficiency of this method is O(n).
     * we have 3 basic steps of defining parameters.
     * we have 4 "for" loops/
     * the first loop has 1+3n steps.
     * the second loop has 2+27n steps.
     * the third loop has 1+4n steps.
     * the fourth loop has 1+6n steps.
     * in total we have 9+40n steps. not great. not terrible
     */
    
    public static int isSink(int[][] mat){
        boolean[] sinkIndicator = new boolean[mat.length];
        final int  NO_SINK = -1;
        int sinkIndex = -1;
        
        for (int i = 0; i < mat.length; i++){  // fills the boolean array with "true" values. 
            sinkIndicator[i] = true;    
        }
        for (int i = 0, j = mat.length -1; i < mat.length; i++, j--){ 
            // if the given array length is odd
            if ( i == j ){    
                if (mat[i][i] == 0){
                    if((mat[0][j] == 1) && (mat[mat.length-1][j] == 1)){
                        sinkIndicator[0] = false;  
                        sinkIndicator[mat.length-1] = false; 
                    }
                    if ( (mat[i][0] == 0) && (mat[j][mat.length-1] == 0) ){
                        sinkIndicator[0] = false;  
                        sinkIndicator[mat.length-1] = false;    
                    }
                }
                else {
                    sinkIndicator[i] = false;
                }
            }
            // this if statment is checking the diagonal line from top left to bottom right. if its not a 0 it cant be a sink.
            if (mat[i][i] == 0){  
                if( (mat[i][j] != 0) || (mat[j][i] != 1) ){
                    sinkIndicator[i] = false;    
                }
                else {
                    sinkIndicator[j] = false;
                } 
            }
            else {
                sinkIndicator[i] = false;
            }
             //all this if statments are used to check the edges of the array. and by that it eliminates option of sink
            if (i > 0 && i < mat.length -1){  
                if(mat[i][0] == 0){
                    sinkIndicator[0] = false;    
                }
                else {
                    sinkIndicator[i] = false;
                }
                if (mat[0][i] == 0){
                    sinkIndicator[i] = false;    
                }
                else {
                    sinkIndicator[0] = false; 
                }
                if (mat[mat.length-1][i] == 0 ){
                    sinkIndicator[i] = false; 
                }
                else {
                    sinkIndicator[mat.length-1] = false;        
                }
                if(mat[i][mat.length-1] == 0 ){
                    sinkIndicator[mat.length-1] = false;     
                }
                else {
                    sinkIndicator[i] = false; 
                }
            }
        }
        //checks the sinkIndicator array if we have a "true" value. if we do, we found a possible sink.
        for (int i = 0; i < mat.length; i++){
            if (sinkIndicator[i]){
                sinkIndex = i;
            }
        }
        // if we have a possible sink from the privious loop, the following loop confirms it.
        for (int i = 0; i < mat.length; i++){
            if (sinkIndex > -1){
                if (i != sinkIndex){
                    if (mat[i][sinkIndex] != 1 || mat[sinkIndex][i] !=0 ){
                        return NO_SINK;  
                    }
                }
            }
        }    
        return sinkIndex;
    }
    /*
     * ********************************************************
     * Question 2 - end
     * ********************************************************
     */
    
    /*
     * ********************************************************
     * Question 3 - start
     * ********************************************************
     */
    
    /**
     * size finds a continuous "stain" in a two dimensional array.
     * the method receives a two dimensional boolean array and an index.
     * @param stainSize - represents the overall stain size.
     * @param stainArr - holds a copy of the original array.
     * @param tempArr - holds a copy of the stain given by the index.
     * @param tempCArr - used to keep tack of the rout chosen to search for the entire stain. 
     * mainly used to make sure not to use the same rout again.
     * @return  - 0 if the index doesnt point at a stain. otherwise the method returns the stain total size.
     */
    
        public static int size(boolean[][] mat, int x, int y){
        int stainSize= 0;
        if (!validate(mat,x,y)){return stainSize;}
        boolean[][] stainArr = new boolean[mat.length][mat[0].length];
        boolean[][] tempArr = new boolean[mat.length][mat[0].length];
        boolean[][] tempCArr = new boolean[mat.length][mat[0].length];
        
        stainArr= copyArray(mat,stainArr,0,0);
        tempArr = copyStain(x,y,x,y,stainArr,tempArr,tempCArr);
        /* 
         * if you want to print the stain use this loop
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat.length; j++){
                System.out.print(tempArr[i][j] + " ");
            }
            System.out.println();
        }
        */
        stainSize= countStain(tempArr,0,0,stainSize);
        return stainSize;
    }
    
    /*
     * The "validate" method is used to check if the given index is in bounds of the array 
     * and if the index containes a valid stain.
     */
    private static boolean validate(boolean[][] mat,int x,int y){
        if ((x>=0) && (y>=0) && (x<mat.length) && (y<mat[0].length)){
            if (mat[x][y]){
                return true;
            }
        }
        return false;
    }
    
    /*
     * The "copyArray" method is used to copy the original array (which is forbidden for modification),
     * to another array which will be used for our search of the stain.
     */
    private static boolean[][] copyArray(boolean[][] mat, boolean[][] stainArr, int x, int y){
        if (x<mat.length){
            if(y<mat[0].length){
                stainArr[x][y] = mat[x][y];
                return copyArray(mat,stainArr,x,y+1);
            }
            return copyArray(mat,stainArr,x+1,0);
        }
        return stainArr;
    }
    
    /*
     * The "countStain" method is used to count of how much cells the stain consists of.
     */
    private static int countStain(boolean[][] tempArr,int x, int y, int stainSize){
        if (x<tempArr.length){
            if(y<tempArr[0].length){
                if(tempArr[x][y]){
                    stainSize= stainSize+1;
                    return countStain(tempArr,x,y+1,stainSize);
                }
                return countStain(tempArr,x,y+1,stainSize);
            }
            return countStain(tempArr, x+1, 0, stainSize);
        }
        return stainSize;
    }
    
    /*
     * the "isEdge" method is used to check is an index is the edge of a stain.
     */
    private static boolean isEdge(boolean[][] stainArr, boolean[][] tempCArr,int x, int y){
        if ( (!validate(stainArr,x-1,y) || tempCArr[x-1][y]) && (!validate(stainArr,x+1,y) || tempCArr[x+1][y]) && (!validate(stainArr,x,y+1) || tempCArr[x][y+1]) && (!validate(stainArr,x,y-1) || tempCArr[x][y-1]) && (!validate(stainArr,x+1,y+1) || tempCArr[x+1][y+1]) && (!validate(stainArr,x+1,y-1) || tempCArr[x+1][y-1]) && (!validate(stainArr,x-1,y-1) || tempCArr[x-1][y-1]) && (!validate(stainArr,x-1,y+1) || tempCArr[x-1][y+1])){
            return true;
        }
        return false;
    }
    
    /*
     * the "copyStain" method is used to copy only the selected stain to a new array, where in turn it will be counted for its size.  
     */
    private static boolean[][] copyStain(int x, int y,int j,int k, boolean[][] stainArr, boolean[][] tempArr, boolean[][]tempCArr){
        if (validate(stainArr,j,k)){
            tempCArr[x][y]=true; //mark that this index has been visited
            
            if (validate(stainArr,x-1,y) && !tempCArr[x-1][y]){ //move up
                return copyStain(x-1, y,j,k, stainArr, tempArr, tempCArr);
            }
            
            if (validate(stainArr,x+1,y) && !tempCArr[x+1][y]){ // move down
                return copyStain(x+1, y,j,k, stainArr, tempArr, tempCArr);
            }   
            
            if (validate(stainArr,x,y+1) && !tempCArr[x][y+1]){ // move right 
                return copyStain(x, y+1,j,k, stainArr, tempArr, tempCArr);
            }
            
            if (validate(stainArr,x,y-1) && !tempCArr[x][y-1]){// move left
                return copyStain(x, y-1,j,k, stainArr, tempArr, tempCArr);
            }
            
            if (validate(stainArr,x+1,y+1) && !tempCArr[x+1][y+1]){ // move down right
                return copyStain(x+1, y+1,j,k, stainArr, tempArr, tempCArr); 
            } 
            
            if (validate(stainArr,x+1,y-1) && !tempCArr[x+1][y-1]){  // move down left
                return copyStain(x+1, y-1,j,k, stainArr, tempArr, tempCArr);
            }
            
            if (validate(stainArr,x-1,y-1) && !tempCArr[x-1][y-1]){ // move up right
                return copyStain(x-1, y-1,j,k, stainArr, tempArr, tempCArr);
            }
            
            if (validate(stainArr,x-1,y+1) && !tempCArr[x-1][y+1]){ // move up left
                return copyStain(x-1, y+1,j,k, stainArr, tempArr, tempCArr);
            }
            
            if (isEdge(stainArr, tempCArr, x,y)){ 
                stainArr[x][y] = false;
                tempArr[x][y] = true;
                tempCArr = new boolean[stainArr.length][stainArr[0].length];
                return copyStain(j,k,j,k ,stainArr, tempArr,tempCArr);
            }    
        }
        return tempArr;
    }
    /*
     * ********************************************************
     * Question 3 - end
     * ********************************************************
     */
    
    /*
     * ********************************************************
     * Question 4 - start
     * ********************************************************
     */
    /**
     * isPermutation receives two arrays and check if one is a permutation of the other.
     * @param aa - is a copy of the first array
     * @param bb - is a copy of the second array
     * @param contentOfIndex - holds a boolean value about the status of two compared indexes.
     * true if both indexes hold the same value and false otherwise.
     * @return true if both arrays are a permutation of each other. false otherwise.  
     */
    
    public static boolean isPermutation(int[] a, int[] b){
        if (a.length != b.length){
            return false;
        }
        int[] aa = new int[a.length];
        int[] bb = new int[b.length];    
        
        copyArray(a, aa, 0);
        copyArray(b, bb, 0);
        /*
        System.out.println("this is aa after copy");    
        for (int i = 0; i < a.length; i++){
            System.out.print(aa[i] + " ");
        }
        System.out.println();  
        System.out.println("this is bb after copy");    
        for (int i = 0; i < a.length; i++){
            System.out.print(bb[i] + " ");
        }
        System.out.println();  
        */
        selectionSort(aa, 0);  
        selectionSort(bb, 0); 
        /*
        System.out.println("this is aa after sort");    
        for (int i = 0; i < a.length; i++){
            System.out.print(aa[i] + " ");
        }
        System.out.println();  
        System.out.println("this is bb after sort");    
        for (int i = 0; i < a.length; i++){
            System.out.print(bb[i] + " ");
        }
        */
        if (aa[0] == bb[0]){
           boolean contentOfIndex = true;
           if (compare(aa, bb, 1, contentOfIndex)){
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
    
    // The copyArray method is used to copy an array.
    private static void copyArray(int[] a, int[] aa, int x){
        if (x< a.length){
                aa[x] = a[x];
                copyArray(a,aa,x+1);
            }   
        return;
    }
    
    // The findMin method is used to find the minimum value in a given array.
    private static int findMin(int[] a, int x){
        if (x == a.length-1){
            return a.length -1;
        }
        int minIndex = findMin(a, x+1);
        if (a[x] > a[minIndex]){
            return minIndex;
        }
        else{
            return x;
        }
    }
    
    // The selectionSort method is used to sort a given array from minimum to maximum.
    private static void selectionSort(int[] a, int x){
        if ( x == a.length-1){  //recursion limit
            return;
        }
        int minimum = findMin(a, x);
        if (minimum != x){
            int temp = a[minimum];
            a[minimum] = a[x];
            a[x] = temp;
        }
        selectionSort(a, x+1);
    }
    
    // The compare method is used to compare two arrays.
    private static boolean compare(int[] aa, int[] bb, int x, boolean contentOfIndex){
        if (x < aa.length){
            if (aa[x] != bb[x]){
                contentOfIndex = false;
                return contentOfIndex;
            }
            contentOfIndex = compare (aa, bb, x+1, contentOfIndex); 
        }
        return contentOfIndex;
    }
    
    /*
     * ********************************************************
     * Question 4 - end
     * ********************************************************
     */
    
    
}


