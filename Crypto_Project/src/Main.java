import app.Application;
import controller.MainContoller;
import entity.Result;
import view.ConsoleView;
import view.View;
public class Main {
    public static void main(String[] args) {
        View view = new ConsoleView();
        MainContoller mainContoller = new MainContoller(view);
        Application application = new Application(mainContoller);

        Result result = application.run();
        application.printResult(result);
    }
}