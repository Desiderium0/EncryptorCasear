import java.util.Random;
import java.util.Scanner;

public class EncryptorCasear {

    private static String[] _message = new String[] 
    {
        "Hello!", "What your name?", "Lets go!",
        "I have a bread!", "Money.", "Get out please.",
        "Not, laught!", "Cat.", "Dog."
    };

    public static String RndMessage;
    public static String PrMessage;
    public static String Result;
    public static int Step;


    public static void main(String[] args)
    {
        while (true)
        {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            System.out.println("\nВыберите действие:");
            System.out.println("1. Ввод исходных данных, как вручную, так и сгенерированных случайным образом");
            System.out.println("2. Выполнение алгоритма по заданию");
            System.out.println("3. Вывод результата");
            System.out.println("4. Завершение работы программы.");
            System.out.print("Ваш выбор: ");
            int input = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            
            if (input < 1 || input > 5) {
                System.out.println("Некорректный ввод. Пожалуйста, введите число от 1 до 4.");
                continue; // Переход к следующей итерации цикла
            }

            switch (input) {
                case 1: // Ввод пользователем или случайно
                    System.out.println("1. Случайно");
                    System.out.println("2. Вручную");
                    System.out.print("Ваш выбор: ");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println();

                    if (num == 1)
                    {
                        RndMessage = null;
                        RndMessage = _message[random.nextInt(8)];
                        System.out.println("Случайное слово: " + RndMessage);

                        System.out.print("Выберете шаг сдвига: ");
                        Step = scanner.nextInt();

                        System.out.println();
                        break;
                    }
                    else if (num == 2)
                    {
                        PrMessage = null;
                        System.out.print("Введите сообщение: ");
                        PrMessage = scanner.nextLine();
                        
                        System.out.print("Выберете шаг сдвига: ");
                        Step = scanner.nextInt();
    
                        System.out.println();
                        break;
                    }
                    else
                    {
                        System.out.println("Некорректный ввод. Пожалуйста, введите число 1 или 2.");
                        System.out.println("Возврат в меню...");
                        continue;   
                    }
                case 2:
                if (PrMessage != null)
                {
                    Result = Encrypt(PrMessage, Step);
                    System.out.println("Шифрование завершено!");
                    break;
                }
                else if (RndMessage != null)
                {
                    Result = Encrypt(RndMessage, Step);
                    System.out.println("Шифрование завершено!");
                    break;
                }
                else
                {
                    System.out.println("Шифрования не было произведено!");
                }
                break;
                case 3:
                    if (Result == null) {
                        Result = "///Пусто///";
                    }
                    System.out.println("====================================");
                    System.out.println("Вывод результата: " + Result);
                    System.out.println("====================================");
                    break;
                case 4:
                    System.out.println("Выход из программы.");
                    return;
            }
        }
    }

    public static String Encrypt(String message, int step) // Метод содержит парметры для сообщения и шага изменения символа
    {
        char[] messageChars = message.toCharArray();     // Превращаем всё сообщение в массив знаков для дальнейшего из изменения при помощи шага
        String result = "";
        for (char ch : messageChars)
        {
            int numChar = (int)ch;
            char encChar;

            if (numChar <= 122 && numChar >= 97)                /////////// Проверка диапозона для строчных букв
            {
                numChar += step; // Изменение буквы при помощи заданого шага
                encChar = (char)numChar;

                if (numChar > 122) // Тут специально для последней буквы Z-z т.к. дальше идет другой символ нужно перестроить в самое начало в A-a
                {
                    if (numChar == 123)
                    {
                        numChar = 97;
                    }
                    else if (numChar > 123)
                    {
                        numChar = 97 + step;
                    } 
                }
                result += (char)numChar;
            }
            else if (numChar <= 90 && numChar >= 65)                /////////// Проверка диапозона для заглавных букв
            {
                numChar += step;
                encChar = (char)numChar;

                if (numChar > 90) 
                {
                    if (numChar == 91)
                    {
                        numChar = 65;
                    }
                    else if (numChar > 91)
                    {
                        numChar = 65 + step;
                    }
                }
                result += (char)numChar;
            }
            else 
            {
                if (numChar == 32)
                { 
                    result += (char)32; // " " - пробел
                }
                else if (numChar == 33)
                {
                    result += (char)33; // '!' - восклицательный знак
                }
                else if (numChar == 63)
                {
                    result += (char)63; // '?' - вопросительный знак
                }
                else if (numChar == 46)
                {
                    result += (char)46; // '.' - точка
                }
                else if (numChar == 44)
                {
                    result += (char)44; // ',' - запятая
                }
                else // Если знак относится не к лаытни то выведется это сообщение и закроет foreach
                {
                    System.out.println(" [Сообщение не на латыне] "); 
                    break;
                }
            }                  
        }
        return result;
    }
} 