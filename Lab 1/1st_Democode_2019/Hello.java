class Hello
{
  String helloText = "Hello Class, beautiful day today!";

  public static void main(String args[]) {
    Hello myApp = new Hello();
    myApp.startApp();
    myApp.demonstrateThread();
  }

  public void demonstrateThread() {
    ExampleThread thread = new ExampleThread();
    thread.start();
  }

  public void startApp() {
    System.out.println("System is up and running!");
    System.out.println(helloText);

    /* Can't be done because Animal is Abstact */

    /*
    Animal seppo = new Animal();
    seppo.setName("Seppo Taalasmaa");
    seppo.setNumber(1);
    */

    MammalType humanType = new MammalType();
    humanType.typeName = "Human";
    humanType.typeNameLatin = "Homo Sapiens";
    humanType.typeId = 34;

    Mammal ulla = new Mammal();
    ulla.setName("Ulla Taalasmaa");
    ulla.setNumber(2);
    ulla.setType(humanType);

    Mammal ismo = new Mammal();
    ismo.setName("Ismo Taalasmaa");
    ismo.setType(humanType);
  }
}
