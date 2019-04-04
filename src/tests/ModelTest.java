package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import junit.framework.TestCase;
import smrt2.Model;
import smrt2.Ode;

public class ModelTest extends TestCase {
	
	private Model m;
	private String pathToSave = "./data/ModelTestSave.dumb";
	
	public void setUp() {
		m = new Model();
	}
	
	
	public void testModelCreation() {
		Model m = new Model();
		assertNotNull(m);
	}
	
	public void testModelHasName() {
		Model m = new Model("Name");
		assertEquals("Name", m.getName());
	}
	
	public void testModelSetName() {
		m.setName("Tim");
		assertEquals("Tim", m.getName());
	}
	
	public void testAddParameter() throws Exception {
		m.addParameter("a");
		assertTrue(m.getParameters().contains("a"));
	}
	
	public void testAddParameters() throws Exception {
		m.addParameter("a");
		m.addParameter("b");
		assertTrue(m.getParameters().contains("a"));
		assertTrue(m.getParameters().contains("b"));
	}	
	
	public void testDuplicateParameters() {
		try {
			m.addParameter("a");
			m.addParameter("a");
			fail("Exception not raised");
		} catch (Exception e) {
			String m = e.getMessage();
			assertEquals("Duplicate parameters not allowed", m);
		}
		
	}
	
	public void testAddState() throws Exception {
		m.addState("a");
		assertTrue(m.getStates().contains("a"));
	}
	
	public void testAddStates() throws Exception {
		m.addState("a");
		m.addState("b");
		assertTrue(m.getStates().contains("a"));
		assertTrue(m.getStates().contains("b"));
	}	
	
	public void testDuplicateStates() {
		try {
			m.addState("a");
			m.addState("a");
			fail("Exception not raised");
		} catch (Exception e) {
			String m = e.getMessage();
			assertEquals("Duplicate states not allowed", m);
		}
		
	}
	
	public void testAddOde() {
		
		Ode testODE = new Ode("A", "k");
		String expected = testODE.toString();
		
		m.addOde("A", "k");
		
		String actual = m.getOdeList().get(0).toString();
		
		assertEquals(expected, actual);	
	}
	
	public void testDisplayOdes(){
		m.addOde("A", "k1");
		m.addOde("B", "k2");
		String[][] actual = m.displayOdeList();
		String[][] expected = {{"dA/dt", "k1"}, {"dB/dt", "k2"}}; 
		for (int i = 0; i < actual.length; i++) {
			for (int j = 0; j < actual[i].length; j++) {
				assertEquals(expected[i][j], actual[i][j]);
			}
			
		}
	}
	
	public void testModelIsSavable() {
		try {
			FileOutputStream fileOut = new FileOutputStream(pathToSave);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(m);
			objectOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("File not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Something wrong with IO.");
		}
	}
	
	public void testModelLoadsCorrectly() {
		m.setName("Name of this model");
		testModelIsSavable();
		
		try {
			FileInputStream fileIn = new FileInputStream(pathToSave);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Model b = (Model) objectIn.readObject();
			objectIn.close();
			assertEquals(m.getName(), b.getName());
		} catch (Exception e) {
			// TODO: handle exception
			fail("Something went wrong with loading model");
		}
	}
	
	public void testSaveModel() {
		// Model should have a method to save its own instance.
		fail("Not implemented");
	}
	
	public void testLoadModel() {
		// model should have a static method that loads a saved model from file and returns THAT instance.
		// Can sort of be used instead of the constructor.
		fail("Not implemented");
	}
	
	
	

}