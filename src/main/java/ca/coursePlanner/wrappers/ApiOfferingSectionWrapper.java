package ca.coursePlanner.wrappers;

import ca.coursePlanner.model.Offering;

public class ApiOfferingSectionWrapper {
    public String type;
    public int enrollmentCap;
    public int enrollmentTotal;

    public static ApiOfferingSectionWrapper getOfferingSectionWrapper(Offering o) {
        ApiOfferingSectionWrapper result = new ApiOfferingSectionWrapper();
        result.type = o.getComponent();
        result.enrollmentCap = o.getEnrollmentCap();
        result.enrollmentTotal = o.getEnrollmentTotal();
        return result;
    }
}
