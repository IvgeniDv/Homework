
/**
 * Maman 15: WordNode.java -
 * This class represents a "word" object
 * which holds the information regarding said word in a specific text,
 * such as number of appearances, the letter it starts with,
 * and clearly the word itself.
 * 
 * @version  09/06/2021
 */

public class WordNode
{
    // instance variables 
    private String _word;
    private WordNode _next;
    private int _frequency; // number of appearances in the given text
    private char _startingChar; // the first letter of the given word
    
    /**
     * Constructs a word node using a specified word.
     * @param word - the desired string we want to make into a word node.
     * Runtime complexity - O(1)
     * Memory complexity - O(1)
     */
    public WordNode(String word){
        _word = word; // assignment opertation - runtime complexity - O(1); Memory complexity is constant - O(1). 
        _next = null; // assignment opertation - runtime complexity independent of user value - O(1); Memory complexity is constant - O(1).
        _frequency = 1; // assignment opertation - runtime complexity independent of user value - O(1); Memory complexity is constant - O(1).
        if (word != ""){ // runtime complexity of if statment with a single condition - O(1).
            _startingChar = word.charAt(0); // assignment opertation - runtime complexity is constant - O(1); Memory complexity is constant - O(1).
        }
    }
    
    /**
     * Returns the string that defines the node.
     * @return the word of the node.
     * Runtime complexity - O(1) 
     * Memory complexity - O(1) 
     */
    public String getWord(){
        return _word; // memory complexity O(1) - doesnt define parameters. memory use is constant; Runtime complexity - a single return of one value.
    }
    
    /**
     * Changes the string that defines the node.
     * @param word -  the new string for the node.
     * Runtime complexity - O(1)  
     * Memory complexity - O(1) 
     */
    public void setWord(String word){
        _word = word; // memory complexity O(1) - method defines a single parameter. Runtime complexity O(1) - assignment opertation.
    }
    
    /**
     * Returns the next node in line.
     * @return  -  the next node in lexicographic order.
     * Runtime complexity - O(1) 
     * Memory complexity - O(1) 
     */
    public WordNode getNext(){
        return _next; // memory complexity O(1) - doesnt define parameters. memory use is constant; Runtime complexity - a single return of one value.
    }
    
    /**
     * Changes the next node to the desired node.
     * @param wnode -  the next node to be.
     * Runtime complexity - O(1)
     * Memory complexity - O(1)
     */
    public void setNext(WordNode wnode){
        _next = wnode; // memory complexity O(1) - method defines a single parameter. Runtime complexity O(1) - assignment opertation.
    }
    
    /**
     * Changes the frequency of appearance of the word in the node.
     * @param x -  the new frequency value.
     * Runtime complexity - O(1)
     * Memory complexity - O(1)
     */
    public void setFrequency(int x){
        _frequency = x; // memory complexity O(1) - method defines a single parameter. Runtime complexity O(1) - assignment opertation.    
    }
    
    /**
     * @return -  frequency of appearance of the word in the node.
     * Runtime complexity - O(1)
     * Memory complexity - O(1)
     */
    public int getFrequency(){
        return _frequency; // memory complexity O(1) - doesnt define parameters. memory use is constant; Runtime complexity - a single return of one value.
    }

    /**
     * Changes the "first letter of the word" value in the node.
     * @param startingChar -  the new character representing the starting letter of the word in the node.
     * Runtime complexity - O(1)
     * Memory complexity - O(1)
     */
    public void setStartingChar(char startingChar){
        _startingChar = startingChar; // memory complexity O(1) - method defines a single parameter. Runtime complexity O(1) - assignment opertation.   
    }
    
    /**
     * @return -  the character representing the starting letter of the word in the node.
     * Runtime complexity - O(1)
     * Memory complexity - O(1)
     */
    public char getStartingChar(){
     return _startingChar; // memory complexity O(1) - doesnt define parameters. memory use is constant; Runtime complexity - a single return of one value.
    }
}
