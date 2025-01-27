package work1;

import java.util.Scanner;
import java.time.Year;

/**
 * Class Main.
 * 
 */
public class Main {

  /** Scanner */
  private static Scanner scan;

  /** PrintedEdition. Базовый объект. */
  private static PrintedEdition base;

  /**
   * Точка входа. Инициализация алгоритма. Вызов меню.
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.print("\nВариант 4. Печатное издание, журнал, книга, учебник.\n"
        + "----------------------------------------------\n"
        + "Команды:                                      \n"
        + " 1  - добавить                                \n"
        + " 2  - удалить                                 \n"
        + " 3  - показать                                \n"
        + " 4  - сравнить                                \n"
        + " 00 - выход/отмена                            \n"
        + "                                              \n"
        + " типы изданий: б- базовое, ж- журнал,         \n"
        + "               к- книга,   у- учебник.        \n"
        + "----------------------------------------------\n" + "-> команда: ");
    scan = new Scanner(System.in);
    base = new PrintedEdition();
    String command;
    while (true) {
      try {
        command = prompt("");
      } catch (Exception e) {
        System.out.print("Что-то пошло не так..\n" + e + "\nВыход из программы");
        break;
      }
      switch (command) {
      case "1":
        add();
        break;
      case "2":
        delete();
        break;
      case "3":
        if (base.isEmptyList()) {
          System.out.println("<- Список печатных изданий пуст.");
          break;
        }
        base.show();
        break;
      case "4":
        compare();
        break;
      case "00":
      case "0":
        System.out.println("Выход из программы");
        System.exit(0);
        break;
      default:
        System.out.println("<- Неизвестная команда.");
      }
      System.out.print("\n-> команда: ");
    }
  }

  /**
   * Пробует удалить элемент из общей колекции.
   */
  private static void delete() {
    if (base.isEmptyList()) {
      System.out.println("<- Список печатных изданий пуст.");
      return;
    }
    int index;
    while (true) {
      String data = getStringData("  -> Индекс: ");
      if (data == null) {
        return;
      }
      if (isNumberInRange(data, 0, 20000)) {
        index = Integer.parseInt(data);
        break;
      }
    }
    String name = base.delete(index);
    if (name != null) {
      System.out.println("  <- Удалено издание: " + name);
      return;
    }
    System.out.println("  <- Отсутствует печатное издание с индексом " + index);
    delete();
  }

  /**
   * Сравнивает два объекта.
   */
  private static void compare() {
    String objs = prompt("  -> Индексы двух объектов (через пробел): ");
    if (objs.equals("00")) {
      return;
    }
    if (!objs.isBlank()) {
      String[] indexes = objs.split(" ");
      if (indexes.length == 2) {
        if (isInt(indexes[0]) && isInt(indexes[1])) {
          int index1 = Integer.parseInt(indexes[0]);
          int index2 = Integer.parseInt(indexes[1]);
          if (base.issetByIndex(index1) && base.issetByIndex(index2)) {
            if (base.getElement(index1).equals(base.getElement(index2))) {
              System.out.println("  <- Объекты равны.");
            } else {
              System.out.println("  <- Объекты НЕ равны.");
            }
            return;
          }
        }
      }
    }
    System.out.println("  <- Некорректные индексы.");
    compare();
  }

  /**
   * Добавляет объект в коллекцию. Поля объекта вводятся через консоль.
   */
  private static void add() {
    // тип издания
    String publicationType = getPublicationType();
    if (publicationType.isEmpty()) {
      return;
    }

    // название издания
    System.out.println("  !! если оставить только точку, то создаётся объект по умолчанию.");
    String nameEdition = getStringData("  -> Название: ");
    if (nameEdition == null) {
      return;
    }
    if (nameEdition.equals(".")) {
      addStandartEdition(publicationType);
      return;
    }

    // количество страниц
    int numPages;
    while (true) {
      String data = getStringData("  -> Количество страниц (1-5000): ");
      if (data == null) {
        return;
      }
      if (isNumberInRange(data, 1, 5000)) {
        numPages = Integer.parseInt(data);
        break;
      }
      System.out.println("  <- Некорректное количество страниц.");
    }

    // создаем объект на основе типа публикации
    switch (publicationType) {
    case "PrintedEdition":
      new PrintedEdition(nameEdition, numPages);
      System.out.println("  <- Добавлено печатное издание '" + nameEdition + "'.");
      break;

    case "Magazine":
      String subjectMatter = getStringData("  -> Тематика: ");
      if (subjectMatter.isEmpty()) {
        return;
      }
      int issue;
      while (true) {
        String data = getStringData("  -> Выпуск (1-100000): ");
        if (data == null) {
          return;
        }
        if (isNumberInRange(data, 1, 100000)) {
          issue = Integer.parseInt(data);
          break;
        }
        System.out.println("  <- Некорректный выпуск.");
      }
      new Magazine(nameEdition, numPages, subjectMatter, issue);
      System.out.println("  <- Добавлен журнал '" + nameEdition + "'.");
      break;

    case "Book":
      String authors = getStringData("  -> Автор(ы): ");
      if (authors == null) {
        return;
      }
      int yearPublishing;
      while (true) {
        String data = getStringData("  -> Год издания: ");
        if (data == null) {
          return;
        }
        if (isNumberInRange(data, -2147483648, Year.now().getValue())) {
          yearPublishing = Integer.parseInt(data);
          break;
        }
        System.out.println("  <- Некорректный год.");
      }
      new Book(nameEdition, numPages, authors, yearPublishing);
      System.out.println("  <- Добавлена книга '" + nameEdition + "'.");
      break;

    case "ClassBook":
      String publishingHouse = getStringData("  -> Издательство: ");
      if (publishingHouse == null) {
        return;
      }
      int cls;
      while (true) {
        String data = getStringData("  -> Класс/курс (1-11): ");
        if (data == null) {
          return;
        }
        if (isNumberInRange(data, 1, 11)) {
          cls = Integer.parseInt(data);
          break;
        }
        System.out.println("  <- Некорректный класс/курс.");
      }
      new ClassBook(nameEdition, numPages, publishingHouse, cls);
      System.out.println("  <- Добавлен учебник '" + nameEdition + "'.");
      break;
    }
  }

  /**
   * Добавляет в коллекцию стандартный (по умолчанию) объект на основе типа
   * издания.
   * 
   * @param publicationType - тип издания, добавляемого объекта.
   */
  private static void addStandartEdition(String publicationType) {
    switch (publicationType) {
    case "PrintedEdition":
      new PrintedEdition("Тетрадь", 12);
      System.out.println("  <- Добавлено печатное издание.");
      break;
    case "Magazine":
      new Magazine();
      System.out.println("  <- Добавлен журнал.");
      break;
    case "Book":
      new Book();
      System.out.println("  <- Добавлена книга.");
      break;
    case "ClassBook":
      new ClassBook();
      System.out.println("  <- Добавлен учебник.");
      break;
    }
  }

  /**
   * Проверяет можно ли преобразовать строку в число в некотором диапазоне.
   * 
   * @param expected - ожидаемое число.
   * @param min      - минимальное значение числа.
   * @param max      - максимальное значение числа.
   * @return true, если `expected` можно преобразовать в число и это число
   *         находится в диапазоне от `min` до `max` (включительно), иначе -
   *         false.
   */
  private static boolean isNumberInRange(String expected, int min, int max) {
    return isInt(expected) && Integer.parseInt(expected) >= min
        && Integer.parseInt(expected) <= max;
  }

  /**
   * Предлагает, через консоль, ввести строку. Предложение будет выводиться, пока
   * не будет введена строка.
   * 
   * @param offer - строка с предложение ввести данные в консоли.
   * @return пустую строку, если ввести '00', иначе введенные даные.
   */
  private static String getStringData(String offer) {
    String input = prompt(offer);
    if (input.equals("00")) {
      return null;
    }
    return input.isBlank() ? getStringData(offer) : input;
  }

  /**
   * Предлагает, через консоль, ввести букву, обозначающую тип издания.
   * Предложение будет выводиться, пока не будет введен нужный символ. Символ
   * можно ввести в любом регистре и любой раскладке.
   * 
   * @return пустую строку, если ввести '00', иначе строку с типом издания.
   */
  private static String getPublicationType() {
    String input = prompt("  -> Тип издания: ");
    switch (input) {
    case "Б":
    case "б":
    case ",":
    case "<":
      return "PrintedEdition";
    case "Ж":
    case "ж":
    case ";":
    case ":":
      return "Magazine";
    case "К":
    case "к":
    case "r":
    case "R":
      return "Book";
    case "У":
    case "у":
    case "e":
    case "E":
      return "ClassBook";
    case "00":
      return "";
    default:
      System.out.println("  <- Неизвестное издание.");
      return getPublicationType();
    }
  }

  /**
   * Проверяет, можно ли преобразовать строку в число int.
   * 
   * @param value - проверяемая строка.
   * @return true, если можно преобразовать в int, иначе - false.
   */
  private static boolean isInt(String value) { // -2147483648 to 2147483647 (or +2147483647)
    if (value.length() > 11) {
      return false;
    }
    char sign = value.charAt(0);
    if (sign == '-' || sign == '+') {
      value = value.substring(1);
    }
    if (value.length() == 10) {
      String regexp = sign == '-' ? "[^9]" : "[^89]";
      return value.matches("[12][01][0-4][^89][0-4][^9][0-3][0-6][0-4]" + regexp);
    }
    if (value.length() > 10) {
      return false;
    }
    return value.matches("^(-|\\+)?\\d+$");
  }

  /**
   * Предлагает ввести данные через консоль.
   * 
   * @param offer - строка с предложением.
   * @return введенные данные.
   */
  private static String prompt(String offer) {
    System.out.print(offer);
    return scan.nextLine().trim();
  }

}
