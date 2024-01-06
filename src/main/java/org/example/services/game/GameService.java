package org.example.services.game;


import lombok.Data;
import org.example.models.Team;
import org.example.services.characters.CloneService;
import org.example.services.characters.ForceUserService;
import org.example.services.game.composite.GameComponent;
import org.example.services.game.composite.TeamComposite;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Data
public class GameService {
    private ForceUserService forceUserService;
    private CloneService cloneService;
    private TeamComposite teamSith;
    private TeamComposite teamRepublic;
    private Scanner scanner = new Scanner(System.in);


    public GameService(ForceUserService forceUserService, CloneService cloneService, TeamComposite teamSith, TeamComposite teamRepublic) {
        this.forceUserService = forceUserService;
        this.cloneService = cloneService;
        this.teamSith = teamSith;
        this.teamRepublic = teamRepublic;
    }

    public void TeamsInitialization() throws SQLException {

        teamSith = new TeamComposite();
        teamSith.addAMember(forceUserService.getOneRandomSith());
        teamSith.addAMember(cloneService.produceClones(Team.SITH,3));

        teamRepublic = new TeamComposite();
        teamRepublic.addAMember(forceUserService.getOneRandomJedi());
        teamRepublic.addAMember(cloneService.produceClones(Team.REPUBLIC,3));

    }

    public void turnInsideTeam(TeamComposite teamName, Team chosenTeam){
        List<GameComponent> allUserTeamMembers = teamName.getTeamMembers();
        List<GameComponent> allMachineTeamMembers;
        Team actualTeam = teamName.getTeamName(teamName);

        if (actualTeam.equals(Team.SITH)){
            // get machine team members
            allMachineTeamMembers = getTeamRepublic().getTeamMembers();
        } else{
            allMachineTeamMembers = getTeamSith().getTeamMembers();
        }


        for (GameComponent member : allUserTeamMembers
             ) {

            int choice;
            if (member.isAlive()){
                do {
                    askUser();
                    choice = scanner.nextInt();
                    if (choice != 1 && choice != 2) {
                        System.out.println("Not a valid input! Please try again.");
                    }
                }while (choice != 1 && choice != 2);


                if (actualTeam.equals(chosenTeam)){
                    switch (choice){
                        case 1:
                            // command attack for user
                            break;
                        case 2:
                            // command heal for user
                            break;
                    }

                } else {
                    machineAction(allMachineTeamMembers);

                }

            }
        }
    }

    private void machineAction(List<GameComponent> allMachineTeamMembers) {
        // random attack or heal from machine for each team members
        for (GameComponent machineMember: allMachineTeamMembers
             ) {
            if (machineMember.isAlive()) {
                int action = Math.random() < 0.5 ? 1 : 2;

                if (action == 1){
                    // command attack
                }else {
                    // comand heal
                }
            }
        }
    }

    public void askUser (){
        System.out.println("You choose to Attack or Heal ?");
        System.out.println("1. Attack");
        System.out.println("2. Heal");
    }

    public void endGame() {

        if (!teamSith.isAlive() && !teamRepublic.isAlive()) {
            System.out.println("That's a draw ! ");
        } else if (teamSith.isAlive()) {
            System.out.println("Sith wins ! ");
        } else if (teamRepublic.isAlive()) {
            System.out.println("Republic wins ! ");
        }
    }

}






