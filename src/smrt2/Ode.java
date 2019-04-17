package smrt2;
import java.io.Serializable;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import smrt2.EquationParser2;


public class Ode implements Serializable{
	// initialize variables
	// State and formula should not contain trailing and leading white spaces.
	private String formula;
	private String state;
	private String[] variables;
	private String[] operators;
	
	// create constructor
	@JsonCreator
	public Ode(@JsonProperty("state") String state,
			   @JsonProperty("formula") String formula){
		this.formula = formula;
		this.state = state;
		
		if (this.formula != null) {
			EquationParser2 parser = new EquationParser2(this.formula);
			this.variables = parser.getVariables();
			this.operators = parser.getOperators();
		}
	}
	
	// Getters and setters for variables
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String equation) {
		this.formula = equation;
		
	}
	
	@Override
	public String toString(){
		String stringOde = "";
		stringOde = "d" + this.state + "/dt = " + this.formula;
		return stringOde;
	}
	@JsonIgnore
	public String[] getVariables() {
		return this.variables;
	}
	@JsonIgnore
	public String[] getOperators() {
		return this.operators;
	}
	public String[] toArray() {
		String[] odeArray = {this.getState(),this.getFormula()};
		return odeArray;
	}
	
	public boolean testFormula(String formula) {
		String reconstructedFormula ="";
		if (variables.length > 0 && variables[0].isEmpty()){
			// the list that contains the empty string could be larger so index on the length of the other list 
			for (int j = 0; j < operators.length; j++) {
				reconstructedFormula += "1" + operators[j];
			}
		}
		
		//check if ode equation has an operator
		else if (operators.length > 0 && operators[0].isEmpty()) {
			for (int j = 0; j < variables.length; j++) {
				reconstructedFormula += operators[j] + "1";
			}
		}
		
		// if the variable list was larger that the operator list the last variable needs to be added and vice versa
		if (variables.length > operators.length){
			reconstructedFormula += "1";
		}
		
		if (variables.length < operators.length){
			reconstructedFormula += operators[operators.length-1];
		}
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		try {
			engine.eval(reconstructedFormula);
		} catch (ScriptException e) {
			return false;
		}
		return true;
	}
}
