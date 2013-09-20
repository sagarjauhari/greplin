package challenge1;

public class PrimeNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrimeNumbers pn = new PrimeNumbers();
		int a = pn.getSmallestPrimeFibNumber(227000);
		System.err.println(a);
		System.out.println(pn.sumPrimeDivisors(a + 1));

	}
	
	/**
	 * Computes the sum of prime divisors of x
	 * @param x
	 * @return
	 */
	int sumPrimeDivisors(int x){
		int sum = 0;
		int sqrt = (int) Math.sqrt(x);
		for(int i = 2; i <=sqrt; i++){
			if(x%i==0){
				if(isPrime(i)) sum+=i;
				if(isPrime(x/i)) sum+=(x/i);
			}
		}
		return sum;
	}
	
	/**
	 * Compute the smallest prime fibonacci number greater than x
	 * @param x
	 * @return
	 */
	int getSmallestPrimeFibNumber(int x){
		int i = 1;
		int ans = generateNthFibonacci(i++);
		while(ans <= 227000 || !isPrime(ans)){
			ans = generateNthFibonacci(i++);
		}
		return ans;
	}
	
	public boolean isPrime(int x){
		int sqrt = (int) Math.sqrt(x);
		for(int i = 2; i <=sqrt; i++){
			if(x%i==0) return false;
		}
		return true;
	}

	/**
	 * Generates all prime numbers using the optimised sieve method.
	 * 
	 * Pseudo code:
	 * 1. Write all numbers from 1 to n.
	 * 2. Find the 1st prime number after 1 (=2). Cross out every 2nd number starting from 2^2 = 4.
	 * 3. Find next prime number after 2 (=3). Cross out every 3rd number starting from 3^2 = 9.
	 * 4. Find next prime number after 3 ( = 5). Cross out every 5th number starting from 5^2 = 25.
	 * 5 .... continue up to squareRoot of 'n'.
	 * 
	 * @param x
	 *            the number to be checked
	 * @return true - number is prime <br>false - number is composite
	 */
	public int[] generatePrime(int x) {
		boolean[] vec = new boolean[x+1];
		int sqrt = (int) Math.sqrt(x);
		int idx = 2;
		int count = 0; //counts the number of primes starting from 2
		while(idx <= sqrt){
			if(vec[idx]) {idx++;	continue;}
			for(int j = (int)Math.pow(idx, 2); j <= x; j+=idx) vec[j] = true;
			idx++;
		}
		for(idx = 2; idx <= x; idx++){
			if(!vec[idx])count++;
		}
		int[] primes = new int[count];
		int i = 0;
		for(idx = 2; idx <= x; idx++){
			if(!vec[idx])primes[i++] = idx;
		}
		
		return primes;
	}

	int generateNthFibonacci(int n){
		int a = 0;
		int b = 1;
		
		if(n==1) return a;
		else if (n==2) return b;
		
		int c = 0;
		for(int i = 3; i <=n; i++){
			c = a+b;
			a = b;
			b = c;
		}
		return c;
	}
}
