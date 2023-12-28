package org.example.models.factories;


import org.example.models.Jedi;
import org.example.models.Sith;

public class ForceUserFactory implements CharactersFactory {

    @Override
    public ForceUser produceForceUser(String type) {
        if (type.equals("jedi")){
            return new Jedi();
        } else if (type.equals("sith")) {
            return new Sith();
        }else {
            System.out.println("Force user characters can be sith or jedi only ! ");
            return null;
        }
    }

    @Override
    public NonForceUser produceNonForceUser(String type) {
        return null;
    }
}
