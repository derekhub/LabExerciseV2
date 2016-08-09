package HandsOnExerciseCoreJava;

import java.util.HashMap;

public interface IProject {
    
	public double getPendingAmount();
    public HashMap<Donor, Integer> getDonorAmountDonated();
	public void receiveDonation(Integer amount, Donor donor);  

}
