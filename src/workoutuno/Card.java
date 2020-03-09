//Carlos Cuartas
//FOR: CS 2365
//Help from Code Clique and Brandon!
//He did some minor touch ups to naming and bugs
package workoutuno;
/**
 * 
 * @author lolc4
 */

/*
TEST CASE
Input: None
Expected: Card&String
Actual: Card&String
*/
public class Card {
    enum Color      //creates final variables for colors, like an array
    {
        Green, Red, Blue, Yellow, Black;        //so Blue would be Color.colors[2];
        
        private static final Color[] colors=Color.values();
        public static Color getColor(int i)
        {
            return Color.colors[i];
        }
    }
    enum Type       //same as above
    {   //BRBORT- Added Zero as a type
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Skip, Reverse, Draw2, Wild, Wild4;
        private static final Type[]type=Type.values();
        public static Type getType(int i)
        {
            return Type.type[i];
        }
    }
    private final Color color;      //card color and type arent dynamic so i locked them in
    private final Type type;
   
    /**
     *Color of Card
     * @param color
     * @param type
     */
    /*
TEST CASE
Input: color&type
Expected: Card&String
Actual: Card&String
*/
    public Card(Color color, Type type)     
    {
        this.color=color;
        this.type=type;
    }
    ////////////////////////////////////////////Gets here, idk if i need setters for card class

    /**
     *getter
     * @return color of card
     */
    /*
TEST CASE
Input: None
Expected: color
Actual: color
*/
public Color getColor()
{
    return this.color;
}

    /**
     *getter
     * @return type and number
     */
/*
TEST CASE
Input: None
Expected: type  
Actual: type
*/
    public Type getType()
{
    return this.type;
}

    /**
     *
     * @return string statement to clarify
     */
    /*
TEST CASE
Input: None
Expected: String with color&type
Actual: String with color&type
*/
    public String toString()        //use this to call and make it easier to see what ur card is
{
    return color+"_"+type;
}
    
}
