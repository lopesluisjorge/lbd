package teste;

import junit.framework.Test;
import junit.framework.TestSuite;
import teste.dao.ClienteDaoTest;
import teste.dao.FilmeDaoTest;
import teste.dao.VideoDaoTest;

public class Testes {

    public static Test suite(){
        TestSuite testSuite = new TestSuite();
        testSuite.addTest(FilmeDaoTest.suite());
        testSuite.addTest(ClienteDaoTest.suite());
        testSuite.addTest(VideoDaoTest.suite());
        return testSuite;
    }

}
