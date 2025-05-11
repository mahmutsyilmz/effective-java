public class CarWithIoC {
    private Engine engine;

    public CarWithIoC(Engine engine){
        this.engine = engine;
    }

    public void start(){
        System.out.println("Engine " + engine.getName() + " is on process with IoC");
    }
}
