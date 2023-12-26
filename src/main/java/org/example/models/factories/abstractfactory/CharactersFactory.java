package org.example.models.factories.abstractfactory;

public interface CharactersFactory {

    ForceUser produceForceUser(String type);
   NonForceUser produceNonForceUser(String type);

}
