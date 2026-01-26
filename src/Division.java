public class Division implements BinaryOperation{
        public @interface Override{}
        public int execute(int num1, int num2) {
            return num1 / num2;
        }

        @Override
        public String getSymbol() {
            return "/";
        }

}
