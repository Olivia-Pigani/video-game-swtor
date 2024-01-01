//package org.example.services.game.command;
//
//import org.example.models.factories.ForceUser;
//import org.example.models.factories.NonForceUser;
//
//public class HealCommand implements Command{
//
//    private ForceUser forceUser;
//    private NonForceUser nonForceUser;
//
//
//    public HealCommand(ForceUser forceUser) {
//        this.forceUser = forceUser;
//    }
//
//    public HealCommand(NonForceUser nonForceUser) {
//        this.nonForceUser = nonForceUser;
//    }
//
//    @Override
//    public void execute() {
//        if (forceUser != null){
//            forceUser.heal();
//        } else if (nonForceUser != null ) {
//            nonForceUser.heal();
//
//        }
//    }
//}
