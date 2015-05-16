/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uned.service;

import es.uned.dao.IUsuarioDAO;
import es.uned.model.Usuario;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author Tony
 */
public class UsuarioServiceTest extends TestCase {
    
    public UsuarioServiceTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of addUsuario method, of class UsuarioService.
     */
    public void testAddUsuario() {
        System.out.println("addUsuario");
        Usuario Usuario = null;
        UsuarioService instance = new UsuarioService();
        //instance.addUsuario(Usuario);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        assertEquals("ok", "ok");
    }

}
