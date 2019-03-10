package classes;

public class TestClassesFunctions {
	public static void main(String[] args) {
		/*Person person1 = new Person("Rihards", "Cirulis", "21049511913");
		System.out.println("ID: "+person1.getID());
		System.out.println("Vârds: "+person1.getName());
		System.out.println("Uzvârds: "+person1.getSurname());
		System.out.println("Personas kods: "+person1.getPersonCode());
		System.out.println("");
		Person person2 = new Person("Andris", "Mielavs", "25068310235");
		System.out.println("ID: "+person2.getID());
		System.out.println("Vârds: "+person2.getName());
		System.out.println("Uzvârds: "+person2.getSurname());
		System.out.println("Personas kods: "+person2.getPersonCode());
		System.out.println("");
		Person person_default = new Person();
		System.out.println("ID: "+person_default.getID());
		System.out.println("Vârds: "+person_default.getName());
		System.out.println("Uzvârds: "+person_default.getSurname());
		System.out.println("Personas kods: "+person_default.getPersonCode());
		System.out.println("");*/
		appClient client_default = new appClient();
		System.out.println("ID: "+client_default.getID());
		System.out.println("Vârds: "+client_default.getName());
		System.out.println("Uzvârds: "+client_default.getSurname());
		System.out.println("Personas kods: "+client_default.getPersonCode());
	}
}
