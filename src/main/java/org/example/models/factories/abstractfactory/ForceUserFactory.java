package org.example.models.factories.abstractfactory;





public class ForceUserFactory implements CharactersFactory {

    @Override
    public ForceUser produceForceUser(String type) {
        return null;
    }

    @Override
    public NonForceUser produceNonForceUser(String type) {
        return null;
    }
}
