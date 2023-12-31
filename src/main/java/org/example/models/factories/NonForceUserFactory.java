package org.example.models.factories;

import org.example.models.RepublicSoldier;
import org.example.models.Trooper;

public class NonForceUserFactory implements CharactersFactory {


    @Override
    public ForceUser produceForceUser(String type) {
 return null;
    }

    @Override
    public NonForceUser produceNonForceUser(String type) {
        type = type.toLowerCase();
        if (type.equals("trooper")){
            return new Trooper();
        } else if (type.equals("republic soldier")) {
            return new RepublicSoldier();

        }else {
            System.out.println("Non force user characters can be trooper or republic soldier only ! ");
            return null;
        }
    }
}