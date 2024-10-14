package homework_9_10_2024;

public class Task_1 {

	class GenericTest<T> {
	    T a, b;

	    GenericTest(T a, T b) {
	        this.a = a;
	        this.b = b;
	    }
	    //Если объект реализует интерфейс Comparable, 
	    //это означает, что у него есть метод compareTo(), 
	    //с помощью которого можно сравнивать данный объект с другим объектом того же типа.
	    
	    public boolean aIsGreaterThanB() {
	        if (a instanceof Comparable && b instanceof Comparable) {
	            return ((Comparable<T>) a).compareTo(b) > 0;
	        }
	        return false;
	    }
	}

}
