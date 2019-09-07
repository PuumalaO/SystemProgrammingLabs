public  class Animal {

//  private static int numberOfCreatedAnimals = 0;

  private int number;
  private String name;

  public Animal() {}

  public void setNumber(int num) {
    this.number = num;
  }

  public int getNumber() {
    return number;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

/*
  static Animal createAnimal(int number, String name) {
    Animal newObject = new Animal();

    newObject.number = number;
    newObject.name = name;
    numberOfCreatedAnimals++;
    return newObject;
  }
*/
}
