package work1;

import java.util.Objects;

/**
 * Класс `Учебник`.
 */
public class ClassBook extends PrintedEdition {

  /** Издательство */
  public String publishingHouse;

  /** Класс/курс */
  public int cls;

  /**
   * Конструктор класса.
   */
  public ClassBook() {
    this("Информатика", 240, "Бином", 10);
  }

  /**
   * Конструктор класса.
   * 
   * @param name            - название.
   * @param numPages        - количество страниц.
   * @param publishingHouse - издательство.
   * @param cls             - класс/курс, для которго предназначен учебник.
   */
  public ClassBook(String name, int numPages, String publishingHouse, int cls) {
    super(name, numPages);
    this.publishingHouse = publishingHouse;
    this.cls = cls;
  }

  /**
   * Переопределенный метод сравнения объектов.
   * 
   * @return true, если объект, созданный на основе текущего класса, равен объекту
   *         из аргумента, иначе - false.
   */
  @Override
  public boolean equals(Object other) {
    if (super.equals(other)) {
      ClassBook obj = (ClassBook) other;
      return publishingHouse.equals(obj.publishingHouse) && cls == obj.cls;
    }
    return false;
  }

  /**
   * Переопределенный метод получения уникального целочисленного значения.
   * 
   * @return значение хэш-кода для объекта.
   */
  @Override
  public int hashCode() {
    return super.hashCode() + Objects.hash(publishingHouse, cls);
  }

  /**
   * Переопределенный метод строкового представления объекта.
   * 
   * @return строку с описанием объекта.
   */
  @Override
  public String toString() {
    return super.toString() + ";  Издательство: " + publishingHouse + ";  Класс (курс): " + cls;
  }

}
