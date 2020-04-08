package ca.coursePlanner.wrappers;

import ca.coursePlanner.Observer.Observer;

public class ApiDepartmentWrapper {
    public long deptId;
    public String name;

    public static ApiDepartmentWrapper makeNewWrapper (long deptId, String name){
        ApiDepartmentWrapper result = new ApiDepartmentWrapper();
        result.deptId = deptId;
        result.name = name;
        return result;
    }
}