import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws ScannerException {

        String input = input();
        String output = calc(input);

        System.out.println();
        System.out.println("Output:");
        System.out.println();
        System.out.println(output);
    }
        public static String calc(String input) throws ScannerException {

            String output;
            int a, b, resArab = 0;
            List<String> accRom = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");

            String[] stringsArray = input.toUpperCase().replaceAll("\\s+", "").split("([+\\-*/])");

                if (accRom.contains(stringsArray[0])) {
                    a = ConvAccRom.valueOf(stringsArray[0]).getNum();
                    b = ConvAccRom.valueOf(stringsArray[1]).getNum();
                } else {
                    a = parseInt(stringsArray[0]);
                    b = parseInt(stringsArray[1]);
                }

            char[] charArray = input.toCharArray();
            for (char element : charArray) {
                switch (element){
                    case '+':
                        resArab = a + b;
                        break;
                    case '-':
                        if (accRom.contains(stringsArray[0]) && a<=b) {
                            throw new ScannerException("////т.к. в римской системе нет отрицательных чисел и ноля");
                        } else {
                        resArab = a - b;}
                        break;
                    case '*':
                        resArab = a * b;
                        break;
                    case '/':
                        if (accRom.contains(stringsArray[0]) && a<b) {
                            throw new ScannerException("////т.к. в римской системе нет дробей и ноля");
                        } else {
                        resArab = a / b;}
                        break;
                }
            }
            if (accRom.contains(stringsArray[0])) {
                output = IntegerConverter.intToRoman(resArab);
            } else {
                output = String.valueOf(resArab);
            }
            return output;
        }
        static String input() throws ScannerException {

            String input;

            Scanner in = new Scanner(System.in);
            System.out.println("Input:");
            System.out.println();
            input = in.nextLine();
            in.close();

            String[] stringsArray = input.toUpperCase().replaceAll("\\s+", "").split("([+\\-*/])");
            char[] charArray = input.toCharArray();

            String[] accAct = {"+", "-", "*", "/"};
            List<String> accArab = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
            List<String> accRom = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");

            if (stringsArray.length != 2) {
                throw new ScannerException("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }

            Arrays.sort(charArray);

            for (String element : accAct) {
                for (int i = 0; i < charArray.length - 1; i++) {
                    if ((charArray[i] == charArray[i + 1] && String.valueOf(charArray[i]).equals(element))) {
                        throw new ScannerException("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    }
                }
            }
            for (int i = 0; i < charArray.length - 1; i++) {
                for (int j = 0; j < accAct.length - 1; j++) {
                    if ((String.valueOf(charArray[i]).equals(accAct[j]) && String.valueOf(charArray[i + 1]).equals(accAct[j + 1]))) {
                        throw new ScannerException("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    }
                }
            }
            if ((accArab.contains(stringsArray[0]) && accRom.contains(stringsArray[1])) ||
                    (accArab.contains(stringsArray[1]) && accRom.contains(stringsArray[0]))){
                throw new ScannerException("//т.к. используются одновременно разные системы счисления");
            }
            if ((!accArab.contains(stringsArray[0]) && !accRom.contains(stringsArray[0])) ||
                    (!accArab.contains(stringsArray[1]) && !accRom.contains(stringsArray[1]))) {
                throw new ScannerException("//т.к. операнд(ы) не соответствуют диапазону допустимых значений");
            }

            return input;

        }
}