
public class Mammal extends Animal {
  MammalType type;

  public Mammal() {
  }

  public MammalType getType(){
    return type;
  }

  public void setType(MammalType type) {
    this.type = type;
  }
}
