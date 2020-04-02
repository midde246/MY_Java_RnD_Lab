package com.col.mapping;

public class Person {
	private String name;
	private String adharNo;

	
	public Person(String name, String adharNo) {
		this.name = name;
		this.adharNo = adharNo;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdharNo() {
		return adharNo;
	}
	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adharNo == null) ? 0 : adharNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Person other = (Person) obj;
		if (adharNo == null) {
			if (other.adharNo != null)
				return false;
		} else if (!adharNo.equals(other.adharNo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		 boolean isEqual = false;
//		 System.out.println("Inside Equals() ");
//		 if(obj != null && obj instanceof Person) {
//			 Person person = (Person) obj;
//			 if(person.getName().equals(name) && person.getAdharNo().equals(adharNo))  {
//				 isEqual = true;
//				 System.out.println("Inside Equals() ");
//			 }
//		 }
//		 
//		 
//		 return isEqual;
//	}
	
	
	
	 
	
}
