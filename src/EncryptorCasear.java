import java.util.Scanner;

public class EncryptorCasear {
    public static void main(String[] args)
    {
        while (true)
        {
            System.out.print("Введите сообщение: ");
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine();

            Encrypt(message, 2);
            System.out.println();
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
                System.out.println("\n\tВы ввели часть сообщения или все не относящееся к латынице!"); 
                break;
            }                  
        }
    }
} 

// Я пришел с миром из .NET разработки :D