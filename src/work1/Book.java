package work1;

import java.util.Objects;

/**
 * Класс `Книга`.
 */
public class Book extends PrintedEdition {

  /** Авторы */
  public String authors;

  /** Год издания */
  public int yearPublishing;

  /**
   * Конструктор класса.
   */
  public Book() {
    this("Java. Полное руководство", 1488, "Герберт Шилдт", 2022);
  }

  /**
   * Конструктор класса.
   * 
   * @param name           - название.
   * @param numPages       - количество страниц.
   * @param authors        - автор(ы).
   * @param yearPublishing - год издания (отрицательный год означает год до н.э.).
   */
  public Book(String name, int numPages, String authors, int yearPublishing) {
    super(name, numPages);
    this.authors = authors;
    this.yearPublishing = yearPublishing;
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
      Book obj = (Book) other;
      return authors.equals(obj.authors) && yearPublishing == obj.yearPublishing;
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
    return super.hashCode() + Objects.hash(authors, yearPublishing);
  }

  /**
   * Переопределенный метод строкового представления объекта.
   * 
   * @return строку с описанием объекта.
   */
  @Override
  public String toString() {
    return super.toString() + ";  Автор(ы): " + authors + ";  Год издания: "
        + ((yearPublishing < 0) ? yearPublishing + " до н.э." : yearPublishing);
  }

}
