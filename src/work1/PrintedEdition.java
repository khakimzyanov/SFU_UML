package work1;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Класс `Печатное издание`.
 * 
 * Базовый родительский класс печатных изданий. Хранит коллекцию всех печатных изданий.
 */
public class PrintedEdition {

  /** Тип издания */
  public String publicationType;

  /** Количество страниц */
  public int numPages;

  /** Название издания */
  public String name;

  /** Общее количество печатных изданий */
  private static ArrayList<PrintedEdition> list = new ArrayList<>();

  /**
   * Конструктор класса.
   */
  public PrintedEdition() {
    publicationType = this.getClass().getSimpleName();
  }

  /**
   * Конструктор класса.
   * 
   * @param name     - название.
   * @param numPages - количество страниц.
   */
  public PrintedEdition(String name, int numPages) {
    this();
    this.name = name;
    this.numPages = numPages;
    list.add(this);
  }

  /**
   * @return true, если общее количество печатных изданий пусто, иначе - false.
   */
  public boolean isEmptyList() {
    return list.isEmpty();
  }

  /**
   * Проверяет наличие элемента по индексу.
   * 
   * @param index - предполагаемый индекс элемента.
   * @return true, если есть элемент с указанным индексом, иначе - false.
   */
  public boolean issetByIndex(int index) {
    return !(index < 0 || list.isEmpty() || list.size() <= index);
  }

  /**
   * Пробует получить элемент по индексу.
   * 
   * @param index - предполагаемый индекс элемента.
   * @return элемент, иначе - null.
   */
  public PrintedEdition getElement(int index) {
    return issetByIndex(index) ? list.get(index) : null;
  }

  /**
   * Пробует удалить элемент по индексу.
   * 
   * Элемент может удалить только объект класса, который его создал. Объект базового класса
   * может удалять любой элемент.
   * 
   * @param index - индекс удаляемого элемента.
   * @return если элемент был удален - строку с типом издания и его названием, иначе - null.
   */
  public final String delete(int index) {
    if (issetByIndex(index)) {
      if (publicationType.equals("PrintedEdition")
          || publicationType.equals(list.get(index).publicationType)) {
        String name = list.get(index).publicationType + " - " + list.get(index).name;
        list.remove(index);
        return name;
      }
      System.out.println("Не достаточно прав для выполнения данной операции.");
    }
    return null;

  }

  /**
   * Выводит в консоль все элементы.
   */
  public void show() {
    for (PrintedEdition obj : PrintedEdition.list) {
      System.out.println("\n" + obj.toString());
    }
  }

  /**
   * @return тип публикации на русском (для toString)
   */
  private String getPublicationType() {
    switch (publicationType) {
      case "ClassBook":
        return "Учебник";
      case "Book":
        return "Книга";
      case "Magazine":
        return "Журнал";
      default:
        return "Базовое";
    }
  }

  /**
   * Переопределенный метод сравнения объектов.
   * 
   * @return true, если объект, созданный на основе текущего класса, равен объекту из
   *         аргумента, иначе - false.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null) {
      return false;
    }
    if (getClass() != other.getClass()) {
      return false;
    }
    if (!(other instanceof PrintedEdition)) {
      return false;
    }
    PrintedEdition obj = (PrintedEdition) other;
    return publicationType.equals(obj.publicationType) && numPages == obj.numPages
        && name.equals(obj.name);
  }

  /**
   * Переопределенный метод получения уникального целочисленного значения.
   * 
   * @return значение хэш-кода для объекта.
   */
  @Override
  public int hashCode() {
    return Objects.hash(publicationType, name, numPages);
  }

  /**
   * Переопределенный метод строкового представления объекта.
   * 
   * @return строку с описанием объекта.
   */
  @Override
  public String toString() {
    return getClass().getName() + "\nТип: " + getPublicationType() + ";  Название: " + name
        + ";  Страниц: " + numPages;
  }

}
