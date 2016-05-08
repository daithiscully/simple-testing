package com.david.simple_testing.models;

public class Step extends InisTest{

	private int id;
	private String action;
	private String actionData1;
	private String actionData2;

	public Step(int id, String action) {
		super();
		this.id = id;
		this.action = action;
	}

	public Step(int id, String action, String actionData1) {
		super();
		this.id = id;
		this.action = action;
		this.actionData1 = actionData1;
	}

	public Step(int id, String action, String actionData1, String actionData2) {
		super();
		this.id = id;
		this.action = action;
		this.actionData1 = actionData1;
		this.actionData2 = actionData2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionData1() {
		return actionData1;
	}

	public void setActionData1(String actionData1) {
		this.actionData1 = actionData1;
	}

	public String getActionData2() {
		return actionData2;
	}

	public void setActionData2(String actionData2) {
		this.actionData2 = actionData2;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((actionData1 == null) ? 0 : actionData1.hashCode());
		result = prime * result + ((actionData2 == null) ? 0 : actionData2.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Step other = (Step) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (actionData1 == null) {
			if (other.actionData1 != null)
				return false;
		} else if (!actionData1.equals(other.actionData1))
			return false;
		if (actionData2 == null) {
			if (other.actionData2 != null)
				return false;
		} else if (!actionData2.equals(other.actionData2))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Step [id=" + id + ", action=" + action + ", actionData1=" + actionData1 + ", actionData2=" + actionData2
				+ "]";
	}
	
}
