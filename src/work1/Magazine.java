package work1;

import java.util.Objects;

/**
 * Класс `Журнал`.
 */
public class Magazine extends PrintedEdition {

  /** Тематика */
  public String subjectMatter;
  
  /** Выпуск */
  public int issue;

  /**
   * Конструктор класса.
   */
  public Magazine() {
    this("Хакер", 50, "Информационные технологии", 73);
  }

  /**
   * Конструктор класса.
   * 
   * @param name          - название.
   * @param numPages      - количество страниц.
   * @param subjectMatter - тематика журнала.
   * @param issue         - номер выпуска.
   */
  public Magazine(String name, int numPages, String subjectMatter, int issue) {
    super(name, numPages);
    this.subjectMatter = subjectMatter;
    this.issue = issue;
  }

  /**
   * Переопределенный метод сравнения объектов.
   * 
   * @return true, если объект, созданный на основе текущего класса, равен объекту из
   *         аргумента, иначе - false.
   */
  @Override
  public boolean equals(Object other) {
    if (super.equals(other)) {
      Magazine obj = (Magazine) other;
      return subjectMatter.equals(obj.subjectMatter) && issue == obj.issue;
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
    return super.hashCode() + Objects.hash(subjectMatter, issue);
  }

  /**
   * Переопределенный метод строкового представления объекта.
   * 
   * @return строку с описанием объекта.
   */
  @Override
  public String toString() {
    return super.toString() + ";  Тематика: " + subjectMatter + ";  Выпуск: " + issue;
  }

}
