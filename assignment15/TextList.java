
/**
 * Maman 15: TextLIst.java - 
 * The class TextList is a database that holds the words of a given string.
 * Using TextList we can make queries regarding the given text.
 *
 * @version  09/06/2021
 * @author Ivgeni Dvorkin
 */

public class TextList
{
    // instance variables
    // a single node that holds a word and its related data, in the given string
    private WordNode _head;

    /**
     * An empty constructor to initialize the database.
     * Runtime complexity - O(1)
     * Memory complexity - O(1)
     */
    public TextList(){
        _head = null; // assignment opertation - runtime complexity independent of user value - O(1); Memory complexity is constant - O(1).        
    }
    
    /**
     * Constructor that receives a collection of words.
     * It constructs a new list out of this text. 
     * Runtime complexity - O(n) + O(nlogn) = O(nlogn);
     * Memory complexity - O(n)
     */
    public TextList(String text){
        if (text.length() == 0){ // runtime complexity of a single statment O(1)
            String emptyString = ""; // runtime complexity is independent of user input - O(1); Memory complexity is constant - O(1).
            WordNode emptyTemp = new WordNode(emptyString); // runtime complexity is independent of user input - O(1); Memory complexity is constant - O(1).
            _head = emptyTemp; // runtime complexity is independent of user input - O(1); Memory complexity is constant - O(1)
        }
        else{
            int count = 0; // runtime complexity - O(1); Memory complexity is constant - O(1)
            String newWord = ""; // runtime complexity - O(1); Memory complexity is constant - O(1)
            for (int i = 0; i < text.length(); i++){ //Memory complexity - O(1); runtime complexity O(n)
                while (text.charAt(i) != ' '){ // runtime complexity O(1)
                    newWord = newWord + text.charAt(i); // runtime complexity O(1); Memory complexity - O(1)
                    if (i == text.length() -1){ // runtime complexity O(1)
                        break;
                    }
                    i++; // runtime complexity O(1) 
                }
                WordNode temp = new WordNode(newWord); // runtime complexity O(1); Memory complexity - O(1)
                addToHead(temp); // runtime complexity O(1); Memory complexity - O(1)
                newWord = ""; // runtime complexity O(1); Memory complexity - O(1)
            }
            _head = mergeSort(_head);  // runtime complexity O(nlogn);  Memory complexity - O(n)
        }
    }
    
    /**
     * Adds a given word to the databse and adjusts the lists and its parameters accordingly. 
     * @param word - a string given by the user to be added to the list.
     * Runtime complexity - O(n)
     * Memory complexity - O(1)
     */
    public void addToData(String word){
        if (word != ""){ // runtime complexity O(1)
            WordNode newData = new WordNode(word); // runtime complexity O(1); Memory complexity - O(1)
            if (_head == null){ // runtime complexity O(1)
                _head = newData;  // runtime complexity O(1); Memory complexity - O(1)
            }
            else{
                if(newData.getWord().compareTo(_head.getWord()) == 0){ // runtime complexity O(1); Memory complexity - O(1)
                    _head.setFrequency(_head.getFrequency() + 1);  // runtime complexity O(1); Memory complexity - O(1)
                }
                else if(newData.getWord().compareTo(_head.getWord()) < 0){ // runtime complexity O(1); Memory complexity - O(1)
                    addToHead(newData); // runtime complexity O(1); Memory complexity - O(1)
                }
                else{
                    WordNode previousNode = _head; // runtime complexity O(1); Memory complexity - O(1)
                    WordNode currentNode = _head.getNext(); // runtime complexity O(1); Memory complexity - O(1)
                    while (currentNode != null){  // runtime complexity O(n); Memory complexity - O(1) (worst case)
                        if(newData.getWord().compareTo(currentNode.getWord()) == 0){ // runtime complexity O(1); Memory complexity - O(1)
                            currentNode.setFrequency(currentNode.getFrequency() + 1); // runtime complexity O(1); Memory complexity - O(1)
                            break;
                        }
                        else if(newData.getWord().compareTo(currentNode.getWord()) < 0){ // runtime complexity O(1); Memory complexity - O(1) 
                            previousNode.setNext(newData); // runtime complexity O(1); Memory complexity - O(1)
                            newData.setNext(currentNode); // runtime complexity O(1); Memory complexity - O(1)
                            break;
                        }
                        else{
                            previousNode = previousNode.getNext(); // runtime complexity O(1); Memory complexity - O(1)
                            currentNode = currentNode.getNext(); // runtime complexity O(1); Memory complexity - O(1)
                        }
                    }
                    if (currentNode == null){ // runtime complexity O(1)
                        previousNode.setNext(newData);  // runtime complexity O(1); Memory complexity - O(1)  
                    }
                }
            }
        }
    }
    
    /**
     * Calcuates the total number of words in the text. returns an integer. 
     * @return  - the number of total words in the given text.
     * Runtime complexity - O(n)
     * Memory complexity - O(1) - defines 1 object and 1 integer.
     */
    public int howManyWords(){
        WordNode temp = _head; // runtime complexity O(1); Memory complexity - O(1)  
        int count = 0; // runtime complexity O(1); Memory complexity - O(1)  
        if (_head.getWord().compareTo("") == 0){ // runtime complexity O(1);  
            return count; // runtime complexity O(1);  
        }
        while (temp != null){ // runtime complexity O(n); Memory complexity - O(1) (worst case)
            count += temp.getFrequency(); // runtime complexity O(1); Memory complexity - O(1)
            temp = temp.getNext(); // runtime complexity O(1); Memory complexity - O(1)
        }
        return count; // runtime complexity O(1);
    }
    
    /**
     * Calcuates the total number of different words in the text. returns an integer. 
     * @return  - the number of different words in the given text.
     * Runtime complexity - O(n) - to count all the lists nodes we must go through all its objects.
     * Memory complexity - O(1) - defines 1 object and 1 int - once.
     */
    public int howManyDifferentWords(){
        WordNode temp = _head;
        int count = 0;
        if (_head.getWord().compareTo("") == 0){
            return count;
        }
        while (temp != null){
            count++;
            temp = temp.getNext();
        }
        return count;
    }
    
    /**
     * Returns the word with most frequent appearances. returns a String. 
     * @return  - the most frequent word in the given text.
     * Runtime complexity - O(n) - we must go through all the objects and compare frequency.
     * Memory complexity - O(1) - defines 1 string 1 WordNode object and 1 int - once
     */
    public String mostFrequentWord(){
        String mostFrequentWord ="";
        if (_head == null){
            return mostFrequentWord;
        }
        else{
            int maxAppearances = 0;
            WordNode temp = _head;
            while (temp != null){
                if (temp.getFrequency() > maxAppearances){
                    maxAppearances = temp.getFrequency();
                    mostFrequentWord = temp.getWord();
                }
                temp = temp.getNext();
            } 
            return mostFrequentWord;
        }
    }
    
    /**
     * Receives a single character and calculates how many words start with that character. 
     * returns an integer. 
     * @param letter - a single character that will be used to check for frequency of words starting with it.
     * @return  - total number of words starting with a given character.
     * Runtime complexity - O(n) - loops through all the objects in the list
     * Memory complexity - O(1) - creates only one variable of type int
     */
    public int howManyStarting (char letter){
        int numOfStarting = 0;
        if (_head.getWord().equals("")){
            return numOfStarting;
        }
        else{
            WordNode temp = _head;
            while (temp != null){
                if (letter == temp.getStartingChar()){
                    numOfStarting = numOfStarting + temp.getFrequency();    
                }
                temp = temp.getNext();
            }
        }
        return numOfStarting;
    }
    
    /**
     * Prints a representation of the list by the output of the word following its frequency in the text.
     * Sorted in lexicographic order.
     * @return  - a string representation of the list.
     * Runtime complexity - O(n) - runs through all the list.
     * Memory complexity - O(1) - creates only two variables.
     */
    public String toString(){
        String list = "";
        WordNode temp = _head;
        if (_head == null || _head.getWord() == ""){
            return list;
        }
        while (temp != null){
            list = list + temp.getWord() + "\t" + temp.getFrequency() + "\n";
            temp = temp.getNext();
        }
        return list;
    }
    
    /**
     * Finds and returns the most frequent starting letter of the words in the text.
     * @return  - the character that most words in the text start with.
     */
    public char mostFrequentStartingLetter(){
        char mostUsed = ' ';
        if ( (_head == null) || (_head.getWord().compareTo("") == 0)){
            return mostUsed;
        }
        else{
            WordNode subHead = null;
            subHead = subList(subHead, _head);
            subHead = mergeSort(subHead);
            int max = 0;
            return frequentlyUsedChar(subHead, max, mostUsed );
        }
    }
    
    /*
     * *******************************************************
     * PRIVATE METHOD SECTION
     * *******************************************************
     */
    
    /**
     * The methods purpose is to find the most used starting letter of the words in the text.
     * It does this by comparing frequency of appearances of the words and returning their first letter.
     * @param subHead - a pointer to the start of the list.
     * @param x - the max value that will be compared against the frequency of the word.
     * @param z - a default charcater that will eventually hold the most used charcater.
     * @return - the most used starting charcter in the most number of words.
     * Runtime complexity - O(n) - runs through all the sub list comparing frequency.
     * Memory complexity - O(1) - no variables are created in this method.
     */
    private char frequentlyUsedChar(WordNode subHead, int x, char z){
        if (subHead == null){
            return z;
        }
        else{
            if (subHead.getFrequency() > x){
                x = subHead.getFrequency();
                z = subHead.getStartingChar();
                return frequentlyUsedChar(subHead.getNext(), x, z);
            }
            else{
                return frequentlyUsedChar(subHead.getNext(), x, z);
            }
        }
    }
    
    /**
     * The method is creating a sub list using the main list.
     * its main purpose is to remove and calculate duplicates of the starting characters. 
     * @param subHead - a pointer to the start of the new list.
     * @param head - a pointer to the start of the main list
     * @return - a WordNode object that points to the new sub list.
     * Runtime complexity - O(n) - runs through all the objects in the list.
     * Memory complexity - O(n) - creates 2 parameters every time the method runs. total of 2n parameters.
     */
    private WordNode subList(WordNode subHead, WordNode head){
        if (head == null){
            return subHead;
        }
        else{
            WordNode temp1 = subNode(head);
            WordNode temp2 = subHead;
            subHead = temp1;
            temp1.setNext(temp2);
            return subList(subHead, head.getNext());
        }
    }
    
    /**
     * The method is creating a new WordNode object from a given WordNode.
     * its main purpose is to eventually help ease the sorting process of the sub list. 
     * @param wnode - the WordNode we want to create a subNode from.
     * @return - a new WordNode object.
     * Runtime complexity - O(1).
     * Memory complexity - O(1).
     */
    private WordNode subNode(WordNode wnode){
        if (wnode == null){
            return null;
        }
        else {
            WordNode temp = new WordNode(wnode.getWord().substring(0,1));
            temp.setFrequency(wnode.getFrequency());
            return temp;
        }
    }
    
    /**
     * Adds a single given WordNode object to the start of the list.
     * @param wnode - the WordNode we want to add to the start of the list.
     * Runtime complexity - O(1).
     * Memory complexity - O(1).
     */
    private void addToHead(WordNode wnode){
        WordNode temp = _head; // Memory complexity - O(1);  runtime complexity O(1)
        _head = wnode; // runtime complexity O(1)
        wnode.setNext(temp); // runtime complexity O(1)
    }
    
    /**
     * Merges two lists into one while sorting the WordNode objects lexicographically.
     * @param list1 - head of the first list.
     * @param list2 - head of the second list.
     * @return The head of the new merged list.
     * Runtime complexity - O(n) -going through the two lists and marging each object into one list.
     * Memory complexity - O(1) - no variables created in this method.
     */
    private WordNode merge(WordNode list1, WordNode list2){
        if (list1 == null){ // runtime complexity O(1)
            return list2; // runtime complexity O(1)
        }
        if (list2 == null){ // runtime complexity O(1)
            return list1; // runtime complexity O(1)
        }
        if (list1.getWord().compareTo(list2.getWord()) == 0){ // runtime complexity O(1)
            list1.setFrequency(list1.getFrequency() + list2.getFrequency()); // runtime complexity O(1)
            list1.setNext(merge(list1.getNext(), list2.getNext())); // runtime complexity O(n)
            return list1; // runtime complexity O(1)
        }
        else if ( list1.getWord().compareTo(list2.getWord()) < 0 ){ // runtime complexity O(1)
            list1.setNext(merge(list1.getNext(), list2)); // runtime complexity O(n)
            return list1;  // runtime complexity O(1)
        }
        else{
            list2.setNext(merge(list1, list2.getNext())); // runtime complexity O(n)
            return list2; // runtime complexity O(1)   
        }
    }
    
    /**
     * Splits one list into two.
     * @param wnode - head of the list we want to split.
     * @return The head of the new split list.
     * Runtime complexity - O(n) - runs through all the list apliting objects one by one.
     * Memory complexity - O(n) - creating a new list with half the objects.
     */
    private WordNode split(WordNode wnode){
        if (wnode == null || wnode.getNext() == null){ // runtime complexity O(1)
            return null; // runtime complexity O(1)
        }
        WordNode list2 = wnode.getNext(); // runtime complexity O(1); Memory complexity - O(1)
        wnode.setNext(list2.getNext()); // runtime complexity O(1)
        list2.setNext(split(list2.getNext())); // runtime complexity O(n); Memory complexity - O(n)
        return list2; // runtime complexity O(1)
    }
    
    /**
     * Sorts a list lexicographically.
     * @param wnode - head of the list we want to sort.
     * @return The head of the new sorted list.
     * Runtime complexity - O(nlogn).
     * Memory complexity - O(n).
     */
    private WordNode mergeSort(WordNode wnode){
        if (wnode == null || wnode.getNext() == null){ // runtime complexity O(1)
            return wnode; // runtime complexity O(1)
        }
        WordNode list2 = split(wnode); // runtime complexity O(n); Memory complexity - O(1)
        wnode = mergeSort(wnode); // runtime complexity O(nlogn); Memory complexity - O(n)
        list2 = mergeSort(list2); // runtime complexity O(nlogn); Memory complexity - O(n)
        return merge(wnode, list2); // runtime complexity O(n); Memory complexity - O(1)
    }
    
}
