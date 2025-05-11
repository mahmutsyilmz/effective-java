public class CarNoIoC {
    private Engine engine;

    public CarNoIoC(){
        this.engine = new Engine("Mercedes in ctor");
    }

    public void start(){
        System.out.println("Engine " + engine.getName() + " is on process without IoC");
    }
}
