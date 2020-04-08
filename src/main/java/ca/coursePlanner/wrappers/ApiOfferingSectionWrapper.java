package ca.coursePlanner.wrappers;

import ca.coursePlanner.model.Offering;
import ca.coursePlanner.model.Section;

public class ApiOfferingSectionWrapper {
    public String type;
    public int enrollmentCap;
    public int enrollmentTotal;

    public static ApiOfferingSectionWrapper getOfferingSectionWrapper(Section s) {
        ApiOfferingSectionWrapper result = new ApiOfferingSectionWrapper();
        result.type = s.getType();
        result.enrollmentCap = s.getEnrollmentCap();
        result.enrollmentTotal = s.getEnrollmentTotal();
        return result;
    }
}
