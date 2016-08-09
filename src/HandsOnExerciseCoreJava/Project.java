package HandsOnExerciseCoreJava;

import java.util.ArrayList;
import java.util.HashMap;

public class Project implements IProject {

    private int projectId;
    private String name;
    private String details;
    private double projectCost = 0;
    private double totalDonationAmount = 0;
    private double pendingAmount = 0;
    private ArrayList<String> photos = new ArrayList<String>();
    // private ArrayList<Donor> donors = new ArrayList<Donor>();

    private HashMap<Donor, Integer> donorAmountDonated = new HashMap<Donor, Integer>();

    public Project() {

    }

    public Project(int projectId, String name, String details, double projectCost, ArrayList<String> photos) {
        this.projectId = projectId;
        this.name = name;
        this.details = details;
        this.projectCost = projectCost;
        this.photos = photos;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(double projectCost) {
        this.projectCost = projectCost;
    }

    public double getDonationAmount() {
        return totalDonationAmount;
    }

    public void setDonationAmount(double donationAmount) {
        this.totalDonationAmount = donationAmount;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public double getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(double pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public HashMap<Donor, Integer> getDonorAmountDonated() {
        return donorAmountDonated;
    }

    public void receiveDonation(Integer amount, Donor donor) {
        this.totalDonationAmount += amount;

        if (!donorAmountDonated.containsKey(donor)) {
            donorAmountDonated.put(donor, amount);
        } else {
            donorAmountDonated.put(donor, donorAmountDonated.get(donor) + amount);
        }

    }

}
