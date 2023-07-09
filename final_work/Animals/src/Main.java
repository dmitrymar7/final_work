import controller.AnimalController;
import controller.IController;
import model.*;
import view.ViewOperationMainMenu;


public class Main {
    public static void main(String[] args) {

        IFileOperation fileOperation = new FileOperation("animals.txt");
        Repository repository = new Repository(new AnimalMapperJSON(), fileOperation);
        IController controller = new AnimalController(repository);
        ViewOperationMainMenu viewOperations = new ViewOperationMainMenu(controller);
        viewOperations.startView();

    }
}