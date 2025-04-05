import java.util.Random;
import java.util.Scanner;

public class EncryptorCasear {

    private static String[] _message = new String[] 
    {
        "Hello", "What your name", "Lets go",
        "I have a bread", "Money", "Get out please",
        "Not laught", "Cat", "Dog"
    };

    public static void main(String[] args)
    {
        while (true)
        {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            System.out.println("\nВыберите действие:");
            System.out.println("1. Выход");
            System.out.println("2. Ввести сообщение вручную");
            System.out.println("3. Сгенерировать случайное сообщение");
            System.out.println("4. Очистить консоль");
            System.out.print("Ваш выбор: ");

            int input = scanner.nextInt();
            
            if (input < 1 || input > 4) {
                System.out.println("Некорректный ввод. Пожалуйста, введите число от 1 до 4.");
                continue; // Переход к следующей итерации цикла
            }

            int step;

            switch (input) {
                case 1:
                    System.out.println("Выход из программы.");
                    return;
                case 2: // Ввод пользователем
                    System.out.print("Введите сообщение: ");
                    String message = scanner.next();

                    System.out.print("Выберете шаг сдвига: ");
                    step = scanner.nextInt();

                    Encrypt(message, step);
                    System.out.println();
                    break;
                case 3: // Рандомный ввод
                    String rndMessage = _message[random.nextInt(8)];
                    System.out.println("Рандомное слово: " + rndMessage);

                    System.out.print("Выберете шаг сдвига: ");
                    step = scanner.nextInt();

                    Encrypt(rndMessage, step);
                    System.out.println();
                    break;
                case 4: // Очистка консоли
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    break;
            }
            
        }
    }

    public static void Encrypt(String message, int step) // Метод содержит парметры для сообщения и шага изменения символа
    {
        char[] messageChars = message.toCharArray();     // Превращаем всё сообщение в массив знаков для дальнейшего из изменения при помощи шага
        System.out.print("Ваше зашифрованное слово: ");
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
                System.out.print((char)numChar);
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
                System.out.print((char)numChar);
            }
            else if (numChar == 32) // Для пробела сделал типо исключения чтобы можно было писать нормальные предложения, но и знаки можно добавить к примеру [,;-./!?]
            {
                System.out.print(" ");
            }
            else
            {
                System.out.println(" [знак не латынь] "); 
                break;
            }                  
        }
    }
} 

// Я пришел с миром из .NET разработки :D