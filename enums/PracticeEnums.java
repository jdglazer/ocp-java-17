import java.util.Arrays;

interface Addable<T extends Number> { // Could also use a UnaryOperator since this is a functional interface
     T add(T t);
}

public class PracticeEnums {
    /*
        - Practice creating an enum with functions, fields and constructors
        - Practice static and instance methods inherited by enums
    */
    
    void localEnum() {
        enum LocalEnum implements Addable<Integer> {
            TWO(2), ZERO, ONE(1), THREE(3);
            
            private final int value;
            private final String String; // Notice that String is allowed as variable name

            private LocalEnum() {
                this(0);
            }
            
            private LocalEnum(int value) {
                this.value = value;
                this.String = this.name() + ": value - " + value + ", ordinal - " + this.ordinal();
            }

            public int intValue() {
                return this.value;
            }
            
            @Override
            public Integer add(Integer i) {
                return i + value;
            }

            @Override
            public String toString() {
                return this.String;
            }
        }
        
        System.out.println(LocalEnum.TWO.name()); // "TWO"
        System.out.println(LocalEnum.TWO.toString()); // "TWO: 2"
        System.out.println(LocalEnum.ONE.intValue()); // 1
        System.out.println(LocalEnum.TWO.add(4)); // 6
        System.out.println(Arrays.toString(LocalEnum.values())); // TWO, ZERO, ONE, THREE
        System.out.println(LocalEnum.TWO.ordinal()); // 0
        System.out.println(LocalEnum.valueOf("THREE").name()); // "THREE"
        
        try {
            LocalEnum.valueOf("Two");
        } catch (IllegalArgumentException iae) {
            System.out.println(iae);
        }
        
        // Show sorted order as ordinal
        LocalEnum[] scrambledLocalEnums = {LocalEnum.TWO, LocalEnum.ZERO, LocalEnum.THREE, LocalEnum.ONE};
        System.out.println("Before sort: " + Arrays.toString(scrambledLocalEnums));
        Arrays.sort(scrambledLocalEnums);
        System.out.println("After sort: " + Arrays.toString(scrambledLocalEnums));
    }
    
    public static void main(String...args) {
        var practiceEnums = new PracticeEnums();
        practiceEnums.localEnum();
    }
}
