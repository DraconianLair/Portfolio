import java.util.*;
public class CrossProduct 
{
	public static void main (String [] args)
	{
		Scanner num = new Scanner(System.in);
		
		System.out.println("Enter the value of U1:   ");
		float U1 = num.nextFloat();
		System.out.println("Enter the value of U2:   ");
		float U2 = num.nextFloat();
		System.out.println("Enter the value of U3:   ");
		float U3 = num.nextFloat();
		System.out.println("Enter the value of V1:   ");
		float V1 = num.nextFloat();
		System.out.println("Enter the value of V2:   ");
		float V2 = num.nextFloat();
		System.out.println("Enter the value of V3:   ");
		float V3 = num.nextFloat();
		
		float UxVx = (U2*V3 - U3*V2);
		float UxVy = -1*(U1*V3-U3*V1);
		float UxVz = U1*V2-U2*V1;
		
		System.out.println("The answer is < " + UxVx + " , " + UxVy + " , " + UxVz + " >");
		
	}
}
