import java.util.Arrays;
import java.io.PrintStream;
public class Driver {
    public static void main(String[] args) {
        //init
        Pawns One = new Pawns(); 
        //prints board out 
        System.out.println(One.toString());
        One.move(6, 0, false);
        // move black pawn on the edge by one.
        System.out.println(One.toString());
        One.move(1,0,true); 
        // move white pawn on the edge by two.
        One.move(6, 1, true);
        System.out.println(One.toString());
        // move black pawn on the second to left edge by two.

        One.attack(3, 0, true);
        System.out.println(One.toString());
        // move white pawn on edge captures the black pawn that just moved

        One.attack(4, 1, false);
        System.out.println(One.toString());
        // move white pawn on edge captures again; Black pawn on the edge. 

        One.move(5, 0, false);
        System.out.println(One.toString());
        //moves foward until promation
        One.move(6, 0, false);
        System.out.println(One.toString());

        One.attack(1, 1, true);
         // invalid attack


    }
    
}
