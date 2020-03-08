//Carlos Cuartas
//Help from Code Clique
package workoutuno;
/**
 * 
 * @author lolc4
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
     *
     * @param color
     * @param type
     */
    public Card(Color color, Type type)     
    {
        this.color=color;
        this.type=type;
    }
    ////////////////////////////////////////////Gets here, idk if i need setters for card class

    /**
     *
     * @return
     */
public Color getColor()
{
    return this.color;
}

    /**
     *
     * @return
     */
    public Type getType()
{
    return this.type;
}

    /**
     *
     * @return
     */
    public String toString()        //use this to call and make it easier to see what ur card is
{
    return color+"_"+type;
}
    
}
