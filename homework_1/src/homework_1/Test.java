package homework_1;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		 Scanner scanner = new Scanner(System.in);
         System.out.println("ВВедите свою дату рождения, пример 21 января 1974 г");
         String input = scanner.nextLine();
         
         DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy г");
         
         LocalDate birthDate = LocalDate.parse(input, dateFormatter);

         String zodiacSign = getZodiacSign(birthDate);

         int year = birthDate.getYear();
         String chineseYear = getChineseZodiac(year);

         System.out.println("Знак: " + zodiacSign);
         System.out.println("Год: " + chineseYear);
         
        
	}
	public static String getZodiacSign(LocalDate date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();

        if ((month == 12 && day >= 22) || (month == 1 && day <= 19)) return "козерог";
        if ((month == 1 && day >= 20) || (month == 2 && day <= 18)) return "водолей";
        if ((month == 2 && day >= 19) || (month == 3 && day <= 20)) return "рыбы";
        if ((month == 3 && day >= 21) || (month == 4 && day <= 19)) return "овен";
        if ((month == 4 && day >= 20) || (month == 5 && day <= 20)) return "телец";
        if ((month == 5 && day >= 21) || (month == 6 && day <= 20)) return "близнецы";
        if ((month == 6 && day >= 21) || (month == 7 && day <= 22)) return "рак";
        if ((month == 7 && day >= 23) || (month == 8 && day <= 22)) return "лев";
        if ((month == 8 && day >= 23) || (month == 9 && day <= 22)) return "дева";
        if ((month == 9 && day >= 23) || (month == 10 && day <= 22)) return "весы";
        if ((month == 10 && day >= 23) || (month == 11 && day <= 21)) return "скорпион";
        if ((month == 11 && day >= 22) || (month == 12 && day <= 21)) return "стрелец";
        

        return "Неизвестно";
    }
	
	public static String getChineseZodiac(int year) {
        String[] chineseZodiac = { "крысы", "быка", "тигра", "кролика", "дракона", "змеи", "лошади", "козы", "обезьяны", "петуха", "собаки", "свиньи" };
        int index = (year - 4) % 12;
        return chineseZodiac[index];
    }
}
