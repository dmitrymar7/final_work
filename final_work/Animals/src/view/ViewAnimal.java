package view;

import controller.IController;

import java.util.Scanner;

public abstract class ViewAnimal implements IViewAnimal{

    IViewAnimal prevView;
    IController controller;

    public ViewAnimal(IController controller, IViewAnimal prevView) {
        this.controller = controller;
        this.prevView = prevView;
    }

    public ViewAnimal(IController controller) {
        this.controller = controller;
    }

    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
