package teste.unitario;

import junit.framework.Test;
import junit.framework.TestSuite;
import teste.unitario.dao.ClienteDaoTest;
import teste.unitario.dao.EmprestimoDaoTest;
import teste.unitario.dao.FilmeDaoTest;
import teste.unitario.dao.VideoDaoTest;

final public class Testes {

    public static Test suite(){
        TestSuite testSuite = new TestSuite();
        testSuite.addTest(FilmeDaoTest.suite());
        testSuite.addTest(ClienteDaoTest.suite());
        testSuite.addTest(VideoDaoTest.suite());
        testSuite.addTest(EmprestimoDaoTest.suite());
        return testSuite;
    }

}
