package org.example.models.factories;

public interface CharactersFactory {

 ForceUser produceForceUser(String type);
 NonForceUser produceNonForceUser(String type);

}
